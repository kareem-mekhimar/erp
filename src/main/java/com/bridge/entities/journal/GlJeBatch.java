/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.journal;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.periods.GlPeriod;
import com.bridge.enums.JournalBatchApproveStatus;
import com.bridge.enums.JournalBatchPostStatus;
import com.bridge.enums.JournalBatchType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "GL_JE_BATCHES")
@NamedQueries({
    @NamedQuery(name = "GlJeBatch.findAll", query = "SELECT g FROM GlJeBatch g")})
public class GlJeBatch implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jeBatch")
    @TableGenerator(name = "jeBatch", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "GlJeBatch", valueColumnName = "CURRENT_VALUE")
    @Column(name = "JE_BATCH_ID")
    private Integer jeBatchId;
    @Size(max = 100)
    @Column(name = "BATCH_NAME")
    private String batchName;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "JE_TYPE")
    private Integer jeType;
    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    @Column(name = "CHART_OF_ACCOUNTS_ID")
    private Integer chartOfAccountsId;
    @Column(name = "PERIOD_SET_ID")
    private Integer periodSetId;
    @Column(name = "APPROVAL_STATUS_ID")
    private JournalBatchApproveStatus approveStatus = JournalBatchApproveStatus.NOT_APPROVED;
    
    @Column(name = "FUNDS_STATUS_ID")
    private Integer fundsStatusId;
    
    @Column(name = "POSTING_STATUS_ID")
    private JournalBatchPostStatus batchPostStatus = JournalBatchPostStatus.NOT_POSTED ;
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "POSTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "POSTED_BY")
    private Integer postedBy;
    @Column(name = "ACTUAL_FLAG")
    private Integer actualFlag;
    @Column(name = "DEFAULT_EFFECTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date defaultEffectiveDate;
    @Column(name = "AVERAGE_JOURNAL_FLAG")
    private Integer averageJournalFlag;
    @Column(name = "BUDGETARY_CONTROL_STATUS")
    private Integer budgetaryControlStatus;
    @Column(name = "STATUS_RESET_FLAG")
    private Integer statusResetFlag;
    @Size(max = 15)
    @Column(name = "DEFAULT_PERIOD_NAME")
    private String defaultPeriodName;
    @Size(max = 30)
    @Column(name = "UNIQUE_DATE")
    private String uniqueDate;
    @Column(name = "EARLIEST_POSTABLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earliestPostableDate;
    @Column(name = "CONTROL_TOTAL")
    private Integer controlTotal;
    @Column(name = "RUNNING_TOTAL_DR")
    private Integer runningTotalDr;
    @Column(name = "RUNNING_TOTAL_CR")
    private Integer runningTotalCr;
    @Column(name = "RUNNING_TOTAL_ACCOUNTED_DR")
    private Integer runningTotalAccountedDr;
    @Column(name = "RUNNING_TOTAL_ACCOUNTED_CR")
    private Integer runningTotalAccountedCr;
    @Column(name = "PARENT_JE_BATCH_ID")
    private Integer parentJeBatchId;
    @Column(name = "UNRESERVATION_PACKET_ID")
    private Integer unreservationPacketId;
    @Column(name = "PACKET_ID")
    private Integer packetId;
    @Size(max = 30)
    @Column(name = "USSGL_TRANSACTION_CODE")
    private String ussglTransactionCode;
    @Size(max = 150)
    @Column(name = "CONTEXT2")
    private String context2;
    @Column(name = "POSTING_RUN_ID")
    private Integer postingRunId;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Size(max = 15)
    @Column(name = "ACCOUNTED_PERIOD_TYPE")
    private String accountedPeriodType;
    @Column(name = "GROUP_ID")
    private Integer groupId;
    @Column(name = "APPROVER_EMPLOYEE_ID")
    private Long approverEmployeeId;
    
    @OneToOne
    @JoinColumn(name = "GL_PERIOD_ID")
    private GlPeriodStatus period;
    
    @ManyToOne
    @JoinColumn(name = "GL_ID")
    private GlLedger ledger;
  
    @OneToMany(mappedBy = "batch")
    private List<GlJeHeader> headers ;
    
    @Column(name = "JE_BATCH_TYPE")
    private JournalBatchType batchType = JournalBatchType.NORMAL ;

    @Column(name = "RUN_AUTOMATICALLY")
    private boolean runAutomatic ;
    
    @Column(name = "RUN_MANUALLY")
    private boolean runManual = true;
    
    @Column(name = "RUN_ONCE_PER_PERIOD")
    private boolean runOncePeriod  ;
    
    @Column(name="DEFAULT_BATCH")
    private boolean defaultFlag ;
    
    public GlJeBatch() {
    }

    public GlJeBatch(GlPeriodStatus periodStatus)
    {
       this.batchName = periodStatus.getPeriodName()+" - Default" ;
       
       this.period = periodStatus ;
       
       this.defaultFlag = true ;
    }
    
    public GlJeBatch(Integer jeBatchId) {
        this.jeBatchId = jeBatchId;
    }

    public Integer getJeBatchId() {
        return jeBatchId;
    }

    public void setJeBatchId(Integer jeBatchId) {
        this.jeBatchId = jeBatchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public boolean isRunAutomatic() {
        return runAutomatic;
    }

    public boolean isRunManual() {
        return runManual;
    }

    public void setRunAutomatic(boolean runAutomatic) {
        this.runAutomatic = runAutomatic;
    }

    public void setRunManual(boolean runManual) {
        this.runManual = runManual;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJeType() {
        return jeType;
    }

    public void setRunOncePeriod(boolean runOncePeriod) {
        this.runOncePeriod = runOncePeriod;
    }

    public boolean isRunOncePeriod() {
        return runOncePeriod;
    }

    
    public void setJeType(Integer jeType) {
        this.jeType = jeType;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public Integer getChartOfAccountsId() {
        return chartOfAccountsId;
    }

    public void setChartOfAccountsId(Integer chartOfAccountsId) {
        this.chartOfAccountsId = chartOfAccountsId;
    }

    public Integer getPeriodSetId() {
        return periodSetId;
    }

    public void setPeriodSetId(Integer periodSetId) {
        this.periodSetId = periodSetId;
    }

    public JournalBatchApproveStatus getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(JournalBatchApproveStatus approveStatus) {
        this.approveStatus = approveStatus;
    }

    public JournalBatchPostStatus getBatchPostStatus() {
        return batchPostStatus;
    }

    public void setBatchPostStatus(JournalBatchPostStatus batchPostStatus) {
        this.batchPostStatus = batchPostStatus;
    }

    
    public Integer getFundsStatusId() {
        return fundsStatusId;
    }

    public void setFundsStatusId(Integer fundsStatusId) {
        this.fundsStatusId = fundsStatusId;
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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getActualFlag() {
        return actualFlag;
    }

    public void setActualFlag(Integer actualFlag) {
        this.actualFlag = actualFlag;
    }

    public Date getDefaultEffectiveDate() {
        return defaultEffectiveDate;
    }

    public void setDefaultEffectiveDate(Date defaultEffectiveDate) {
        this.defaultEffectiveDate = defaultEffectiveDate;
    }

    public Integer getAverageJournalFlag() {
        return averageJournalFlag;
    }

    public void setAverageJournalFlag(Integer averageJournalFlag) {
        this.averageJournalFlag = averageJournalFlag;
    }

    public Integer getBudgetaryControlStatus() {
        return budgetaryControlStatus;
    }

    public void setBudgetaryControlStatus(Integer budgetaryControlStatus) {
        this.budgetaryControlStatus = budgetaryControlStatus;
    }

    public Integer getStatusResetFlag() {
        return statusResetFlag;
    }

    public void setStatusResetFlag(Integer statusResetFlag) {
        this.statusResetFlag = statusResetFlag;
    }

    public String getDefaultPeriodName() {
        return defaultPeriodName;
    }

    public void setDefaultPeriodName(String defaultPeriodName) {
        this.defaultPeriodName = defaultPeriodName;
    }

    public String getUniqueDate() {
        return uniqueDate;
    }

    public void setUniqueDate(String uniqueDate) {
        this.uniqueDate = uniqueDate;
    }

    public Date getEarliestPostableDate() {
        return earliestPostableDate;
    }

    public void setEarliestPostableDate(Date earliestPostableDate) {
        this.earliestPostableDate = earliestPostableDate;
    }

    public Integer getControlTotal() {
        return controlTotal;
    }

    public void setControlTotal(Integer controlTotal) {
        this.controlTotal = controlTotal;
    }

    public Integer getRunningTotalDr() {
        return runningTotalDr;
    }

    public void setRunningTotalDr(Integer runningTotalDr) {
        this.runningTotalDr = runningTotalDr;
    }

    public Integer getRunningTotalCr() {
        return runningTotalCr;
    }

    public void setRunningTotalCr(Integer runningTotalCr) {
        this.runningTotalCr = runningTotalCr;
    }

    public Integer getRunningTotalAccountedDr() {
        return runningTotalAccountedDr;
    }

    public void setRunningTotalAccountedDr(Integer runningTotalAccountedDr) {
        this.runningTotalAccountedDr = runningTotalAccountedDr;
    }

    public Integer getRunningTotalAccountedCr() {
        return runningTotalAccountedCr;
    }

    public void setRunningTotalAccountedCr(Integer runningTotalAccountedCr) {
        this.runningTotalAccountedCr = runningTotalAccountedCr;
    }

    public Integer getParentJeBatchId() {
        return parentJeBatchId;
    }

    public void setParentJeBatchId(Integer parentJeBatchId) {
        this.parentJeBatchId = parentJeBatchId;
    }

    public Integer getUnreservationPacketId() {
        return unreservationPacketId;
    }

    public void setUnreservationPacketId(Integer unreservationPacketId) {
        this.unreservationPacketId = unreservationPacketId;
    }

    public Integer getPacketId() {
        return packetId;
    }

    public void setPacketId(Integer packetId) {
        this.packetId = packetId;
    }

    public String getUssglTransactionCode() {
        return ussglTransactionCode;
    }

    public void setUssglTransactionCode(String ussglTransactionCode) {
        this.ussglTransactionCode = ussglTransactionCode;
    }

    public String getContext2() {
        return context2;
    }

    public void setContext2(String context2) {
        this.context2 = context2;
    }

    public Integer getPostingRunId() {
        return postingRunId;
    }

    public void setPostingRunId(Integer postingRunId) {
        this.postingRunId = postingRunId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getAccountedPeriodType() {
        return accountedPeriodType;
    }

    public void setAccountedPeriodType(String accountedPeriodType) {
        this.accountedPeriodType = accountedPeriodType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Long getApproverEmployeeId() {
        return approverEmployeeId;
    }

    public void setApproverEmployeeId(Long approverEmployeeId) {
        this.approverEmployeeId = approverEmployeeId;
    }
    
    public GlLedger getLedger() {
        return ledger;
    }

    public void setLedger(GlLedger ledger) {
        this.ledger = ledger;
    }

    public GlPeriodStatus getPeriod() {
        return period;
    }

    public void setPeriod(GlPeriodStatus period) {
        this.period = period;
    }
    
    
    public JournalBatchType getBatchType() {
        return batchType;
    }

    public void setBatchType(JournalBatchType batchType) {
        this.batchType = batchType;
    }

    public List<GlJeHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<GlJeHeader> headers) {
        this.headers = headers;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jeBatchId != null ? jeBatchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlJeBatch)) {
            return false;
        }
        GlJeBatch other = (GlJeBatch) object;
        if ((this.jeBatchId == null && other.jeBatchId != null) || (this.jeBatchId != null && !this.jeBatchId.equals(other.jeBatchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.journal.GlJeBatch[ jeBatchId=" + jeBatchId + " ]";
    }
    
}
