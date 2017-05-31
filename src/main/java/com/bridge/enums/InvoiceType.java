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
public enum InvoiceType {
        
    AWT("Withholding tax invoice"),
    CREDIT("Credit memo"),
    DEBIT("Debit memo"),
    EXPENSE("REPORT Employee expense report"),
    INTEREST("Interest invoice"),
    MIXED(" Mixed type invoice"),
    PREPAYMENT(" Prepayments and advances"),
    QUICKDEFAULT(" PO default standard invoice"),
    QUICKMATCH(" QuickMatch standard invoice"),
    STANDARD(" Standard invoice");
    
    private final String val;

    InvoiceType(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }
}
