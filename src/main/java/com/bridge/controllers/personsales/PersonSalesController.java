/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.personsales;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.clients.PriceListLines;
import com.bridge.entities.personsales.ReservationOrder;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.ClientType;
import com.bridge.enums.ReservationLineType;
import com.bridge.enums.SalesOrderStatus;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.PriceListHeaderService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.personsales.ReservationOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class PersonSalesController implements Serializable {

    @EJB
    private ReservationOrderService reservationOrderService;
    @EJB
    private PriceListHeaderService priceListService;
    @EJB
    private ClientService clientService;
    @EJB
    private SiteService siteService;

    private Integer orderId;

    private Integer no = 0;

    private ReservationOrder currentReservation;

    private OeOrderHeader currentSalesOrder;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ReservationOrder getCurrentReservation() {
        return currentReservation;
    }

    public void setCurrentReservation(ReservationOrder currentReservation) {
        this.currentReservation = currentReservation;
    }

    public OeOrderHeader getCurrentSalesOrder() {
        return currentSalesOrder;
    }

    public void setCurrentSalesOrder(OeOrderHeader currentSalesOrder) {
        this.currentSalesOrder = currentSalesOrder;
    }

    public void loadOrder() {
        if (orderId != null) {
            currentReservation = reservationOrderService.findOrderWithLines(orderId);
        }
        if (currentReservation != null) {
            createSalesOrder(currentReservation);
        }
    }

    public void createSalesOrder(ReservationOrder order) {

        currentSalesOrder = new OeOrderHeader(order);

        List<OeOrderLine> lines = order.getReservationLines().stream()
                .filter(l -> l.getStatus() == ReservationLineType.RESERVED)
                .map(OeOrderLine::new)
                .collect(Collectors.toCollection(ArrayList::new));
        lines.forEach(l -> {
            l.setLineNumber(no + 1);
            l.setHeader(currentSalesOrder);
        });

        currentSalesOrder.setLines(lines);
    }

    public void setItemsPrice() {

        List<PriceListLines> priceList = priceListService.findPriceListLines(currentSalesOrder.getInvoiceToSite().getSiteId());

        currentSalesOrder.getLines().forEach(l -> {
            if (priceList.stream().filter(m -> m.getSystemItem().equals(l.getInventoryItem())).count() > 0) {
                l.setUnitListPrice(priceList.stream().filter(m -> m.getSystemItem().equals(l.getInventoryItem())).findFirst().get().getListPrice());
            } else {
                l.setUnitListPrice(BigDecimal.ZERO);
                l.setOrderedQuantity(null);
            }

            if (l.getOrderedQuantity() != null) {
                l.setAmount(l.getOrderedQuantity().multiply(l.getUnitListPrice()));
            } else {
                l.setAmount(BigDecimal.ZERO);
            }
        });
        
        calcTotalAmount();
        
    }
    
    public void resetSite(){
        currentSalesOrder.setInvoiceToSite(null);
    }

    public void calcAmount(OeOrderLine line) {

        if (line.getOrderedQuantity() != null) {
            line.setAmount(line.getOrderedQuantity().multiply(line.getUnitListPrice()));
        } else {
            line.setAmount(BigDecimal.ZERO);
        }
        calcTotalAmount();
    }

    public void calcTotalAmount() {

        currentSalesOrder.setTotalAmount(BigDecimal.ZERO);

        currentSalesOrder.getLines().forEach(l -> {
            if (l.getOrderedQuantity() != null) {

                currentSalesOrder.setTotalAmount(currentSalesOrder.getTotalAmount().add(l.getAmount()));
            }
        });
    }

    public void validateQuantity(FacesContext context, UIComponent component, Object value) {

        OeOrderLine line = (OeOrderLine) JSFUtils.evaluateValueExpression("#{line}", OeOrderLine.class);

        if (value == null) {
            return;
        }

        BigDecimal qty = (BigDecimal) value;

        if (qty.compareTo(BigDecimal.ZERO) <= 0 || qty.compareTo(line.getQtyToPick()) > 0) {
            throw new ValidatorException(new FacesMessage("Not valid Qty!"));
        }
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date orderDate = (Date) value;

        if (orderDate.compareTo(currentSalesOrder.getRequestDate()) < 0) {
            throw new ValidatorException(new FacesMessage("can not order before request date " + currentSalesOrder.getRequestDate()));
        } else if (orderDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not order after today"));
        }
    }

    public List<Client> completeCustomerByName(String name) {
        return clientService.findByName(ClientType.CUSTOMER, name);
    }

    public List<ClientSite> completeSitesForCustomer(String text) {

        Client customer = currentSalesOrder.getSoldToCustomers();

        if (customer == null) {
            return null;
        }

        return siteService.findSitesForClientByName(customer, text);
    }

    public String save() {

        reservationOrderService.saveMession(currentSalesOrder, currentReservation);

        return "reservationOrderView?faces-redirect=true";
    }

}
