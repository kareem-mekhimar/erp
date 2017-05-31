/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.coa;

import com.bridge.entities.coa.CoaStructure;
import com.bridge.services.coa.CoaStructureService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author AIA
 */
@Model
public class CoaStructureController {
    
    @EJB
    private CoaStructureService coaStructureService;
    private CoaStructure coaStructure;
    private List<CoaStructure> coaStructureList;
    private String coaName;
    private String coaCode;
    private Integer status;
private List<CoaStructure> availableCoaStructure;

    public void setAvailableCoaStructure(List<CoaStructure> availableCoaStructure) {
        this.availableCoaStructure = availableCoaStructure;
    }

    public List<CoaStructure> getAvailableCoaStructure() {
        return availableCoaStructure;
    }

    public CoaStructure getCoaStructure() {
        return coaStructure;
    }

    public void setCoaStructure(CoaStructure coaStructure) {
        this.coaStructure = coaStructure;
    }

    public List<CoaStructure> getCoaStructureList() {
        return coaStructureList;
    }

    public void setCoaStructureList(List<CoaStructure> coaStructureList) {
        this.coaStructureList = coaStructureList;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public List<CoaStructure> completeCoaStructure(String text)
    {
        availableCoaStructure = coaStructureService.findCoaStructureByName(text) ;
       
       return availableCoaStructure ;
    }
      public void filter(){
        coaStructureList=coaStructureService.findCoaStructure(coaCode, coaName, status);     
    }
    
    
}
