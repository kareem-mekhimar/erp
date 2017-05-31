/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.modules.CashManagementModuleSetup;
import com.bridge.services.modules.CashManagementModuleService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Bridge
 */

@Model
public class CashModuleSetupConverter implements Converter {

  @EJB
  private CashManagementModuleService service ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value == null)
            
            return null ;
        
        
        return service.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           
        if(value == null)
            
            return null ;
        
        CashManagementModuleSetup m = (CashManagementModuleSetup) value ;

        return String.valueOf(m.getId()) ;
    }
    
}

