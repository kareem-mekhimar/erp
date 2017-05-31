/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities;

import java.io.Serializable;
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
 * @author Bridge
 */
@Entity
@Table(name = "LOOKUP_DETAILS")
@NamedQueries({
    @NamedQuery(name = "FndLookupDetail.findStatusForDomainObject",
            query = "SELECT f FROM FndLookupDetail f WHERE f.lookupMasterId = :statusId")})
public class FndLookupDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOOKUP_DETAIL_ID")
    private Integer lookupDetailId;
    @Size(max = 100)
    @Column(name = "LOOKUP_DETAIL_EN_NAME")
    private String lookupDetailEnName;
    @Size(max = 100)
    @Column(name = "LOOKUP_DETAIL_AR_NAME")
    private String lookupDetailArName;
    @Column(name = "STATUS")
    private Character status;
    @Size(max = 50)
    @Column(name = "LOOKUP_DETAIL_CODE")
    private String lookupDetailCode;
      
    @Column(name = "LOOKUP_MASTER_ID")
    private Integer lookupMasterId ;
    
    public FndLookupDetail() {
    }

    public FndLookupDetail(Integer lookupDetailId) {
        this.lookupDetailId = lookupDetailId;
    }

    public Integer getLookupDetailId() {
        return lookupDetailId;
    }

    public void setLookupDetailId(Integer lookupDetailId) {
        this.lookupDetailId = lookupDetailId;
    }

    public String getLookupDetailEnName() {
        return lookupDetailEnName;
    }

    public void setLookupDetailEnName(String lookupDetailEnName) {
        this.lookupDetailEnName = lookupDetailEnName;
    }

    public String getLookupDetailArName() {
        return lookupDetailArName;
    }

    public void setLookupDetailArName(String lookupDetailArName) {
        this.lookupDetailArName = lookupDetailArName;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getLookupDetailCode() {
        return lookupDetailCode;
    }

    public void setLookupDetailCode(String lookupDetailCode) {
        this.lookupDetailCode = lookupDetailCode;
    }

    public Integer getLookupMasterId() {
        return lookupMasterId;
    }

    public void setLookupMasterId(Integer lookupMasterId) {
        this.lookupMasterId = lookupMasterId;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lookupDetailId != null ? lookupDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FndLookupDetail)) {
            return false;
        }
        FndLookupDetail other = (FndLookupDetail) object;
        if ((this.lookupDetailId == null && other.lookupDetailId != null) || (this.lookupDetailId != null && !this.lookupDetailId.equals(other.lookupDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.FndLookupDetail[ lookupDetailId=" + lookupDetailId + " ]";
    }
    
}
