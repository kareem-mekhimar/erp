/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.services.apinvoice.ApInvoiceService;
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
public class SupplierInvoiceAccountController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private List<ApInvoice> invoices ;
    
    private List<ApInvoice> selectedInvoices ;

    private Integer currentInvoiceId ; 
    
    public void filter()
    {
       invoices = invoiceService.findNotAccountedAndValidatedById(currentInvoiceId) ;
    }
    
    public void createJournals()
    {
        //invoiceService.createJournals(selectedInvoices);
        
        notificationController.showNotification("Journals Created Successfully..", NotificationController.SUCCESS);
   
        filter();
        
        selectedInvoices = null ;
    }
    
    public List<ApInvoice> getSelectedInvoices() {
        return selectedInvoices;
    }

    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    
    public List<ApInvoice> getInvoices() {
        return invoices;
    }

    public void setSelectedInvoices(List<ApInvoice> selectedInvoices) {
        this.selectedInvoices = selectedInvoices;
    }
    
    
}
