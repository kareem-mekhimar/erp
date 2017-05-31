/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.moveorder;

import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.services.moveorder.MoveOrderService;
import java.io.Serializable;
import java.util.Arrays;
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
public class MoveOrderController implements Serializable{
    
    @EJB
    private MoveOrderService moveOrderService ;
    
    private List<MtlTxnRequestHeader> moveOrders ;
    
    private Integer currentNo ;

    public void filter()
    {
       moveOrders = moveOrderService.findNormalById(currentNo) ;   
    }
    
    public Integer getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(Integer currentNo) {
        this.currentNo = currentNo;
    }

    public List<MtlTxnRequestHeader> getMoveOrders() {
        return moveOrders;
    }
    
    
    
}
