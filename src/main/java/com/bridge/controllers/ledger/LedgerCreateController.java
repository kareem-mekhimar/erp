/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.ledger;

import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.enums.AccountingMethod;
import com.bridge.services.coa.CoaStructureService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.periods.CalendarService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class LedgerCreateController implements Serializable{
    
    @EJB
    private GlLedgerService ledgerService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    @EJB
    private CoaStructureService structureService ;
    
    @EJB
    private CalendarService calendarService ;
    
    private GlLedger currentLedger ;
    
    private List<FndCurrency> currencys ;

    public LedgerCreateController() {
    
        currentLedger = new GlLedger() ;
        
    }
    
    
    @PostConstruct
    private void init()
    {
       currencys = currencyService.findAll() ;
    }
     
    public List<CoaStructure> completeCOAByName(String text)
    {
       return structureService.findCoaStructureByName(text) ;
    }
    
    public List<GlPeriodSet> completeCalendarByName(String text)
    {
       return calendarService.findByName(text) ;
    }
    
    
    public String save()
    {
       ledgerService.create(currentLedger);
       
       return "details?faces-redirect=true&id="+currentLedger.getLedgerId() ;
    }
    
    
    public List<FndCurrency> getCurrencys() {
       
        return currencys;
    }

    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public AccountingMethod [] getAccountingMethods()
    {
        return AccountingMethod.values() ;
    }
}
