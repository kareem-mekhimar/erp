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
@Table(name = "CASH_TRANSACTIONS")
@NamedQueries({
    @NamedQuery(name = "CashTransaction.findAll", query = "SELECT c FROM CashTransaction c")})
public class CashTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "cashtx")
    @TableGenerator(name = "cashtx",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "CashTx",valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CASH_MNGMNT_ID")
    private Integer cashMngmntId;
    
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    
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
    
    @Size(max = 100)
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "CURRENCY_ID")
    private Integer currencyId ;
    
    public CashTransaction() {
    }

    public CashTransaction(ApInvoicePayment invoicePayment) {
        
        ApInvoice invoice = invoicePayment.getInvoice() ;
        
        this.invoiceId = invoice.getInvoiceId();
        
        this.invoicePaymentId = invoicePayment.getPaymentId() ;
        
        this.currencyId = invoice.getInvoiceCurrency().getCurrencyId();
        
        this.transactionAmount = invoicePayment.getAmount() ;
        
        this.operatingUnitId = invoice.getOperatingUnit().getOrgUnitId() ;
        
        this.transactionDate = invoicePayment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.PAY ;
    }

    public CashTransaction(ArInvoicePayment invoicePayment) {
        
        ArInvoice invoice = invoicePayment.getInvoice() ;
        
        this.invoiceId = invoice.getInvoiceId();
        
        this.invoicePaymentId = invoicePayment.getPaymentId() ;
        
        this.currencyId = invoice.getInvoiceCurrency().getCurrencyId();
        
        this.transactionAmount = invoicePayment.getAmount() ;
        
        this.operatingUnitId = invoice.getOperatingUnit().getOrgUnitId() ;
        
        this.transactionDate = invoicePayment.getPaymentDate() ;
        
        this.transactionType = PaymentTxType.RECIEVE ;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCashMngmntId() {
        return cashMngmntId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setTransactionType(PaymentTxType transactionType) {
        this.transactionType = transactionType;
    }

    
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    
    public void setCashMngmntId(Integer cashMngmntId) {
        this.cashMngmntId = cashMngmntId;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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


    public Integer getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Integer invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof CashTransaction)) {
            return false;
        }
        CashTransaction other = (CashTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.cashmanagement.CashTransaction[ id=" + id + " ]";
    }
    
}
