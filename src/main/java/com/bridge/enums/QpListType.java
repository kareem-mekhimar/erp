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
public enum QpListType {
    
    CHARGES("Freight and Special Charges List"),
    DEL("Deal"),
    DLT("Discount List"),
    PML("Price Modifier List"),
    PRL("Price List"),
    PRO("Promotion"),
    SLT("Surcharge List");

    private final String val;

    QpListType(String v) {
        val = v;
    }

    public String val() {
        return val;
   }
    

}
