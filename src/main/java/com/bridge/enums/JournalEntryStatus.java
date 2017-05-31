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
public enum JournalEntryStatus {
    
    UNPOSTED("Unposted"),
    POSTED("Posted"),
    INVALIDCURRENCY("Invalid currency code"),
    INVALIDSOURCE("Invalid source"),
    INVALIDCATEGORY("Invalid category"),
    INVALIDSETOFBOOKS("Invalid set of books");
    
    
    private final String val;

    JournalEntryStatus(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }
    
}
