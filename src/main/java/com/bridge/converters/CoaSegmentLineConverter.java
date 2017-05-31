/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.coa.CoaSegmentLine;
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
public class CoaSegmentLineConverter implements Converter{

    @EJB
    private CoaStructureService structureService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
     
        //============================== RETURN TEST =======================================
        
        try {
            
            Integer.parseInt(value);
        } catch (Exception e) {
            return null ;
        }
 
        
        return structureService.findCoaSegmentLineById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        return ((CoaSegmentLine) value).getSegmentDetailId() + "";
    }
    
}
