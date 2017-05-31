/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.journal;

import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.periods.GlPeriod;
import com.bridge.enums.JournalBatchApproveStatus;
import com.bridge.enums.JournalBatchPostStatus;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.periods.CalendarService;
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
public class JournalBatchController implements Serializable{
    
    @EJB
    private JournalBatchService batchService ;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    private List<GlJeBatch> batches ;

    private GlLedger currentLedger ;
    
    private GlPeriodStatus currentPeriod ;
            
    private String currentName ;
    
    public void filter()
    {
       batches = batchService.findByNameOrLedgerOrPeriod(currentName, currentLedger, currentPeriod) ;
    }
    
    public List<GlLedger> completeLedger(String text)
    {
       return ledgerService.findByName(text) ;
    }
    
    public List<GlPeriodStatus> completePeriod(String text)
    {
       if(currentLedger != null)
           
           return ledgerService.findPeriodsByNameAndLedger(currentLedger, text) ;
       
       return ledgerService.findPeriodsByName(text) ;
    }
    
    public void onLedgerChange()
    {
       currentPeriod = null ;
    }
    
    public List<GlJeBatch> getBatches() {
       
        return batches;
    }

    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public void setCurrentLedger(GlLedger currentLedger) {
        this.currentLedger = currentLedger;
    }

    
    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public GlPeriodStatus getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(GlPeriodStatus currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
    
    
}
