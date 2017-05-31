/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.clients.Client;
import com.bridge.enums.ClientType;
import com.bridge.enums.InvoiceStatus;
import com.bridge.enums.InvoiceType;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.services.clients.ClientService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CustomerInvoiceController implements Serializable{
    
    @EJB
    private ArInvoiceService invoiceService ;
    
    @EJB
    private ClientService clientService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private List<ArInvoice> invoices ;
    
    private List<ArInvoice> selectedInvoices ;
    
    private Integer currentInvoiceNo ;
    
    private Integer currentSupplierId ;
    
    private InvoiceStatus currentStatus ;
    
    private ArInvoice currentInvoice ;

    private ArInvoice newCorrectedInvoice ;
    
    public void filter()
    {
       invoices = invoiceService.findStandardByIdOrStatusOrCustomerId(currentInvoiceNo, currentStatus,currentSupplierId) ;
    }

    public void validateInvoices()
    {
        invoiceService.validateInvoices(selectedInvoices);
        
        notificationController.showNotification("Invoices Validated..",NotificationController.SUCCESS);
        
        selectedInvoices = null ;
        
        filter();       
    }
    
    
    public void cancelInvoice(ArInvoice invoice)
    {
        invoiceService.cancelInvoice(invoice);
        
        notificationController.showNotification("Invoice No.("+invoice.getInvoiceId()+") Cancelled",
                NotificationController.SUCCESS);
    }
    
    
    public void onCorrectDialogOk()
    {
       invoiceService.create(newCorrectedInvoice) ;
       
       notificationController.showNotification("Correcion Invoice Created..!", NotificationController.SUCCESS);
       
        RequestContext requestContext = RequestContext.getCurrentInstance() ;
        
        requestContext.update("dialogContent2");
        
        requestContext.execute("modal2.hide()");
        
        this.currentInvoice = null ;
        
        this.newCorrectedInvoice = null ;
    }
    
    
    
    public void validateCorrectionAmount(FacesContext facesContext,UIComponent component , Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero Or Less", "Value Can't Be Zero Or Less")) ;
       } 
       
       BigDecimal currentInvoiceAmount = currentInvoice.getInvoiceAmount() ;
       
       UIInput amountInput = (UIInput) component.findComponent("type") ;
       
       InvoiceType newInvoiceType = (InvoiceType) amountInput.getValue() ;
       
       if(newInvoiceType == InvoiceType.CREDIT)
       {
         if(quantity.compareTo(currentInvoiceAmount) > 0)
             
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Can't Exceed Invoice Quantity", "Can't Exceed Invoice Quantity")) ;
       }          
    }

    public ArInvoice getNewCorrectedInvoice() {
  
        if(newCorrectedInvoice == null)
            
            newCorrectedInvoice = new ArInvoice(currentInvoice, InvoiceType.CREDIT);
        
        return newCorrectedInvoice;
    }

    public void setNewCorrectedInvoice(ArInvoice newCorrectedInvoice) {
        this.newCorrectedInvoice = newCorrectedInvoice;
    }
    
    
    public List<Client> completeCustomerByName(String text)
    {
       return clientService.findByName(ClientType.CUSTOMER, text) ;
    }

    public List<ArInvoice> getInvoices() {
        return invoices;
    }
    
    public Integer getCurrentSupplierId() {
        return currentSupplierId;
    }

    public void setCurrentSupplierId(Integer currentSupplierId) {
        this.currentSupplierId = currentSupplierId;
    }

    public List<ArInvoice> getSelectedInvoices() {
        return selectedInvoices;
    }

    public void setSelectedInvoices(List<ArInvoice> selectedInvoices) {
        this.selectedInvoices = selectedInvoices;
    }

    
    public Integer getCurrentInvoiceNo() {
        return currentInvoiceNo;
    }

    public InvoiceStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentInvoiceNo(Integer currentInvoiceNo) {
        this.currentInvoiceNo = currentInvoiceNo;
    }

    public void setCurrentStatus(InvoiceStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    
    public void setCurrentInvoice(ArInvoice currentInvoice) {
        this.currentInvoice = currentInvoice;
    }

    public ArInvoice getCurrentInvoice() {
        return currentInvoice;
    }
    
    public InvoiceStatus[] getInvoiceTypes() {
        
        return InvoiceStatus.values() ;
    }
       
    
    public List<InvoiceType> getCorrectionInvoiceTypes()
    {
       return Arrays.asList(InvoiceType.CREDIT , InvoiceType.DEBIT) ;
    }
    
        
}
