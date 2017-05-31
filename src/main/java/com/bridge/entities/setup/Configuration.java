/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.setup;

import com.bridge.entities.inventory.SecondaryInventory;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "PAYABLE_MODULE_SETUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c")})
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "GL_ID")
    private Integer glId;
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    @Size(max = 50)
    @Column(name = "ACCOUNT_PAYABLE_SETUP_FLAG")
    private String accountPayableSetupFlag;
    @Column(name = "LIABILITY_ACCOUNT_ID")
    private Integer liabilityAccountId;
    @Column(name = "PREPAYMENT_ACCOUNT_ID")
    private Integer prepaymentAccountId;
    @Column(name = "DISCOUNT_ACCOUNT_ID")
    private Integer discountAccountId;
    @Column(name = "PO_RATE_VRINC_GAIN_ACCOUNT_ID")
    private Integer poRateVrincGainAccountId;
    @Column(name = "PO_RATE_VRINCE_LOSS_ACCOUNT_ID")
    private Integer poRateVrinceLossAccountId;
    @Column(name = "BILLS_PAYABLE_ACCOUNT_ID")
    private Integer billsPayableAccountId;
    @Column(name = "EXPESNES_CLEARING_ACCOUNT_ID")
    private Integer expesnesClearingAccountId;
    @Column(name = "MISCELLANEOUS_ACCOUNT_ID")
    private Integer miscellaneousAccountId;
    @Column(name = "RETAINAGE_ACCOUNT_ID")
    private Integer retainageAccountId;
    @Column(name = "SHIP_TO_LOCATION")
    private Integer shipToLocation;
    @Column(name = "BILL_TO_LOCATION")
    private Integer billToLocation;
    @Column(name = "INVOICE_MATCH_OPTION")
    private Integer invoiceMatchOption;
    @Column(name = "HOLD_UNMATCHED_INVOICES")
    private Integer holdUnmatchedInvoices;
    @Column(name = "INVOICE_CURRENCY")
    private Integer invoiceCurrency;
    @Column(name = "PAY_GROUP")
    private Integer payGroup;
    @Column(name = "TERMS_DATE_BASIS")
    private Integer termsDateBasis;
    @Column(name = "PAY_DATE_BASIS")
    private Integer payDateBasis;
    @Column(name = "PAYMENT_TERMS")
    private Integer paymentTerms;
    @OneToOne
    @JoinColumn(name = "SHIPPING_INVENTORY")
    private SecondaryInventory shippingInventory;

    public Configuration() {
    }

    public Configuration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
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

    public String getAccountPayableSetupFlag() {
        return accountPayableSetupFlag;
    }

    public void setAccountPayableSetupFlag(String accountPayableSetupFlag) {
        this.accountPayableSetupFlag = accountPayableSetupFlag;
    }

    public Integer getLiabilityAccountId() {
        return liabilityAccountId;
    }

    public void setLiabilityAccountId(Integer liabilityAccountId) {
        this.liabilityAccountId = liabilityAccountId;
    }

    public Integer getPrepaymentAccountId() {
        return prepaymentAccountId;
    }

    public void setPrepaymentAccountId(Integer prepaymentAccountId) {
        this.prepaymentAccountId = prepaymentAccountId;
    }

    public Integer getDiscountAccountId() {
        return discountAccountId;
    }

    public void setDiscountAccountId(Integer discountAccountId) {
        this.discountAccountId = discountAccountId;
    }

    public Integer getPoRateVrincGainAccountId() {
        return poRateVrincGainAccountId;
    }

    public void setPoRateVrincGainAccountId(Integer poRateVrincGainAccountId) {
        this.poRateVrincGainAccountId = poRateVrincGainAccountId;
    }

    public Integer getPoRateVrinceLossAccountId() {
        return poRateVrinceLossAccountId;
    }

    public void setPoRateVrinceLossAccountId(Integer poRateVrinceLossAccountId) {
        this.poRateVrinceLossAccountId = poRateVrinceLossAccountId;
    }

    public Integer getBillsPayableAccountId() {
        return billsPayableAccountId;
    }

    public void setBillsPayableAccountId(Integer billsPayableAccountId) {
        this.billsPayableAccountId = billsPayableAccountId;
    }

    public Integer getExpesnesClearingAccountId() {
        return expesnesClearingAccountId;
    }

    public void setExpesnesClearingAccountId(Integer expesnesClearingAccountId) {
        this.expesnesClearingAccountId = expesnesClearingAccountId;
    }

    public Integer getMiscellaneousAccountId() {
        return miscellaneousAccountId;
    }

    public void setMiscellaneousAccountId(Integer miscellaneousAccountId) {
        this.miscellaneousAccountId = miscellaneousAccountId;
    }

    public Integer getRetainageAccountId() {
        return retainageAccountId;
    }

    public void setRetainageAccountId(Integer retainageAccountId) {
        this.retainageAccountId = retainageAccountId;
    }

    public Integer getShipToLocation() {
        return shipToLocation;
    }

    public void setShipToLocation(Integer shipToLocation) {
        this.shipToLocation = shipToLocation;
    }

    public Integer getBillToLocation() {
        return billToLocation;
    }

    public void setBillToLocation(Integer billToLocation) {
        this.billToLocation = billToLocation;
    }

    public Integer getInvoiceMatchOption() {
        return invoiceMatchOption;
    }

    public void setInvoiceMatchOption(Integer invoiceMatchOption) {
        this.invoiceMatchOption = invoiceMatchOption;
    }

    public Integer getHoldUnmatchedInvoices() {
        return holdUnmatchedInvoices;
    }

    public void setHoldUnmatchedInvoices(Integer holdUnmatchedInvoices) {
        this.holdUnmatchedInvoices = holdUnmatchedInvoices;
    }

    public Integer getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(Integer invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public Integer getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(Integer payGroup) {
        this.payGroup = payGroup;
    }

    public Integer getTermsDateBasis() {
        return termsDateBasis;
    }

    public void setTermsDateBasis(Integer termsDateBasis) {
        this.termsDateBasis = termsDateBasis;
    }

    public Integer getPayDateBasis() {
        return payDateBasis;
    }

    public void setPayDateBasis(Integer payDateBasis) {
        this.payDateBasis = payDateBasis;
    }

    public Integer getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(Integer paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public SecondaryInventory getShippingInventory() {
        return shippingInventory;
    }

    public void setShippingInventory(SecondaryInventory shippingInventory) {
        this.shippingInventory = shippingInventory;
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
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.setup.Configuration[ id=" + id + " ]";
    }
    
}
