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
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "FM_MATL_DTL")
@NamedQueries({
    @NamedQuery(name = "FormulaLines.findAll", query = "SELECT f FROM FormulaLines f")})
public class FormulaLines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
        @GeneratedValue(strategy = GenerationType.TABLE,generator = "fLine")
    @TableGenerator(name = "fLine",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "FormulaLine",valueColumnName = "CURRENT_VALUE")
    @Column(name = "FORMULALINE_ID")
    private Integer formulalineId;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "FORMULA_ID")
//    private long formulaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_TYPE")
    private FormulaLineType lineType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINE_NO")
    private int lineNo;
   
    @OneToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID" ,referencedColumnName = "INVENTORY_ITEM_ID")
    private SystemItem systemItem;
    
    //relation required ==========================================
    @Column(name = "ITEM_UOM_ID")
    private Integer itemUomId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private BigDecimal qty;
    @Column(name = "RELEASE_TYPE")
    private Integer releaseType;
    @Column(name = "SCRAP_FACTOR")
    private BigInteger scrapFactor;
    @Column(name = "SCALE_TYPE")
    private Integer scaleType;
    @Column(name = "COST_ALLOC")
    private BigInteger costAlloc;
    @Column(name = "REWORK_TYPE")
    private Integer reworkType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private long lastUpdatedBy;
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
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;
    @Column(name = "TPFORMULA_ID")
    private Long tpformulaId;
    @Column(name = "IAFORMULA_ID")
    private BigInteger iaformulaId;
    @Size(max = 4)
    @Column(name = "SCALE_UOM")
    private String scaleUom;
    @Size(max = 1)
    @Column(name = "CONTRIBUTE_STEP_QTY_IND")
    private String contributeStepQtyInd;
    @Size(max = 1)
    @Column(name = "CONTRIBUTE_YIELD_IND")
    private String contributeYieldInd;
    @Column(name = "SCALE_MULTIPLE")
    private BigInteger scaleMultiple;
    @Column(name = "SCALE_ROUNDING_VARIANCE")
    private BigInteger scaleRoundingVariance;
    @Column(name = "ROUNDING_DIRECTION")
    private Integer roundingDirection;
    @Size(max = 1)
    @Column(name = "BY_PRODUCT_TYPE")
    private String byProductType;
    @Column(name = "INGREDIENT_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ingredientEndDate;
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;
    @Size(max = 3)
    @Column(name = "DETAIL_UOM_CODE")
    private String detailUomCode;
    @Size(max = 3)
    @Column(name = "REVISION")
    private String revision;
    @Column(name = "PROD_PERCENT")
    private BigInteger prodPercent;
    @ManyToOne
    @JoinColumn(name="FORMULA_ID")
    private Formula formula;

    
    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    
    public FormulaLines() {
    }

    public Integer getFormulalineId() {
        return formulalineId;
    }

    public void setFormulalineId(Integer formulalineId) {
        this.formulalineId = formulalineId;
    }



//    public long getFormulaId() {
//        return formulaId;
//    }
//
//    public void setFormulaId(long formulaId) {
//        this.formulaId = formulaId;
//    }

    public SystemItem getSystemItem() {
        return systemItem;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }


    public void setItemUomId(Integer itemUomId) {
        this.itemUomId = itemUomId;
    }

    public Integer getItemUomId() {
        return itemUomId;
    }

    
    public FormulaLineType getLineType() {
        return lineType;
    }

    public void setLineType(FormulaLineType lineType) {
        this.lineType = lineType;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    public BigInteger getScrapFactor() {
        return scrapFactor;
    }

    public void setScrapFactor(BigInteger scrapFactor) {
        this.scrapFactor = scrapFactor;
    }

    public Integer getScaleType() {
        return scaleType;
    }

    public void setScaleType(Integer scaleType) {
        this.scaleType = scaleType;
    }

    public BigInteger getCostAlloc() {
        return costAlloc;
    }

    public void setCostAlloc(BigInteger costAlloc) {
        this.costAlloc = costAlloc;
    }

    public Integer getReworkType() {
        return reworkType;
    }

    public void setReworkType(Integer reworkType) {
        this.reworkType = reworkType;
    }

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }
    
    public Long getTpformulaId() {
        return tpformulaId;
    }

    public void setTpformulaId(Long tpformulaId) {
        this.tpformulaId = tpformulaId;
    }

    public BigInteger getIaformulaId() {
        return iaformulaId;
    }

    public void setIaformulaId(BigInteger iaformulaId) {
        this.iaformulaId = iaformulaId;
    }

    public String getScaleUom() {
        return scaleUom;
    }

    public void setScaleUom(String scaleUom) {
        this.scaleUom = scaleUom;
    }

    public String getContributeStepQtyInd() {
        return contributeStepQtyInd;
    }

    public void setContributeStepQtyInd(String contributeStepQtyInd) {
        this.contributeStepQtyInd = contributeStepQtyInd;
    }

    public String getContributeYieldInd() {
        return contributeYieldInd;
    }

    public void setContributeYieldInd(String contributeYieldInd) {
        this.contributeYieldInd = contributeYieldInd;
    }

    public BigInteger getScaleMultiple() {
        return scaleMultiple;
    }

    public void setScaleMultiple(BigInteger scaleMultiple) {
        this.scaleMultiple = scaleMultiple;
    }

    public BigInteger getScaleRoundingVariance() {
        return scaleRoundingVariance;
    }

    public void setScaleRoundingVariance(BigInteger scaleRoundingVariance) {
        this.scaleRoundingVariance = scaleRoundingVariance;
    }

    public Integer getRoundingDirection() {
        return roundingDirection;
    }

    public void setRoundingDirection(Integer roundingDirection) {
        this.roundingDirection = roundingDirection;
    }

    public String getByProductType() {
        return byProductType;
    }

    public void setByProductType(String byProductType) {
        this.byProductType = byProductType;
    }

    public Date getIngredientEndDate() {
        return ingredientEndDate;
    }

    public void setIngredientEndDate(Date ingredientEndDate) {
        this.ingredientEndDate = ingredientEndDate;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getDetailUomCode() {
        return detailUomCode;
    }

    public void setDetailUomCode(String detailUomCode) {
        this.detailUomCode = detailUomCode;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public BigInteger getProdPercent() {
        return prodPercent;
    }

    public void setProdPercent(BigInteger prodPercent) {
        this.prodPercent = prodPercent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formulalineId != null ? formulalineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulaLines)) {
            return false;
        }
        FormulaLines other = (FormulaLines) object;
        if ((this.formulalineId == null && other.formulalineId != null) || (this.formulalineId != null && !this.formulalineId.equals(other.formulalineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.controllers.FormulaLines[ formulalineId=" + formulalineId + " ]";
    }
        @PrePersist
    public void setNullValues(){
        setScaleType(1);
        setCreatedBy(1);
        setCreationDate(new Date());
        setLastUpdatedBy(1);
        setLastUpdateDate(new Date());
    }
}
