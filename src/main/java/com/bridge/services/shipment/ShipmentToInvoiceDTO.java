/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.shipment;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Bridge
 */

public class ShipmentToInvoiceDTO implements Serializable{
    
    private Integer salesLineId ;
    
    private Integer shipmentLineId ;
    
    private BigDecimal totalDelivered;

    public ShipmentToInvoiceDTO(Integer salesLineId, Integer shipmentLineId, BigDecimal totalDelivered) {
        this.salesLineId = salesLineId;
        this.shipmentLineId = shipmentLineId;
        this.totalDelivered = totalDelivered;
    }

    public Integer getShipmentLineId() {
        return shipmentLineId;
    }

    

    public Integer getSalesLineId() {
        return salesLineId;
    }

    public BigDecimal getTotalDelivered() {
        return totalDelivered;
    }
    
   
}
