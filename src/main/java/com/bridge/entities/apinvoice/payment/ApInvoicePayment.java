/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.apinvoice.payment;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.arinvoice.payment.ArInvoicePayment;
import com.bridge.entities.bank.BankAccount;
import com.bridge.enums.InvoicePaymentType;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "AP_INVOICE_PAYMENTS")
@NamedQueries({
    @NamedQuery(name = "ApInvoicePayment.findAll", query = "SELECT a FROM ApInvoicePayment a")})
public class ApInvoicePayment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "invoicepay")
    @TableGenerator(name = "invoicepay", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ApInvoicePayment", valueColumnName = "CURRENT_VALUE")
    @Column(name = "PAYMENT_ID")
    private Integer paymentId;
    
    @Column(name = "ACCOUNTING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountingDate;
    
    @Column(name = "ACCRUAL_POSTED_FLAG")
    private Integer accrualPostedFlag;
    

    @Column(name = "CASH_POSTED_FLAG")
    private Integer cashPostedFlag;
    @Column(name = "CHECK_ID")
    private Integer checkId;

    @Column(name = "INVOICE_PAYMENT_ID")
    private Integer invoicePaymentId;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "PAYMENT_NUM")
    private Integer paymentNum;
    @Size(max = 15)
    @Column(name = "PERIOD_NAME")
    private String periodName;
    @Column(name = "POSTED_FLAG")
    private Integer postedFlag;
    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    @Column(name = "ACCTS_PAY_CODE_COMBINATION_ID")
    private Integer acctsPayCodeCombinationId;
    @Column(name = "ASSET_CODE_COMBINATION_ID")
    private Integer assetCodeCombinationId;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Size(max = 30)
    @Column(name = "BANK_ACCOUNT_NUM")
    private String bankAccountNum;
    @Size(max = 25)
    @Column(name = "BANK_ACCOUNT_TYPE")
    private String bankAccountType;
    @Size(max = 25)
    @Column(name = "BANK_NUM")
    private String bankNum;
    @Column(name = "DISCOUNT_LOST")
    private Integer discountLost;
    @Column(name = "DISCOUNT_TAKEN")
    private Integer discountTaken;
    @Column(name = "EXCHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    @Column(name = "EXCHANGE_RATE")
    private Integer exchangeRate;
    @Size(max = 30)
    @Column(name = "EXCHANGE_RATE_TYPE")
    private String exchangeRateType;
    @Column(name = "GAIN_CODE_COMBINATION_ID")
    private Integer gainCodeCombinationId;
    @Column(name = "INVOICE_BASE_AMOUNT")
    private Integer invoiceBaseAmount;
    @Column(name = "LOSS_CODE_COMBINATION_ID")
    private Integer lossCodeCombinationId;
    @Column(name = "PAYMENT_BASE_AMOUNT")
    private Integer paymentBaseAmount;
    @Column(name = "CASH_JE_BATCH_ID")
    private Integer cashJeBatchId;
    @Column(name = "FUTURE_PAY_CODE_COMBINATION_ID")
    private Integer futurePayCodeCombinationId;
    @Column(name = "FUTURE_PAY_POSTED_FLAG")
    private Integer futurePayPostedFlag;
    @Column(name = "JE_BATCH_ID")
    private Integer jeBatchId;
    @Column(name = "ELECTRONIC_TRANSFER_ID")
    private Integer electronicTransferId;
    @Column(name = "ASSETS_ADDITION_FLAG")
    private Integer assetsAdditionFlag;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "INVOICE_PAYMENT_TYPE")
    private InvoicePaymentType invoicePaymentType = InvoicePaymentType.CASH;
 
    @Column(name = "OTHER_INVOICE_ID")
    private Integer otherInvoiceId;
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Column(name = "EXTERNAL_BANK_ACCOUNT_ID")
    private Integer externalBankAccountId;
    @Size(max = 2000)
    @Column(name = "MRC_ACCRUAL_POSTED_FLAG")
    private String mrcAccrualPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_CASH_POSTED_FLAG")
    private String mrcCashPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_POSTED_FLAG")
    private String mrcPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_EXCHANGE_DATE")
    private String mrcExchangeDate;
    @Size(max = 2000)
    @Column(name = "MRC_EXCHANGE_RATE")
    private String mrcExchangeRate;
    @Size(max = 2000)
    @Column(name = "MRC_EXCHANGE_RATE_TYPE")
    private String mrcExchangeRateType;
    @Size(max = 2000)
    @Column(name = "MRC_GAIN_CODE_COMBINATION_ID")
    private String mrcGainCodeCombinationId;
    @Size(max = 2000)
    @Column(name = "MRC_INVOICE_BASE_AMOUNT")
    private String mrcInvoiceBaseAmount;
    @Size(max = 2000)
    @Column(name = "MRC_LOSS_CODE_COMBINATION_ID")
    private String mrcLossCodeCombinationId;
    @Size(max = 2000)
    @Column(name = "MRC_PAYMENT_BASE_AMOUNT")
    private String mrcPaymentBaseAmount;
    @Size(max = 2000)
    @Column(name = "MRC_CASH_JE_BATCH_ID")
    private String mrcCashJeBatchId;
    @Size(max = 2000)
    @Column(name = "MRC_FUTURE_PAY_POSTED_FLAG")
    private String mrcFuturePayPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_JE_BATCH_ID")
    private String mrcJeBatchId;
    @Column(name = "ACCOUNTING_EVENT_ID")
    private Integer accountingEventId;
    @Column(name = "REVERSAL_FLAG")
    private Integer reversalFlag;
    @Column(name = "REVERSAL_INV_PMT_ID")
    private Integer reversalInvPmtId;
    @Size(max = 40)
    @Column(name = "IBAN_NUMBER")
    private String ibanNumber;
    @Column(name = "INVOICING_PARTY_ID")
    private Integer invoicingPartyId;
    @Column(name = "INVOICING_PARTY_SITE_ID")
    private Integer invoicingPartySiteId;
    @Column(name = "INVOICING_VENDOR_SITE_ID")
    private Integer invoicingVendorSiteId;
    
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate = new Date();

    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private ApInvoice invoice;    
        
    @Column(name = "CANCELLED_FLAG")
    private boolean cancelled ;
    
    @Column(name = "ACCOUNTED_FLAG")
    private boolean accounted ;
    
    @ManyToOne
    @JoinColumn(name = "BANK_ACCOUNT_ID")
    private BankAccount bankAccount;
            
    @Column(name="CHECK_NO")
    private String checkNo ;
    
    @Column(name="JE_HEADER_ID")
    private Integer journalHeaderId ; // NO More Data To Read Execption
    
    public ApInvoicePayment() {
    }

    public ApInvoicePayment(ApInvoice invoice)
    {
       this.invoice = invoice ;
       
       this.amount = this.invoice.getInvoiceAmount().subtract(this.invoice.getPaymentAmountTotal()) ;
    }
    
    public ApInvoicePayment(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public boolean isAccounted() {
        return accounted;
    }

    public void setAccounted(boolean accounted) {
        this.accounted = accounted;
    }

    
    public Integer getAccrualPostedFlag() {
        return accrualPostedFlag;
    }

    public void setAccrualPostedFlag(Integer accrualPostedFlag) {
        this.accrualPostedFlag = accrualPostedFlag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    
    public Integer getCashPostedFlag() {
        return cashPostedFlag;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    
    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    
    public Integer getJournalHeaderId() {
        return journalHeaderId;
    }

    public void setJournalHeaderId(Integer journalHeaderId) {
        this.journalHeaderId = journalHeaderId;
    }

    
    public void setCashPostedFlag(Integer cashPostedFlag) {
        this.cashPostedFlag = cashPostedFlag;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public ApInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(ApInvoice invoice) {
        this.invoice = invoice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    
    public Integer getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Integer invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(Integer paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Integer getPostedFlag() {
        return postedFlag;
    }

    public void setPostedFlag(Integer postedFlag) {
        this.postedFlag = postedFlag;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public Integer getAcctsPayCodeCombinationId() {
        return acctsPayCodeCombinationId;
    }

    public void setAcctsPayCodeCombinationId(Integer acctsPayCodeCombinationId) {
        this.acctsPayCodeCombinationId = acctsPayCodeCombinationId;
    }

    public Integer getAssetCodeCombinationId() {
        return assetCodeCombinationId;
    }

    public void setAssetCodeCombinationId(Integer assetCodeCombinationId) {
        this.assetCodeCombinationId = assetCodeCombinationId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public Integer getDiscountLost() {
        return discountLost;
    }

    public void setDiscountLost(Integer discountLost) {
        this.discountLost = discountLost;
    }

    public Integer getDiscountTaken() {
        return discountTaken;
    }

    public void setDiscountTaken(Integer discountTaken) {
        this.discountTaken = discountTaken;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Integer getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Integer exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeRateType() {
        return exchangeRateType;
    }

    public void setExchangeRateType(String exchangeRateType) {
        this.exchangeRateType = exchangeRateType;
    }

    public Integer getGainCodeCombinationId() {
        return gainCodeCombinationId;
    }

    public void setGainCodeCombinationId(Integer gainCodeCombinationId) {
        this.gainCodeCombinationId = gainCodeCombinationId;
    }

    public Integer getInvoiceBaseAmount() {
        return invoiceBaseAmount;
    }

    public void setInvoiceBaseAmount(Integer invoiceBaseAmount) {
        this.invoiceBaseAmount = invoiceBaseAmount;
    }

    public Integer getLossCodeCombinationId() {
        return lossCodeCombinationId;
    }

    public void setLossCodeCombinationId(Integer lossCodeCombinationId) {
        this.lossCodeCombinationId = lossCodeCombinationId;
    }

    public Integer getPaymentBaseAmount() {
        return paymentBaseAmount;
    }

    public void setPaymentBaseAmount(Integer paymentBaseAmount) {
        this.paymentBaseAmount = paymentBaseAmount;
    }

    public Integer getCashJeBatchId() {
        return cashJeBatchId;
    }

    public void setCashJeBatchId(Integer cashJeBatchId) {
        this.cashJeBatchId = cashJeBatchId;
    }

    public Integer getFuturePayCodeCombinationId() {
        return futurePayCodeCombinationId;
    }

    public void setFuturePayCodeCombinationId(Integer futurePayCodeCombinationId) {
        this.futurePayCodeCombinationId = futurePayCodeCombinationId;
    }

    public Integer getFuturePayPostedFlag() {
        return futurePayPostedFlag;
    }

    public void setFuturePayPostedFlag(Integer futurePayPostedFlag) {
        this.futurePayPostedFlag = futurePayPostedFlag;
    }

    public Integer getJeBatchId() {
        return jeBatchId;
    }

    public void setJeBatchId(Integer jeBatchId) {
        this.jeBatchId = jeBatchId;
    }

    public Integer getElectronicTransferId() {
        return electronicTransferId;
    }

    public void setElectronicTransferId(Integer electronicTransferId) {
        this.electronicTransferId = electronicTransferId;
    }

    public Integer getAssetsAdditionFlag() {
        return assetsAdditionFlag;
    }

    public void setAssetsAdditionFlag(Integer assetsAdditionFlag) {
        this.assetsAdditionFlag = assetsAdditionFlag;
    }

    public InvoicePaymentType getInvoicePaymentType() {
        return invoicePaymentType;
    }

    public void setInvoicePaymentType(InvoicePaymentType invoicePaymentType) {
        this.invoicePaymentType = invoicePaymentType;
    }
    
    
    public Integer getOtherInvoiceId() {
        return otherInvoiceId;
    }

    public void setOtherInvoiceId(Integer otherInvoiceId) {
        this.otherInvoiceId = otherInvoiceId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getExternalBankAccountId() {
        return externalBankAccountId;
    }

    public void setExternalBankAccountId(Integer externalBankAccountId) {
        this.externalBankAccountId = externalBankAccountId;
    }

    public String getMrcAccrualPostedFlag() {
        return mrcAccrualPostedFlag;
    }

    public void setMrcAccrualPostedFlag(String mrcAccrualPostedFlag) {
        this.mrcAccrualPostedFlag = mrcAccrualPostedFlag;
    }

    public String getMrcCashPostedFlag() {
        return mrcCashPostedFlag;
    }

    public void setMrcCashPostedFlag(String mrcCashPostedFlag) {
        this.mrcCashPostedFlag = mrcCashPostedFlag;
    }

    public String getMrcPostedFlag() {
        return mrcPostedFlag;
    }

    public void setMrcPostedFlag(String mrcPostedFlag) {
        this.mrcPostedFlag = mrcPostedFlag;
    }

    public String getMrcExchangeDate() {
        return mrcExchangeDate;
    }

    public void setMrcExchangeDate(String mrcExchangeDate) {
        this.mrcExchangeDate = mrcExchangeDate;
    }

    public String getMrcExchangeRate() {
        return mrcExchangeRate;
    }

    public void setMrcExchangeRate(String mrcExchangeRate) {
        this.mrcExchangeRate = mrcExchangeRate;
    }

    public String getMrcExchangeRateType() {
        return mrcExchangeRateType;
    }

    public void setMrcExchangeRateType(String mrcExchangeRateType) {
        this.mrcExchangeRateType = mrcExchangeRateType;
    }

    public String getMrcGainCodeCombinationId() {
        return mrcGainCodeCombinationId;
    }

    public void setMrcGainCodeCombinationId(String mrcGainCodeCombinationId) {
        this.mrcGainCodeCombinationId = mrcGainCodeCombinationId;
    }

    public String getMrcInvoiceBaseAmount() {
        return mrcInvoiceBaseAmount;
    }

    public void setMrcInvoiceBaseAmount(String mrcInvoiceBaseAmount) {
        this.mrcInvoiceBaseAmount = mrcInvoiceBaseAmount;
    }

    public String getMrcLossCodeCombinationId() {
        return mrcLossCodeCombinationId;
    }

    public void setMrcLossCodeCombinationId(String mrcLossCodeCombinationId) {
        this.mrcLossCodeCombinationId = mrcLossCodeCombinationId;
    }

    public String getMrcPaymentBaseAmount() {
        return mrcPaymentBaseAmount;
    }

    public void setMrcPaymentBaseAmount(String mrcPaymentBaseAmount) {
        this.mrcPaymentBaseAmount = mrcPaymentBaseAmount;
    }

    public String getMrcCashJeBatchId() {
        return mrcCashJeBatchId;
    }

    public void setMrcCashJeBatchId(String mrcCashJeBatchId) {
        this.mrcCashJeBatchId = mrcCashJeBatchId;
    }

    public String getMrcFuturePayPostedFlag() {
        return mrcFuturePayPostedFlag;
    }

    public void setMrcFuturePayPostedFlag(String mrcFuturePayPostedFlag) {
        this.mrcFuturePayPostedFlag = mrcFuturePayPostedFlag;
    }

    public String getMrcJeBatchId() {
        return mrcJeBatchId;
    }

    public void setMrcJeBatchId(String mrcJeBatchId) {
        this.mrcJeBatchId = mrcJeBatchId;
    }

    public Integer getAccountingEventId() {
        return accountingEventId;
    }

    public void setAccountingEventId(Integer accountingEventId) {
        this.accountingEventId = accountingEventId;
    }

    public Integer getReversalFlag() {
        return reversalFlag;
    }

    public void setReversalFlag(Integer reversalFlag) {
        this.reversalFlag = reversalFlag;
    }

    public Integer getReversalInvPmtId() {
        return reversalInvPmtId;
    }

    public void setReversalInvPmtId(Integer reversalInvPmtId) {
        this.reversalInvPmtId = reversalInvPmtId;
    }

    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

    public Integer getInvoicingPartyId() {
        return invoicingPartyId;
    }

    public void setInvoicingPartyId(Integer invoicingPartyId) {
        this.invoicingPartyId = invoicingPartyId;
    }

    public Integer getInvoicingPartySiteId() {
        return invoicingPartySiteId;
    }

    public void setInvoicingPartySiteId(Integer invoicingPartySiteId) {
        this.invoicingPartySiteId = invoicingPartySiteId;
    }

    public Integer getInvoicingVendorSiteId() {
        return invoicingVendorSiteId;
    }

    public void setInvoicingVendorSiteId(Integer invoicingVendorSiteId) {
        this.invoicingVendorSiteId = invoicingVendorSiteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApInvoicePayment)) {
            return false;
        }
        ApInvoicePayment other = (ApInvoicePayment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.invoice.payment.ApInvoicePayment[ paymentId=" + paymentId + " ]";
    }
    
}
