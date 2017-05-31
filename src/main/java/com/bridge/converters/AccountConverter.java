/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.services.coa.COAAccountService;
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
public class AccountConverter implements Converter {
       
    @EJB
    private COAAccountService service ;

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
        
        GlCodeCombination m = (GlCodeCombination) value ;

        return String.valueOf(m.getCodeCombinationId()+"") ;
    }
    
}
