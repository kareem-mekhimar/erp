/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.batch.Formula;
import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.enums.BatchStatus;
import com.bridge.services.batch.BatchService;
import com.bridge.services.batch.FormulaService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class BatchController implements Serializable{
    
    @EJB
    private BatchService batchService ;
    
    private Integer currentNo ;
    
    private BatchStatus currentStatus ;
    
    private String currentBatchName ;
    
    private BatchStatus [] states ;

    private List<GmeBatchHeader> batches ;

    public String getCurrentBatchName() {
        return currentBatchName;
    }

    public BatchStatus getCurrentStatus() {
        return currentStatus;
    }

    public BatchStatus[] getStates() {
        return BatchStatus.values() ;
    }


    
    public void setCurrentNo(Integer currentNo) {
        this.currentNo = currentNo;
    }

    public Integer getCurrentNo() {
        return currentNo;
    }

    
    public void setCurrentBatchName(String currentBatchName) {
        this.currentBatchName = currentBatchName;
    }

    public void setCurrentStatus(BatchStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public List<GmeBatchHeader> getBatches() {
    
        return batches;
    }
   
    public void filter()
    {
       batches = batchService.findByNoOrNameOrStatus(currentNo, currentBatchName, currentStatus) ;
    }
    
}
