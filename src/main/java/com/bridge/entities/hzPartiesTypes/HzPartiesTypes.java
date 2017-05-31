/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hzPartiesTypes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HZ_PARTIES_TYPES")
@NamedQueries({
    @NamedQuery(name = "HzPartiesTypes.findAll", query = "SELECT h FROM HzPartiesTypes h")})
public class HzPartiesTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "hzPartiesTypes")
    @TableGenerator(name = "hzPartiesTypes",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "hzPartiesTypes",valueColumnName = "CURRENT_VALUE")    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE_ID")
    private Integer typeId;
    
    @Size(max = 100)
    @Column(name = "TYPE_AR_NAME")
    private String typeArName;
    @Size(max = 100)
    @Column(name = "TYPE_EN_NAME")
    private String typeEnName;
    
    @Column(name = "TYPE_STATUS")
    private boolean typeStatus;

    public boolean isEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TYPE_CODE")
    private String typeCode;
    
    @Column(name = "ENABLED_FLAG_ID")
    private boolean  enabledFlagId;

    public HzPartiesTypes() {
    }

    public HzPartiesTypes(Integer typeId) {
        this.typeId = typeId;
    }

    public HzPartiesTypes(Integer typeId, String typeCode) {
        this.typeId = typeId;
        this.typeCode = typeCode;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeArName() {
        return typeArName;
    }

    public void setTypeArName(String typeArName) {
        this.typeArName = typeArName;
    }

    public String getTypeEnName() {
        return typeEnName;
    }

    public void setTypeEnName(String typeEnName) {
        this.typeEnName = typeEnName;
    }

    public boolean getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(boolean typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HzPartiesTypes)) {
            return false;
        }
        HzPartiesTypes other = (HzPartiesTypes) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.customerTypes.HzPartiesTypes[ typeId=" + typeId + " ]";
    }
    
}
