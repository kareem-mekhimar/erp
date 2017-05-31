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
public enum ListLineType {
    
    ITEM("Item"),
    SERVICE("Service");

    private final String val;

    ListLineType(String v) {
        val = v;
    }

    public String val() {
        return val;
    }
    
}
