/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.po;

import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.enums.PoLineType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "PO_LINE_LOCATIONS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "PoLineLocation.findAll", query = "SELECT p FROM PoLineLocation p")})
public class PoLineLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
       @GeneratedValue(strategy = GenerationType.TABLE,generator = "poLineLocation")
    @TableGenerator(name = "poLineLocation",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PoLineLocation",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_LOCATION_ID")
    private Integer lineLocationId;
    @Column(name = "PO_HEADER_ID")
    private Integer poHeaderId;
    @ManyToOne
    @JoinColumn(name = "PO_LINE_ID")
    private PoLine poLine;
    @Column(name = "ITEM_TYPE_ID")
    private PoLineType itemType;
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private PhysicalLocation location;
    @Column(name = "RECEIVING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivingDate;
    @Column(name = "RECEIVED_BY")
    private Integer receivedBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "UNITS_RECEIVED")
    private BigDecimal unitsReceived;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @Column(name="ORGANIZATION_ID")
    private Integer organizationId;
    @Column(name="SYSTEM_ITEM_ID")
    private Integer systemItemId;
    
    
    public PoLineLocation() {
    }
    
    public PoLineLocation(PoLine line) {
        
        this.poLine = line ;
        this.organizationId=line.getPoHeader().getOrg().getOrgUnitId();
        
    }

    public Integer getSystemItemId() {
        return systemItemId;
    }

    public void setSystemItemId(Integer systemItemId) {
        this.systemItemId = systemItemId;
    }

    
    
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    
   

    public PoLineLocation(Integer lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    public Integer getLineLocationId() {
        return lineLocationId;
    }

    public void setLineLocationId(Integer lineLocationId) {
        this.lineLocationId = lineLocationId;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public PoLine getPoLine() {
        return poLine;
    }

    public void setPoLine(PoLine poLine) {
        this.poLine = poLine;
    }

    public PoLineType getItemType() {
        return itemType;
    }

    public void setItemType(PoLineType itemType) {
        this.itemType = itemType;
    }

    public PhysicalLocation getLocation() {
        return location;
    }

    public void setLocation(PhysicalLocation location) {
        this.location = location;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Integer getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(Integer receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getUnitsReceived() {
        return unitsReceived;
    }

    public void setUnitsReceived(BigDecimal unitsReceived) {
        this.unitsReceived = unitsReceived;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineLocationId != null ? lineLocationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoLineLocation)) {
            return false;
        }
        PoLineLocation other = (PoLineLocation) object;
        if ((this.lineLocationId == null && other.lineLocationId != null) || (this.lineLocationId != null && !this.lineLocationId.equals(other.lineLocationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.PoLineLocations[ lineLocationId=" + lineLocationId + " ]";
    }
    
}
