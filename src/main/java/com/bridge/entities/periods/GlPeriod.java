/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.periods;

import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.enums.PeriodStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GL_PERIODS")
@NamedQueries({
    @NamedQuery(name = "GlPeriod.findAll", query = "SELECT g FROM GlPeriod g")})
public class GlPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "period")
    @TableGenerator(name = "period",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Period",valueColumnName = "CURRENT_VALUE")
    @Column(name = "PERIOD_ID")
    private Integer periodId;
    
    @Size(max = 15)
    @Column(name = "PERIOD_NAME")
    private String periodName;
    
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    
    @Column(name = "START_DATE")
    private LocalDate startDate;
    
    @Column(name = "END_DATE")
    private LocalDate endDate;
    
    @Column(name = "YEAR_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearStartDate;
    
    @Column(name = "QUARTER_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date quarterStartDate;
    
    @Size(max = 15)
    @Column(name = "PERIOD_TYPE")
    private String periodType;
    
    @Column(name = "PERIOD_TYPE_ID")
    private Integer periodTypeId;
    @Column(name = "PERIOD_YEAR")
    private Integer periodYear;
    @Column(name = "PERIOD_NUM")
    private Integer periodNum;
    @Column(name = "QUARTER_NUM")
    private Integer quarterNum;
    @Size(max = 15)
    @Column(name = "ENTERED_PERIOD_NAME")
    private String enteredPeriodName;
  
    @Column(name = "ADJUSTMENT_PERIOD_FLAG")
    private boolean adjustmentPeriodFlag;
  
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "PERIOD_SET_ID")
    private GlPeriodSet periodSet;
    
    @Column(name = "PERIOD_STATUS")    
    private PeriodStatus status ;
    
    public GlPeriod() {
    }

    public GlPeriod(Integer periodId) {
        this.periodId = periodId;
    }

     
    public GlPeriod(GlPeriodSet periodSet , int year,boolean adjust) {
    
        this.periodSet = periodSet ;
        
        this.periodYear = year ;
        
        this.adjustmentPeriodFlag = adjust ;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public GlPeriodSet getPeriodSet() {
        return periodSet;
    }

    public void setPeriodSet(GlPeriodSet periodSet) {
        this.periodSet = periodSet;
    }

    
    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    
    public Date getYearStartDate() {
        return yearStartDate;
    }

    public void setYearStartDate(Date yearStartDate) {
        this.yearStartDate = yearStartDate;
    }

    public Date getQuarterStartDate() {
        return quarterStartDate;
    }

    public void setQuarterStartDate(Date quarterStartDate) {
        this.quarterStartDate = quarterStartDate;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriodTypeId() {
        return periodTypeId;
    }

    public void setPeriodTypeId(Integer periodTypeId) {
        this.periodTypeId = periodTypeId;
    }

    public PeriodStatus getStatus() {
        return status;
    }

    public void setStatus(PeriodStatus status) {
        this.status = status;
    }

    
    public Integer getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(Integer periodYear) {
        this.periodYear = periodYear;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public Integer getQuarterNum() {
        return quarterNum;
    }

    public void setQuarterNum(Integer quarterNum) {
        this.quarterNum = quarterNum;
    }

    public String getEnteredPeriodName() {
        return enteredPeriodName;
    }

    public void setEnteredPeriodName(String enteredPeriodName) {
        this.enteredPeriodName = enteredPeriodName;
    }

    public boolean isAdjustmentPeriodFlag() {
        return adjustmentPeriodFlag;
    }

    public void setAdjustmentPeriodFlag(boolean adjustmentPeriodFlag) {
        this.adjustmentPeriodFlag = adjustmentPeriodFlag;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodId != null ? periodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlPeriod)) {
            return false;
        }
        GlPeriod other = (GlPeriod) object;
        if ((this.periodId == null && other.periodId != null) || (this.periodId != null && !this.periodId.equals(other.periodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.periods.GlPeriod[ periodId=" + periodId + " ]";
    }
    
}
