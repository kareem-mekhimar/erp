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
public enum EmailType {
        PERSONAL(1),
    BUSINESS(2);

    
    private final Integer value;

    EmailType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
