/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.hr.People;
import com.bridge.entities.security.UserAccount;
import com.bridge.services.security.AccountsService;
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
public class UserAccountConverter implements Converter{
   
    @EJB
    private AccountsService accountsService ;
            
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value == null)
            
            return null ;
        
        return accountsService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           
        if(value == null)
            
            return null ;
        
        UserAccount person = (UserAccount) value ;
        
        return person.getId()+"" ;
    }  
}
