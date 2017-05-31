/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.shipment;

import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.enums.ShipmentHeaderStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "MTL_SHIPMENT_HEADER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentHeader.findAll", query = "SELECT s FROM ShipmentHeader s")})
public class ShipmentHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ShipHead")
    @TableGenerator(name = "ShipHead",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "ShipHead",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEADER_ID")
    private Integer headerId;
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    @Column(name = "MAIN_INV_ID")
    private Integer mainInvId;
    @Column(name = "SECONDARY_INV_ID")
    private Integer secondaryInvId;
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @Column(name = "CLIENT_SITE_ID")
    private Integer clientSiteId;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ShipmentHeaderStatus status;
    
    @Column(name = "MOVE_ORDER_ID")
    private Integer moveOrderId;
   
    @Column(name="SO_HEADER_ID")
    private Integer salesOrderId;
    
    @Column(name = "SHIPMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;
    @Column(name = "SHIPPED_BY")
    private Integer shippedBy;
    @Column(name="FROM_SUB_INVENTORY_ID")
    private Integer fromSubInventoryId;
    @Column(name="TO_ACCOUNT_ID")
    private  Integer toAccountId;
    
    @OneToMany(mappedBy = "header",cascade = CascadeType.ALL)
    private List<ShipmentLine> shipmentLines;

    public ShipmentHeader() {
    }

    public ShipmentHeader(Integer headerId) {
        this.headerId = headerId;
    }
    
    public ShipmentHeader(MtlTxnRequestHeader header) {
        
            this.clientId=header.getSalesOrder().getSoldToCustomers().getClientId();
            this.clientSiteId=header.getSalesOrder().getShipToSite().getSiteId();
            this.operatingUnitId=header.getOrganization().getOrgUnitId();
            this.mainInvId=header.getFromMainInventoryId().getOrganizationId();
            this.secondaryInvId=header.getFromSubinventoryId().getSecondaryInventoryId();
            this.moveOrderId=header.getHeaderId();
            this.salesOrderId=header.getSalesOrder().getHeaderId();
            this.status=ShipmentHeaderStatus.NEW;
            this.shipmentDate=header.getDateRequired();
            this.fromSubInventoryId=header.getToSubinventoryId().getSecondaryInventoryId();
            this.toAccountId=header.getToAccountId().getCodeCombinationId();
            
    }
    

    public List<ShipmentLine> getShipmentLines() {
        return shipmentLines;
    }

    public void setShipmentLines(List<ShipmentLine> shipmentLines) {
        this.shipmentLines = shipmentLines;
    }
    
    

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public Integer getMainInvId() {
        return mainInvId;
    }

    public void setMainInvId(Integer mainInvId) {
        this.mainInvId = mainInvId;
    }

    public Integer getSecondaryInvId() {
        return secondaryInvId;
    }

    public void setSecondaryInvId(Integer secondaryInvId) {
        this.secondaryInvId = secondaryInvId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getClientSiteId() {
        return clientSiteId;
    }

    public void setClientSiteId(Integer clientSiteId) {
        this.clientSiteId = clientSiteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShipmentHeaderStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentHeaderStatus status) {
        this.status = status;
    }


    public Integer getMoveOrderId() {
        return moveOrderId;
    }

    public void setMoveOrderId(Integer moveOrderId) {
        this.moveOrderId = moveOrderId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getShippedBy() {
        return shippedBy;
    }

    public void setShippedBy(Integer shippedBy) {
        this.shippedBy = shippedBy;
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Integer getFromSubInventoryId() {
        return fromSubInventoryId;
    }

    public void setFromSubInventoryId(Integer fromSubInventoryId) {
        this.fromSubInventoryId = fromSubInventoryId;
    }

 

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (headerId != null ? headerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShipmentHeader)) {
            return false;
        }
        ShipmentHeader other = (ShipmentHeader) object;
        if ((this.headerId == null && other.headerId != null) || (this.headerId != null && !this.headerId.equals(other.headerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.moveorder.ShipmentHeader[ headerId=" + headerId + " ]";
    }
    
}
