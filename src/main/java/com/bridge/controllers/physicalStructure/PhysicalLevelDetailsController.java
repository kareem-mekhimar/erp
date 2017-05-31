/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.physicalStructure;

import com.bridge.entities.physicalStructure.PhysicalLevel;
import com.bridge.entities.physicalStructure.PhysicalLevelDetail;
import com.bridge.services.physicalStructure.PhysicalLevelService;
import com.bridge.services.physicalStructure.PhysicalStructureService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
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
public class PhysicalLevelDetailsController implements Serializable {
    
    @EJB
    private PhysicalLevelService levelService;
    
    @EJB
    private PhysicalStructureService structureService;
    
    private Integer currentLevelId;
    
    private PhysicalLevel currPhysicalLevel;

    public Integer getCurrentLevelId() {
        return currentLevelId;
    }

    public void setCurrentLevelId(Integer currentLevelId) {
        this.currentLevelId = currentLevelId;
    }

    public PhysicalLevel getCurrPhysicalLevel() {
        if(currPhysicalLevel==null)currPhysicalLevel=new PhysicalLevel();
        return currPhysicalLevel;
    }

    public void setCurrPhysicalLevel(PhysicalLevel currPhysicalLevel) {
        this.currPhysicalLevel = currPhysicalLevel;
    }
    
    public List<PhysicalLevelDetail> completeLevelDetail(String text){
        
        return levelService.findDependsLevelDetail(currPhysicalLevel.getDependOnLevelOrder(), currPhysicalLevel.getPhysicalStrId(), text);
    }
    
    
    public void addLine(){
        List<PhysicalLevelDetail> levelDetails=currPhysicalLevel.getLevelDetails();
        
        if(levelDetails==null){
            levelDetails=new ArrayList<PhysicalLevelDetail>();
            currPhysicalLevel.setLevelDetails(levelDetails);
        }
            PhysicalLevelDetail detail=new PhysicalLevelDetail();
            detail.setDetailStatus(true);
            detail.setLevelId(currPhysicalLevel);
            currPhysicalLevel.getLevelDetails().add(detail);
        
    }
    
    public void loadLevel(){
        if(currentLevelId!=null){
            
            currPhysicalLevel= levelService.findPhysicalLevelWithDetails(currentLevelId);
            
            if(currPhysicalLevel == null){
                
                JSFUtils.redirectTo404("Physical level");
            }
        }
           
    }
    
    public String save(){
        
        levelService.update(currPhysicalLevel);
        
        return "physicalStructureDetails?faces-redirect=true&id="+currPhysicalLevel.getPhysicalStrId().getId();
    }
    
}
