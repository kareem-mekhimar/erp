/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.physicalStructure;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.services.physicalStructure.PhysicalStructureService;
import java.io.Serializable;
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
public class PhysicalStructureController implements Serializable {
    
    @EJB
    private PhysicalStructureService physicalStructureService;
    
    private PhysicalStructure currentPhysicalStructure;
    
    private Integer currentPhysicalStructureId;
    
    private String structureName;
    
    private List<PhysicalStructure> structureList;
    
    private OrganizationUnit operatingUnit;

    public PhysicalStructure getCurrentPhysicalStructure() {
        return currentPhysicalStructure;
    }

    public void setCurrentPhysicalStructure(PhysicalStructure currentPhysicalStructure) {
        this.currentPhysicalStructure = currentPhysicalStructure;
    }

    public Integer getCurrentPhysicalStructureId() {
        return currentPhysicalStructureId;
    }

    public void setCurrentPhysicalStructureId(Integer currentPhysicalStructureId) {
        this.currentPhysicalStructureId = currentPhysicalStructureId;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public List<PhysicalStructure> getStructureList() {
        return structureList;
    }

    public void setStructureList(List<PhysicalStructure> structureList) {
        this.structureList = structureList;
    }
    
    public void filter(){
        structureList=physicalStructureService.findPhysicalStructure(structureName, operatingUnit);
    }
    
}
