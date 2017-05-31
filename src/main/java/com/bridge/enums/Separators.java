/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.enums;

/**
 *
 * @author AIA
 */
public enum Separators {
    
    DOT("."),
    COMMA(","),
    COLON(":"),
    SEMI_COLON(";"),
    SINGLE_QUOTE("'"),
    PLUS("+"),
    MINUS("-"),
    UNDER_SCORE("_");
    
    private String value;
    private String label;
    
    Separators(String value){
        this.value=value;
        this.label=this.name()+" ( "+value+" )";
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
    
    
    
    
    
    
}
