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
public enum CustomerType {
    
    PERSON(1),
    GROUP(2),
    ORGANIZATION(3);
    
    private final Integer value;

    CustomerType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
    
}
