/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HZ_PARTIES_CATEGORIES")
@NamedQueries({
    @NamedQuery(name = "HzPartiesCategories.findAll", query = "SELECT h FROM HzPartiesCategories h")})
public class HzPartiesCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORY_ID")
    private BigDecimal categoryId;
    @Column(name = "PARTY_TYPE_ID")
    private BigInteger partyTypeId;
    @Size(max = 200)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "STATUS")
    private BigInteger status;

    public HzPartiesCategories() {
    }

    public HzPartiesCategories(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }

    public BigInteger getPartyTypeId() {
        return partyTypeId;
    }

    public void setPartyTypeId(BigInteger partyTypeId) {
        this.partyTypeId = partyTypeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HzPartiesCategories)) {
            return false;
        }
        HzPartiesCategories other = (HzPartiesCategories) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.HzPartiesCategories[ categoryId=" + categoryId + " ]";
    }
    
}
