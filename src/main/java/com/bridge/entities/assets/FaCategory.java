/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.assets;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AssetInterval;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "FA_CATEGORIES")
public class FaCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
          @GeneratedValue(strategy = GenerationType.TABLE,generator = "FaCategory")
    @TableGenerator(name = "FaCategory",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "FaCategory",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;
    @Size(max = 255)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "PARENT_CATEGORY_ID")
    private Integer parentCategoryId;
    @Column(name = "STRUCTURE_ID")
    private Integer structureId;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DISABLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disableDate;
    @Column(name = "ENABLED_FLAG_ID")
    private boolean enabledFlagId;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Size(max = 10)
    @Column(name = "CATEGORY_CODE")
    private String categoryCode;
    @Size(max = 150)
    @Column(name = "CATEGORY_TYPE")
    private String categoryType;
    @Enumerated(EnumType.STRING)
    @Column(name = "PRORATE_TYPE")
    private AssetInterval prorateType;
    @Column(name = "PRORATE_PERCENT")
    private BigDecimal proratePercent;
    @OneToOne
    @JoinColumn(name="ASSET_ACC")
    private GlCodeCombination assetAcc;
    @OneToOne
    @JoinColumn(name="ASSET_CLEARING_ACC")
    private GlCodeCombination assetClearingAcc;
    @OneToOne
    @JoinColumn(name="DEPRECIATION_EXPENSE_ACC")
    private GlCodeCombination depreciationExpenseAcc;
    @OneToOne
    @JoinColumn(name="ACCUMULATED_DEPRECIATION_ACC")
    private GlCodeCombination acumulationDepreciationAcc;
    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;

    public FaCategory() {
    }

    public FaCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public FaCategory(Integer categoryId, Date lastUpdateDate, Integer lastUpdatedBy, Date creationDate, Integer createdBy) {
        this.categoryId = categoryId;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public boolean getEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public AssetInterval getProrateType() {
        return prorateType;
    }

    public void setProrateType(AssetInterval prorateType) {
        this.prorateType = prorateType;
    }

    public BigDecimal getProratePercent() {
        return proratePercent;
    }

    public void setProratePercent(BigDecimal proratePercent) {
        this.proratePercent = proratePercent;
    }

    public GlCodeCombination getAssetAcc() {
        return assetAcc;
    }

    public void setAssetAcc(GlCodeCombination assetAcc) {
        this.assetAcc = assetAcc;
    }

    public GlCodeCombination getAcumulationDepreciationAcc() {
        return acumulationDepreciationAcc;
    }

    public void setAcumulationDepreciationAcc(GlCodeCombination acumulationDepreciationAcc) {
        this.acumulationDepreciationAcc = acumulationDepreciationAcc;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public GlCodeCombination getAssetClearingAcc() {
        return assetClearingAcc;
    }

    public void setAssetClearingAcc(GlCodeCombination assetClearingAcc) {
        this.assetClearingAcc = assetClearingAcc;
    }

    public GlCodeCombination getDepreciationExpenseAcc() {
        return depreciationExpenseAcc;
    }

    public void setDepreciationExpenseAcc(GlCodeCombination depreciationExpenseAcc) {
        this.depreciationExpenseAcc = depreciationExpenseAcc;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaCategory)) {
            return false;
        }
        FaCategory other = (FaCategory) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.categories.FaCategories[ categoryId=" + categoryId + " ]";
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
