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
public enum ApprovalLevel {
    //NONE(1),
    TWO_WAYS(2),
    THREE_WAYS(3)
    //FOUR_WAYS(4)
    ;
    
    private Integer value;
    ApprovalLevel(Integer value){
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }
    
}
