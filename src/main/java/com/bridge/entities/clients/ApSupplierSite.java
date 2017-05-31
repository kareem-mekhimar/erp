/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.hr.City;
import com.bridge.entities.hr.Country;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "AP_SUPPLIER_SITES")
@NamedQueries({
    @NamedQuery(name = "ApSupplierSite.findAll", query = "SELECT a FROM ApSupplierSite a")})
public class ApSupplierSite implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "site")
    @TableGenerator(name = "site",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "SupSite",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENDOR_SITE_ID")
    private Integer vendorSiteId;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "VENDOR_SITE_CODE")
    private String vendorSiteCode;
    @Size(max = 320)
    @Column(name = "VENDOR_SITE_CODE_ALT")
    private String vendorSiteCodeAlt;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Size(max = 1)
    @Column(name = "PURCHASING_SITE_FLAG")
    private String purchasingSiteFlag;
    @Size(max = 1)
    @Column(name = "RFQ_ONLY_SITE_FLAG")
    private String rfqOnlySiteFlag;
    @Size(max = 1)
    @Column(name = "PAY_SITE_FLAG")
    private String paySiteFlag;
    @Size(max = 1)
    @Column(name = "ATTENTION_AR_FLAG")
    private String attentionArFlag;
    @Size(max = 240)
    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;
    @Size(max = 560)
    @Column(name = "ADDRESS_LINES_ALT")
    private String addressLinesAlt;
    @Size(max = 240)
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;
    @Size(max = 240)
    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;
    @Size(max = 60)
    @Column(name = "CITY")
    private String city;
    @Size(max = 150)
    @Column(name = "STATE")
    private String state;
    @Size(max = 60)
    @Column(name = "ZIP")
    private String zip;
    @Size(max = 150)
    @Column(name = "PROVINCE")
    private String province;
    @Size(max = 60)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 10)
    @Column(name = "AREA_CODE")
    private String areaCode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 25)
    @Column(name = "CUSTOMER_NUM")
    private String customerNum;
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
    @Column(name = "INACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "FAX")
    private String fax;
    @Size(max = 10)
    @Column(name = "FAX_AREA_CODE")
    private String faxAreaCode;
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
    @Size(max = 25)
    @Column(name = "PAY_DATE_BASIS_LOOKUP_CODE")
    private String payDateBasisLookupCode;
    @Size(max = 15)
    @Column(name = "INVOICE_CURRENCY_CODE")
    private String invoiceCurrencyCode;
    @Size(max = 15)
    @Column(name = "PAYMENT_CURRENCY_CODE")
    private String paymentCurrencyCode;
    @Size(max = 1)
    @Column(name = "HOLD_ALL_PAYMENTS_FLAG")
    private String holdAllPaymentsFlag;
    @Size(max = 1)
    @Column(name = "HOLD_FUTURE_PAYMENTS_FLAG")
    private String holdFuturePaymentsFlag;
    @Size(max = 240)
    @Column(name = "HOLD_REASON")
    private String holdReason;
    @Size(max = 1)
    @Column(name = "HOLD_UNMATCHED_INVOICES_FLAG")
    private String holdUnmatchedInvoicesFlag;
    @Size(max = 1)
    @Column(name = "AP_TAX_ROUNDING_RULE")
    private String apTaxRoundingRule;
    @Size(max = 1)
    @Column(name = "AUTO_TAX_CALC_FLAG")
    private String autoTaxCalcFlag;
    @Size(max = 1)
    @Column(name = "AUTO_TAX_CALC_OVERRIDE")
    private String autoTaxCalcOverride;
    @Size(max = 1)
    @Column(name = "AMOUNT_INCLUDES_TAX_FLAG")
    private String amountIncludesTaxFlag;
    @Size(max = 1)
    @Column(name = "EXCLUSIVE_PAYMENT_FLAG")
    private String exclusivePaymentFlag;
    @Size(max = 1)
    @Column(name = "TAX_REPORTING_SITE_FLAG")
    private String taxReportingSiteFlag;
    @Column(name = "ORG_ID")
    private Integer orgId;
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
    @Size(max = 25)
    @Column(name = "CREATE_DEBIT_MEMO_FLAG")
    private String createDebitMemoFlag;
    @Size(max = 25)
    @Column(name = "SUPPLIER_NOTIF_METHOD")
    private String supplierNotifMethod;
    @Size(max = 2000)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 2000)
    @Column(name = "REMITTANCE_EMAIL")
    private String remittanceEmail;
    @Size(max = 1)
    @Column(name = "PRIMARY_PAY_SITE_FLAG")
    private String primaryPaySiteFlag;
    @Size(max = 30)
    @Column(name = "SHIPPING_CONTROL")
    private String shippingControl;
    @Size(max = 10)
    @Column(name = "SELLING_COMPANY_IDENTIFIER")
    private String sellingCompanyIdentifier;
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Column(name = "PARTY_SITE_ID")
    private Long partySiteId;
    @Size(max = 240)
    @Column(name = "LEGAL_BUSINESS_NAME")
    private String legalBusinessName;
    @Size(max = 10)
    @Column(name = "SMALL_BUSINESS_CODE")
    private String smallBusinessCode;
    @Column(name = "SHIP_TO_FLAG")
    private boolean shipToFlag;
    @Column(name = "BILL_TO_FLAG")
    private boolean billToFlag;
    @Size(max = 255)
    @Column(name = "VENDOR_SITE_NAME")
    private String vendorSiteName;
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    private Country countryId;
    @ManyToOne
    @JoinColumn(name="CITY_ID")
    private City cityId;
    
    @JoinColumn(name = "VENDOR_ID", referencedColumnName = "VENDOR_ID")
    @ManyToOne(optional = false)
    private ApSupplier vendor;

    @OneToMany(mappedBy = "site",cascade = CascadeType.ALL)
    private List<ApSupplierContact> siteContacts ;
    
    public ApSupplierSite() {
    }

    public ApSupplierSite(Integer vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public ApSupplierSite(Integer vendorSiteId, Date lastUpdateDate, Integer lastUpdatedBy, String vendorSiteCode, Date creationDate, Integer createdBy) {
        this.vendorSiteId = vendorSiteId;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.vendorSiteCode = vendorSiteCode;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
    }

    public Integer getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(Integer vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
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

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getVendorSiteCodeAlt() {
        return vendorSiteCodeAlt;
    }

    public void setVendorSiteCodeAlt(String vendorSiteCodeAlt) {
        this.vendorSiteCodeAlt = vendorSiteCodeAlt;
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getPurchasingSiteFlag() {
        return purchasingSiteFlag;
    }

    public void setPurchasingSiteFlag(String purchasingSiteFlag) {
        this.purchasingSiteFlag = purchasingSiteFlag;
    }

    public String getRfqOnlySiteFlag() {
        return rfqOnlySiteFlag;
    }

    public void setRfqOnlySiteFlag(String rfqOnlySiteFlag) {
        this.rfqOnlySiteFlag = rfqOnlySiteFlag;
    }

    public String getPaySiteFlag() {
        return paySiteFlag;
    }

    public void setPaySiteFlag(String paySiteFlag) {
        this.paySiteFlag = paySiteFlag;
    }

    public String getAttentionArFlag() {
        return attentionArFlag;
    }

    public void setAttentionArFlag(String attentionArFlag) {
        this.attentionArFlag = attentionArFlag;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLinesAlt() {
        return addressLinesAlt;
    }

    public void setAddressLinesAlt(String addressLinesAlt) {
        this.addressLinesAlt = addressLinesAlt;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
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

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxAreaCode() {
        return faxAreaCode;
    }

    public void setFaxAreaCode(String faxAreaCode) {
        this.faxAreaCode = faxAreaCode;
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

    public String getHoldAllPaymentsFlag() {
        return holdAllPaymentsFlag;
    }

    public void setHoldAllPaymentsFlag(String holdAllPaymentsFlag) {
        this.holdAllPaymentsFlag = holdAllPaymentsFlag;
    }

    public String getHoldFuturePaymentsFlag() {
        return holdFuturePaymentsFlag;
    }

    public void setHoldFuturePaymentsFlag(String holdFuturePaymentsFlag) {
        this.holdFuturePaymentsFlag = holdFuturePaymentsFlag;
    }

    public String getHoldReason() {
        return holdReason;
    }

    public void setHoldReason(String holdReason) {
        this.holdReason = holdReason;
    }

    public String getHoldUnmatchedInvoicesFlag() {
        return holdUnmatchedInvoicesFlag;
    }

    public void setHoldUnmatchedInvoicesFlag(String holdUnmatchedInvoicesFlag) {
        this.holdUnmatchedInvoicesFlag = holdUnmatchedInvoicesFlag;
    }

    public String getApTaxRoundingRule() {
        return apTaxRoundingRule;
    }

    public void setApTaxRoundingRule(String apTaxRoundingRule) {
        this.apTaxRoundingRule = apTaxRoundingRule;
    }

    public String getAutoTaxCalcFlag() {
        return autoTaxCalcFlag;
    }

    public void setAutoTaxCalcFlag(String autoTaxCalcFlag) {
        this.autoTaxCalcFlag = autoTaxCalcFlag;
    }

    public String getAutoTaxCalcOverride() {
        return autoTaxCalcOverride;
    }

    public void setAutoTaxCalcOverride(String autoTaxCalcOverride) {
        this.autoTaxCalcOverride = autoTaxCalcOverride;
    }

    public String getAmountIncludesTaxFlag() {
        return amountIncludesTaxFlag;
    }

    public void setAmountIncludesTaxFlag(String amountIncludesTaxFlag) {
        this.amountIncludesTaxFlag = amountIncludesTaxFlag;
    }

    public String getExclusivePaymentFlag() {
        return exclusivePaymentFlag;
    }

    public void setExclusivePaymentFlag(String exclusivePaymentFlag) {
        this.exclusivePaymentFlag = exclusivePaymentFlag;
    }

    public String getTaxReportingSiteFlag() {
        return taxReportingSiteFlag;
    }

    public void setTaxReportingSiteFlag(String taxReportingSiteFlag) {
        this.taxReportingSiteFlag = taxReportingSiteFlag;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String getCreateDebitMemoFlag() {
        return createDebitMemoFlag;
    }

    public void setCreateDebitMemoFlag(String createDebitMemoFlag) {
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

    public String getPrimaryPaySiteFlag() {
        return primaryPaySiteFlag;
    }

    public void setPrimaryPaySiteFlag(String primaryPaySiteFlag) {
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getPartySiteId() {
        return partySiteId;
    }

    public void setPartySiteId(Long partySiteId) {
        this.partySiteId = partySiteId;
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

    public String getVendorSiteName() {
        return vendorSiteName;
    }

    public void setVendorSiteName(String vendorSiteName) {
        this.vendorSiteName = vendorSiteName;
    }

    public ApSupplier getVendor() {
        return vendor;
    }

    public void setVendor(ApSupplier vendor) {
        this.vendor = vendor;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public List<ApSupplierContact> getSiteContacts() {
        return siteContacts;
    }

    public void setSiteContacts(List<ApSupplierContact> siteContacts) {
        this.siteContacts = siteContacts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.vendorSiteId);
        hash = 89 * hash + Objects.hashCode(this.vendorSiteCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApSupplierSite other = (ApSupplierSite) obj;
        if (!Objects.equals(this.vendorSiteCode, other.vendorSiteCode)) {
            return false;
        }
        if (!Objects.equals(this.vendorSiteId, other.vendorSiteId)) {
            return false;
        }
        return true;
    }

    


    @Override
    public String toString() {
        return "com.bridge.entities.po.ApSupplierSite[ vendorSiteId=" + vendorSiteId + " ]";
    }


    
    
}
