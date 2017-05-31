/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.clients.HzParties;
import com.bridge.entities.clients.HzPartySite;
import com.bridge.services.clients.HzPartyService;
import java.io.Serializable;
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
public class HzPartySiteConverter implements Converter{
    
    @EJB
    private HzPartyService partyService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
        
        return partyService.findSiteById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;
        
        return ((HzPartySite) value).getPartySiteId()+"" ;
    }
    
}
