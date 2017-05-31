/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hr;

import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "HR_LOCATIONS_ALL")
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT h FROM Location h")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
       @GeneratedValue(strategy = GenerationType.TABLE,generator = "loc")
    @TableGenerator(name = "loc",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Location",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @Size(max = 20)
    @Column(name = "LOCATION_CODE")
    private String locationCode;
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroupId;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Column(name = "SHIP_TO_SITE_FLAG")
    private Integer shipToSiteFlag;
    @Column(name = "RECEIVING_SITE_FLAG")
    private Integer receivingSiteFlag;
    @Column(name = "BILL_TO_SITE_FLAG")
    private Integer billToSiteFlag;
    @Column(name = "IN_ORGANIZATION_FLAG")
    private Integer inOrganizationFlag;
    @Column(name = "OFFICE_SITE_FLAG")
    private Integer officeSiteFlag;
    @Column(name = "DESIGNATED_RECEIVER_ID")
    private Integer designatedReceiverId;
    @Column(name = "INVENTORY_ORGANIZATION_ID")
    private Integer inventoryOrganizationId;
    @Size(max = 15)
    @Column(name = "TAX_NAME")
    private String taxName;
    @Column(name = "INACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    @Column(name = "STYLE")
    private Integer style;
    @Size(max = 60)
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Size(max = 60)
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;
    @Size(max = 60)
    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;
    @Size(max = 30)
    @Column(name = "TOWN_OR_CITY")
    private String townOrCity;
    @ManyToOne
    @JoinColumn(name = "COUNTRY",referencedColumnName = "COUNTRY_CODE")
    private Country country;
    @Size(max = 30)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 70)
    @Column(name = "REGION_1")
    private String region1;
    @Size(max = 70)
    @Column(name = "REGION_2")
    private String region2;
    @Size(max = 70)
    @Column(name = "REGION_3")
    private String region3;
    @Size(max = 60)
    @Column(name = "TELEPHONE_NUMBER_1")
    private String telephoneNumber1;
    @Size(max = 60)
    @Column(name = "TELEPHONE_NUMBER_2")
    private String telephoneNumber2;
    @Size(max = 60)
    @Column(name = "TELEPHONE_NUMBER_3")
    private String telephoneNumber3;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTERED_BY")
    private Integer enteredBy;
    @Column(name = "TP_HEADER_ID")
    private Integer tpHeaderId;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;
    @Column(name = "COUNTRY_ID")
    private Integer countryId;
    
    @Column(name = "TOWN_OR_CITY_ID")
    private Integer townOrCityId;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getTownOrCityId() {
        return townOrCityId;
    }

    public void setTownOrCityId(Integer townOrCityId) {
        this.townOrCityId = townOrCityId;
    }

    
    
    public Location() {
    }

    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    public Location(Integer locationId, Integer enteredBy) {
        this.locationId = locationId;
        this.enteredBy = enteredBy;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Integer getShipToSiteFlag() {
        return shipToSiteFlag;
    }

    public void setShipToSiteFlag(Integer shipToSiteFlag) {
        this.shipToSiteFlag = shipToSiteFlag;
    }

    public Integer getReceivingSiteFlag() {
        return receivingSiteFlag;
    }

    public void setReceivingSiteFlag(Integer receivingSiteFlag) {
        this.receivingSiteFlag = receivingSiteFlag;
    }

    public Integer getBillToSiteFlag() {
        return billToSiteFlag;
    }

    public void setBillToSiteFlag(Integer billToSiteFlag) {
        this.billToSiteFlag = billToSiteFlag;
    }

    public Integer getInOrganizationFlag() {
        return inOrganizationFlag;
    }

    public void setInOrganizationFlag(Integer inOrganizationFlag) {
        this.inOrganizationFlag = inOrganizationFlag;
    }

    public Integer getOfficeSiteFlag() {
        return officeSiteFlag;
    }

    public void setOfficeSiteFlag(Integer officeSiteFlag) {
        this.officeSiteFlag = officeSiteFlag;
    }

    public Integer getDesignatedReceiverId() {
        return designatedReceiverId;
    }

    public void setDesignatedReceiverId(Integer designatedReceiverId) {
        this.designatedReceiverId = designatedReceiverId;
    }

    public Integer getInventoryOrganizationId() {
        return inventoryOrganizationId;
    }

    public void setInventoryOrganizationId(Integer inventoryOrganizationId) {
        this.inventoryOrganizationId = inventoryOrganizationId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getRegion3() {
        return region3;
    }

    public void setRegion3(String region3) {
        this.region3 = region3;
    }

    public String getTelephoneNumber1() {
        return telephoneNumber1;
    }

    public void setTelephoneNumber1(String telephoneNumber1) {
        this.telephoneNumber1 = telephoneNumber1;
    }

    public String getTelephoneNumber2() {
        return telephoneNumber2;
    }

    public void setTelephoneNumber2(String telephoneNumber2) {
        this.telephoneNumber2 = telephoneNumber2;
    }

    public String getTelephoneNumber3() {
        return telephoneNumber3;
    }

    public void setTelephoneNumber3(String telephoneNumber3) {
        this.telephoneNumber3 = telephoneNumber3;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
    }

    public Integer getTpHeaderId() {
        return tpHeaderId;
    }

    public void setTpHeaderId(Integer tpHeaderId) {
        this.tpHeaderId = tpHeaderId;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }
    
    @PrePersist
    public void setData(){
        enteredBy=1;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.HrLocation[ locationId=" + locationId + " ]";
    }
    
}
