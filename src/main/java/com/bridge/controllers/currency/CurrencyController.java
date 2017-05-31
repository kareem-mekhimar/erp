/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.currency;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.services.currency.CurrencyService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CurrencyController implements Serializable{
    
    @EJB
    private CurrencyService currencyService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private String currentcode ;
    
    private String currentName ;

    private Boolean currentStatus ;
    
    private boolean filterTriggred ;
    
    private List<FndCurrency> currencies ;
    
    private FndCurrency currentCurrency ;
    
    private String openDialogCurrencyCode ;
    
    public void filter()
    {
       currencies = currencyService.findByCodeOrNameOrStatus(currentcode, currentName,currentStatus);   
    }

    public void onDialogOk()
    {
       currencyService.update(currentCurrency) ;
       
       notificationController.showNotification(currentCurrency.getCurrencyCode()+" Saved ..",
               NotificationController.SUCCESS);
       
      if(filterTriggred)
          
           filter() ;
    }
    
    public void validateCodeExist(FacesContext facesContext , UIComponent comp , Object value)
    {
       if(value == null)
           
           return;
              
       if(currencyService.isCodeExists(String.valueOf(value)) && ! String.valueOf(value).equals(openDialogCurrencyCode))
       {               
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        value +" already Exist..!",
                        value +" already Exist..!"));
           
       }   
    }
    
    public void setCurrentCurrency(FndCurrency currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public void setFilterTriggred(boolean filterTriggred) {
        this.filterTriggred = filterTriggred;
    }

    public boolean isFilterTriggred() {
        return filterTriggred;
    }
    
    
    public FndCurrency getCurrentCurrency() {
       
        if(currentCurrency == null){
            currentCurrency = new FndCurrency() ;
            currentCurrency.setEnabled(true);
        }

        return currentCurrency;
    }

    
    public List<FndCurrency> getCurrencies() {
        return currencies;
    }

    public String getOpenDialogCurrencyCode() {
        return openDialogCurrencyCode;
    }

    public void setOpenDialogCurrencyCode(String openDialogCurrencyCode) {
        this.openDialogCurrencyCode = openDialogCurrencyCode;
    }
    
    
    public String getCurrentcode() {
    
        return currentcode;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public void setCurrentcode(String currentcode) {
        this.currentcode = currentcode;
    }

    public Boolean getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Boolean currentStatus) {
        this.currentStatus = currentStatus;
    }

    
    
    
}
