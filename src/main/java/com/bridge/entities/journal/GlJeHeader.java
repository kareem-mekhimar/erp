/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.journal;

import com.bridge.enums.JournalBatchApproveStatus;
import com.bridge.enums.JournalBatchPostStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "GL_JE_HEADERS")
@NamedQueries({
    @NamedQuery(name = "GlJeHeader.findAll", query = "SELECT g FROM GlJeHeader g")})
public class GlJeHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jeHeader")
    @TableGenerator(name = "jeHeader", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "GlJeHeader", valueColumnName = "CURRENT_VALUE")
    @Column(name = "JE_HEADER_ID")
    private Integer jeHeaderId;
    
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LEDGER_ID")
    private Integer ledgerId;
    @Size(max = 25)
    @Column(name = "JE_CATEGORY")
    private String jeCategory;
    @Size(max = 25)
    @Column(name = "JE_SOURCE")
    private String jeSource;
    @Size(max = 15)
    @Column(name = "PERIOD_NAME")
    private String periodName;
    @Size(max = 100)
    @Column(name = "JE_HEADER_NAME")
    private String jeHeaderName;
    @Size(max = 15)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "ACCRUAL_REV_FLAG")
    private Integer accrualRevFlag;
    @Column(name = "MULTI_BAL_SEG_FLAG")
    private Integer multiBalSegFlag;
    @Column(name = "ACTUAL_FLAG")
    private Integer actualFlag;
    @Column(name = "DEFAULT_EFFECTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date defaultEffectiveDate;
    @Column(name = "TAX_STATUS_CODE")
    private Integer taxStatusCode;
    @Column(name = "CONVERSION_FLAG")
    private Integer conversionFlag;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "ENCUMBRANCE_TYPE_ID")
    private Integer encumbranceTypeId;
    @Column(name = "BUDGET_VERSION_ID")
    private Integer budgetVersionId;
    @Column(name = "BALANCED_JE_FLAG")
    private Integer balancedJeFlag;
    @Size(max = 25)
    @Column(name = "BALANCING_SEGMENT_VALUE")
    private String balancingSegmentValue;

    @Column(name = "FROM_RECURRING_HEADER_ID")
    private Integer fromRecurringHeaderId;
    @Size(max = 30)
    @Column(name = "UNIQUE_DATE")
    private String uniqueDate;
    @Column(name = "EARLIEST_POSTABLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earliestPostableDate;
    @Column(name = "POSTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "ACCRUAL_REV_EFFECTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accrualRevEffectiveDate;
    @Size(max = 15)
    @Column(name = "ACCRUAL_REV_PERIOD_NAME")
    private String accrualRevPeriodName;
    @Column(name = "ACCRUAL_REV_STATUS")
    private Integer accrualRevStatus;
    @Column(name = "ACCRUAL_REV_JE_HEADER_ID")
    private Integer accrualRevJeHeaderId;
    @Column(name = "ACCRUAL_REV_CHANGE_SIGN_FLAG")
    private Integer accrualRevChangeSignFlag;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CONTROL_TOTAL")
    private Integer controlTotal;
    
    @Column(name = "RUNNING_TOTAL_DR")
    private BigDecimal runningTotalDr = BigDecimal.ZERO;
    
    @Column(name = "RUNNING_TOTAL_CR")
    private BigDecimal runningTotalCr = BigDecimal.ZERO;
    
    @Column(name = "RUNNING_TOTAL_ACCOUNTED_DR")
    private Integer runningTotalAccountedDr;
    @Column(name = "RUNNING_TOTAL_ACCOUNTED_CR")
    private Integer runningTotalAccountedCr;
    @Column(name = "CURRENCY_CONVERSION_RATE")
    private Integer currencyConversionRate;
    @Size(max = 30)
    @Column(name = "CURRENCY_CONVERSION_TYPE")
    private String currencyConversionType;
    @Column(name = "CURRENCY_CONVERSION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currencyConversionDate;
    @Size(max = 80)
    @Column(name = "EXTERNAL_REFERENCE")
    private String externalReference;
    @Column(name = "PARENT_JE_HEADER_ID")
    private Integer parentJeHeaderId;

    @Size(max = 25)
    @Column(name = "ORIGINATING_BAL_SEG_VALUE")
    private String originatingBalSegValue;
    @Column(name = "INTERCOMPANY_MODE")
    private Integer intercompanyMode;
    @Size(max = 25)
    @Column(name = "DR_BAL_SEG_VALUE")
    private String drBalSegValue;
    @Size(max = 25)
    @Column(name = "CR_BAL_SEG_VALUE")
    private String crBalSegValue;
    @Size(max = 30)
    @Column(name = "USSGL_TRANSACTION_CODE")
    private String ussglTransactionCode;
    @Column(name = "DOC_SEQUENCE_ID")
    private Integer docSequenceId;
    @Column(name = "DOC_SEQUENCE_VALUE")
    private Integer docSequenceValue;
    @Size(max = 30)
    @Column(name = "JGZZ_RECON_CONTEXT")
    private String jgzzReconContext;
    @Size(max = 240)
    @Column(name = "JGZZ_RECON_REF")
    private String jgzzReconRef;
    @Column(name = "REFERENCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date referenceDate;
    @Column(name = "LOCAL_DOC_SEQUENCE_ID")
    private Integer localDocSequenceId;
    @Column(name = "LOCAL_DOC_SEQUENCE_VALUE")
    private Integer localDocSequenceValue;
    @Column(name = "DISPLAY_ALC_JOURNAL_FLAG")
    private Integer displayAlcJournalFlag;
    @Column(name = "JE_FROM_SLA_FLAG")
    private Integer jeFromSlaFlag;
    @Column(name = "POSTING_ACCT_SEQ_VERSION_ID")
    private Integer postingAcctSeqVersionId;
    @Column(name = "POSTING_ACCT_SEQ_ASSIGN_ID")
    private Integer postingAcctSeqAssignId;
    @Column(name = "POSTING_ACCT_SEQ_VALUE")
    private Integer postingAcctSeqValue;
    @Column(name = "CLOSE_ACCT_SEQ_VERSION_ID")
    private Integer closeAcctSeqVersionId;
    @Column(name = "CLOSE_ACCT_SEQ_ASSIGN_ID")
    private Integer closeAcctSeqAssignId;
    @Column(name = "CLOSE_ACCT_SEQ_VALUE")
    private Integer closeAcctSeqValue;
    
    @Column(name = "POSTING_STATUS_ID")
    private JournalBatchPostStatus postStatus = JournalBatchPostStatus.NOT_POSTED;
    
    @Column(name = "APPROVAL_STATUS_ID")
    private JournalBatchApproveStatus approveStatus = JournalBatchApproveStatus.NOT_APPROVED ;
    
    @Column(name = "CURRENCY_ID")
    private Integer currencyId;

    @ManyToOne
    @JoinColumn(name = "JE_BATCH_ID")
    private GlJeBatch batch ;
    
    @Column(name = "DISTRIBUTED_FLAG")
    private boolean distributedFlag ;
    
    @OneToMany(mappedBy = "header",cascade = CascadeType.ALL)
    private List<GlJeLine> lines ;
    
    @Column(name = "LINES_ARE_BALANCED")
    private boolean balanced;
    
    @Column(name = "LINES_HAVE_VALUES")
    private boolean valuesFilled ;
    
    @Column(name = "REVERSED_JE_HEADER_ID")
    private Integer reversedJeHeaderId;
    
    @Column(name = "REVERSED_FLAG")
    private boolean reversedFlag ;
    
    @Column(name="CREDIT_CHECKED")
    private boolean atLeastCreditCheck ;
    
    public GlJeHeader() {
    }

    public GlJeHeader(String name,BigDecimal amount)
    {
       this.jeHeaderName = name;
               
       balanced = true; 
       
       valuesFilled = true ;
       
       this.runningTotalCr = amount ;
       
       this.runningTotalDr = amount ;
    }
    
    public GlJeHeader(Integer jeHeaderId) {
        this.jeHeaderId = jeHeaderId;
    }

    public Integer getJeHeaderId() {
        return jeHeaderId;
    }

    public void setJeHeaderId(Integer jeHeaderId) {
        this.jeHeaderId = jeHeaderId;
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

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getJeCategory() {
        return jeCategory;
    }

    public void setJeCategory(String jeCategory) {
        this.jeCategory = jeCategory;
    }

    public String getJeSource() {
        return jeSource;
    }

    public void setJeSource(String jeSource) {
        this.jeSource = jeSource;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getJeHeaderName() {
        return jeHeaderName;
    }

    public void setJeHeaderName(String jeHeaderName) {
        this.jeHeaderName = jeHeaderName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getStatus() {
        return status;
    }

    public boolean isDistributedFlag() {
        return distributedFlag;
    }

    public void setDistributedFlag(boolean distributedFlag) {
        this.distributedFlag = distributedFlag;
    }
    
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isReversedFlag() {
        return reversedFlag;
    }

    public void setReversedFlag(boolean reversedFlag) {
        this.reversedFlag = reversedFlag;
    }

    
    public Integer getAccrualRevFlag() {
        return accrualRevFlag;
    }

    public void setAccrualRevFlag(Integer accrualRevFlag) {
        this.accrualRevFlag = accrualRevFlag;
    }

    public Integer getMultiBalSegFlag() {
        return multiBalSegFlag;
    }

    public void setMultiBalSegFlag(Integer multiBalSegFlag) {
        this.multiBalSegFlag = multiBalSegFlag;
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

    public Integer getTaxStatusCode() {
        return taxStatusCode;
    }

    public void setTaxStatusCode(Integer taxStatusCode) {
        this.taxStatusCode = taxStatusCode;
    }

    public Integer getConversionFlag() {
        return conversionFlag;
    }

    public void setConversionFlag(Integer conversionFlag) {
        this.conversionFlag = conversionFlag;
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

    public Integer getEncumbranceTypeId() {
        return encumbranceTypeId;
    }

    public void setEncumbranceTypeId(Integer encumbranceTypeId) {
        this.encumbranceTypeId = encumbranceTypeId;
    }

    public Integer getBudgetVersionId() {
        return budgetVersionId;
    }

    public void setBudgetVersionId(Integer budgetVersionId) {
        this.budgetVersionId = budgetVersionId;
    }

    public Integer getBalancedJeFlag() {
        return balancedJeFlag;
    }

    public void setBalancedJeFlag(Integer balancedJeFlag) {
        this.balancedJeFlag = balancedJeFlag;
    }

    public String getBalancingSegmentValue() {
        return balancingSegmentValue;
    }

    public void setBalancingSegmentValue(String balancingSegmentValue) {
        this.balancingSegmentValue = balancingSegmentValue;
    }

    public GlJeBatch getBatch() {
        return batch;
    }

    public void setBatch(GlJeBatch batch) {
        this.batch = batch;
    }
    
    
    public Integer getFromRecurringHeaderId() {
        return fromRecurringHeaderId;
    }

    public void setFromRecurringHeaderId(Integer fromRecurringHeaderId) {
        this.fromRecurringHeaderId = fromRecurringHeaderId;
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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getAccrualRevEffectiveDate() {
        return accrualRevEffectiveDate;
    }

    public void setAccrualRevEffectiveDate(Date accrualRevEffectiveDate) {
        this.accrualRevEffectiveDate = accrualRevEffectiveDate;
    }

    public String getAccrualRevPeriodName() {
        return accrualRevPeriodName;
    }

    public void setAccrualRevPeriodName(String accrualRevPeriodName) {
        this.accrualRevPeriodName = accrualRevPeriodName;
    }

    public Integer getAccrualRevStatus() {
        return accrualRevStatus;
    }

    public void setAccrualRevStatus(Integer accrualRevStatus) {
        this.accrualRevStatus = accrualRevStatus;
    }

    public Integer getAccrualRevJeHeaderId() {
        return accrualRevJeHeaderId;
    }

    public void setAccrualRevJeHeaderId(Integer accrualRevJeHeaderId) {
        this.accrualRevJeHeaderId = accrualRevJeHeaderId;
    }

    public Integer getAccrualRevChangeSignFlag() {
        return accrualRevChangeSignFlag;
    }

    public void setAccrualRevChangeSignFlag(Integer accrualRevChangeSignFlag) {
        this.accrualRevChangeSignFlag = accrualRevChangeSignFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getControlTotal() {
        return controlTotal;
    }

    public void setControlTotal(Integer controlTotal) {
        this.controlTotal = controlTotal;
    }

    public BigDecimal getRunningTotalCr() {
        return runningTotalCr;
    }

    public void setRunningTotalCr(BigDecimal runningTotalCr) {
        this.runningTotalCr = runningTotalCr;
    }

    public BigDecimal getRunningTotalDr() {
        return runningTotalDr;
    }

    public void setRunningTotalDr(BigDecimal runningTotalDr) {
        this.runningTotalDr = runningTotalDr;
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

    public Integer getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(Integer currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public String getCurrencyConversionType() {
        return currencyConversionType;
    }

    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }

    public Date getCurrencyConversionDate() {
        return currencyConversionDate;
    }

    public void setCurrencyConversionDate(Date currencyConversionDate) {
        this.currencyConversionDate = currencyConversionDate;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public Integer getParentJeHeaderId() {
        return parentJeHeaderId;
    }

    public void setParentJeHeaderId(Integer parentJeHeaderId) {
        this.parentJeHeaderId = parentJeHeaderId;
    }

    public Integer getReversedJeHeaderId() {
        return reversedJeHeaderId;
    }

    public void setReversedJeHeaderId(Integer reversedJeHeaderId) {
        this.reversedJeHeaderId = reversedJeHeaderId;
    }

    public String getOriginatingBalSegValue() {
        return originatingBalSegValue;
    }

    public void setOriginatingBalSegValue(String originatingBalSegValue) {
        this.originatingBalSegValue = originatingBalSegValue;
    }

    public Integer getIntercompanyMode() {
        return intercompanyMode;
    }

    public void setIntercompanyMode(Integer intercompanyMode) {
        this.intercompanyMode = intercompanyMode;
    }

    public String getDrBalSegValue() {
        return drBalSegValue;
    }

    public void setDrBalSegValue(String drBalSegValue) {
        this.drBalSegValue = drBalSegValue;
    }

    public String getCrBalSegValue() {
        return crBalSegValue;
    }

    public void setCrBalSegValue(String crBalSegValue) {
        this.crBalSegValue = crBalSegValue;
    }

    public String getUssglTransactionCode() {
        return ussglTransactionCode;
    }

    public void setUssglTransactionCode(String ussglTransactionCode) {
        this.ussglTransactionCode = ussglTransactionCode;
    }

    public Integer getDocSequenceId() {
        return docSequenceId;
    }

    public void setDocSequenceId(Integer docSequenceId) {
        this.docSequenceId = docSequenceId;
    }

    public Integer getDocSequenceValue() {
        return docSequenceValue;
    }

    public void setDocSequenceValue(Integer docSequenceValue) {
        this.docSequenceValue = docSequenceValue;
    }

    public String getJgzzReconContext() {
        return jgzzReconContext;
    }

    public void setJgzzReconContext(String jgzzReconContext) {
        this.jgzzReconContext = jgzzReconContext;
    }

    public String getJgzzReconRef() {
        return jgzzReconRef;
    }

    public void setJgzzReconRef(String jgzzReconRef) {
        this.jgzzReconRef = jgzzReconRef;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Integer getLocalDocSequenceId() {
        return localDocSequenceId;
    }

    public void setLocalDocSequenceId(Integer localDocSequenceId) {
        this.localDocSequenceId = localDocSequenceId;
    }

    public Integer getLocalDocSequenceValue() {
        return localDocSequenceValue;
    }

    public void setLocalDocSequenceValue(Integer localDocSequenceValue) {
        this.localDocSequenceValue = localDocSequenceValue;
    }

    public Integer getDisplayAlcJournalFlag() {
        return displayAlcJournalFlag;
    }

    public void setDisplayAlcJournalFlag(Integer displayAlcJournalFlag) {
        this.displayAlcJournalFlag = displayAlcJournalFlag;
    }

    public Integer getJeFromSlaFlag() {
        return jeFromSlaFlag;
    }

    public void setJeFromSlaFlag(Integer jeFromSlaFlag) {
        this.jeFromSlaFlag = jeFromSlaFlag;
    }

    public Integer getPostingAcctSeqVersionId() {
        return postingAcctSeqVersionId;
    }

    public void setPostingAcctSeqVersionId(Integer postingAcctSeqVersionId) {
        this.postingAcctSeqVersionId = postingAcctSeqVersionId;
    }

    public Integer getPostingAcctSeqAssignId() {
        return postingAcctSeqAssignId;
    }

    public void setPostingAcctSeqAssignId(Integer postingAcctSeqAssignId) {
        this.postingAcctSeqAssignId = postingAcctSeqAssignId;
    }

    public Integer getPostingAcctSeqValue() {
        return postingAcctSeqValue;
    }

    public void setPostingAcctSeqValue(Integer postingAcctSeqValue) {
        this.postingAcctSeqValue = postingAcctSeqValue;
    }

    public Integer getCloseAcctSeqVersionId() {
        return closeAcctSeqVersionId;
    }

    public void setCloseAcctSeqVersionId(Integer closeAcctSeqVersionId) {
        this.closeAcctSeqVersionId = closeAcctSeqVersionId;
    }

    public Integer getCloseAcctSeqAssignId() {
        return closeAcctSeqAssignId;
    }

    public void setCloseAcctSeqAssignId(Integer closeAcctSeqAssignId) {
        this.closeAcctSeqAssignId = closeAcctSeqAssignId;
    }

    public Integer getCloseAcctSeqValue() {
        return closeAcctSeqValue;
    }

    public void setCloseAcctSeqValue(Integer closeAcctSeqValue) {
        this.closeAcctSeqValue = closeAcctSeqValue;
    }

    public JournalBatchApproveStatus getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(JournalBatchApproveStatus approveStatus) {
        this.approveStatus = approveStatus;
    }

    public JournalBatchPostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(JournalBatchPostStatus postStatus) {
        this.postStatus = postStatus;
    }

    
    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jeHeaderId != null ? jeHeaderId.hashCode() : 0);
        return hash;
    }

    public List<GlJeLine> getLines() {
        return lines;
    }

    public void setLines(List<GlJeLine> lines) {
        this.lines = lines;
    }

    public boolean isValuesFilled() {
        return valuesFilled;
    }

    public void setValuesFilled(boolean valuesFilled) {
        this.valuesFilled = valuesFilled;
    }
    
    
    public boolean isBalanced() {
        return balanced;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }

    public boolean isAtLeastCreditCheck() {
        return atLeastCreditCheck;
    }

    public void setAtLeastCreditCheck(boolean atLeastCreditCheck) {
        this.atLeastCreditCheck = atLeastCreditCheck;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlJeHeader)) {
            return false;
        }
        GlJeHeader other = (GlJeHeader) object;
        if ((this.jeHeaderId == null && other.jeHeaderId != null) || (this.jeHeaderId != null && !this.jeHeaderId.equals(other.jeHeaderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.journal.GlJeHeader[ jeHeaderId=" + jeHeaderId + " ]";
    }
    
}
