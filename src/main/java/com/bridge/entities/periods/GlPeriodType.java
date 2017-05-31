/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.periods;

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
@Table(name = "GL_PERIOD_TYPES")
@NamedQueries({
    @NamedQuery(name = "GlPeriodType.findAll", query = "SELECT g FROM GlPeriodType g")})
public class GlPeriodType implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "periodType")
    @TableGenerator(name = "periodType",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PeriodType",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIOD_TYPE_ID")
    private Integer periodTypeId;
    
    @Size(max = 15)
    @Column(name = "PERIOD_TYPE_NAME")
    private String periodTypeName;
   
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
   
    @Size(max = 64)
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
   
    @Column(name = "ACCOUNTING_PERIODS_IN_YEAR")
    private Integer accountingPeriods;

    @Column(name = "ADJUSTMENT_PERIODS_IN_YEAR")
    private Integer noOfAdjusts ;
    
    @Size(max = 15)
    @Column(name = "USER_PERIOD_TYPE")
    private String userPeriodType;
    @Size(max = 1)
    @Column(name = "YEAR_TYPE_IN_NAME")
    private String yearTypeInName;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 64)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Size(max = 32)
    @Column(name = "LAST_UPDATE_LOGIN")
    private String lastUpdateLogin;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;

    public GlPeriodType() {
    }

    public GlPeriodType(Integer periodTypeId) {
        this.periodTypeId = periodTypeId;
    }

    public Integer getPeriodTypeId() {
        return periodTypeId;
    }

    public void setPeriodTypeId(Integer periodTypeId) {
        this.periodTypeId = periodTypeId;
    }

    public String getPeriodTypeName() {
        return periodTypeName;
    }

    public void setPeriodTypeName(String periodTypeName) {
        this.periodTypeName = periodTypeName;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getNoOfAdjusts() {
        return noOfAdjusts;
    }

    public void setAccountingPeriods(Integer accountingPeriods) {
        this.accountingPeriods = accountingPeriods;
    }

    public void setNoOfAdjusts(Integer noOfAdjusts) {
        this.noOfAdjusts = noOfAdjusts;
    }

    public Integer getAccountingPeriods() {
        return accountingPeriods;
    }

    
    public String getUserPeriodType() {
        return userPeriodType;
    }

    public void setUserPeriodType(String userPeriodType) {
        this.userPeriodType = userPeriodType;
    }

    public String getYearTypeInName() {
        return yearTypeInName;
    }

    public void setYearTypeInName(String yearTypeInName) {
        this.yearTypeInName = yearTypeInName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodTypeId != null ? periodTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlPeriodType)) {
            return false;
        }
        GlPeriodType other = (GlPeriodType) object;
        if ((this.periodTypeId == null && other.periodTypeId != null) || (this.periodTypeId != null && !this.periodTypeId.equals(other.periodTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.periods.GlPeriodType[ periodTypeId=" + periodTypeId + " ]";
    }
    
}
