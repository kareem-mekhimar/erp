/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.salesorder;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.clients.CustomerAcc;
import com.bridge.entities.clients.PriceListHeaders;
import com.bridge.entities.clients.PriceListLines;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.ClientType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.PaymentTerms;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.PriceListHeaderService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.modules.ReciveablesConfigurationService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.salesorder.SalesOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kareem
 */
@Named
@ViewScoped
public class SalesOrderDetailsController implements Serializable {

    @EJB
    private SalesOrderService salesOrderService;

    @EJB
    private ClientService clientService;

    @EJB
    private SiteService siteService;

    @EJB
    private SystemItemService systemItemService;

    @EJB
    private OrganizationUnitService unitService;

    @EJB
    private PriceListHeaderService listHeaderService;

    @EJB
    private CurrencyService currencyService;

    @EJB
    private ReciveablesConfigurationService receivableService;

    private Integer currentId;

    private OeOrderHeader currentOrder;

    private int no;

    private List<String> removedIds = new ArrayList<>();

    private List<PriceListLines> priceList;

    private PaymentTerms paymentTerms;

    private List<FndCurrency> currencies;

    public void loadOrder() {
        if (currentId != null) {
            currentOrder = salesOrderService.findWithLines(currentId);

            if (currentOrder == null) {
                JSFUtils.redirectTo404("Sales Order");
            } else {
                
                no = currentOrder.getLines().stream().mapToInt(OeOrderLine::getLineNumber).max().orElse(0);

                loadPriceList();
                loadCurrencies();
            }

        }

    }

    public List<OrganizationUnit> completeOperatingUnitsByName(String text) {
        return unitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);
    }

    public List<Client> completeCustomerByName(String name) {
        return clientService.findByName(ClientType.CUSTOMER, name);
    }

    public List<SystemItem> completeProducts(String text) {
        if (currentOrder.getInvoiceToSite() == null) {
            return null;
        }

        return priceList.stream().filter(l -> l.getSystemItem().getDescription().toUpperCase().startsWith(text.toUpperCase()))
                .map(PriceListLines::getSystemItem).collect(Collectors.toList());

//        String ids = currentOrder.getLines().stream()
//                .filter(l -> l.getInventoryItem() != null)
//                .map(l -> String.valueOf(l.getInventoryItem().getInventoryItemId()))
//                .collect(Collectors.joining(",")) ;
//                
//       if(ids.isEmpty())
//           
//           return systemItemService.findByNameAndType(text, SystemItemType.FINISHED_PRODUCT) ;
//       
//       return systemItemService.findByNameAndTypeAndNotIn(text, SystemItemType.FINISHED_PRODUCT, ids) ;
    }

    public List<ClientSite> completeSitesForCustomer(String text) {
        Client customer = currentOrder.getSoldToCustomers();

        if (customer == null) {
            return null;
        }

        return siteService.findSitesForClientByName(customer, text);
    }

    public void resetSites() {

        currentOrder.setShipToSite(null);

        currentOrder.setInvoiceToSite(null);
    }

    public void addLine() {
        List<OeOrderLine> lines = currentOrder.getLines();

        if (lines == null) {
            lines = new ArrayList<>();

            currentOrder.setLines(lines);
        }

        OeOrderLine line = new OeOrderLine();

        line.setHeader(currentOrder);

        line.setLineNumber(++no);

        lines.add(line);
    }

    public void onItemChange(OeOrderLine line) {
        line.setOrderQuantityUom(line.getInventoryItem().getPrimaryUomCode());
        line.setUnitListPrice(itemPrice(line.getInventoryItem()));
        line.setUnitSellingPrice(itemPrice(line.getInventoryItem()));
        onUnitPriceOrQuantityChange(line);
    }

    public void loadPriceList() {

        if (currentOrder.getInvoiceToSite() != null) {
            priceList = listHeaderService.findPriceListLines(currentOrder.getInvoiceToSite().getSiteId());
        }

    }

    public BigDecimal itemPrice(SystemItem item) {

        BigDecimal price;
        PriceListHeaders header = new PriceListHeaders();
        header.setListHeaderId(1);
        price = priceList.stream().filter(l -> l.getSystemItem().getInventoryItemId().equals(item.getInventoryItemId())).findFirst().get().getListPrice();
        return price;
    }

    public void onUnitPriceOrQuantityChange(OeOrderLine line) {
        BigDecimal unitPrice = line.getUnitSellingPrice();

        BigDecimal quantity = line.getOrderedQuantity();

        if (quantity != null && unitPrice != null) {
            line.setAmount(unitPrice.multiply(quantity));

            line.getHeader().setTotalAmount(
                    line.getHeader().getLines().stream().map(OeOrderLine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

    }

    public void validateSite(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        ClientSite site = (ClientSite) value;

        if (site.getCustomerAcc() == null) {

            throw new ValidatorException(new FacesMessage("Site Have no Account !"));
        }
    }

    public void removeLine(OeOrderLine line) {

        currentOrder.getLines().remove(line);

        if (line.getLineId() != null) {

            removedIds.add(line.getLineId().toString());
        }

        --no;
    }

    public List<PriceListLines> getPriceList() {
        if (priceList == null) {
            priceList = new ArrayList();
        }
        return priceList;
    }

    public void setPriceList(List<PriceListLines> priceList) {
        this.priceList = priceList;
    }

    public PaymentTerms[] getPaymentTerms() {
        return PaymentTerms.values();
    }

    public Integer getCurrentId() {
        return currentId;
    }

    public List<FndCurrency> getCurrencies() {
        if (currencies == null) {
            currencies = new ArrayList();
        }
        return currencies;
    }

    public void setCurrencies(List<FndCurrency> currencies) {
        this.currencies = currencies;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    public OeOrderHeader getCurrentOrder() {

        if (currentOrder == null) {
            currentOrder = new OeOrderHeader();
        }

        return currentOrder;
    }
    
    public String save()
    {
        if(currentOrder.getLines()==null){
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('order have no lines!');");
            return null;
        }
        if(currentOrder.getLines().isEmpty()){
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('order have no lines!');");
            return null;
        }
        
        salesOrderService.update(currentOrder) ;
                  
        if (!removedIds.isEmpty()) {

            String lineIdsToBeRemoved = removedIds.stream().collect(Collectors.joining(","));

            salesOrderService.removeLines(lineIdsToBeRemoved);
        }

        return "view?faces-redirect=true";
    }

    public void loadCurrencies() {

        if (currentOrder.getOrg() == null) {
            return;
        }

        currencies = currencyService.findValidCurrencies(currentOrder.getOrg().getGlId());
    }

    public void validateOperatingUnitReceivableConfiguration(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        OrganizationUnit org = (OrganizationUnit) value;

        if (receivableService.findByOperating(org) == null) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Receivable Setting Not Installed in This Operating Unit", "Receivable Setting Not Installed in This Operating Unit"));

        }
    }

}
