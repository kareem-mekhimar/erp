/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.services.ledger.GlLedgerService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CustomerInvoiceJournalController implements Serializable{
    
   public enum JournalCreateType {
         
        INVOICES , PAYMENTS ;
    }
    
    @EJB
    private ArInvoiceService invoiceService ;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    private GlLedger currentLedger ;
    
    private GlPeriodStatus currentOpenPeriod ;
    
    private JournalCreateType currentType ;
    
    public List<GlLedger> completeLedger(String text)
    {
       return ledgerService.findByName(text) ;
    }
    
    public List<GlPeriodStatus> completePeriods(String text)
    {
        return ledgerService.findOpenPeriodsByPeriodNameAndLedger(currentLedger, text) ;
    }

    
    public void createJournals()
    {
        if(currentType == JournalCreateType.INVOICES)
        {
            long count = invoiceService.countNotAccountedValidatedInvoicesInPeriod(currentOpenPeriod) ;
        
            if(count == 0)
            {
               RequestContext.getCurrentInstance().execute("UIkit.modal.alert('There are no Invoices In This Period..');");

               return; 
            }
            
            invoiceService.createInvoicesJournalsForPeriod(currentOpenPeriod);
        }
        else
        {
            long count = invoiceService.countNotAccountedPaymentsInPeriod(currentOpenPeriod) ;
        
            if(count == 0)
            {
               RequestContext.getCurrentInstance().execute("UIkit.modal.alert('There are no Payments In This Period..');");

               return; 
            }
            
            invoiceService.createPaymentJournalsForPeriod(currentOpenPeriod);        
        }
        
        currentLedger = null ;
        
        currentOpenPeriod = null ;
        
        currentType = null ;
    }
    
    
    public void onLedgerChange()
    {
       currentOpenPeriod = null ;
    }
    
    
    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public void setCurrentLedger(GlLedger currentLedger) {
        this.currentLedger = currentLedger;
    }

    public GlPeriodStatus getCurrentOpenPeriod() {
        return currentOpenPeriod;
    }

    public void setCurrentOpenPeriod(GlPeriodStatus currentOpenPeriod) {
        this.currentOpenPeriod = currentOpenPeriod;
    }

    public JournalCreateType getCurrentType() {
        return currentType;
    }

    public void setCurrentType(JournalCreateType currentType) {
        this.currentType = currentType;
    }
    
    public JournalCreateType [] getJournalCreateTypes()
    {
       return JournalCreateType.values() ;
    }    
}
