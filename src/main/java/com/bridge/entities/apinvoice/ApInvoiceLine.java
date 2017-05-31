/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.apinvoice;

import com.bridge.entities.clients.SupplierAcc;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.InvoiceLineType;
import com.bridge.enums.InvoiceMatchingOptions;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author abdalrahman
 */
@Entity
@Table(name = "AP_INVOICE_DISTRIBUTIONS")
public class ApInvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "invoiceline")
    @TableGenerator(name = "invoiceline", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
            pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ApInvoiceLine", valueColumnName = "CURRENT_VALUE")
    @Column(name = "INVOICE_DISTRIBUTION_ID")
    private Integer invoiceDistributionId;

    @Column(name = "ACCOUNTING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountingDate;
    @Column(name = "ACCRUAL_POSTED_FLAG")
    private Integer accrualPostedFlag;

    @Column(name = "ASSETS_ADDITION_FLAG")
    private Integer assetsAdditionFlag;

    @Column(name = "ASSETS_TRACKING_FLAG")
    private Integer assetsTrackingFlag;
    @Column(name = "CASH_POSTED_FLAG")
    private Integer cashPostedFlag;

    @Column(name = "DISTRIBUTION_LINE_NUMBER")
    private Integer distributionLineNumber;

    @Column(name = "DIST_CODE_COMBINATION_ID")
    private Integer distCodeCombinationId;

    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Size(min = 1, max = 25)
    @Column(name = "LINE_TYPE_LOOKUP_CODE")
    private String lineTypeLookupCode;

    @Size(min = 1, max = 15)
    @Column(name = "PERIOD_NAME")
    private String periodName;

    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    @Column(name = "ACCTS_PAY_CODE_COMBINATION_ID")
    private Integer acctsPayCodeCombinationId;

    @Column(name = "BASE_AMOUNT")
    private Integer baseAmount;
    @Column(name = "BASE_INVOICE_PRICE_VARIANCE")
    private Integer baseInvoicePriceVariance;
    @Column(name = "BATCH_ID")
    private Integer batchId;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EXCHANGE_RATE_VARIANCE")
    private Integer exchangeRateVariance;
    @Column(name = "FINAL_MATCH_FLAG")
    private Integer finalMatchFlag;
    @Size(max = 10)
    @Column(name = "INCOME_TAX_REGION")
    private String incomeTaxRegion;
    @Column(name = "INVOICE_PRICE_VARIANCE")
    private Integer invoicePriceVariance;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "MATCH_STATUS_FLAG")
    private Integer matchStatusFlag;
    @Column(name = "POSTED_FLAG")
    private Integer postedFlag;

    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;

    @Column(name = "RATE_VAR_CODE_COMBINATION_ID")
    private Integer rateVarCodeCombinationId;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "REVERSAL_FLAG")
    private Integer reversalFlag;
    @Size(max = 10)
    @Column(name = "TYPE_1099")
    private String type1099;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "AMOUNT_ENCUMBERED")
    private Integer amountEncumbered;
    @Column(name = "BASE_AMOUNT_ENCUMBERED")
    private Integer baseAmountEncumbered;
    @Column(name = "ENCUMBERED_FLAG")
    private Integer encumberedFlag;
    @Column(name = "EXCHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    @Column(name = "EXCHANGE_RATE")
    private Integer exchangeRate;
    @Size(max = 30)
    @Column(name = "EXCHANGE_RATE_TYPE")
    private String exchangeRateType;
    @Column(name = "PRICE_ADJUSTMENT_FLAG")
    private Integer priceAdjustmentFlag;
    @Column(name = "PRICE_VAR_CODE_COMBINATION_ID")
    private Integer priceVarCodeCombinationId;
    @Column(name = "QUANTITY_UNENCUMBERED")
    private Integer quantityUnencumbered;
    @Column(name = "STAT_AMOUNT")
    private Integer statAmount;
    @Column(name = "AMOUNT_TO_POST")
    private Integer amountToPost;
    @Column(name = "BASE_AMOUNT_TO_POST")
    private Integer baseAmountToPost;
    @Column(name = "CASH_JE_BATCH_ID")
    private Integer cashJeBatchId;
    @Column(name = "EXPENDITURE_ITEM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expenditureItemDate;
    @Column(name = "EXPENDITURE_ORGANIZATION_ID")
    private Integer expenditureOrganizationId;
    @Size(max = 30)
    @Column(name = "EXPENDITURE_TYPE")
    private String expenditureType;
    @Column(name = "JE_BATCH_ID")
    private Integer jeBatchId;
    @Column(name = "PARENT_INVOICE_ID")
    private Integer parentInvoiceId;
    @Column(name = "PA_ADDITION_FLAG")
    private Integer paAdditionFlag;
    @Column(name = "PA_QUANTITY")
    private Integer paQuantity;
    @Column(name = "POSTED_AMOUNT")
    private Integer postedAmount;
    @Column(name = "POSTED_BASE_AMOUNT")
    private Integer postedBaseAmount;
    @Column(name = "PREPAY_AMOUNT_REMAINING")
    private Integer prepayAmountRemaining;
    @Size(max = 30)
    @Column(name = "PROJECT_ACCOUNTING_CONTEXT")
    private String projectAccountingContext;
    @Column(name = "PROJECT_ID")
    private Integer projectId;
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Size(max = 30)
    @Column(name = "USSGL_TRANSACTION_CODE")
    private String ussglTransactionCode;
    @Size(max = 30)
    @Column(name = "USSGL_TRX_CODE_CONTEXT")
    private String ussglTrxCodeContext;
    @Column(name = "EARLIEST_SETTLEMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date earliestSettlementDate;
    @Column(name = "REQ_DISTRIBUTION_ID")
    private Integer reqDistributionId;
    @Column(name = "QUANTITY_VARIANCE")
    private Integer quantityVariance;
    @Column(name = "BASE_QUANTITY_VARIANCE")
    private Integer baseQuantityVariance;
    @Column(name = "PACKET_ID")
    private Integer packetId;
    @Column(name = "AWT_FLAG")
    private Integer awtFlag;
    @Column(name = "AWT_GROUP_ID")
    private Integer awtGroupId;
    @Column(name = "AWT_TAX_RATE_ID")
    private Integer awtTaxRateId;
    @Column(name = "AWT_GROSS_AMOUNT")
    private Integer awtGrossAmount;
    @Column(name = "AWT_INVOICE_ID")
    private Integer awtInvoiceId;
    @Column(name = "AWT_ORIGIN_GROUP_ID")
    private Integer awtOriginGroupId;
    @Size(max = 30)
    @Column(name = "REFERENCE_1")
    private String reference1;
    @Size(max = 30)
    @Column(name = "REFERENCE_2")
    private String reference2;
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Column(name = "OTHER_INVOICE_ID")
    private Integer otherInvoiceId;
    @Column(name = "AWT_INVOICE_PAYMENT_ID")
    private Integer awtInvoicePaymentId;
    @Column(name = "LINE_GROUP_NUMBER")
    private Integer lineGroupNumber;
    @Column(name = "RECEIPT_VERIFIED_FLAG")
    private Integer receiptVerifiedFlag;
    @Column(name = "RECEIPT_REQUIRED_FLAG")
    private Integer receiptRequiredFlag;
    @Column(name = "RECEIPT_MISSING_FLAG")
    private Integer receiptMissingFlag;
    @Size(max = 240)
    @Column(name = "JUSTIFICATION")
    private String justification;
    @Size(max = 80)
    @Column(name = "EXPENSE_GROUP")
    private String expenseGroup;
    @Column(name = "START_EXPENSE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startExpenseDate;
    @Column(name = "END_EXPENSE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endExpenseDate;
    @Size(max = 15)
    @Column(name = "RECEIPT_CURRENCY_CODE")
    private String receiptCurrencyCode;
    @Column(name = "RECEIPT_CONVERSION_RATE")
    private Integer receiptConversionRate;
    @Column(name = "RECEIPT_CURRENCY_AMOUNT")
    private Integer receiptCurrencyAmount;
    @Column(name = "DAILY_AMOUNT")
    private Integer dailyAmount;
    @Column(name = "WEB_PARAMETER_ID")
    private Integer webParameterId;
    @Size(max = 240)
    @Column(name = "ADJUSTMENT_REASON")
    private String adjustmentReason;
    @Column(name = "AWARD_ID")
    private Integer awardId;
    @Column(name = "MRC_ACCRUAL_POSTED_FLAG")
    private Integer mrcAccrualPostedFlag;
    @Column(name = "MRC_CASH_POSTED_FLAG")
    private Integer mrcCashPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_DIST_CODE_COMBINATION_ID")
    private String mrcDistCodeCombinationId;
    @Size(max = 2000)
    @Column(name = "MRC_AMOUNT")
    private String mrcAmount;
    @Size(max = 2000)
    @Column(name = "MRC_BASE_AMOUNT")
    private String mrcBaseAmount;
    @Size(max = 2000)
    @Column(name = "MRC_BASE_INV_PRICE_VARIANCE")
    private String mrcBaseInvPriceVariance;
    @Size(max = 2000)
    @Column(name = "MRC_EXCHANGE_RATE_VARIANCE")
    private String mrcExchangeRateVariance;
    @Column(name = "MRC_POSTED_FLAG")
    private Integer mrcPostedFlag;
    @Size(max = 2000)
    @Column(name = "MRC_PROGRAM_APPLICATION_ID")
    private String mrcProgramApplicationId;
    @Size(max = 2000)
    @Column(name = "MRC_PROGRAM_ID")
    private String mrcProgramId;
    @Size(max = 2000)
    @Column(name = "MRC_PROGRAM_UPDATE_DATE")
    private String mrcProgramUpdateDate;
    @Size(max = 2000)
    @Column(name = "MRC_RATE_VAR_CCID")
    private String mrcRateVarCcid;
    @Size(max = 2000)
    @Column(name = "MRC_REQUEST_ID")
    private String mrcRequestId;
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
    @Column(name = "MRC_AMOUNT_TO_POST")
    private String mrcAmountToPost;
    @Size(max = 2000)
    @Column(name = "MRC_BASE_AMOUNT_TO_POST")
    private String mrcBaseAmountToPost;
    @Size(max = 2000)
    @Column(name = "MRC_CASH_JE_BATCH_ID")
    private String mrcCashJeBatchId;
    @Size(max = 2000)
    @Column(name = "MRC_JE_BATCH_ID")
    private String mrcJeBatchId;
    @Size(max = 2000)
    @Column(name = "MRC_POSTED_AMOUNT")
    private String mrcPostedAmount;
    @Size(max = 2000)
    @Column(name = "MRC_POSTED_BASE_AMOUNT")
    private String mrcPostedBaseAmount;
    @Size(max = 2000)
    @Column(name = "MRC_RECEIPT_CONVERSION_RATE")
    private String mrcReceiptConversionRate;
    @Column(name = "CREDIT_CARD_TRX_ID")
    private Integer creditCardTrxId;
    @Size(max = 25)
    @Column(name = "DIST_MATCH_TYPE")
    private String distMatchType;
    @Column(name = "RCV_TRANSACTION_ID")
    private Integer rcvTransactionId;
    @Column(name = "PARENT_REVERSAL_ID")
    private Integer parentReversalId;
    @Column(name = "TAX_RECOVERABLE_FLAG")
    private Integer taxRecoverableFlag;
    @Column(name = "PA_CC_AR_INVOICE_ID")
    private Integer paCcArInvoiceId;
    @Column(name = "PA_CC_AR_INVOICE_LINE_NUM")
    private Integer paCcArInvoiceLineNum;
    @Column(name = "PA_CC_PROCESSED_CODE")
    private Integer paCcProcessedCode;
    @Size(max = 80)
    @Column(name = "MERCHANT_DOCUMENT_NUMBER")
    private String merchantDocumentNumber;
    @Size(max = 80)
    @Column(name = "MERCHANT_NAME")
    private String merchantName;
    @Size(max = 240)
    @Column(name = "MERCHANT_REFERENCE")
    private String merchantReference;
    @Size(max = 80)
    @Column(name = "MERCHANT_TAX_REG_NUMBER")
    private String merchantTaxRegNumber;
    @Size(max = 80)
    @Column(name = "MERCHANT_TAXPAYER_ID")
    private String merchantTaxpayerId;
    @Size(max = 5)
    @Column(name = "COUNTRY_OF_SUPPLY")
    private String countryOfSupply;
    @Size(max = 25)
    @Column(name = "MATCHED_UOM_LOOKUP_CODE")
    private String matchedUomLookupCode;
    @Column(name = "GMS_BURDENABLE_RAW_COST")
    private Integer gmsBurdenableRawCost;
    @Column(name = "ACCOUNTING_EVENT_ID")
    private Integer accountingEventId;
    @Column(name = "PREPAY_DISTRIBUTION_ID")
    private Integer prepayDistributionId;
    @Column(name = "UPGRADE_POSTED_AMT")
    private Integer upgradePostedAmt;
    @Column(name = "UPGRADE_BASE_POSTED_AMT")
    private Integer upgradeBasePostedAmt;
    @Column(name = "INVENTORY_TRANSFER_STATUS")
    private Integer inventoryTransferStatus;
    @Column(name = "COMPANY_PREPAID_INVOICE_ID")
    private Integer companyPrepaidInvoiceId;
    @Column(name = "CC_REVERSAL_FLAG")
    private Integer ccReversalFlag;
    @Column(name = "AWT_WITHHELD_AMT")
    private Integer awtWithheldAmt;
    @Column(name = "INVOICE_INCLUDES_PREPAY_FLAG")
    private Integer invoiceIncludesPrepayFlag;
    @Column(name = "PRICE_CORRECT_INV_ID")
    private Integer priceCorrectInvId;
    @Column(name = "PRICE_CORRECT_QTY")
    private Integer priceCorrectQty;
    @Column(name = "PA_CMT_XFACE_FLAG")
    private Integer paCmtXfaceFlag;
    @Column(name = "CANCELLATION_FLAG")
    private Integer cancellationFlag;
    @Column(name = "INVOICE_LINE_NUMBER")
    private Integer invoiceLineNumber;
    @Column(name = "CORRECTED_INVOICE_DIST_ID")
    private Integer correctedInvoiceDistId;
    @Column(name = "ROUNDING_AMT")
    private Integer roundingAmt;
    @Column(name = "CHARGE_APPLICABLE_TO_DIST_ID")
    private Integer chargeApplicableToDistId;
    @Column(name = "CORRECTED_QUANTITY")
    private Integer correctedQuantity;
    @Column(name = "RELATED_ID")
    private Integer relatedId;
    @Size(max = 15)
    @Column(name = "ASSET_BOOK_TYPE_CODE")
    private String assetBookTypeCode;
    @Column(name = "ASSET_CATEGORY_ID")
    private Integer assetCategoryId;
    @Size(max = 30)
    @Column(name = "DISTRIBUTION_CLASS")
    private String distributionClass;
    @Column(name = "FINAL_PAYMENT_ROUNDING")
    private Integer finalPaymentRounding;
    @Column(name = "FINAL_APPLICATION_ROUNDING")
    private Integer finalApplicationRounding;
    @Column(name = "AMOUNT_AT_PREPAY_XRATE")
    private Integer amountAtPrepayXrate;
    @Column(name = "CASH_BASIS_FINAL_APP_ROUNDING")
    private Integer cashBasisFinalAppRounding;
    @Column(name = "AMOUNT_AT_PREPAY_PAY_XRATE")
    private Integer amountAtPrepayPayXrate;
    @Size(max = 30)
    @Column(name = "INTENDED_USE")
    private String intendedUse;
    @Column(name = "DETAIL_TAX_DIST_ID")
    private Integer detailTaxDistId;
    @Column(name = "REC_NREC_RATE")
    private Integer recNrecRate;
    @Column(name = "RECOVERY_RATE_ID")
    private Integer recoveryRateId;
    @Size(max = 150)
    @Column(name = "RECOVERY_RATE_NAME")
    private String recoveryRateName;
    @Size(max = 30)
    @Column(name = "RECOVERY_TYPE_CODE")
    private String recoveryTypeCode;
    @Size(max = 30)
    @Column(name = "RECOVERY_RATE_CODE")
    private String recoveryRateCode;
    @Column(name = "WITHHOLDING_TAX_CODE_ID")
    private Integer withholdingTaxCodeId;
    @Column(name = "TAX_ALREADY_DISTRIBUTED_FLAG")
    private Integer taxAlreadyDistributedFlag;
    @Column(name = "SUMMARY_TAX_LINE_ID")
    private Integer summaryTaxLineId;
    @Column(name = "TAXABLE_AMOUNT")
    private Integer taxableAmount;
    @Column(name = "TAXABLE_BASE_AMOUNT")
    private Integer taxableBaseAmount;
    @Column(name = "EXTRA_PO_ERV")
    private Integer extraPoErv;
    @Column(name = "PREPAY_TAX_DIFF_AMOUNT")
    private Integer prepayTaxDiffAmount;
    @Column(name = "TAX_CODE_ID")
    private Integer taxCodeId;
    @Size(max = 15)
    @Column(name = "VAT_CODE")
    private String vatCode;
    @Column(name = "AMOUNT_INCLUDES_TAX_FLAG")
    private Integer amountIncludesTaxFlag;
    @Column(name = "TAX_CALCULATED_FLAG")
    private Integer taxCalculatedFlag;
    @Column(name = "TAX_RECOVERY_RATE")
    private Integer taxRecoveryRate;
    @Column(name = "TAX_RECOVERY_OVERRIDE_FLAG")
    private Integer taxRecoveryOverrideFlag;
    @Column(name = "TAX_CODE_OVERRIDE_FLAG")
    private Integer taxCodeOverrideFlag;
    @Column(name = "TOTAL_DIST_AMOUNT")
    private Integer totalDistAmount;
    @Column(name = "TOTAL_DIST_BASE_AMOUNT")
    private Integer totalDistBaseAmount;
    @Column(name = "PREPAY_TAX_PARENT_ID")
    private Integer prepayTaxParentId;
    @Column(name = "CANCELLED_FLAG")
    private Integer cancelledFlag;
    @Column(name = "OLD_DISTRIBUTION_ID")
    private Integer oldDistributionId;
    @Column(name = "OLD_DIST_LINE_NUMBER")
    private Integer oldDistLineNumber;
    @Column(name = "AMOUNT_VARIANCE")
    private Integer amountVariance;
    @Column(name = "BASE_AMOUNT_VARIANCE")
    private Integer baseAmountVariance;
    @Column(name = "HISTORICAL_FLAG")
    private Integer historicalFlag;
    @Column(name = "RCV_CHARGE_ADDITION_FLAG")
    private Integer rcvChargeAdditionFlag;
    @Column(name = "AWT_RELATED_ID")
    private Integer awtRelatedId;
    @Column(name = "RELATED_RETAINAGE_DIST_ID")
    private Integer relatedRetainageDistId;
    @Column(name = "RETAINED_AMOUNT_REMAINING")
    private Integer retainedAmountRemaining;
    @Column(name = "BC_EVENT_ID")
    private Integer bcEventId;
    @Column(name = "RETAINED_INVOICE_DIST_ID")
    private Integer retainedInvoiceDistId;
    @Column(name = "FINAL_RELEASE_ROUNDING")
    private Integer finalReleaseRounding;
    @Column(name = "FULLY_PAID_ACCTD_FLAG")
    private Integer fullyPaidAcctdFlag;
    @Column(name = "ROOT_DISTRIBUTION_ID")
    private Integer rootDistributionId;
    @Column(name = "XINV_PARENT_REVERSAL_ID")
    private Integer xinvParentReversalId;
    @Column(name = "RECURRING_PAYMENT_ID")
    private Integer recurringPaymentId;
    @Column(name = "RELEASE_INV_DIST_DERIVED_FROM")
    private Integer releaseInvDistDerivedFrom;
    @Column(name = "PAY_AWT_GROUP_ID")
    private Integer payAwtGroupId;
    @Column(name = "SOURCE_HEADER_ID")
    private Integer sourceHeaderId;
    @Column(name = "SOURCE_LINE_ID")
    private Integer sourceLineId;

    @Column(name = "ADDITIONAL_EXPENSES_FLAG")
    private boolean additionalExpensesFlag;

    @Enumerated(EnumType.STRING)
    @Column(name = "LINE_TYPE")
    private InvoiceLineType lineType ;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount ;

    @Column(name = "QUANTITY_INVOICED")
    private BigDecimal quantityInvoiced = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private ApInvoice invoice;

    @OneToOne
    @JoinColumn(name = "PO_DISTRIBUTION_ID")
    private PoLine poLine;

    public ApInvoiceLine() {
    }

    public ApInvoiceLine(PoLine line) {

        this.poLine = line;

        this.unitPrice = line.getListPricePerUnit();

        SupplierAcc supplierAcc = poLine.getPoHeader().getClientSite().getSupplierAcc();

        if (supplierAcc.getInvoiceMatchingOption() == InvoiceMatchingOptions.PURCHASE_ORDER) {
          
            this.quantityInvoiced = line.getQuantityOrdered();
        
        } else {
        
            this.quantityInvoiced = line.getQuantityReceived().subtract(line.getInvoicedQuantity());
        }

        this.amount = this.quantityInvoiced.multiply(unitPrice);
    }

    public void setLineType(InvoiceLineType lineType) {
        this.lineType = lineType;
    }

    public InvoiceLineType getLineType() {
        return lineType;
    }

    
    public Integer getInvoiceDistributionId() {
        return invoiceDistributionId;
    }


    public void setInvoiceDistributionId(Integer invoiceDistributionId) {
        this.invoiceDistributionId = invoiceDistributionId;
    }

    public boolean isAdditionalExpensesFlag() {
        return additionalExpensesFlag;
    }

    public void setAdditionalExpensesFlag(boolean additionalExpensesFlag) {
        this.additionalExpensesFlag = additionalExpensesFlag;
    }



    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public Integer getAccrualPostedFlag() {
        return accrualPostedFlag;
    }

    public void setAccrualPostedFlag(Integer accrualPostedFlag) {
        this.accrualPostedFlag = accrualPostedFlag;
    }

    public Integer getAssetsAdditionFlag() {
        return assetsAdditionFlag;
    }

    public void setAssetsAdditionFlag(Integer assetsAdditionFlag) {
        this.assetsAdditionFlag = assetsAdditionFlag;
    }

    public Integer getAssetsTrackingFlag() {
        return assetsTrackingFlag;
    }

    public void setAssetsTrackingFlag(Integer assetsTrackingFlag) {
        this.assetsTrackingFlag = assetsTrackingFlag;
    }

    public Integer getCashPostedFlag() {
        return cashPostedFlag;
    }

    public void setCashPostedFlag(Integer cashPostedFlag) {
        this.cashPostedFlag = cashPostedFlag;
    }

    public Integer getDistributionLineNumber() {
        return distributionLineNumber;
    }

    public void setDistributionLineNumber(Integer distributionLineNumber) {
        this.distributionLineNumber = distributionLineNumber;
    }

    public Integer getDistCodeCombinationId() {
        return distCodeCombinationId;
    }

    public void setDistCodeCombinationId(Integer distCodeCombinationId) {
        this.distCodeCombinationId = distCodeCombinationId;
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

    public String getLineTypeLookupCode() {
        return lineTypeLookupCode;
    }

    public void setLineTypeLookupCode(String lineTypeLookupCode) {
        this.lineTypeLookupCode = lineTypeLookupCode;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
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

    public Integer getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Integer getBaseInvoicePriceVariance() {
        return baseInvoicePriceVariance;
    }

    public void setBaseInvoicePriceVariance(Integer baseInvoicePriceVariance) {
        this.baseInvoicePriceVariance = baseInvoicePriceVariance;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExchangeRateVariance() {
        return exchangeRateVariance;
    }

    public void setExchangeRateVariance(Integer exchangeRateVariance) {
        this.exchangeRateVariance = exchangeRateVariance;
    }

    public Integer getFinalMatchFlag() {
        return finalMatchFlag;
    }

    public void setFinalMatchFlag(Integer finalMatchFlag) {
        this.finalMatchFlag = finalMatchFlag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantityInvoiced() {
        return quantityInvoiced;
    }

    public void setQuantityInvoiced(BigDecimal quantityInvoiced) {
        this.quantityInvoiced = quantityInvoiced;
    }

    public ApInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(ApInvoice invoice) {
        this.invoice = invoice;
    }

    public PoLine getPoLine() {
        return poLine;
    }

    public void setPoLine(PoLine poLine) {
        this.poLine = poLine;
    }

    public String getIncomeTaxRegion() {
        return incomeTaxRegion;
    }

    public void setIncomeTaxRegion(String incomeTaxRegion) {
        this.incomeTaxRegion = incomeTaxRegion;
    }

    public Integer getInvoicePriceVariance() {
        return invoicePriceVariance;
    }

    public void setInvoicePriceVariance(Integer invoicePriceVariance) {
        this.invoicePriceVariance = invoicePriceVariance;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getMatchStatusFlag() {
        return matchStatusFlag;
    }

    public void setMatchStatusFlag(Integer matchStatusFlag) {
        this.matchStatusFlag = matchStatusFlag;
    }

    public Integer getPostedFlag() {
        return postedFlag;
    }

    public void setPostedFlag(Integer postedFlag) {
        this.postedFlag = postedFlag;
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

    public Integer getRateVarCodeCombinationId() {
        return rateVarCodeCombinationId;
    }

    public void setRateVarCodeCombinationId(Integer rateVarCodeCombinationId) {
        this.rateVarCodeCombinationId = rateVarCodeCombinationId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getReversalFlag() {
        return reversalFlag;
    }

    public void setReversalFlag(Integer reversalFlag) {
        this.reversalFlag = reversalFlag;
    }

    public String getType1099() {
        return type1099;
    }

    public void setType1099(String type1099) {
        this.type1099 = type1099;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmountEncumbered() {
        return amountEncumbered;
    }

    public void setAmountEncumbered(Integer amountEncumbered) {
        this.amountEncumbered = amountEncumbered;
    }

    public Integer getBaseAmountEncumbered() {
        return baseAmountEncumbered;
    }

    public void setBaseAmountEncumbered(Integer baseAmountEncumbered) {
        this.baseAmountEncumbered = baseAmountEncumbered;
    }

    public Integer getEncumberedFlag() {
        return encumberedFlag;
    }

    public void setEncumberedFlag(Integer encumberedFlag) {
        this.encumberedFlag = encumberedFlag;
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

    public Integer getPriceAdjustmentFlag() {
        return priceAdjustmentFlag;
    }

    public void setPriceAdjustmentFlag(Integer priceAdjustmentFlag) {
        this.priceAdjustmentFlag = priceAdjustmentFlag;
    }

    public Integer getPriceVarCodeCombinationId() {
        return priceVarCodeCombinationId;
    }

    public void setPriceVarCodeCombinationId(Integer priceVarCodeCombinationId) {
        this.priceVarCodeCombinationId = priceVarCodeCombinationId;
    }

    public Integer getQuantityUnencumbered() {
        return quantityUnencumbered;
    }

    public void setQuantityUnencumbered(Integer quantityUnencumbered) {
        this.quantityUnencumbered = quantityUnencumbered;
    }

    public Integer getStatAmount() {
        return statAmount;
    }

    public void setStatAmount(Integer statAmount) {
        this.statAmount = statAmount;
    }

    public Integer getAmountToPost() {
        return amountToPost;
    }

    public void setAmountToPost(Integer amountToPost) {
        this.amountToPost = amountToPost;
    }

    public Integer getBaseAmountToPost() {
        return baseAmountToPost;
    }

    public void setBaseAmountToPost(Integer baseAmountToPost) {
        this.baseAmountToPost = baseAmountToPost;
    }

    public Integer getCashJeBatchId() {
        return cashJeBatchId;
    }

    public void setCashJeBatchId(Integer cashJeBatchId) {
        this.cashJeBatchId = cashJeBatchId;
    }

    public Date getExpenditureItemDate() {
        return expenditureItemDate;
    }

    public void setExpenditureItemDate(Date expenditureItemDate) {
        this.expenditureItemDate = expenditureItemDate;
    }

    public Integer getExpenditureOrganizationId() {
        return expenditureOrganizationId;
    }

    public void setExpenditureOrganizationId(Integer expenditureOrganizationId) {
        this.expenditureOrganizationId = expenditureOrganizationId;
    }

    public String getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(String expenditureType) {
        this.expenditureType = expenditureType;
    }

    public Integer getJeBatchId() {
        return jeBatchId;
    }

    public void setJeBatchId(Integer jeBatchId) {
        this.jeBatchId = jeBatchId;
    }

    public Integer getParentInvoiceId() {
        return parentInvoiceId;
    }

    public void setParentInvoiceId(Integer parentInvoiceId) {
        this.parentInvoiceId = parentInvoiceId;
    }

    public Integer getPaAdditionFlag() {
        return paAdditionFlag;
    }

    public void setPaAdditionFlag(Integer paAdditionFlag) {
        this.paAdditionFlag = paAdditionFlag;
    }

    public Integer getPaQuantity() {
        return paQuantity;
    }

    public void setPaQuantity(Integer paQuantity) {
        this.paQuantity = paQuantity;
    }

    public Integer getPostedAmount() {
        return postedAmount;
    }

    public void setPostedAmount(Integer postedAmount) {
        this.postedAmount = postedAmount;
    }

    public Integer getPostedBaseAmount() {
        return postedBaseAmount;
    }

    public void setPostedBaseAmount(Integer postedBaseAmount) {
        this.postedBaseAmount = postedBaseAmount;
    }

    public Integer getPrepayAmountRemaining() {
        return prepayAmountRemaining;
    }

    public void setPrepayAmountRemaining(Integer prepayAmountRemaining) {
        this.prepayAmountRemaining = prepayAmountRemaining;
    }

    public String getProjectAccountingContext() {
        return projectAccountingContext;
    }

    public void setProjectAccountingContext(String projectAccountingContext) {
        this.projectAccountingContext = projectAccountingContext;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getUssglTransactionCode() {
        return ussglTransactionCode;
    }

    public void setUssglTransactionCode(String ussglTransactionCode) {
        this.ussglTransactionCode = ussglTransactionCode;
    }

    public String getUssglTrxCodeContext() {
        return ussglTrxCodeContext;
    }

    public void setUssglTrxCodeContext(String ussglTrxCodeContext) {
        this.ussglTrxCodeContext = ussglTrxCodeContext;
    }

    public Date getEarliestSettlementDate() {
        return earliestSettlementDate;
    }

    public void setEarliestSettlementDate(Date earliestSettlementDate) {
        this.earliestSettlementDate = earliestSettlementDate;
    }

    public Integer getReqDistributionId() {
        return reqDistributionId;
    }

    public void setReqDistributionId(Integer reqDistributionId) {
        this.reqDistributionId = reqDistributionId;
    }

    public Integer getQuantityVariance() {
        return quantityVariance;
    }

    public void setQuantityVariance(Integer quantityVariance) {
        this.quantityVariance = quantityVariance;
    }

    public Integer getBaseQuantityVariance() {
        return baseQuantityVariance;
    }

    public void setBaseQuantityVariance(Integer baseQuantityVariance) {
        this.baseQuantityVariance = baseQuantityVariance;
    }

    public Integer getPacketId() {
        return packetId;
    }

    public void setPacketId(Integer packetId) {
        this.packetId = packetId;
    }

    public Integer getAwtFlag() {
        return awtFlag;
    }

    public void setAwtFlag(Integer awtFlag) {
        this.awtFlag = awtFlag;
    }

    public Integer getAwtGroupId() {
        return awtGroupId;
    }

    public void setAwtGroupId(Integer awtGroupId) {
        this.awtGroupId = awtGroupId;
    }

    public Integer getAwtTaxRateId() {
        return awtTaxRateId;
    }

    public void setAwtTaxRateId(Integer awtTaxRateId) {
        this.awtTaxRateId = awtTaxRateId;
    }

    public Integer getAwtGrossAmount() {
        return awtGrossAmount;
    }

    public void setAwtGrossAmount(Integer awtGrossAmount) {
        this.awtGrossAmount = awtGrossAmount;
    }

    public Integer getAwtInvoiceId() {
        return awtInvoiceId;
    }

    public void setAwtInvoiceId(Integer awtInvoiceId) {
        this.awtInvoiceId = awtInvoiceId;
    }

    public Integer getAwtOriginGroupId() {
        return awtOriginGroupId;
    }

    public void setAwtOriginGroupId(Integer awtOriginGroupId) {
        this.awtOriginGroupId = awtOriginGroupId;
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOtherInvoiceId() {
        return otherInvoiceId;
    }

    public void setOtherInvoiceId(Integer otherInvoiceId) {
        this.otherInvoiceId = otherInvoiceId;
    }

    public Integer getAwtInvoicePaymentId() {
        return awtInvoicePaymentId;
    }

    public void setAwtInvoicePaymentId(Integer awtInvoicePaymentId) {
        this.awtInvoicePaymentId = awtInvoicePaymentId;
    }

    public Integer getLineGroupNumber() {
        return lineGroupNumber;
    }

    public void setLineGroupNumber(Integer lineGroupNumber) {
        this.lineGroupNumber = lineGroupNumber;
    }

    public Integer getReceiptVerifiedFlag() {
        return receiptVerifiedFlag;
    }

    public void setReceiptVerifiedFlag(Integer receiptVerifiedFlag) {
        this.receiptVerifiedFlag = receiptVerifiedFlag;
    }

    public Integer getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(Integer receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public Integer getReceiptMissingFlag() {
        return receiptMissingFlag;
    }

    public void setReceiptMissingFlag(Integer receiptMissingFlag) {
        this.receiptMissingFlag = receiptMissingFlag;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getExpenseGroup() {
        return expenseGroup;
    }

    public void setExpenseGroup(String expenseGroup) {
        this.expenseGroup = expenseGroup;
    }

    public Date getStartExpenseDate() {
        return startExpenseDate;
    }

    public void setStartExpenseDate(Date startExpenseDate) {
        this.startExpenseDate = startExpenseDate;
    }

    public Date getEndExpenseDate() {
        return endExpenseDate;
    }

    public void setEndExpenseDate(Date endExpenseDate) {
        this.endExpenseDate = endExpenseDate;
    }

    public String getReceiptCurrencyCode() {
        return receiptCurrencyCode;
    }

    public void setReceiptCurrencyCode(String receiptCurrencyCode) {
        this.receiptCurrencyCode = receiptCurrencyCode;
    }

    public Integer getReceiptConversionRate() {
        return receiptConversionRate;
    }

    public void setReceiptConversionRate(Integer receiptConversionRate) {
        this.receiptConversionRate = receiptConversionRate;
    }

    public Integer getReceiptCurrencyAmount() {
        return receiptCurrencyAmount;
    }

    public void setReceiptCurrencyAmount(Integer receiptCurrencyAmount) {
        this.receiptCurrencyAmount = receiptCurrencyAmount;
    }

    public Integer getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(Integer dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public Integer getWebParameterId() {
        return webParameterId;
    }

    public void setWebParameterId(Integer webParameterId) {
        this.webParameterId = webParameterId;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Integer getMrcAccrualPostedFlag() {
        return mrcAccrualPostedFlag;
    }

    public void setMrcAccrualPostedFlag(Integer mrcAccrualPostedFlag) {
        this.mrcAccrualPostedFlag = mrcAccrualPostedFlag;
    }

    public Integer getMrcCashPostedFlag() {
        return mrcCashPostedFlag;
    }

    public void setMrcCashPostedFlag(Integer mrcCashPostedFlag) {
        this.mrcCashPostedFlag = mrcCashPostedFlag;
    }

    public String getMrcDistCodeCombinationId() {
        return mrcDistCodeCombinationId;
    }

    public void setMrcDistCodeCombinationId(String mrcDistCodeCombinationId) {
        this.mrcDistCodeCombinationId = mrcDistCodeCombinationId;
    }

    public String getMrcAmount() {
        return mrcAmount;
    }

    public void setMrcAmount(String mrcAmount) {
        this.mrcAmount = mrcAmount;
    }

    public String getMrcBaseAmount() {
        return mrcBaseAmount;
    }

    public void setMrcBaseAmount(String mrcBaseAmount) {
        this.mrcBaseAmount = mrcBaseAmount;
    }

    public String getMrcBaseInvPriceVariance() {
        return mrcBaseInvPriceVariance;
    }

    public void setMrcBaseInvPriceVariance(String mrcBaseInvPriceVariance) {
        this.mrcBaseInvPriceVariance = mrcBaseInvPriceVariance;
    }

    public String getMrcExchangeRateVariance() {
        return mrcExchangeRateVariance;
    }

    public void setMrcExchangeRateVariance(String mrcExchangeRateVariance) {
        this.mrcExchangeRateVariance = mrcExchangeRateVariance;
    }

    public Integer getMrcPostedFlag() {
        return mrcPostedFlag;
    }

    public void setMrcPostedFlag(Integer mrcPostedFlag) {
        this.mrcPostedFlag = mrcPostedFlag;
    }

    public String getMrcProgramApplicationId() {
        return mrcProgramApplicationId;
    }

    public void setMrcProgramApplicationId(String mrcProgramApplicationId) {
        this.mrcProgramApplicationId = mrcProgramApplicationId;
    }

    public String getMrcProgramId() {
        return mrcProgramId;
    }

    public void setMrcProgramId(String mrcProgramId) {
        this.mrcProgramId = mrcProgramId;
    }

    public String getMrcProgramUpdateDate() {
        return mrcProgramUpdateDate;
    }

    public void setMrcProgramUpdateDate(String mrcProgramUpdateDate) {
        this.mrcProgramUpdateDate = mrcProgramUpdateDate;
    }

    public String getMrcRateVarCcid() {
        return mrcRateVarCcid;
    }

    public void setMrcRateVarCcid(String mrcRateVarCcid) {
        this.mrcRateVarCcid = mrcRateVarCcid;
    }

    public String getMrcRequestId() {
        return mrcRequestId;
    }

    public void setMrcRequestId(String mrcRequestId) {
        this.mrcRequestId = mrcRequestId;
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

    public String getMrcAmountToPost() {
        return mrcAmountToPost;
    }

    public void setMrcAmountToPost(String mrcAmountToPost) {
        this.mrcAmountToPost = mrcAmountToPost;
    }

    public String getMrcBaseAmountToPost() {
        return mrcBaseAmountToPost;
    }

    public void setMrcBaseAmountToPost(String mrcBaseAmountToPost) {
        this.mrcBaseAmountToPost = mrcBaseAmountToPost;
    }

    public String getMrcCashJeBatchId() {
        return mrcCashJeBatchId;
    }

    public void setMrcCashJeBatchId(String mrcCashJeBatchId) {
        this.mrcCashJeBatchId = mrcCashJeBatchId;
    }

    public String getMrcJeBatchId() {
        return mrcJeBatchId;
    }

    public void setMrcJeBatchId(String mrcJeBatchId) {
        this.mrcJeBatchId = mrcJeBatchId;
    }

    public String getMrcPostedAmount() {
        return mrcPostedAmount;
    }

    public void setMrcPostedAmount(String mrcPostedAmount) {
        this.mrcPostedAmount = mrcPostedAmount;
    }

    public String getMrcPostedBaseAmount() {
        return mrcPostedBaseAmount;
    }

    public void setMrcPostedBaseAmount(String mrcPostedBaseAmount) {
        this.mrcPostedBaseAmount = mrcPostedBaseAmount;
    }

    public String getMrcReceiptConversionRate() {
        return mrcReceiptConversionRate;
    }

    public void setMrcReceiptConversionRate(String mrcReceiptConversionRate) {
        this.mrcReceiptConversionRate = mrcReceiptConversionRate;
    }

    public Integer getCreditCardTrxId() {
        return creditCardTrxId;
    }

    public void setCreditCardTrxId(Integer creditCardTrxId) {
        this.creditCardTrxId = creditCardTrxId;
    }

    public String getDistMatchType() {
        return distMatchType;
    }

    public void setDistMatchType(String distMatchType) {
        this.distMatchType = distMatchType;
    }

    public Integer getRcvTransactionId() {
        return rcvTransactionId;
    }

    public void setRcvTransactionId(Integer rcvTransactionId) {
        this.rcvTransactionId = rcvTransactionId;
    }

    public Integer getParentReversalId() {
        return parentReversalId;
    }

    public void setParentReversalId(Integer parentReversalId) {
        this.parentReversalId = parentReversalId;
    }

    public Integer getTaxRecoverableFlag() {
        return taxRecoverableFlag;
    }

    public void setTaxRecoverableFlag(Integer taxRecoverableFlag) {
        this.taxRecoverableFlag = taxRecoverableFlag;
    }

    public Integer getPaCcArInvoiceId() {
        return paCcArInvoiceId;
    }

    public void setPaCcArInvoiceId(Integer paCcArInvoiceId) {
        this.paCcArInvoiceId = paCcArInvoiceId;
    }

    public Integer getPaCcArInvoiceLineNum() {
        return paCcArInvoiceLineNum;
    }

    public void setPaCcArInvoiceLineNum(Integer paCcArInvoiceLineNum) {
        this.paCcArInvoiceLineNum = paCcArInvoiceLineNum;
    }

    public Integer getPaCcProcessedCode() {
        return paCcProcessedCode;
    }

    public void setPaCcProcessedCode(Integer paCcProcessedCode) {
        this.paCcProcessedCode = paCcProcessedCode;
    }

    public String getMerchantDocumentNumber() {
        return merchantDocumentNumber;
    }

    public void setMerchantDocumentNumber(String merchantDocumentNumber) {
        this.merchantDocumentNumber = merchantDocumentNumber;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public String getMerchantTaxRegNumber() {
        return merchantTaxRegNumber;
    }

    public void setMerchantTaxRegNumber(String merchantTaxRegNumber) {
        this.merchantTaxRegNumber = merchantTaxRegNumber;
    }

    public String getMerchantTaxpayerId() {
        return merchantTaxpayerId;
    }

    public void setMerchantTaxpayerId(String merchantTaxpayerId) {
        this.merchantTaxpayerId = merchantTaxpayerId;
    }

    public String getCountryOfSupply() {
        return countryOfSupply;
    }

    public void setCountryOfSupply(String countryOfSupply) {
        this.countryOfSupply = countryOfSupply;
    }

    public String getMatchedUomLookupCode() {
        return matchedUomLookupCode;
    }

    public void setMatchedUomLookupCode(String matchedUomLookupCode) {
        this.matchedUomLookupCode = matchedUomLookupCode;
    }

    public Integer getGmsBurdenableRawCost() {
        return gmsBurdenableRawCost;
    }

    public void setGmsBurdenableRawCost(Integer gmsBurdenableRawCost) {
        this.gmsBurdenableRawCost = gmsBurdenableRawCost;
    }

    public Integer getAccountingEventId() {
        return accountingEventId;
    }

    public void setAccountingEventId(Integer accountingEventId) {
        this.accountingEventId = accountingEventId;
    }

    public Integer getPrepayDistributionId() {
        return prepayDistributionId;
    }

    public void setPrepayDistributionId(Integer prepayDistributionId) {
        this.prepayDistributionId = prepayDistributionId;
    }

    public Integer getUpgradePostedAmt() {
        return upgradePostedAmt;
    }

    public void setUpgradePostedAmt(Integer upgradePostedAmt) {
        this.upgradePostedAmt = upgradePostedAmt;
    }

    public Integer getUpgradeBasePostedAmt() {
        return upgradeBasePostedAmt;
    }

    public void setUpgradeBasePostedAmt(Integer upgradeBasePostedAmt) {
        this.upgradeBasePostedAmt = upgradeBasePostedAmt;
    }

    public Integer getInventoryTransferStatus() {
        return inventoryTransferStatus;
    }

    public void setInventoryTransferStatus(Integer inventoryTransferStatus) {
        this.inventoryTransferStatus = inventoryTransferStatus;
    }

    public Integer getCompanyPrepaidInvoiceId() {
        return companyPrepaidInvoiceId;
    }

    public void setCompanyPrepaidInvoiceId(Integer companyPrepaidInvoiceId) {
        this.companyPrepaidInvoiceId = companyPrepaidInvoiceId;
    }

    public Integer getCcReversalFlag() {
        return ccReversalFlag;
    }

    public void setCcReversalFlag(Integer ccReversalFlag) {
        this.ccReversalFlag = ccReversalFlag;
    }

    public Integer getAwtWithheldAmt() {
        return awtWithheldAmt;
    }

    public void setAwtWithheldAmt(Integer awtWithheldAmt) {
        this.awtWithheldAmt = awtWithheldAmt;
    }

    public Integer getInvoiceIncludesPrepayFlag() {
        return invoiceIncludesPrepayFlag;
    }

    public void setInvoiceIncludesPrepayFlag(Integer invoiceIncludesPrepayFlag) {
        this.invoiceIncludesPrepayFlag = invoiceIncludesPrepayFlag;
    }

    public Integer getPriceCorrectInvId() {
        return priceCorrectInvId;
    }

    public void setPriceCorrectInvId(Integer priceCorrectInvId) {
        this.priceCorrectInvId = priceCorrectInvId;
    }

    public Integer getPriceCorrectQty() {
        return priceCorrectQty;
    }

    public void setPriceCorrectQty(Integer priceCorrectQty) {
        this.priceCorrectQty = priceCorrectQty;
    }

    public Integer getPaCmtXfaceFlag() {
        return paCmtXfaceFlag;
    }

    public void setPaCmtXfaceFlag(Integer paCmtXfaceFlag) {
        this.paCmtXfaceFlag = paCmtXfaceFlag;
    }

    public Integer getCancellationFlag() {
        return cancellationFlag;
    }

    public void setCancellationFlag(Integer cancellationFlag) {
        this.cancellationFlag = cancellationFlag;
    }

    public Integer getInvoiceLineNumber() {
        return invoiceLineNumber;
    }

    public void setInvoiceLineNumber(Integer invoiceLineNumber) {
        this.invoiceLineNumber = invoiceLineNumber;
    }

    public Integer getCorrectedInvoiceDistId() {
        return correctedInvoiceDistId;
    }

    public void setCorrectedInvoiceDistId(Integer correctedInvoiceDistId) {
        this.correctedInvoiceDistId = correctedInvoiceDistId;
    }

    public Integer getRoundingAmt() {
        return roundingAmt;
    }

    public void setRoundingAmt(Integer roundingAmt) {
        this.roundingAmt = roundingAmt;
    }

    public Integer getChargeApplicableToDistId() {
        return chargeApplicableToDistId;
    }

    public void setChargeApplicableToDistId(Integer chargeApplicableToDistId) {
        this.chargeApplicableToDistId = chargeApplicableToDistId;
    }

    public Integer getCorrectedQuantity() {
        return correctedQuantity;
    }

    public void setCorrectedQuantity(Integer correctedQuantity) {
        this.correctedQuantity = correctedQuantity;
    }

    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public String getAssetBookTypeCode() {
        return assetBookTypeCode;
    }

    public void setAssetBookTypeCode(String assetBookTypeCode) {
        this.assetBookTypeCode = assetBookTypeCode;
    }

    public Integer getAssetCategoryId() {
        return assetCategoryId;
    }

    public void setAssetCategoryId(Integer assetCategoryId) {
        this.assetCategoryId = assetCategoryId;
    }

    public String getDistributionClass() {
        return distributionClass;
    }

    public void setDistributionClass(String distributionClass) {
        this.distributionClass = distributionClass;
    }

    public Integer getFinalPaymentRounding() {
        return finalPaymentRounding;
    }

    public void setFinalPaymentRounding(Integer finalPaymentRounding) {
        this.finalPaymentRounding = finalPaymentRounding;
    }

    public Integer getFinalApplicationRounding() {
        return finalApplicationRounding;
    }

    public void setFinalApplicationRounding(Integer finalApplicationRounding) {
        this.finalApplicationRounding = finalApplicationRounding;
    }

    public Integer getAmountAtPrepayXrate() {
        return amountAtPrepayXrate;
    }

    public void setAmountAtPrepayXrate(Integer amountAtPrepayXrate) {
        this.amountAtPrepayXrate = amountAtPrepayXrate;
    }

    public Integer getCashBasisFinalAppRounding() {
        return cashBasisFinalAppRounding;
    }

    public void setCashBasisFinalAppRounding(Integer cashBasisFinalAppRounding) {
        this.cashBasisFinalAppRounding = cashBasisFinalAppRounding;
    }

    public Integer getAmountAtPrepayPayXrate() {
        return amountAtPrepayPayXrate;
    }

    public void setAmountAtPrepayPayXrate(Integer amountAtPrepayPayXrate) {
        this.amountAtPrepayPayXrate = amountAtPrepayPayXrate;
    }

    public String getIntendedUse() {
        return intendedUse;
    }

    public void setIntendedUse(String intendedUse) {
        this.intendedUse = intendedUse;
    }

    public Integer getDetailTaxDistId() {
        return detailTaxDistId;
    }

    public void setDetailTaxDistId(Integer detailTaxDistId) {
        this.detailTaxDistId = detailTaxDistId;
    }

    public Integer getRecNrecRate() {
        return recNrecRate;
    }

    public void setRecNrecRate(Integer recNrecRate) {
        this.recNrecRate = recNrecRate;
    }

    public Integer getRecoveryRateId() {
        return recoveryRateId;
    }

    public void setRecoveryRateId(Integer recoveryRateId) {
        this.recoveryRateId = recoveryRateId;
    }

    public String getRecoveryRateName() {
        return recoveryRateName;
    }

    public void setRecoveryRateName(String recoveryRateName) {
        this.recoveryRateName = recoveryRateName;
    }

    public String getRecoveryTypeCode() {
        return recoveryTypeCode;
    }

    public void setRecoveryTypeCode(String recoveryTypeCode) {
        this.recoveryTypeCode = recoveryTypeCode;
    }

    public String getRecoveryRateCode() {
        return recoveryRateCode;
    }

    public void setRecoveryRateCode(String recoveryRateCode) {
        this.recoveryRateCode = recoveryRateCode;
    }

    public Integer getWithholdingTaxCodeId() {
        return withholdingTaxCodeId;
    }

    public void setWithholdingTaxCodeId(Integer withholdingTaxCodeId) {
        this.withholdingTaxCodeId = withholdingTaxCodeId;
    }

    public Integer getTaxAlreadyDistributedFlag() {
        return taxAlreadyDistributedFlag;
    }

    public void setTaxAlreadyDistributedFlag(Integer taxAlreadyDistributedFlag) {
        this.taxAlreadyDistributedFlag = taxAlreadyDistributedFlag;
    }

    public Integer getSummaryTaxLineId() {
        return summaryTaxLineId;
    }

    public void setSummaryTaxLineId(Integer summaryTaxLineId) {
        this.summaryTaxLineId = summaryTaxLineId;
    }

    public Integer getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Integer taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Integer getTaxableBaseAmount() {
        return taxableBaseAmount;
    }

    public void setTaxableBaseAmount(Integer taxableBaseAmount) {
        this.taxableBaseAmount = taxableBaseAmount;
    }

    public Integer getExtraPoErv() {
        return extraPoErv;
    }

    public void setExtraPoErv(Integer extraPoErv) {
        this.extraPoErv = extraPoErv;
    }

    public Integer getPrepayTaxDiffAmount() {
        return prepayTaxDiffAmount;
    }

    public void setPrepayTaxDiffAmount(Integer prepayTaxDiffAmount) {
        this.prepayTaxDiffAmount = prepayTaxDiffAmount;
    }

    public Integer getTaxCodeId() {
        return taxCodeId;
    }

    public void setTaxCodeId(Integer taxCodeId) {
        this.taxCodeId = taxCodeId;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public Integer getAmountIncludesTaxFlag() {
        return amountIncludesTaxFlag;
    }

    public void setAmountIncludesTaxFlag(Integer amountIncludesTaxFlag) {
        this.amountIncludesTaxFlag = amountIncludesTaxFlag;
    }

    public Integer getTaxCalculatedFlag() {
        return taxCalculatedFlag;
    }

    public void setTaxCalculatedFlag(Integer taxCalculatedFlag) {
        this.taxCalculatedFlag = taxCalculatedFlag;
    }

    public Integer getTaxRecoveryRate() {
        return taxRecoveryRate;
    }

    public void setTaxRecoveryRate(Integer taxRecoveryRate) {
        this.taxRecoveryRate = taxRecoveryRate;
    }

    public Integer getTaxRecoveryOverrideFlag() {
        return taxRecoveryOverrideFlag;
    }

    public void setTaxRecoveryOverrideFlag(Integer taxRecoveryOverrideFlag) {
        this.taxRecoveryOverrideFlag = taxRecoveryOverrideFlag;
    }

    public Integer getTaxCodeOverrideFlag() {
        return taxCodeOverrideFlag;
    }

    public void setTaxCodeOverrideFlag(Integer taxCodeOverrideFlag) {
        this.taxCodeOverrideFlag = taxCodeOverrideFlag;
    }

    public Integer getTotalDistAmount() {
        return totalDistAmount;
    }

    public void setTotalDistAmount(Integer totalDistAmount) {
        this.totalDistAmount = totalDistAmount;
    }

    public Integer getTotalDistBaseAmount() {
        return totalDistBaseAmount;
    }

    public void setTotalDistBaseAmount(Integer totalDistBaseAmount) {
        this.totalDistBaseAmount = totalDistBaseAmount;
    }

    public Integer getPrepayTaxParentId() {
        return prepayTaxParentId;
    }

    public void setPrepayTaxParentId(Integer prepayTaxParentId) {
        this.prepayTaxParentId = prepayTaxParentId;
    }

    public Integer getCancelledFlag() {
        return cancelledFlag;
    }

    public void setCancelledFlag(Integer cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }

    public Integer getOldDistributionId() {
        return oldDistributionId;
    }

    public void setOldDistributionId(Integer oldDistributionId) {
        this.oldDistributionId = oldDistributionId;
    }

    public Integer getOldDistLineNumber() {
        return oldDistLineNumber;
    }

    public void setOldDistLineNumber(Integer oldDistLineNumber) {
        this.oldDistLineNumber = oldDistLineNumber;
    }

    public Integer getAmountVariance() {
        return amountVariance;
    }

    public void setAmountVariance(Integer amountVariance) {
        this.amountVariance = amountVariance;
    }

    public Integer getBaseAmountVariance() {
        return baseAmountVariance;
    }

    public void setBaseAmountVariance(Integer baseAmountVariance) {
        this.baseAmountVariance = baseAmountVariance;
    }

    public Integer getHistoricalFlag() {
        return historicalFlag;
    }

    public void setHistoricalFlag(Integer historicalFlag) {
        this.historicalFlag = historicalFlag;
    }

    public Integer getRcvChargeAdditionFlag() {
        return rcvChargeAdditionFlag;
    }

    public void setRcvChargeAdditionFlag(Integer rcvChargeAdditionFlag) {
        this.rcvChargeAdditionFlag = rcvChargeAdditionFlag;
    }

    public Integer getAwtRelatedId() {
        return awtRelatedId;
    }

    public void setAwtRelatedId(Integer awtRelatedId) {
        this.awtRelatedId = awtRelatedId;
    }

    public Integer getRelatedRetainageDistId() {
        return relatedRetainageDistId;
    }

    public void setRelatedRetainageDistId(Integer relatedRetainageDistId) {
        this.relatedRetainageDistId = relatedRetainageDistId;
    }

    public Integer getRetainedAmountRemaining() {
        return retainedAmountRemaining;
    }

    public void setRetainedAmountRemaining(Integer retainedAmountRemaining) {
        this.retainedAmountRemaining = retainedAmountRemaining;
    }

    public Integer getBcEventId() {
        return bcEventId;
    }

    public void setBcEventId(Integer bcEventId) {
        this.bcEventId = bcEventId;
    }

    public Integer getRetainedInvoiceDistId() {
        return retainedInvoiceDistId;
    }

    public void setRetainedInvoiceDistId(Integer retainedInvoiceDistId) {
        this.retainedInvoiceDistId = retainedInvoiceDistId;
    }

    public Integer getFinalReleaseRounding() {
        return finalReleaseRounding;
    }

    public void setFinalReleaseRounding(Integer finalReleaseRounding) {
        this.finalReleaseRounding = finalReleaseRounding;
    }

    public Integer getFullyPaidAcctdFlag() {
        return fullyPaidAcctdFlag;
    }

    public void setFullyPaidAcctdFlag(Integer fullyPaidAcctdFlag) {
        this.fullyPaidAcctdFlag = fullyPaidAcctdFlag;
    }

    public Integer getRootDistributionId() {
        return rootDistributionId;
    }

    public void setRootDistributionId(Integer rootDistributionId) {
        this.rootDistributionId = rootDistributionId;
    }

    public Integer getXinvParentReversalId() {
        return xinvParentReversalId;
    }

    public void setXinvParentReversalId(Integer xinvParentReversalId) {
        this.xinvParentReversalId = xinvParentReversalId;
    }

    public Integer getRecurringPaymentId() {
        return recurringPaymentId;
    }

    public void setRecurringPaymentId(Integer recurringPaymentId) {
        this.recurringPaymentId = recurringPaymentId;
    }

    public Integer getReleaseInvDistDerivedFrom() {
        return releaseInvDistDerivedFrom;
    }

    public void setReleaseInvDistDerivedFrom(Integer releaseInvDistDerivedFrom) {
        this.releaseInvDistDerivedFrom = releaseInvDistDerivedFrom;
    }

    public Integer getPayAwtGroupId() {
        return payAwtGroupId;
    }

    public void setPayAwtGroupId(Integer payAwtGroupId) {
        this.payAwtGroupId = payAwtGroupId;
    }

    public Integer getSourceHeaderId() {
        return sourceHeaderId;
    }

    public void setSourceHeaderId(Integer sourceHeaderId) {
        this.sourceHeaderId = sourceHeaderId;
    }

    public Integer getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(Integer sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceDistributionId != null ? invoiceDistributionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApInvoiceLine)) {
            return false;
        }
        ApInvoiceLine other = (ApInvoiceLine) object;
        if ((this.invoiceDistributionId == null && other.invoiceDistributionId != null) || (this.invoiceDistributionId != null && !this.invoiceDistributionId.equals(other.invoiceDistributionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ApInvoiceDistributions[ invoiceDistributionId=" + invoiceDistributionId + " ]";
    }

}
