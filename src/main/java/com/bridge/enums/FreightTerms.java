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
public enum FreightTerms {
    
    NOSELECTION("Choose Freight Terms"),
    PrepayAndAdd("Prepay & Add ");

    private final String val;

    FreightTerms(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }
    
}
