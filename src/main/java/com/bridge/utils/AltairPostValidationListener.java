/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ListenerFor;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 *
 * @author Bridge
 */
public class AltairPostValidationListener implements SystemEventListener{

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {

        UIInput source = (UIInput) event.getSource();
 
        if(!source.isValid()) {
         
            System.out.println("com.bridge.utils.AltairPostValidationListener.processEvent() "+source.getAttributes().get("styleClass"));
            //source.getAttributes().put("styleClass", "ui-input-invalid");
        }
    }

    @Override
    public boolean isListenerForSource(Object source) {

        return true ;
    }
    
}
