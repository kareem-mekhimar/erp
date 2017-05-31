/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.ApInvoiceLine;
import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.ApSupplierSite;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.po.PoHeader;
import com.bridge.enums.ClientType;
import com.bridge.enums.InvoiceType;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.apinvoice.ApInvoiceService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.po.PurchaseOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
public class PoSupplierInvoicesController implements Serializable{
    
    @EJB
    private PurchaseOrderService purchaseOrderService ;

    @EJB
    private ClientService clientService ;
    
    @EJB
    private SiteService siteService ;
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    @Inject
    private Conversation conversation ;

    private Client currentClient ;
    
    private Integer currentPoNo ;
        
    private List<PoHeader> poHeaders ;
    
    private List<PoHeader> selectedHeaders ;

    private List<ApInvoice> currentInvoices ;

    public void startConversation()
    {
       if(conversation.isTransient()) 
           
           conversation.begin();
    }
    
    public List<Client> completeSupplierByName(String text)
    {
       return clientService.findByName(ClientType.SUPPLIER, text) ;
    }
    
    
    public List<ClientSite> completeSitesForSupplier(String text)
    {
       Client client = JSFUtils.evaluateValueExpression("#{invoice.client}", Client.class) ;
       
       return siteService.findSitesForClient(client);
    }
    
    
    public void filter()
    {
       poHeaders = purchaseOrderService.findApprovedPosNeededToInvoiceByClient(currentClient, currentPoNo) ;
       
    }
    
    public String createInvoices()
    {
       String selectedPoIdsDelmited = selectedHeaders.stream()
               .map(po -> po.getPoHeaderId()+"")
               .collect(Collectors.joining(",")) ;
       
       selectedHeaders = purchaseOrderService.findWithLinesToInvoiceByIdsAndClient(selectedPoIdsDelmited,currentClient);
       
       currentInvoices = new ArrayList() ;
       
       Map<FndCurrency,Map<ClientSite , Map<OrganizationUnit, List<PoHeader>>>> poByCurrencyBySiteByOrg = selectedHeaders.stream()
               .collect(Collectors.groupingBy(PoHeader::getCurrency,Collectors.groupingBy(PoHeader::getClientSite,Collectors.groupingBy(PoHeader::getOrg)))) ;
       
       
       poByCurrencyBySiteByOrg.forEach((FndCurrency currency ,Map<ClientSite ,Map<OrganizationUnit, List<PoHeader>>> headersBySiteMap) -> {
       
           headersBySiteMap.forEach((ClientSite clientSite , Map<OrganizationUnit, List<PoHeader>> headersByOrg) -> {
           
               headersByOrg.forEach((org , headers) -> {
             
                   ApInvoice invoice = new ApInvoice(currentClient,clientSite,currency,org) ;
           
                   currentInvoices.add(invoice) ;
           
                   List<ApInvoiceLine> invoiceLines = new ArrayList<>() ;
           
                   headers.forEach(po -> {
             
                       po.getPoLines().forEach(poLine -> {
              
                       ApInvoiceLine line = new ApInvoiceLine(poLine);
                
                       line.setInvoice(invoice);
               
                       invoiceLines.add(line) ;
                  });
                 
                }) ;
             
                invoice.setLines(invoiceLines);
           
                BigDecimal initialInvoicedAmount = invoiceLines.stream()
                          .map(ApInvoiceLine::getAmount)
                          .reduce(BigDecimal.ZERO, BigDecimal::add) ;
           
                invoice.setInvoiceAmount(initialInvoicedAmount);
                     
                
               }) ; // END INNER FOREACH

            })  ; //END INNER FOREACH

       }); //END OUTER FOREACH
       
       selectedHeaders = null ;
       
       poHeaders = null ;
       
       return "createinvoice" ;
    }
    
    
    public void validateUnitPriceGtZero(FacesContext facesContext,UIComponent component , Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero Or Less", "Value Can't Be Zero Or Less")) ;
       }       
    }
    
    
    public void onUnitPriceChange(ApInvoiceLine invoiceLine)
    {
       ApInvoice invoice = invoiceLine.getInvoice() ;
       
       BigDecimal invoicedQuantity = invoiceLine.getQuantityInvoiced() ;
       
       BigDecimal oldInvoiceAmount = invoiceLine.getAmount() ;
       
       invoiceLine.setAmount(invoiceLine.getUnitPrice().multiply(invoicedQuantity));
       
       BigDecimal change = invoiceLine.getAmount().subtract(oldInvoiceAmount) ;
       
       invoice.setInvoiceAmount(invoice.getInvoiceAmount().add(change));
    }
      
    
    public String save()
    {
        invoiceService.create(currentInvoices);
        
        conversation.end();
        
        return "invoices?faces-redirect=true" ;
    }
    

    public List<PoHeader> getPoHeaders() {
        return poHeaders;
    }

    public void setSelectedHeaders(List<PoHeader> selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public List<PoHeader> getSelectedHeaders() {
        return selectedHeaders;
    }

    public Integer getCurrentPoNo() {
        return currentPoNo;
    }

    public void setCurrentPoNo(Integer currentPoNo) {
        this.currentPoNo = currentPoNo;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public List<ApInvoice> getCurrentInvoices() {
        return currentInvoices;
    }

    
}
