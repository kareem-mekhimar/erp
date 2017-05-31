/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.hr.Country;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "CL_CLIENT_SITES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientSite.findAll", query = "SELECT c FROM ClientSite c")})
public class ClientSite implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
               @GeneratedValue(strategy = GenerationType.TABLE,generator = "account")
    @TableGenerator(name = "account",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Account",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITE_ID")
    private Integer siteId;
    @Column(name = "SITE_TYPE")
    private Integer siteType;
    @Size(max = 100)
    @Column(name = "ALIAS_NAME")
    private String aliasName;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client clientId;
    @Size(max = 250)
    @Column(name = "SITE_NAME")
    private String siteName;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Size(max = 30)
    @Column(name = "SITE_NUMBER")
    private String siteNumber;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "REQUEST_ID")
    private Long requestId;
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
    @ManyToOne
    @JoinColumn(name = "COUNTRY_CODE",referencedColumnName = "COUNTRY_CODE")
    private Country countryCode;
    @Size(max = 30)
    @Column(name = "STATE_CODE")
    private String stateCode;
    @Column(name = "CITY_CODE")
    private String cityCode;
    @Size(max = 360)
    @Column(name = "ADDRESS")
    private String address;
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
    @Column(name = "IDENTIFYING_ADDRESS_FLAG")
    private Integer identifyingAddressFlag;
    @Column(name = "STATUS")
    private boolean status;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;
    @Size(max = 150)
    @Column(name = "CREATED_BY_MODULE")
    private String createdByModule;
    @Column(name = "APPLICATION_ID")
    private Integer applicationId;
    @Size(max = 30)
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

    @Column(name="BANK_ACCOUNT_ID")
    private Integer bankAccount;
    
    @OneToMany(mappedBy = "siteId", cascade = CascadeType.ALL)
    private List<ClientContact> contactList;
    
    @OneToOne(mappedBy = "siteId",cascade = CascadeType.ALL)
    private SupplierAcc supplierAcc;
    
    @OneToOne(mappedBy = "siteId",cascade = CascadeType.ALL)
    private CustomerAcc customerAcc;

    public ClientSite() {
    }

    public ClientSite(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
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

    public Integer getIdentifyingAddressFlag() {
        return identifyingAddressFlag;
    }

    public void setIdentifyingAddressFlag(Integer identifyingAddressFlag) {
        this.identifyingAddressFlag = identifyingAddressFlag;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getCreatedByModule() {
        return createdByModule;
    }

    public void setCreatedByModule(String createdByModule) {
        this.createdByModule = createdByModule;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
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

    public List<ClientContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<ClientContact> contactList) {
        this.contactList = contactList;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isShipFlag() {
        return shipFlag;
    }

    public boolean isInvoiceFlag() {
        return invoiceFlag;
    }

    public SupplierAcc getSupplierAcc() {
        return supplierAcc;
    }

    public void setSupplierAcc(SupplierAcc supplierAcc) {
        this.supplierAcc = supplierAcc;
    }

    public CustomerAcc getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(CustomerAcc customerAcc) {
        this.customerAcc = customerAcc;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteId != null ? siteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientSite)) {
            return false;
        }
        ClientSite other = (ClientSite) object;
        if ((this.siteId == null && other.siteId != null) || (this.siteId != null && !this.siteId.equals(other.siteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ClientAccount[ siteId=" + siteId + " ]";
    }

   

}
