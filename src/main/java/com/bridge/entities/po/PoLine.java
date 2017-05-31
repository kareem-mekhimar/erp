/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.po;

import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.PoLineType;
import com.bridge.enums.MatchBasis;
import java.io.Serializable;
import java.lang.Integer;
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
import javax.persistence.OneToOne;
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
 * @author Bridge
 */
@Entity
@Table(name = "PO_LINES")
@NamedQueries({
    @NamedQuery(name = "PoLines.findAll", query = "SELECT p FROM PoLine p")})
public class PoLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "poLine")
    @TableGenerator(name = "poLine",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PoLine",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PO_LINE_ID")
    private Integer poLineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LINE_TYPE_ID")
    private PoLineType poLineType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NUM")
    private Integer lineNum;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
 
    @OneToOne
    @JoinColumn(name = "ITEM_ID",referencedColumnName = "INVENTORY_ITEM_ID")
    private SystemItem systemItem;
    
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;
    @Size(max = 240)
    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;
    @Size(max = 25)
    @Column(name = "UNIT_MEAS_LOOKUP_CODE")
    private String unitMeasLookupCode;
    
    @Column(name = "QUANTITY_RECEIVED")
    private BigDecimal quantityReceived = BigDecimal.ZERO;
    
    @Column(name = "COMMITTED_AMOUNT")
    private Integer committedAmount;
    @Column(name = "LIST_PRICE_PER_UNIT")
    private BigDecimal listPricePerUnit;

    @Column(name = "QUANTITY_ORDERED")
    private BigDecimal quantityOrdered;
    
    @Size(max = 480)
    @Column(name = "NOTE_TO_VENDOR")
    private String noteToVendor;
    @Column(name = "FROM_HEADER_ID")
    private Integer fromHeaderId;
    @Column(name = "FROM_LINE_ID")
    private Integer fromLineId;
    @Column(name = "MIN_ORDER_QUANTITY")
    private BigDecimal minOrderQuantity;
    @Column(name = "MAX_ORDER_QUANTITY")
    private BigDecimal maxOrderQuantity;
    @Column(name = "QTY_RCV_TOLERANCE")
    private BigDecimal qtyRcvTolerance;
    @Size(max = 1)
    @Column(name = "CLOSED_FLAG")
    private String closedFlag = "N";
    @Size(max = 1)
    @Column(name = "USER_HOLD_FLAG")
    private String userHoldFlag;
    @Size(max = 1)
    @Column(name = "CANCEL_FLAG")
    private String cancelFlag;
    @Column(name = "CANCELLED_BY")
    private Integer cancelledBy;
    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;
    @Size(max = 240)
    @Column(name = "CANCEL_REASON")
    private String cancelReason;
    @Size(max = 25)
    @Column(name = "VENDOR_PRODUCT_NUM")
    private String vendorProductNum;
    @Size(max = 25)
    @Column(name = "CONTRACT_NUM")
    private String contractNum;
   
    @Column(name = "TAXABLE_FLAG_ID")
    private boolean taxable;
    
    @Size(max = 30)
    @Column(name = "TAX_NAME")
    private String taxName;
    @Column(name = "MIN_RELEASE_AMOUNT")
    private BigDecimal minReleaseAmount;
    @Size(max = 25)
    @Column(name = "PRICE_TYPE_LOOKUP_CODE")
    private String priceTypeLookupCode;
    @Size(max = 25)
    @Column(name = "CLOSED_CODE")
    private String closedCode;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Size(max = 240)
    @Column(name = "CLOSED_REASON")
    private String closedReason;
    @Column(name = "CLOSED_BY")
    private Integer closedBy;
    @Size(max = 25)
    @Column(name = "TRANSACTION_REASON_CODE")
    private String transactionReasonCode;
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Size(max = 25)
    @Column(name = "BASE_UOM")
    private String baseUom;
    @Column(name = "BASE_QTY")
    private BigDecimal baseQty;
    @Size(max = 25)
    @Column(name = "SECONDARY_UOM")
    private String secondaryUom;
    @Column(name = "SECONDARY_QTY")
    private BigDecimal secondaryQty;
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "TAX_CODE_ID")
    private Integer taxCodeId;
    @Column(name = "SECONDARY_QUANTITY")
    private BigDecimal secondaryQuantity;
    @Size(max = 25)
    @Column(name = "SECONDARY_UNIT_OF_MEASURE")
    private String secondaryUnitOfMeasure;
    @Size(max = 150)
    @Column(name = "SUPPLIER_REF_NUMBER")
    private String supplierRefNumber;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
   
    @Column(name = "MATCHING_BASIS")
    private MatchBasis matchingBasis;
    
    @Size(max = 255)
    @Column(name = "CATALOG_NAME")
    private String catalogName;
    
    @Size(max = 240)
    @Column(name = "LINE_NUM_DISPLAY")
    private String lineNumDisplay;
    
    @Column(name = "REVISION_NUM")
    private Integer revisionNum;
    
    @Column(name = "CLOSED_CODE_ID")
    private Integer closedCodeId;
     
    @Column(name = "PO_LINE_STATUS")
    private boolean poLineStatus;
    
    @Column(name = "QUANTITY_INVOICED")
    private BigDecimal invoicedQuantity = BigDecimal.ZERO ;
    
    @OneToOne
    @JoinColumn(name = "PRIMARY_UOM_ID",referencedColumnName = "UOM_ID")
    private UomLine primaryUom ;

    @JoinColumn(name = "PO_HEADER_ID", referencedColumnName = "PO_HEADER_ID")
    @ManyToOne(optional = false)
    private PoHeader poHeader;
    
    @Column(name="ITEM_NAME")
    private String itemName;

    /////////////////////////////////////////////////////////////////////////
    
    @Transient
    private BigDecimal txQuantity ;
    
    @Transient
    private Date txDate ;
    
    @Transient
    private MainInventory tagertMainInv ;
    
    @Transient
    private SecondaryInventory targetSecInv ;
    
    public PoLine() {
    }

    public PoLine(Integer poLineId) {
        this.poLineId = poLineId;
    }

    public PoLine(Integer poLineId, Date lastUpdateDate, Integer lastUpdatedBy, Integer lineNum) {
        this.poLineId = poLineId;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lineNum = lineNum;
    }

    public Integer getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
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

    public PoLineType getPoLineType() {
        return poLineType;
    }

    public void setPoLineType(PoLineType poLineType) {
        this.poLineType = poLineType;
    }
    
    
    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public SystemItem getSystemItem() {
        return systemItem;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }

    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUnitMeasLookupCode() {
        return unitMeasLookupCode;
    }

    public void setUnitMeasLookupCode(String unitMeasLookupCode) {
        this.unitMeasLookupCode = unitMeasLookupCode;
    }

    public BigDecimal getQuantityReceived() {
        return quantityReceived;
    }

    public BigDecimal getInvoicedQuantity() {
        return invoicedQuantity;
    }

    public void setInvoicedQuantity(BigDecimal invoicedQuantity) {
        this.invoicedQuantity = invoicedQuantity;
    }

    
    public void setQuantityReceived(BigDecimal quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public MainInventory getTagertMainInv() {
        return tagertMainInv;
    }

    public SecondaryInventory getTargetSecInv() {
        return targetSecInv;
    }

    public void setTargetSecInv(SecondaryInventory targetSecInv) {
        this.targetSecInv = targetSecInv;
    }

    public void setTagertMainInv(MainInventory tagertMainInv) {
        this.tagertMainInv = tagertMainInv;
    }

    
    public Integer getCommittedAmount() {
        return committedAmount;
    }

    public void setCommittedAmount(Integer committedAmount) {
        this.committedAmount = committedAmount;
    }

    public BigDecimal getListPricePerUnit() {
        return listPricePerUnit;
    }

    public void setListPricePerUnit(BigDecimal listPricePerUnit) {
        this.listPricePerUnit = listPricePerUnit;
    }

    public BigDecimal getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(BigDecimal quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
    
    
    public String getNoteToVendor() {
        return noteToVendor;
    }

    public void setNoteToVendor(String noteToVendor) {
        this.noteToVendor = noteToVendor;
    }

    public Integer getFromHeaderId() {
        return fromHeaderId;
    }

    public void setFromHeaderId(Integer fromHeaderId) {
        this.fromHeaderId = fromHeaderId;
    }

    public Integer getFromLineId() {
        return fromLineId;
    }

    public void setFromLineId(Integer fromLineId) {
        this.fromLineId = fromLineId;
    }

    public BigDecimal getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(BigDecimal minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public BigDecimal getMaxOrderQuantity() {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(BigDecimal maxOrderQuantity) {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public BigDecimal getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(BigDecimal qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public String getClosedFlag() {
        return closedFlag;
    }

    public void setClosedFlag(String closedFlag) {
        this.closedFlag = closedFlag;
    }

    public String getUserHoldFlag() {
        return userHoldFlag;
    }

    public void setUserHoldFlag(String userHoldFlag) {
        this.userHoldFlag = userHoldFlag;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public Integer getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(Integer cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getVendorProductNum() {
        return vendorProductNum;
    }

    public void setVendorProductNum(String vendorProductNum) {
        this.vendorProductNum = vendorProductNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    
    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public BigDecimal getMinReleaseAmount() {
        return minReleaseAmount;
    }

    public void setMinReleaseAmount(BigDecimal minReleaseAmount) {
        this.minReleaseAmount = minReleaseAmount;
    }

    public String getPriceTypeLookupCode() {
        return priceTypeLookupCode;
    }

    public void setPriceTypeLookupCode(String priceTypeLookupCode) {
        this.priceTypeLookupCode = priceTypeLookupCode;
    }

    public String getClosedCode() {
        return closedCode;
    }

    public void setClosedCode(String closedCode) {
        this.closedCode = closedCode;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getClosedReason() {
        return closedReason;
    }

    public void setClosedReason(String closedReason) {
        this.closedReason = closedReason;
    }

    public Integer getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Integer closedBy) {
        this.closedBy = closedBy;
    }

    public String getTransactionReasonCode() {
        return transactionReasonCode;
    }

    public void setTransactionReasonCode(String transactionReasonCode) {
        this.transactionReasonCode = transactionReasonCode;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getBaseUom() {
        return baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public BigDecimal getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(BigDecimal baseQty) {
        this.baseQty = baseQty;
    }

    public String getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public BigDecimal getSecondaryQty() {
        return secondaryQty;
    }

    public void setSecondaryQty(BigDecimal secondaryQty) {
        this.secondaryQty = secondaryQty;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getTaxCodeId() {
        return taxCodeId;
    }

    public void setTaxCodeId(Integer taxCodeId) {
        this.taxCodeId = taxCodeId;
    }

    public BigDecimal getSecondaryQuantity() {
        return secondaryQuantity;
    }

    public void setSecondaryQuantity(BigDecimal secondaryQuantity) {
        this.secondaryQuantity = secondaryQuantity;
    }

    public String getSecondaryUnitOfMeasure() {
        return secondaryUnitOfMeasure;
    }

    public void setSecondaryUnitOfMeasure(String secondaryUnitOfMeasure) {
        this.secondaryUnitOfMeasure = secondaryUnitOfMeasure;
    }

    public String getSupplierRefNumber() {
        return supplierRefNumber;
    }

    public void setSupplierRefNumber(String supplierRefNumber) {
        this.supplierRefNumber = supplierRefNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
    public MatchBasis getMatchingBasis() {
        return matchingBasis;
    }

    public Date getTxDate() {
        
        if(txDate == null)
            
            txDate = new Date() ;
        
        return txDate;
    }

    public BigDecimal getTxQuantity() {
        return txQuantity;
    }

    public void setTxQuantity(BigDecimal txQuantity) {
        this.txQuantity = txQuantity;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    
    public void setMatchingBasis(MatchBasis matchingBasis) {
        this.matchingBasis = matchingBasis;
    }

    
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getLineNumDisplay() {
        return lineNumDisplay;
    }

    public void setLineNumDisplay(String lineNumDisplay) {
        this.lineNumDisplay = lineNumDisplay;
    }

    public Integer getRevisionNum() {
        return revisionNum;
    }

    public void setRevisionNum(Integer revisionNum) {
        this.revisionNum = revisionNum;
    }

    public Integer getClosedCodeId() {
        return closedCodeId;
    }

    public void setClosedCodeId(Integer closedCodeId) {
        this.closedCodeId = closedCodeId;
    }


    public PoHeader getPoHeader() {
        return poHeader;
    }

    public void setPoHeader(PoHeader poHeader) {
        this.poHeader = poHeader;
    }

    public UomLine getPrimaryUom() {
        return primaryUom;
    }

    
    public void setPrimaryUom(UomLine primaryUom) {
        this.primaryUom = primaryUom;
    }

    public boolean getPoLineStatus() {
        return poLineStatus;
    }

    public void setPoLineStatus(boolean poLineStatus) {
        this.poLineStatus = poLineStatus;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poLineId != null ? poLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoLine)) {
            return false;
        }
        PoLine other = (PoLine) object;
        if ((this.poLineId == null && other.poLineId != null) || (this.poLineId != null && !this.poLineId.equals(other.poLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.PoLines[ poLineId=" + poLineId + " ]";
    }
    
        
    @PrePersist
    private void onPersist()
    {
        lastUpdateDate = new Date() ;
        
        lastUpdatedBy = 1 ;
        
        createdBy = 1 ;
        
        creationDate = new Date() ;
    }
}
