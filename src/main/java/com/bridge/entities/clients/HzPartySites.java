/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HZ_PARTY_SITES")

public class HzPartySites implements Serializable {

    private static final Integer serialVersionUID = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen")
    @TableGenerator(name = "gen", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "HzParty", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARTY_SITE_ID")
    private Integer partySiteId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PARTY_SITE_NUMBER")
    private String partySiteNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "WH_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whUpdateDate;
    @Size(max = 240)
    @Column(name = "ORIG_SYSTEM_REFERENCE")
    private String origSystemReference;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Size(max = 30)
    @Column(name = "REGION")
    private String region;
    @Size(max = 60)
    @Column(name = "MAILSTOP")
    private String mailstop;
    @Size(max = 255)
    @Column(name = "CUSTOMER_KEY_OSM")
    private String customerKeyOsm;
    @Size(max = 255)
    @Column(name = "PHONE_KEY_OSM")
    private String phoneKeyOsm;
    @Size(max = 255)
    @Column(name = "CONTACT_KEY_OSM")
    private String contactKeyOsm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDENTIFYING_ADDRESS_FLAG")
    private boolean identifyingAddressFlag;
    @Size(max = 30)
    @Column(name = "LANGUAGE")
    private String language;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private boolean status;
    @Size(max = 240)
    @Column(name = "PARTY_SITE_NAME")
    private String partySiteName;
    @Size(max = 360)
    @Column(name = "ADDRESSEE")
    private String addressee;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private BigInteger objectVersionNumber;
    @Size(max = 150)
    @Column(name = "CREATED_BY_MODULE")
    private String createdByModule;
    @Column(name = "APPLICATION_ID")
    private BigInteger applicationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACTUAL_CONTENT_SOURCE")
    private String actualContentSource;
    @Size(max = 40)
    @Column(name = "GLOBAL_LOCATION_NUMBER")
    private String globalLocationNumber;
    @Size(max = 30)
    @Column(name = "DUNS_NUMBER_C")
    private String dunsNumberC;
    @Column(name = "SHIP_FLAG")
    private boolean shipFlag;
    @Column(name = "INVOICE_FLAG")
    private boolean invoiceFlag;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PARTY_ID")
    private HzParties hzParty;

    public HzParties getParty() {
        return hzParty;
    }

    public void setParty(HzParties hsParty) {
        this.hzParty = hsParty;
    }
    
    public HzPartySites() {
    }

    public HzPartySites(Integer partySiteId) {
        this.partySiteId = partySiteId;
    }

    public Integer getPartySiteId() {
        return partySiteId;
    }

    public void setPartySiteId(Integer partySiteId) {
        this.partySiteId = partySiteId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPartySiteNumber() {
        return partySiteNumber;
    }

    public void setPartySiteNumber(String partySiteNumber) {
        this.partySiteNumber = partySiteNumber;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(Integer programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Date getWhUpdateDate() {
        return whUpdateDate;
    }

    public void setWhUpdateDate(Date whUpdateDate) {
        this.whUpdateDate = whUpdateDate;
    }

    public String getOrigSystemReference() {
        return origSystemReference;
    }

    public void setOrigSystemReference(String origSystemReference) {
        this.origSystemReference = origSystemReference;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMailstop() {
        return mailstop;
    }

    public void setMailstop(String mailstop) {
        this.mailstop = mailstop;
    }

    public String getCustomerKeyOsm() {
        return customerKeyOsm;
    }

    public void setCustomerKeyOsm(String customerKeyOsm) {
        this.customerKeyOsm = customerKeyOsm;
    }

    public String getPhoneKeyOsm() {
        return phoneKeyOsm;
    }

    public void setPhoneKeyOsm(String phoneKeyOsm) {
        this.phoneKeyOsm = phoneKeyOsm;
    }

    public String getContactKeyOsm() {
        return contactKeyOsm;
    }

    public void setContactKeyOsm(String contactKeyOsm) {
        this.contactKeyOsm = contactKeyOsm;
    }

    public boolean getIdentifyingAddressFlag() {
        return identifyingAddressFlag;
    }

    public void setIdentifyingAddressFlag(boolean identifyingAddressFlag) {
        this.identifyingAddressFlag = identifyingAddressFlag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPartySiteName() {
        return partySiteName;
    }

    public void setPartySiteName(String partySiteName) {
        this.partySiteName = partySiteName;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public BigInteger getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(BigInteger objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getCreatedByModule() {
        return createdByModule;
    }

    public void setCreatedByModule(String createdByModule) {
        this.createdByModule = createdByModule;
    }

    public BigInteger getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(BigInteger applicationId) {
        this.applicationId = applicationId;
    }

    public String getActualContentSource() {
        return actualContentSource;
    }

    public void setActualContentSource(String actualContentSource) {
        this.actualContentSource = actualContentSource;
    }

    public String getGlobalLocationNumber() {
        return globalLocationNumber;
    }

    public void setGlobalLocationNumber(String globalLocationNumber) {
        this.globalLocationNumber = globalLocationNumber;
    }

    public String getDunsNumberC() {
        return dunsNumberC;
    }

    public void setDunsNumberC(String dunsNumberC) {
        this.dunsNumberC = dunsNumberC;
    }

    public boolean getShipFlag() {
        return shipFlag;
    }

    public void setShipFlag(boolean shipFlag) {
        this.shipFlag = shipFlag;
    }

    public boolean getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(boolean invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partySiteId != null ? partySiteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HzPartySites)) {
            return false;
        }
        HzPartySites other = (HzPartySites) object;
        if ((this.partySiteId == null && other.partySiteId != null) || (this.partySiteId != null && !this.partySiteId.equals(other.partySiteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.HzPartySites[ partySiteId=" + partySiteId + " ]";
    }
    
}
