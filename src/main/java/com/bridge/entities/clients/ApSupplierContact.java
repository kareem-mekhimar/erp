/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "AP_SUPPLIER_CONTACTS")
@NamedQueries({
    @NamedQuery(name = "ApSupplierContact.findAll", query = "SELECT a FROM ApSupplierContact a")})
public class ApSupplierContact implements Serializable {



    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
               @GeneratedValue(strategy = GenerationType.TABLE,generator = "contact")
    @TableGenerator(name = "contact",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "SupContact",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENDOR_CONTACT_ID")
    private Integer vendorContactId;
    @Size(max = 200)
    @Column(name = "VENDOR_CONTACT_NAME")
    private String vendorContactName;
    @Column(name = "VENDOR_ID")
    private Integer vendorId;
    @Size(max = 50)
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Size(max = 250)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "DISABLED_FLAG")
    private boolean disabledFlag;
    @ManyToOne
    @JoinColumn(name = "VENDOR_SITE_ID" ,referencedColumnName = "VENDOR_SITE_ID")
    private ApSupplierSite site ;
    
    
    
    
    
    public ApSupplierContact() {
    }

    public ApSupplierContact(Integer vendorContactId) {
        this.vendorContactId = vendorContactId;
    }

    public Integer getVendorContactId() {
        return vendorContactId;
    }

    public void setVendorContactId(Integer vendorContactId) {
        this.vendorContactId = vendorContactId;
    }

    public String getVendorContactName() {
        return vendorContactName;
    }

    public void setVendorContactName(String vendorContactName) {
        this.vendorContactName = vendorContactName;
    }

    public ApSupplierSite getSite() {
        return site;
    }

    public void setSite(ApSupplierSite site) {
        this.site = site;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorContactId != null ? vendorContactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApSupplierContact)) {
            return false;
        }
        ApSupplierContact other = (ApSupplierContact) object;
        if ((this.vendorContactId == null && other.vendorContactId != null) || (this.vendorContactId != null && !this.vendorContactId.equals(other.vendorContactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ApSupplierContact[ vendorContactId=" + vendorContactId + " ]";
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(boolean disabledFlag) {
        this.disabledFlag = disabledFlag;
    }
    
}
