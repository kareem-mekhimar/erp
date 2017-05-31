/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.periods;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.services.periods.CalendarService;
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
public class CalendarController implements Serializable{
   
    @EJB
    private CalendarService calendarService ;
    
    @EJB
    private PeriodTypeService periodTypeService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private String currenName ;
    
    private Integer currentPeriodTypeId ;

    private List<GlPeriodSet> periodSets ;
    
    private GlPeriodSet currentCalendar ;
    
    private boolean filterTriggred ;
    
    public List<GlPeriodType> completePeriods(String text)
    {
       return periodTypeService.findByName(text) ;
    }
    
    public void filter()
    {
       periodSets = calendarService.findByNameOrTypeId(currenName, currentPeriodTypeId) ;
    }
    
    public String getCurrenName() {
        return currenName;
    }

    public Integer getCurrentPeriodTypeId() {
        return currentPeriodTypeId;
    }

    public void setCurrentPeriodTypeId(Integer currentPeriodTypeId) {
        this.currentPeriodTypeId = currentPeriodTypeId;
    }

    
    public void setCurrenName(String currenName) {
        this.currenName = currenName;
    }

    public List<GlPeriodSet> getPeriodSets() {
        return periodSets;
    }

    public boolean isFilterTriggred() {
        return filterTriggred;
    }

    public GlPeriodSet getCurrentCalendar() {
    
        if(currentCalendar== null)
            
            currentCalendar = new GlPeriodSet();
        
        return currentCalendar;
    }

    public String onDialogOk()
    {
       boolean isNew = currentCalendar.getPeriodSetId() == null;
       
       currentCalendar = calendarService.update(currentCalendar);
       
       int id = currentCalendar.getPeriodSetId() ;
       
       currentCalendar = null ;
       
       if(isNew)
           
           return "calendarDetails?faces-redirect=true&id="+id ;
       
       if(filterTriggred)
           
            filter() ;
 
       notificationController.showNotification("Saved..", NotificationController.SUCCESS);
       
       return null ;
    }
    
    public void setFilterTriggred(boolean filterTriggred) {
        this.filterTriggred = filterTriggred;
    }

    public void setCurrentCalendar(GlPeriodSet currentCalendar) {
        this.currentCalendar = currentCalendar;
    }
    
    
    
}
