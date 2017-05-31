/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.moveorder;

import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.services.moveorder.MoveOrderService;
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
public class MoveOrderSearchController implements Serializable{
    
    @EJB
    private MoveOrderService moveOrderService ;
    
    private List<MtlTxnRequestHeader> mtlHeaderList ;
    
    private MtlTxnRequestHeader selectedMo ;
    
    private Integer moveOrderId ;
    
    private Integer batchId ;
    
    public void filter()
    {
       mtlHeaderList = moveOrderService.findByIdOrBatchId(moveOrderId, batchId) ;
    }

    public List<MtlTxnRequestHeader> getMtlHeaderList() {
        return mtlHeaderList;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public Integer getMoveOrderId() {
        return moveOrderId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setMoveOrderId(Integer moveOrderId) {
        this.moveOrderId = moveOrderId;
    }

    public MtlTxnRequestHeader getSelectedMo() {
        return selectedMo;
    }

    public void setSelectedMo(MtlTxnRequestHeader selectedMo) {
        this.selectedMo = selectedMo;
    }
    
    
}
