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
public enum PriceListApplicationMethod {
    

    UNITPRICE("Unit Price");

    private final String val;

    PriceListApplicationMethod(String v) {
        val = v;
    }

    public String val() {
        return val;
   }
    
    
}
