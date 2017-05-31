/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "ONHAND_QTY")
@NamedQueries({
    @NamedQuery(name = "OnhandQty.findAll", query = "SELECT o FROM OnhandQty o")})
public class OnhandQty implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OnHand")
    @TableGenerator(name = "OnHand", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
            pkColumnName = "SEQUENCE_NAME", pkColumnValue = "OnHand", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private int id;
        
    @Column(name = "ITEM_ID")
    private int itemId;
    
    @Column(name = "SUB_INV_ID")
    private int subInvId;
   
    @Column(name = "TX_DATE")
    private LocalDate txDate;
   
    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Version
    private long version ;
    
    public OnhandQty() {
    }

    public OnhandQty(int itemId, int subInvId, LocalDate txDate, BigDecimal quantity) {
      
        this.itemId = itemId;
      
        this.subInvId = subInvId;
       
        this.txDate = txDate;
      
        this.quantity = quantity;
      
    }
    
    
    public int getItemId() {
        return itemId;
    }

    public int getSubInvId() {
        return subInvId;
    }

    public LocalDate getTxDate() {
        return txDate;
    }

    public void setTxDate(LocalDate txDate) {
        this.txDate = txDate;
    }

    
    public int getId() {
        return id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setSubInvId(int subInvId) {
        this.subInvId = subInvId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OnhandQty other = (OnhandQty) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
   
    @Override
    public String toString() {
        return "com.bridge.entities.inventory.OnhandQty[ id=" + id + " ]";
    }
    
}
