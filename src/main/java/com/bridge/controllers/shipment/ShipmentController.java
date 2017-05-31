/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.shipment;

import com.bridge.entities.shipment.ShipmentHeader;
import com.bridge.services.shipment.ShipmentService;
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
public class ShipmentController implements Serializable{
    
    @EJB
    private ShipmentService shipmentService;

    private List<ShipmentHeader> shepmentHeaders;
    
    private Integer salesOrderId;
    
    private Integer moveOrderId;

    public List<ShipmentHeader> getShepmentHeaders() {
        return shepmentHeaders;
    }

    public void setShepmentHeaders(List<ShipmentHeader> shepmentHeaders) {
        this.shepmentHeaders = shepmentHeaders;
    }


    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Integer getMoveOrderId() {
        return moveOrderId;
    }

    public void setMoveOrderId(Integer moveOrderId) {
        this.moveOrderId = moveOrderId;
    }
    
    
    public void filter(){
        
       shepmentHeaders = shipmentService.findShipmentBySalesAndMoveOrder(salesOrderId, moveOrderId);
    }
    
    
    
    
    
}
