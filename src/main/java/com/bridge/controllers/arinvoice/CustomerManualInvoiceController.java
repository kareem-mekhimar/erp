/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ClientType;
import com.bridge.enums.InvoiceType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CustomerManualInvoiceController implements Serializable {
    
    @EJB
    private ArInvoiceService invoiceService ;
    
    @EJB
    private ClientService clientService ;
    
    @EJB
    private OrganizationUnitService orgUnitService ;
    
    @EJB
    private SiteService siteService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private List<FndCurrency> currencies ;
    
    private ArInvoice currentInvoice   ;

    @PostConstruct
    private void init()
    {
       currencies = currencyService.findAll() ;
    }
    
    public List<Client> completeCustomerByName(String text)
    {
       return clientService.findByName(ClientType.CUSTOMER, text) ;
    }
    
    public List<ClientSite> completeSites(String text) {

        if (currentInvoice.getClient() == null) {
           
            return null;
        }

        return siteService.findSitesForClientByName(currentInvoice.getClient(), text);
    }    
   
    
    public List<OrganizationUnit> completeOperatingUnit(String text) {

        return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

    }    
    
    public void validateInvoiceAmount(FacesContext context,UIComponent component ,Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero Or less", "Value Can't Be Zero Or Less")) ;
       }
       
    }
    
    
    public void save()
    {
        invoiceService.create(currentInvoice); ;
        
        currentInvoice = null ;
        
        notificationController.showNotification("Invoice Created...", NotificationController.SUCCESS);
    }
    
    public void onClientChange()
    {
       currentInvoice.setClientSite(null);
    }
    
    public ArInvoice getCurrentInvoice() {
      
        if(currentInvoice == null)
            
            currentInvoice = new ArInvoice() ;
        
        return currentInvoice;
    }

    public void setCurrentInvoice(ArInvoice currentInvoice) {
        this.currentInvoice = currentInvoice;
    }
    
    public List<InvoiceType> getInvoiceTypes()
    {
        return Arrays.asList(InvoiceType.CREDIT,InvoiceType.DEBIT,InvoiceType.PREPAYMENT) ;
    }
    
    
   public List<FndCurrency> getAllCurrencies() {
      
       return currencies ;
   }    
}
