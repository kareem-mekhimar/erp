/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.salesorder;

import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.personsales.ReservationLines;
import com.bridge.enums.SalesLineStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kareem
 */
@Entity
@Table(name = "OE_ORDER_LINES")
@NamedQueries({
    @NamedQuery(name = "OeOrderLine.findAll", query = "SELECT o FROM OeOrderLine o")})
public class OeOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "oeLine")
    @TableGenerator(name = "oeLine",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "OeLine",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_ID")
    private Integer lineId;
    
        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEADER_ID")
    private OeOrderHeader header;
    
        @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_TYPE_ID")
    private Integer lineTypeId = 1;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NUMBER")
    private Integer lineNumber;
    
        @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate=new Date();
    
    @Column(name = "PROMISE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promiseDate;
    
        
    @Column(name = "SCHEDULE_SHIP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleShipDate;
    
        @Size(max = 3)
    @Column(name = "ORDER_QUANTITY_UOM")
    private String orderQuantityUom;
    
        @Column(name = "SHIPPED_QUANTITY")
    private BigDecimal shippedQuantity = BigDecimal.ZERO;
   
    @Column(name = "ORDERED_QUANTITY")
    private BigDecimal orderedQuantity;
    
        @ManyToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID")
    private SystemItem inventoryItem;
        
            @Column(name = "PRICE_LIST_ID")
    private Integer priceListId;
        
       @Column(name = "PRICING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pricingDate;     
        
           @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPMENT_NUMBER")
    private Integer shipmentNumber = 1; 
        
         @Column(name = "UNIT_SELLING_PRICE")
    private BigDecimal unitSellingPrice;   
        
         @Column(name = "UNIT_LIST_PRICE")
    private BigDecimal unitListPrice=BigDecimal.ZERO;   
        
         @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    
         @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private Integer createdBy = 1;   
    
        @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate = new Date();
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy = 1;
    
        @Column(name = "INVOICED_QUANTITY")
    private BigDecimal invoicedQuantity = BigDecimal.ZERO;
        
        @Column(name="AMOUNT")
    private BigDecimal amount =BigDecimal.ZERO ;
    
        @Column(name = "RETURNED_QUANTITY")
    private BigDecimal returnedQuantity=BigDecimal.ZERO;
    
        @Column(name = "ITEM_RESERVATION_ID")
    private Integer itemReservationId;
    
            @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private SalesLineStatus status=SalesLineStatus.NEW;
    
        @Column(name = "DELIVERED_QUANTITY")
    private BigDecimal deliveredQuantity=BigDecimal.ZERO;
        
        @Column(name="PICKED_QUANTITY")
    private BigDecimal pickedQuantity=BigDecimal.ZERO;    
        
        
     
    @Transient
    private BigDecimal qtyToPick;
    
    @Transient
    private BigDecimal actualQtyInInventory =BigDecimal.ZERO;
    
    @Transient
    private Date txDate = new Date() ;
    
    @Transient
    private SecondaryInventory subInv;
    
    public OeOrderLine() {
    }

    public OeOrderLine(Integer lineId) {
        this.lineId = lineId;
    }
    
     public OeOrderLine(ReservationLines line) {
         
                this.itemReservationId=line.getId();
                this.unitListPrice=BigDecimal.ZERO;
                this.amount=BigDecimal.ZERO;
                this.subInv=line.getSubInvId();
                this.inventoryItem=line.getInventoryItemId();
                this.orderQuantityUom=line.getInventoryItemId().getPrimaryUomId().getUomCode();
                this.qtyToPick=line.getReservedQty().subtract(line.getProcessedQty());             
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

       public Integer getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeId(Integer lineTypeId) {
        this.lineTypeId = lineTypeId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(Date promiseDate) {
        this.promiseDate = promiseDate;
    }

    public Date getScheduleShipDate() {
        return scheduleShipDate;
    }

    public void setScheduleShipDate(Date scheduleShipDate) {
        this.scheduleShipDate = scheduleShipDate;
    }

    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    public BigDecimal getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(BigDecimal shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public BigDecimal getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
    
    public OeOrderHeader getHeader() {
        return header;
    }

    public void setHeader(OeOrderHeader header) {
        this.header = header;
    }

    public SystemItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(SystemItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public Date getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(Date pricingDate) {
        this.pricingDate = pricingDate;
    }

    public Integer getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(Integer shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public BigDecimal getUnitListPrice() {
        return unitListPrice;
    }

    public void setUnitListPrice(BigDecimal unitListPrice) {
        this.unitListPrice = unitListPrice;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public BigDecimal getInvoicedQuantity() {
        return invoicedQuantity;
    }

    public void setInvoicedQuantity(BigDecimal invoicedQuantity) {
        this.invoicedQuantity = invoicedQuantity;
    }


    
    public BigDecimal getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(BigDecimal returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public Integer getItemReservationId() {
        return itemReservationId;
    }

    public void setItemReservationId(Integer itemReservationId) {
        this.itemReservationId = itemReservationId;
    }

    public SalesLineStatus getStatus() {
        return status;
    }

    public void setStatus(SalesLineStatus status) {
        this.status = status;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
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
        if (!(object instanceof OeOrderLine)) {
            return false;
        }
        OeOrderLine other = (OeOrderLine) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.salesorder.OeOrderLine[ lineId=" + lineId + " ]";
    }

    public BigDecimal getActualQtyInInventory() {
        return actualQtyInInventory;
    }

    public BigDecimal getQtyToPick() {
        return qtyToPick;
    }

    public void setActualQtyInInventory(BigDecimal actualQtyInInventory) {
        this.actualQtyInInventory = actualQtyInInventory;
    }

    public void setQtyToPick(BigDecimal qtyToPick) {
        this.qtyToPick = qtyToPick;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }



    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SecondaryInventory getSubInv() {
        return subInv;
    }

    public void setSubInv(SecondaryInventory subInv) {
        this.subInv = subInv;
    }

    public BigDecimal getPickedQuantity() {
        return pickedQuantity;
    }

    public void setPickedQuantity(BigDecimal pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }
    
    
    
    
    @PrePersist
    private void prePersist()
    {
       createdBy = 1;
       
       lastUpdatedBy = 1 ;
       
       creationDate = new Date() ;
       
       lastUpdateDate = new Date() ;
    }
    
    
    
}
