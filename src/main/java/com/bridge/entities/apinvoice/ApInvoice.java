/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.apinvoice;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AccountedStatus;
import com.bridge.enums.ApprovalStatus;
import com.bridge.enums.InvoiceStatus;
import com.bridge.enums.InvoiceType;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "AP_INVOICES")
@NamedQueries({
    @NamedQuery(name = "ApInvoice.findAll", query = "SELECT a FROM ApInvoice a")})
public class ApInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "invoice")
    @TableGenerator(name = "invoice", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ApInvoice", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;
   

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
   
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;

    @Size(min = 1, max = 50)
    @Column(name = "INVOICE_NUM")
    private String invoiceNum;
    
    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    
    @ManyToOne
    @JoinColumn(name = "INVOICE_CURRENCY_ID")
    private FndCurrency invoiceCurrency;

    @Column(name = "INVOICE_CURRENCY_CODE")
    private String invoiceCurrencyCode;
    
    @Size(max = 15)
    @Column(name = "PAYMENT_CURRENCY_CODE")
    private String paymentCurrencyCode;
 
    @Basic(optional = false)
    @Column(name = "PAYMENT_CURRENCY_ID")
    private Integer paymentCurrencyId;
   
    @Basic(optional = false)
    @Column(name = "PAYMENT_CROSS_RATE")
    private Integer paymentCrossRate;
    
    @Column(name = "INVOICE_AMOUNT")
    private BigDecimal invoiceAmount;

    @Column(name = "AMOUNT_PAID")
    private Integer amountPaid;
    @Column(name = "DISCOUNT_AMOUNT_TAKEN")
    private Integer discountAmountTaken;
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate = new Date();
    @Size(max = 25)
    @Column(name = "SOURCE")
    private String source;

    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BATCH_ID")
    private Integer batchId;
    @Column(name = "AMOUNT_APPLICABLE_TO_DISCOUNT")
    private Integer amountApplicableToDiscount;
    @Column(name = "TAX_AMOUNT")
    private Integer taxAmount;
    @Column(name = "TERMS_ID")
    private Integer termsId;
    @Column(name = "TERMS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date termsDate;
    @Size(max = 25)
    @Column(name = "PAYMENT_METHOD_LOOKUP_CODE")
    private String paymentMethodLookupCode;
    @Size(max = 25)
    @Column(name = "PAY_GROUP_LOOKUP_CODE")
    private String payGroupLookupCode;
    @Column(name = "ACCTS_PAY_CODE_COMBINATION_ID")
    private Integer acctsPayCodeCombinationId;
    @Column(name = "PAYMENT_STATUS_FLAG")
    private Integer paymentStatusFlag;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "BASE_AMOUNT")
    private Integer baseAmount;
    @Size(max = 15)
    @Column(name = "VAT_CODE")
    private String vatCode;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Size(max = 1)
    @Column(name = "EXCLUSIVE_PAYMENT_FLAG")
    private String exclusivePaymentFlag;

    @Column(name = "FREIGHT_AMOUNT")
    private Integer freightAmount;
    @Column(name = "GOODS_RECEIVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date goodsReceivedDate;
    @Column(name = "INVOICE_RECEIVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceReceivedDate;
    @Size(max = 50)
    @Column(name = "VOUCHER_NUM")
    private String voucherNum;
    @Column(name = "APPROVED_AMOUNT")
    private Integer approvedAmount;
    @Column(name = "RECURRING_PAYMENT_ID")
    private Integer recurringPaymentId;
    @Column(name = "EXCHANGE_RATE")
    private Integer exchangeRate;
    @Size(max = 30)
    @Column(name = "EXCHANGE_RATE_TYPE")
    private String exchangeRateType;
    @Column(name = "EXCHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    @Column(name = "EARLIEST_SETTLEMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earliestSettlementDate;
    @Column(name = "ORIGINAL_PREPAYMENT_AMOUNT")
    private Integer originalPrepaymentAmount;
    @Column(name = "DOC_SEQUENCE_ID")
    private Integer docSequenceId;
    @Column(name = "DOC_SEQUENCE_VALUE")
    private Integer docSequenceValue;
    @Size(max = 30)
    @Column(name = "DOC_CATEGORY_CODE")
    private String docCategoryCode;
   
    @Column(name = "APPROVAL_STATUS")
    private ApprovalStatus approvalStatus;
    
    @Column(name = "APPROVAL_STATUS_ID")
    private Integer approvalStatusId;
    @Size(max = 240)
    @Column(name = "APPROVAL_DESCRIPTION")
    private String approvalDescription;
    @Column(name = "INVOICE_DISTRIBUTION_TOTAL")
    private Integer invoiceDistributionTotal;
    @Size(max = 15)
    @Column(name = "POSTING_STATUS")
    private String postingStatus;
    @Column(name = "POSTING_STATUS_ID")
    private Integer postingStatusId;
    @Size(max = 1)
    @Column(name = "PREPAY_FLAG")
    private String prepayFlag;
    @Column(name = "CANCELLED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelledDate;
    @Column(name = "CANCELLED_BY")
    private Integer cancelledBy;
    @Column(name = "CANCELLED_AMOUNT")
    private Integer cancelledAmount;
    @Column(name = "PA_QUANTITY")
    private Integer paQuantity;
    @Column(name = "EXPENDITURE_ORGANIZATION_ID")
    private Integer expenditureOrganizationId;
    @Column(name = "VENDOR_PREPAY_AMOUNT")
    private Integer vendorPrepayAmount;

    @Size(max = 30)
    @Column(name = "REFERENCE_1")
    private String reference1;
    @Size(max = 30)
    @Column(name = "REFERENCE_2")
    private String reference2;

    @Column(name = "PRE_WITHHOLDING_AMOUNT")
    private Integer preWithholdingAmount;
    @Column(name = "AUTO_TAX_CALC_FLAG")
    private Integer autoTaxCalcFlag;
    @Column(name = "PAY_CURR_INVOICE_AMOUNT")
    private Integer payCurrInvoiceAmount;
   

    @Column(name = "GL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date glDate;
    @Column(name = "AWARD_ID")
    private Integer awardId;
    @Column(name = "PAID_ON_BEHALF_EMPLOYEE_ID")
    private Integer paidOnBehalfEmployeeId;


    @Column(name = "APPROVAL_READY_FLAG")
    private Integer approvalReadyFlag;


    @Size(min = 1, max = 50)
    @Column(name = "WFAPPROVAL_STATUS")
    private String wfapprovalStatus;
    @Column(name = "CREDITED_INVOICE_ID")
    private Integer creditedInvoiceId;
    @Column(name = "DISTRIBUTION_SET_ID")
    private Integer distributionSetId;
    @Column(name = "TOTAL_TAX_AMOUNT")
    private Integer totalTaxAmount;
    @Column(name = "TAX_RELATED_INVOICE_ID")
    private Integer taxRelatedInvoiceId;
    @Size(max = 240)
    @Column(name = "TRX_BUSINESS_CATEGORY")
    private String trxBusinessCategory;
    @Size(max = 150)
    @Column(name = "SUPPLIER_TAX_INVOICE_NUMBER")
    private String supplierTaxInvoiceNumber;
    @Column(name = "SUPPLIER_TAX_INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supplierTaxInvoiceDate;
    @Size(max = 150)
    @Column(name = "TAX_INVOICE_INTERNAL_SEQ")
    private String taxInvoiceInternalSeq;
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    @Column(name = "HISTORICAL_FLAG")
    private Integer historicalFlag;
    @Column(name = "FORCE_REVALIDATION_FLAG")
    private Integer forceRevalidationFlag;
    @Size(max = 30)
    @Column(name = "BANK_CHARGE_BEARER")
    private String bankChargeBearer;
    @Size(max = 150)
    @Column(name = "REMITTANCE_MESSAGE1")
    private String remittanceMessage1;
    @Size(max = 150)
    @Column(name = "REMITTANCE_MESSAGE2")
    private String remittanceMessage2;
    @Size(max = 30)
    @Column(name = "SETTLEMENT_PRIORITY")
    private String settlementPriority;
    @Size(max = 30)
    @Column(name = "PAYMENT_REASON_CODE")
    private String paymentReasonCode;
    @Size(max = 240)
    @Column(name = "PAYMENT_REASON_COMMENTS")
    private String paymentReasonComments;
    @Size(max = 30)
    @Column(name = "PAYMENT_METHOD_CODE")
    private String paymentMethodCode;
    @Size(max = 30)
    @Column(name = "DELIVERY_CHANNEL_CODE")
    private String deliveryChannelCode;
    @Column(name = "QUICK_PO_HEADER_ID")
    private Long quickPoHeaderId;
    @Column(name = "RELEASE_AMOUNT_NET_OF_TAX")
    private Integer releaseAmountNetOfTax;
    @Column(name = "CONTROL_AMOUNT")
    private Integer controlAmount;
    @Column(name = "PARTY_ID")
    private Long partyId;
    @Column(name = "PARTY_SITE_ID")
    private Long partySiteId;
    @Size(max = 50)
    @Column(name = "CUST_REGISTRATION_CODE")
    private String custRegistrationCode;
    @Size(max = 30)
    @Column(name = "CUST_REGISTRATION_NUMBER")
    private String custRegistrationNumber;
    @Size(max = 30)
    @Column(name = "PORT_OF_ENTRY_CODE")
    private String portOfEntryCode;
    @Column(name = "EXTERNAL_BANK_ACCOUNT_ID")
    private Long externalBankAccountId;
    @Column(name = "VENDOR_CONTACT_ID")
    private Long vendorContactId;
    @Size(max = 2000)
    @Column(name = "INTERNAL_CONTACT_EMAIL")
    private String internalContactEmail;
    @Column(name = "EXCLUDE_FREIGHT_FROM_DISCOUNT")
    private Integer excludeFreightFromDiscount;
    @Column(name = "ORIGINAL_INVOICE_AMOUNT")
    private Integer originalInvoiceAmount;
    @Column(name = "PO_MATCHED_FLAG")
    private Integer poMatchedFlag;
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "PROJECT_ID")
    private Integer projectId;
    @Column(name = "INVOICE_TYPE_LOOKUP_CODE_ID")
    private Integer invoiceTypeLookupCodeId;
   
    @Enumerated(EnumType.STRING)
    @Column(name = "INVOICE_TYPE_LOOKUP_CODE")
    private InvoiceType invoiceType = InvoiceType.STANDARD;
        
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNTED_STATUS")
    private AccountedStatus accountedStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "INVOICE_STATUS")
    private InvoiceStatus invoiceStatus;
    
    @Column(name = "ACCOUNTED_FLAG")
    private boolean accounted ;
    
    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Client client;
    
    @OneToMany(mappedBy = "invoice",cascade = {CascadeType.ALL})
    private List<ApInvoiceLine> lines ;
    
    @Column(name = "PAYMENT_AMOUNT_TOTAL")
    private BigDecimal paymentAmountTotal = BigDecimal.ZERO;
        
    @ManyToOne
    @JoinColumn(name = "VENDOR_SITE_ID")
    private ClientSite clientSite;

    @ManyToOne
    @JoinColumn(name = "OPERATINGUNIT_ID")
    private OrganizationUnit operatingUnit;
    
    @Column(name="JE_HEADER_ID")
    private Integer journalHeaderId ;
    
    @ManyToOne
    @JoinColumn(name = "PARENT_INVOICE_ID")
    private ApInvoice parentInvoice ;
    
    public ApInvoice() {
    }
    
    public ApInvoice(Client client,ClientSite site,FndCurrency invoiceCurrency,OrganizationUnit org)
    {
        this.client =  client;
        
        this.clientSite = site ;
        
        this.invoiceCurrency = invoiceCurrency ;
        
        this.invoiceStatus = InvoiceStatus.NEVER_VALIDATED ;
        
        this.approvalStatus = ApprovalStatus.NOT_REQUIRED ;
        
        this.operatingUnit = org ;
        
        this.accountedStatus = AccountedStatus.NO ;
    }
    
    public ApInvoice(ApInvoice parent , InvoiceType type)
    {   
        this(parent.getClient(), parent.getClientSite(),parent.getInvoiceCurrency(), parent.getOperatingUnit());
        
        this.parentInvoice = parent ;
        
        this.invoiceType = type ;
    }
            
    
    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public boolean isAccounted() {
        return accounted;
    }

    public void setAccounted(boolean accounted) {
        this.accounted = accounted;
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

    public Client getClient() {
        return client;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public ApInvoice getParentInvoice() {
        return parentInvoice;
    }

    public void setParentInvoice(ApInvoice parentInvoice) {
        this.parentInvoice = parentInvoice;
    }

    
    public void setClient(Client client) {
        this.client = client;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public ClientSite getClientSite() {
        return clientSite;
    }

    public void setClientSite(ClientSite clientSite) {
        this.clientSite = clientSite;
    }

    
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public Integer getJournalHeaderId() {
        return journalHeaderId;
    }

    public void setJournalHeaderId(Integer journalHeaderId) {
        this.journalHeaderId = journalHeaderId;
    }

    
    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
    }

    

    public FndCurrency getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(FndCurrency invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    
    public String getPaymentCurrencyCode() {
        return paymentCurrencyCode;
    }

    public void setPaymentCurrencyCode(String paymentCurrencyCode) {
        this.paymentCurrencyCode = paymentCurrencyCode;
    }

    public Integer getPaymentCurrencyId() {
        return paymentCurrencyId;
    }

    public void setPaymentCurrencyId(Integer paymentCurrencyId) {
        this.paymentCurrencyId = paymentCurrencyId;
    }

    public Integer getPaymentCrossRate() {
        return paymentCrossRate;
    }

    public void setPaymentCrossRate(Integer paymentCrossRate) {
        this.paymentCrossRate = paymentCrossRate;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    
    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getDiscountAmountTaken() {
        return discountAmountTaken;
    }

    public void setDiscountAmountTaken(Integer discountAmountTaken) {
        this.discountAmountTaken = discountAmountTaken;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getAmountApplicableToDiscount() {
        return amountApplicableToDiscount;
    }

    public void setAmountApplicableToDiscount(Integer amountApplicableToDiscount) {
        this.amountApplicableToDiscount = amountApplicableToDiscount;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getTermsId() {
        return termsId;
    }

    public void setTermsId(Integer termsId) {
        this.termsId = termsId;
    }

    public Date getTermsDate() {
        return termsDate;
    }

    public void setTermsDate(Date termsDate) {
        this.termsDate = termsDate;
    }

    public String getPaymentMethodLookupCode() {
        return paymentMethodLookupCode;
    }

    public void setPaymentMethodLookupCode(String paymentMethodLookupCode) {
        this.paymentMethodLookupCode = paymentMethodLookupCode;
    }

    public String getPayGroupLookupCode() {
        return payGroupLookupCode;
    }

    public void setPayGroupLookupCode(String payGroupLookupCode) {
        this.payGroupLookupCode = payGroupLookupCode;
    }

    public Integer getAcctsPayCodeCombinationId() {
        return acctsPayCodeCombinationId;
    }

    public void setAcctsPayCodeCombinationId(Integer acctsPayCodeCombinationId) {
        this.acctsPayCodeCombinationId = acctsPayCodeCombinationId;
    }

    public Integer getPaymentStatusFlag() {
        return paymentStatusFlag;
    }

    public void setPaymentStatusFlag(Integer paymentStatusFlag) {
        this.paymentStatusFlag = paymentStatusFlag;
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

    public Integer getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getExclusivePaymentFlag() {
        return exclusivePaymentFlag;
    }

    public void setExclusivePaymentFlag(String exclusivePaymentFlag) {
        this.exclusivePaymentFlag = exclusivePaymentFlag;
    }


    public Integer getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Integer freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Date getGoodsReceivedDate() {
        return goodsReceivedDate;
    }

    public void setGoodsReceivedDate(Date goodsReceivedDate) {
        this.goodsReceivedDate = goodsReceivedDate;
    }

    public Date getInvoiceReceivedDate() {
        return invoiceReceivedDate;
    }

    public void setInvoiceReceivedDate(Date invoiceReceivedDate) {
        this.invoiceReceivedDate = invoiceReceivedDate;
    }

    public String getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(String voucherNum) {
        this.voucherNum = voucherNum;
    }

    public Integer getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Integer approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Integer getRecurringPaymentId() {
        return recurringPaymentId;
    }

    public void setRecurringPaymentId(Integer recurringPaymentId) {
        this.recurringPaymentId = recurringPaymentId;
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

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Date getEarliestSettlementDate() {
        return earliestSettlementDate;
    }

    public void setEarliestSettlementDate(Date earliestSettlementDate) {
        this.earliestSettlementDate = earliestSettlementDate;
    }

    public Integer getOriginalPrepaymentAmount() {
        return originalPrepaymentAmount;
    }

    public void setOriginalPrepaymentAmount(Integer originalPrepaymentAmount) {
        this.originalPrepaymentAmount = originalPrepaymentAmount;
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

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    
    public String getDocCategoryCode() {
        return docCategoryCode;
    }

    public void setDocCategoryCode(String docCategoryCode) {
        this.docCategoryCode = docCategoryCode;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    

    public Integer getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(Integer approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }

    public String getApprovalDescription() {
        return approvalDescription;
    }

    public void setApprovalDescription(String approvalDescription) {
        this.approvalDescription = approvalDescription;
    }

    public Integer getInvoiceDistributionTotal() {
        return invoiceDistributionTotal;
    }

    public void setInvoiceDistributionTotal(Integer invoiceDistributionTotal) {
        this.invoiceDistributionTotal = invoiceDistributionTotal;
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus;
    }

    public Integer getPostingStatusId() {
        return postingStatusId;
    }

    public void setPostingStatusId(Integer postingStatusId) {
        this.postingStatusId = postingStatusId;
    }

    public String getPrepayFlag() {
        return prepayFlag;
    }

    public void setPrepayFlag(String prepayFlag) {
        this.prepayFlag = prepayFlag;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Integer getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(Integer cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public Integer getCancelledAmount() {
        return cancelledAmount;
    }

    public void setCancelledAmount(Integer cancelledAmount) {
        this.cancelledAmount = cancelledAmount;
    }

    public Integer getPaQuantity() {
        return paQuantity;
    }

    public void setPaQuantity(Integer paQuantity) {
        this.paQuantity = paQuantity;
    }

    public Integer getExpenditureOrganizationId() {
        return expenditureOrganizationId;
    }

    public void setExpenditureOrganizationId(Integer expenditureOrganizationId) {
        this.expenditureOrganizationId = expenditureOrganizationId;
    }

    public Integer getVendorPrepayAmount() {
        return vendorPrepayAmount;
    }

    public void setVendorPrepayAmount(Integer vendorPrepayAmount) {
        this.vendorPrepayAmount = vendorPrepayAmount;
    }

    public BigDecimal getPaymentAmountTotal() {
        return paymentAmountTotal;
    }

    public void setPaymentAmountTotal(BigDecimal paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }


    
    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }
    
    public Integer getPreWithholdingAmount() {
        return preWithholdingAmount;
    }

    public void setPreWithholdingAmount(Integer preWithholdingAmount) {
        this.preWithholdingAmount = preWithholdingAmount;
    }

    public Integer getAutoTaxCalcFlag() {
        return autoTaxCalcFlag;
    }

    public void setAutoTaxCalcFlag(Integer autoTaxCalcFlag) {
        this.autoTaxCalcFlag = autoTaxCalcFlag;
    }

    public Integer getPayCurrInvoiceAmount() {
        return payCurrInvoiceAmount;
    }

    public void setPayCurrInvoiceAmount(Integer payCurrInvoiceAmount) {
        this.payCurrInvoiceAmount = payCurrInvoiceAmount;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Integer getPaidOnBehalfEmployeeId() {
        return paidOnBehalfEmployeeId;
    }

    public void setPaidOnBehalfEmployeeId(Integer paidOnBehalfEmployeeId) {
        this.paidOnBehalfEmployeeId = paidOnBehalfEmployeeId;
    }

    public Integer getApprovalReadyFlag() {
        return approvalReadyFlag;
    }

    public void setApprovalReadyFlag(Integer approvalReadyFlag) {
        this.approvalReadyFlag = approvalReadyFlag;
    }

    public String getWfapprovalStatus() {
        return wfapprovalStatus;
    }

    public void setWfapprovalStatus(String wfapprovalStatus) {
        this.wfapprovalStatus = wfapprovalStatus;
    }

    public Integer getCreditedInvoiceId() {
        return creditedInvoiceId;
    }

    public void setCreditedInvoiceId(Integer creditedInvoiceId) {
        this.creditedInvoiceId = creditedInvoiceId;
    }

    public Integer getDistributionSetId() {
        return distributionSetId;
    }

    public void setDistributionSetId(Integer distributionSetId) {
        this.distributionSetId = distributionSetId;
    }

    public Integer getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Integer totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Integer getTaxRelatedInvoiceId() {
        return taxRelatedInvoiceId;
    }

    public void setTaxRelatedInvoiceId(Integer taxRelatedInvoiceId) {
        this.taxRelatedInvoiceId = taxRelatedInvoiceId;
    }

    public String getTrxBusinessCategory() {
        return trxBusinessCategory;
    }

    public void setTrxBusinessCategory(String trxBusinessCategory) {
        this.trxBusinessCategory = trxBusinessCategory;
    }

    public String getSupplierTaxInvoiceNumber() {
        return supplierTaxInvoiceNumber;
    }

    public void setSupplierTaxInvoiceNumber(String supplierTaxInvoiceNumber) {
        this.supplierTaxInvoiceNumber = supplierTaxInvoiceNumber;
    }

    public Date getSupplierTaxInvoiceDate() {
        return supplierTaxInvoiceDate;
    }

    public void setSupplierTaxInvoiceDate(Date supplierTaxInvoiceDate) {
        this.supplierTaxInvoiceDate = supplierTaxInvoiceDate;
    }

    public String getTaxInvoiceInternalSeq() {
        return taxInvoiceInternalSeq;
    }

    public void setTaxInvoiceInternalSeq(String taxInvoiceInternalSeq) {
        this.taxInvoiceInternalSeq = taxInvoiceInternalSeq;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Integer getHistoricalFlag() {
        return historicalFlag;
    }

    public void setHistoricalFlag(Integer historicalFlag) {
        this.historicalFlag = historicalFlag;
    }

    public Integer getForceRevalidationFlag() {
        return forceRevalidationFlag;
    }

    public void setForceRevalidationFlag(Integer forceRevalidationFlag) {
        this.forceRevalidationFlag = forceRevalidationFlag;
    }

    public String getBankChargeBearer() {
        return bankChargeBearer;
    }

    public void setBankChargeBearer(String bankChargeBearer) {
        this.bankChargeBearer = bankChargeBearer;
    }

    public String getRemittanceMessage1() {
        return remittanceMessage1;
    }

    public void setRemittanceMessage1(String remittanceMessage1) {
        this.remittanceMessage1 = remittanceMessage1;
    }

    public String getRemittanceMessage2() {
        return remittanceMessage2;
    }

    public void setRemittanceMessage2(String remittanceMessage2) {
        this.remittanceMessage2 = remittanceMessage2;
    }

    public String getSettlementPriority() {
        return settlementPriority;
    }

    public void setSettlementPriority(String settlementPriority) {
        this.settlementPriority = settlementPriority;
    }

    public String getPaymentReasonCode() {
        return paymentReasonCode;
    }

    public void setPaymentReasonCode(String paymentReasonCode) {
        this.paymentReasonCode = paymentReasonCode;
    }

    public String getPaymentReasonComments() {
        return paymentReasonComments;
    }

    public void setPaymentReasonComments(String paymentReasonComments) {
        this.paymentReasonComments = paymentReasonComments;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getDeliveryChannelCode() {
        return deliveryChannelCode;
    }

    public void setDeliveryChannelCode(String deliveryChannelCode) {
        this.deliveryChannelCode = deliveryChannelCode;
    }

    public Long getQuickPoHeaderId() {
        return quickPoHeaderId;
    }

    public void setQuickPoHeaderId(Long quickPoHeaderId) {
        this.quickPoHeaderId = quickPoHeaderId;
    }

    public Integer getReleaseAmountNetOfTax() {
        return releaseAmountNetOfTax;
    }

    public void setReleaseAmountNetOfTax(Integer releaseAmountNetOfTax) {
        this.releaseAmountNetOfTax = releaseAmountNetOfTax;
    }

    public Integer getControlAmount() {
        return controlAmount;
    }

    public void setControlAmount(Integer controlAmount) {
        this.controlAmount = controlAmount;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartySiteId() {
        return partySiteId;
    }

    public void setPartySiteId(Long partySiteId) {
        this.partySiteId = partySiteId;
    }

    public String getCustRegistrationCode() {
        return custRegistrationCode;
    }

    public void setCustRegistrationCode(String custRegistrationCode) {
        this.custRegistrationCode = custRegistrationCode;
    }

    public String getCustRegistrationNumber() {
        return custRegistrationNumber;
    }

    public void setCustRegistrationNumber(String custRegistrationNumber) {
        this.custRegistrationNumber = custRegistrationNumber;
    }

    public String getPortOfEntryCode() {
        return portOfEntryCode;
    }

    public void setPortOfEntryCode(String portOfEntryCode) {
        this.portOfEntryCode = portOfEntryCode;
    }

    public Long getExternalBankAccountId() {
        return externalBankAccountId;
    }

    public void setExternalBankAccountId(Long externalBankAccountId) {
        this.externalBankAccountId = externalBankAccountId;
    }

    public Long getVendorContactId() {
        return vendorContactId;
    }

    public void setVendorContactId(Long vendorContactId) {
        this.vendorContactId = vendorContactId;
    }

    public String getInternalContactEmail() {
        return internalContactEmail;
    }

    public void setInternalContactEmail(String internalContactEmail) {
        this.internalContactEmail = internalContactEmail;
    }

    public Integer getExcludeFreightFromDiscount() {
        return excludeFreightFromDiscount;
    }

    public void setExcludeFreightFromDiscount(Integer excludeFreightFromDiscount) {
        this.excludeFreightFromDiscount = excludeFreightFromDiscount;
    }

    public Integer getOriginalInvoiceAmount() {
        return originalInvoiceAmount;
    }

    public void setOriginalInvoiceAmount(Integer originalInvoiceAmount) {
        this.originalInvoiceAmount = originalInvoiceAmount;
    }

    public Integer getPoMatchedFlag() {
        return poMatchedFlag;
    }

    public void setPoMatchedFlag(Integer poMatchedFlag) {
        this.poMatchedFlag = poMatchedFlag;
    }

    public List<ApInvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<ApInvoiceLine> lines) {
        this.lines = lines;
    }
    
    

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getInvoiceTypeLookupCodeId() {
        return invoiceTypeLookupCodeId;
    }

    public void setInvoiceTypeLookupCodeId(Integer invoiceTypeLookupCodeId) {
        this.invoiceTypeLookupCodeId = invoiceTypeLookupCodeId;
    }

    public AccountedStatus getAccountedStatus() {
        return accountedStatus;
    }

    public void setAccountedStatus(AccountedStatus accountedStatus) {
        this.accountedStatus = accountedStatus;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApInvoice)) {
            return false;
        }
        ApInvoice other = (ApInvoice) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ApInvoice[ invoiceId=" + invoiceId + " ]";
    }
    
}
