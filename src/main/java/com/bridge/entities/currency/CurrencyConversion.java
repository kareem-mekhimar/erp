/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "CURRENCY_DAILY_CONVERSION")
@NamedQueries({
    @NamedQuery(name = "CurrencyConversion.findAll", query = "SELECT c FROM CurrencyConversion c")})
public class CurrencyConversion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
      @GeneratedValue(strategy = GenerationType.TABLE,generator = "currconv")
    @TableGenerator(name = "currconv",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "currConv",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayDate;
    @OneToOne
    @JoinColumn(name = "FROM_CURRENCY_ID")
    private FndCurrency fromCurrency;
    @OneToOne
    @JoinColumn(name = "TO_CURRENCY_ID")
    private FndCurrency toCurrency;
    @Column(name = "CONVERSION_RATE")
    private BigDecimal conversionRate;
    @Column(name = "GL_ID")
    private Integer glId;
    @Column(name = "GL_DEFAULT_CURRENCY")
    private boolean defaultCurrency;

    public CurrencyConversion() {
    }

    public CurrencyConversion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public FndCurrency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(FndCurrency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public FndCurrency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(FndCurrency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
    }

    public boolean isDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(boolean defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
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
        if (!(object instanceof CurrencyConversion)) {
            return false;
        }
        CurrencyConversion other = (CurrencyConversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.currency.CurrencyDailyConversion[ id=" + id + " ]";
    }
    
}
