/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.categories;

import com.bridge.entities.inventory.MtlCategory;
import com.bridge.services.categories.MtlCategoryService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class MtlCategoryDetailsController implements Serializable{

  @EJB
    private MtlCategoryService categoryService;
   
    private MtlCategory category ;
    
    private List<MtlCategory> parentCategories;
    
    private Integer currentCategoryId ;
    
    private boolean parent;
    
    
    public MtlCategory getCategory() {
        
        if(category == null){
            category = new MtlCategory() ;
            category.setEnabledFlagId(true);
        }
        return category;
    }
    
    
    public void setCategory(MtlCategory category) {
        this.category = category;
    }

    public List<MtlCategory> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<MtlCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public boolean getParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }


    
    
    public void setCurrentCategoryId(Integer currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }

    public Integer getCurrentCategoryId() {
        return currentCategoryId;
    }

    
    public void loadCategory()
    {
        
        
        if(currentCategoryId != null) 
        {
            category = categoryService.findById(currentCategoryId) ;
            
            if(category == null){
                
                JSFUtils.redirectTo404("Category");
                
            }
            
            parent=categoryService.isParent(currentCategoryId);
        }
                parentCategories = categoryService.findAllParent().stream().filter(l->! l.equals(category)).collect(Collectors.toList());

    }

    
    public String save() {
        
       
        categoryService.update(category);
        
        return "itemCategoryView?faces-redirect=true" ;
    }
    
    
    public void validateNameExist(FacesContext context,UIComponent component , Object value)
    {
        UIInput input = (UIInput) component ;
        
        if(currentCategoryId != null)
        {
            if(input.getValue().equals(value))
                
                return;
        }
        
        if(categoryService.isNameExists((String) value))
        {
             throw new ValidatorException(new FacesMessage(value + " already existes..")) ;
        }
    }

}
