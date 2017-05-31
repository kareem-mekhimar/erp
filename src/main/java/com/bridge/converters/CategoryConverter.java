/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.inventory.MtlCategory;
import com.bridge.services.categories.MtlCategoryService;
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
public class CategoryConverter implements Converter{
    
    @EJB
    private MtlCategoryService categoryService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value == null)
            
            return null ;
        
        return categoryService.findById(Integer.parseInt(value)) ;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           
        if(value == null)
            
            return null ;
        
        MtlCategory category = (MtlCategory) value ;
        
        return category.getCategoryId()+"" ;
    }
}
