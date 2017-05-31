/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.modules;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.organization.OrganizationUnitService;
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
public class ModulesController implements Serializable {

    private enum ModulesNames {
        PAYABLES, INVENTORIES, RECIEVAVBLES, FIXEDASSETS , CASH;
    }

    @EJB
    private OrganizationUnitService organizationUnitService;

    private OrganizationUnit selectedOrg;

    private ModulesNames currentSelectedModule;

    public List<OrganizationUnit> completeOrgUnit(String text) {
        return organizationUnitService.findOperatingAssignedToLedgerByName(text);
    }

    public String redirectToModule() {
        
        if (currentSelectedModule == ModulesNames.PAYABLES) {
            
            return "payables?faces-redirect=true&operating=" + selectedOrg.getOrgUnitId();
            
        } else if (currentSelectedModule == ModulesNames.INVENTORIES) {
            
            return "inventories?faces-redirect=true&operating=" + selectedOrg.getOrgUnitId();
            
        } else if (currentSelectedModule == ModulesNames.RECIEVAVBLES) {
            
            return "reciveables?faces-redirect=true&operating=" + selectedOrg.getOrgUnitId();
            
        } else if (currentSelectedModule == ModulesNames.FIXEDASSETS) {
            
            return "fixedassets?faces-redirect=true&operating=" + selectedOrg.getOrgUnitId();
            
        }
        else if(currentSelectedModule == ModulesNames.CASH)
        {
            return "cashmanagement?faces-redirect=true&operating=" + selectedOrg.getOrgUnitId();

        }
        
        return null;
    }

    public OrganizationUnit getSelectedOrg() {
        return selectedOrg;
    }

    public void setSelectedOrg(OrganizationUnit selectedOrg) {
        this.selectedOrg = selectedOrg;
    }

    public ModulesNames getCurrentSelectedModule() {
        return currentSelectedModule;
    }

    public void setCurrentSelectedModule(ModulesNames currentSelectedModule) {
        this.currentSelectedModule = currentSelectedModule;
    }

}
