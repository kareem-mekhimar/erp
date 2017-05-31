/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.po;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.clients.ApSupplierSite;
import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.ApSupplierContact;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientContact;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.PoStatus;
import java.io.Serializable;
import java.lang.Integer;
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
 * @author Bridge
 */
@Entity
@Table(name = "PO_HEADERS")
@NamedQueries({
    @NamedQuery(name = "PoHeader.findAll", query = "SELECT p FROM PoHeader p")})
public class PoHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "poHeader")
    @TableGenerator(name = "poHeader",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PoHeader",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PO_HEADER_ID")
    private Integer poHeaderId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENT_ID")
    private int agentId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name="REQUESTED_ON")
    private Date requestedOn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    
    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Client client;
    
    @OneToOne
    @JoinColumn(name = "VENDOR_SITE_ID")
    private ClientSite clientSite;
    
    @OneToOne
    @JoinColumn(name = "VENDOR_PAYMENT_SITE_ID")
    private ClientSite clientPaymentSite;
    
    
    @OneToOne
    @JoinColumn(name = "VENDOR_CONTACT_ID")
    private ClientContact clientContact;
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TERM")
    private PaymentTerms paymentTerm;
    @Size(max = 25)
    @Column(name = "SHIP_VIA_LOOKUP_CODE")
    private String shipViaLookupCode;
    @Size(max = 25)
    @Column(name = "FOB_LOOKUP_CODE")
    private String fobLookupCode;
    @Size(max = 25)
    @Column(name = "FREIGHT_TERMS_LOOKUP_CODE")
    private String freightTermsLookupCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "PO_STATUS")
    private PoStatus poStatus;
    
    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "REVISION_NUM")
    private Integer revisionNum = 1;
    @Size(max = 1)
    @Column(name = "APPROVED_FLAG")
    private String approvedFlag;
    @Column(name = "APPROVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;
    @Column(name = "AMOUNT_LIMIT")
    private Integer amountLimit;
    @Column(name = "MIN_RELEASE_AMOUNT")
    private Integer minReleaseAmount;
    @Size(max = 240)
    @Column(name = "NOTE_TO_AUTHORIZER")
    private String noteToAuthorizer;
    @Size(max = 480)
    @Column(name = "NOTE_TO_VENDOR")
    private String noteToVendor;
    @Size(max = 480)
    @Column(name = "NOTE_TO_RECEIVER")
    private String noteToReceiver;
    @Column(name = "PRINT_COUNT")
    private Integer printCount;
    @Column(name = "PRINTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date printedDate;
    @Size(max = 25)
    @Column(name = "VENDOR_ORDER_NUM")
    private String vendorOrderNum;
    @Size(max = 240)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "RFQ_CLOSE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rfqCloseDate;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Size(max = 1)
    @Column(name = "USER_HOLD_FLAG")
    private String userHoldFlag;
    @Size(max = 1)
    @Column(name = "APPROVAL_REQUIRED_FLAG")
    private String approvalRequiredFlag;
    @Size(max = 1)
    @Column(name = "CANCEL_FLAG")
    private String cancelFlag;
    @Size(max = 1)
    @Column(name = "SUPPLY_AGREEMENT_FLAG")
    private String supplyAgreementFlag;
    @Size(max = 25)
    @Column(name = "CLOSED_CODE")
    private String closedCode;
    @Size(max = 25)
    @Column(name = "REFERENCE_NUM")
    private String referenceNum;
    @Size(max = 1)
    @Column(name = "PENDING_SIGNATURE_FLAG")
    private String pendingSignatureFlag;
    @Size(max = 2000)
    @Column(name = "CHANGE_SUMMARY")
    private String changeSummary;
    @Size(max = 30)
    @Column(name = "DOCUMENT_CREATION_METHOD")
    private String documentCreationMethod;
    @Column(name = "SUBMIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;
    @Size(max = 25)
    @Column(name = "SUPPLIER_NOTIF_METHOD")
    private String supplierNotifMethod;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "FAX")
    private String fax;
    @Size(max = 2000)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "AME_APPROVAL_ID")
    private Integer ameApprovalId;
    @Column(name = "DRAFT_ID")
    private Integer draftId;
    @Column(name = "STATUS_LOOKUP_ID")
    private Integer statusLookupId;
    @Size(max = 255)
    @Column(name = "PO_CODE")
    private String poCode;
    @Size(max = 25)
    @Column(name = "AUTHORIZATION_STATUS")
    private String authorizationStatus;
    @Column(name = "AUTHORIZATION_STATUS_ID")
    private Integer authorizationStatusId;
    @Column(name = "CLOSED_CODE_ID")
    private boolean closedCodeId;
    
    @Column(name = "PO_TOTAL_AMOUNT")
    private BigDecimal totalAmount ;
    
    @Column(name = "INVOICED_AMOUNT")
    private BigDecimal invoicedAmount = BigDecimal.ZERO ;
    
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ORG_UNIT_ID")
    @ManyToOne
    private OrganizationUnit org;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poHeader")
    private List<PoLine> poLines;

    
    public PoHeader() {
    }

    public PoHeader(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    
    
    public PoHeader(Integer poHeaderId, int agentId, Date lastUpdateDate, Integer lastUpdatedBy) {
        this.poHeaderId = poHeaderId;
        this.agentId = agentId;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }


    public boolean isClosedCodeId() {
        return closedCodeId;
    }




    
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientContact getClientContact() {
        return clientContact;
    }

    public void setClientContact(ClientContact clientContact) {
        this.clientContact = clientContact;
    }

    
    

    public PaymentTerms getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerms paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getShipViaLookupCode() {
        return shipViaLookupCode;
    }

    public void setShipViaLookupCode(String shipViaLookupCode) {
        this.shipViaLookupCode = shipViaLookupCode;
    }

    public String getFobLookupCode() {
        return fobLookupCode;
    }

    public void setFobLookupCode(String fobLookupCode) {
        this.fobLookupCode = fobLookupCode;
    }

    public String getFreightTermsLookupCode() {
        return freightTermsLookupCode;
    }

    public void setFreightTermsLookupCode(String freightTermsLookupCode) {
        this.freightTermsLookupCode = freightTermsLookupCode;
    }

    public PoStatus getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(PoStatus poStatus) {
        this.poStatus = poStatus;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRevisionNum() {
        return revisionNum;
    }

    public void setRevisionNum(Integer revisionNum) {
        this.revisionNum = revisionNum;
    }

    public String getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(String approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Integer getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(Integer amountLimit) {
        this.amountLimit = amountLimit;
    }

    public Integer getMinReleaseAmount() {
        return minReleaseAmount;
    }

    public void setMinReleaseAmount(Integer minReleaseAmount) {
        this.minReleaseAmount = minReleaseAmount;
    }

    public String getNoteToAuthorizer() {
        return noteToAuthorizer;
    }

    public void setNoteToAuthorizer(String noteToAuthorizer) {
        this.noteToAuthorizer = noteToAuthorizer;
    }

    public String getNoteToVendor() {
        return noteToVendor;
    }

    public void setNoteToVendor(String noteToVendor) {
        this.noteToVendor = noteToVendor;
    }

    public String getNoteToReceiver() {
        return noteToReceiver;
    }

    public void setNoteToReceiver(String noteToReceiver) {
        this.noteToReceiver = noteToReceiver;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public Date getPrintedDate() {
        return printedDate;
    }

    public void setPrintedDate(Date printedDate) {
        this.printedDate = printedDate;
    }

    public String getVendorOrderNum() {
        return vendorOrderNum;
    }

    public void setVendorOrderNum(String vendorOrderNum) {
        this.vendorOrderNum = vendorOrderNum;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getRfqCloseDate() {
        return rfqCloseDate;
    }

    public void setRfqCloseDate(Date rfqCloseDate) {
        this.rfqCloseDate = rfqCloseDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getUserHoldFlag() {
        return userHoldFlag;
    }

    public void setUserHoldFlag(String userHoldFlag) {
        this.userHoldFlag = userHoldFlag;
    }

    public String getApprovalRequiredFlag() {
        return approvalRequiredFlag;
    }

    public void setApprovalRequiredFlag(String approvalRequiredFlag) {
        this.approvalRequiredFlag = approvalRequiredFlag;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getSupplyAgreementFlag() {
        return supplyAgreementFlag;
    }

    public void setSupplyAgreementFlag(String supplyAgreementFlag) {
        this.supplyAgreementFlag = supplyAgreementFlag;
    }

    public String getClosedCode() {
        return closedCode;
    }

    public void setClosedCode(String closedCode) {
        this.closedCode = closedCode;
    }

    public String getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    public String getPendingSignatureFlag() {
        return pendingSignatureFlag;
    }

    public void setPendingSignatureFlag(String pendingSignatureFlag) {
        this.pendingSignatureFlag = pendingSignatureFlag;
    }

    public String getChangeSummary() {
        return changeSummary;
    }

    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }

    public String getDocumentCreationMethod() {
        return documentCreationMethod;
    }

    public void setDocumentCreationMethod(String documentCreationMethod) {
        this.documentCreationMethod = documentCreationMethod;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getSupplierNotifMethod() {
        return supplierNotifMethod;
    }

    public void setSupplierNotifMethod(String supplierNotifMethod) {
        this.supplierNotifMethod = supplierNotifMethod;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getAmeApprovalId() {
        return ameApprovalId;
    }

    public void setAmeApprovalId(Integer ameApprovalId) {
        this.ameApprovalId = ameApprovalId;
    }

    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
    }

    public Integer getStatusLookupId() {
        return statusLookupId;
    }

    public void setStatusLookupId(Integer statusLookupId) {
        this.statusLookupId = statusLookupId;
    }

    public String getPoCode() {
        return poCode;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public String getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public Integer getAuthorizationStatusId() {
        return authorizationStatusId;
    }

    public void setAuthorizationStatusId(Integer authorizationStatusId) {
        this.authorizationStatusId = authorizationStatusId;
    }

    public boolean getClosedCodeId() {
        return closedCodeId;
    }

    public void setClosedCodeId(boolean closedCodeId) {
        this.closedCodeId = closedCodeId;
    }

    public ClientSite getClientSite() {
        return clientSite;
    }

    public void setClientSite(ClientSite clientSite) {
        this.clientSite = clientSite;
    }

    
    public OrganizationUnit getOrg() {
        return org;
    }

    public void setOrg(OrganizationUnit org) {
        this.org = org;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
    public List<PoLine> getPoLines() {
        return poLines;
    }

    public void setPoLines(List<PoLine> poLines) {
        this.poLines = poLines;
    }

    public BigDecimal getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(BigDecimal invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public ClientSite getClientPaymentSite() {
        return clientPaymentSite;
    }

    public void setClientPaymentSite(ClientSite clientPaymentSite) {
        this.clientPaymentSite = clientPaymentSite;
    }
  
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poHeaderId != null ? poHeaderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoHeader)) {
            return false;
        }
        PoHeader other = (PoHeader) object;
        if ((this.poHeaderId == null && other.poHeaderId != null) || (this.poHeaderId != null && !this.poHeaderId.equals(other.poHeaderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.PoHeader[ poHeaderId=" + poHeaderId + " ]";
    }
    
    @PrePersist
    private void onPersist()
    {
        lastUpdateDate = new Date() ;
        
        lastUpdatedBy = 1 ;
        
        createdBy = 1 ;
        
        creationDate = new Date() ;
    }
}
