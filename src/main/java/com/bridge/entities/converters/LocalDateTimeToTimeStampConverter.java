/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.converters;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Bridge
 */

@Converter(autoApply = true)
public class LocalDateTimeToTimeStampConverter implements AttributeConverter<LocalDateTime,Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {

        return Timestamp.valueOf(attribute) ;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
    
        return dbData.toLocalDateTime() ;
    }    
}
