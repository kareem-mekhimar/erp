/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

import java.math.BigDecimal;

/**
 *
 * @author Bridge
 */

public class ClientPrePaymentDTO {
    
    private String unitName ;
    
    private String name ;
    
    private String siteName ;
    
    private BigDecimal credit ;
    
    private BigDecimal debit ;

    private String currencyCode ;

    public ClientPrePaymentDTO(String unitName, String name, String siteName, BigDecimal credit, BigDecimal debit, String currencyCode) {
        this.unitName = unitName;
        this.name = name;
        this.siteName = siteName;
        this.credit = credit;
        this.debit = debit;
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
     
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    
    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public String getName() {
        return name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    
}
