/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.cashmanagement;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.payment.ApInvoicePayment;
import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.payment.ArInvoicePayment;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.enums.BankCheckStatus;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "BANK_ACCOUNT_TRANSACTIONS")
@NamedQueries({
    @NamedQuery(name = "BankAccountTransaction.findAll", query = "SELECT b FROM BankAccountTransaction b")})
public class BankAccountTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "banktx")
    @TableGenerator(name = "banktx",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "BankTx",valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "BANK_ACCOUNT_ID")
    private Integer bankAccountId;
    
    @Column(name = "TRANSACTION_DATE")
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private PaymentTxType transactionType;
    
    @Column(name = "INVOICE_ID")
    private Integer invoiceId;
    
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    
    @Column(name = "INVOICE_PAYMENT_ID")
    private Integer invoicePaymentId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CHECK_STATUS")
    private BankCheckStatus checkStatus;
    
    @Size(max = 100)
    @Column(name = "CHECK_NUMBER")
    private String checkNumber;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private FndCurrency currency ;
    
    @Column(name = "CLEARED_FLAG")    
    private boolean cleared ;
    
    public BankAccountTransaction() {
    }

    public BankAccountTransaction(ApInvoicePayment invoicePayment) {
        
        ApInvoice invoice = invoicePayment.getInvoice() ;
        
        this.invoiceId = invoice.getInvoiceId();
        
        this.invoicePaymentId = invoicePayment.getPaymentId();
        
        this.currency = invoice.getInvoiceCurrency();
        
        this.transactionAmount = invoicePayment.getAmount() ;
        
        this.transactionDate = invoicePayment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.PAY ;
        
        this.checkNumber = invoicePayment.getCheckNo() ;
        
        this.bankAccountId = invoicePayment.getBankAccount().getBankAccountId();
        
        this.checkStatus = BankCheckStatus.ISSUED;
                
    }

    public BankAccountTransaction(ArInvoicePayment invoicePayment) {
        
        ArInvoice invoice = invoicePayment.getInvoice() ;
        
        this.invoiceId = invoice.getInvoiceId();
        
        this.invoicePaymentId = invoicePayment.getPaymentId();
        
        this.currency = invoice.getInvoiceCurrency();
        
        this.transactionAmount = invoicePayment.getAmount() ;

        this.transactionDate = invoicePayment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.RECIEVE ;

        this.checkNumber = invoicePayment.getCheckNo() ;
        
        this.bankAccountId = invoicePayment.getBankAccount().getBankAccountId() ;
        
        this.checkStatus = BankCheckStatus.ISSUED;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public FndCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(FndCurrency currency) {
        this.currency = currency;
    }

    
    public PaymentTxType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(PaymentTxType transactionType) {
        this.transactionType = transactionType;
    }

    
    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public Integer getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Integer invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public BankCheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(BankCheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
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
        if (!(object instanceof BankAccountTransaction)) {
            return false;
        }
        BankAccountTransaction other = (BankAccountTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.cashmanagement.BankAccountTransaction[ id=" + id + " ]";
    }
    
}
