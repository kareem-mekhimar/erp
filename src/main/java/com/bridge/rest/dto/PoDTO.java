/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bridge.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bridge
 */
@XmlRootElement(name = "Po")
@XmlAccessorType(XmlAccessType.FIELD)
public class PoDTO implements Serializable {

    private int no;

    private String clientName;

    private String siteName;

    private BigDecimal totalAmount;

    private String currencyCode ;
    
    public PoDTO() {
    }

    public PoDTO(int no, String clientName, String siteName, BigDecimal totalAmount,String currencyCode) {
        this.no = no;
        this.clientName = clientName;
        this.siteName = siteName;
        this.totalAmount = totalAmount;
        this.currencyCode = currencyCode ;
    }

    public String getClientName() {
        return clientName;
    }

    public int getNo() {
        return no;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    
    public String getSiteName() {
        return siteName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

}
