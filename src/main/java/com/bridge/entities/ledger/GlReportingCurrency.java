/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.ledger;

import com.bridge.entities.currency.FndCurrency;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GL_REPORTING_CURRENCIES")
public class GlReportingCurrency implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "glCurr")
    @TableGenerator(name = "glCurr", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "GlReportingCurrency", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "GL_ID")
    private GlLedger ledger;
    
    @Column(name = "CURRENCY_CONVERSION_LEVEL_ID")
    private Integer currencyConversionLevelId;
    
    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency;
    
    @Size(max = 100)
    @Column(name = "REPORTING_CURRENCY_NAME")
    private String reportingCurrencyName;
    
    @Size(max = 10)
    @Column(name = "REPORTING_CURRENCY_SHORT_NAME")
    private String reportingCurrencyShortName;
    
    @Column(name = "PERIOD_END_RATE_TYPE_ID")
    private Integer periodEndRateTypeId;
    
    @Column(name = "PERIOD_AVERAGE_RATE_TYPE_ID")
    private Integer periodAverageRateTypeId;
    
    @Size(max = 150)
    @Column(name = "CURRENCY_CONVERSION_LEVEL")
    private String currencyConversionLevel;
    

    @Size(max = 150)
    @Column(name = "PERIOD_END_RATE_TYPE")
    private String periodEndRateType;
    
    @Size(max = 150)
    @Column(name = "PERIOD_AVERAGE_RATE_TYPE")
    private String periodAverageRateType;
    
    @Column(name = "ENABLED_FLAG")
    private Integer enabledFlag;

    public GlReportingCurrency() {
    }

    public GlReportingCurrency(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getCurrencyConversionLevelId() {
        return currencyConversionLevelId;
    }

    public void setCurrencyConversionLevelId(Integer currencyConversionLevelId) {
        this.currencyConversionLevelId = currencyConversionLevelId;
    }



    public String getReportingCurrencyName() {
        return reportingCurrencyName;
    }

    public void setReportingCurrencyName(String reportingCurrencyName) {
        this.reportingCurrencyName = reportingCurrencyName;
    }

    public String getReportingCurrencyShortName() {
        return reportingCurrencyShortName;
    }

    public GlLedger getLedger() {
        return ledger;
    }

    public void setLedger(GlLedger ledger) {
        this.ledger = ledger;
    }
    
    
    public void setReportingCurrencyShortName(String reportingCurrencyShortName) {
        this.reportingCurrencyShortName = reportingCurrencyShortName;
    }

    public Integer getPeriodEndRateTypeId() {
        return periodEndRateTypeId;
    }

    public void setPeriodEndRateTypeId(Integer periodEndRateTypeId) {
        this.periodEndRateTypeId = periodEndRateTypeId;
    }

    public Integer getPeriodAverageRateTypeId() {
        return periodAverageRateTypeId;
    }

    public void setPeriodAverageRateTypeId(Integer periodAverageRateTypeId) {
        this.periodAverageRateTypeId = periodAverageRateTypeId;
    }

    public String getCurrencyConversionLevel() {
        return currencyConversionLevel;
    }

    public void setCurrencyConversionLevel(String currencyConversionLevel) {
        this.currencyConversionLevel = currencyConversionLevel;
    }


    public String getPeriodEndRateType() {
        return periodEndRateType;
    }

    public void setPeriodEndRateType(String periodEndRateType) {
        this.periodEndRateType = periodEndRateType;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }
    
    
    public String getPeriodAverageRateType() {
        return periodAverageRateType;
    }

    public void setPeriodAverageRateType(String periodAverageRateType) {
        this.periodAverageRateType = periodAverageRateType;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
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
        if (!(object instanceof GlReportingCurrency)) {
            return false;
        }
        GlReportingCurrency other = (GlReportingCurrency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.ledger.GlReportingCurrency[ id=" + id + " ]";
    }

}
