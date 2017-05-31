/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.salesorder;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.ApInvoiceLine;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.ArInvoiceLine;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.shipment.ShipmentLine;
import com.bridge.enums.ClientType;
import com.bridge.enums.SalesOrderInvoiceType;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.salesorder.SalesOrderService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ConversationScoped
public class SalesOrderInvoiceController implements Serializable{
 
    @EJB
    private ArInvoiceService invoiceService ;
    
    @EJB
    private SalesOrderService salesOrderService ;
   
    @EJB
    private ClientService clientService ;
    
    @Inject
    private Conversation conversation ;
    
    private Client currentClient ;
    
    private Integer currentSalesOrderId ;
    
    private SalesOrderInvoiceType currentInvoiceType = SalesOrderInvoiceType.SALES_ORDER;
    
    private List<OeOrderHeader> headers ;
    
    private List<OeOrderHeader> selectedHeaders ;
    
    private List<ArInvoice> currentInvoices ;
    
    public void startConversation()
    {
       if(conversation.isTransient()) 
           
           conversation.begin();
    }
    
    public List<Client> completeCustomerByName(String text)
    {
       return clientService.findByName(ClientType.CUSTOMER, text) ;
    }
    
    public void filter()
    {
       switch(currentInvoiceType)
       {
           case SHIPPED : 
               
               headers = salesOrderService.findShippedNeededToInvoiceByClient(currentClient, currentSalesOrderId) ;
               
               break; 
               
          case DELIVERD : 
              
              headers = salesOrderService.findDeliveredNeededToInvoiceByClient(currentClient, currentSalesOrderId) ;
              
              break; 
              
          case DUE :
              
              headers = salesOrderService.findDueNeededToInvoiceByClient(currentClient, currentSalesOrderId) ;
              
              break; 
              
          default:
              
              headers = salesOrderService.findFullNeededToInvoiceByClient(currentClient, currentSalesOrderId) ;
       }
    }
    
    
    public String createInvoices()
    {     
       String selectedSoIdsDelmited = selectedHeaders.stream()
               .map(so -> so.getHeaderId()+"")
               .collect(Collectors.joining(",")) ;
       
 
       switch(currentInvoiceType)
       {
           case SHIPPED : 
               
               selectedHeaders = salesOrderService.findWithLinesShippedNeededToInvoiceByClientAndIds(selectedSoIdsDelmited, currentClient) ;
               
               break; 
               
          case DELIVERD : 
              
              selectedHeaders = salesOrderService.findWithLinesDeliveredNeededToInvoiceByClientAndIds(selectedSoIdsDelmited, currentClient) ;
              
              break; 
             
          case DUE :
              
              selectedHeaders = salesOrderService.findWithLinesDueNeededToInvoiceByClientAndIds(selectedSoIdsDelmited, currentClient) ;
              
              break;
              
          default:
              
              selectedHeaders = salesOrderService.findWithLinesFullNeededToInvoiceByClientAndIds(selectedSoIdsDelmited, currentClient) ;
       }
        
       currentInvoices = new ArrayList() ;
       
       selectedHeaders.stream().flatMap(h -> h.getLines().stream()).forEach(l -> {
       
           System.out.println(l.getHeader()+",,"+l.getLineId());
           
       });
       Map<FndCurrency,Map<ClientSite , Map<OrganizationUnit, List<OeOrderHeader>>>> soByCurrencyBySiteByOrg = selectedHeaders.stream()
               .collect(Collectors.groupingBy(OeOrderHeader::getCurrency,Collectors.groupingBy(OeOrderHeader::getInvoiceToSite,Collectors.groupingBy(OeOrderHeader::getOrg)))) ;
       
       
       soByCurrencyBySiteByOrg.forEach((FndCurrency currency ,Map<ClientSite ,Map<OrganizationUnit, List<OeOrderHeader>>> headersBySiteMap) -> {
       
           headersBySiteMap.forEach((ClientSite clientSite , Map<OrganizationUnit, List<OeOrderHeader>> headersByOrg) -> {
           
               headersByOrg.forEach((org , headers) -> {
             
                   ArInvoice invoice = new ArInvoice(currentClient,clientSite,currency,org) ;
           
                   currentInvoices.add(invoice) ;
           
                   List<ArInvoiceLine> invoiceLines = new ArrayList<>() ;
           
                   headers.forEach(so -> {
             
                       so.getLines().forEach(soLine -> {
              
                            ArInvoiceLine line = new ArInvoiceLine(soLine,currentInvoiceType);
                
                            line.setInvoice(invoice);
               
                            invoiceLines.add(line) ;
                   });
                 
                }) ;
             
                invoice.setInvoiceLines(invoiceLines);
           
                BigDecimal initialInvoicedAmount = invoiceLines.stream()
                          .map(ArInvoiceLine::getAmount)
                          .reduce(BigDecimal.ZERO, BigDecimal::add) ;
           
                invoice.setInvoiceAmount(initialInvoicedAmount);
                     
                
               }) ; // END INNER FOREACH

            })  ; //END INNER FOREACH

       }); //END OUTER FOREACH
       
       selectedHeaders = null ;
       
       headers = null ;
              
       return "createinvoices" ;
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
    
    
    public void onUnitPriceChange(ArInvoiceLine invoiceLine)
    {
       ArInvoice invoice = invoiceLine.getInvoice() ;
       
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
    
    public List<ArInvoice> getCurrentInvoices() {
    
        return currentInvoices;
    }
    
    
    public Client getCurrentClient() {
        return currentClient;
    }

    
    
    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Integer getCurrentSalesOrderId() {
        return currentSalesOrderId;
    }

    public List<OeOrderHeader> getHeaders() {
        return headers;
    }

    public SalesOrderInvoiceType getCurrentInvoiceType() {
        return currentInvoiceType;
    }

    public void setCurrentInvoiceType(SalesOrderInvoiceType currentInvoiceType) {
        this.currentInvoiceType = currentInvoiceType;
    }

    
    public SalesOrderInvoiceType [] getInvoiceTypes(){
        
        return SalesOrderInvoiceType.values() ;
    }
    
    
    public void setCurrentSalesOrderId(Integer currentSalesOrderId) {
        this.currentSalesOrderId = currentSalesOrderId;
    }

    public List<OeOrderHeader> getSelectedHeaders() {
        return selectedHeaders;
    }

    public void setSelectedHeaders(List<OeOrderHeader> selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }
       
    
}
