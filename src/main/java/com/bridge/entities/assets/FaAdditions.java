/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.assets;

import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.FaAdditionType;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "FA_ADDITIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaAdditions.findAll", query = "SELECT f FROM FaAdditions f")})
public class FaAdditions implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "FaAdditions")
    @TableGenerator(name = "FaAdditions",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "FaAdditions",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PO_HEADER_ID")
    private Integer poHeaderId;
    @OneToOne
    @JoinColumn(name = "PO_LINE_ID")
    private PoLine poLine;
    @Enumerated(EnumType.STRING)
    @Column(name = "ITEM_TYPE")
    private FaAdditionType itemType;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @OneToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private OrganizationUnit operatingUnit;
    @Column(name = "MAIN_INVENTORY_ID")
    private Integer mainInventoryId;
    @Column(name = "SUBINVENTORY_ID")
    private Integer subinventoryId;
    @Column(name = "RECEIVED_QUANTITY")
    private BigDecimal receivedQuantity;
    @Column(name = "LOCATED_QUANTITY")
    private BigDecimal locatedQuantity=BigDecimal.ZERO;
    @Column(name = "AVAILABLE_QUANTITY")
    private BigDecimal availableQuantity=BigDecimal.ZERO;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @Column(name = "RECEIVING_DATE")
    @Temporal(TemporalType.DATE)
    private Date receivingDate=new Date();
    @Column(name = "RECEIVED_BY")
    private Integer receivedBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @OneToOne
    @JoinColumn(name="LOCATION_ID")
    private PhysicalLocation location;
    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    

    public FaAdditions() {
    }

    public FaAdditions(Integer id) {
        this.id = id;
    }
    
    public FaAdditions(PoLine line) {
        this.poLine= line;
        this.operatingUnit=line.getPoHeader().getOrg();
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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



    public FaAdditionType getItemType() {
        return itemType;
    }

    public void setItemType(FaAdditionType itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public Integer getMainInventoryId() {
        return mainInventoryId;
    }

    public void setMainInventoryId(Integer mainInventoryId) {
        this.mainInventoryId = mainInventoryId;
    }

    public Integer getSubinventoryId() {
        return subinventoryId;
    }

    public void setSubinventoryId(Integer subinventoryId) {
        this.subinventoryId = subinventoryId;
    }

    public BigDecimal getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(BigDecimal receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
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

    public BigDecimal getLocatedQuantity() {
        return locatedQuantity;
    }

    public void setLocatedQuantity(BigDecimal locatedQuantity) {
        this.locatedQuantity = locatedQuantity;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public PhysicalLocation getLocation() {
        return location;
    }

    public void setLocation(PhysicalLocation location) {
        this.location = location;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaAdditions)) {
            return false;
        }
        FaAdditions other = (FaAdditions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.assets.FaReceive[ id=" + id + " ]";
    }
    
}
