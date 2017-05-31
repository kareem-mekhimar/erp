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
public enum GLSetupSteps {
    
    /*
    SETUP_STEP_CODE
    SETUP_STEP_SHORT_DESC
    SETUP_STEP_FULL_DESC
    */    
    
    LEDGER_OPTIONS("Ledger Options","update the primary ledger options."),
    ALC_SETUP("Reporting Currencies","Create reporting currencies and update currency conversion and journal processing options."),
    //BSV_ASSIGNMENTS("Balancing Segment Value Assignments","Assign balancing segment values to the ledger."),    
    //SLAM_SETUP("Subledger Accounting Options","Define rules to generate your accounting entries from subledger transactions."),    
    //OU_SETUP("Operating Units","Define and maintain operating units for your primary ledger."),        
    //ACCTG_SEQUENCE("Sequencing","Define and maintain accounting and reporting sequencing options for ledgers and reporting currencies."),
    BASIC_ACCOUNTS("Basic Accounts","define primary GL basic accounts."),
    CALENDAR("Calendar","define main calendar")  
    ;
    
    private final String val;
    private final String desc;

    
    GLSetupSteps(String v, String d) {
        val = v;
        desc = d;
    }

    
    public String getVal() {
        return val;
    }
    
    public String getDesc() {
        return desc;
    }
    
}
