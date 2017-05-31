/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.assets.AssetService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class AssetReceiptsController implements Serializable{
    
    @EJB
    private AssetService assetService;
    @EJB
    private OrganizationUnitService orgUnitService;
    
    private String name;
    
    private OrganizationUnit org;
    
    private List<FaAdditions> faAdditions;
    
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationUnit getOrg() {
        return org;
    }

    public void setOrg(OrganizationUnit org) {
        this.org = org;
    }

    public List<FaAdditions> getFaAdditions() {
        return faAdditions;
    }

    public void setFaAdditions(List<FaAdditions> faAdditions) {
        this.faAdditions = faAdditions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
 
    
     public List<OrganizationUnit> completeOperatingUnit(String text) {

        return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

    }
    
    public void filter(){
       faAdditions = assetService.findAdditions(org, name);
    }
    
    
    
    
}
