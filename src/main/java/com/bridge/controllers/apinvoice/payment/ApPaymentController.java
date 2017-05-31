/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice.payment;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.services.apinvoice.ApInvoiceService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class ApPaymentController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    private List<ApInvoice> invoices ;
    
    private Integer currentInvoiceId  ;
    
    private Integer currentSupplierId ;

    public void filter()
    {
       invoices = invoiceService.findValidatedHavePaymentsByIdOrClientId(currentInvoiceId, currentSupplierId) ;
    }
    
    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    public Integer getCurrentSupplierId() {
        return currentSupplierId;
    }

    public void setCurrentSupplierId(Integer currentSupplierId) {
        this.currentSupplierId = currentSupplierId;
    }

    public List<ApInvoice> getInvoices() {
        return invoices;
    }
    
    
    
    
}
