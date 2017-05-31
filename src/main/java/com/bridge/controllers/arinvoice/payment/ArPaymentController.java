/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice.payment;

import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.services.arinvoice.ArInvoiceService;
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
public class ArPaymentController implements Serializable{
    
    @EJB
    private ArInvoiceService invoiceService ;
    
    private List<ArInvoice> invoices ;
    
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

    public List<ArInvoice> getInvoices() {
        return invoices;
    }
    
        
}
