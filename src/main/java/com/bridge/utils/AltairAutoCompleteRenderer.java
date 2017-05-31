/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.autocomplete.AutoCompleteRenderer;

/**
 *
 * @author kareem
 */
public class AltairAutoCompleteRenderer extends AutoCompleteRenderer{

    @Override
    protected void encodeSingleMarkup(FacesContext context, AutoComplete ac) throws IOException {
     
        ResponseWriter writer = context.getResponseWriter();

        String clientId = ac.getClientId(context);

        encodeInput(context, ac, clientId);
        
        if(ac.getVar() != null) {
            encodeHiddenInput(context, ac, clientId);
        }
        if(ac.isDropdown()) {

            encodeDropDown(context, ac);
        }
        encodePanel(context, ac);
  
    }
       
}
