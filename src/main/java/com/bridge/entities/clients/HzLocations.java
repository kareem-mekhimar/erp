/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HZ_LOCATIONS")
@NamedQueries({
    @NamedQuery(name = "HzLocations.findAll", query = "SELECT h FROM HzLocations h")})
public class HzLocations implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_ID")
    private BigDecimal locationId;
    @Size(max = 64)
    @Column(name = "TIMEZONE_CODE")
    private String timezoneCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "REQUEST_ID")
    private Long requestId;
    @Size(max = 100)
    @Column(name = "JOB_DEFINITION_NAME")
    private String jobDefinitionName;
    @Size(max = 900)
    @Column(name = "JOB_DEFINITION_PACKAGE")
    private String jobDefinitionPackage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "ORIG_SYSTEM_REFERENCE")
    private String origSystemReference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 240)
    @Column(name = "ADDRESS1")
    private String address1;
    @Size(max = 240)
    @Column(name = "ADDRESS2")
    private String address2;
    @Size(max = 240)
    @Column(name = "ADDRESS3")
    private String address3;
    @Size(max = 240)
    @Column(name = "ADDRESS4")
    private String address4;
    @Size(max = 60)
    @Column(name = "CITY")
    private String city;
    @Size(max = 60)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 60)
    @Column(name = "STATE")
    private String state;
    @Size(max = 60)
    @Column(name = "PROVINCE")
    private String province;
    @Size(max = 60)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 30)
    @Column(name = "ADDRESS_STYLE")
    private String addressStyle;
    @Size(max = 560)
    @Column(name = "ADDRESS_LINES_PHONETIC")
    private String addressLinesPhonetic;
    @Size(max = 10)
    @Column(name = "POSTAL_PLUS4_CODE")
    private String postalPlus4Code;
    @Size(max = 50)
    @Column(name = "POSITION")
    private String position;
    @Size(max = 640)
    @Column(name = "LOCATION_DIRECTIONS")
    private String locationDirections;
    @Column(name = "ADDRESS_EFFECTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addressEffectiveDate;
    @Column(name = "ADDRESS_EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addressExpirationDate;
    @Size(max = 60)
    @Column(name = "CLLI_CODE")
    private String clliCode;
    @Size(max = 240)
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 1)
    @Column(name = "VALIDATED_FLAG")
    private String validatedFlag;
    @Size(max = 30)
    @Column(name = "SALES_TAX_GEOCODE")
    private String salesTaxGeocode;
    @Size(max = 1)
    @Column(name = "SALES_TAX_INSIDE_CITY_LIMITS")
    private String salesTaxInsideCityLimits;
    @Column(name = "FA_LOCATION_ID")
    private BigInteger faLocationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OBJECT_VERSION_NUMBER")
    private BigInteger objectVersionNumber;
    @Size(max = 30)
    @Column(name = "CREATED_BY_MODULE")
    private String createdByModule;
    @Column(name = "TIMEZONE_ID")
    private BigInteger timezoneId;
    @Size(max = 30)
    @Column(name = "GEOMETRY_STATUS_CODE")
    private String geometryStatusCode;
    @Size(max = 30)
    @Column(name = "ACTUAL_CONTENT_SOURCE")
    private String actualContentSource;
    @Size(max = 30)
    @Column(name = "VALIDATION_STATUS_CODE")
    private String validationStatusCode;
    @Column(name = "DATE_VALIDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidated;
    @Size(max = 1)
    @Column(name = "DO_NOT_VALIDATE_FLAG")
    private String doNotValidateFlag;
    @Size(max = 2000)
    @Column(name = "COMMENTS")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONFLICT_ID")
    private BigInteger conflictId;
    @Column(name = "USER_LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastUpdateDate;
    @Size(max = 30)
    @Column(name = "HOUSE_TYPE")
    private String houseType;
    @Column(name = "CPDRF_VER_SOR")
    private BigInteger cpdrfVerSor;
    @Column(name = "CPDRF_VER_PILLAR")
    private BigInteger cpdrfVerPillar;
    @Size(max = 15)
    @Column(name = "CPDRF_LAST_UPD")
    private String cpdrfLastUpd;
    @Size(max = 4)
    @Column(name = "LOCATION_LANGUAGE")
    private String locationLanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS_FLAG")
    private String statusFlag;
    @Size(max = 240)
    @Column(name = "BUILDING")
    private String building;
    @Size(max = 40)
    @Column(name = "FLOOR_NUMBER")
    private String floorNumber;
    @Size(max = 150)
    @Column(name = "ADDR_ELEMENT_ATTRIBUTE1")
    private String addrElementAttribute1;
    @Size(max = 150)
    @Column(name = "ADDR_ELEMENT_ATTRIBUTE2")
    private String addrElementAttribute2;
    @Size(max = 150)
    @Column(name = "ADDR_ELEMENT_ATTRIBUTE3")
    private String addrElementAttribute3;
    @Size(max = 150)
    @Column(name = "ADDR_ELEMENT_ATTRIBUTE4")
    private String addrElementAttribute4;
    @Size(max = 150)
    @Column(name = "ADDR_ELEMENT_ATTRIBUTE5")
    private String addrElementAttribute5;
    @Size(max = 1)
    @Column(name = "INTERNAL_FLAG")
    private String internalFlag;
    @Column(name = "LONGITUDE")
    private BigInteger longitude;
    @Column(name = "LATITUDE")
    private BigInteger latitude;

    public HzLocations() {
    }

    public HzLocations(BigDecimal locationId) {
        this.locationId = locationId;
    }

    public HzLocations(BigDecimal locationId, Date lastUpdateDate, Date creationDate, String origSystemReference, String country, BigInteger objectVersionNumber, BigInteger conflictId, String statusFlag) {
        this.locationId = locationId;
        this.lastUpdateDate = lastUpdateDate;
        this.creationDate = creationDate;
        this.origSystemReference = origSystemReference;
        this.country = country;
        this.objectVersionNumber = objectVersionNumber;
        this.conflictId = conflictId;
        this.statusFlag = statusFlag;
    }

    public BigDecimal getLocationId() {
        return locationId;
    }

    public void setLocationId(BigDecimal locationId) {
        this.locationId = locationId;
    }

    public String getTimezoneCode() {
        return timezoneCode;
    }

    public void setTimezoneCode(String timezoneCode) {
        this.timezoneCode = timezoneCode;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getJobDefinitionName() {
        return jobDefinitionName;
    }

    public void setJobDefinitionName(String jobDefinitionName) {
        this.jobDefinitionName = jobDefinitionName;
    }

    public String getJobDefinitionPackage() {
        return jobDefinitionPackage;
    }

    public void setJobDefinitionPackage(String jobDefinitionPackage) {
        this.jobDefinitionPackage = jobDefinitionPackage;
    }

    public String getOrigSystemReference() {
        return origSystemReference;
    }

    public void setOrigSystemReference(String origSystemReference) {
        this.origSystemReference = origSystemReference;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressStyle() {
        return addressStyle;
    }

    public void setAddressStyle(String addressStyle) {
        this.addressStyle = addressStyle;
    }

    public String getAddressLinesPhonetic() {
        return addressLinesPhonetic;
    }

    public void setAddressLinesPhonetic(String addressLinesPhonetic) {
        this.addressLinesPhonetic = addressLinesPhonetic;
    }

    public String getPostalPlus4Code() {
        return postalPlus4Code;
    }

    public void setPostalPlus4Code(String postalPlus4Code) {
        this.postalPlus4Code = postalPlus4Code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocationDirections() {
        return locationDirections;
    }

    public void setLocationDirections(String locationDirections) {
        this.locationDirections = locationDirections;
    }

    public Date getAddressEffectiveDate() {
        return addressEffectiveDate;
    }

    public void setAddressEffectiveDate(Date addressEffectiveDate) {
        this.addressEffectiveDate = addressEffectiveDate;
    }

    public Date getAddressExpirationDate() {
        return addressExpirationDate;
    }

    public void setAddressExpirationDate(Date addressExpirationDate) {
        this.addressExpirationDate = addressExpirationDate;
    }

    public String getClliCode() {
        return clliCode;
    }

    public void setClliCode(String clliCode) {
        this.clliCode = clliCode;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidatedFlag() {
        return validatedFlag;
    }

    public void setValidatedFlag(String validatedFlag) {
        this.validatedFlag = validatedFlag;
    }

    public String getSalesTaxGeocode() {
        return salesTaxGeocode;
    }

    public void setSalesTaxGeocode(String salesTaxGeocode) {
        this.salesTaxGeocode = salesTaxGeocode;
    }

    public String getSalesTaxInsideCityLimits() {
        return salesTaxInsideCityLimits;
    }

    public void setSalesTaxInsideCityLimits(String salesTaxInsideCityLimits) {
        this.salesTaxInsideCityLimits = salesTaxInsideCityLimits;
    }

    public BigInteger getFaLocationId() {
        return faLocationId;
    }

    public void setFaLocationId(BigInteger faLocationId) {
        this.faLocationId = faLocationId;
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

    public BigInteger getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(BigInteger timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getGeometryStatusCode() {
        return geometryStatusCode;
    }

    public void setGeometryStatusCode(String geometryStatusCode) {
        this.geometryStatusCode = geometryStatusCode;
    }

    public String getActualContentSource() {
        return actualContentSource;
    }

    public void setActualContentSource(String actualContentSource) {
        this.actualContentSource = actualContentSource;
    }

    public String getValidationStatusCode() {
        return validationStatusCode;
    }

    public void setValidationStatusCode(String validationStatusCode) {
        this.validationStatusCode = validationStatusCode;
    }

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getDoNotValidateFlag() {
        return doNotValidateFlag;
    }

    public void setDoNotValidateFlag(String doNotValidateFlag) {
        this.doNotValidateFlag = doNotValidateFlag;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigInteger getConflictId() {
        return conflictId;
    }

    public void setConflictId(BigInteger conflictId) {
        this.conflictId = conflictId;
    }

    public Date getUserLastUpdateDate() {
        return userLastUpdateDate;
    }

    public void setUserLastUpdateDate(Date userLastUpdateDate) {
        this.userLastUpdateDate = userLastUpdateDate;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public BigInteger getCpdrfVerSor() {
        return cpdrfVerSor;
    }

    public void setCpdrfVerSor(BigInteger cpdrfVerSor) {
        this.cpdrfVerSor = cpdrfVerSor;
    }

    public BigInteger getCpdrfVerPillar() {
        return cpdrfVerPillar;
    }

    public void setCpdrfVerPillar(BigInteger cpdrfVerPillar) {
        this.cpdrfVerPillar = cpdrfVerPillar;
    }

    public String getCpdrfLastUpd() {
        return cpdrfLastUpd;
    }

    public void setCpdrfLastUpd(String cpdrfLastUpd) {
        this.cpdrfLastUpd = cpdrfLastUpd;
    }

    public String getLocationLanguage() {
        return locationLanguage;
    }

    public void setLocationLanguage(String locationLanguage) {
        this.locationLanguage = locationLanguage;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getAddrElementAttribute1() {
        return addrElementAttribute1;
    }

    public void setAddrElementAttribute1(String addrElementAttribute1) {
        this.addrElementAttribute1 = addrElementAttribute1;
    }

    public String getAddrElementAttribute2() {
        return addrElementAttribute2;
    }

    public void setAddrElementAttribute2(String addrElementAttribute2) {
        this.addrElementAttribute2 = addrElementAttribute2;
    }

    public String getAddrElementAttribute3() {
        return addrElementAttribute3;
    }

    public void setAddrElementAttribute3(String addrElementAttribute3) {
        this.addrElementAttribute3 = addrElementAttribute3;
    }

    public String getAddrElementAttribute4() {
        return addrElementAttribute4;
    }

    public void setAddrElementAttribute4(String addrElementAttribute4) {
        this.addrElementAttribute4 = addrElementAttribute4;
    }

    public String getAddrElementAttribute5() {
        return addrElementAttribute5;
    }

    public void setAddrElementAttribute5(String addrElementAttribute5) {
        this.addrElementAttribute5 = addrElementAttribute5;
    }

    public String getInternalFlag() {
        return internalFlag;
    }

    public void setInternalFlag(String internalFlag) {
        this.internalFlag = internalFlag;
    }

    public BigInteger getLongitude() {
        return longitude;
    }

    public void setLongitude(BigInteger longitude) {
        this.longitude = longitude;
    }

    public BigInteger getLatitude() {
        return latitude;
    }

    public void setLatitude(BigInteger latitude) {
        this.latitude = latitude;
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
        if (!(object instanceof HzLocations)) {
            return false;
        }
        HzLocations other = (HzLocations) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.HzLocations[ locationId=" + locationId + " ]";
    }
    
}
