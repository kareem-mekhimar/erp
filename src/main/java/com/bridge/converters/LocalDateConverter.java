/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bridge
 */

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter{

    private DateTimeFormatter dateTimeFormatter ;

    public LocalDateConverter() {
       dateTimeFormatter = new DateTimeFormatterBuilder()
             .appendPattern("dd-MM-yyyy").toFormatter();
    }
    
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value == null)
            
            return null ;
        
        try{
        
            return LocalDate.parse(value,dateTimeFormatter);
        
        }catch(DateTimeParseException e)
        {
           throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   "Bad Format. must be (dd-MM-yyyy)", "Bad Format. must be (dd-MM-yyyy)")) ;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value == null)
            
            return null ;

        return dateTimeFormatter.format((LocalDate) value) ;
    }
    
}
