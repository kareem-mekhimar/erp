/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.ledger;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.enums.GLSetupSteps;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class LedgerOptionsController implements Serializable{
    
    @EJB
    private GlLedgerService ledgerService ;
    
    private Integer currentLedgerId ;

    private GlLedger currentLedger ;
    
    public void loadLedger()
    {
       if(currentLedgerId != null)
       {
           currentLedger = ledgerService.findById(currentLedgerId) ;
           
           if(currentLedger == null)
               
               JSFUtils.redirectTo404("Ledger");
       }
    }


   
    public String save()
    {
        ledgerService.update(currentLedger) ;
        
        ledgerService.completeOption(currentLedger, GLSetupSteps.LEDGER_OPTIONS);
        
        return "details?faces-redirect=true&id="+currentLedger.getLedgerId() ;
    }   


    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public Integer getCurrentLedgerId() {
        return currentLedgerId;
    }

    public void setCurrentLedgerId(Integer currentLedgerId) {
        this.currentLedgerId = currentLedgerId;
    }

    
}
