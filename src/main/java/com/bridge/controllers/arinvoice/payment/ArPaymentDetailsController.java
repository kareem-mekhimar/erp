/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice.payment;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.payment.ArInvoicePayment;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class ArPaymentDetailsController implements Serializable{
    
    @EJB
    private ArInvoiceService invoiceService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private ArInvoice currenInvoice ;
    
    private Integer currentInvoiceId ;

    private List<ArInvoicePayment> payments ;
    
    public void loadInvoicePayments()
    {
       if(currentInvoiceId == null || !invoiceService.isExists(currentInvoiceId)) 
       {
           JSFUtils.redirectTo404("Payments");
       }
       else
       {
           payments = invoiceService.findPaymentsByInvoiceId(currentInvoiceId) ;
        
           currenInvoice = payments.get(0).getInvoice() ;
       }
    }

    
    public void cancelPayment(ArInvoicePayment invoicePayment)
    {
        invoiceService.cancelPayment(invoicePayment);
        
        notificationController.showNotification("Payment With Amount ("+invoicePayment.getAmount()+") Cancelled", NotificationController.SUCCESS);
    }
    
    
    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    public List<ArInvoicePayment> getPayments() {
        return payments;
    }

    public ArInvoice getCurrenInvoice() {
        return currenInvoice;
    }
    
        
}
