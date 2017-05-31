/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.personsales;

import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.ReservationLineType;
import com.bridge.enums.ReservationOrderType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "OE_RESERVATION_LINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservationLines.findAll", query = "SELECT i FROM ReservationLines i")})
public class ReservationLines implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ReservationLines")
    @TableGenerator(name = "ReservationLines",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "ReservationLines",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID")
    private ReservationOrder reservationId;
    @Column(name = "PRIMARY_RESERVED_QTY")
    private BigDecimal reservedQty;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_BY")
    private Integer lastUpdateBy;
    @Column(name = "RESERVED_IN_INV")
    private Integer reservedInInv;
    @Column(name = "RESERVED_WITH_ENTITY")
    private Integer reservedWithEntity;
    @OneToOne
    @JoinColumn(name = "SUB_INV_ID")
    private SecondaryInventory subInvId;
    @OneToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID")
    private SystemItem inventoryItemId;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ReservationLineType status;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "LINE_NUMBER")
    private Integer lineNumber;
    @Column(name = "PROCESSED_QTY")
    private BigDecimal processedQty=BigDecimal.ZERO;
    @Column(name = "RETURNED_QTY")
    private BigDecimal returnedQty=BigDecimal.ZERO;
    
    @Transient
    private BigDecimal itemBalance=BigDecimal.ZERO;
    

    public ReservationLines() {
    }

    public ReservationLines(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationOrder getReservationId() {
        return reservationId;
    }

    public void setReservationId(ReservationOrder reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getReservedQty() {
        return reservedQty;
    }

    public void setReservedQty(BigDecimal reservedQty) {
        this.reservedQty = reservedQty;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Integer lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Integer getReservedInInv() {
        return reservedInInv;
    }

    public void setReservedInInv(Integer reservedInInv) {
        this.reservedInInv = reservedInInv;
    }

    public Integer getReservedWithEntity() {
        return reservedWithEntity;
    }

    public void setReservedWithEntity(Integer reservedWithEntity) {
        this.reservedWithEntity = reservedWithEntity;
    }

    public SecondaryInventory getSubInvId() {
        return subInvId;
    }

    public void setSubInvId(SecondaryInventory subInvId) {
        this.subInvId = subInvId;
    }

    public SystemItem getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(SystemItem inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public ReservationLineType getStatus() {
        return status;
    }

    public void setStatus(ReservationLineType status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getProcessedQty() {
        return processedQty;
    }

    public void setProcessedQty(BigDecimal processedQty) {
        this.processedQty = processedQty;
    }

    public BigDecimal getReturnedQty() {
        return returnedQty;
    }

    public void setReturnedQty(BigDecimal returnedQty) {
        this.returnedQty = returnedQty;
    }

    public BigDecimal getItemBalance() {
        return itemBalance;
    }

    public void setItemBalance(BigDecimal itemBalance) {
        this.itemBalance = itemBalance;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.subInvId);
        hash = 23 * hash + Objects.hashCode(this.inventoryItemId);
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
        final ReservationLines other = (ReservationLines) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.subInvId, other.subInvId)) {
            return false;
        }
        if (!Objects.equals(this.inventoryItemId, other.inventoryItemId)) {
            return false;
        }
        return true;
    }

    
  

    @Override
    public String toString() {
        return "com.bridge.entities.personsales.ItemReserved[ id=" + id + " ]";
    }
    
}
