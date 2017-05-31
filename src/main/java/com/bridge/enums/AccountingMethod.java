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
public enum AccountingMethod {
    
    ENCUMBRANCE_ACCRUAL("Encumbrance Accrual"),
    ENCUMBRANCE_CASH("Encumbrance Cash"),    
    STANDARD_ACCRUAL("Standard Accrual"),
    STANDARD_CASH("Standard Cash")
    ;
    
    private String value;
    
    AccountingMethod(String value){
        
        this.value = value ;
        
    }

    public String getValue() {
        return value;
    }
    
}
