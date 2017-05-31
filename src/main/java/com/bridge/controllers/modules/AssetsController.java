/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.modules;

import com.bridge.entities.modules.AssetModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.modules.AssetConfigurationService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */

@Named
@ViewScoped
public class AssetsController implements Serializable {

    @EJB
    private OrganizationUnitService organizationUnitService;
    
    @EJB
    private AssetConfigurationService configurationService ;

    private AssetModuleSetup currentModuleConfug ;
    
    private OrganizationUnit operatingUnit;
    private Integer currentOperatingUnitId;
    


    public void loadModuleConfig() {
        if (currentOperatingUnitId != null) {
            operatingUnit = organizationUnitService.findById(currentOperatingUnitId);

            if (operatingUnit == null) {
                JSFUtils.redirectTo404("OperatingUnit");
            } else {
                currentModuleConfug = configurationService.findByOperating(operatingUnit);

                if (currentModuleConfug == null) {
                    currentModuleConfug = new AssetModuleSetup();

                    currentModuleConfug.setOperatingUnit(operatingUnit);
                }
            }
        } else {
            JSFUtils.redirectTo404("OperatingUnit");
        }
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public Integer getCurrentOperatingUnitId() {
        return currentOperatingUnitId;
    }

    public void setCurrentOperatingUnitId(Integer currentOperatingUnitId) {
        this.currentOperatingUnitId = currentOperatingUnitId;
    }
    public AssetModuleSetup getCurrentModuleConfug() {
        return currentModuleConfug;
    }
    
    public String save()
    {
       configurationService.update(currentModuleConfug) ;
       
       return "modulesview?faces-redirect=true" ;
    }
    
    
    
}
