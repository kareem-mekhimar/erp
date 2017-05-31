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
public enum SegmentQualifier {
    
    NONE("None","None"),
    BALANCING_SEGMENT("Balancing Segment","Balancing Segment"),
    COST_CENTER_SEGMENT("Cost Center Segment","Cost Center Segment"),
    INTERCOMPANY_SEGMENT("Intercompany Segment","Intercompany Segment"),
    MANAGEMENT_SEGMENT("Management Segment","Management Segment"),
    NATURAL_ACCOUNT_SEGMENT("Natural Account Segment","Natural Account Segment"),
    SECONDARY_TRACKING_SEGMENT("Secondary Tracking Segment","Secondary Tracking Segment");

    private final String val;
    private final String desc;

    SegmentQualifier(String v, String d) {
        val = v;
        desc = d;
    }

    public String val() {
        return val;
    }
    
    public String desc() {
        return desc;
    }
    
}
