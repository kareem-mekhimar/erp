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
public enum ClientType {
    
SUPPLIER("Supplier"),
CUSTOMER("Customer");

    private final String value;
    
    ClientType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
   
}
