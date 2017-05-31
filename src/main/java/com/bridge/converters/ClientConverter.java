/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.Client;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.ClientService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author kareem
 */

@Model
public class ClientConverter implements Converter{

    @EJB
    private ClientService clientService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
        if(value != null)
        {
           return clientService.findById(Integer.parseInt(value)) ;
        }
        
        return null ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value != null)
            
            return ((Client) value).getClientId()+"" ;
        
        return null ;
    }
    
}
