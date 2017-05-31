/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.ledger;

import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.enums.PeriodStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GL_PERIOD_STATUSES")
@NamedQueries({
    @NamedQuery(name = "GlPeriodStatus.findAll", query = "SELECT g FROM GlPeriodStatus g")})
public class GlPeriodStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "glperiod")
    @TableGenerator(name = "glperiod",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "GlPeriodStatus",valueColumnName = "CURRENT_VALUE")
    @Column(name = "GL_PERIOD_ID")
    private Integer  glPeriodId;

    @Size(max = 150)
    @Column(name = "PERIOD_NAME")
    private String periodName;
    
    @Column(name = "START_DATE")
    private LocalDate startDate;
  
    @Column(name = "END_DATE")
    private LocalDate endDate;
  
    @Column(name = "PERIOD_NUMBER")
    private Integer periodNum;
    
    @Column(name = "PERIOD_YEAR")
    private Integer periodYear;
    
    @ManyToOne
    @JoinColumn(name = "PERIOD_SET_ID")
    private GlPeriodSet periodSet;
    
    @Column(name = "GL_PERIOD_STATUS")
    private PeriodStatus periodStatus = PeriodStatus.NOT_OPENED;
    
    @OneToOne
    @JoinColumn(name="DEFAULT_BATCH_ID")
    private GlJeBatch defaultBatch ;
    
    @Column(name = "ADJUSTMENT_PERIOD_FLAG")
    private boolean adjustmentPeriodFlag;
    
    @Enumerated(EnumType.STRING)
    @Column(name="FA_PERIOD_STATUS")
    private PeriodStatus faPeriodStatus = PeriodStatus.NOT_OPENED;
     
    @Column(name = "FA_PRORATE_DATE")
    private LocalDate faProrateDate;
    
    
    @Transient
    private String journalHeaderIds ;
    
    public GlPeriodStatus() {
    }
    
    public GlPeriodStatus(GlPeriodSet periodSet , int year,boolean adjust) {
    
        this.periodSet = periodSet ;
        
        this.periodYear = year ;
        
        this.adjustmentPeriodFlag = adjust ;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getGlPeriodId() {
        return glPeriodId;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setGlPeriodId(Integer glPeriodId) {
        this.glPeriodId = glPeriodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Integer getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(Integer periodYear) {
        this.periodYear = periodYear;
    }

    public GlPeriodSet getPeriodSet() {
        return periodSet;
    }

    public void setPeriodSet(GlPeriodSet periodSet) {
        this.periodSet = periodSet;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }
    
    
    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    
    public GlJeBatch getDefaultBatch() {
        return defaultBatch;
    }

    public void setDefaultBatch(GlJeBatch defaultBatch) {
        this.defaultBatch = defaultBatch;
    }

    
    public PeriodStatus getPeriodStatus() {
        return periodStatus;
    }

    public void setPeriodStatus(PeriodStatus periodStatus) {
        this.periodStatus = periodStatus;
    }

    public String getJournalHeaderIds() {
        return journalHeaderIds;
    }

    public void setJournalHeaderIds(String journalHeaderIds) {
        this.journalHeaderIds = journalHeaderIds;
    }


    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (glPeriodId != null ? glPeriodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlPeriodStatus)) {
            return false;
        }
        GlPeriodStatus other = (GlPeriodStatus) object;
        if ((this.glPeriodId == null && other.glPeriodId != null) || (this.glPeriodId != null && !this.glPeriodId.equals(other.glPeriodId))) {
            return false;
        }
        return true;
    }

    public boolean isAdjustmentPeriodFlag() {
        return adjustmentPeriodFlag;
    }

    public void setAdjustmentPeriodFlag(boolean adjustmentPeriodFlag) {
        this.adjustmentPeriodFlag = adjustmentPeriodFlag;
    }

    public PeriodStatus getFaPeriodStatus() {
        return faPeriodStatus;
    }

    public void setFaPeriodStatus(PeriodStatus faPeriodStatus) {
        this.faPeriodStatus = faPeriodStatus;
    }

    public LocalDate getFaProrateDate() {
        return faProrateDate;
    }

    public void setFaProrateDate(LocalDate faProrateDate) {
        this.faProrateDate = faProrateDate;
    }
    
    
    
    @Override
    public String toString() {
        return "com.bridge.entities.ledger.GlPeriodStatus[ glPeriodId=" + glPeriodId + " ]";
    }
    
   
    
    
    @PrePersist
    private void onPersist()
    {
        setFaProrateDate(startDate);
    }    

    
    
}
