/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.journal;

import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.enums.JournalBatchType;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.utils.JSFUtils;
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
public class JournalBatchDetailController implements Serializable{ 
    
    @EJB
    private JournalBatchService batchService ;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    private Integer currentBatchId ;
    
    private GlJeBatch currentBatch ;
    
    public void loadBatch()
    {
        if(currentBatchId != null)
        {
            currentBatch = batchService.findById(currentBatchId) ;
            
            if(currentBatch == null)
                
                JSFUtils.redirectTo404("Journal Batch");
        }
    }
    
    public List<GlLedger> completeLedger(String text)
    {
       return ledgerService.findByName(text) ;
    }
    
    public List<GlPeriodStatus> completePeriod(String text)
    {
       GlLedger currentLedger = currentBatch.getLedger() ;

       if(currentLedger != null)
           
           return ledgerService.findOpenPeriodsByPeriodNameAndLedger(currentBatch.getLedger(),text) ;
       
       return null ;
    }
    
    public void onLedgerChange()
    {
       currentBatch.setPeriod(null);
    }
    
    public String save()
    {
       currentBatch = batchService.update(currentBatch) ;
       
       if(currentBatchId == null)
           
           return  "journalentrydetails?faces-redirect=true&batch="+currentBatch.getJeBatchId();
       
       else
           
           return "viewbatches?faces-redirect=true" ;
    }

    public void onTypeChange()
    {
       if(currentBatch.getBatchType() == JournalBatchType.RECURRING)
       {
          currentBatch.setPeriod(null);
       }
    }
    
    public GlJeBatch getCurrentBatch() {
        
        if(currentBatch == null)
            
            currentBatch = new GlJeBatch() ;
        
        return currentBatch;
    }

    public Integer getCurrentBatchId() {
        return currentBatchId;
    }

    public void setCurrentBatchId(Integer currentBatchId) {
        this.currentBatchId = currentBatchId;
        
    }

    public JournalBatchType [] getBatchTypes()
    {
       return JournalBatchType.values() ;
    }
      
}
