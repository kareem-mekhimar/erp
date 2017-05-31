/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.currency.FndCurrency;
import com.bridge.services.currency.CurrencyService;
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
public class FndCurrencyConverter implements Converter{
       
    @EJB
    private CurrencyService currencyService ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;

        return currencyService.findById(Integer.parseInt(value)) ;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           
        if(value == null)
            
            return null ;
        
        return String.valueOf(((FndCurrency) value).getCurrencyId()) ;
    }
    
}
