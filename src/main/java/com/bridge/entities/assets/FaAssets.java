/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.assets;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.enums.AssetReleaseType;
import com.bridge.enums.AssetStatus;
import com.bridge.enums.AssetType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "FA_ASSETS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaAssets.findAll", query = "SELECT f FROM FaAssets f")})
public class FaAssets implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FaLocations")
    @TableGenerator(name = "FaLocations", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "FaLocations", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSET_ID")
    private Integer assetId;
    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;
    @OneToOne
    @JoinColumn(name = "ASSET_LOCATION")
    private PhysicalLocation assetLocation;
    @OneToOne
    @JoinColumn(name = "ASSET_CATEGORY_ID")
    private FaCategory assetCategory;
    @OneToOne()
    @JoinColumn(name = "PARENT_ASSET_ID")
    private FaAssets mainAsset;
    @Size(max = 100)
    @Column(name = "ASSET_CODE")
    private String assetCode;
    @Size(max = 255)
    @Column(name = "ASSET_NAME")
    private String assetName;
    @Column(name = "ASSET_LIFE_MONTHS")
    private BigDecimal assetLifeMonths;
    @Column(name = "ASSET_LIFE_REMAINING_MONTHS")
    private BigDecimal remainingMonths;
    @Column(name = "ORIGINAL_COST")
    private BigDecimal originalCost;
    @Column(name = "SALVAGE_COST")
    private BigDecimal salvageCost;
    @Column(name = "PREMIUM_DEPRECIATION")
    private BigDecimal premiumDepreciation;
    @Column(name = "CURRENT_COST")
    private BigDecimal currentCost;
    @Column(name = "ADDITION_DATE")
    @Temporal(TemporalType.DATE)
    private Date additionDate;
    @Column(name = "LAST_EVALUATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastEvaluationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "ASSET_TYPE")
    private AssetType assetType;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AssetStatus status;
    @Column(name = "ADDITION_JOURNAL_FLAG")
    private boolean addJornalFlag;
    @Column(name = "SUB_ASSET")
    private boolean subAsset;
    @Column(name = "RECEIVING_DATE")
    @Temporal(TemporalType.DATE)
    private Date receivingDate;
    @Column(name = "RETIRMENT_REASON")
    private String retirmentReason;
    @Column(name = "RETIRMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date retirmentDate;
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "ONSERVICE_DATE")
    @Temporal(TemporalType.DATE)
    private Date onserviceDate;
    @Column(name = "RETIRMENT_REMOVAL_COST")
    private BigDecimal removalCost;
    @Column(name = "ASSET_COST_WHEN_RETIRING")
    private BigDecimal retirmentCost;
    @Enumerated(EnumType.STRING)
    @Column(name = "RELEASE_TYPE")
    private AssetReleaseType releaseType;
    @Column(name = "RELEASE_AMOUNT")
    private BigDecimal releaseAmount;

    public FaAssets() {
    }

    public FaAssets(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public PhysicalLocation getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(PhysicalLocation assetLocation) {
        this.assetLocation = assetLocation;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    public FaCategory getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(FaCategory assetCategory) {
        this.assetCategory = assetCategory;
    }

    public BigDecimal getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(BigDecimal originalCost) {
        this.originalCost = originalCost;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public FaAssets getMainAsset() {
        return mainAsset;
    }

    public void setMainAsset(FaAssets mainAsset) {
        this.mainAsset = mainAsset;
    }

    public Date getLastEvaluationDate() {
        return lastEvaluationDate;
    }

    public void setLastEvaluationDate(Date lastEvaluationDate) {
        this.lastEvaluationDate = lastEvaluationDate;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public BigDecimal getAssetLifeMonths() {
        return assetLifeMonths;
    }

    public void setAssetLifeMonths(BigDecimal assetLifeMonths) {
        this.assetLifeMonths = assetLifeMonths;
    }

    public BigDecimal getSalvageCost() {
        return salvageCost;
    }

    public void setSalvageCost(BigDecimal salvageCost) {
        this.salvageCost = salvageCost;
    }

    public BigDecimal getPremiumDepreciation() {
        return premiumDepreciation;
    }

    public void setPremiumDepreciation(BigDecimal premiumDepreciation) {
        this.premiumDepreciation = premiumDepreciation;
    }

    public boolean getAddJornalFlag() {
        return addJornalFlag;
    }

    public void setAddJornalFlag(boolean addJornalFlag) {
        this.addJornalFlag = addJornalFlag;
    }


    public BigDecimal getRemainingMonths() {
        return remainingMonths;
    }

    public void setRemainingMonths(BigDecimal remainingMonths) {
        this.remainingMonths = remainingMonths;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getRetirmentReason() {
        return retirmentReason;
    }

    public void setRetirmentReason(String retirmentReason) {
        this.retirmentReason = retirmentReason;
    }

    public Date getRetirmentDate() {
        return retirmentDate;
    }

    public void setRetirmentDate(Date retirmentDate) {
        this.retirmentDate = retirmentDate;
    }

    public BigDecimal getRemovalCost() {
        return removalCost;
    }

    public void setRemovalCost(BigDecimal removalCost) {
        this.removalCost = removalCost;
    }

    public BigDecimal getRetirmentCost() {
        return retirmentCost;
    }

    public void setRetirmentCost(BigDecimal retirmentCost) {
        this.retirmentCost = retirmentCost;
    }

    public AssetReleaseType getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(AssetReleaseType releaseType) {
        this.releaseType = releaseType;
    }

    public BigDecimal getReleaseAmount() {
        return releaseAmount;
    }

    public void setReleaseAmount(BigDecimal releaseAmount) {
        this.releaseAmount = releaseAmount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getOnserviceDate() {
        return onserviceDate;
    }

    public void setOnserviceDate(Date onserviceDate) {
        this.onserviceDate = onserviceDate;
    }

    public boolean getSubAsset() {
        return subAsset;
    }

    public void setSubAsset(boolean subAsset) {
        this.subAsset = subAsset;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaAssets)) {
            return false;
        }
        FaAssets other = (FaAssets) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.assets.FaLocations[ assetId=" + assetId + " ]";
    }

}
