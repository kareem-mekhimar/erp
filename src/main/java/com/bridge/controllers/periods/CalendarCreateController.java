/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.periods;

import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.services.periods.CalendarService;
import com.bridge.services.periods.PeriodTypeService;
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
public class CalendarCreateController implements Serializable{
    
    @EJB
    private CalendarService calendarService ;
    
    @EJB
    private PeriodTypeService periodTypeService ;
    
    private GlPeriodSet currentCalendar = new GlPeriodSet();

    
    public List<GlPeriodType> completePeriods(String text)
    {
       return periodTypeService.findByName(text) ;
    }
    
    public String save()
    {
        calendarService.create(currentCalendar);
        
        return "calendarDetails?faces-redirect=true&id="+currentCalendar.getPeriodSetId() ;
    }
    
    public GlPeriodSet getCurrentCalendar() {
        return currentCalendar;
    }
    
}
