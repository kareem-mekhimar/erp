/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.batch.FormulaClass;
import com.bridge.services.batch.FormulaClassService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author AIA
 */
@Model
public class FormulaClassConverter implements Converter {

    @EJB
    private FormulaClassService formulaClassService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
              if(value == null)
            
            return null ;
        
        return formulaClassService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
                       if(value == null)
            
            return null ;
        
        FormulaClass formulaClass = (FormulaClass) value ;
        
        return formulaClass.getId()+"" ;

    }
    
}
