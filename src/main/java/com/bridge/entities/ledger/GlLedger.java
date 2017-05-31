/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.ledger;

import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.enums.AccountingMethod;
import com.bridge.enums.GlSetupStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "GL_LEDGERS")
@NamedQueries({
    @NamedQuery(name = "GlLedger.findAll", query = "SELECT g FROM GlLedger g")})
public class GlLedger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "gl")
    @TableGenerator(name = "gl",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "GlLedger",valueColumnName = "CURRENT_VALUE")
    @NotNull
    @Column(name = "LEDGER_ID")
    private Integer ledgerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
 
    @Size(min = 1, max = 100)
    @Column(name = "SHORT_NAME")
    private String shortName;
    
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    

    @Size(min = 1, max = 30)
    @Column(name = "LEDGER_CATEGORY_CODE")
    private String ledgerCategoryCode;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "COMPLETION_STATUS_CODE")
    private GlSetupStatus completionStatusCode = GlSetupStatus.IN_PROGRESS;
    
    @OneToOne
    @JoinColumn(name = "CHART_OF_ACCOUNTS_ID")
    private CoaStructure coa ;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency;
    
    @OneToOne
    @JoinColumn(name = "PERIOD_SET_ID")
    private GlPeriodSet periodSet;
    
    @Size(min = 1, max = 15)
    @Column(name = "ACCOUNTED_PERIOD_TYPE")
    private String accountedPeriodType;
    

    @Size(min = 1, max = 15)
    @Column(name = "FIRST_LEDGER_PERIOD_NAME")
    private String firstLedgerPeriodName;
    
    @Basic(optional = false)
    @Column(name = "RET_EARN_CODE_COMBINATION_ID")
    private long retEarnCodeCombinationId;

    @Size(min = 1, max = 1)
    @Column(name = "SUSPENSE_ALLOWED_FLAG")
    private String suspenseAllowedFlag;

    @Size(min = 1, max = 1)
    @Column(name = "ALLOW_INTERCOMPANY_POST_FLAG")
    private String allowIntercompanyPostFlag;

    @Size(min = 1, max = 1)
    @Column(name = "ENABLE_AVERAGE_BALANCES_FLAG")
    private String enableAverageBalancesFlag;
    @Column(name = "CUM_TRANS_CODE_COMBINATION_ID")
    private Integer cumTransCodeCombinationId;
    @Column(name = "RES_ENCUMB_CODE_COMBINATION_ID")
    private Integer resEncumbCodeCombinationId;
    @Column(name = "NET_INCOME_CODE_COMBINATION_ID")
    private Integer netIncomeCodeCombinationId;
    @OneToOne
    @JoinColumn(name = "ROUNDING_CODE_COMBINATION_ID")
    private GlCodeCombination roundingCodeCombinationId;
    @OneToOne
    @JoinColumn(name = "RETAIN_EARNINGS_ACCOUNT_ID")
    private GlCodeCombination retainEarningsAccountId;
    @OneToOne
    @JoinColumn(name = "SUSPENSE_ACCOUNT_ID")
    private GlCodeCombination suspenseAccountId;


    @Size(min = 1, max = 1)
    @Column(name = "ENABLE_BUDGETARY_CONTROL_FLAG")
    private String enableBudgetaryControlFlag;
    @Basic(optional = false)

    @Column(name = "REQUIRE_BUDGET_JOURNALS_FLAG")
    private String requireBudgetJournalsFlag;
    @Basic(optional = false)

    @Size(min = 1, max = 1)
    @Column(name = "ENABLE_JE_APPROVAL_FLAG")
    private String enableJeApprovalFlag;

    @Size(min = 1, max = 1)
    @Column(name = "ENABLE_AUTOMATIC_TAX_FLAG")
    private String enableAutomaticTaxFlag;
    @Basic(optional = false)

    @Size(min = 1, max = 1)
    @Column(name = "CONSOLIDATION_LEDGER_FLAG")
    private String consolidationLedgerFlag;
    @Column(name = "TRANSACTION_CALENDAR_ID")
    private Integer transactionCalendarId;

    @Column(name = "DAILY_TRANSLATION_RATE_TYPE")
    private String dailyTranslationRateType;

    @Size(min = 1, max = 1)
    @Column(name = "AUTOMATICALLY_CREATED_FLAG")
    private String automaticallyCreatedFlag;

    @Size(min = 1, max = 1)
    @Column(name = "BAL_SEG_VALUE_OPTION_CODE")
    private String balSegValueOptionCode;

    @Size(min = 1, max = 25)
    @Column(name = "BAL_SEG_COLUMN_NAME")
    private String balSegColumnName;

    @Size(min = 1, max = 1)
    @Column(name = "MGT_SEG_VALUE_OPTION_CODE")
    private String mgtSegValueOptionCode;
    @Size(max = 25)
    @Column(name = "MGT_SEG_COLUMN_NAME")
    private String mgtSegColumnName;

    @Column(name = "BAL_SEG_VALUE_SET_ID")
    private long balSegValueSetId;
    @Column(name = "MGT_SEG_VALUE_SET_ID")
    private Integer mgtSegValueSetId;
    @Column(name = "IMPLICIT_ACCESS_SET_ID")
    private Integer implicitAccessSetId;
    @Column(name = "CRITERIA_SET_ID")
    private Integer criteriaSetId;

    @Column(name = "FUTURE_ENTERABLE_PERIODS_LIMIT")
    private long futureEnterablePeriodsLimit;

    @Size(min = 1, max = 2000)
    @Column(name = "LEDGER_ATTRIBUTES")
    private String ledgerAttributes;
    @Column(name = "IMPLICIT_LEDGER_SET_ID")
    private Integer implicitLedgerSetId;
    @Size(max = 15)
    @Column(name = "LATEST_OPENED_PERIOD_NAME")
    private String latestOpenedPeriodName;
    @Column(name = "LATEST_ENCUMBRANCE_YEAR")
    private Integer latestEncumbranceYear;
    @Size(max = 30)
    @Column(name = "PERIOD_AVERAGE_RATE_TYPE")
    private String periodAverageRateType;
    @Size(max = 30)
    @Column(name = "PERIOD_END_RATE_TYPE")
    private String periodEndRateType;
    @Size(max = 30)
    @Column(name = "BUDGET_PERIOD_AVG_RATE_TYPE")
    private String budgetPeriodAvgRateType;
    @Size(max = 30)
    @Column(name = "BUDGET_PERIOD_END_RATE_TYPE")
    private String budgetPeriodEndRateType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SLA_ACCOUNTING_METHOD_CODE")
    private AccountingMethod slaAccountingMethodCode;
    
    @Size(max = 1)
    @Column(name = "SLA_ACCOUNTING_METHOD_TYPE")
    private String slaAccountingMethodType;
    
    @Size(max = 15)
    @Column(name = "SLA_DESCRIPTION_LANGUAGE")
    private String slaDescriptionLanguage;
    @Column(name = "SLA_ENTERED_CUR_BAL_SUS_CCID")
    private Integer slaEnteredCurBalSusCcid;
    @Size(max = 1)
    @Column(name = "SLA_SEQUENCING_FLAG")
    private String slaSequencingFlag;
    @Size(max = 1)
    @Column(name = "SLA_BAL_BY_LEDGER_CURR_FLAG")
    private String slaBalByLedgerCurrFlag;
    @Column(name = "SLA_LEDGER_CUR_BAL_SUS_CCID")
    private Integer slaLedgerCurBalSusCcid;
    @Size(max = 1)
    @Column(name = "ENABLE_SECONDARY_TRACK_FLAG")
    private String enableSecondaryTrackFlag;
    @Size(max = 1)
    @Column(name = "ENABLE_REVAL_SS_TRACK_FLAG")
    private String enableRevalSsTrackFlag;
  

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private long lastUpdatedBy;
    

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private long createdBy;
    
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    
    @Size(max = 150)
    @Column(name = "CONTEXT")
    private String context;
    

    @Size(min = 1, max = 1)
    @Column(name = "ENABLE_RECONCILIATION_FLAG")
    private String enableReconciliationFlag;
    
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "CREATE_JE_FLAG")
    private String createJeFlag;
    
    @Size(max = 1)
    @Column(name = "SLA_LEDGER_CASH_BASIS_FLAG")
    private String slaLedgerCashBasisFlag;
    
    @Size(max = 1)
    @Column(name = "COMPLETE_FLAG")
    private String completeFlag;
    
    @Size(max = 1)
    @Column(name = "COMMITMENT_BUDGET_FLAG")
    private String commitmentBudgetFlag;
    
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "NET_CLOSING_BAL_FLAG")
    private String netClosingBalFlag;
    
    @Size(max = 1)
    @Column(name = "AUTOMATE_SEC_JRNL_REV_FLAG")
    private String automateSecJrnlRevFlag;

    @OneToMany(mappedBy = "ledger",cascade = CascadeType.ALL)
    private List<GlLedgerConfigDetail> configDetails ;
    
    @OneToMany(mappedBy = "ledger",cascade = CascadeType.ALL)
    private List<GlReportingCurrency> reportingCurrencys ;
    
    public GlLedger() {
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLedgerCategoryCode() {
        return ledgerCategoryCode;
    }

    public List<GlLedgerConfigDetail> getConfigDetails() {
        return configDetails;
    }

    public List<GlReportingCurrency> getReportingCurrencys() {
        return reportingCurrencys;
    }

    public void setConfigDetails(List<GlLedgerConfigDetail> configDetails) {
        this.configDetails = configDetails;
    }

    public void setReportingCurrencys(List<GlReportingCurrency> reportingCurrencys) {
        this.reportingCurrencys = reportingCurrencys;
    }

    
    public void setLedgerCategoryCode(String ledgerCategoryCode) {
        this.ledgerCategoryCode = ledgerCategoryCode;
    }
    
    public CoaStructure getCoa() {
        return coa;
    }

    public void setCoa(CoaStructure coa) {
        this.coa = coa;
    }

    public GlSetupStatus getCompletionStatusCode() {
        return completionStatusCode;
    }

    public void setCompletionStatusCode(GlSetupStatus completionStatusCode) {
        this.completionStatusCode = completionStatusCode;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public GlPeriodSet getPeriodSet() {
        return periodSet;
    }

    public void setPeriodSet(GlPeriodSet periodSet) {
        this.periodSet = periodSet;
    }

   
    public String getAccountedPeriodType() {
        return accountedPeriodType;
    }

    public void setAccountedPeriodType(String accountedPeriodType) {
        this.accountedPeriodType = accountedPeriodType;
    }

    public String getFirstLedgerPeriodName() {
        return firstLedgerPeriodName;
    }

    public void setFirstLedgerPeriodName(String firstLedgerPeriodName) {
        this.firstLedgerPeriodName = firstLedgerPeriodName;
    }

    public long getRetEarnCodeCombinationId() {
        return retEarnCodeCombinationId;
    }

    public void setRetEarnCodeCombinationId(long retEarnCodeCombinationId) {
        this.retEarnCodeCombinationId = retEarnCodeCombinationId;
    }

    public String getSuspenseAllowedFlag() {
        return suspenseAllowedFlag;
    }

    public void setSuspenseAllowedFlag(String suspenseAllowedFlag) {
        this.suspenseAllowedFlag = suspenseAllowedFlag;
    }

    public String getAllowIntercompanyPostFlag() {
        return allowIntercompanyPostFlag;
    }

    public void setAllowIntercompanyPostFlag(String allowIntercompanyPostFlag) {
        this.allowIntercompanyPostFlag = allowIntercompanyPostFlag;
    }

    public String getEnableAverageBalancesFlag() {
        return enableAverageBalancesFlag;
    }

    public void setEnableAverageBalancesFlag(String enableAverageBalancesFlag) {
        this.enableAverageBalancesFlag = enableAverageBalancesFlag;
    }

    public Integer getCumTransCodeCombinationId() {
        return cumTransCodeCombinationId;
    }

    public void setCumTransCodeCombinationId(Integer cumTransCodeCombinationId) {
        this.cumTransCodeCombinationId = cumTransCodeCombinationId;
    }

    public Integer getResEncumbCodeCombinationId() {
        return resEncumbCodeCombinationId;
    }

    public void setResEncumbCodeCombinationId(Integer resEncumbCodeCombinationId) {
        this.resEncumbCodeCombinationId = resEncumbCodeCombinationId;
    }

    public Integer getNetIncomeCodeCombinationId() {
        return netIncomeCodeCombinationId;
    }

    public void setNetIncomeCodeCombinationId(Integer netIncomeCodeCombinationId) {
        this.netIncomeCodeCombinationId = netIncomeCodeCombinationId;
    }

    public GlCodeCombination getRoundingCodeCombinationId() {
        return roundingCodeCombinationId;
    }

    public void setRoundingCodeCombinationId(GlCodeCombination roundingCodeCombinationId) {
        this.roundingCodeCombinationId = roundingCodeCombinationId;
    }

    public GlCodeCombination getRetainEarningsAccountId() {
        return retainEarningsAccountId;
    }

    public void setRetainEarningsAccountId(GlCodeCombination retainEarningsAccountId) {
        this.retainEarningsAccountId = retainEarningsAccountId;
    }

    public GlCodeCombination getSuspenseAccountId() {
        return suspenseAccountId;
    }

    public void setSuspenseAccountId(GlCodeCombination suspenseAccountId) {
        this.suspenseAccountId = suspenseAccountId;
    }

    
    public String getEnableBudgetaryControlFlag() {
        return enableBudgetaryControlFlag;
    }

    public void setEnableBudgetaryControlFlag(String enableBudgetaryControlFlag) {
        this.enableBudgetaryControlFlag = enableBudgetaryControlFlag;
    }

    public String getRequireBudgetJournalsFlag() {
        return requireBudgetJournalsFlag;
    }

    public void setRequireBudgetJournalsFlag(String requireBudgetJournalsFlag) {
        this.requireBudgetJournalsFlag = requireBudgetJournalsFlag;
    }

    public String getEnableJeApprovalFlag() {
        return enableJeApprovalFlag;
    }

    public void setEnableJeApprovalFlag(String enableJeApprovalFlag) {
        this.enableJeApprovalFlag = enableJeApprovalFlag;
    }

    public String getEnableAutomaticTaxFlag() {
        return enableAutomaticTaxFlag;
    }

    public void setEnableAutomaticTaxFlag(String enableAutomaticTaxFlag) {
        this.enableAutomaticTaxFlag = enableAutomaticTaxFlag;
    }

    public String getConsolidationLedgerFlag() {
        return consolidationLedgerFlag;
    }

    public void setConsolidationLedgerFlag(String consolidationLedgerFlag) {
        this.consolidationLedgerFlag = consolidationLedgerFlag;
    }

    public Integer getTransactionCalendarId() {
        return transactionCalendarId;
    }

    public void setTransactionCalendarId(Integer transactionCalendarId) {
        this.transactionCalendarId = transactionCalendarId;
    }

    public String getDailyTranslationRateType() {
        return dailyTranslationRateType;
    }

    public void setDailyTranslationRateType(String dailyTranslationRateType) {
        this.dailyTranslationRateType = dailyTranslationRateType;
    }

    public String getAutomaticallyCreatedFlag() {
        return automaticallyCreatedFlag;
    }

    public void setAutomaticallyCreatedFlag(String automaticallyCreatedFlag) {
        this.automaticallyCreatedFlag = automaticallyCreatedFlag;
    }

    public String getBalSegValueOptionCode() {
        return balSegValueOptionCode;
    }

    public void setBalSegValueOptionCode(String balSegValueOptionCode) {
        this.balSegValueOptionCode = balSegValueOptionCode;
    }

    public String getBalSegColumnName() {
        return balSegColumnName;
    }

    public void setBalSegColumnName(String balSegColumnName) {
        this.balSegColumnName = balSegColumnName;
    }

    public String getMgtSegValueOptionCode() {
        return mgtSegValueOptionCode;
    }

    public void setMgtSegValueOptionCode(String mgtSegValueOptionCode) {
        this.mgtSegValueOptionCode = mgtSegValueOptionCode;
    }

    public String getMgtSegColumnName() {
        return mgtSegColumnName;
    }

    public void setMgtSegColumnName(String mgtSegColumnName) {
        this.mgtSegColumnName = mgtSegColumnName;
    }

    public long getBalSegValueSetId() {
        return balSegValueSetId;
    }

    public void setBalSegValueSetId(long balSegValueSetId) {
        this.balSegValueSetId = balSegValueSetId;
    }

    public Integer getMgtSegValueSetId() {
        return mgtSegValueSetId;
    }

    public void setMgtSegValueSetId(Integer mgtSegValueSetId) {
        this.mgtSegValueSetId = mgtSegValueSetId;
    }

    public Integer getImplicitAccessSetId() {
        return implicitAccessSetId;
    }

    public void setImplicitAccessSetId(Integer implicitAccessSetId) {
        this.implicitAccessSetId = implicitAccessSetId;
    }

    public Integer getCriteriaSetId() {
        return criteriaSetId;
    }

    public void setCriteriaSetId(Integer criteriaSetId) {
        this.criteriaSetId = criteriaSetId;
    }

    public long getFutureEnterablePeriodsLimit() {
        return futureEnterablePeriodsLimit;
    }

    public void setFutureEnterablePeriodsLimit(long futureEnterablePeriodsLimit) {
        this.futureEnterablePeriodsLimit = futureEnterablePeriodsLimit;
    }

    public String getLedgerAttributes() {
        return ledgerAttributes;
    }

    public void setLedgerAttributes(String ledgerAttributes) {
        this.ledgerAttributes = ledgerAttributes;
    }

    public Integer getImplicitLedgerSetId() {
        return implicitLedgerSetId;
    }

    public void setImplicitLedgerSetId(Integer implicitLedgerSetId) {
        this.implicitLedgerSetId = implicitLedgerSetId;
    }

    public String getLatestOpenedPeriodName() {
        return latestOpenedPeriodName;
    }

    public void setLatestOpenedPeriodName(String latestOpenedPeriodName) {
        this.latestOpenedPeriodName = latestOpenedPeriodName;
    }

    public Integer getLatestEncumbranceYear() {
        return latestEncumbranceYear;
    }

    public void setLatestEncumbranceYear(Integer latestEncumbranceYear) {
        this.latestEncumbranceYear = latestEncumbranceYear;
    }

    public String getPeriodAverageRateType() {
        return periodAverageRateType;
    }

    public void setPeriodAverageRateType(String periodAverageRateType) {
        this.periodAverageRateType = periodAverageRateType;
    }

    public String getPeriodEndRateType() {
        return periodEndRateType;
    }

    public void setPeriodEndRateType(String periodEndRateType) {
        this.periodEndRateType = periodEndRateType;
    }

    public String getBudgetPeriodAvgRateType() {
        return budgetPeriodAvgRateType;
    }

    public void setBudgetPeriodAvgRateType(String budgetPeriodAvgRateType) {
        this.budgetPeriodAvgRateType = budgetPeriodAvgRateType;
    }

    public String getBudgetPeriodEndRateType() {
        return budgetPeriodEndRateType;
    }

    public void setBudgetPeriodEndRateType(String budgetPeriodEndRateType) {
        this.budgetPeriodEndRateType = budgetPeriodEndRateType;
    }

    public AccountingMethod getSlaAccountingMethodCode() {
        return slaAccountingMethodCode;
    }

    public void setSlaAccountingMethodCode(AccountingMethod slaAccountingMethodCode) {
        this.slaAccountingMethodCode = slaAccountingMethodCode;
    }
    
    
    public String getSlaAccountingMethodType() {
        return slaAccountingMethodType;
    }

    public void setSlaAccountingMethodType(String slaAccountingMethodType) {
        this.slaAccountingMethodType = slaAccountingMethodType;
    }

    public String getSlaDescriptionLanguage() {
        return slaDescriptionLanguage;
    }

    public void setSlaDescriptionLanguage(String slaDescriptionLanguage) {
        this.slaDescriptionLanguage = slaDescriptionLanguage;
    }

    public Integer getSlaEnteredCurBalSusCcid() {
        return slaEnteredCurBalSusCcid;
    }

    public void setSlaEnteredCurBalSusCcid(Integer slaEnteredCurBalSusCcid) {
        this.slaEnteredCurBalSusCcid = slaEnteredCurBalSusCcid;
    }

    public String getSlaSequencingFlag() {
        return slaSequencingFlag;
    }

    public void setSlaSequencingFlag(String slaSequencingFlag) {
        this.slaSequencingFlag = slaSequencingFlag;
    }

    public String getSlaBalByLedgerCurrFlag() {
        return slaBalByLedgerCurrFlag;
    }

    public void setSlaBalByLedgerCurrFlag(String slaBalByLedgerCurrFlag) {
        this.slaBalByLedgerCurrFlag = slaBalByLedgerCurrFlag;
    }

    public Integer getSlaLedgerCurBalSusCcid() {
        return slaLedgerCurBalSusCcid;
    }

    public void setSlaLedgerCurBalSusCcid(Integer slaLedgerCurBalSusCcid) {
        this.slaLedgerCurBalSusCcid = slaLedgerCurBalSusCcid;
    }

    public String getEnableSecondaryTrackFlag() {
        return enableSecondaryTrackFlag;
    }

    public void setEnableSecondaryTrackFlag(String enableSecondaryTrackFlag) {
        this.enableSecondaryTrackFlag = enableSecondaryTrackFlag;
    }

    public String getEnableRevalSsTrackFlag() {
        return enableRevalSsTrackFlag;
    }

    public void setEnableRevalSsTrackFlag(String enableRevalSsTrackFlag) {
        this.enableRevalSsTrackFlag = enableRevalSsTrackFlag;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getEnableReconciliationFlag() {
        return enableReconciliationFlag;
    }

    public void setEnableReconciliationFlag(String enableReconciliationFlag) {
        this.enableReconciliationFlag = enableReconciliationFlag;
    }

    public String getCreateJeFlag() {
        return createJeFlag;
    }

    public void setCreateJeFlag(String createJeFlag) {
        this.createJeFlag = createJeFlag;
    }

    public String getSlaLedgerCashBasisFlag() {
        return slaLedgerCashBasisFlag;
    }

    public void setSlaLedgerCashBasisFlag(String slaLedgerCashBasisFlag) {
        this.slaLedgerCashBasisFlag = slaLedgerCashBasisFlag;
    }

    public String getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(String completeFlag) {
        this.completeFlag = completeFlag;
    }

    public String getCommitmentBudgetFlag() {
        return commitmentBudgetFlag;
    }

    public void setCommitmentBudgetFlag(String commitmentBudgetFlag) {
        this.commitmentBudgetFlag = commitmentBudgetFlag;
    }

    public String getNetClosingBalFlag() {
        return netClosingBalFlag;
    }

    public void setNetClosingBalFlag(String netClosingBalFlag) {
        this.netClosingBalFlag = netClosingBalFlag;
    }

    public String getAutomateSecJrnlRevFlag() {
        return automateSecJrnlRevFlag;
    }

    public void setAutomateSecJrnlRevFlag(String automateSecJrnlRevFlag) {
        this.automateSecJrnlRevFlag = automateSecJrnlRevFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ledgerId != null ? ledgerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlLedger)) {
            return false;
        }
        GlLedger other = (GlLedger) object;
        if ((this.ledgerId == null && other.ledgerId != null) || (this.ledgerId != null && !this.ledgerId.equals(other.ledgerId))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.ledger.GlLedger[ ledgerId=" + ledgerId + " ]";
    }
    
}
