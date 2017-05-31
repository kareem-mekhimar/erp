/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.enums;

/**
 *
 * @author Bridge
 */

public enum PaymentTerms {
    
    IMMEDIATE("Immediate",0) , THIRTY("30",30) , FOURTYFIVE("45",45),SIXTY("60",60) , FIRST_NEXT_MONTH("Next Month",0) ;
   
    private String label ;
    
    private int value ;

    private PaymentTerms(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
 
    
   
   
}
