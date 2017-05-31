/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.enums.ApprovalLevel;
import com.bridge.enums.EnforceShip;
import com.bridge.enums.RcvException;
import com.bridge.enums.RecException;
import com.bridge.enums.RecRouting;
import com.bridge.enums.ClientType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "AP_SUPPLIERS")
@NamedQueries({
    @NamedQuery(name = "ApSupplier.findAll", query = "SELECT a FROM ApSupplier a")})
public class ApSupplier implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
           @GeneratedValue(strategy = GenerationType.TABLE,generator = "sup")
    @TableGenerator(name = "sup",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Supplier",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENDOR_ID")
    private Integer vendorId;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    @NotNull
    @Size(max = 240)
    @Column(name = "VENDOR_NAME")
    private String vendorName;
    @Size(max = 320)
    @Column(name = "VENDOR_NAME_ALT")
    private String vendorNameAlt;
    @Column(name = "ENABLED_FLAG_ID")
    private boolean enabledFlagId;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "VENDOR_TYPE_LOOKUP_CODE")
    private ClientType vendorTypeLookupCode;
    @Size(max = 25)
    @Column(name = "CUSTOMER_NUM")
    private String customerNum;
    @Column(name = "ONE_TIME_FLAG")
    private boolean oneTimeFlag;
    @Column(name = "PARENT_VENDOR_ID")
    private Integer parentVendorId;
    @Column(name = "MIN_ORDER_AMOUNT")
    private Integer minOrderAmount;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Column(name = "BILL_TO_LOCATION_ID")
    private Integer billToLocationId;
    @Size(max = 25)
    @Column(name = "SHIP_VIA_LOOKUP_CODE")
    private String shipViaLookupCode;
    @Size(max = 25)
    @Column(name = "FREIGHT_TERMS_LOOKUP_CODE")
    private String freightTermsLookupCode;
    @Size(max = 25)
    @Column(name = "FOB_LOOKUP_CODE")
    private String fobLookupCode;
    @Column(name = "TERMS_ID")
    private Integer termsId;
    @Column(name = "SET_OF_BOOKS_ID")
    private Integer setOfBooksId;
    @Size(max = 25)
    @Column(name = "CREDIT_STATUS_LOOKUP_CODE")
    private String creditStatusLookupCode;
    @Column(name = "CREDIT_LIMIT")
    private Integer creditLimit;
    @Column(name = "PAYMENT_PRIORITY")
    private Integer paymentPriority;
    @Size(max = 15)
    @Column(name = "INVOICE_CURRENCY_CODE")
    private String invoiceCurrencyCode;
    @Column(name = "PAYMENT_CURRENCY_CODE")
    private String paymentCurrencyCode;
    @Column(name = "PAYMENT_CURRENCY_ID")
    private Integer paymentCurrencyId;
    @Column(name = "INVOICE_AMOUNT_LIMIT")
    private Integer invoiceAmountLimit;
    @Column(name = "HOLD_ALL_PAYMENTS_FLAG")
    private boolean holdAllPaymentsFlag;
    @Column(name = "HOLD_FUTURE_PAYMENTS_FLAG")
    private boolean holdFuturePaymentsFlag;
    @Size(max = 240)
    @Column(name = "HOLD_REASON")
    private String holdReason;
    @Column(name = "PREPAY_CODE_COMBINATION_ID")
    private Integer prepayCodeCombinationId;
    @Size(max = 25)
    @Column(name = "WITHHOLDING_STATUS_LOOKUP_CODE")
    private String withholdingStatusLookupCode;
    @Column(name = "WITHHOLDING_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date withholdingStartDate;
    @Size(max = 25)
    @Column(name = "ORGANIZATION_TYPE_LOOKUP_CODE")
    private String organizationTypeLookupCode;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Column(name = "PAYMENT_METHOD_LOOKUP_CODE")
    private boolean paymentMethodLookupCode;
    @Size(max = 80)
    @Column(name = "BANK_ACCOUNT_NAME")
    private String bankAccountName;
    @Size(max = 30)
    @Column(name = "BANK_ACCOUNT_NUM")
    private String bankAccountNum;
    @Size(max = 25)
    @Column(name = "BANK_NUM")
    private String bankNum;
    @Size(max = 25)
    @Column(name = "BANK_ACCOUNT_TYPE")
    private String bankAccountType;
    @Column(name = "SMALL_BUSINESS_FLAG")
    private boolean smallBusinessFlag;
    @Size(max = 25)
    @Column(name = "STANDARD_INDUSTRY_CLASS")
    private String standardIndustryClass;
    @Column(name = "HOLD_FLAG")
    private boolean holdFlag;
    @Size(max = 240)
    @Column(name = "PURCHASING_HOLD_REASON")
    private String purchasingHoldReason;
    @Column(name = "HOLD_BY")
    private Integer holdBy;
    @Column(name = "HOLD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date holdDate;
    @Size(max = 25)
    @Column(name = "TERMS_DATE_BASIS")
    private String termsDateBasis;
    @Column(name = "INSPECTION_REQUIRED_FLAG")
    private boolean inspectionRequiredFlag;
    @Column(name = "RECEIPT_REQUIRED_FLAG")
    private boolean receiptRequiredFlag;
    @Column(name = "QTY_RCV_TOLERANCE")
    private Integer qtyRcvTolerance;
    @Column(name = "QTY_RCV_EXCEPTION_CODE")
    private RcvException qtyRcvExceptionCode;
    @Column(name = "DAYS_EARLY_RECEIPT_ALLOWED")
    private Integer daysEarlyReceiptAllowed;
    @Column(name = "DAYS_LATE_RECEIPT_ALLOWED")
    private Integer daysLateReceiptAllowed;
    @Column(name = "ALLOW_SUBSTITUTE_RECEIPTS_FLAG")
    private boolean allowSubstituteReceiptsFlag;
    @Column(name = "ALLOW_UNORDERED_RECEIPTS_FLAG")
    private boolean allowUnorderedReceiptsFlag;
    @Column(name = "HOLD_UNMATCHED_INVOICES_FLAG")
    private boolean holdUnmatchedInvoicesFlag;
    @Column(name = "EXCLUSIVE_PAYMENT_FLAG")
    private boolean exclusivePaymentFlag;
    @Size(max = 1)
    @Column(name = "AP_TAX_ROUNDING_RULE")
    private String apTaxRoundingRule;
    @Column(name = "AUTO_TAX_CALC_FLAG")
    private boolean autoTaxCalcFlag;
    @Size(max = 1)
    @Column(name = "AUTO_TAX_CALC_OVERRIDE")
    private String autoTaxCalcOverride;
    @Column(name = "AMOUNT_INCLUDES_TAX_FLAG")
    private boolean amountIncludesTaxFlag;
    @Column(name = "TAX_VERIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxVerificationDate;
    @Size(max = 4)
    @Column(name = "NAME_CONTROL")
    private String nameControl;
    @Column(name = "STATE_REPORTABLE_FLAG")
    private boolean stateReportableFlag;
    @Column(name = "FEDERAL_REPORTABLE_FLAG")
    private boolean federalReportableFlag;
    @Size(max = 30)
    @Column(name = "BANK_NUMBER")
    private String bankNumber;
    @Column(name = "ALLOW_AWT_FLAG")
    private boolean allowAwtFlag;
    @Column(name = "AWT_GROUP_ID")
    private Long awtGroupId;
    @Column(name = "CREATE_DEBIT_MEMO_FLAG")
    private boolean createDebitMemoFlag;
    @Column(name = "OFFSET_TAX_FLAG")
    private boolean offsetTaxFlag;
    @Column(name = "PARTY_ID")
    private Integer partyId;
    @Column(name = "PARENT_PARTY_ID")
    private Integer parentPartyId;
    @Size(max = 240)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 240)
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Size(max = 240)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 240)
    @Column(name = "TRADING_NAME")
    private String tradingName;
    @Size(max = 30)
    @Column(name = "COMPANY_REGISTRATION_NUMBER")
    private String companyRegistrationNumber;
    @Size(max = 30)
    @Column(name = "NATIONAL_INSURANCE_NUMBER")
    private String nationalInsuranceNumber;
    @Column(name="PURCHASING_HOLD_FLAG")
    private boolean purchasingHoldFlag;
    @Column(name="VENDOR_NUMBER")
    private String vendorNumber;
    @Column(name="ENFORCE_SHIP_TO_LOCATION")
    private EnforceShip enforceShipToLocation;
    @Column(name="RECEIPT_ROUTING")
    private RecRouting receiptRouting;
    @Column(name="MATCH_APPROVAL_LEVEL")
    private ApprovalLevel matchApprovalLevel;
    @Column(name="RECEIPT_DATE_EXCEPTION")
    private RecException receiptDateException;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private Set<ApSupplierSite> supplierSites;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private Set<SiteAccount> supplierSiteAcounts;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorNameAlt() {
        return vendorNameAlt;
    }

    public void setVendorNameAlt(String vendorNameAlt) {
        this.vendorNameAlt = vendorNameAlt;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }
    
    public boolean isEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
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

    public ClientType getVendorTypeLookupCode() {
        return vendorTypeLookupCode;
    }

    public void setVendorTypeLookupCode(ClientType vendorTypeLookupCode) {
        this.vendorTypeLookupCode = vendorTypeLookupCode;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public boolean getOneTimeFlag() {
        return oneTimeFlag;
    }

    public void setOneTimeFlag(boolean oneTimeFlag) {
        this.oneTimeFlag = oneTimeFlag;
    }

    public Integer getParentVendorId() {
        return parentVendorId;
    }

    public void setParentVendorId(Integer parentVendorId) {
        this.parentVendorId = parentVendorId;
    }

    public Integer getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Integer minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Integer getBillToLocationId() {
        return billToLocationId;
    }

    public void setBillToLocationId(Integer billToLocationId) {
        this.billToLocationId = billToLocationId;
    }

    public String getShipViaLookupCode() {
        return shipViaLookupCode;
    }

    public void setShipViaLookupCode(String shipViaLookupCode) {
        this.shipViaLookupCode = shipViaLookupCode;
    }

    public String getFreightTermsLookupCode() {
        return freightTermsLookupCode;
    }

    public void setFreightTermsLookupCode(String freightTermsLookupCode) {
        this.freightTermsLookupCode = freightTermsLookupCode;
    }

    public String getFobLookupCode() {
        return fobLookupCode;
    }

    public void setFobLookupCode(String fobLookupCode) {
        this.fobLookupCode = fobLookupCode;
    }

    public Integer getTermsId() {
        return termsId;
    }

    public void setTermsId(Integer termsId) {
        this.termsId = termsId;
    }

    public Integer getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(Integer setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public String getCreditStatusLookupCode() {
        return creditStatusLookupCode;
    }

    public void setCreditStatusLookupCode(String creditStatusLookupCode) {
        this.creditStatusLookupCode = creditStatusLookupCode;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getPaymentPriority() {
        return paymentPriority;
    }

    public void setPaymentPriority(Integer paymentPriority) {
        this.paymentPriority = paymentPriority;
    }

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
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
    
    

    public Integer getInvoiceAmountLimit() {
        return invoiceAmountLimit;
    }

    public void setInvoiceAmountLimit(Integer invoiceAmountLimit) {
        this.invoiceAmountLimit = invoiceAmountLimit;
    }

    public boolean getHoldAllPaymentsFlag() {
        return holdAllPaymentsFlag;
    }

    public void setHoldAllPaymentsFlag(boolean holdAllPaymentsFlag) {
        this.holdAllPaymentsFlag = holdAllPaymentsFlag;
    }

    public boolean getHoldFuturePaymentsFlag() {
        return holdFuturePaymentsFlag;
    }

    public void setHoldFuturePaymentsFlag(boolean holdFuturePaymentsFlag) {
        this.holdFuturePaymentsFlag = holdFuturePaymentsFlag;
    }

    public String getHoldReason() {
        return holdReason;
    }

    public void setHoldReason(String holdReason) {
        this.holdReason = holdReason;
    }

    public Integer getPrepayCodeCombinationId() {
        return prepayCodeCombinationId;
    }

    public void setPrepayCodeCombinationId(Integer prepayCodeCombinationId) {
        this.prepayCodeCombinationId = prepayCodeCombinationId;
    }

    public String getWithholdingStatusLookupCode() {
        return withholdingStatusLookupCode;
    }

    public void setWithholdingStatusLookupCode(String withholdingStatusLookupCode) {
        this.withholdingStatusLookupCode = withholdingStatusLookupCode;
    }

    public Date getWithholdingStartDate() {
        return withholdingStartDate;
    }

    public void setWithholdingStartDate(Date withholdingStartDate) {
        this.withholdingStartDate = withholdingStartDate;
    }

    public String getOrganizationTypeLookupCode() {
        return organizationTypeLookupCode;
    }

    public void setOrganizationTypeLookupCode(String organizationTypeLookupCode) {
        this.organizationTypeLookupCode = organizationTypeLookupCode;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public boolean getPaymentMethodLookupCode() {
        return paymentMethodLookupCode;
    }

    public void setPaymentMethodLookupCode(boolean paymentMethodLookupCode) {
        this.paymentMethodLookupCode = paymentMethodLookupCode;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public boolean getSmallBusinessFlag() {
        return smallBusinessFlag;
    }

    public void setSmallBusinessFlag(boolean smallBusinessFlag) {
        this.smallBusinessFlag = smallBusinessFlag;
    }

    public String getStandardIndustryClass() {
        return standardIndustryClass;
    }

    public void setStandardIndustryClass(String standardIndustryClass) {
        this.standardIndustryClass = standardIndustryClass;
    }

    public boolean getHoldFlag() {
        return holdFlag;
    }

    public void setHoldFlag(boolean holdFlag) {
        this.holdFlag = holdFlag;
    }

    public String getPurchasingHoldReason() {
        return purchasingHoldReason;
    }

    public void setPurchasingHoldReason(String purchasingHoldReason) {
        this.purchasingHoldReason = purchasingHoldReason;
    }

    public Integer getHoldBy() {
        return holdBy;
    }

    public void setHoldBy(Integer holdBy) {
        this.holdBy = holdBy;
    }

    public Date getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    public String getTermsDateBasis() {
        return termsDateBasis;
    }

    public void setTermsDateBasis(String termsDateBasis) {
        this.termsDateBasis = termsDateBasis;
    }

    public boolean getInspectionRequiredFlag() {
        return inspectionRequiredFlag;
    }

    public void setInspectionRequiredFlag(boolean inspectionRequiredFlag) {
        this.inspectionRequiredFlag = inspectionRequiredFlag;
    }

    public boolean getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(boolean receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public Integer getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(Integer qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public RcvException getQtyRcvExceptionCode() {
        return qtyRcvExceptionCode;
    }

    public void setQtyRcvExceptionCode(RcvException qtyRcvExceptionCode) {
        this.qtyRcvExceptionCode = qtyRcvExceptionCode;
    }

    public EnforceShip getEnforceShipToLocation() {
        return enforceShipToLocation;
    }

    public void setEnforceShipToLocation(EnforceShip enforceShipToLocation) {
        this.enforceShipToLocation = enforceShipToLocation;
    }

    public RecRouting getReceiptRouting() {
        return receiptRouting;
    }

    public void setReceiptRouting(RecRouting receiptRouting) {
        this.receiptRouting = receiptRouting;
    }

    public ApprovalLevel getMatchApprovalLevel() {
        return matchApprovalLevel;
    }

    public void setMatchApprovalLevel(ApprovalLevel matchApprovalLevel) {
        this.matchApprovalLevel = matchApprovalLevel;
    }

    public RecException getReceiptDateException() {
        return receiptDateException;
    }

    public void setReceiptDateException(RecException receiptDateException) {
        this.receiptDateException = receiptDateException;
    }
    
    
    

    public Integer getDaysEarlyReceiptAllowed() {
        return daysEarlyReceiptAllowed;
    }

    public void setDaysEarlyReceiptAllowed(Integer daysEarlyReceiptAllowed) {
        this.daysEarlyReceiptAllowed = daysEarlyReceiptAllowed;
    }

    public Integer getDaysLateReceiptAllowed() {
        return daysLateReceiptAllowed;
    }

    public void setDaysLateReceiptAllowed(Integer daysLateReceiptAllowed) {
        this.daysLateReceiptAllowed = daysLateReceiptAllowed;
    }

    public boolean getAllowSubstituteReceiptsFlag() {
        return allowSubstituteReceiptsFlag;
    }

    public void setAllowSubstituteReceiptsFlag(boolean allowSubstituteReceiptsFlag) {
        this.allowSubstituteReceiptsFlag = allowSubstituteReceiptsFlag;
    }

    public boolean getAllowUnorderedReceiptsFlag() {
        return allowUnorderedReceiptsFlag;
    }

    public void setAllowUnorderedReceiptsFlag(boolean allowUnorderedReceiptsFlag) {
        this.allowUnorderedReceiptsFlag = allowUnorderedReceiptsFlag;
    }

    public boolean getHoldUnmatchedInvoicesFlag() {
        return holdUnmatchedInvoicesFlag;
    }

    public void setHoldUnmatchedInvoicesFlag(boolean holdUnmatchedInvoicesFlag) {
        this.holdUnmatchedInvoicesFlag = holdUnmatchedInvoicesFlag;
    }

    public boolean getExclusivePaymentFlag() {
        return exclusivePaymentFlag;
    }

    public void setExclusivePaymentFlag(boolean exclusivePaymentFlag) {
        this.exclusivePaymentFlag = exclusivePaymentFlag;
    }

    public String getApTaxRoundingRule() {
        return apTaxRoundingRule;
    }

    public void setApTaxRoundingRule(String apTaxRoundingRule) {
        this.apTaxRoundingRule = apTaxRoundingRule;
    }

    public boolean getAutoTaxCalcFlag() {
        return autoTaxCalcFlag;
    }

    public void setAutoTaxCalcFlag(boolean autoTaxCalcFlag) {
        this.autoTaxCalcFlag = autoTaxCalcFlag;
    }

    public String getAutoTaxCalcOverride() {
        return autoTaxCalcOverride;
    }

    public void setAutoTaxCalcOverride(String autoTaxCalcOverride) {
        this.autoTaxCalcOverride = autoTaxCalcOverride;
    }

    public boolean getAmountIncludesTaxFlag() {
        return amountIncludesTaxFlag;
    }

    public void setAmountIncludesTaxFlag(boolean amountIncludesTaxFlag) {
        this.amountIncludesTaxFlag = amountIncludesTaxFlag;
    }

    public Date getTaxVerificationDate() {
        return taxVerificationDate;
    }

    public void setTaxVerificationDate(Date taxVerificationDate) {
        this.taxVerificationDate = taxVerificationDate;
    }

    public String getNameControl() {
        return nameControl;
    }

    public void setNameControl(String nameControl) {
        this.nameControl = nameControl;
    }

    public boolean getStateReportableFlag() {
        return stateReportableFlag;
    }

    public void setStateReportableFlag(boolean stateReportableFlag) {
        this.stateReportableFlag = stateReportableFlag;
    }

    public boolean getFederalReportableFlag() {
        return federalReportableFlag;
    }

    public void setFederalReportableFlag(boolean federalReportableFlag) {
        this.federalReportableFlag = federalReportableFlag;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public boolean getAllowAwtFlag() {
        return allowAwtFlag;
    }

    public void setAllowAwtFlag(boolean allowAwtFlag) {
        this.allowAwtFlag = allowAwtFlag;
    }

    public Long getAwtGroupId() {
        return awtGroupId;
    }

    public void setAwtGroupId(Long awtGroupId) {
        this.awtGroupId = awtGroupId;
    }

    public boolean getCreateDebitMemoFlag() {
        return createDebitMemoFlag;
    }

    public void setCreateDebitMemoFlag(boolean createDebitMemoFlag) {
        this.createDebitMemoFlag = createDebitMemoFlag;
    }

    public boolean getOffsetTaxFlag() {
        return offsetTaxFlag;
    }

    public void setOffsetTaxFlag(boolean offsetTaxFlag) {
        this.offsetTaxFlag = offsetTaxFlag;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getParentPartyId() {
        return parentPartyId;
    }

    public void setParentPartyId(Integer parentPartyId) {
        this.parentPartyId = parentPartyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(String companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public boolean getPurchasingHoldFlag() {
        return purchasingHoldFlag;
    }

    public void setPurchasingHoldFlag(boolean purchasingHoldFlag) {
        this.purchasingHoldFlag = purchasingHoldFlag;
    }

    public Set<ApSupplierSite> getSupplierSites() {
        return supplierSites;
    }

    public void setSupplierSites(Set<ApSupplierSite> supplierSites) {
        this.supplierSites = supplierSites;
    }

    public Set<SiteAccount> getSupplierSiteAcounts() {
        return supplierSiteAcounts;
    }

    public void setSupplierSiteAcounts(Set<SiteAccount> supplierSiteAcounts) {
        this.supplierSiteAcounts = supplierSiteAcounts;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorId != null ? vendorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApSupplier)) {
            return false;
        }
        ApSupplier other = (ApSupplier) object;
        if ((this.vendorId == null && other.vendorId != null) || (this.vendorId != null && !this.vendorId.equals(other.vendorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.ApSupplier[ vendorId=" + vendorId + " ]";
    }
    
}
