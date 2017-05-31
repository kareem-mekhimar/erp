/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.payment.ApInvoicePayment;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.payment.ArInvoicePayment;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ClientType;
import com.bridge.enums.PaymentTxType;
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

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "CL_PREPAYMENTS_TRANSACTIONS")
@NamedQueries({
    @NamedQuery(name = "ClPrepaymentsTransaction.findAll", query = "SELECT c FROM ClPrepaymentsTransaction c")})
public class ClPrepaymentsTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "prePay")
    @TableGenerator(name = "prePay",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PrePayTx",valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "CLIENT_SITE_ID")
    private ClientSite clientSite;
    
    @Column(name = "CLIENT_TYPE")
    private ClientType clientType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private PaymentTxType transactionType;
 
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    
    @ManyToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    
    public ClPrepaymentsTransaction() {
    }

    public ClPrepaymentsTransaction(ApInvoice invoice ) {
     
        this.client = invoice.getClient();
       
        this.clientSite = invoice.getClientSite();
      
        this.clientType = ClientType.SUPPLIER;
      
        this.transactionType = PaymentTxType.PAY;
        
        this.transactionDate = invoice.getInvoiceDate();
        
        this.transactionAmount = invoice.getInvoiceAmount();
       
        this.operatingUnit = invoice.getOperatingUnit() ;
        
        this.currency = invoice.getInvoiceCurrency() ;
    }

       
    public ClPrepaymentsTransaction(ArInvoice invoice) {
     
        this.client = invoice.getClient();
       
        this.clientSite = invoice.getClientSite();
      
        this.clientType = ClientType.CUSTOMER;
      
        this.transactionType = PaymentTxType.RECIEVE;
        
        this.transactionDate = invoice.getInvoiceDate();
        
        this.transactionAmount = invoice.getInvoiceAmount();
       
        this.operatingUnit = invoice.getOperatingUnit() ;
        
        this.currency = invoice.getInvoiceCurrency() ;
    }
         
    public ClPrepaymentsTransaction(ApInvoicePayment payment) {
    
        this(payment.getInvoice());
        
        this.transactionDate = payment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.CLEAR ;
        
        this.transactionAmount = payment.getAmount().negate() ;
    }
    
    public ClPrepaymentsTransaction(ArInvoicePayment payment) {
    
        this(payment.getInvoice());
        
        this.transactionDate = payment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.CLEAR ;
        
        this.transactionAmount = payment.getAmount().negate() ;
    }
    
    
    public ClPrepaymentsTransaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }




    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientSite getClientSite() {
        return clientSite;
    }

    public void setClientSite(ClientSite clientSite) {
        this.clientSite = clientSite;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public PaymentTxType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(PaymentTxType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
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
        if (!(object instanceof ClPrepaymentsTransaction)) {
            return false;
        }
        ClPrepaymentsTransaction other = (ClPrepaymentsTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ClPrepaymentsTransaction[ id=" + id + " ]";
    }
    
}
