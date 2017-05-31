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
public enum ListLineTypeCode {
    
    PRICELISTLINE("Price List Line");

    private final String val;

    ListLineTypeCode(String v) {
        val = v;
    }

    public String val() {
        return val;
   }
    
    
}
