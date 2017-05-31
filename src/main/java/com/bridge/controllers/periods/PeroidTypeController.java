/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.periods;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.periods.GlPeriod;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.services.periods.PeriodTypeService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class PeroidTypeController implements Serializable{
    
    @EJB
    private PeriodTypeService service ;
 
    @Inject
    private NotificationController notificationController ;
    
    private String periodName ;

    private List<GlPeriodType> periodTypes ;
    
    private GlPeriodType currentPeriod ;
    
    private boolean filterTriggred ;
    
    public void filter()
    {
       if(periodName != null)
           
           periodTypes = service.findByName(periodName) ;
       
       else
           
           periodTypes = service.findAll() ;
    }

    public void onDialogOk()
    {
       service.update(currentPeriod) ;
       
       notificationController.showNotification(currentPeriod.getPeriodTypeName()+" Saved", NotificationController.SUCCESS);
    
       if(filterTriggred)
           
           filter() ;
       
       currentPeriod = null ;
       
    }
    
    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public List<GlPeriodType> getPeriodTypes() {
        return periodTypes;
    }

    public GlPeriodType getCurrentPeriod() {
        
        if(currentPeriod == null)
            
            currentPeriod = new GlPeriodType() ;
        
        return currentPeriod;
    }

    public void setCurrentPeriod(GlPeriodType currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public boolean isFilterTriggred() {
        return filterTriggred;
    }

    public void setFilterTriggred(boolean filterTriggred) {
        this.filterTriggred = filterTriggred;
    }
    
    
    
}
