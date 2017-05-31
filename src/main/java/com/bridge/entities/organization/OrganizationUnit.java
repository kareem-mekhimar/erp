/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.organization;

import com.bridge.entities.bank.BankAccount;
import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.hr.Country;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.po.ErpSystemClient;
import com.bridge.enums.OrganizationUnitType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "ORGANIZATION_UNITS")
@NamedQueries({
    @NamedQuery(name = "OrganizationUnit.findAll", query = "SELECT o FROM OrganizationUnit o")})
public class OrganizationUnit implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "org")
    @TableGenerator(name = "org", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "OrgUnit", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORG_UNIT_ID")
    private Integer  orgUnitId;
    @Basic(optional = false)

    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    @Column(name = "ORG_UNIT_NAME")
    private String orgUnitName;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;
    @Size(max = 80)
    @Column(name = "INTERNAL_ADDRESS_LINE")
    private String internalAddressLine;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Size(max = 10)
    @Column(name = "ORG_CODE")
    private String orgCode;
 
    @Column(name = "ORG_UNIT_TYPE_ID")
    private OrganizationUnitType unitType ;    
    @Column(name = "ENABLED_FLAG_ID")
    private boolean  enabledFlagId;    
   
    @JoinColumn(name = "ERP_SYSTEM_CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ErpSystemClient erpSystemClientId;    
    
    @OneToMany(mappedBy = "parentUnitId")
    private List<OrganizationUnit> organizationUnitList;   

    @Column(name = "PARENT_UNIT_ID")
    private Integer parentUnitId;
    @Column(name = "BUSINESS_GROUP_ID")
    private Long businessGroupId;
    @Size(max = 30)
    @Column(name = "ORG_UNIT_TYPE")
    private String orgUnitType;
    @Column(name = "ENABLED_FLAG")
    private Character enabledFlag;
    
    @Column(name="GL_ID")
    private Integer glId;
    
    @OneToMany(mappedBy = "organization")
    private Collection<CoaStructure> coaStructureCollection;
    
    @OneToMany(mappedBy = "operatingUnit")
    private List<MainInventory> mainInventoryMasterList;
    
    @OneToMany(mappedBy = "orgUnitId")
    private List<BankAccount> bankAccounts;
   
    @ManyToOne
    @JoinColumn(name = "COUNTRY",referencedColumnName = "COUNTRY_CODE")
    private Country country;
    
    @Column(name = "COUNTRY_ID")
    private Integer countryId;
    @Column(name = "TOWN_OR_CITY_ID")
    private Integer townOrCityId;
    @Size(max = 30)
    @Column(name = "TOWN_OR_CITY")
    private String townOrCity;
    @Size(max = 30)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    
    @Column(name = "REGION")
    private String region;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;

    @Column(name = "INVENTORY_ORGANIZATION_FLAG")
    private boolean  inventoryOrganizationFlag; 
    
  

    public OrganizationUnit() {
    }

    public OrganizationUnit(Integer  orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public OrganizationUnit(Integer  orgUnitId, Date dateFrom, String orgUnitName) {
        this.orgUnitId = orgUnitId;
        this.dateFrom = dateFrom;
        this.orgUnitName = orgUnitName;
    }

    public Integer  getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Integer  orgUnitId) {
        this.orgUnitId = orgUnitId;
    }


    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getInternalAddressLine() {
        return internalAddressLine;
    }

    public void setInternalAddressLine(String internalAddressLine) {
        this.internalAddressLine = internalAddressLine;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setComments(String description) {
        this.description = description;
    }

    public OrganizationUnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(OrganizationUnitType unitType) {
        this.unitType = unitType;
    }

    public boolean isEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }


    public ErpSystemClient getErpSystemClientId() {
        return erpSystemClientId;
    }

    public void setErpSystemClientId(ErpSystemClient erpSystemClientId) {
        this.erpSystemClientId = erpSystemClientId;
    }

    public List<OrganizationUnit> getOrganizationUnitList() {
        return organizationUnitList;
    }

    public void setOrganizationUnitList(List<OrganizationUnit> organizationUnitList) {
        this.organizationUnitList = organizationUnitList;
    }

    public Integer getParentUnitId() {
        return parentUnitId;
    }

    public void setParentUnitId(Integer parentUnitId) {
        this.parentUnitId = parentUnitId;
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgUnitId != null ? orgUnitId.hashCode() : 0);
        return hash;
    }

     
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getTownOrCityId() {
        return townOrCityId;
    }

    public void setTownOrCityId(Integer townOrCityId) {
        this.townOrCityId = townOrCityId;
    }
    
    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }
    
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationUnit)) {
            return false;
        }
        OrganizationUnit other = (OrganizationUnit) object;
        if ((this.orgUnitId == null && other.orgUnitId != null) || (this.orgUnitId != null && !this.orgUnitId.equals(other.orgUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.OrganizationUnit[ organizationId=" + orgUnitId + " ]";
    }

    public Long getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(Long businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getOrgUnitType() {
        return orgUnitType;
    }

    public void setOrgUnitType(String orgUnitType) {
        this.orgUnitType = orgUnitType;
    }

    public Character getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Character enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Collection<CoaStructure> getCoaStructureCollection() {
        return coaStructureCollection;
    }

    public void setCoaStructureCollection(Collection<CoaStructure> coaStructureCollection) {
        this.coaStructureCollection = coaStructureCollection;
    }

    public List<MainInventory> getMainInventoryMasterList() {
        return mainInventoryMasterList;
    }

    public void setMainInventoryMasterList(List<MainInventory> mainInventoryMasterList) {
        this.mainInventoryMasterList = mainInventoryMasterList;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public boolean isInventoryOrganizationFlag() {
        return inventoryOrganizationFlag;
    }

    public void setInventoryOrganizationFlag(boolean inventoryOrganizationFlag) {
        this.inventoryOrganizationFlag = inventoryOrganizationFlag;
    }



    
    
    
}
