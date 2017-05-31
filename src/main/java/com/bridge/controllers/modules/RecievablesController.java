/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.modules.ReceivableModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.modules.ReciveablesConfigurationService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class RecievablesController implements Serializable{
    
    @EJB
    private COAAccountService accountService ;
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private ReciveablesConfigurationService configurationService ;
    
    private OrganizationUnit operatingUnit ;
    
    private ReceivableModuleSetup currentModuleConfug ;
    
    private Integer currentOperatingUnitId ;
    
    public void loadModuleConfig()
    {
      if(currentOperatingUnitId != null)
      {
         operatingUnit = organizationUnitService.findById(currentOperatingUnitId) ;
         
         if(operatingUnit == null)
             
             JSFUtils.redirectTo404("OperatingUnit") ;
         
         else
         {
             currentModuleConfug = configurationService.findByOperating(operatingUnit) ;
             
             if(currentModuleConfug == null)
             {
                 currentModuleConfug = new ReceivableModuleSetup() ;
              
                 currentModuleConfug.setOperatingUnit(operatingUnit);
             }
         }
      }
      else
          
           JSFUtils.redirectTo404("OperatingUnit") ;
    }
    

    public List<GlCodeCombination> completeAccounts(String text) {
        
        return accountService.findByAlias(text) ;
    }
    
    public String save()
    {
       configurationService.update(currentModuleConfug) ;
       
       return "modulesview?faces-redirect=true" ;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public ReceivableModuleSetup getCurrentModuleConfug() {
        return currentModuleConfug;
    }

    public Integer getCurrentOperatingUnitId() {
        return currentOperatingUnitId;
    }

    public void setCurrentOperatingUnitId(Integer currentOperatingUnitId) {
        this.currentOperatingUnitId = currentOperatingUnitId;
    }
    
        
}
