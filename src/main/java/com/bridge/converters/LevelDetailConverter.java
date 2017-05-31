/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.physicalStructure.PhysicalLevel;
import com.bridge.entities.physicalStructure.PhysicalLevelDetail;
import com.bridge.services.physicalStructure.PhysicalLevelService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author AIA
 */
@Model
public class LevelDetailConverter implements Converter{

    @EJB
    private PhysicalLevelService levelService;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
 if(value == null)
            
            return null ;
 
        try {
            Integer.parseInt(value);
        } catch (Exception e) {
            return null ;
        }
        
        return levelService.findDetailById(Integer.parseInt(value)) ;
    }

    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
      if(value == null)
            
            return null ;
        
        PhysicalLevelDetail level = (PhysicalLevelDetail) value ;
        
        return level.getId()+"" ;
    }
    
    
}
