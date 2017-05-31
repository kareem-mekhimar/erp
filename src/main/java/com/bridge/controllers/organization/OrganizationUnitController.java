/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.organization;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named
@ViewScoped
public class OrganizationUnitController implements Serializable {

    @EJB
    private OrganizationUnitService organizationUnitService;
    private Integer enabledFlagId;
    private Integer organizationId;
    private String orgUnitName;
    private List<OrganizationUnit> organizationUnitsList;
    private OrganizationUnit userOrg;
    private OrganizationUnitType listUnitType;
    private OrganizationUnitType unitType;
    private OrganizationUnit orgUnit;
    private Integer orgUnitId;
    private List<OrganizationUnit> orgUnits;
    private String orgCode;
    

    public OrganizationUnit getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(OrganizationUnit userOrg) {
        this.userOrg = userOrg;
    }

    public OrganizationUnitType getListUnitType() {
        return listUnitType;
    }

    public void setListUnitType(OrganizationUnitType listUnitType) {
        this.listUnitType = listUnitType;
    }

    public OrganizationUnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(OrganizationUnitType unitType) {
        this.unitType = unitType;
    }

    
    public List<OrganizationUnit> getOrgUnits() {
        return orgUnits;
    }

    public void setOrgUnits(List<OrganizationUnit> orgUnits) {
        this.orgUnits = orgUnits;
    }

    public void setOrgUnit(OrganizationUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public OrganizationUnit getOrgUnit() {
        return orgUnit;
    }

    public Integer getEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(Integer enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public List<OrganizationUnit> getOrganizationUnitsList() {
        return organizationUnitsList;
    }

    public void setOrganizationUnitsList(List<OrganizationUnit> organizationUnitsList) {
        this.organizationUnitsList = organizationUnitsList;
    }

    public Integer getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Integer orgUnitId) {
        this.orgUnitId = orgUnitId;
    }



    public void chooseType(Integer t) {
        switch (t) {
            case 0:
                setUnitType(OrganizationUnitType.BUSINESS_GROUP) ;
                setListUnitType(null) ; 
                break;
            case 1:
                setUnitType(OrganizationUnitType.LEGAL_ENTITY);
                setListUnitType(OrganizationUnitType.BUSINESS_GROUP) ;
                break;
            case 2:
                setUnitType(OrganizationUnitType.OPERATING_UNIT);
                setListUnitType(OrganizationUnitType.LEGAL_ENTITY) ;
                break;
            default:
                setUnitType(null) ;
                setListUnitType(null) ;
               
        }
        orgUnits = organizationUnitService.findByOrgType(listUnitType);
    }

//    public void filter() {
//        organizationUnitsList = organizationUnitService.findOrgUnit(organizationId, orgUnitName, enabledFlagId, orgUnitId, unitType, orgCode);
//    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public void filter(OrganizationUnitType orgUnitType){
        
        organizationUnitsList = organizationUnitService.findOrgUnit(organizationId, orgUnitName, enabledFlagId, orgUnitId, orgUnitType, orgCode);
    
    }
    
    
}
