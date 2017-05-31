/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.enums;

/**
 *
 * @author AIA
 */
public enum RecRouting {
    NONE(1),
    STANDARD_RECEIPT(2),
    INSPECTION_REQUIRED(3),
    DIRECT_DELIVERY(4);
    private Integer value;
    RecRouting(Integer value){
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }
    
}
