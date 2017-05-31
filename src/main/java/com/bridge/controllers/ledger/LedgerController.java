/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.ledger;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.enums.GlSetupStatus;
import com.bridge.services.ledger.GlLedgerService;
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
public class LedgerController implements Serializable{
    
    @EJB
    private GlLedgerService ledgerService;
    
    private Integer currentLedgerId ;
    
    private String currentLedgerName ;

    private GlSetupStatus currentStatus ;
            
    private List<GlLedger> ledgers ;
    
    public void filter()
    {
       ledgers = ledgerService.findByNameOrId(currentLedgerName, currentLedgerId, currentStatus) ;
    }

    public Integer getCurrentLedgerId() {
        return currentLedgerId;
    }

    public void setCurrentLedgerId(Integer currentLedgerId) {
        this.currentLedgerId = currentLedgerId;
    }

    public String getCurrentLedgerName() {
        return currentLedgerName;
    }

    public void setCurrentLedgerName(String currentLedgerName) {
        this.currentLedgerName = currentLedgerName;
    }
    
    
    public GlSetupStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(GlSetupStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
    
    public GlSetupStatus[] getStatuses()
    {
        return GlSetupStatus.values() ;
    }

    public List<GlLedger> getLedgers() {
        return ledgers;
    }
 
    
}
