/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.enums.ApprovalLevel;
import com.bridge.enums.EnforceShip;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.RcvException;
import com.bridge.enums.RecException;
import com.bridge.enums.RecRouting;
import java.io.Serializable;
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
@Table(name = "CL_CLIENT_SUPPLIER_ACC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierAcc.findAll", query = "SELECT s FROM SupplierAcc s")})
public class SupplierAcc implements Serializable {



    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "supplier")
    @TableGenerator(name = "supplier",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Supplier",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "SITE_ID")
    private ClientSite siteId;
    @Size(max = 100)
    @Column(name = "LEGAL_ENTITY_NAME")
    private String legalEntityName;
    @Size(max = 100)
    @Column(name = "OPERATING_UNIT_NAME")
    private String operatingUnitName;
    @Size(max = 250)
    @Column(name = "LIABILITY_ACCOUNT_NAME")
    private String liabilityAccountName;
    @Size(max = 250)
    @Column(name = "PREPAYMENT_ACCOUNT_NAME")
    private String prepaymentAccountName;
    @Size(max = 250)
    @Column(name = "BILLS_PAYABLE_ACCOUNT_NAME")
    private String billsPayableAccountName;
    @Size(max = 25)
    @Column(name = "CUSTOMER_NUM")
    private String customerNum;
    @Size(max = 25)
    @Column(name = "SHIP_VIA_LOOKUP_CODE")
    private String shipViaLookupCode;
    @Size(max = 25)
    @Column(name = "FREIGHT_TERMS_LOOKUP_CODE")
    private String freightTermsLookupCode;
    @Size(max = 25)
    @Column(name = "FOB_LOOKUP_CODE")
    private String fobLookupCode;
    @Column(name = "INACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    @Size(max = 25)
    @Column(name = "PAYMENT_METHOD_LOOKUP_CODE")
    private String paymentMethodLookupCode;
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
    @Size(max = 25)
    @Column(name = "TERMS_DATE_BASIS")
    private String termsDateBasis;
    @Size(max = 20)
    @Column(name = "CURRENT_CATALOG_NUM")
    private String currentCatalogNum;
    @Size(max = 25)
    @Column(name = "PAY_DATE_BASIS_LOOKUP_CODE")
    private String payDateBasisLookupCode;
    @Size(max = 15)
    @Column(name = "INVOICE_CURRENCY_CODE")
    private String invoiceCurrencyCode;
    @Size(max = 15)
    @Column(name = "PAYMENT_CURRENCY_CODE")
    private String paymentCurrencyCode;
    @Column(name = "HOLD_ALL_PAYMENTS_FLAG")
    private boolean holdAllPaymentsFlag;
    @Column(name = "HOLD_FUTURE_PAYMENTS_FLAG")
    private boolean holdFuturePaymentsFlag;
    @Size(max = 240)
    @Column(name = "HOLD_REASON")
    private String holdReason;
    @Column(name = "HOLD_UNMATCHED_INVOICES_FLAG")
    private boolean holdUnmatchedInvoicesFlag;
    @Column(name = "AP_TAX_ROUNDING_RULE")
    private String apTaxRoundingRule;
    @Column(name = "AUTO_TAX_CALC_FLAG")
    private boolean autoTaxCalcFlag;
    @Column(name = "AUTO_TAX_CALC_OVERRIDE")
    private String autoTaxCalcOverride;
    @Column(name = "AMOUNT_INCLUDES_TAX_FLAG")
    private boolean amountIncludesTaxFlag;
    @Column(name = "EXCLUSIVE_PAYMENT_FLAG")
    private boolean exclusivePaymentFlag;
    @Column(name = "TAX_REPORTING_SITE_FLAG")
    private boolean taxReportingSiteFlag;
    @Size(max = 30)
    @Column(name = "CHECK_DIGITS")
    private String checkDigits;
    @Size(max = 30)
    @Column(name = "BANK_NUMBER")
    private String bankNumber;
    @Size(max = 240)
    @Column(name = "ADDRESS_LINE4")
    private String addressLine4;
    @Size(max = 150)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 30)
    @Column(name = "ADDRESS_STYLE")
    private String addressStyle;
    @Column(name = "DEFAULT_PAY_SITE_ID")
    private Long defaultPaySiteId;
    @Size(max = 25)
    @Column(name = "PAY_ON_RECEIPT_SUMMARY_CODE")
    private String payOnReceiptSummaryCode;
    @Size(max = 2)
    @Column(name = "COUNTRY_OF_ORIGIN_CODE")
    private String countryOfOriginCode;
    @Column(name = "CREATE_DEBIT_MEMO_FLAG")
    private boolean createDebitMemoFlag;
    @Size(max = 25)
    @Column(name = "SUPPLIER_NOTIF_METHOD")
    private String supplierNotifMethod;
    @Size(max = 2000)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 2000)
    @Column(name = "REMITTANCE_EMAIL")
    private String remittanceEmail;
    @Column(name = "PRIMARY_PAY_SITE_FLAG")
    private boolean primaryPaySiteFlag;
    @Size(max = 30)
    @Column(name = "SHIPPING_CONTROL")
    private String shippingControl;
    @Size(max = 10)
    @Column(name = "SELLING_COMPANY_IDENTIFIER")
    private String sellingCompanyIdentifier;
    @Size(max = 240)
    @Column(name = "LEGAL_BUSINESS_NAME")
    private String legalBusinessName;
    @Size(max = 10)
    @Column(name = "SMALL_BUSINESS_CODE")
    private String smallBusinessCode;
    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client clientId;
    @Column(name = "LEDGER_ID")
    private Integer ledgerId;
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    
    @OneToOne
    @JoinColumn(name = "LIABILITY_ACCOUNT_ID")
    private GlCodeCombination liabilityAccountId;
    @OneToOne
    
    @JoinColumn(name = "PREPAYMENT_ACCOUNT_ID")
    private GlCodeCombination prepaymentAccountId;
    @OneToOne
    @JoinColumn(name = "BILLS_PAYABLE_ACCOUNT_ID")
    private GlCodeCombination billsPayableAccountId;
    @Column(name = "ENABLED_FLAG")
    private boolean enabledFlag;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Column(name = "BILL_TO_LOCATION_ID")
    private Integer billToLocationId;
    @Column(name = "ACCTS_PAY_CODE_COMBINATION_ID")
    private Integer acctsPayCodeCombinationId;
    @Column(name = "PREPAY_CODE_COMBINATION_ID")
    private Integer prepayCodeCombinationId;
    @Column(name = "PAYMENT_PRIORITY")
    private Integer paymentPriority;
    @Column(name = "TERMS_ID")
    private Integer termsId;
    @Column(name = "INVOICE_AMOUNT_LIMIT")
    private Integer invoiceAmountLimit;
    @Column(name = "SHIP_TO_FLAG")
    private boolean shipToFlag;
    @Column(name = "BILL_TO_FLAG")
    private boolean billToFlag;
    @Column(name = "PURCHASING_HOLD_FLAG")
    private boolean purchasingHoldFlag;
    @Column(name = "DAYS_EARLY_RECEIPT_ALLOWED")
    private Integer daysEarlyReceiptAllowed;
    @Column(name = "DAYS_LATE_RECEIPT_ALLOWED")
    private Integer daysLateReceiptAllowed;
    @Column(name = "QTY_RCV_TOLERANCE")
    private Integer qtyRcvTolerance;
    @Column(name = "MATCH_APPROVAL_LEVEL")
    private ApprovalLevel matchApprovalLevel;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "INVOICE_MATCHING_OPTION")
    private InvoiceMatchingOptions invoiceMatchingOption;
    
    @Column(name = "ENFORCE_SHIP_TO_LOCATION")
    private EnforceShip enforceShipToLocation;
    @Column(name = "QTY_RCV_EXCEPTION_CODE")
    private RcvException qtyRcvExceptionCode;
    @Column(name = "RECEIPT_DATE_EXCEPTION")
    private RecException receiptDateException;
    @Column(name = "RECEIPT_ROUTING")
    private RecRouting receiptRouting;
    @Column(name = "ALLOW_UNORDERED_RECEIPTS_FLAG")
    private boolean allowUnorderedReceiptsFlag;
    @Column(name = "ALLOW_SUBSTITUTE_RECEIPTS_FLAG")
    private boolean allowSubstituteReceiptsFlag;
    @Column(name = "PURCHASING_FLAG")
    private boolean purchasingFlag;
    @Column(name = "PAYMENT_FLAG")
    private boolean paymentFlag;
    
    
    
    public SupplierAcc() {
    }

    public SupplierAcc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientSite getSiteId() {
        return siteId;
    }

    public void setSiteId(ClientSite siteId) {
        this.siteId = siteId;
    }

    public String getLegalEntityName() {
        return legalEntityName;
    }

    public void setLegalEntityName(String legalEntityName) {
        this.legalEntityName = legalEntityName;
    }

    public String getOperatingUnitName() {
        return operatingUnitName;
    }

    public void setOperatingUnitName(String operatingUnitName) {
        this.operatingUnitName = operatingUnitName;
    }

    public String getLiabilityAccountName() {
        return liabilityAccountName;
    }

    public void setLiabilityAccountName(String liabilityAccountName) {
        this.liabilityAccountName = liabilityAccountName;
    }

    public String getPrepaymentAccountName() {
        return prepaymentAccountName;
    }

    public void setPrepaymentAccountName(String prepaymentAccountName) {
        this.prepaymentAccountName = prepaymentAccountName;
    }

    public String getBillsPayableAccountName() {
        return billsPayableAccountName;
    }

    public void setBillsPayableAccountName(String billsPayableAccountName) {
        this.billsPayableAccountName = billsPayableAccountName;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
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

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getPaymentMethodLookupCode() {
        return paymentMethodLookupCode;
    }

    public void setPaymentMethodLookupCode(String paymentMethodLookupCode) {
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

    public String getTermsDateBasis() {
        return termsDateBasis;
    }

    public void setTermsDateBasis(String termsDateBasis) {
        this.termsDateBasis = termsDateBasis;
    }

    public String getCurrentCatalogNum() {
        return currentCatalogNum;
    }

    public void setCurrentCatalogNum(String currentCatalogNum) {
        this.currentCatalogNum = currentCatalogNum;
    }

    public String getPayDateBasisLookupCode() {
        return payDateBasisLookupCode;
    }

    public void setPayDateBasisLookupCode(String payDateBasisLookupCode) {
        this.payDateBasisLookupCode = payDateBasisLookupCode;
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

    public boolean getHoldUnmatchedInvoicesFlag() {
        return holdUnmatchedInvoicesFlag;
    }

    public void setHoldUnmatchedInvoicesFlag(boolean holdUnmatchedInvoicesFlag) {
        this.holdUnmatchedInvoicesFlag = holdUnmatchedInvoicesFlag;
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

    public boolean getExclusivePaymentFlag() {
        return exclusivePaymentFlag;
    }

    public void setExclusivePaymentFlag(boolean exclusivePaymentFlag) {
        this.exclusivePaymentFlag = exclusivePaymentFlag;
    }

    public boolean getTaxReportingSiteFlag() {
        return taxReportingSiteFlag;
    }

    public void setTaxReportingSiteFlag(boolean taxReportingSiteFlag) {
        this.taxReportingSiteFlag = taxReportingSiteFlag;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(String checkDigits) {
        this.checkDigits = checkDigits;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressStyle() {
        return addressStyle;
    }

    public void setAddressStyle(String addressStyle) {
        this.addressStyle = addressStyle;
    }

    public Long getDefaultPaySiteId() {
        return defaultPaySiteId;
    }

    public void setDefaultPaySiteId(Long defaultPaySiteId) {
        this.defaultPaySiteId = defaultPaySiteId;
    }

    public String getPayOnReceiptSummaryCode() {
        return payOnReceiptSummaryCode;
    }

    public void setPayOnReceiptSummaryCode(String payOnReceiptSummaryCode) {
        this.payOnReceiptSummaryCode = payOnReceiptSummaryCode;
    }

    public String getCountryOfOriginCode() {
        return countryOfOriginCode;
    }

    public void setCountryOfOriginCode(String countryOfOriginCode) {
        this.countryOfOriginCode = countryOfOriginCode;
    }

    public boolean getCreateDebitMemoFlag() {
        return createDebitMemoFlag;
    }

    public void setCreateDebitMemoFlag(boolean createDebitMemoFlag) {
        this.createDebitMemoFlag = createDebitMemoFlag;
    }

    public String getSupplierNotifMethod() {
        return supplierNotifMethod;
    }

    public void setSupplierNotifMethod(String supplierNotifMethod) {
        this.supplierNotifMethod = supplierNotifMethod;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRemittanceEmail() {
        return remittanceEmail;
    }

    public void setRemittanceEmail(String remittanceEmail) {
        this.remittanceEmail = remittanceEmail;
    }

    public boolean getPrimaryPaySiteFlag() {
        return primaryPaySiteFlag;
    }

    public void setPrimaryPaySiteFlag(boolean primaryPaySiteFlag) {
        this.primaryPaySiteFlag = primaryPaySiteFlag;
    }

    public String getShippingControl() {
        return shippingControl;
    }

    public void setShippingControl(String shippingControl) {
        this.shippingControl = shippingControl;
    }

    public String getSellingCompanyIdentifier() {
        return sellingCompanyIdentifier;
    }

    public void setSellingCompanyIdentifier(String sellingCompanyIdentifier) {
        this.sellingCompanyIdentifier = sellingCompanyIdentifier;
    }

    public String getLegalBusinessName() {
        return legalBusinessName;
    }

    public void setLegalBusinessName(String legalBusinessName) {
        this.legalBusinessName = legalBusinessName;
    }

    public String getSmallBusinessCode() {
        return smallBusinessCode;
    }

    public void setSmallBusinessCode(String smallBusinessCode) {
        this.smallBusinessCode = smallBusinessCode;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public GlCodeCombination getLiabilityAccountId() {
        return liabilityAccountId;
    }

    public void setLiabilityAccountId(GlCodeCombination liabilityAccountId) {
        this.liabilityAccountId = liabilityAccountId;
    }

    public GlCodeCombination getPrepaymentAccountId() {
        return prepaymentAccountId;
    }

    public void setPrepaymentAccountId(GlCodeCombination prepaymentAccountId) {
        this.prepaymentAccountId = prepaymentAccountId;
    }

    public GlCodeCombination getBillsPayableAccountId() {
        return billsPayableAccountId;
    }

    public void setBillsPayableAccountId(GlCodeCombination billsPayableAccountId) {
        this.billsPayableAccountId = billsPayableAccountId;
    }

    public boolean getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
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

    public Integer getAcctsPayCodeCombinationId() {
        return acctsPayCodeCombinationId;
    }

    public void setAcctsPayCodeCombinationId(Integer acctsPayCodeCombinationId) {
        this.acctsPayCodeCombinationId = acctsPayCodeCombinationId;
    }

    public Integer getPrepayCodeCombinationId() {
        return prepayCodeCombinationId;
    }

    public void setPrepayCodeCombinationId(Integer prepayCodeCombinationId) {
        this.prepayCodeCombinationId = prepayCodeCombinationId;
    }

    public Integer getPaymentPriority() {
        return paymentPriority;
    }

    public void setPaymentPriority(Integer paymentPriority) {
        this.paymentPriority = paymentPriority;
    }

    public Integer getTermsId() {
        return termsId;
    }

    public void setTermsId(Integer termsId) {
        this.termsId = termsId;
    }

    public Integer getInvoiceAmountLimit() {
        return invoiceAmountLimit;
    }

    public void setInvoiceAmountLimit(Integer invoiceAmountLimit) {
        this.invoiceAmountLimit = invoiceAmountLimit;
    }

    public boolean getShipToFlag() {
        return shipToFlag;
    }

    public void setShipToFlag(boolean shipToFlag) {
        this.shipToFlag = shipToFlag;
    }

    public boolean getBillToFlag() {
        return billToFlag;
    }

    public void setBillToFlag(boolean billToFlag) {
        this.billToFlag = billToFlag;
    }

    public boolean getPurchasingHoldFlag() {
        return purchasingHoldFlag;
    }

    public void setPurchasingHoldFlag(boolean purchasingHoldFlag) {
        this.purchasingHoldFlag = purchasingHoldFlag;
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

    public Integer getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(Integer qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public ApprovalLevel getMatchApprovalLevel() {
        return matchApprovalLevel;
    }

    public void setMatchApprovalLevel(ApprovalLevel matchApprovalLevel) {
        this.matchApprovalLevel = matchApprovalLevel;
    }

    public EnforceShip getEnforceShipToLocation() {
        return enforceShipToLocation;
    }

    public void setEnforceShipToLocation(EnforceShip enforceShipToLocation) {
        this.enforceShipToLocation = enforceShipToLocation;
    }

    public RcvException getQtyRcvExceptionCode() {
        return qtyRcvExceptionCode;
    }

    public void setQtyRcvExceptionCode(RcvException qtyRcvExceptionCode) {
        this.qtyRcvExceptionCode = qtyRcvExceptionCode;
    }

    public RecException getReceiptDateException() {
        return receiptDateException;
    }

    public void setReceiptDateException(RecException receiptDateException) {
        this.receiptDateException = receiptDateException;
    }

    public RecRouting getReceiptRouting() {
        return receiptRouting;
    }

    public void setReceiptRouting(RecRouting receiptRouting) {
        this.receiptRouting = receiptRouting;
    }

    public boolean getAllowUnorderedReceiptsFlag() {
        return allowUnorderedReceiptsFlag;
    }

    public void setAllowUnorderedReceiptsFlag(boolean allowUnorderedReceiptsFlag) {
        this.allowUnorderedReceiptsFlag = allowUnorderedReceiptsFlag;
    }

    public boolean getAllowSubstituteReceiptsFlag() {
        return allowSubstituteReceiptsFlag;
    }

    public void setAllowSubstituteReceiptsFlag(boolean allowSubstituteReceiptsFlag) {
        this.allowSubstituteReceiptsFlag = allowSubstituteReceiptsFlag;
    }

    public boolean isPurchasingFlag() {
        return purchasingFlag;
    }

    public void setPurchasingFlag(boolean purchasingFlag) {
        this.purchasingFlag = purchasingFlag;
    }

    public boolean isPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(boolean paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public InvoiceMatchingOptions getInvoiceMatchingOption() {
        return invoiceMatchingOption;
    }

    public void setInvoiceMatchingOption(InvoiceMatchingOptions invoiceMatchingOption) {
        this.invoiceMatchingOption = invoiceMatchingOption;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierAcc)) {
            return false;
        }
        SupplierAcc other = (SupplierAcc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.SupplierAcc[ id=" + id + " ]";
    }

   
    
}
