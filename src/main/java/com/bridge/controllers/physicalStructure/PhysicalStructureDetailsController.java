/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.physicalStructure;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalLevel;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.StructureStatus;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.physicalStructure.PhysicalLevelService;
import com.bridge.services.physicalStructure.PhysicalStructureService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class PhysicalStructureDetailsController implements Serializable {
    
    @EJB
    private PhysicalStructureService physicalStructureService;
    @EJB
    private PhysicalLevelService physicalLevelService;
    
     @EJB
    private OrganizationUnitService orgUnitService;
     
     private PhysicalStructure currentPhysicalStructure;
     
     private Integer currentPhysicalStructureId;
     
     private PhysicalLevel currentPhysicalLevel;
     
     private List<PhysicalLevel> physicalLevels;
     
     private List<OrganizationUnit> businessGroups;
     
     private List<OrganizationUnit> legalEntities;
     
     private List<OrganizationUnit> operatingUnits;
     
     private StructureStatus[] status;

    public OrganizationUnitService getOrgUnitService() {
        return orgUnitService;
    }

    public void setOrgUnitService(OrganizationUnitService orgUnitService) {
        this.orgUnitService = orgUnitService;
    }

    public PhysicalStructure getCurrentPhysicalStructure() {
        if(currentPhysicalStructure==null){
            currentPhysicalStructure=new PhysicalStructure();
            currentPhysicalStructure.setPhysicalLevels(new ArrayList<PhysicalLevel>());
        }
        
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

    public List<OrganizationUnit> getBusinessGroups() {
        return businessGroups;
    }

    public void setBusinessGroups(List<OrganizationUnit> businessGroups) {
        this.businessGroups = businessGroups;
    }

    public List<OrganizationUnit> getLegalEntities() {
        return legalEntities;
    }

    public void setLegalEntities(List<OrganizationUnit> legalEntities) {
        this.legalEntities = legalEntities;
    }

    public List<OrganizationUnit> getOperatingUnits() {
        return operatingUnits;
    }

    public void setOperatingUnits(List<OrganizationUnit> operatingUnits) {
        this.operatingUnits = operatingUnits;
    }

    public List<PhysicalLevel> getPhysicalLevels() {
        return physicalLevels;
    }

    public void setPhysicalLevels(List<PhysicalLevel> physicalLevels) {
        this.physicalLevels = physicalLevels;
    }

    public PhysicalLevel getCurrentPhysicalLevel() {
        if(currentPhysicalLevel==null){
            currentPhysicalLevel=new PhysicalLevel();
            currentPhysicalLevel.setLevelStatus(true);
        }
        return currentPhysicalLevel;
    }

    public void setCurrentPhysicalLevel(PhysicalLevel currentPhysicalLevel) {
        this.currentPhysicalLevel = currentPhysicalLevel;
    }

    public StructureStatus[] getStatus() {
        return StructureStatus.values();
    }
     
    
    
    
        public String redirectToLevelDetails(int levelIndex)
    {
       
                  
PhysicalLevel physicalLevel=currentPhysicalStructure.getPhysicalLevels().get(levelIndex);
       
       
       return "physicalLevelDetails?faces-redirect=true&id="+physicalLevel.getLevelId();
    }
     public void loadModal(PhysicalLevel level) {

            currentPhysicalLevel = level;
            loadDependableLevel(level);
    }
    
    
       public void loadDependableLevel(PhysicalLevel level){
         

           
         physicalLevels=new ArrayList<PhysicalLevel>();
         if(level==null){
             
             physicalLevels.addAll(currentPhysicalStructure.getPhysicalLevels());
             
         }else{
             
            physicalLevels.addAll(currentPhysicalStructure.getPhysicalLevels().stream()
                    .filter(s-> s.getLevelOrder()<level.getLevelOrder())
                    .collect(Collectors.toList()));  
         }
       }
         
         
         public List<PhysicalLevel> completeLevel(String text) {
             return physicalLevelService.findLevelsByName(text);
         }
        
  
    
      public List<OrganizationUnit> completeBusinessGroup(String text) {
        businessGroups = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);
        return businessGroups;
    }
      public List<OrganizationUnit> completeLegalEntity(String text) {
        legalEntities = orgUnitService.findByOrgNameAndOrgParent(text, currentPhysicalStructure.getBusinessGroupId().getOrgUnitId());
        return legalEntities;
    }
      public List<OrganizationUnit> completeOperatingUnit(String text) {
        operatingUnits = orgUnitService.findByOrgNameAndOrgParent(text, currentPhysicalStructure.getLegalEntityId().getOrgUnitId());
        return operatingUnits;
    }
      
      
      public void addLine(){
          
          
          currentPhysicalLevel.setDependentTypeId(currentPhysicalLevel.getDependOnLevelOrder()!= null);
          
        if(currentPhysicalLevel.getLevelOrder()!=null)return;
        
         List<PhysicalLevel> levels=currentPhysicalStructure.getPhysicalLevels();
          
          if(levels==null){
              
              levels=new ArrayList<PhysicalLevel>();
              
              currentPhysicalStructure.setPhysicalLevels(levels);
          }
          
          Integer i=currentPhysicalStructure.getPhysicalLevels().size();
          
          currentPhysicalLevel.setPhysicalStrId(currentPhysicalStructure);
          
          currentPhysicalLevel.setLevelOrder(i+1);
          
          currentPhysicalStructure.getPhysicalLevels().add(currentPhysicalLevel);
          
          
      }
      
    public void loadStructure(){
        
        if(currentPhysicalStructureId!=null){
            
            currentPhysicalStructure = physicalStructureService.findPhysicalStructureWithLevels(currentPhysicalStructureId);
            
            if(currentPhysicalStructure == null){
                JSFUtils.redirectTo404("Physical Structure");
                
            }
        }
            
        
    }
    
    public String save(){
        physicalStructureService.update(currentPhysicalStructure);
        return "physicalStructureView?faces-redirect=true";
    }
     
}
