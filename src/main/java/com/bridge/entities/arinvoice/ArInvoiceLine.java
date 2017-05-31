/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.arinvoice;

import com.bridge.entities.apinvoice.ApInvoiceLine;
import com.bridge.entities.clients.SupplierAcc;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.InvoiceLineType;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.SalesOrderInvoiceType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "AR_INVOICE_DISTRIBUTIONS")
public class ArInvoiceLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "arinvoiceline")
    @TableGenerator(name = "arinvoiceline", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
            pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ArInvoiceLine", valueColumnName = "CURRENT_VALUE")
    @Column(name = "INVOICE_DISTRIBUTION_ID")
    private Integer invoiceDistributionId;

    @ManyToOne
    @JoinColumn(name = "SO_DISTRIBUTION_ID")
    private OeOrderLine salesOrderLine;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "QUANTITY_INVOICED")
    private BigDecimal quantityInvoiced;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private ArInvoice invoice;

    @Column(name = "ADDITIONAL_EXPENSES_FLAG")
    private boolean additionalExpensesFlag;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "LINE_TYPE")
    private InvoiceLineType lineType ;
        
    public ArInvoiceLine() {
    }

    public ArInvoiceLine(OeOrderLine line, SalesOrderInvoiceType invoiceType) {

        this.salesOrderLine = line;

        this.unitPrice = line.getUnitListPrice();

        switch (invoiceType) {
            case SHIPPED:

                this.quantityInvoiced = line.getShippedQuantity().subtract(line.getInvoicedQuantity());

                break;

            case DELIVERD:
            case DUE:

                this.quantityInvoiced = line.getDeliveredQuantity().subtract(line.getInvoicedQuantity());

                break;

            default:

                this.quantityInvoiced = line.getOrderedQuantity();
        }

        this.amount = this.quantityInvoiced.multiply(unitPrice);
    }

    public ArInvoiceLine(Integer invoiceDistributionId) {
        this.invoiceDistributionId = invoiceDistributionId;
    }

    public Integer getInvoiceDistributionId() {
        return invoiceDistributionId;
    }

    public void setInvoiceDistributionId(Integer invoiceDistributionId) {
        this.invoiceDistributionId = invoiceDistributionId;
    }

    public OeOrderLine getSalesOrderLine() {
        return salesOrderLine;
    }

    public void setSalesOrderLine(OeOrderLine salesOrderLine) {
        this.salesOrderLine = salesOrderLine;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public InvoiceLineType getLineType() {
        return lineType;
    }

    public void setLineType(InvoiceLineType lineType) {
        this.lineType = lineType;
    }

    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantityInvoiced() {
        return quantityInvoiced;
    }

    public void setQuantityInvoiced(BigDecimal quantityInvoiced) {
        this.quantityInvoiced = quantityInvoiced;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ArInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(ArInvoice invoice) {
        this.invoice = invoice;
    }

    public boolean isAdditionalExpensesFlag() {
        return additionalExpensesFlag;
    }

    public void setAdditionalExpensesFlag(boolean additionalExpensesFlag) {
        this.additionalExpensesFlag = additionalExpensesFlag;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof ArInvoiceLine)) {
            return false;
        }
        ArInvoiceLine other = (ArInvoiceLine) object;
        if ((this.invoiceDistributionId == null && other.invoiceDistributionId != null) || (this.invoiceDistributionId != null && !this.invoiceDistributionId.equals(other.invoiceDistributionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.arinvoices.ArInvoiceDistribution[ invoiceDistributionId=" + invoiceDistributionId + " ]";
    }

}
