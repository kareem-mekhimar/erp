/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.personsales;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.hr.People;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.personsales.ReservationLines;
import com.bridge.entities.personsales.ReservationOrder;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.ReservationLineType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.ReservationOrderType;
import com.bridge.services.hr.PeopleService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.personsales.ReservationOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class ReservationOrderDetailsController implements Serializable {

    @EJB
    private ReservationOrderService reservationOrderService;
    @EJB
    private OrganizationUnitService orgUnitService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private SubInvService subInvService;
    @EJB
    private PeopleService peopleService;
    @EJB
    private SystemItemService itemService;
    @EJB
    private TransactionService transService;

    @Inject
    private NotificationController notificationController ;
    
    private BigDecimal balance;

    private Integer orderId;

    private ReservationOrder currentOrder;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ReservationOrder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(ReservationOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<OrganizationUnit> completeOperatingUnit(String text) {
        return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);
    }

    public List<MainInventory> completeMainInventory(String text) {
        if (currentOrder.getOperatingUnitId() == null) {
            return null;
        }
        return mainInvService.findByNameAndParentOperatingUnitId(text, currentOrder.getOperatingUnitId().getOrgUnitId());
    }

    public List<SecondaryInventory> completeSubInventory(String text) {
        return subInvService.findByNameForMain(text, currentOrder.getMainInvId());
    }

    public List<People> completePerson(String text) {
        return peopleService.findAgentPerson(text);
    }

    public List<SystemItem> completeProducts(String text) {

        ReservationLines line = (ReservationLines) JSFUtils.evaluateValueExpression("#{line}", ReservationLines.class);

        if (line.getSubInvId() == null) {
            return null;
        }

        List<SystemItem> newList = itemService.findGoodsForSaleInInventory(line.getSubInvId().getSecondaryInventoryId(), text);

        newList.removeAll(currentOrder.getReservationLines().stream()
                .filter(l -> !l.equals(line) && l.getSubInvId().equals(line.getSubInvId()) && (l.getStatus() == ReservationLineType.NEW || l.getStatus() == ReservationLineType.RESERVED))
                .map(ReservationLines::getInventoryItemId).collect(Collectors.toList()));

        return newList;
    }

    public void resetLine(ReservationLines line){
        line.setInventoryItemId(null);
        line.setItemBalance(BigDecimal.ZERO);
        line.setReservedQty(null);
    }
    
    
    public void loadOrder() {

        if (orderId != null) {

            currentOrder = reservationOrderService.findOrderWithLines(orderId);
            
            if(currentOrder == null){
                
                JSFUtils.redirectTo404("reservation Order");
            }

        }else{
            
            currentOrder = new ReservationOrder();
            currentOrder.setStatus(ReservationOrderType.OPENED);
            currentOrder.setReservationLines(new ArrayList());
        }
        



    }

    public void setBalance(ReservationLines line) {
        
        if(currentOrder.getReservationDate()==null){
            return;
        }
        
        line.setReservedQty(null);
        
        Date txDate=currentOrder.getReservationDate();
        
        LocalDate localDate = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        balance = transService.findNotReservedQuantityUntilDate(line.getInventoryItemId().getInventoryItemId(), line.getSubInvId().getSecondaryInventoryId(),localDate);

        currentOrder.getReservationLines().forEach(l -> {
            if (line.getSubInvId().equals(l.getSubInvId())
                    && line.getInventoryItemId().equals(l.getInventoryItemId()) && l.getStatus() == ReservationLineType.RELEASED) {
                balance = balance.add(l.getReturnedQty());
            }
        });

        line.setItemBalance(balance);
    }

    public void onMessageRecieved() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ExternalContext context = facesContext.getExternalContext();

        String msg = context.getRequestParameterMap().get("data");

        int sourceId = 0, targetId = 0;

        if (msg.contains(",")) {
            String[] ids = msg.split(",");

            sourceId = Integer.parseInt(ids[0]);

            targetId = Integer.parseInt(ids[1]);
        } else // RECIEVE ONLY
        {
            targetId = Integer.parseInt(msg);
        }

        if (currentOrder == null) // TO BE CHANGED
        {
            return;
        }

        List<ReservationLines> lines = currentOrder.getReservationLines();
              Date txDate = currentOrder.getReservationDate();

                if (txDate == null) {
                    return;
                }

            lines.stream().forEach(l -> {
                
                if(l.getSubInvId()!= null && l.getInventoryItemId() !=null){

          

                LocalDate localDate = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                BigDecimal qty = transService.findNotReservedQuantityUntilDate(l.getInventoryItemId().getInventoryItemId(),
                        l.getSubInvId().getSecondaryInventoryId(), localDate);

                if (qty == null) {
                    qty = BigDecimal.ZERO;
                }

                l.setItemBalance(qty);
                
                }});

            notificationController.showNotification("Some Quantities has been updated by another user...", msg);
    }

    public void addLine() {

        ReservationLines line = new ReservationLines();

        line.setSubInvId(currentOrder.getSubInvId());

        line.setReservationId(currentOrder);

        line.setStatus(ReservationLineType.NEW);

        currentOrder.getReservationLines().add(line);

    }

    public void removeLine(ReservationLines line) {

        currentOrder.getReservationLines().remove(line);
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date orderDate = (Date) value;

        if (orderDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not Reserve after today"));
        }
    }

    public void validateQtyGtZeroOrNotAvailableInInv(FacesContext context, UIComponent component, Object value) {

        ReservationLines line = (ReservationLines) JSFUtils.evaluateValueExpression("#{line}", ReservationLines.class);

        BigDecimal actualQty = line.getItemBalance();

        BigDecimal quantity = (BigDecimal) value;

        if (quantity == null) {
            return;
        }

        if (quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't Be Zero", "Value Can't Be Zero"));
        }

        if (quantity.compareTo(actualQty) > 0) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't Exceed Inventory Quantity", "Value Can't Exceed Inventory Value"));

        }

    }

    public void releaseLine(ReservationLines line) {

        line.setStatus(ReservationLineType.RELEASED);
        line.setReturnedQty(line.getReservedQty().subtract(line.getProcessedQty()));
    }

    public String save() {
        reservationOrderService.updateReservation(currentOrder);

        return "reservationOrderView?faces-redirect=true";
    }
}
