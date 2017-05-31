/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.coa.CoaStructure;
import com.bridge.services.coa.CoaStructureService;
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
public class CoaStructureConverter implements Converter{

    @EJB
    private CoaStructureService structureService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
   
        if(value == null)
            
            return null ;
        
        return structureService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        return ((CoaStructure) value).getCoaId()+"" ;
    }
    
}
