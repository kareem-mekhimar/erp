/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.security;

import com.bridge.enums.SystemPermissionDomain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "SYSTEM_PERMISSIONS")
@NamedQueries({
    @NamedQuery(name = "SystemPermission.findAll", query = "SELECT s FROM SystemPermission s")})
public class SystemPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Size(max = 255)
    @Column(name = "DOMAIN")
    private String domain;
    @Size(max = 255)
    @Column(name = "ACTION")
    private String action;
    @Size(max = 255)
    @Column(name = "LABEL")
    private String label;
    @Column(name = "APPLICATION_ID")
    private int applicationId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_CODE")
    private SystemPermissionDomain systemPermissionDomain;

    public SystemPermission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public SystemPermissionDomain getSystemPermissionDomain() {
        return systemPermissionDomain;
    }

    public void setSystemPermissionDomain(SystemPermissionDomain systemPermissionDomain) {
        this.systemPermissionDomain = systemPermissionDomain;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final SystemPermission other = (SystemPermission) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.security.SystemPermission[ id=" + id + " ]";
    }
    
}
