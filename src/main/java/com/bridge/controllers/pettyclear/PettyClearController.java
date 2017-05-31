/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.pettyclear;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.modules.CashManagementModuleSetup;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.modules.CashManagementModuleService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
public class PettyClearController implements Serializable{
    
    @EJB
    private CashManagementModuleService cashManagementModuleService;

    @EJB
    private GlLedgerService ledgerService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private CashManagementModuleSetup currentSetup ;
   
    private Date date ;
    
    private String description ;
    
    private BigDecimal neededAmount ;
    
    public List<CashManagementModuleSetup> completeOperatingUnit(String text) {

        return cashManagementModuleService.findByOperatingUnitName(text) ;

    }    
        
    public CashManagementModuleSetup getCurrentSetup() {
       
        return currentSetup;
    }

    public void setCurrentSetup(CashManagementModuleSetup currentSetup) {
        this.currentSetup = currentSetup;
    }

    public void validateDate(FacesContext facesContext,UIComponent component , Object value)
    {
       Date date = (Date) value ;
       
       if(! ledgerService.isInOpenPeriod(currentSetup.getOperatingUnit().getGlId(), date))
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Not in open period", "Not in open period")) ;
       } 
       
    }
    
    public void validateNeededAmount(FacesContext facesContext,UIComponent component , Object value)
    {
       BigDecimal amount = (BigDecimal) value ;
       
       if(amount.compareTo(BigDecimal.ZERO) == 0 || amount.compareTo(BigDecimal.ZERO) < 0 )
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't Be Zero or less", "Value Can't Be Zero or less")) ;
        
       if(amount.compareTo(currentSetup.getMiscellaneousExpenseAmount()) > 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Can't Exceed current amount", "Can't Exceed current amount")) ;
       } 
       
    }
    
    
    public void onChange()
    {
       neededAmount = null; 
    }
    
    public void registerExpenses()
    {
       cashManagementModuleService.registerExpenses(currentSetup,description, date,neededAmount);
       
       neededAmount = null ;
       
       notificationController.showNotification("Register Completed..", NotificationController.SUCCESS);
    }
    
    public void resetExpenses()
    {
       cashManagementModuleService.resetExpense(currentSetup, description, date);
       
       neededAmount = null ;
       
       notificationController.showNotification("Reset Completed..", NotificationController.SUCCESS);
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getNeededAmount() {
        return neededAmount;
    }

    public void setNeededAmount(BigDecimal neededAmount) {
        this.neededAmount = neededAmount;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
