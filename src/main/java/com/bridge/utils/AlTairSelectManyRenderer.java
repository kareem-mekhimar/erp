/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.selectmanymenu.SelectManyMenu;
import org.primefaces.component.selectmanymenu.SelectManyMenuRenderer;

/**
 *
 * @author Bridge
 */
public class AlTairSelectManyRenderer extends SelectManyMenuRenderer{

    @Override
    protected void encodeFilter(FacesContext context, SelectManyMenu menu) throws IOException {
       
        ResponseWriter writer = context.getResponseWriter();
        String id = menu.getClientId(context) + "_filter";
        boolean disabled = menu.isDisabled();
        String filterClass = disabled ? SelectManyMenu.FILTER_CLASS + " ui-state-disabled" : SelectManyMenu.FILTER_CLASS;
        
        writer.startElement("div", null);
        writer.writeAttribute("class", SelectManyMenu.FILTER_CONTAINER_CLASS, null);
       
//        writer.startElement("span", null);
//        writer.writeAttribute("class", SelectManyMenu.FILTER_ICON_CLASS, id);
//        writer.endElement("span");
        
        writer.startElement("input", null);
        writer.writeAttribute("class", "md-input "+filterClass, null);
        writer.writeAttribute("id", id, null);
        writer.writeAttribute("name", id, null);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("autocomplete", "off", null);
        writer.writeAttribute("placeholder", "Filter", null);
        writer.writeAttribute("style", "margin-bottom:15px", null);
        if(disabled) writer.writeAttribute("disabled", "disabled", null);

        writer.endElement("input");
        
        writer.endElement("div");
    }
    
    
}
