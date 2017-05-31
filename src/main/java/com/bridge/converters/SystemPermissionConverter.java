/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.security.SystemPermission;
import com.bridge.services.security.ResponsablityService;
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
public class SystemPermissionConverter implements Converter{

    @EJB
    private ResponsablityService responsablityService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
        
        return responsablityService.findSystemPermissionById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        return String.valueOf(((SystemPermission) value).getId()) ;
    }
        
}
