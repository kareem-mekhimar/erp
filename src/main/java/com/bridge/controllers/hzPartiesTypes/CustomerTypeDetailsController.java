/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hzPartiesTypes;

/**
 *
 * @author Administrator
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bridge.entities.hzPartiesTypes.HzPartiesTypes;
import com.bridge.services.hzPartiesTypes.HzPartiesTypesService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
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
public class CustomerTypeDetailsController implements Serializable{

    @EJB
    private HzPartiesTypesService hzPartiesTypesService;
        
    private HzPartiesTypes hzPartiesTypes  ;
   
    private Integer currentTypeId ;
       
    public HzPartiesTypes getHzPartiesTypes() {
        
        if(hzPartiesTypes == null)
            
            hzPartiesTypes = new HzPartiesTypes() ;
        
        return hzPartiesTypes;
    }

    public void setHzPartiesTypes(HzPartiesTypes hzPartiesTypes) {
        this.hzPartiesTypes = hzPartiesTypes;
    }

    public Integer getCurrentTypeId() {
        return currentTypeId;
    }

    public void setCurrentTypeId(Integer currentTypeId) {
        this.currentTypeId = currentTypeId;
    }
   
    public void loadType()
    {
        
        if(currentTypeId != null) 
        {
            hzPartiesTypes = hzPartiesTypesService .findById(currentTypeId) ;
            
            if(hzPartiesTypes == null)
            {
                JSFUtils.redirectTo404("HzPartiesTypes With no = "+currentTypeId);
            }
        }
    }
    
    public String save() {
        
        hzPartiesTypesService.update(hzPartiesTypes);
        
        return "customerTypesView?faces-redirect=true" ;
    }
    
    public void validateNameExist(FacesContext context,UIComponent component , Object value)
    {
            UIInput input = (UIInput) component ;
        
        if(currentTypeId != null)
        {
            if(input.getValue().equals(value))
                
                return;
        }
        if( hzPartiesTypesService.isNameExists((String) value))
        {
             throw new ValidatorException(new FacesMessage(value + " already existes..")) ;
        }
    }

}
