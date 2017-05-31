/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.entities.batch.Formula;
import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.batch.BatchService;
import com.bridge.services.batch.FormulaService;
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
public class BatchCreateController implements Serializable{
    
    @EJB
    private BatchService batchService ;
    
    @EJB
    private FormulaService formulaService ;
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    private GmeBatchHeader newBatch = new GmeBatchHeader() ;

    public List<Formula> completeFormulaByName(String text)
    {
      return formulaService.findApprovedByName(text) ;
    }
        
    public List<OrganizationUnit> completeOperatingunit(String text)
    {
       return organizationUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text) ;
    }
        
    public GmeBatchHeader getNewBatch() {
      
        return newBatch;
    }
    
    public String save()
    {
       batchService.create(newBatch);
       
       return "batchDetails?faces-redirect=true&id="+newBatch.getBatchId() ;
    }
}
