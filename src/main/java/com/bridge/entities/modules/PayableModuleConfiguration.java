/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AutomaticOffsetMethod;
import com.bridge.enums.BillsPayableAccountSource;
import com.bridge.enums.DiscountMethods;
import com.bridge.enums.InterestOptions;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.PaymentDateBasis;
import com.bridge.enums.PaymentGroup;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.PrepaymentAccountOptions;
import com.bridge.enums.TermsDateBasis;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "PAYABLE_MODULE_SETUP")
@NamedQueries({
    @NamedQuery(name = "PayableModuleConfiguration.findAll", query = "SELECT g FROM PayableModuleConfiguration g")})
public class PayableModuleConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "glconfig")
    @TableGenerator(name = "glconfig",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "GlModuleConfig",valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "GL_ID")
    private Integer glId;
    
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    
    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;
    
    
    @Column(name = "ACCOUNT_PAYABLE_SETUP_FLAG")
    private String accountPayableSetupFlag;
    
    @ManyToOne
    @JoinColumn(name = "LIABILITY_ACCOUNT_ID")
    private GlCodeCombination liabilityAccount;
    
    @ManyToOne
    @JoinColumn(name = "PREPAYMENT_ACCOUNT_ID")
    private GlCodeCombination prepaymentAccount;
    
    @ManyToOne
    @JoinColumn(name = "DISCOUNT_ACCOUNT_ID")
    private GlCodeCombination discountAccount;
    
    @ManyToOne
    @JoinColumn(name = "PO_RATE_VRINC_GAIN_ACCOUNT_ID")
    private GlCodeCombination poRateVrincGainAccount;
    
    @ManyToOne
    @JoinColumn(name = "PO_RATE_VRINCE_LOSS_ACCOUNT_ID")
    private GlCodeCombination poRateVrinceLossAccount;
    
    @ManyToOne
    @JoinColumn(name = "BILLS_PAYABLE_ACCOUNT_ID")
    private GlCodeCombination billsPayableAccount;
    
    @ManyToOne
    @JoinColumn(name = "EXPESNES_CLEARING_ACCOUNT_ID")
    private GlCodeCombination expesnesClearingAccount;
    
    @ManyToOne
    @JoinColumn(name = "MISCELLANEOUS_ACCOUNT_ID")
    private GlCodeCombination miscellaneousAccount;
    
    @ManyToOne
    @JoinColumn(name = "RETAINAGE_ACCOUNT_ID")
    private GlCodeCombination retainageAccount;
    
    @Column(name = "SHIP_TO_LOCATION")
    private Integer shipToLocation;
    
    @Column(name = "BILL_TO_LOCATION")
    private Integer billToLocation;
    
    @Column(name = "INVOICE_MATCH_OPTION")
    private InvoiceMatchingOptions invoiceMatchOption;
    
    @Column(name = "HOLD_UNMATCHED_INVOICES")
    private boolean holdUnmatchedInvoices;
   
    @ManyToOne
    @JoinColumn(name = "INVOICE_CURRENCY")
    private FndCurrency invoiceCurrency;
    
    @Column(name = "PAY_GROUP")
    private PaymentGroup payGroup;
    
    @Column(name = "TERMS_DATE_BASIS")
    private TermsDateBasis termsDateBasis;
    @Column(name = "PAY_DATE_BASIS")
    private PaymentDateBasis payDateBasis;
    
    @Column(name = "PAYMENT_TERMS")
    private PaymentTerms paymentTerms;
    
    @Column(name = "SHIPPING_INVENTORY")
    private Integer shippingInventory;
    
    // check box
    @Column(name = "PAYMENT_ACC_WHEN_ISSUED")
    private boolean paymentAccWhenIssued;
    
    // check box
    @Column(name = "PAYMENT_ACC_WHEN_CLEARS")
    private boolean paymentAccWhenClears;
    
    // check box
    @Column(name = "GAINLOSS_PAY_ACC_WHEN_ISSUED")
    private boolean gainlossPayAccWhenIssued;
    
    // check box
    @Column(name = "GAINLOSS_PAY_ACC_WHEN_CLEARS")
    private boolean gainlossPayAccWhenClears;
    
    // radio buttons
    @Column(name = "BILLS_PAY_ACC_SRC")
    private BillsPayableAccountSource billsPayAccSrc;
    
    // radio buttons
    @Column(name = "AUTOMATIC_OFFSET_MTHOD")
    private AutomaticOffsetMethod automaticOffsetMthod;
    
    // radio buttons
    @Column(name = "DISCOUNT_METHOD")
    private DiscountMethods discountMethod;
    
    // radio buttons
    @Column(name = "INTEREST")
    private InterestOptions interest;
    
    // radio buttons
    @Column(name = "PREPAYMENT_ACCOUNT_OPTION")
    private PrepaymentAccountOptions prepaymentAccountOption;

    // check box
    @Column(name = "USE_MULTIPLE_CURRENCY")
    private boolean useMultipleCurrency;
    
    // check box
    @Column(name = "EXCHANGE_RATE_ENTRY_REQ")
    private boolean exchangeRateEntryReq;
    
    // check box
    @Column(name = "CALC_USR_EXCHANGE_RATE")
    private boolean calcUsrExchangeRate;
    
    // account combination
    @ManyToOne
    @JoinColumn(name = "REALIZED_GAIN_ACC")
    private GlCodeCombination realizedGainAcc;
    
    // account combination  
    @ManyToOne
    @JoinColumn(name = "REALIZED_LOSS_ACC")
    private GlCodeCombination  realizedLossAcc;
    
    // account combination
    @ManyToOne
    @JoinColumn(name = "ROUNDING_ACC")
    private GlCodeCombination roundingAcc;
    
    
     // account combination
    @ManyToOne
    @JoinColumn(name = "CHARGE_ACCOUNT")
    private GlCodeCombination chargeAccount;   
    
    // account combination
    @ManyToOne
    @JoinColumn(name = "CASH_CLEARING_ACC")
    private GlCodeCombination cachClearingAcc; 
    

    public PayableModuleConfiguration() {
    }

    public PayableModuleConfiguration(Integer id) {
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

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getAccountPayableSetupFlag() {
        return accountPayableSetupFlag;
    }

    public void setAccountPayableSetupFlag(String accountPayableSetupFlag) {
        this.accountPayableSetupFlag = accountPayableSetupFlag;
    }

    
    public GlCodeCombination getLiabilityAccount() {
        return liabilityAccount;
    }

    public void setLiabilityAccount(GlCodeCombination liabilityAccount) {
        this.liabilityAccount = liabilityAccount;
    }

    public GlCodeCombination getPrepaymentAccount() {
        return prepaymentAccount;
    }

    public void setPrepaymentAccount(GlCodeCombination prepaymentAccount) {
        this.prepaymentAccount = prepaymentAccount;
    }

    public GlCodeCombination getDiscountAccount() {
        return discountAccount;
    }

    public void setDiscountAccount(GlCodeCombination discountAccount) {
        this.discountAccount = discountAccount;
    }

    public GlCodeCombination getPoRateVrincGainAccount() {
        return poRateVrincGainAccount;
    }

    public void setPoRateVrincGainAccount(GlCodeCombination poRateVrincGainAccount) {
        this.poRateVrincGainAccount = poRateVrincGainAccount;
    }

    public GlCodeCombination getPoRateVrinceLossAccount() {
        return poRateVrinceLossAccount;
    }

    public void setPoRateVrinceLossAccount(GlCodeCombination poRateVrinceLossAccount) {
        this.poRateVrinceLossAccount = poRateVrinceLossAccount;
    }

    public GlCodeCombination getBillsPayableAccount() {
        return billsPayableAccount;
    }

    public void setBillsPayableAccount(GlCodeCombination billsPayableAccount) {
        this.billsPayableAccount = billsPayableAccount;
    }

    public GlCodeCombination getExpesnesClearingAccount() {
        return expesnesClearingAccount;
    }

    public void setExpesnesClearingAccount(GlCodeCombination expesnesClearingAccount) {
        this.expesnesClearingAccount = expesnesClearingAccount;
    }

    public GlCodeCombination getMiscellaneousAccount() {
        return miscellaneousAccount;
    }

    public void setMiscellaneousAccount(GlCodeCombination miscellaneousAccount) {
        this.miscellaneousAccount = miscellaneousAccount;
    }

    public GlCodeCombination getRetainageAccount() {
        return retainageAccount;
    }

    public void setRetainageAccount(GlCodeCombination retainageAccount) {
        this.retainageAccount = retainageAccount;
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

    public InvoiceMatchingOptions getInvoiceMatchOption() {
        return invoiceMatchOption;
    }

    public void setInvoiceMatchOption(InvoiceMatchingOptions invoiceMatchOption) {
        this.invoiceMatchOption = invoiceMatchOption;
    }

    public boolean isHoldUnmatchedInvoices() {
        return holdUnmatchedInvoices;
    }

    public void setHoldUnmatchedInvoices(boolean holdUnmatchedInvoices) {
        this.holdUnmatchedInvoices = holdUnmatchedInvoices;
    }

    
    public FndCurrency getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(FndCurrency invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    
    public PaymentGroup getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(PaymentGroup payGroup) {
        this.payGroup = payGroup;
    }

    public TermsDateBasis getTermsDateBasis() {
        return termsDateBasis;
    }

    public void setTermsDateBasis(TermsDateBasis termsDateBasis) {
        this.termsDateBasis = termsDateBasis;
    }

    public PaymentDateBasis getPayDateBasis() {
        return payDateBasis;
    }

    public void setPayDateBasis(PaymentDateBasis payDateBasis) {
        this.payDateBasis = payDateBasis;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Integer getShippingInventory() {
        return shippingInventory;
    }

    public void setShippingInventory(Integer shippingInventory) {
        this.shippingInventory = shippingInventory;
    }

    public boolean isPaymentAccWhenIssued() {
        return paymentAccWhenIssued;
    }

    public void setPaymentAccWhenIssued(boolean paymentAccWhenIssued) {
        this.paymentAccWhenIssued = paymentAccWhenIssued;
    }

    public boolean isPaymentAccWhenClears() {
        return paymentAccWhenClears;
    }

    public void setPaymentAccWhenClears(boolean paymentAccWhenClears) {
        this.paymentAccWhenClears = paymentAccWhenClears;
    }

    public boolean isGainlossPayAccWhenIssued() {
        return gainlossPayAccWhenIssued;
    }

    public void setGainlossPayAccWhenIssued(boolean gainlossPayAccWhenIssued) {
        this.gainlossPayAccWhenIssued = gainlossPayAccWhenIssued;
    }

    public boolean isGainlossPayAccWhenClears() {
        return gainlossPayAccWhenClears;
    }

    public void setGainlossPayAccWhenClears(boolean gainlossPayAccWhenClears) {
        this.gainlossPayAccWhenClears = gainlossPayAccWhenClears;
    }

 

    public BillsPayableAccountSource getBillsPayAccSrc() {
        return billsPayAccSrc;
    }

    public void setBillsPayAccSrc(BillsPayableAccountSource billsPayAccSrc) {
        this.billsPayAccSrc = billsPayAccSrc;
    }

    public AutomaticOffsetMethod getAutomaticOffsetMthod() {
        return automaticOffsetMthod;
    }

    public void setAutomaticOffsetMthod(AutomaticOffsetMethod automaticOffsetMthod) {
        this.automaticOffsetMthod = automaticOffsetMthod;
    }

    public DiscountMethods getDiscountMethod() {
        return discountMethod;
    }

    public void setDiscountMethod(DiscountMethods discountMethod) {
        this.discountMethod = discountMethod;
    }

    public InterestOptions getInterest() {
        return interest;
    }

    public void setInterest(InterestOptions interest) {
        this.interest = interest;
    }

    public boolean isUseMultipleCurrency() {
        return useMultipleCurrency;
    }

    public void setUseMultipleCurrency(boolean useMultipleCurrency) {
        this.useMultipleCurrency = useMultipleCurrency;
    }

    public boolean isExchangeRateEntryReq() {
        return exchangeRateEntryReq;
    }

    public void setExchangeRateEntryReq(boolean exchangeRateEntryReq) {
        this.exchangeRateEntryReq = exchangeRateEntryReq;
    }

    public boolean isCalcUsrExchangeRate() {
        return calcUsrExchangeRate;
    }

    public void setCalcUsrExchangeRate(boolean calcUsrExchangeRate) {
        this.calcUsrExchangeRate = calcUsrExchangeRate;
    }


    public GlCodeCombination getRealizedGainAcc() {
        return realizedGainAcc;
    }

    public void setRealizedGainAcc(GlCodeCombination realizedGainAcc) {
        this.realizedGainAcc = realizedGainAcc;
    }

    public GlCodeCombination getRealizedLossAcc() {
        return realizedLossAcc;
    }

    public void setRealizedLossAcc(GlCodeCombination realizedLossAcc) {
        this.realizedLossAcc = realizedLossAcc;
    }

    public GlCodeCombination getRoundingAcc() {
        return roundingAcc;
    }

    public void setRoundingAcc(GlCodeCombination roundingAcc) {
        this.roundingAcc = roundingAcc;
    }

    public PrepaymentAccountOptions getPrepaymentAccountOption() {
        return prepaymentAccountOption;
    }

    public void setPrepaymentAccountOption(PrepaymentAccountOptions prepaymentAccountOption) {
        this.prepaymentAccountOption = prepaymentAccountOption;
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
        if (!(object instanceof PayableModuleConfiguration)) {
            return false;
        }
        PayableModuleConfiguration other = (PayableModuleConfiguration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.ledger.GlModulesConfiguration[ id=" + id + " ]";
    }

    public GlCodeCombination getChargeAccount() {
        return chargeAccount;
    }

    public void setChargeAccount(GlCodeCombination chargeAccount) {
        this.chargeAccount = chargeAccount;
    }

    public GlCodeCombination getCachClearingAcc() {
        return cachClearingAcc;
    }

    public void setCachClearingAcc(GlCodeCombination cachClearingAcc) {
        this.cachClearingAcc = cachClearingAcc;
    }
    
}
