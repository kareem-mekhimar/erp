/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.bank;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "BANK_ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankAccount.findAll", query = "SELECT b FROM BankAccount b")})
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bankAccount")
    @TableGenerator(name = "bankAccount", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "BankAccount", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANK_ACCOUNT_ID")
    private Integer bankAccountId;
    @Size(max = 80)
    @Column(name = "BANK_ACCOUNT_NAME")
    private String bankAccountName;
    
    @Column(name = "BANK_BRANCH_ID")
    private Integer bankBranchId;
    
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 15)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Size(max = 30)
    @Column(name = "BANK_ACCOUNT_NUM")
    private String bankAccountNum;
    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    @Size(max = 15)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 15)
    @Column(name = "CONTACT_FIRST_NAME")
    private String contactFirstName;
    @Size(max = 15)
    @Column(name = "CONTACT_MIDDLE_NAME")
    private String contactMiddleName;
    @Size(max = 20)
    @Column(name = "CONTACT_LAST_NAME")
    private String contactLastName;
    @Size(max = 5)
    @Column(name = "CONTACT_PREFIX")
    private String contactPrefix;
    @Size(max = 30)
    @Column(name = "CONTACT_TITLE")
    private String contactTitle;
    @Size(max = 10)
    @Column(name = "CONTACT_AREA_CODE")
    private String contactAreaCode;
    @Size(max = 15)
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;
    @Column(name = "MAX_CHECK_AMOUNT")
    private Integer maxCheckAmount;
    @Column(name = "MIN_CHECK_AMOUNT")
    private Integer minCheckAmount;
    @Column(name = "ONE_SIGNATURE_MAX_FLAG")
    private Integer oneSignatureMaxFlag;
    @Column(name = "INACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    @Column(name = "AVG_FLOAT_DAYS")
    private Integer avgFloatDays;
    @Column(name = "ASSET_CODE_COMBINATION_ID")
    private Integer assetCodeCombinationId;
    @Column(name = "GAIN_CODE_COMBINATION_ID")
    private Integer gainCodeCombinationId;
    @Column(name = "LOSS_CODE_COMBINATION_ID")
    private Integer lossCodeCombinationId;
    @Size(max = 25)
    @Column(name = "BANK_ACCOUNT_TYPE")
    private String bankAccountType;
    @Column(name = "VALIDATION_NUMBER")
    private Integer validationNumber;
    @Column(name = "MAX_OUTLAY")
    private Integer maxOutlay;
    @Column(name = "MULTI_CURRENCY_FLAG")
    private Integer multiCurrencyFlag;
    @Size(max = 25)
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "POOLED_FLAG")
    private Integer pooledFlag;
    @Column(name = "ZERO_AMOUNTS_ALLOWED")
    private Integer zeroAmountsAllowed;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "RECEIPT_MULTI_CURRENCY_FLAG")
    private Integer receiptMultiCurrencyFlag;
    @Size(max = 30)
    @Column(name = "CHECK_DIGITS")
    private String checkDigits;
    @Column(name = "ORG_ID")
    private Integer orgId;

    @Column(name = "BANK_ERRORS_CCID")
    private Integer bankErrorsCcid;
    @Column(name = "EARNED_CCID")
    private Integer earnedCcid;
    @Column(name = "UNEARNED_CCID")
    private Integer unearnedCcid;
    @Column(name = "ON_ACCOUNT_CCID")
    private Integer onAccountCcid;
    @Column(name = "UNAPPLIED_CCID")
    private Integer unappliedCcid;
    @Column(name = "UNIDENTIFIED_CCID")
    private Integer unidentifiedCcid;
    @Column(name = "FACTOR_CCID")
    private Integer factorCcid;
    @Column(name = "RECEIPT_CLEARING_CCID")
    private Integer receiptClearingCcid;
    @Column(name = "REMITTANCE_CCID")
    private Integer remittanceCcid;
    @Column(name = "SHORT_TERM_DEPOSIT_CCID")
    private Integer shortTermDepositCcid;
    @Size(max = 320)
    @Column(name = "BANK_ACCOUNT_NAME_ALT")
    private String bankAccountNameAlt;
    @Size(max = 240)
    @Column(name = "ACCOUNT_HOLDER_NAME")
    private String accountHolderName;
    @Size(max = 150)
    @Column(name = "ACCOUNT_HOLDER_NAME_ALT")
    private String accountHolderNameAlt;
    @Size(max = 25)
    @Column(name = "EFT_REQUESTER_ID")
    private String eftRequesterId;
    @Size(max = 30)
    @Column(name = "EFT_USER_NUMBER")
    private String eftUserNumber;
    @Column(name = "PAYROLL_BANK_ACCOUNT_ID")
    private Integer payrollBankAccountId;
    @Column(name = "FUTURE_DATED_PAYMENT_CCID")
    private Integer futureDatedPaymentCcid;
    @Column(name = "EDISC_RECEIVABLES_TRX_ID")
    private Integer ediscReceivablesTrxId;
    @Column(name = "UNEDISC_RECEIVABLES_TRX_ID")
    private Integer unediscReceivablesTrxId;
    @Column(name = "BR_REMITTANCE_CCID")
    private Integer brRemittanceCcid;
    @Column(name = "BR_FACTOR_CCID")
    private Integer brFactorCcid;
    @Column(name = "BR_STD_RECEIVABLES_TRX_ID")
    private Integer brStdReceivablesTrxId;
    @Column(name = "ALLOW_MULTI_ASSIGNMENTS_FLAG")
    private Integer allowMultiAssignmentsFlag;
    @Size(max = 30)
    @Column(name = "AGENCY_LOCATION_CODE")
    private String agencyLocationCode;
    @Size(max = 40)
    @Column(name = "IBAN_NUMBER")
    private String ibanNumber;
    
    @ManyToOne
    @JoinColumn(name = "ORG_UNIT_ID")
    private OrganizationUnit orgUnitId;

    @ManyToOne
    @JoinColumn(name = "BANK_CHARGES_CCID")
    private GlCodeCombination bankCharge ;
        
    @ManyToOne
    @JoinColumn(name = "CASH_CLEARING_CCID")
    private GlCodeCombination cashClearing;
    
    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    
    @Column(name = "CURRENT_AMOUNT")
    private BigDecimal currentAmount = BigDecimal.ZERO;
    
    @Column(name = "INITIAL_AMOUNT")
    private BigDecimal initialAmount = BigDecimal.ZERO ;
    
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank ;
    
    public BankAccount() {
    }

    public BankAccount(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }


    
    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public Integer getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
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

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setBank(Bank bank) {
        this.bank = bank;

    }

    public Bank getBank() {
        return bank;
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

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GlCodeCombination getBankCharge() {
        return bankCharge;
    }

    public void setBankCharge(GlCodeCombination bankCharge) {
        this.bankCharge = bankCharge;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactMiddleName() {
        return contactMiddleName;
    }

    public void setContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactPrefix() {
        return contactPrefix;
    }

    public void setContactPrefix(String contactPrefix) {
        this.contactPrefix = contactPrefix;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactAreaCode() {
        return contactAreaCode;
    }

    public void setContactAreaCode(String contactAreaCode) {
        this.contactAreaCode = contactAreaCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getMaxCheckAmount() {
        return maxCheckAmount;
    }

    public void setMaxCheckAmount(Integer maxCheckAmount) {
        this.maxCheckAmount = maxCheckAmount;
    }

    public Integer getMinCheckAmount() {
        return minCheckAmount;
    }

    public void setMinCheckAmount(Integer minCheckAmount) {
        this.minCheckAmount = minCheckAmount;
    }

    public Integer getOneSignatureMaxFlag() {
        return oneSignatureMaxFlag;
    }

    public void setOneSignatureMaxFlag(Integer oneSignatureMaxFlag) {
        this.oneSignatureMaxFlag = oneSignatureMaxFlag;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public Integer getAvgFloatDays() {
        return avgFloatDays;
    }

    public void setAvgFloatDays(Integer avgFloatDays) {
        this.avgFloatDays = avgFloatDays;
    }

    public Integer getAssetCodeCombinationId() {
        return assetCodeCombinationId;
    }

    public void setAssetCodeCombinationId(Integer assetCodeCombinationId) {
        this.assetCodeCombinationId = assetCodeCombinationId;
    }

    public Integer getGainCodeCombinationId() {
        return gainCodeCombinationId;
    }

    public void setGainCodeCombinationId(Integer gainCodeCombinationId) {
        this.gainCodeCombinationId = gainCodeCombinationId;
    }

    public Integer getLossCodeCombinationId() {
        return lossCodeCombinationId;
    }

    public void setLossCodeCombinationId(Integer lossCodeCombinationId) {
        this.lossCodeCombinationId = lossCodeCombinationId;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public Integer getValidationNumber() {
        return validationNumber;
    }

    public void setValidationNumber(Integer validationNumber) {
        this.validationNumber = validationNumber;
    }

    public Integer getMaxOutlay() {
        return maxOutlay;
    }

    public void setMaxOutlay(Integer maxOutlay) {
        this.maxOutlay = maxOutlay;
    }

    public Integer getMultiCurrencyFlag() {
        return multiCurrencyFlag;
    }

    public void setMultiCurrencyFlag(Integer multiCurrencyFlag) {
        this.multiCurrencyFlag = multiCurrencyFlag;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getPooledFlag() {
        return pooledFlag;
    }

    public void setPooledFlag(Integer pooledFlag) {
        this.pooledFlag = pooledFlag;
    }

    public Integer getZeroAmountsAllowed() {
        return zeroAmountsAllowed;
    }

    public void setZeroAmountsAllowed(Integer zeroAmountsAllowed) {
        this.zeroAmountsAllowed = zeroAmountsAllowed;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(Integer programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Integer getReceiptMultiCurrencyFlag() {
        return receiptMultiCurrencyFlag;
    }

    public void setReceiptMultiCurrencyFlag(Integer receiptMultiCurrencyFlag) {
        this.receiptMultiCurrencyFlag = receiptMultiCurrencyFlag;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(String checkDigits) {
        this.checkDigits = checkDigits;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public GlCodeCombination getCashClearing() {
        return cashClearing;
    }

    public void setCashClearing(GlCodeCombination cashClearing) {
        this.cashClearing = cashClearing;
    }

    
    public Integer getBankErrorsCcid() {
        return bankErrorsCcid;
    }

    public void setBankErrorsCcid(Integer bankErrorsCcid) {
        this.bankErrorsCcid = bankErrorsCcid;
    }

    public Integer getEarnedCcid() {
        return earnedCcid;
    }

    public void setEarnedCcid(Integer earnedCcid) {
        this.earnedCcid = earnedCcid;
    }

    public Integer getUnearnedCcid() {
        return unearnedCcid;
    }

    public void setUnearnedCcid(Integer unearnedCcid) {
        this.unearnedCcid = unearnedCcid;
    }

    public Integer getOnAccountCcid() {
        return onAccountCcid;
    }

    public void setOnAccountCcid(Integer onAccountCcid) {
        this.onAccountCcid = onAccountCcid;
    }

    public Integer getUnappliedCcid() {
        return unappliedCcid;
    }

    public void setUnappliedCcid(Integer unappliedCcid) {
        this.unappliedCcid = unappliedCcid;
    }

    public Integer getUnidentifiedCcid() {
        return unidentifiedCcid;
    }

    public void setUnidentifiedCcid(Integer unidentifiedCcid) {
        this.unidentifiedCcid = unidentifiedCcid;
    }

    public Integer getFactorCcid() {
        return factorCcid;
    }

    public void setFactorCcid(Integer factorCcid) {
        this.factorCcid = factorCcid;
    }

    public Integer getReceiptClearingCcid() {
        return receiptClearingCcid;
    }

    public void setReceiptClearingCcid(Integer receiptClearingCcid) {
        this.receiptClearingCcid = receiptClearingCcid;
    }

    public Integer getRemittanceCcid() {
        return remittanceCcid;
    }

    public void setRemittanceCcid(Integer remittanceCcid) {
        this.remittanceCcid = remittanceCcid;
    }

    public Integer getShortTermDepositCcid() {
        return shortTermDepositCcid;
    }

    public void setShortTermDepositCcid(Integer shortTermDepositCcid) {
        this.shortTermDepositCcid = shortTermDepositCcid;
    }

    public String getBankAccountNameAlt() {
        return bankAccountNameAlt;
    }

    public void setBankAccountNameAlt(String bankAccountNameAlt) {
        this.bankAccountNameAlt = bankAccountNameAlt;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderNameAlt() {
        return accountHolderNameAlt;
    }

    public void setAccountHolderNameAlt(String accountHolderNameAlt) {
        this.accountHolderNameAlt = accountHolderNameAlt;
    }

    public String getEftRequesterId() {
        return eftRequesterId;
    }

    public void setEftRequesterId(String eftRequesterId) {
        this.eftRequesterId = eftRequesterId;
    }

    public String getEftUserNumber() {
        return eftUserNumber;
    }

    public void setEftUserNumber(String eftUserNumber) {
        this.eftUserNumber = eftUserNumber;
    }

    public Integer getPayrollBankAccountId() {
        return payrollBankAccountId;
    }

    public void setPayrollBankAccountId(Integer payrollBankAccountId) {
        this.payrollBankAccountId = payrollBankAccountId;
    }

    public Integer getFutureDatedPaymentCcid() {
        return futureDatedPaymentCcid;
    }

    public void setFutureDatedPaymentCcid(Integer futureDatedPaymentCcid) {
        this.futureDatedPaymentCcid = futureDatedPaymentCcid;
    }

    public Integer getEdiscReceivablesTrxId() {
        return ediscReceivablesTrxId;
    }

    public void setEdiscReceivablesTrxId(Integer ediscReceivablesTrxId) {
        this.ediscReceivablesTrxId = ediscReceivablesTrxId;
    }

    public Integer getUnediscReceivablesTrxId() {
        return unediscReceivablesTrxId;
    }

    public void setUnediscReceivablesTrxId(Integer unediscReceivablesTrxId) {
        this.unediscReceivablesTrxId = unediscReceivablesTrxId;
    }

    public Integer getBrRemittanceCcid() {
        return brRemittanceCcid;
    }

    public void setBrRemittanceCcid(Integer brRemittanceCcid) {
        this.brRemittanceCcid = brRemittanceCcid;
    }

    public Integer getBrFactorCcid() {
        return brFactorCcid;
    }

    public void setBrFactorCcid(Integer brFactorCcid) {
        this.brFactorCcid = brFactorCcid;
    }

    public Integer getBrStdReceivablesTrxId() {
        return brStdReceivablesTrxId;
    }

    public void setBrStdReceivablesTrxId(Integer brStdReceivablesTrxId) {
        this.brStdReceivablesTrxId = brStdReceivablesTrxId;
    }

    public Integer getAllowMultiAssignmentsFlag() {
        return allowMultiAssignmentsFlag;
    }

    public void setAllowMultiAssignmentsFlag(Integer allowMultiAssignmentsFlag) {
        this.allowMultiAssignmentsFlag = allowMultiAssignmentsFlag;
    }

    public String getAgencyLocationCode() {
        return agencyLocationCode;
    }

    public void setAgencyLocationCode(String agencyLocationCode) {
        this.agencyLocationCode = agencyLocationCode;
    }

    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankAccountId != null ? bankAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankAccount)) {
            return false;
        }
        BankAccount other = (BankAccount) object;
        if ((this.bankAccountId == null && other.bankAccountId != null) || (this.bankAccountId != null && !this.bankAccountId.equals(other.bankAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.bank.BankAccount[ bankAccountId=" + bankAccountId + " ]";
    }

    public OrganizationUnit getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(OrganizationUnit orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

}
