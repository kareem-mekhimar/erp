/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice.payment;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.payment.ApInvoicePayment;
import com.bridge.services.apinvoice.ApInvoiceService;
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
public class ApPaymentDetailsController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private ApInvoice currenInvoice ;
    
    private Integer currentInvoiceId ;

    private List<ApInvoicePayment> payments ;
    
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

    public void cancelPayment(ApInvoicePayment invoicePayment)
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

    public List<ApInvoicePayment> getPayments() {
        return payments;
    }

    public ApInvoice getCurrenInvoice() {
        return currenInvoice;
    }
    
    
    
}
