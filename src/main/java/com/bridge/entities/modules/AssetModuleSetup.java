/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "ASSET_MODULE_SETUP")
@NamedQueries({
    @NamedQuery(name = "AssetModuleSetup.findAll", query = "SELECT a FROM AssetModuleSetup a")})
public class AssetModuleSetup implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    
    
     @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "assetconfig")
    @TableGenerator(name = "assetconfig",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "AssetConfig",valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    
    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;
    @Size(max = 255)
    @Column(name = "PRORATE_CONVENTION_OPTION")
    private String prorateConventionOption;
    @Column(name = "OLDEST_DATE_PLACED_IN_SERVICE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date oldestDatePlacedInService;
    @Column(name = "ALLOW_GL_POSTING")
    private BigInteger allowGlPosting;
    @Column(name = "CURRENT_PERIOD")
    private BigInteger currentPeriod;
    @Column(name = "CURRENT_YEAR")
    private BigInteger currentYear;
    @Column(name = "LAST_DEPRECATION_RUN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDeprecationRun;
    @ManyToOne
    @JoinColumn(name = "PROCDS_OF_SAL_GAIN_ACC")
    private GlCodeCombination procdsOfSalGainAcc;
    @ManyToOne
    @JoinColumn(name = "PROCDS_OF_SAL_LOSS_ACC")
    private GlCodeCombination procdsOfSalLossAcc;
    @ManyToOne
    @JoinColumn(name = "PROCDS_OF_SAL_CLEARING_ACC")
    private GlCodeCombination procdsOfSalClearingAcc;
    @ManyToOne
    @JoinColumn(name = "NT_BOOK_VAL_RTIRD_GAIN_ACC")
    private GlCodeCombination ntBookValRtirdGainAcc;
    @ManyToOne
    @JoinColumn(name = "NT_BOOK_VAL_RTIRD_LOSS_ACC")
    private GlCodeCombination ntBookValRtirdLossAcc;
    @ManyToOne
    @JoinColumn(name = "COST_OF_REMOVAL_GAIN_ACC")
    private GlCodeCombination costOfRemovalGainAcc;
    @ManyToOne
    @JoinColumn(name = "COST_OF_REMOVAL_LOSS_ACC")
    private GlCodeCombination costOfRemovalLossAcc;
    @ManyToOne
    @JoinColumn(name = "COST_OF_REMOVAL_CLEARING_ACC")
    private GlCodeCombination costOfRemovalClearingAcc;
    @ManyToOne
    @JoinColumn(name = "DFRD_DPRCTION_RESERVE_ACC")
    private GlCodeCombination dfrdDprctionReserveAcc;
    @ManyToOne
    @JoinColumn(name = "DFRD_DPRCTION_EXPENSE_ACC")
    private GlCodeCombination dfrdDprctionExpenseAcc;
    @ManyToOne
    @JoinColumn(name = "DPRCTION_ADJUSTMENT_ACC")
    private GlCodeCombination dprctionAdjustmentAcc;
    @ManyToOne
    @JoinColumn(name = "ACC_GENERATOR_DEFAULTS")
    private GlCodeCombination accGeneratorDefaults;

    
    
    @ManyToOne
    @JoinColumn(name = "SCRAP_LOSS_ACC")
    private GlCodeCombination scrapLossAcc;
    @ManyToOne
    @JoinColumn(name = "CAPITAL_GAINS_ACC")
    private GlCodeCombination capitalGainsAcc;
    @ManyToOne
    @JoinColumn(name = "CAPITAL_LOSSES_ACC")
    private GlCodeCombination capitalLossesAcc;    
    


    @ManyToOne
    @JoinColumn(name = "ASSET_EXPENSES_ACC")
    private GlCodeCombination assetExpensesAcc; 
    
    
    
    public AssetModuleSetup() {
    }

    public AssetModuleSetup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getProrateConventionOption() {
        return prorateConventionOption;
    }

    public void setProrateConventionOption(String prorateConventionOption) {
        this.prorateConventionOption = prorateConventionOption;
    }

    public Date getOldestDatePlacedInService() {
        return oldestDatePlacedInService;
    }

    public void setOldestDatePlacedInService(Date oldestDatePlacedInService) {
        this.oldestDatePlacedInService = oldestDatePlacedInService;
    }

    public BigInteger getAllowGlPosting() {
        return allowGlPosting;
    }

    public void setAllowGlPosting(BigInteger allowGlPosting) {
        this.allowGlPosting = allowGlPosting;
    }

    public BigInteger getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(BigInteger currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public BigInteger getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(BigInteger currentYear) {
        this.currentYear = currentYear;
    }

    public Date getLastDeprecationRun() {
        return lastDeprecationRun;
    }

    public void setLastDeprecationRun(Date lastDeprecationRun) {
        this.lastDeprecationRun = lastDeprecationRun;
    }

    public GlCodeCombination getProcdsOfSalGainAcc() {
        return procdsOfSalGainAcc;
    }

    public void setProcdsOfSalGainAcc(GlCodeCombination procdsOfSalGainAcc) {
        this.procdsOfSalGainAcc = procdsOfSalGainAcc;
    }

    public GlCodeCombination getProcdsOfSalLossAcc() {
        return procdsOfSalLossAcc;
    }

    public void setProcdsOfSalLossAcc(GlCodeCombination procdsOfSalLossAcc) {
        this.procdsOfSalLossAcc = procdsOfSalLossAcc;
    }

    public GlCodeCombination getProcdsOfSalClearingAcc() {
        return procdsOfSalClearingAcc;
    }

    public void setProcdsOfSalClearingAcc(GlCodeCombination procdsOfSalClearingAcc) {
        this.procdsOfSalClearingAcc = procdsOfSalClearingAcc;
    }

    public GlCodeCombination getNtBookValRtirdGainAcc() {
        return ntBookValRtirdGainAcc;
    }

    public void setNtBookValRtirdGainAcc(GlCodeCombination ntBookValRtirdGainAcc) {
        this.ntBookValRtirdGainAcc = ntBookValRtirdGainAcc;
    }

    public GlCodeCombination getNtBookValRtirdLossAcc() {
        return ntBookValRtirdLossAcc;
    }

    public void setNtBookValRtirdLossAcc(GlCodeCombination ntBookValRtirdLossAcc) {
        this.ntBookValRtirdLossAcc = ntBookValRtirdLossAcc;
    }

    public GlCodeCombination getCostOfRemovalGainAcc() {
        return costOfRemovalGainAcc;
    }

    public void setCostOfRemovalGainAcc(GlCodeCombination costOfRemovalGainAcc) {
        this.costOfRemovalGainAcc = costOfRemovalGainAcc;
    }

    public GlCodeCombination getCostOfRemovalLossAcc() {
        return costOfRemovalLossAcc;
    }

    public void setCostOfRemovalLossAcc(GlCodeCombination costOfRemovalLossAcc) {
        this.costOfRemovalLossAcc = costOfRemovalLossAcc;
    }

    public GlCodeCombination getCostOfRemovalClearingAcc() {
        return costOfRemovalClearingAcc;
    }

    public void setCostOfRemovalClearingAcc(GlCodeCombination costOfRemovalClearingAcc) {
        this.costOfRemovalClearingAcc = costOfRemovalClearingAcc;
    }

    public GlCodeCombination getDfrdDprctionReserveAcc() {
        return dfrdDprctionReserveAcc;
    }

    public void setDfrdDprctionReserveAcc(GlCodeCombination dfrdDprctionReserveAcc) {
        this.dfrdDprctionReserveAcc = dfrdDprctionReserveAcc;
    }

    public GlCodeCombination getDfrdDprctionExpenseAcc() {
        return dfrdDprctionExpenseAcc;
    }

    public void setDfrdDprctionExpenseAcc(GlCodeCombination dfrdDprctionExpenseAcc) {
        this.dfrdDprctionExpenseAcc = dfrdDprctionExpenseAcc;
    }

    public GlCodeCombination getDprctionAdjustmentAcc() {
        return dprctionAdjustmentAcc;
    }

    public void setDprctionAdjustmentAcc(GlCodeCombination dprctionAdjustmentAcc) {
        this.dprctionAdjustmentAcc = dprctionAdjustmentAcc;
    }

    public GlCodeCombination getAccGeneratorDefaults() {
        return accGeneratorDefaults;
    }

    public void setAccGeneratorDefaults(GlCodeCombination accGeneratorDefaults) {
        this.accGeneratorDefaults = accGeneratorDefaults;
    }

    public GlCodeCombination getScrapLossAcc() {
        return scrapLossAcc;
    }

    public void setScrapLossAcc(GlCodeCombination scrapLossAcc) {
        this.scrapLossAcc = scrapLossAcc;
    }

    public GlCodeCombination getCapitalGainsAcc() {
        return capitalGainsAcc;
    }

    public void setCapitalGainsAcc(GlCodeCombination capitalGainsAcc) {
        this.capitalGainsAcc = capitalGainsAcc;
    }

    public GlCodeCombination getCapitalLossesAcc() {
        return capitalLossesAcc;
    }

    public void setCapitalLossesAcc(GlCodeCombination capitalLossesAcc) {
        this.capitalLossesAcc = capitalLossesAcc;
    }

    public GlCodeCombination getAssetExpensesAcc() {
        return assetExpensesAcc;
    }

    public void setAssetExpensesAcc(GlCodeCombination assetExpensesAcc) {
        this.assetExpensesAcc = assetExpensesAcc;
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
        if (!(object instanceof AssetModuleSetup)) {
            return false;
        }
        AssetModuleSetup other = (AssetModuleSetup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.modules.AssetModuleSetup[ id=" + id + " ]";
    }
    
}
