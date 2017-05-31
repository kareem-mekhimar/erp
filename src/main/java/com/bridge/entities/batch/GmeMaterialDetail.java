/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.batch;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.FormulaLineType;
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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GME_MATERIAL_DETAILS")
@NamedQueries({
    @NamedQuery(name = "GmeMaterialDetail.findAll", query = "SELECT g FROM GmeMaterialDetail g")})
public class GmeMaterialDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "gen")
    @TableGenerator(name = "gen",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "BatchLine",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATERIAL_DETAIL_ID")
    private Integer materialDetailId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NO")
    private int lineNo;
    @Column(name = "ITEM_ID")
    private Integer itemId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_TYPE")
    private FormulaLineType lineType;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAN_QTY")
    private BigDecimal planQty ;
    
    @Size(max = 4)
    @Column(name = "ITEM_UM")
    private String itemUm;
    @Size(max = 4)
    @Column(name = "ITEM_UM2")
    private String itemUm2;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTUAL_QTY")
    private BigDecimal actualQty = BigDecimal.ZERO;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELEASE_TYPE")
    private int releaseType;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCRAP_FACTOR")
    private Integer scrapFactor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCALE_TYPE")
    private int scaleType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHANTOM_TYPE")
    private int phantomType;
    @Column(name = "COST_ALLOC")
    private Integer costAlloc;
    @Column(name = "ALLOC_IND")
    private Integer allocInd;
    @Column(name = "COST")
    private Integer cost;
    @Column(name = "TEXT_CODE")
    private Integer textCode;
    @Column(name = "PHANTOM_ID")
    private Integer phantomId;
    @Column(name = "ROUNDING_DIRECTION")
    private Integer roundingDirection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private long createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private long lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "SCALE_ROUNDING_VARIANCE")
    private Integer scaleRoundingVariance;
    @Column(name = "SCALE_MULTIPLE")
    private Integer scaleMultiple;
    @Size(max = 1)
    @Column(name = "CONTRIBUTE_YIELD_IND")
    private String contributeYieldInd;
    @Size(max = 1)
    @Column(name = "CONTRIBUTE_STEP_QTY_IND")
    private String contributeStepQtyInd;
    @Column(name = "WIP_PLAN_QTY")
    private Integer wipPlanQty;
    @Column(name = "ORIGINAL_QTY")
    private Integer originalQty;
    @Size(max = 1)
    @Column(name = "BY_PRODUCT_TYPE")
    private String byProductType;
    @Column(name = "BACKORDERED_QTY")
    private Integer backorderedQty;
    @Size(max = 1)
    @Column(name = "DISPENSE_IND")
    private String dispenseInd;
    @Size(max = 3)
    @Column(name = "DTL_UM")
    private String dtlUm;

    @Column(name = "LOCATOR_ID")
    private Integer locatorId;
    @Column(name = "MATERIAL_REQUIREMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date materialRequirementDate;
    @Column(name = "MOVE_ORDER_LINE_ID")
    private Integer moveOrderLineId;
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    @Column(name = "ORIGINAL_PRIMARY_QTY")
    private Integer originalPrimaryQty;
    @Column(name = "PHANTOM_LINE_ID")
    private Integer phantomLineId;
    @Size(max = 3)
    @Column(name = "REVISION")
    private String revision;
    @Size(max = 10)
    @Column(name = "SUBINVENTORY")
    private String subinventory;
    @Column(name = "ITEM_UOM_ID")
    private Integer itemUomId;
    @Column(name = "SUBINVENTORY_ID")
    private Integer subinventoryId;
   
    @Column(name = "RESERVED_QTY")
    private BigDecimal reservedQty = BigDecimal.ZERO ;
    
    @Column(name = "FORMULA_QTY")
    private BigDecimal formulaQty;
   
    @ManyToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID")
    private SystemItem inventoryItem;
        
    @ManyToOne(optional = false)
    @JoinColumn(name = "BATCH_ID", referencedColumnName = "BATCH_ID")
    private GmeBatchHeader batch;

    @OneToOne
    @JoinColumn(name = "FORMULALINE_ID")
    private FormulaLines formulaLine;
    
    @Column(name = "LINE_QTY_IN_WIP")    
    private BigDecimal qtyRemainAfterTransactProduct = BigDecimal.ZERO  ;
    
    @Transient
    private boolean editable ;

    /// will be copied in requestline dateRequired
    @Transient
    private Date txDate = new Date() ;
    
    // quantity for requestline
    @Transient
    private BigDecimal quantityNeededToPickOrTransact ;
    
    // when secondary inventory selected , it will be updated with current quantity
    //to match it with quantityNeededToPick for warning
    @Transient
    private BigDecimal actualQtyInInventory ;
    
    public GmeMaterialDetail() {
    }

    public GmeMaterialDetail(Integer materialDetailId) {
        this.materialDetailId = materialDetailId;
    }


    public GmeMaterialDetail(FormulaLines formulaLines)
    {
        initFromFormulaLine(formulaLines);
    }
    
    public void initFromFormulaLine(FormulaLines formulaLines)
    {
       this.formulaLine = formulaLines ;
       
       this.inventoryItem = formulaLines.getSystemItem() ;
       
       this.formulaQty = formulaLines.getQty() ;
       
       this.lineType = formulaLines.getLineType() ;
       
       this.lineNo = formulaLines.getLineNo();
       
       this.dtlUm = formulaLines.getDetailUomCode();        
    }
    
    public Integer getMaterialDetailId() {
        return materialDetailId;
    }

    public void setMaterialDetailId(Integer materialDetailId) {
        this.materialDetailId = materialDetailId;
    }

    public FormulaLines getFormulaLine() {
        return formulaLine;
    }

    public void setFormulaLine(FormulaLines formulaLine) {
        this.formulaLine = formulaLine;
    }

    
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public FormulaLineType getLineType() {
        return lineType;
    }

    public void setLineType(FormulaLineType lineType) {
        this.lineType = lineType;
    }

    public BigDecimal getPlanQty() {
        return planQty;
    }

    public void setPlanQty(BigDecimal planQty) {
        this.planQty = planQty;
    }

    
    public String getItemUm() {
        return itemUm;
    }

    public void setItemUm(String itemUm) {
        this.itemUm = itemUm;
    }

    public String getItemUm2() {
        return itemUm2;
    }

    public void setItemUm2(String itemUm2) {
        this.itemUm2 = itemUm2;
    }

    public int getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(int releaseType) {
        this.releaseType = releaseType;
    }

    public Integer getScrapFactor() {
        return scrapFactor;
    }

    public void setScrapFactor(Integer scrapFactor) {
        this.scrapFactor = scrapFactor;
    }

    public int getScaleType() {
        return scaleType;
    }

    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
    }

    public int getPhantomType() {
        return phantomType;
    }

    public void setPhantomType(int phantomType) {
        this.phantomType = phantomType;
    }

    public Integer getCostAlloc() {
        return costAlloc;
    }

    public void setCostAlloc(Integer costAlloc) {
        this.costAlloc = costAlloc;
    }

    public Integer getAllocInd() {
        return allocInd;
    }

    public void setAllocInd(Integer allocInd) {
        this.allocInd = allocInd;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getTextCode() {
        return textCode;
    }

    public void setTextCode(Integer textCode) {
        this.textCode = textCode;
    }

    public Integer getPhantomId() {
        return phantomId;
    }

    public void setPhantomId(Integer phantomId) {
        this.phantomId = phantomId;
    }

    public Integer getRoundingDirection() {
        return roundingDirection;
    }

    public void setRoundingDirection(Integer roundingDirection) {
        this.roundingDirection = roundingDirection;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getScaleRoundingVariance() {
        return scaleRoundingVariance;
    }

    public void setScaleRoundingVariance(Integer scaleRoundingVariance) {
        this.scaleRoundingVariance = scaleRoundingVariance;
    }

    public Integer getScaleMultiple() {
        return scaleMultiple;
    }

    public void setScaleMultiple(Integer scaleMultiple) {
        this.scaleMultiple = scaleMultiple;
    }

    public String getContributeYieldInd() {
        return contributeYieldInd;
    }

    public void setContributeYieldInd(String contributeYieldInd) {
        this.contributeYieldInd = contributeYieldInd;
    }

    public String getContributeStepQtyInd() {
        return contributeStepQtyInd;
    }

    public void setContributeStepQtyInd(String contributeStepQtyInd) {
        this.contributeStepQtyInd = contributeStepQtyInd;
    }

    public Integer getWipPlanQty() {
        return wipPlanQty;
    }

    public void setWipPlanQty(Integer wipPlanQty) {
        this.wipPlanQty = wipPlanQty;
    }

    public Integer getOriginalQty() {
        return originalQty;
    }

    public void setOriginalQty(Integer originalQty) {
        this.originalQty = originalQty;
    }

    public String getByProductType() {
        return byProductType;
    }

    public void setByProductType(String byProductType) {
        this.byProductType = byProductType;
    }

    public Integer getBackorderedQty() {
        return backorderedQty;
    }

    public void setBackorderedQty(Integer backorderedQty) {
        this.backorderedQty = backorderedQty;
    }

    public String getDispenseInd() {
        return dispenseInd;
    }

    public void setDispenseInd(String dispenseInd) {
        this.dispenseInd = dispenseInd;
    }

    public String getDtlUm() {
        return dtlUm;
    }

    public void setDtlUm(String dtlUm) {
        this.dtlUm = dtlUm;
    }

    public SystemItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(SystemItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    

    public Integer getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Integer locatorId) {
        this.locatorId = locatorId;
    }

    public Date getMaterialRequirementDate() {
        return materialRequirementDate;
    }

    public void setMaterialRequirementDate(Date materialRequirementDate) {
        this.materialRequirementDate = materialRequirementDate;
    }

    public Integer getMoveOrderLineId() {
        return moveOrderLineId;
    }

    public void setMoveOrderLineId(Integer moveOrderLineId) {
        this.moveOrderLineId = moveOrderLineId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOriginalPrimaryQty() {
        return originalPrimaryQty;
    }

    public void setOriginalPrimaryQty(Integer originalPrimaryQty) {
        this.originalPrimaryQty = originalPrimaryQty;
    }

    public Integer getPhantomLineId() {
        return phantomLineId;
    }

    public void setPhantomLineId(Integer phantomLineId) {
        this.phantomLineId = phantomLineId;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getSubinventory() {
        return subinventory;
    }

    public void setSubinventory(String subinventory) {
        this.subinventory = subinventory;
    }

    public Integer getItemUomId() {
        return itemUomId;
    }

    public void setItemUomId(Integer itemUomId) {
        this.itemUomId = itemUomId;
    }

    public Integer getSubinventoryId() {
        return subinventoryId;
    }

    public void setSubinventoryId(Integer subinventoryId) {
        this.subinventoryId = subinventoryId;
    }

    public BigDecimal getFormulaQty() {
        return formulaQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public void setFormulaQty(BigDecimal formulaQty) {
        this.formulaQty = formulaQty;
    }

    public GmeBatchHeader getBatch() {
        return batch;
    }

    public void setBatch(GmeBatchHeader batch) {
        this.batch = batch;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialDetailId != null ? materialDetailId.hashCode() : 0);
        return hash;
    }

    public BigDecimal getReservedQty() {
        return reservedQty;
    }

    public void setReservedQty(BigDecimal reservedQty) {
        this.reservedQty = reservedQty;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GmeMaterialDetail)) {
            return false;
        }
        GmeMaterialDetail other = (GmeMaterialDetail) object;
        if ((this.materialDetailId == null && other.materialDetailId != null) || (this.materialDetailId != null && !this.materialDetailId.equals(other.materialDetailId))) {
            return false;
        }
        return true;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.batch.GmeMaterialDetail[ materialDetailId=" + materialDetailId + " ]";
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public BigDecimal getQuantityNeededToPickOrTransact() {
        return quantityNeededToPickOrTransact;
    }

    public void setQuantityNeededToPickOrTransact(BigDecimal quantityNeededToPickOrTransact) {
        this.quantityNeededToPickOrTransact = quantityNeededToPickOrTransact;
    }

    
    public BigDecimal getActualQty() {
        return actualQty;
    }

    public BigDecimal getActualQtyInInventory() {
        return actualQtyInInventory;
    }

    
    public void setActualQtyInInventory(BigDecimal actualQtyInInventory) {
        this.actualQtyInInventory = actualQtyInInventory;
    }

    public BigDecimal getQtyRemainAfterTransactProduct() {
        return qtyRemainAfterTransactProduct;
    }

    public void setQtyRemainAfterTransactProduct(BigDecimal qtyRemainAfterTransactProduct) {
        this.qtyRemainAfterTransactProduct = qtyRemainAfterTransactProduct;
    }

        
    @PrePersist
    private void prePresist()
    {
      lastUpdateDate = new Date() ;
      
      creationDate = new Date() ;
      
      lastUpdatedBy = 1 ;
      
      createdBy = 1 ;
      
      scrapFactor = 1 ;
      
    }
}
