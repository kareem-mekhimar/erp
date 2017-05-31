/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.currency;

import com.bridge.entities.currency.CurrencyConversion;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.ledger.GlLedgerService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named
@ViewScoped
public class CurrencyConversionController implements Serializable {

    @EJB
    private CurrencyService currencyService;
    @EJB
    private GlLedgerService ledgerService;

    private List<CurrencyConversion> coversionList;

    private CurrencyConversion currentConversion;

    private List<FndCurrency> allCurrencies;
    
    private List<FndCurrency> currencies;
    
    private List<GlLedger> ledgerList;

    private String actionFlag;

    private Integer firstCurrencyId;

    private Integer secondCurrencyId;

    public List<CurrencyConversion> getCoversionList() {
        if (coversionList == null) {
            coversionList = new ArrayList();
        }
        return coversionList;
    }

    public void setCoversionList(List<CurrencyConversion> coversionList) {

        this.coversionList = coversionList;
    }

    public CurrencyConversion getCurrentConversion() {

        return currentConversion;
    }

    public void setCurrentConversion(CurrencyConversion currentConversion) {
        this.currentConversion = currentConversion;
    }

    public Integer getFirstCurrencyId() {
        return firstCurrencyId;
    }

    public void setFirstCurrencyId(Integer firstCurrencyId) {
        this.firstCurrencyId = firstCurrencyId;
    }

    public Integer getSecondCurrencyId() {
        return secondCurrencyId;
    }

    public void setSecondCurrencyId(Integer secondCurrencyId) {
        this.secondCurrencyId = secondCurrencyId;
    }

    public List<FndCurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<FndCurrency> currencies) {
        this.currencies = currencies;
    }

    public List<GlLedger> getLedgerList() {
        return ledgerList;
    }

    public void setLedgerList(List<GlLedger> ledgerList) {
        this.ledgerList = ledgerList;
    }

    public List<FndCurrency> getAllCurrencies() {
        return allCurrencies;
    }

    public void setAllCurrencies(List<FndCurrency> allCurrencies) {
        this.allCurrencies = allCurrencies;
    }

    
    
    public void setToCurrency(){
        currentConversion.setToCurrency(ledgerService.findById(currentConversion.getGlId()).getCurrency());
        currentConversion.setFromCurrency(null);
        loadCurrencies();
        
    }
    
    public void validateCurrency(FacesContext context, UIComponent component, Object value) {

        UIInput firstCurrency = (UIInput) component.findComponent("fromCurrency");

        FndCurrency toCurrency = (FndCurrency) value;

        FndCurrency fromCurrency = (FndCurrency) firstCurrency.getValue();

        if (toCurrency == null) {
            return;
        }
        if (fromCurrency == null) {
            return;
        }

        if (fromCurrency.getCurrencyId().equals(toCurrency.getCurrencyId())) {
            throw new ValidatorException(new FacesMessage("can not convert to same currency!"));
        }
    }
    
    public void validateDate(FacesContext context, UIComponent component, Object value) {

    UIInput firstCurrency = (UIInput) component.findComponent("fromCurrency");

       
        
        FndCurrency fromCurrency = (FndCurrency) firstCurrency.getValue();

     
        
        Date date = (Date) value;
        if(actionFlag.equals("update")){
          if(currentConversion.getDayDate().compareTo(date)==0) return;
        }

        if (currencyService.isConversionExists(fromCurrency, currentConversion.getToCurrency(), date)) {
            
            throw new ValidatorException(new FacesMessage("this day has Conversion!"));
        }
    }

    
    public void filter() {
        coversionList = currencyService.findConversionByCurrency(firstCurrencyId, secondCurrencyId);
    }

    public void loadAllCurrencies() {

        allCurrencies = currencyService.findAll();
    }
    
    public void loadCurrencies() {

        currencies =allCurrencies.stream().filter(l-> ! l.equals(currentConversion.getToCurrency())).collect(Collectors.toList());
    }

    public void loadDialog(CurrencyConversion conversion) {

        ledgerList=ledgerService.findAll();
        currentConversion = conversion; 
        actionFlag = "update";
        if (conversion == null) {
            currentConversion = new CurrencyConversion();
            actionFlag = "add";
        }
        loadCurrencies();
    }

    public void removeConversion() {
        coversionList.remove(currentConversion);
        if (currentConversion.getId() != null) {
            currencyService.removeConversion(currentConversion);
        }
    }

    public void onDialogOk() {
        
        currentConversion=currencyService.update(currentConversion);
        if (actionFlag.equals("add")) {
            coversionList.add(currentConversion);
        }
        
    }
}
