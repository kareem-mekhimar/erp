/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.commandbutton.CommandButtonRenderer;

 import org.primefaces.util.HTML;

/**
 *
 * @author Bridge
 */

public class AlTairButtonRenderer extends CommandButtonRenderer{

    @Override
    protected void encodeMarkup(FacesContext context, CommandButton button) throws IOException {
		
        ResponseWriter writer = context.getResponseWriter();
 		
        String clientId = button.getClientId(context);
 	
        String type = button.getType();
        
        boolean pushButton = (type.equals("reset")||type.equals("button"));
        
        Object value = button.getValue();
        
        String icon = button.resolveIcon();
        
        String request = pushButton ? null: buildRequest(context, button, clientId);        
        
        String onclick = buildDomEvent(context, button, "onclick", "click", "action", request);
         
        writer.startElement("button", button);
 	
        writer.writeAttribute("id", clientId, "id");
 	
        writer.writeAttribute("name", clientId, "name");
        
        writer.writeAttribute("class", button.resolveStyleClass(), "styleClass");

        if(onclick != null) {

            if(button.requiresConfirmation()) {
            
                writer.writeAttribute("data-pfconfirmcommand", onclick, null);
                
                writer.writeAttribute("onclick", button.getConfirmationScript(), "onclick");
            }
            else
           
                writer.writeAttribute("onclick", onclick, "onclick");
 		
        }
		
        renderPassThruAttributes(context, button, HTML.BUTTON_ATTRS, HTML.CLICK_EVENT);
 
        if(button.isDisabled()) writer.writeAttribute("disabled", "disabled", "disabled");
        if(button.isReadonly()) writer.writeAttribute("readonly", "readonly", "readonly");
 		
         //icon
         if(icon != null && !icon.trim().equals("")) {
             String defaultIconClass = button.getIconPos().equals("left") ? HTML.BUTTON_LEFT_ICON_CLASS : HTML.BUTTON_RIGHT_ICON_CLASS; 
             String iconClass = defaultIconClass + " " + icon;
             
             writer.startElement("span", null);
             writer.writeAttribute("class", iconClass, null);
             writer.endElement("span");
         }
         
         String materialIcon = (String) button.getAttributes().get("materialIcon") ;
         
         if(materialIcon != null)
         {
           writer.startElement("i", null) ;

           String iconClasses = (String) button.getAttributes().get("iconClasses") ;
            
           writer.writeAttribute("class","material-icons "+iconClasses, null);
           
           writer.writeText(materialIcon,null);
           
           writer.endElement("i");
         }
         else{
          //text
           writer.startElement("span", null);
           writer.writeAttribute("class", HTML.BUTTON_TEXT_CLASS, null);
        
           if(value == null) {
              writer.write("ui-button");
           }
           else {
             if(button.isEscape())
                 writer.writeText(value, "value");
             else
                writer.write(value.toString());
           }
        
           writer.endElement("span");             
         }
		
	writer.endElement("button");
    }        
   
}
  
    
