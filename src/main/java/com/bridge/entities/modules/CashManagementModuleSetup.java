/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.PrePersist;
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
@Table(name = "CASH_MANAGEMENT_MODULE_SETUP")
@NamedQueries({
    @NamedQuery(name = "CashManagementModuleSetup.findAll", query = "SELECT c FROM CashManagementModuleSetup c")})
public class CashManagementModuleSetup implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cashconfig")
    @TableGenerator(name = "cashconfig", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "CashConfig", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnit;

    @Size(max = 100)
    @Column(name = "STATUS")
    private String status;

    @OneToOne
    @JoinColumn(name = "CASH_ACC")
    private GlCodeCombination cashAcc;

    @Column(name = "CASH_AMOUNT")
    private BigDecimal cashAmount ;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @OneToOne
    @JoinColumn(name = "MISCELLANEOUS_EXPENSE_ACC")
    private GlCodeCombination miscellaneousExpenseAcc;

    @OneToOne
    @JoinColumn(name = "VARIOUS_EXPENSES_ACC")
    private GlCodeCombination variousExpensesAcc;

    @Column(name = "MISCELLANEOUS_EXPENSE_LIMIT")
    private BigDecimal miscellaneousExpenseLimit;      

    @Column(name = "MISCELLANEOUS_EXPENSE_AMOUNT")
    private BigDecimal miscellaneousExpenseAmount;      
        
    @Column(name = "CASH_INITIAL_AMOUNT")
    private BigDecimal cashInitialAmount; 
    
    @OneToOne
    @JoinColumn(name = "CASH_CURRENCY_ID")
    private FndCurrency cashCurrency;

    @OneToOne
    @JoinColumn(name = "MISCELLANEOUS_EXPENSE_CURRENCY")
    private FndCurrency miscellaneousExpenseCurrency;


    public CashManagementModuleSetup() {
    }

    public CashManagementModuleSetup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GlCodeCombination getCashAcc() {
        return cashAcc;
    }

    public void setCashAcc(GlCodeCombination cashAcc) {
        this.cashAcc = cashAcc;
    }



    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public GlCodeCombination getMiscellaneousExpenseAcc() {
        return miscellaneousExpenseAcc;
    }

    public void setMiscellaneousExpenseAcc(GlCodeCombination miscellaneousExpenseAcc) {
        this.miscellaneousExpenseAcc = miscellaneousExpenseAcc;
    }

    public GlCodeCombination getVariousExpensesAcc() {
        return variousExpensesAcc;
    }

    public void setVariousExpensesAcc(GlCodeCombination variousExpensesAcc) {
        this.variousExpensesAcc = variousExpensesAcc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final CashManagementModuleSetup other = (CashManagementModuleSetup) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public FndCurrency getMiscellaneousExpenseCurrency() {
        return miscellaneousExpenseCurrency;
    }

    public void setMiscellaneousExpenseCurrency(FndCurrency miscellaneousExpenseCurrency) {
        this.miscellaneousExpenseCurrency = miscellaneousExpenseCurrency;
    }

    
    public BigDecimal getMiscellaneousExpenseLimit() {
        return miscellaneousExpenseLimit;
    }

    public void setMiscellaneousExpenseLimit(BigDecimal miscellaneousExpenseLimit) {
        this.miscellaneousExpenseLimit = miscellaneousExpenseLimit;
    }

    public BigDecimal getMiscellaneousExpenseAmount() {
        return miscellaneousExpenseAmount;
    }

    public void setMiscellaneousExpenseAmount(BigDecimal miscellaneousExpenseAmount) {
        this.miscellaneousExpenseAmount = miscellaneousExpenseAmount;
    }

    public BigDecimal getCashInitialAmount() {
        return cashInitialAmount;
    }

    public void setCashInitialAmount(BigDecimal cashInitialAmount) {
        this.cashInitialAmount = cashInitialAmount;
    }

    public FndCurrency getCashCurrency() {
        return cashCurrency;
    }

    public void setCashCurrency(FndCurrency cashCurrency) {
        this.cashCurrency = cashCurrency;
    }


}