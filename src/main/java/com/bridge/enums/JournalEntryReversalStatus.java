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
public enum JournalEntryReversalStatus {
    NOTREVERSED("Not (yet) reversed"),
    REVERSED("Reversed");

    private final String val;

    JournalEntryReversalStatus(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }
}
