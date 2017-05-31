/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.journal;

import com.bridge.entities.coa.GlCodeCombination;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "GL_JE_LINES")
@NamedQueries({
    @NamedQuery(name = "GlJeLine.findAll", query = "SELECT g FROM GlJeLine g")})
public class GlJeLine implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jeLine")
    @TableGenerator(name = "jeLine", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "GlJeLine", valueColumnName = "CURRENT_VALUE")
    @Column(name = "JE_LINE_ID")
    private Integer jeLineId;
    

    @Column(name = "JE_LINE_NUM")
    private Integer jeLineNum;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LEDGER_ID")
    private Integer ledgerId;

    @Size(max = 15)
    @Column(name = "PERIOD_NAME")
    private String periodName;
    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "ENTERED_DR")
    private Integer enteredDr;
    @Column(name = "ENTERED_CR")
    private Integer enteredCr;
    
    @Column(name = "ACCOUNTED_DR")
    private BigDecimal accountedDr;
    
    @Column(name = "ACCOUNTED_CR")
    private BigDecimal accountedCr;

    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Size(max = 20)
    @Column(name = "LINE_TYPE_CODE")
    private String lineTypeCode;
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Size(max = 15)
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Size(max = 20)
    @Column(name = "INVOICE_IDENTIFIER")
    private String invoiceIdentifier;
    @Column(name = "INVOICE_AMOUNT")
    private Integer invoiceAmount;
    @Size(max = 150)
    @Column(name = "NO1")
    private String no1;
    @Column(name = "STAT_AMOUNT")
    private Integer statAmount;
    @Column(name = "IGNORE_RATE_FLAG")
    private Integer ignoreRateFlag;
    @Size(max = 30)
    @Column(name = "USSGL_TRANSACTION_CODE")
    private String ussglTransactionCode;
    @Column(name = "SUBLEDGER_DOC_SEQUENCE_ID")
    private Integer subledgerDocSequenceId;
    @Column(name = "SUBLEDGER_DOC_SEQUENCE_VALUE")
    private Integer subledgerDocSequenceValue;
    @Column(name = "GL_SL_LINK_ID")
    private Integer glSlLinkId;
    @Size(max = 30)
    @Column(name = "GL_SL_LINK_TABLE")
    private String glSlLinkTable;
    @Column(name = "JGZZ_RECON_STATUS")
    private Integer jgzzReconStatus;
    @Column(name = "JGZZ_RECON_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jgzzReconDate;
    @Column(name = "JGZZ_RECON_ID")
    private Integer jgzzReconId;
    @Size(max = 240)
    @Column(name = "JGZZ_RECON_REF")
    private String jgzzReconRef;
    @Size(max = 30)
    @Column(name = "JGZZ_RECON_CONTEXT")
    private String jgzzReconContext;
    @Column(name = "TAXABLE_LINE_FLAG")
    private Integer taxableLineFlag;
    @Column(name = "TAX_TYPE_CODE")
    private Integer taxTypeCode;
    @Column(name = "TAX_CODE_ID")
    private Integer taxCodeId;
    @Column(name = "TAX_ROUNDING_RULE_CODE")
    private Integer taxRoundingRuleCode;
    @Column(name = "AMOUNT_INCLUDES_TAX_FLAG")
    private Integer amountIncludesTaxFlag;
    @Size(max = 50)
    @Column(name = "TAX_DOCUMENT_IDENTIFIER")
    private String taxDocumentIdentifier;
    @Column(name = "TAX_DOCUMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxDocumentDate;
    @Size(max = 240)
    @Column(name = "TAX_CUSTOMER_NAME")
    private String taxCustomerName;
    @Size(max = 240)
    @Column(name = "TAX_CUSTOMER_REFERENCE")
    private String taxCustomerReference;
    @Size(max = 50)
    @Column(name = "TAX_REGISTRATION_NUMBER")
    private String taxRegistrationNumber;
    @Column(name = "TAX_LINE_FLAG")
    private Integer taxLineFlag;
    @Column(name = "TAX_GROUP_ID")
    private Integer taxGroupId;
    @Size(max = 14)
    @Column(name = "CO_THIRD_PARTY")
    private String coThirdParty;
    @Column(name = "CO_PROCESSED_FLAG")
    private Integer coProcessedFlag;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "CURRENCY_ID")
    private Integer currencyId;

    @ManyToOne
    @JoinColumn(name = "JE_HEADER_ID")
    private GlJeHeader header ;
    
    @ManyToOne
    @JoinColumn(name = "CODE_COMBINATION_ID")
    private GlCodeCombination account;
    
    @Column(name = "CREDIT_FLAG")
    private boolean creditFlag ;
    
    public GlJeLine() {
    }

    public GlJeLine(GlCodeCombination account,BigDecimal amount,boolean debit,int no) {

        this.account = account ;
        
        if(debit)
            
            this.accountedDr = amount ;
      
        else
            
            this.accountedCr = amount ;
        
        this.jeLineNum = no ;
        
    }

        
    public GlJeLine(Integer jeLineId) {
        this.jeLineId = jeLineId;
    }

    public GlJeHeader getHeader() {
        return header;
    }

    public void setHeader(GlJeHeader header) {
        this.header = header;
    }

    
    public Integer getJeLineNum() {
        return jeLineNum;
    }

    public void setJeLineNum(Integer jeLineNum) {
        this.jeLineNum = jeLineNum;
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

    public boolean isCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(boolean creditFlag) {
        this.creditFlag = creditFlag;
    }
    
    public GlCodeCombination getAccount() {
        return account;
    }

    public void setAccount(GlCodeCombination account) {
        this.account = account;
    }

    public void setAccountedCr(BigDecimal accountedCr) {
        this.accountedCr = accountedCr;
    }

    public void setAccountedDr(BigDecimal accountedDr) {
        this.accountedDr = accountedDr;
    }
    
    
    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getEnteredDr() {
        return enteredDr;
    }

    public void setEnteredDr(Integer enteredDr) {
        this.enteredDr = enteredDr;
    }

    public Integer getEnteredCr() {
        return enteredCr;
    }

    public void setEnteredCr(Integer enteredCr) {
        this.enteredCr = enteredCr;
    }

    public BigDecimal getAccountedCr() {
        return accountedCr;
    }

    public BigDecimal getAccountedDr() {
        return accountedDr;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLineTypeCode() {
        return lineTypeCode;
    }

    public void setLineTypeCode(String lineTypeCode) {
        this.lineTypeCode = lineTypeCode;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getInvoiceIdentifier() {
        return invoiceIdentifier;
    }

    public void setInvoiceIdentifier(String invoiceIdentifier) {
        this.invoiceIdentifier = invoiceIdentifier;
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getNo1() {
        return no1;
    }

    public void setNo1(String no1) {
        this.no1 = no1;
    }

    public Integer getStatAmount() {
        return statAmount;
    }

    public void setStatAmount(Integer statAmount) {
        this.statAmount = statAmount;
    }

    public Integer getIgnoreRateFlag() {
        return ignoreRateFlag;
    }

    public void setIgnoreRateFlag(Integer ignoreRateFlag) {
        this.ignoreRateFlag = ignoreRateFlag;
    }

    public String getUssglTransactionCode() {
        return ussglTransactionCode;
    }

    public void setUssglTransactionCode(String ussglTransactionCode) {
        this.ussglTransactionCode = ussglTransactionCode;
    }

    public Integer getSubledgerDocSequenceId() {
        return subledgerDocSequenceId;
    }

    public void setSubledgerDocSequenceId(Integer subledgerDocSequenceId) {
        this.subledgerDocSequenceId = subledgerDocSequenceId;
    }

    public Integer getSubledgerDocSequenceValue() {
        return subledgerDocSequenceValue;
    }

    public void setSubledgerDocSequenceValue(Integer subledgerDocSequenceValue) {
        this.subledgerDocSequenceValue = subledgerDocSequenceValue;
    }

    public Integer getGlSlLinkId() {
        return glSlLinkId;
    }

    public void setGlSlLinkId(Integer glSlLinkId) {
        this.glSlLinkId = glSlLinkId;
    }

    public String getGlSlLinkTable() {
        return glSlLinkTable;
    }

    public void setGlSlLinkTable(String glSlLinkTable) {
        this.glSlLinkTable = glSlLinkTable;
    }

    public Integer getJgzzReconStatus() {
        return jgzzReconStatus;
    }

    public void setJgzzReconStatus(Integer jgzzReconStatus) {
        this.jgzzReconStatus = jgzzReconStatus;
    }

    public Date getJgzzReconDate() {
        return jgzzReconDate;
    }

    public void setJgzzReconDate(Date jgzzReconDate) {
        this.jgzzReconDate = jgzzReconDate;
    }

    public Integer getJgzzReconId() {
        return jgzzReconId;
    }

    public void setJgzzReconId(Integer jgzzReconId) {
        this.jgzzReconId = jgzzReconId;
    }

    public String getJgzzReconRef() {
        return jgzzReconRef;
    }

    public void setJgzzReconRef(String jgzzReconRef) {
        this.jgzzReconRef = jgzzReconRef;
    }

    public String getJgzzReconContext() {
        return jgzzReconContext;
    }

    public void setJgzzReconContext(String jgzzReconContext) {
        this.jgzzReconContext = jgzzReconContext;
    }

    public Integer getTaxableLineFlag() {
        return taxableLineFlag;
    }

    public void setTaxableLineFlag(Integer taxableLineFlag) {
        this.taxableLineFlag = taxableLineFlag;
    }

    public Integer getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(Integer taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    public Integer getTaxCodeId() {
        return taxCodeId;
    }

    public void setTaxCodeId(Integer taxCodeId) {
        this.taxCodeId = taxCodeId;
    }

    public Integer getTaxRoundingRuleCode() {
        return taxRoundingRuleCode;
    }

    public void setTaxRoundingRuleCode(Integer taxRoundingRuleCode) {
        this.taxRoundingRuleCode = taxRoundingRuleCode;
    }

    public Integer getAmountIncludesTaxFlag() {
        return amountIncludesTaxFlag;
    }

    public void setAmountIncludesTaxFlag(Integer amountIncludesTaxFlag) {
        this.amountIncludesTaxFlag = amountIncludesTaxFlag;
    }

    public String getTaxDocumentIdentifier() {
        return taxDocumentIdentifier;
    }

    public void setTaxDocumentIdentifier(String taxDocumentIdentifier) {
        this.taxDocumentIdentifier = taxDocumentIdentifier;
    }

    public Date getTaxDocumentDate() {
        return taxDocumentDate;
    }

    public void setTaxDocumentDate(Date taxDocumentDate) {
        this.taxDocumentDate = taxDocumentDate;
    }

    public String getTaxCustomerName() {
        return taxCustomerName;
    }

    public void setTaxCustomerName(String taxCustomerName) {
        this.taxCustomerName = taxCustomerName;
    }

    public String getTaxCustomerReference() {
        return taxCustomerReference;
    }

    public void setTaxCustomerReference(String taxCustomerReference) {
        this.taxCustomerReference = taxCustomerReference;
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public Integer getTaxLineFlag() {
        return taxLineFlag;
    }

    public void setTaxLineFlag(Integer taxLineFlag) {
        this.taxLineFlag = taxLineFlag;
    }

    public Integer getTaxGroupId() {
        return taxGroupId;
    }

    public void setTaxGroupId(Integer taxGroupId) {
        this.taxGroupId = taxGroupId;
    }

    public String getCoThirdParty() {
        return coThirdParty;
    }

    public void setCoThirdParty(String coThirdParty) {
        this.coThirdParty = coThirdParty;
    }

    public Integer getCoProcessedFlag() {
        return coProcessedFlag;
    }

    public void setCoProcessedFlag(Integer coProcessedFlag) {
        this.coProcessedFlag = coProcessedFlag;
    }

    public Integer getJeLineId() {
        return jeLineId;
    }

    public void setJeLineId(Integer jeLineId) {
        this.jeLineId = jeLineId;
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
        hash += (jeLineId != null ? jeLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlJeLine)) {
            return false;
        }
        GlJeLine other = (GlJeLine) object;
        if ((this.jeLineId == null && other.jeLineId != null) || (this.jeLineId != null && !this.jeLineId.equals(other.jeLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.journal.GlJeLine[ jeLineId=" + jeLineId + " ]";
    }
    
}
