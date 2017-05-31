/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.util.Objects;
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
 * @author AIA
 */
@Entity
@Table(name = "AP_SUPPLIER_SITES_ACCOUNTING")
@NamedQueries({
    @NamedQuery(name = "SiteAccount.findAll", query = "SELECT s FROM SiteAccount s")})
public class SiteAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
       @GeneratedValue(strategy = GenerationType.TABLE,generator = "SiteAccount")
    @TableGenerator(name = "SiteAccount",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "SiteAccount",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNTING_ID")
    private Integer accountingId;
    @OneToOne
    @JoinColumn(name = "VENDOR_SITE_ID",referencedColumnName ="VENDOR_SITE_ID" )
    private ApSupplierSite vendorSiteId;
    @Column(name = "LEDGER_ID")
    private Integer ledgerId;
    @Column(name = "LEGAL_ENTITY_ID")
    private Integer legalEntityId;
    @Size(max = 100)
    @Column(name = "LEGAL_ENTITY_NAME")
    private String legalEntityName;
    @Column(name = "OPERATING_UNIT_ID")
    private Integer operatingUnitId;
    @Size(max = 100)
    @Column(name = "OPERATING_UNIT_NAME")
    private String operatingUnitName;
    @Size(max = 100)
    @Column(name = "VENDOR_SITE_NAME")
    private String vendorSiteName;
    @OneToOne
    @JoinColumn(name = "LIABILITY_ACCOUNT_ID",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination liabilityAccountId;
    @Size(max = 250)
    @Column(name = "LIABILITY_ACCOUNT_NAME")
    private String liabilityAccountName;
    @OneToOne
    @JoinColumn(name = "PREPAYMENT_ACCOUNT_ID",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination prepaymentAccountId;
    @Size(max = 250)
    @Column(name = "PREPAYMENT_ACCOUNT_NAME")
    private String prepaymentAccountName;
    @OneToOne
    @JoinColumn(name = "BILLS_PAYABLE_ACCOUNT_ID",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination billsPayableAccountId;
    @Size(max = 250)
    @Column(name = "BILLS_PAYABLE_ACCOUNT_NAME")
    private String billsPayableAccountName;
    @Column(name = "ENABLED_FLAG")
    private Integer enabledFlag;
    
    @ManyToOne
    @JoinColumn(name = "VENDOR_ID",referencedColumnName = "VENDOR_ID")
    private ApSupplier vendor;

    public SiteAccount() {
    }

    public SiteAccount(Integer accountingId) {
        this.accountingId = accountingId;
    }

    public Integer getAccountingId() {
        return accountingId;
    }

    public void setAccountingId(Integer accountingId) {
        this.accountingId = accountingId;
    }

    public ApSupplierSite getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(ApSupplierSite vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public String getLegalEntityName() {
        return legalEntityName;
    }

    public void setLegalEntityName(String legalEntityName) {
        this.legalEntityName = legalEntityName;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public String getOperatingUnitName() {
        return operatingUnitName;
    }

    public void setOperatingUnitName(String operatingUnitName) {
        this.operatingUnitName = operatingUnitName;
    }

    public String getVendorSiteName() {
        return vendorSiteName;
    }

    public void setVendorSiteName(String vendorSiteName) {
        this.vendorSiteName = vendorSiteName;
    }

    public GlCodeCombination getLiabilityAccountId() {
        return liabilityAccountId;
    }

    public void setLiabilityAccountId(GlCodeCombination liabilityAccountId) {
        this.liabilityAccountId = liabilityAccountId;
    }

    public String getLiabilityAccountName() {
        return liabilityAccountName;
    }

    public void setLiabilityAccountName(String liabilityAccountName) {
        this.liabilityAccountName = liabilityAccountName;
    }

    public GlCodeCombination getPrepaymentAccountId() {
        return prepaymentAccountId;
    }

    public void setPrepaymentAccountId(GlCodeCombination prepaymentAccountId) {
        this.prepaymentAccountId = prepaymentAccountId;
    }

    public String getPrepaymentAccountName() {
        return prepaymentAccountName;
    }

    public void setPrepaymentAccountName(String prepaymentAccountName) {
        this.prepaymentAccountName = prepaymentAccountName;
    }

    public GlCodeCombination getBillsPayableAccountId() {
        return billsPayableAccountId;
    }

    public void setBillsPayableAccountId(GlCodeCombination billsPayableAccountId) {
        this.billsPayableAccountId = billsPayableAccountId;
    }

    public String getBillsPayableAccountName() {
        return billsPayableAccountName;
    }

    public void setBillsPayableAccountName(String billsPayableAccountName) {
        this.billsPayableAccountName = billsPayableAccountName;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public ApSupplier getVendor() {
        return vendor;
    }

    public void setVendor(ApSupplier vendor) {
        this.vendor = vendor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.accountingId);
        hash = 79 * hash + Objects.hashCode(this.vendorSiteId);
        hash = 79 * hash + Objects.hashCode(this.ledgerId);
        hash = 79 * hash + Objects.hashCode(this.legalEntityId);
        hash = 79 * hash + Objects.hashCode(this.operatingUnitId);
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
        final SiteAccount other = (SiteAccount) obj;
        if (!Objects.equals(this.accountingId, other.accountingId)) {
            return false;
        }
        if (!Objects.equals(this.vendorSiteId, other.vendorSiteId)) {
            return false;
        }
        if (!Objects.equals(this.ledgerId, other.ledgerId)) {
            return false;
        }
        if (!Objects.equals(this.legalEntityId, other.legalEntityId)) {
            return false;
        }
        if (!Objects.equals(this.operatingUnitId, other.operatingUnitId)) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {
        return "com.bridge.entities.clients.SiteAccount[ accountingId=" + accountingId + " ]";
    }
    
}
