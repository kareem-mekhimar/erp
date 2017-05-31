/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientContact;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.po.PoHeader;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.ClientType;
import com.bridge.enums.PoLineType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.PoStatus;
import com.bridge.enums.SystemItemType;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.modules.PayableModuleConfigurationService;
import com.bridge.services.po.PurchaseOrderService;
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

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class PoDetailsController implements Serializable {

    @EJB
    private PurchaseOrderService poService;

    @EJB
    private SiteService siteService;

    @EJB
    private ClientService clientService;

    @EJB
    private OrganizationUnitService unitService;

    @EJB
    private CurrencyService currencyService;

    @EJB
    private SystemItemService systemItemService;
    
    @EJB
    private PayableModuleConfigurationService payableService;

    private Integer currentPoId;

    private PoHeader currentPo;

    private int no;

    private PoLineType[] lineTypes;

    private List<String> removedIds = new ArrayList<>();

    private List<SystemItem> systemItemList;

    private PaymentTerms[] paymentTerms;
    
    private List<FndCurrency> currencies;

    public void addLine() {
        List<PoLine> poLines = currentPo.getPoLines();

        if (poLines == null) {
            poLines = new ArrayList<>();

            currentPo.setPoLines(poLines);
        }

        PoLine line = new PoLine();

        line.setLineNum(++no);

        poLines.add(line);

        line.setPoHeader(currentPo);
    }

    public List<FndCurrency> getCurrencies() {
      
        if(currencies==null){
        
            currencies=new ArrayList();
        }         
        return currencies;
    }

    public void setCurrencies(List<FndCurrency> currencies) {
        this.currencies = currencies;
    }
    
    
    

    public void loadCurrencies() {
        
        if(currentPo.getOrg()==null){
            return;
        }
        
        currencies= currencyService.findValidCurrencies(currentPo.getOrg().getGlId());
    }

    public PoHeader getCurrentPo() {

        if (currentPo == null) {
            currentPo = new PoHeader();
            currentPo.setPoStatus(PoStatus.INCOMPLETE);
        }

        return currentPo;
    }

    public PoLineType[] getLineTypes() {
        return PoLineType.values();
    }

    public void onItemSelectChange(PoLine line) {
        line.setPrimaryUom(line.getSystemItem().getPrimaryUomId());

        line.setTaxable(line.getSystemItem().getTaxableFlag());
    }

    public void loadPo() {

        if (currentPoId != null) {

            currentPo = poService.findPoWithLines(currentPoId);

            currentPo.getPoLines().stream().mapToInt(PoLine::getLineNum).max().ifPresent(max -> no = max);
            
            loadCurrencies();
        }
    }

    public void validatePayableInOperating(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        OrganizationUnit org = (OrganizationUnit) value;

        if (payableService.findByOperating(org) == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Payable Setting Not Installed in This Operating Unit", "Payable Setting Not Installed in This Operating Unit"));
        }
    }

    public List<OrganizationUnit> completeOperatingUnitsByName(String text) {
       
        return unitService.findOperatingHavePayableSetupByName(text) ;
    }

    public List<Client> completeClientByName(String text) {
        return clientService.findByName(ClientType.SUPPLIER, text);
    }

    public List<ClientSite> completeSitesForClient(String text) {

        Client client = JSFUtils.evaluateValueExpression("#{poDetailsController.currentPo.client}", Client.class);

        if (client == null) {
            return null;
        }

        return siteService.findSitesForClientByName(client, text);
    }

    public List<ClientContact> completeContactsForSite(String text) {

        ClientSite site = JSFUtils.evaluateValueExpression("#{poDetailsController.currentPo.clientSite}",
                ClientSite.class);

        if (site == null) {
            return null;
        }

        return siteService.findContactsForSiteByName(site, text);
    }

    public void resetSite() {
        currentPo.setClientSite(null);

        currentPo.setClientContact(null);
    }

    public void resetContact() {
        currentPo.setClientContact(null);
    }

    public void onUnitPriceOrAmountChange(PoLine line) {
        BigDecimal unitPrice = line.getListPricePerUnit();

        BigDecimal quantity = line.getQuantityOrdered();

        if (quantity != null && unitPrice != null) {
            line.setAmount(unitPrice.multiply(quantity));

            calculateTotalAmount();
        }

    }

    public List<SystemItem> completeSystemItems(String text) {

        PoLine line = (PoLine) JSFUtils.evaluateValueExpression("#{line}", PoLine.class);
        PoLineType lineType = line.getPoLineType();

        SystemItemType itemType = SystemItemType.INGREDIENT;
        if (lineType == PoLineType.ASSET) {
            itemType = SystemItemType.FIXED_ASSET;
        }
        if (lineType == PoLineType.SERVICE) {
            itemType = SystemItemType.SERVICE;
        }

        return systemItemService.findByNameAndType(text, itemType);

    }

    public void removeLine(PoLine line) {

        currentPo.getPoLines().remove(line);
        if (line.getPoLineId() != null) {
            removedIds.add(line.getPoLineId().toString());
        }
    }

    private void calculateTotalAmount() {
        BigDecimal total = currentPo.getPoLines().stream().filter(line -> line.getAmount() != null)
                .map(PoLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        currentPo.setTotalAmount(total);
    }

    public String save() {

        poService.update(currentPo);

        if (!removedIds.isEmpty()) {
            String lineIdsToBeRemoved = removedIds.stream().collect(Collectors.joining(","));

            poService.removeLines(lineIdsToBeRemoved);
        }
        return "view?faces-redirect=true";
    }

    public PaymentTerms[] getPaymentTerms() {
        return PaymentTerms.values();
    }

    public Integer getCurrentPoId() {
        return currentPoId;
    }

    public void setCurrentPoId(Integer currentPoId) {
        this.currentPoId = currentPoId;
    }

    public List<ClientSite> completeSupplingSitesForClient(String text) {

        Client client = JSFUtils.evaluateValueExpression("#{poDetailsController.currentPo.client}", Client.class);

        if (client == null) {
            return null;
        }

        return siteService.findSupplingSitesForClientByName(client, text);
    }

    public List<ClientSite> completePaymentSitesForClient(String text) {

        Client client = JSFUtils.evaluateValueExpression("#{poDetailsController.currentPo.client}", Client.class);

        if (client == null) {
            return null;
        }

        return siteService.findPaymentSitesForClientByName(client, text);
    }
    
    public void validateOperatingUnitPayableConfiguration(FacesContext context, UIComponent component, Object value) {
        
        if (value == null) {
            return;
        }

        OrganizationUnit org = (OrganizationUnit) value;

        if (payableService.findByOperating(org) == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Payable Setting Not Installed in This Operating Unit", "Payable Setting Not Installed in This Operating Unit"));
        }
    }
    

}
