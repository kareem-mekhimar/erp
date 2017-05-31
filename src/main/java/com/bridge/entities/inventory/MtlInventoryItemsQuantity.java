/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "MTL_INVENTORY_ITEMS_QUANTITY")
@NamedQueries({
    @NamedQuery(name = "MtlInventoryItemsQuantity.findAll", query = "SELECT m FROM MtlInventoryItemsQuantity m")})
public class MtlInventoryItemsQuantity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Column(name = "BUSINESS_GROUP_ID")
    private Integer businessGroupId;
    
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    
    @Id
    @Column(name = "MAIN_INVENTORY_ID")
    private Integer mainInventoryId;
    
    @Id
    @Column(name = "SECONDARY_INVENTORY_ID")
    private Integer secondaryInventoryId;
           
    @Id
    @OneToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID",referencedColumnName = "INVENTORY_ITEM_ID")
    private SystemItem systemItem;
    
    @Column(name = "ITEM_QUANTITY")
    private BigDecimal itemQuantity;
    
    @Column(name = "RESERVED_QUANTITY")
    private BigDecimal reservedQuantity = BigDecimal.ZERO;
    
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDateTime ;
    
    @Transient
    private Date txDate = new Date();
    
    @Transient
    private BigDecimal txQuantity ;
    
    @Transient
    private BigDecimal qtyUntilDate ;
    
    public MtlInventoryItemsQuantity() {
        
    }

    public MtlInventoryItemsQuantity(Integer operatingUnitId, Integer mainInventoryId, Integer secondaryInventoryId, SystemItem systemItem, BigDecimal itemQuantity) {
        this.operatingUnitId = operatingUnitId;
        this.mainInventoryId = mainInventoryId;
        this.secondaryInventoryId = secondaryInventoryId;
        this.systemItem = systemItem;
        this.itemQuantity = itemQuantity;
    }

    
    public Integer getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(Integer businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public Integer getMainInventoryId() {
        return mainInventoryId;
    }

    public void setMainInventoryId(Integer mainInventoryId) {
        this.mainInventoryId = mainInventoryId;
    }

    public Integer getSecondaryInventoryId() {
        return secondaryInventoryId;
    }

    public void setSecondaryInventoryId(Integer secondaryInventoryId) {
        this.secondaryInventoryId = secondaryInventoryId;
    }

    public SystemItem getSystemItem() {
        return systemItem;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }

    public BigDecimal getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(BigDecimal itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public BigDecimal getTxQuantity() {
        return txQuantity;
    }

    public void setTxQuantity(BigDecimal txQuantity) {
        this.txQuantity = txQuantity;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public BigDecimal getQtyUntilDate() {
        return qtyUntilDate;
    }

    public void setQtyUntilDate(BigDecimal qtyUntilDate) {
        this.qtyUntilDate = qtyUntilDate;
    }

    public BigDecimal getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(BigDecimal reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    
    
    @PrePersist
    @PreUpdate
    public void onPreUpdateOrPersist()
    {
        this.lastUpdateDateTime = LocalDateTime.now();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.mainInventoryId);
        hash = 61 * hash + Objects.hashCode(this.secondaryInventoryId);
        hash = 61 * hash + Objects.hashCode(this.systemItem);
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
        final MtlInventoryItemsQuantity other = (MtlInventoryItemsQuantity) obj;
        if (!Objects.equals(this.mainInventoryId, other.mainInventoryId)) {
            return false;
        }
        if (!Objects.equals(this.secondaryInventoryId, other.secondaryInventoryId)) {
            return false;
        }
        if (!Objects.equals(this.systemItem, other.systemItem)) {
            return false;
        }
        return true;
    }
    
        
}
