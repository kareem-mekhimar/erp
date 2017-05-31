/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.clients.ApSupplierContact;
import com.bridge.entities.clients.ClientContact;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.SiteService;
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
public class ClientContactConverter implements Converter{

    @EJB
    private SiteService siteService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
        
        return siteService.findContactById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        ClientContact contact = (ClientContact) value ;
        
        return contact.getContactId()+"";
    }
    
}
