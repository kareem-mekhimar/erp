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
public enum PhoneType {
    
    HOME(1),
    WORK(2),
    MOBILE(3),
    FAX(4);
    
    private final Integer value;

    PhoneType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
