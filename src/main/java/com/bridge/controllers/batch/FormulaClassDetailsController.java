/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;
import com.bridge.entities.batch.FormulaClass;
import com.bridge.services.batch.FormulaClassService;
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
public class FormulaClassDetailsController implements Serializable{

    @EJB
    private FormulaClassService formulaClassService;
   
    private FormulaClass formulaClass ;
    
    private Integer currentFormulaClassId ;
    
    
    public FormulaClass getFormulaClass() {
        
        if(formulaClass == null){
            formulaClass = new FormulaClass() ;
            formulaClass.setStatus(true);
        }
            
        
        return formulaClass;
    }

    public void setFormulaClass(FormulaClass formulaClass) {
        this.formulaClass = formulaClass;
    }

    public void setCurrentFormulaClassId(Integer currentFormulaClassId) {
        this.currentFormulaClassId = currentFormulaClassId;
    }

    public Integer getCurrentFormulaClassId() {
        return currentFormulaClassId;
    }

    
    public void loadFormulaClass()
    {
        
        
        if(currentFormulaClassId != null) 
        {
            formulaClass = formulaClassService.findById(currentFormulaClassId) ;
            
            if(formulaClass == null){
                JSFUtils.redirectTo404("Formula Class");
            }
            
        }
    }
    
    public String save() {
        
        formulaClassService.update(formulaClass);
        
        return "formulaClassView?faces-redirect=true" ;
    }
    
          public void validateCodeExist(FacesContext context,UIComponent component , Object value)
    {
        UIInput input = (UIInput) component ;
             if(currentFormulaClassId != null)
        {
            if(input.getValue().equals(value))
                
                return;
        }
        if(formulaClassService.isCodeExists((String) value))
        {
             throw new ValidatorException(new FacesMessage(value + " already existes..")) ;
        }
    }

    public void validateNameExist(FacesContext context,UIComponent component , Object value)
    {
            UIInput input = (UIInput) component ;
        
        if(currentFormulaClassId != null)
        {
            if(input.getValue().equals(value))
                
                return;
        }
        if(formulaClassService.isNameExists((String) value))
        {
             throw new ValidatorException(new FacesMessage(value + " already existes..")) ;
        }
    }

}
