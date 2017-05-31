/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
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
@Table(name = "RECEIVABLE_MODULE_SETUP")
@NamedQueries({
    @NamedQuery(name = "ReceivableModuleSetup.findAll", query = "SELECT r FROM ReceivableModuleSetup r")})
public class ReceivableModuleSetup implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "glconfig")
    @TableGenerator(name = "glconfig", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "ReceivableModuleSetup", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;

    @Size(max = 100)
    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "REALIZED_GAINS_ACC")
    private GlCodeCombination realizedGainsAcc;

    @ManyToOne
    @JoinColumn(name = "REALIZED_LOSSES_ACC")
    private GlCodeCombination realizedLossesAcc;

    @ManyToOne
    @JoinColumn(name = "TAX_ACC")
    private GlCodeCombination taxAcc;

    @ManyToOne
    @JoinColumn(name = "UNALLOCATED_REVENUE_ACC")
    private GlCodeCombination unallocatedRevenueAcc;

    @Column(name = "CROSS_CURRENCY_RATE_TYPE")
    private int crossCurrencyRateType;

    @ManyToOne
    @JoinColumn(name = "CROSS_CURRENCY_ROUNDING_ACC")
    private GlCodeCombination crossCurrencyRoundingAcc;

    @ManyToOne
    @JoinColumn(name = "CASH_ACC")
    private GlCodeCombination cashAcc;

    @ManyToOne
    @JoinColumn(name = "REVENUE_ACC")
    private GlCodeCombination revenueAcc;

    public ReceivableModuleSetup() {
    }

    public ReceivableModuleSetup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GlCodeCombination getRealizedGainsAcc() {
        return realizedGainsAcc;
    }

    public void setRealizedGainsAcc(GlCodeCombination realizedGainsAcc) {
        this.realizedGainsAcc = realizedGainsAcc;
    }

    public GlCodeCombination getRealizedLossesAcc() {
        return realizedLossesAcc;
    }

    public void setRealizedLossesAcc(GlCodeCombination realizedLossesAcc) {
        this.realizedLossesAcc = realizedLossesAcc;
    }

    public GlCodeCombination getTaxAcc() {
        return taxAcc;
    }

    public void setTaxAcc(GlCodeCombination taxAcc) {
        this.taxAcc = taxAcc;
    }

    public GlCodeCombination getUnallocatedRevenueAcc() {
        return unallocatedRevenueAcc;
    }

    public void setUnallocatedRevenueAcc(GlCodeCombination unallocatedRevenueAcc) {
        this.unallocatedRevenueAcc = unallocatedRevenueAcc;
    }

    public int getCrossCurrencyRateType() {
        return crossCurrencyRateType;
    }

    public void setCrossCurrencyRateType(int crossCurrencyRateType) {
        this.crossCurrencyRateType = crossCurrencyRateType;
    }

    public GlCodeCombination getCrossCurrencyRoundingAcc() {
        return crossCurrencyRoundingAcc;
    }

    public void setCrossCurrencyRoundingAcc(GlCodeCombination crossCurrencyRoundingAcc) {
        this.crossCurrencyRoundingAcc = crossCurrencyRoundingAcc;
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
        if (!(object instanceof ReceivableModuleSetup)) {
            return false;
        }
        ReceivableModuleSetup other = (ReceivableModuleSetup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.modules.ReceivableModuleSetup[ id=" + id + " ]";
    }

    public GlCodeCombination getCashAcc() {
        return cashAcc;
    }

    public void setCashAcc(GlCodeCombination cashAcc) {
        this.cashAcc = cashAcc;
    }

    public GlCodeCombination getRevenueAcc() {
        return revenueAcc;
    }

    public void setRevenueAcc(GlCodeCombination revenueAcc) {
        this.revenueAcc = revenueAcc;
    }

}
