/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.salesorder;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.hr.People;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.personsales.ReservationOrder;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.SalesOrderStatus;
import com.bridge.enums.SalesOrderType;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kareem
 */
@Entity
@Table(name = "OE_ORDER_HEADERS")
@NamedQueries({
    @NamedQuery(name = "OeOrderHeader.findAll", query = "SELECT o FROM OeOrderHeader o")})
public class OeOrderHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "oeHeader")
    @TableGenerator(name = "oeHeader",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "OeHeader",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEADER_ID")
    private Integer headerId;
    
    @ManyToOne
    @JoinColumn(name = "ORG_ID")
    private OrganizationUnit org;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_TYPE_ID")
    private Integer orderTypeId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION_NUMBER")
    private Integer versionNumber = 1;
    
    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    
    @Column(name = "ORDER_SOURCE_ID")
    private Integer orderSourceId;
    
    @Column(name = "SOURCE_DOCUMENT_TYPE_ID")
    private Integer sourceDocumentTypeId;
    
    @Size(max = 50)
    @Column(name = "ORIG_SYS_DOCUMENT_REF")
    private String origSysDocumentRef;
    @Column(name = "SOURCE_DOCUMENT_ID")
    private Integer sourceDocumentId;
    
    @Column(name = "ORDERED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;
    
    @Column(name = "REQUEST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    
    @Column(name = "PRICING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pricingDate;
    @Size(max = 30)
    @Column(name = "SHIPMENT_PRIORITY_CODE")
    private String shipmentPriorityCode;
    @Size(max = 30)
    @Column(name = "DEMAND_CLASS_CODE")
    private String demandClassCode;
    @Column(name = "PRICE_LIST_ID")
    private Integer priceListId;
    @Size(max = 1)
    @Column(name = "TAX_EXEMPT_FLAG")
    private String taxExemptFlag;
    @Size(max = 80)
    @Column(name = "TAX_EXEMPT_NUMBER")
    private String taxExemptNumber;
    @Size(max = 30)
    @Column(name = "TAX_EXEMPT_REASON_CODE")
    private String taxExemptReasonCode;
    @Column(name = "CONVERSION_RATE")
    private Integer conversionRate;
    @Size(max = 30)
    @Column(name = "CONVERSION_TYPE_CODE")
    private String conversionTypeCode;
    @Column(name = "CONVERSION_RATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conversionRateDate;
    @Size(max = 1)
    @Column(name = "PARTIAL_SHIPMENTS_ALLOWED")
    private String partialShipmentsAllowed;
    @Column(name = "SHIP_TOLERANCE_ABOVE")
    private Integer shipToleranceAbove;
    @Column(name = "SHIP_TOLERANCE_BELOW")
    private Integer shipToleranceBelow;
    @Size(max = 15)
    @Column(name = "TRANSACTIONAL_CURR_CODE")
    private String transactionalCurrCode;
    @Column(name = "AGREEMENT_ID")
    private Integer agreementId;
    @Size(max = 30)
    @Column(name = "TAX_POINT_CODE")
    private String taxPointCode;
    @Size(max = 50)
    @Column(name = "CUST_PO_NUMBER")
    private String custPoNumber;
    @Column(name = "INVOICING_RULE_ID")
    private Long invoicingRuleId;
    @Column(name = "ACCOUNTING_RULE_ID")
    private Long accountingRuleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TERM")
    private PaymentTerms paymentTerm;
    @Size(max = 30)
    @Column(name = "SHIPPING_METHOD_CODE")
    private String shippingMethodCode;
    @Size(max = 30)
    @Column(name = "FREIGHT_CARRIER_CODE")
    private String freightCarrierCode;
    @Size(max = 30)
    @Column(name = "FOB_POINT_CODE")
    private String fobPointCode;
    @Size(max = 30)
    @Column(name = "FREIGHT_TERMS_CODE")
    private String freightTermsCode;
    @Column(name = "SOLD_FROM_ORG_ID")
    private Integer soldFromOrgId;

    @Column(name = "SHIP_FROM_ORG_ID")
    private Integer shipFromOrgId;

    @Column(name = "DELIVER_TO_ORG_ID")
    private Integer deliverToOrgId;
    @Column(name = "SOLD_TO_CONTACT_ID")
    private Integer soldToContactId;
    @Column(name = "SHIP_TO_CONTACT_ID")
    private Integer shipToContactId;
    @Column(name = "INVOICE_TO_CONTACT_ID")
    private Integer invoiceToContactId;
    @Column(name = "DELIVER_TO_CONTACT_ID")
    private Integer deliverToContactId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private Integer createdBy = 1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy = 1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate = new Date();
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Size(max = 1)
    @Column(name = "CANCELLED_FLAG")
    private String cancelledFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "OPEN_FLAG")
    private String openFlag = "1";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "BOOKED_FLAG")
    private String bookedFlag = "1";
    @Column(name = "SALESREP_ID")
    private Long salesrepId;
    @Size(max = 30)
    @Column(name = "RETURN_REASON_CODE")
    private String returnReasonCode;
    @Size(max = 30)
    @Column(name = "ORDER_DATE_TYPE_CODE")
    private String orderDateTypeCode;
    @Column(name = "EARLIEST_SCHEDULE_LIMIT")
    private Integer earliestScheduleLimit;
    @Column(name = "LATEST_SCHEDULE_LIMIT")
    private Integer latestScheduleLimit;
    @Size(max = 30)
    @Column(name = "PAYMENT_TYPE_CODE")
    private String paymentTypeCode;
    @Column(name = "PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;
    @Size(max = 50)
    @Column(name = "CHECK_NUMBER")
    private String checkNumber;
    @Size(max = 80)
    @Column(name = "CREDIT_CARD_CODE")
    private String creditCardCode;
    @Size(max = 80)
    @Column(name = "CREDIT_CARD_HOLDER_NAME")
    private String creditCardHolderName;
    @Size(max = 80)
    @Column(name = "CREDIT_CARD_NUMBER")
    private String creditCardNumber;
    @Column(name = "CREDIT_CARD_EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditCardExpirationDate;
    @Size(max = 80)
    @Column(name = "CREDIT_CARD_APPROVAL_CODE")
    private String creditCardApprovalCode;
    @Size(max = 30)
    @Column(name = "SALES_CHANNEL_CODE")
    private String salesChannelCode;
    @Size(max = 30)
    @Column(name = "FIRST_ACK_CODE")
    private String firstAckCode;
    @Column(name = "FIRST_ACK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstAckDate;
    @Size(max = 30)
    @Column(name = "LAST_ACK_CODE")
    private String lastAckCode;
    @Column(name = "LAST_ACK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAckDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ORDER_CATEGORY_CODE")
    private String orderCategoryCode = "1";
    @Size(max = 50)
    @Column(name = "CHANGE_SEQUENCE")
    private String changeSequence;
    @Size(max = 1)
    @Column(name = "DROP_SHIP_FLAG")
    private String dropShipFlag;
    @Column(name = "CUSTOMER_PAYMENT_TERM_ID")
    private Integer customerPaymentTermId;
    @Size(max = 2000)
    @Column(name = "SHIPPING_INSTRUCTIONS")
    private String shippingInstructions;
    @Size(max = 2000)
    @Column(name = "PACKING_INSTRUCTIONS")
    private String packingInstructions;
    @Column(name = "FLOW_STATUS_CODE")
    @Enumerated(EnumType.STRING)
    private SalesOrderStatus flowStatusCode = SalesOrderStatus.NEW;
    
    @Column(name = "MARKETING_SOURCE_CODE_ID")
    private Integer marketingSourceCodeId;
    @Column(name = "CREDIT_CARD_APPROVAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditCardApprovalDate;
    @Size(max = 1)
    @Column(name = "UPGRADED_FLAG")
    private String upgradedFlag;
    @Size(max = 30)
    @Column(name = "CUSTOMER_PREFERENCE_SET_CODE")
    private String customerPreferenceSetCode;
    @Column(name = "BOOKED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedDate;
    @Column(name = "LOCK_CONTROL")
    private Integer lockControl;
    @Size(max = 240)
    @Column(name = "PRICE_REQUEST_CODE")
    private String priceRequestCode;
    @Column(name = "BATCH_ID")
    private Integer batchId;
    @Column(name = "XML_MESSAGE_ID")
    private Integer xmlMessageId;
    @Column(name = "ACCOUNTING_RULE_DURATION")
    private Long accountingRuleDuration;
    @Column(name = "BLANKET_NUMBER")
    private Integer blanketNumber;
    @Size(max = 30)
    @Column(name = "SALES_DOCUMENT_TYPE_CODE")
    private String salesDocumentTypeCode;
    @Column(name = "SOLD_TO_PHONE_ID")
    private Integer soldToPhoneId;
    @Size(max = 30)
    @Column(name = "FULFILLMENT_SET_NAME")
    private String fulfillmentSetName;
    @Size(max = 30)
    @Column(name = "LINE_SET_NAME")
    private String lineSetName;
    @Size(max = 1)
    @Column(name = "DEFAULT_FULFILLMENT_SET")
    private String defaultFulfillmentSet;
    @Size(max = 30)
    @Column(name = "TRANSACTION_PHASE_CODE")
    private String transactionPhaseCode;
    @Size(max = 240)
    @Column(name = "SALES_DOCUMENT_NAME")
    private String salesDocumentName;
    @Column(name = "QUOTE_NUMBER")
    private Integer quoteNumber;
    @Column(name = "QUOTE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date quoteDate;
    @Size(max = 30)
    @Column(name = "USER_STATUS_CODE")
    private String userStatusCode;
    @Size(max = 1)
    @Column(name = "DRAFT_SUBMITTED_FLAG")
    private String draftSubmittedFlag;
    @Column(name = "SOURCE_DOCUMENT_VERSION_NUMBER")
    private Integer sourceDocumentVersionNumber;
    @Column(name = "SOLD_TO_SITE_USE_ID")
    private Integer soldToSiteUseId;
    @Size(max = 240)
    @Column(name = "SUPPLIER_SIGNATURE")
    private String supplierSignature;
    @Column(name = "SUPPLIER_SIGNATURE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supplierSignatureDate;
    @Size(max = 240)
    @Column(name = "CUSTOMER_SIGNATURE")
    private String customerSignature;
    @Column(name = "CUSTOMER_SIGNATURE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date customerSignatureDate;
    @Column(name = "MINISITE_ID")
    private Integer minisiteId;
    @Column(name = "END_CUSTOMER_ID")
    private Integer endCustomerId;
    @Column(name = "END_CUSTOMER_CONTACT_ID")
    private Integer endCustomerContactId;
    @Column(name = "END_CUSTOMER_SITE_USE_ID")
    private Integer endCustomerSiteUseId;
    @Size(max = 60)
    @Column(name = "IB_OWNER")
    private String ibOwner;
    @Size(max = 60)
    @Column(name = "IB_CURRENT_LOCATION")
    private String ibCurrentLocation;
    @Size(max = 60)
    @Column(name = "IB_INSTALLED_AT_LOCATION")
    private String ibInstalledAtLocation;
    @Column(name = "ORDER_FIRMED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderFirmedDate;
    @Column(name = "INST_ID")
    private Integer instId;
    @Column(name = "PREPAID_AMOUNT")
    private Integer prepaidAmount;
    @Column(name="TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name="SALES_PERSON_RESERVATION_ID")
    private Integer salesReservationId;
    
    @OneToOne
    @JoinColumn(name="SALES_PERSON_ID")
    private People salesPerson;
    
    @ManyToOne
    @JoinColumn(name = "SOLD_TO_ORG_ID")
    private Client soldToCustomers ;

    @ManyToOne
    @JoinColumn(name = "SHIP_TO_ORG_ID")
    private ClientSite shipToSite;
    
    @ManyToOne
    @JoinColumn(name = "INVOICE_TO_ORG_ID")
    private ClientSite invoiceToSite;
    
    @OneToMany(mappedBy = "header",cascade = CascadeType.ALL)
    private List<OeOrderLine> lines ; 
    
    @Column(name = "SALES_ORDER_TYPE")
    private SalesOrderType salesOrderType;
    
    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    
    public OeOrderHeader() {
    }

    public OeOrderHeader(Integer headerId) {
        this.headerId = headerId;
    }

    public OeOrderHeader(Integer headerId, Integer orderTypeId, Integer orderNumber, Integer versionNumber, Date creationDate, Integer createdBy, Integer lastUpdatedBy, Date lastUpdateDate, String openFlag, String bookedFlag, String orderCategoryCode) {
        this.headerId = headerId;
        this.orderTypeId = orderTypeId;
        this.orderNumber = orderNumber;
        this.versionNumber = versionNumber;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateDate = lastUpdateDate;
        this.openFlag = openFlag;
        this.bookedFlag = bookedFlag;
        this.orderCategoryCode = orderCategoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
     public OeOrderHeader(ReservationOrder order) {
         
        this.org=order.getOperatingUnitId();
        this.totalAmount=BigDecimal.ZERO;
        this.salesPerson=order.getSalesPersonId();
        this.flowStatusCode=SalesOrderStatus.CLOSED;
        this.salesOrderType=SalesOrderType.PERSON_SALES;
        this.requestDate=order.getReservationDate();
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public OrganizationUnit getOrg() {
        return org;
    }

    public void setOrg(OrganizationUnit org) {
        this.org = org;
    }

    
    public Integer getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getOrderSourceId() {
        return orderSourceId;
    }

    public void setOrderSourceId(Integer orderSourceId) {
        this.orderSourceId = orderSourceId;
    }

    public Integer getSourceDocumentTypeId() {
        return sourceDocumentTypeId;
    }

    public void setSourceDocumentTypeId(Integer sourceDocumentTypeId) {
        this.sourceDocumentTypeId = sourceDocumentTypeId;
    }

    public String getOrigSysDocumentRef() {
        return origSysDocumentRef;
    }

    public void setOrigSysDocumentRef(String origSysDocumentRef) {
        this.origSysDocumentRef = origSysDocumentRef;
    }

    public Integer getSourceDocumentId() {
        return sourceDocumentId;
    }

    public void setSourceDocumentId(Integer sourceDocumentId) {
        this.sourceDocumentId = sourceDocumentId;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(Date pricingDate) {
        this.pricingDate = pricingDate;
    }

    public String getShipmentPriorityCode() {
        return shipmentPriorityCode;
    }

    public void setShipmentPriorityCode(String shipmentPriorityCode) {
        this.shipmentPriorityCode = shipmentPriorityCode;
    }

    public String getDemandClassCode() {
        return demandClassCode;
    }

    public void setDemandClassCode(String demandClassCode) {
        this.demandClassCode = demandClassCode;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public String getTaxExemptFlag() {
        return taxExemptFlag;
    }

    public void setTaxExemptFlag(String taxExemptFlag) {
        this.taxExemptFlag = taxExemptFlag;
    }

    public String getTaxExemptNumber() {
        return taxExemptNumber;
    }

    public void setTaxExemptNumber(String taxExemptNumber) {
        this.taxExemptNumber = taxExemptNumber;
    }

    public String getTaxExemptReasonCode() {
        return taxExemptReasonCode;
    }

    public void setTaxExemptReasonCode(String taxExemptReasonCode) {
        this.taxExemptReasonCode = taxExemptReasonCode;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getConversionTypeCode() {
        return conversionTypeCode;
    }

    public void setConversionTypeCode(String conversionTypeCode) {
        this.conversionTypeCode = conversionTypeCode;
    }

    public Date getConversionRateDate() {
        return conversionRateDate;
    }

    public void setConversionRateDate(Date conversionRateDate) {
        this.conversionRateDate = conversionRateDate;
    }

    public String getPartialShipmentsAllowed() {
        return partialShipmentsAllowed;
    }

    public void setPartialShipmentsAllowed(String partialShipmentsAllowed) {
        this.partialShipmentsAllowed = partialShipmentsAllowed;
    }

    public Integer getShipToleranceAbove() {
        return shipToleranceAbove;
    }

    public void setShipToleranceAbove(Integer shipToleranceAbove) {
        this.shipToleranceAbove = shipToleranceAbove;
    }

    public Integer getShipToleranceBelow() {
        return shipToleranceBelow;
    }

    public void setShipToleranceBelow(Integer shipToleranceBelow) {
        this.shipToleranceBelow = shipToleranceBelow;
    }

    public String getTransactionalCurrCode() {
        return transactionalCurrCode;
    }

    public void setTransactionalCurrCode(String transactionalCurrCode) {
        this.transactionalCurrCode = transactionalCurrCode;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public String getTaxPointCode() {
        return taxPointCode;
    }

    public void setTaxPointCode(String taxPointCode) {
        this.taxPointCode = taxPointCode;
    }

    public String getCustPoNumber() {
        return custPoNumber;
    }

    public void setCustPoNumber(String custPoNumber) {
        this.custPoNumber = custPoNumber;
    }

    public Long getInvoicingRuleId() {
        return invoicingRuleId;
    }

    public void setInvoicingRuleId(Long invoicingRuleId) {
        this.invoicingRuleId = invoicingRuleId;
    }

    public Long getAccountingRuleId() {
        return accountingRuleId;
    }

    public void setAccountingRuleId(Long accountingRuleId) {
        this.accountingRuleId = accountingRuleId;
    }

    public PaymentTerms getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerms paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getShippingMethodCode() {
        return shippingMethodCode;
    }

    public void setShippingMethodCode(String shippingMethodCode) {
        this.shippingMethodCode = shippingMethodCode;
    }

    public String getFreightCarrierCode() {
        return freightCarrierCode;
    }

    public void setFreightCarrierCode(String freightCarrierCode) {
        this.freightCarrierCode = freightCarrierCode;
    }

    public String getFobPointCode() {
        return fobPointCode;
    }

    public void setFobPointCode(String fobPointCode) {
        this.fobPointCode = fobPointCode;
    }

    public String getFreightTermsCode() {
        return freightTermsCode;
    }

    public void setFreightTermsCode(String freightTermsCode) {
        this.freightTermsCode = freightTermsCode;
    }

    public Integer getSoldFromOrgId() {
        return soldFromOrgId;
    }

    public void setSoldFromOrgId(Integer soldFromOrgId) {
        this.soldFromOrgId = soldFromOrgId;
    }

    public ClientSite getInvoiceToSite() {
        return invoiceToSite;
    }

    public ClientSite getShipToSite() {
        return shipToSite;
    }

    public void setInvoiceToSite(ClientSite invoiceToSite) {
        this.invoiceToSite = invoiceToSite;
    }

    public void setShipToSite(ClientSite shipToSite) {
        this.shipToSite = shipToSite;
    }

    public Client getSoldToCustomers() {
        return soldToCustomers;
    }

    public void setSoldToCustomers(Client soldToCustomers) {
        this.soldToCustomers = soldToCustomers;
    }

    
    public Integer getShipFromOrgId() {
        return shipFromOrgId;
    }

    public void setShipFromOrgId(Integer shipFromOrgId) {
        this.shipFromOrgId = shipFromOrgId;
    }

    public Integer getDeliverToOrgId() {
        return deliverToOrgId;
    }

    public void setDeliverToOrgId(Integer deliverToOrgId) {
        this.deliverToOrgId = deliverToOrgId;
    }

    public Integer getSoldToContactId() {
        return soldToContactId;
    }

    public void setSoldToContactId(Integer soldToContactId) {
        this.soldToContactId = soldToContactId;
    }

    public Integer getShipToContactId() {
        return shipToContactId;
    }

    public void setShipToContactId(Integer shipToContactId) {
        this.shipToContactId = shipToContactId;
    }

    public Integer getInvoiceToContactId() {
        return invoiceToContactId;
    }

    public void setInvoiceToContactId(Integer invoiceToContactId) {
        this.invoiceToContactId = invoiceToContactId;
    }

    public Integer getDeliverToContactId() {
        return deliverToContactId;
    }

    public void setDeliverToContactId(Integer deliverToContactId) {
        this.deliverToContactId = deliverToContactId;
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

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
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

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getCancelledFlag() {
        return cancelledFlag;
    }

    public void setCancelledFlag(String cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getBookedFlag() {
        return bookedFlag;
    }

    public void setBookedFlag(String bookedFlag) {
        this.bookedFlag = bookedFlag;
    }

    public Long getSalesrepId() {
        return salesrepId;
    }

    public void setSalesrepId(Long salesrepId) {
        this.salesrepId = salesrepId;
    }

    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public String getOrderDateTypeCode() {
        return orderDateTypeCode;
    }

    public void setOrderDateTypeCode(String orderDateTypeCode) {
        this.orderDateTypeCode = orderDateTypeCode;
    }

    public Integer getEarliestScheduleLimit() {
        return earliestScheduleLimit;
    }

    public void setEarliestScheduleLimit(Integer earliestScheduleLimit) {
        this.earliestScheduleLimit = earliestScheduleLimit;
    }

    public Integer getLatestScheduleLimit() {
        return latestScheduleLimit;
    }

    public void setLatestScheduleLimit(Integer latestScheduleLimit) {
        this.latestScheduleLimit = latestScheduleLimit;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    
    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCreditCardCode() {
        return creditCardCode;
    }

    public void setCreditCardCode(String creditCardCode) {
        this.creditCardCode = creditCardCode;
    }

    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public String getCreditCardApprovalCode() {
        return creditCardApprovalCode;
    }

    public void setCreditCardApprovalCode(String creditCardApprovalCode) {
        this.creditCardApprovalCode = creditCardApprovalCode;
    }

    public String getSalesChannelCode() {
        return salesChannelCode;
    }

    public void setSalesChannelCode(String salesChannelCode) {
        this.salesChannelCode = salesChannelCode;
    }

    public String getFirstAckCode() {
        return firstAckCode;
    }

    public void setFirstAckCode(String firstAckCode) {
        this.firstAckCode = firstAckCode;
    }

    public Date getFirstAckDate() {
        return firstAckDate;
    }

    public void setFirstAckDate(Date firstAckDate) {
        this.firstAckDate = firstAckDate;
    }

    public String getLastAckCode() {
        return lastAckCode;
    }

    public void setLastAckCode(String lastAckCode) {
        this.lastAckCode = lastAckCode;
    }

    public Date getLastAckDate() {
        return lastAckDate;
    }

    public void setLastAckDate(Date lastAckDate) {
        this.lastAckDate = lastAckDate;
    }

    public String getOrderCategoryCode() {
        return orderCategoryCode;
    }

    public void setOrderCategoryCode(String orderCategoryCode) {
        this.orderCategoryCode = orderCategoryCode;
    }

    public String getChangeSequence() {
        return changeSequence;
    }

    public void setChangeSequence(String changeSequence) {
        this.changeSequence = changeSequence;
    }

    public String getDropShipFlag() {
        return dropShipFlag;
    }

    public void setDropShipFlag(String dropShipFlag) {
        this.dropShipFlag = dropShipFlag;
    }

    public Integer getCustomerPaymentTermId() {
        return customerPaymentTermId;
    }

    public void setCustomerPaymentTermId(Integer customerPaymentTermId) {
        this.customerPaymentTermId = customerPaymentTermId;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setShippingInstructions(String shippingInstructions) {
        this.shippingInstructions = shippingInstructions;
    }

    public String getPackingInstructions() {
        return packingInstructions;
    }

    public void setPackingInstructions(String packingInstructions) {
        this.packingInstructions = packingInstructions;
    }

    public SalesOrderStatus getFlowStatusCode() {
        return flowStatusCode;
    }

    public void setFlowStatusCode(SalesOrderStatus flowStatusCode) {
        this.flowStatusCode = flowStatusCode;
    }

    
    public Integer getMarketingSourceCodeId() {
        return marketingSourceCodeId;
    }

    public void setMarketingSourceCodeId(Integer marketingSourceCodeId) {
        this.marketingSourceCodeId = marketingSourceCodeId;
    }

    public Date getCreditCardApprovalDate() {
        return creditCardApprovalDate;
    }

    public void setCreditCardApprovalDate(Date creditCardApprovalDate) {
        this.creditCardApprovalDate = creditCardApprovalDate;
    }

    public String getUpgradedFlag() {
        return upgradedFlag;
    }

    public void setUpgradedFlag(String upgradedFlag) {
        this.upgradedFlag = upgradedFlag;
    }

    public String getCustomerPreferenceSetCode() {
        return customerPreferenceSetCode;
    }

    public void setCustomerPreferenceSetCode(String customerPreferenceSetCode) {
        this.customerPreferenceSetCode = customerPreferenceSetCode;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Integer getLockControl() {
        return lockControl;
    }

    public void setLockControl(Integer lockControl) {
        this.lockControl = lockControl;
    }

    public String getPriceRequestCode() {
        return priceRequestCode;
    }

    public void setPriceRequestCode(String priceRequestCode) {
        this.priceRequestCode = priceRequestCode;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getXmlMessageId() {
        return xmlMessageId;
    }

    public void setXmlMessageId(Integer xmlMessageId) {
        this.xmlMessageId = xmlMessageId;
    }

    public Long getAccountingRuleDuration() {
        return accountingRuleDuration;
    }

    public void setAccountingRuleDuration(Long accountingRuleDuration) {
        this.accountingRuleDuration = accountingRuleDuration;
    }

    public Integer getBlanketNumber() {
        return blanketNumber;
    }

    public void setBlanketNumber(Integer blanketNumber) {
        this.blanketNumber = blanketNumber;
    }

    public String getSalesDocumentTypeCode() {
        return salesDocumentTypeCode;
    }

    public void setSalesDocumentTypeCode(String salesDocumentTypeCode) {
        this.salesDocumentTypeCode = salesDocumentTypeCode;
    }

    public Integer getSoldToPhoneId() {
        return soldToPhoneId;
    }

    public void setSoldToPhoneId(Integer soldToPhoneId) {
        this.soldToPhoneId = soldToPhoneId;
    }

    public String getFulfillmentSetName() {
        return fulfillmentSetName;
    }

    public void setFulfillmentSetName(String fulfillmentSetName) {
        this.fulfillmentSetName = fulfillmentSetName;
    }

    public String getLineSetName() {
        return lineSetName;
    }

    public void setLineSetName(String lineSetName) {
        this.lineSetName = lineSetName;
    }

    public String getDefaultFulfillmentSet() {
        return defaultFulfillmentSet;
    }

    public void setDefaultFulfillmentSet(String defaultFulfillmentSet) {
        this.defaultFulfillmentSet = defaultFulfillmentSet;
    }

    public String getTransactionPhaseCode() {
        return transactionPhaseCode;
    }

    public void setTransactionPhaseCode(String transactionPhaseCode) {
        this.transactionPhaseCode = transactionPhaseCode;
    }

    public String getSalesDocumentName() {
        return salesDocumentName;
    }

    public void setSalesDocumentName(String salesDocumentName) {
        this.salesDocumentName = salesDocumentName;
    }

    public Integer getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(Integer quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getUserStatusCode() {
        return userStatusCode;
    }

    public void setUserStatusCode(String userStatusCode) {
        this.userStatusCode = userStatusCode;
    }

    public String getDraftSubmittedFlag() {
        return draftSubmittedFlag;
    }

    public void setDraftSubmittedFlag(String draftSubmittedFlag) {
        this.draftSubmittedFlag = draftSubmittedFlag;
    }

    public Integer getSourceDocumentVersionNumber() {
        return sourceDocumentVersionNumber;
    }

    public void setSourceDocumentVersionNumber(Integer sourceDocumentVersionNumber) {
        this.sourceDocumentVersionNumber = sourceDocumentVersionNumber;
    }

    public Integer getSoldToSiteUseId() {
        return soldToSiteUseId;
    }

    public void setSoldToSiteUseId(Integer soldToSiteUseId) {
        this.soldToSiteUseId = soldToSiteUseId;
    }

    public String getSupplierSignature() {
        return supplierSignature;
    }

    public void setSupplierSignature(String supplierSignature) {
        this.supplierSignature = supplierSignature;
    }

    public Date getSupplierSignatureDate() {
        return supplierSignatureDate;
    }

    public void setSupplierSignatureDate(Date supplierSignatureDate) {
        this.supplierSignatureDate = supplierSignatureDate;
    }

    public String getCustomerSignature() {
        return customerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public Date getCustomerSignatureDate() {
        return customerSignatureDate;
    }

    public void setCustomerSignatureDate(Date customerSignatureDate) {
        this.customerSignatureDate = customerSignatureDate;
    }

    public Integer getMinisiteId() {
        return minisiteId;
    }

    public void setMinisiteId(Integer minisiteId) {
        this.minisiteId = minisiteId;
    }

    public Integer getEndCustomerId() {
        return endCustomerId;
    }

    public void setEndCustomerId(Integer endCustomerId) {
        this.endCustomerId = endCustomerId;
    }

    public Integer getEndCustomerContactId() {
        return endCustomerContactId;
    }

    public void setEndCustomerContactId(Integer endCustomerContactId) {
        this.endCustomerContactId = endCustomerContactId;
    }

    public Integer getEndCustomerSiteUseId() {
        return endCustomerSiteUseId;
    }

    public void setEndCustomerSiteUseId(Integer endCustomerSiteUseId) {
        this.endCustomerSiteUseId = endCustomerSiteUseId;
    }

    public String getIbOwner() {
        return ibOwner;
    }

    public void setIbOwner(String ibOwner) {
        this.ibOwner = ibOwner;
    }

    public String getIbCurrentLocation() {
        return ibCurrentLocation;
    }

    public void setIbCurrentLocation(String ibCurrentLocation) {
        this.ibCurrentLocation = ibCurrentLocation;
    }

    public String getIbInstalledAtLocation() {
        return ibInstalledAtLocation;
    }

    public void setIbInstalledAtLocation(String ibInstalledAtLocation) {
        this.ibInstalledAtLocation = ibInstalledAtLocation;
    }

    public Date getOrderFirmedDate() {
        return orderFirmedDate;
    }

    public void setOrderFirmedDate(Date orderFirmedDate) {
        this.orderFirmedDate = orderFirmedDate;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(Integer prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public List<OeOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OeOrderLine> lines) {
        this.lines = lines;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public People getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(People salesPerson) {
        this.salesPerson = salesPerson;
    }

    public SalesOrderType getSalesOrderType() {
        return salesOrderType;
    }

    public void setSalesOrderType(SalesOrderType salesOrderType) {
        this.salesOrderType = salesOrderType;
    }

    public Integer getSalesReservationId() {
        return salesReservationId;
    }

    public void setSalesReservationId(Integer salesReservationId) {
        this.salesReservationId = salesReservationId;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (headerId != null ? headerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OeOrderHeader)) {
            return false;
        }
        OeOrderHeader other = (OeOrderHeader) object;
        if ((this.headerId == null && other.headerId != null) || (this.headerId != null && !this.headerId.equals(other.headerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.salesorder.OeOrderHeader[ headerId=" + headerId + " ]";
    }
    
    @PrePersist
    private void prePersist()
    {
       createdBy = 1;
       
       lastUpdatedBy = 1 ;
       
       creationDate = new Date() ;
       
       lastUpdateDate = new Date() ;
       
       orderTypeId = 1 ;
       
       orderNumber = 1 ;
       
       
    }
}
