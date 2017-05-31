/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.currency;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "FND_CURRENCIES")
@NamedQueries({
    @NamedQuery(name = "FndCurrency.findAll", query = "SELECT f FROM FndCurrency f")})
public class FndCurrency implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "curr")
    @TableGenerator(name = "curr",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Currency",valueColumnName = "CURRENT_VALUE")
    @Column(name = "CURRENCY_ID")
    private Integer currencyId ;
    
    @Size(min = 1, max = 15)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    
    @Basic(optional = false)
    @Column(name = "ENABLED_FLAG")
    private boolean enabled;
    
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
      
    @Size(max = 12)
    @Column(name = "SYMBOL")
    private String symbol;
   
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
  
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    
    @Column(name = "MINIMUM_ACCOUNTABLE_UNIT")
    private Integer minimumAccountableUnit;
    
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "ISO_FLAG")
    private String isoFlag;
    
    @Column(name = "DERIVE_EFFECTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deriveEffective;
   
    @Size(max = 8)
    @Column(name = "DERIVE_TYPE")
    private String deriveType;
    @Column(name = "DERIVE_FACTOR")
    
    private Integer deriveFactor;
    
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "ZD_EDITION_NAME")
    private String zdEditionName;

    public FndCurrency() {
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public Integer getMinimumAccountableUnit() {
        return minimumAccountableUnit;
    }

    public void setMinimumAccountableUnit(Integer minimumAccountableUnit) {
        this.minimumAccountableUnit = minimumAccountableUnit;
    }

    public String getIsoFlag() {
        return isoFlag;
    }

    public void setIsoFlag(String isoFlag) {
        this.isoFlag = isoFlag;
    }

    public Date getDeriveEffective() {
        return deriveEffective;
    }

    public void setDeriveEffective(Date deriveEffective) {
        this.deriveEffective = deriveEffective;
    }

    public String getDeriveType() {
        return deriveType;
    }

    public void setDeriveType(String deriveType) {
        this.deriveType = deriveType;
    }

    public Integer getDeriveFactor() {
        return deriveFactor;
    }

    public void setDeriveFactor(Integer deriveFactor) {
        this.deriveFactor = deriveFactor;
    }

    public String getZdEditionName() {
        return zdEditionName;
    }

    public void setZdEditionName(String zdEditionName) {
        this.zdEditionName = zdEditionName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyCode != null ? currencyCode.hashCode() : 0);
        return hash;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FndCurrency)) {
            return false;
        }
        FndCurrency other = (FndCurrency) object;
        if ((this.currencyCode == null && other.currencyCode != null) || (this.currencyCode!= null && 
                !this.currencyCode.equals(other.currencyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.coa.FndCurrency[ currencyCode=" + currencyCode + " ]";
    }
    
}
