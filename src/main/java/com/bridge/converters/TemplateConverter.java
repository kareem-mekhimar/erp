/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.inventory.ItemTemplate;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.ItemTemplateService;
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
public class TemplateConverter implements Converter{
    
    @EJB
    private ItemTemplateService templateService ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
        
        return templateService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        return String.valueOf(((ItemTemplate) value).getTemplateId()) ;
    }
    
    
}
