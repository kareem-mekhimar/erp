/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.enums;

/**
 *
 * @author Administrator
 */
public enum StructureStatus {
    OPENED("Opened"),
    LOCKED ("Locked");
    
    private String value;
    
    StructureStatus(String value){
        
        this.value = value ;
        
    }

    public String getValue() {
        return value;
    }
    
}
