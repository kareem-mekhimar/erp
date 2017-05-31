/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.arinvoice;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AccountedStatus;
import com.bridge.enums.ApprovalStatus;
import com.bridge.enums.InvoiceStatus;
import com.bridge.enums.InvoiceType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@Table(name = "AR_INVOICES")

public class ArInvoice implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "arinvoice")
    @TableGenerator(name = "arinvoice", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ArInvoice", valueColumnName = "CURRENT_VALUE")
    
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;

    @Size(max = 50)
    @Column(name = "INVOICE_NUM")
    private String invoiceNum;
        
    @ManyToOne
    @JoinColumn(name = "INVOICE_CURRENCY_ID")
    private FndCurrency invoiceCurrency;
    
//    @Size(max = 50)
//    @Column(name = "INVOICE_TYPE_LOOKUP_CODE")
//    private String invoiceTypeLookupCode;
    
    @Column(name = "APPROVAL_STATUS")
    private ApprovalStatus approvalStatus;
    
    @Column(name = "PAYMENT_AMOUNT_TOTAL")
    private BigDecimal paymentAmountTotal = BigDecimal.ZERO;

    @Column(name = "ACCOUNTED_STATUS")
    private AccountedStatus accountedStatus;
    
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate = new Date();
    
    @Column(name = "INVOICE_STATUS")
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinColumn(name = "BILL_TO_SITE_ID")
    private ClientSite clientSite;
    
    @Column(name = "INVOICE_AMOUNT")
    private BigDecimal invoiceAmount;
        
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Client client;
    
    @OneToMany(mappedBy = "invoice",cascade = {CascadeType.ALL})
    private List<ArInvoiceLine> invoiceLines ;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "INVOICE_TYPE_LOOKUP_CODE")
    private InvoiceType invoiceType = InvoiceType.STANDARD;
            
    @ManyToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit ;
    
    @Column(name="JE_HEADER_ID")
    private Integer journalHeaderId ;
    
    @Column(name = "ACCOUNTED_FLAG")
    private boolean accounted ;
    
    @ManyToOne
    @JoinColumn(name = "PARENT_INVOICE_ID")
    private ArInvoice parentInvoice ;
        
    public ArInvoice() {
    }

    public ArInvoice(Client client , ClientSite clientSite , FndCurrency currency , OrganizationUnit org)
    {
        this.client = client ;
        
        this.clientSite = clientSite ;
        
        this.invoiceCurrency = currency ;
        
        this.invoiceStatus = InvoiceStatus.NEVER_VALIDATED ;
        
        this.approvalStatus = ApprovalStatus.NOT_REQUIRED ;
        
        this.accountedStatus = AccountedStatus.NO ;        
        
        this.operatingUnit = org ;
    }
    
    public ArInvoice(ArInvoice parent , InvoiceType type)
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


    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

//    public String getInvoiceTypeLookupCode() {
//        return invoiceTypeLookupCode;
//    }
//
//    public void setInvoiceTypeLookupCode(String invoiceTypeLookupCode) {
//        this.invoiceTypeLookupCode = invoiceTypeLookupCode;
//    }

    public BigDecimal getPaymentAmountTotal() {
        return paymentAmountTotal;
    }

    public void setPaymentAmountTotal(BigDecimal paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }

    public void setAccounted(boolean accounted) {
        this.accounted = accounted;
    }

    public boolean isAccounted() {
        return accounted;
    }

    
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public FndCurrency getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(FndCurrency invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    public ClientSite getClientSite() {
        return clientSite;
    }

    public void setClientSite(ClientSite clientSite) {
        this.clientSite = clientSite;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
    public void setParentInvoice(ArInvoice parentInvoice) {
        this.parentInvoice = parentInvoice;
    }


    public List<ArInvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<ArInvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public Integer getJournalHeaderId() {
        return journalHeaderId;
    }

    public void setJournalHeaderId(Integer journalHeaderId) {
        this.journalHeaderId = journalHeaderId;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public ArInvoice getParentInvoice() {
        return parentInvoice;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
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
        if (!(object instanceof ArInvoice)) {
            return false;
        }
        ArInvoice other = (ArInvoice) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.arinvoices.ArInvoice[ invoiceId=" + invoiceId + " ]";
    }
    
}
