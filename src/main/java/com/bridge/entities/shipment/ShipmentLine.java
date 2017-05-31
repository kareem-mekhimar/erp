/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.shipment;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.enums.ShipmentLineStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "MTL_SHIPMENT_LINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentLine.findAll", query = "SELECT s FROM ShipmentLine s")})
public class ShipmentLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ShipLine")
    @TableGenerator(name = "ShipLine", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ShipLine", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_ID")
    private Integer lineId;
    @ManyToOne
    @JoinColumn(name = "HEADER_ID")
    private ShipmentHeader header;
    @OneToOne
    @JoinColumn(name = "ITEM_ID")
    private SystemItem itemId;
    @Column(name = "SHIPPED_QUANTITY")
    private BigDecimal shippedQuantity = BigDecimal.ZERO;

    @Column(name = "DELIVERED_QUANTITY")
    private BigDecimal deliveredQuantity = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ShipmentLineStatus status = ShipmentLineStatus.NEW;

    @Column(name = "SO_LINE_ID")
    private Integer soLineId;

    @Column(name = "MOVE_ORDER_LINE_ID")
    private Integer moveOrderLineId;

    @Column(name = "REFUSED_QUANTITY")
    private BigDecimal refusedQuantity = BigDecimal.ZERO;

    @Column(name = "SHIPPED_DATE")
    private Date shippedDate;

    @Column(name = "DELIVERED_DATE")
    private Date deliveredDate;

    @Column(name="DEFAULT_INVOICE_DATE")
    private LocalDate invoiceDate;

    @Column(name = "INVOICED_FLAG")
    private boolean invoicedFlag;

    public ShipmentLine() {
    }

    public ShipmentLine(Integer lineId) {
        this.lineId = lineId;
    }

    public ShipmentLine(MtlTxnRequestLine line, ShipmentHeader header) {
        this.moveOrderLineId = line.getLineId();
        this.soLineId = line.getSalesLineId();
        this.itemId = line.getInventoryItem();
        this.shippedQuantity = line.getQuantityThatWillTransferInTx();
        this.shippedDate = line.getTxDateForMoTransfer();
        this.header = header;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public ShipmentHeader getHeader() {
        return header;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setHeader(ShipmentHeader header) {
        this.header = header;
    }

    public SystemItem getItemId() {
        return itemId;
    }

    public void setItemId(SystemItem itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(BigDecimal shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public Integer getMoveOrderLineId() {
        return moveOrderLineId;
    }

    public void setMoveOrderLineId(Integer moveOrderLineId) {
        this.moveOrderLineId = moveOrderLineId;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public boolean isInvoicedFlag() {
        return invoicedFlag;
    }

    public void setInvoicedFlag(boolean invoicedFlag) {
        this.invoicedFlag = invoicedFlag;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public ShipmentLineStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentLineStatus status) {
        this.status = status;
    }

    public Integer getSoLineId() {
        return soLineId;
    }

    public void setSoLineId(Integer soLineId) {
        this.soLineId = soLineId;
    }

    public BigDecimal getRefusedQuantity() {
        return refusedQuantity;
    }

    public void setRefusedQuantity(BigDecimal refusedQuantity) {
        this.refusedQuantity = refusedQuantity;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineId != null ? lineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShipmentLine)) {
            return false;
        }
        ShipmentLine other = (ShipmentLine) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.moveorder.ShipmentLine[ lineId=" + lineId + " ]";
    }

}
