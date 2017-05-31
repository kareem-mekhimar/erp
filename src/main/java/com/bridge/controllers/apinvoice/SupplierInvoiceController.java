/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.clients.Client;
import com.bridge.enums.ClientType;
import com.bridge.enums.InvoiceStatus;
import com.bridge.enums.InvoiceType;
import com.bridge.services.clients.ClientService;
import com.bridge.services.apinvoice.ApInvoiceService;
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
public class SupplierInvoiceController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    @EJB
    private ClientService clientService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private List<ApInvoice> invoices ;
    
    private List<ApInvoice> selectedInvoices ;
    
    private Integer currentInvoiceNo ;
    
    private Integer currentSupplierId ;
    
    private InvoiceStatus currentStatus ;
    
    private ApInvoice currentInvoice ;
   
    private ApInvoice newCorrectedInvoice ;
    
    public void filter()
    {
       invoices = invoiceService.findStandardByIdOrStatusOrSupplierId(currentInvoiceNo, currentStatus,currentSupplierId) ;
    }

    public void validateInvoices()
    {
        invoiceService.validateInvoices(selectedInvoices);
        
        notificationController.showNotification("Invoices Validated..",NotificationController.SUCCESS);
        
        selectedInvoices = null ;
        
        filter();       
    }
    
    
    public void cancelInvoice(ApInvoice invoice)
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
    
    
    public List<Client> completeSupplierByName(String text)
    {
       return clientService.findByName(ClientType.SUPPLIER, text) ;
    }

    public List<ApInvoice> getInvoices() {
        return invoices;
    }
    
    public Integer getCurrentSupplierId() {
        return currentSupplierId;
    }

    public void setCurrentSupplierId(Integer currentSupplierId) {
        this.currentSupplierId = currentSupplierId;
    }

    public List<ApInvoice> getSelectedInvoices() {
        return selectedInvoices;
    }

    public void setSelectedInvoices(List<ApInvoice> selectedInvoices) {
        this.selectedInvoices = selectedInvoices;
    }

    
    public Integer getCurrentInvoiceNo() {
        return currentInvoiceNo;
    }

    public ApInvoice getNewCorrectedInvoice() {
        
        if(newCorrectedInvoice == null)
            
            newCorrectedInvoice = new ApInvoice(currentInvoice, InvoiceType.CREDIT);
        
        return newCorrectedInvoice;
    }
    
    public List<InvoiceType> getCorrectionInvoiceTypes()
    {
       return Arrays.asList(InvoiceType.CREDIT , InvoiceType.DEBIT) ;
    }
    
    public void setNewCorrectedInvoice(ApInvoice newCorrectedInvoice) {
        this.newCorrectedInvoice = newCorrectedInvoice;
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

    public void setCurrentInvoice(ApInvoice currentInvoice) {
        this.currentInvoice = currentInvoice;
    }

    public ApInvoice getCurrentInvoice() {
        return currentInvoice;
    }
    
    public InvoiceStatus[] getInvoiceTypes() {
        
        return InvoiceStatus.values() ;
    }
    
    
}
