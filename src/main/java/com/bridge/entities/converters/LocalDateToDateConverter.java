/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.converters;

import java.time.LocalDate;
import java.sql.Date;
import java.time.Instant;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Bridge
 */

@Converter(autoApply = true)
public class LocalDateToDateConverter implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
      
        if(localDate == null)
            
            return null ;
        
        return Date.valueOf(localDate) ;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {

        if(date == null)
            
            return null ;
        
        return date.toLocalDate() ;
    }
    
}
