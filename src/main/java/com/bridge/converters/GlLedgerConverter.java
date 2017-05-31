/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.services.ledger.GlLedgerService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Model
public class GlLedgerConverter implements Converter{

    @EJB
    private GlLedgerService ledgerService ;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value == null)
            
            return null ;
        
        return ledgerService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value == null)
            
            return null ;
        
        return ((GlLedger) value).getLedgerId()+"" ;
    }
    
}
