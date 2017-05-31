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
public enum RecException {
    NONE(1),
    REJECT(2),
    WARNING(3);
    private Integer value;
    RecException(Integer value ){
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }
}
