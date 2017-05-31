/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice.payment;

import com.bridge.entities.clients.Client;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.payment.ApInvoicePayment;
import com.bridge.entities.bank.BankAccount;
import com.bridge.enums.ClientType;
import com.bridge.enums.InvoicePaymentType;
import com.bridge.services.clients.ClientService;
import com.bridge.services.apinvoice.ApInvoiceService;
import com.bridge.services.bank.AccountService;
import com.bridge.services.bank.BankService;
import com.bridge.services.clients.PrePaymentService;
import com.bridge.services.salesorder.SalesOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ApinvoicePayController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;

    @EJB
    private ClientService clientService ;
    
    @EJB
    private AccountService accountService ;
    
    @EJB
    private PrePaymentService prePaymentService ;
    
    @Inject
    private Conversation conversation ;
    
    private List<ApInvoice> invoices ;
    
    private ApInvoice selectedInvoice ;
    
    private ApInvoicePayment currentPayment ;
    
    private Client currentClient ;
    
    private Integer currentInvoiceId ;
    
    private BigDecimal prePayedAmount ;
    
    public List<Client> completeSupplierByName(String text)
    {
       return clientService.findByName(ClientType.SUPPLIER, text) ;
    }

    public List<BankAccount> completeBankAccounts(String text)
    {
       return accountService.findByNameAndOperatingId(text, selectedInvoice.getOperatingUnit().getOrgUnitId()) ;
    }
    
    public void startConversation()
    {
       if(conversation.isTransient()) 
           
           conversation.begin();
    }
        
    public void filter()
    {
       invoices = invoiceService.findValidatedInvoicesToPay(currentClient,currentInvoiceId) ;
       
    }
    
    public String preparePayments()
    {      
       currentPayment = new ApInvoicePayment(selectedInvoice) ;
       
       return "createpayments" ;
    }
    
    
    public void validatePaymentDate(FacesContext context ,UIComponent component , Object value)
    {
       if(value == null)
           
           return; 
       
        Date date = (Date) value ;
        
        Date invoiceDate = selectedInvoice.getInvoiceDate() ;
        
        SimpleDateFormat format = new SimpleDateFormat("d-M-yyyy") ;
        
        try {
        
            invoiceDate = format.parse(format.format(invoiceDate)) ;
        
        } catch (ParseException ex) {
        
            Logger.getLogger(ApinvoicePayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(date.compareTo(invoiceDate) < 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Date is older than Invoice Date", "Date is older than Invoice Date")) ;
    }
    
    
    public void validateAmount(FacesContext facesContext,UIComponent component , Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero Or Less", "Value Can't Be Zero Or Less")) ;
       } 
       
       if(currentPayment.getInvoicePaymentType() == InvoicePaymentType.PREPAYMENT && quantity.compareTo(prePayedAmount) > 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Can't Exceed Pre Paid Amount", "Can't Exceed Pre Paid Amount")) ;           
       }
    }
    
    public String save()
    {
       invoiceService.createPayment(currentPayment);
       
       conversation.end();
       
       return "viewtopay?faces-redirect=true";
    }
    
    public void onTypeChange()
    {
       currentPayment.setBankAccount(null);
       
       currentPayment.setCheckNo(null);
       
       currentPayment.setAmount(null);
       
       if(currentPayment.getInvoicePaymentType() == InvoicePaymentType.PREPAYMENT)
       {
           ApInvoice invoice = currentPayment.getInvoice() ;
           
           prePayedAmount = prePaymentService.findAmount(invoice.getOperatingUnit(), 
                   invoice.getClient(), invoice.getClientSite(), ClientType.SUPPLIER,invoice.getInvoiceCurrency()) ;
       }
    }
    
    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public List<ApInvoice> getInvoices() {
        return invoices;
    }

    public ApInvoicePayment getCurrentPayment() {
        return currentPayment;
    }

    
    public ApInvoice getSelectedInvoice() {
        return selectedInvoice;
    }

    public void setSelectedInvoice(ApInvoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public InvoicePaymentType[] getInvoicePaymentTypes()
    {
       return InvoicePaymentType.values() ;
    }
    
    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    public BigDecimal getPrePayedAmount() {
        return prePayedAmount;
    }
    
    
        
}
