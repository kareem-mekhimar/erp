/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.ledger;

import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlReportingCurrency;
import com.bridge.enums.GLSetupSteps;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class LedgerCurrenciesController implements Serializable{
    
    @EJB
    private GlLedgerService ledgerService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    private FndCurrency currentCurrency ;
    
    private GlLedger currentLedger ;
    
    private List<FndCurrency> currencysToSelectFrom ;
    
    private Integer currentLedgerId ;
    
    public void loadLedger()
    {
       if(currentLedgerId != null)
       {
           currentLedger = ledgerService.findWithCurrienciesById(currentLedgerId);
           
           if(currentLedger == null)
               
               JSFUtils.redirectTo404("Ledger");
       }
    }
    
    public void onDialogShown()
    {
      List<GlReportingCurrency> reportingCurrencys = currentLedger.getReportingCurrencys() ;
      
      String mainCurrId = String.valueOf(currentLedger.getCurrency().getCurrencyId()) ;
      
      if(reportingCurrencys.isEmpty())
          
          currencysToSelectFrom = currencyService.findAllAndNotIn(mainCurrId) ;
      
      else
      {
         String idsDelmited = reportingCurrencys.stream()
                 .map(g -> g.getCurrency().getCurrencyId()+"")
                 .collect(Collectors.joining(",")) ;
         
         currencysToSelectFrom = currencyService.findAllAndNotIn(idsDelmited+","+mainCurrId) ;
      }
    }
    
    private void addCurrency()
    {
        GlReportingCurrency currency = new GlReportingCurrency() ;
        
        currency.setCurrency(currentCurrency);
        
        currency.setLedger(currentLedger);
        
        currentLedger.getReportingCurrencys().add(currency) ;
    }
    
    public void onDialogOk()
    {
       addCurrency(); 
       
       currentCurrency = null ;
       
       currencysToSelectFrom = null ;
    }
    
    public String save()
    {
        ledgerService.update(currentLedger) ;
        
        ledgerService.completeOption(currentLedger, GLSetupSteps.ALC_SETUP);
        
        return "details?faces-redirect=true&id="+currentLedger.getLedgerId() ;
    }

    public Integer getCurrentLedgerId() {
        return currentLedgerId;
    }

    public void setCurrentLedgerId(Integer currentLedgerId) {
        this.currentLedgerId = currentLedgerId;
    }
    
    
    public FndCurrency getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(FndCurrency currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public List<FndCurrency> getCurrencysToSelectFrom() {
        return currencysToSelectFrom;
    }

    public GlLedger getCurrentLedger() {
        return currentLedger;
    }
      
    
}
