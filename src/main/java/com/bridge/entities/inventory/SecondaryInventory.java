/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import com.bridge.entities.hr.Country;
import com.bridge.entities.hr.City;
import com.bridge.enums.InventoryType;
import com.bridge.enums.LocatorControlType;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kareem
 */
@Entity
@Table(name = "MTL_SECONDARY_INVENTORIES")
@NamedQueries({
    @NamedQuery(name = "SecondaryInventory.findAll", query = "SELECT s FROM SecondaryInventory s")})

public class SecondaryInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "subInv")
    @TableGenerator(name = "subInv", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
            pkColumnName = "SEQUENCE_NAME", pkColumnValue = "secInv", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECONDARY_INVENTORY_ID")
    private Integer secondaryInventoryId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SECONDARY_INVENTORY_NAME")
    private String secondaryInventoryName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
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
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DISABLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disableDate;
    @Column(name = "LOCATOR_TYPE")
    private LocatorControlType locatorType;
    @Column(name = "PICKING_ORDER")
    private Integer pickingOrder;
    @Column(name = "MATERIAL_ACCOUNT")
    private Integer materialAccount;
    @Column(name = "MATERIAL_OVERHEAD_ACCOUNT")
    private Integer materialOverheadAccount;
    @Column(name = "RESOURCE_ACCOUNT")
    private Integer resourceAccount;
    @Column(name = "OVERHEAD_ACCOUNT")
    private Integer overheadAccount;
    @Column(name = "OUTSIDE_PROCESSING_ACCOUNT")
    private Integer outsideProcessingAccount;
    @Column(name = "EXPENSE_ACCOUNT")
    private Integer expenseAccount;
    @Column(name = "ENCUMBRANCE_ACCOUNT")
    private Integer encumbranceAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY_TRACKED")
    private boolean quantityTracked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSET_INVENTORY")
    private boolean assetInventory;

    @Size(max = 10)
    @Column(name = "ORGANIZATION_CODE")
    private String organizationCode;

    @Column(name = "LOCATION_ID")
    private Integer locationId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @OneToOne
    @JoinColumn(name = "MAIN_INV_ID", referencedColumnName = "ORGANIZATION_ID")
    private MainInventory mainInv;

    @Column(name = "PICKING_UOM_ID")
    private Integer pickingUomId;

    @Column(name = "STATUS_ID")
    private boolean statusId;
    @Column(name = "SUBINVENTORY_TYPE_ID")
    private InventoryType subinventoryType;
    @Column(name = "INCLUDE_IN_ATP")
    private boolean includeInAtp;
    @Column(name = "ALLOW_RESERVATION")
    private boolean allowReservation;
    @Column(name = "NETTABLE_FLAG_ID")
    private boolean nettableFlagId;
    @Column(name = "DEPRECIABLE_INVENTORY")
    private boolean depreciableInventory;
    @Column(name = "LOCATOR_STATUS_ID")
    private boolean locatorStatusId;
    @Column(name = "LPN_CONTROLLED_FLAG_ID")
    private boolean lpnControlled;
    @Column(name = "CARTONIZATION_FLAG_ID")
    private boolean cartonization;
    @Column(name = "BULK_PICKED_FLAG_ID")
    private boolean bulkPick;
    @Column(name = "OPPORTUNISTIC_CYCLE_COUNT_FLAG")
    private boolean opportunisticCycleCount;
    @Column(name = "GL_ID")
    private Integer glId;
    @Column(name = "STORAGE_FLAG")
    private boolean storageFlag;
    @Column(name = "RECEIVING_FLAG")
    private boolean receivingFlag;
    @Column(name = "SHIPPING_FLAG")
    private boolean shippingFlag;

    // TRANSISENT ATTRIBUTES
    @Transient
    private String materialAccountName;

    @Transient
    private String materialOverheadAccountName;

    @Transient
    private String resourceAccountName;

    @Transient
    private String overheadAccountName;

    @Transient
    private String outsideProcessingAccountName;

    @Transient
    private String expenseAccountName;

    @Transient
    private String encumbranceAccountName;

    @Transient
    private boolean oldShipping;

    public SecondaryInventory() {
    }

    public SecondaryInventory(Integer secondaryInventoryId) {
        this.secondaryInventoryId = secondaryInventoryId;
    }

    public SecondaryInventory(Integer secondaryInventoryId, String secondaryInventoryName, Date lastUpdateDate, Integer lastUpdatedBy, Date creationDate, Integer createdBy, String description, Date disableDate, LocatorControlType locatorType, Integer pickingOrder, Integer materialAccount, Integer materialOverheadAccount, Integer resourceAccount, Integer overheadAccount, Integer outsideProcessingAccount, Integer expenseAccount, Integer encumbranceAccount, boolean quantityTracked, boolean assetInventory, Integer locationId, MainInventory mainInv, Integer pickingUomId, boolean statusId, InventoryType subinventoryType, boolean includeInAtp, boolean allowReservation, boolean nettableFlagId, boolean depreciableInventory, boolean locatorStatusId, boolean lpnControlled, boolean cartonization, boolean bulkPick, boolean opportunisticCycleCount, String materialAccountName, String materialOverheadAccountName, String resourceAccountName, String overheadAccountName, String outsideProcessingAccountName, String expenseAccountName, String encumbranceAccountName) {
        this.secondaryInventoryId = secondaryInventoryId;
        this.secondaryInventoryName = secondaryInventoryName;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.description = description;
        this.disableDate = disableDate;
        this.locatorType = locatorType;
        this.pickingOrder = pickingOrder;
        this.materialAccount = materialAccount;
        this.materialOverheadAccount = materialOverheadAccount;
        this.resourceAccount = resourceAccount;
        this.overheadAccount = overheadAccount;
        this.outsideProcessingAccount = outsideProcessingAccount;
        this.expenseAccount = expenseAccount;
        this.encumbranceAccount = encumbranceAccount;
        this.quantityTracked = quantityTracked;
        this.assetInventory = assetInventory;
        this.locationId = locationId;
        this.mainInv = mainInv;
        this.pickingUomId = pickingUomId;
        this.statusId = statusId;
        this.subinventoryType = subinventoryType;
        this.includeInAtp = includeInAtp;
        this.allowReservation = allowReservation;
        this.nettableFlagId = nettableFlagId;
        this.depreciableInventory = depreciableInventory;
        this.locatorStatusId = locatorStatusId;
        this.lpnControlled = lpnControlled;
        this.cartonization = cartonization;
        this.bulkPick = bulkPick;
        this.opportunisticCycleCount = opportunisticCycleCount;
        this.materialAccountName = materialAccountName;
        this.materialOverheadAccountName = materialOverheadAccountName;
        this.resourceAccountName = resourceAccountName;
        this.overheadAccountName = overheadAccountName;
        this.outsideProcessingAccountName = outsideProcessingAccountName;
        this.expenseAccountName = expenseAccountName;
        this.encumbranceAccountName = encumbranceAccountName;
    }

    public SecondaryInventory(String m) {
        this.materialAccountName = m;
    }

    public String getSecondaryInventoryName() {
        return secondaryInventoryName;
    }

    public void setSecondaryInventoryName(String secondaryInventoryName) {
        this.secondaryInventoryName = secondaryInventoryName;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Integer getPickingUomId() {
        return pickingUomId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public LocatorControlType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(LocatorControlType locatorType) {
        this.locatorType = locatorType;
    }

    public Integer getPickingOrder() {
        return pickingOrder;
    }

    public void setPickingOrder(Integer pickingOrder) {
        this.pickingOrder = pickingOrder;
    }

    public Integer getMaterialAccount() {
        return materialAccount;
    }

    public void setMaterialAccount(Integer materialAccount) {
        this.materialAccount = materialAccount;
    }

    public Integer getMaterialOverheadAccount() {
        return materialOverheadAccount;
    }

    public void setMaterialOverheadAccount(Integer materialOverheadAccount) {
        this.materialOverheadAccount = materialOverheadAccount;
    }

    public Integer getResourceAccount() {
        return resourceAccount;
    }

    public void setResourceAccount(Integer resourceAccount) {
        this.resourceAccount = resourceAccount;
    }

    public Integer getOverheadAccount() {
        return overheadAccount;
    }

    public void setOverheadAccount(Integer overheadAccount) {
        this.overheadAccount = overheadAccount;
    }

    public Integer getOutsideProcessingAccount() {
        return outsideProcessingAccount;
    }

    public void setOutsideProcessingAccount(Integer outsideProcessingAccount) {
        this.outsideProcessingAccount = outsideProcessingAccount;
    }

    public Integer getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(Integer expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    public Integer getEncumbranceAccount() {
        return encumbranceAccount;
    }

    public void setEncumbranceAccount(Integer encumbranceAccount) {
        this.encumbranceAccount = encumbranceAccount;
    }

    public boolean getQuantityTracked() {
        return quantityTracked;
    }

    public void setQuantityTracked(boolean quantityTracked) {
        this.quantityTracked = quantityTracked;
    }

    public boolean getAssetInventory() {
        return assetInventory;
    }

    public void setAssetInventory(boolean assetInventory) {
        this.assetInventory = assetInventory;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void setPickingUomId(Integer pickingUomId) {
        this.pickingUomId = pickingUomId;
    }

    public boolean getStatusId() {
        return statusId;
    }

    public void setStatusId(boolean statusId) {
        this.statusId = statusId;
    }

    public InventoryType getSubinventoryType() {
        return subinventoryType;
    }

    public void setSubinventoryType(InventoryType subinventoryType) {
        this.subinventoryType = subinventoryType;
    }

    public Integer getSecondaryInventoryId() {
        return secondaryInventoryId;
    }

    public void setSecondaryInventoryId(Integer secondaryInventoryId) {
        this.secondaryInventoryId = secondaryInventoryId;
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
    }

    public MainInventory getMainInv() {
        return mainInv;
    }

    public void setMainInv(MainInventory mainInv) {
        this.mainInv = mainInv;
    }

    public boolean getOldShipping() {
        return oldShipping;
    }

    public void setOldShipping(boolean oldShipping) {
        this.oldShipping = oldShipping;
    }

    public boolean getIncludeInAtp() {
        return includeInAtp;
    }

    public void setIncludeInAtp(boolean includeInAtp) {
        this.includeInAtp = includeInAtp;
    }

    public boolean getAllowReservation() {
        return allowReservation;
    }

    public void setAllowReservation(boolean allowReservation) {
        this.allowReservation = allowReservation;
    }

    public boolean getNettableFlagId() {
        return nettableFlagId;
    }

    public void setNettableFlagId(boolean nettableFlagId) {
        this.nettableFlagId = nettableFlagId;
    }

    public boolean getDepreciableInventory() {
        return depreciableInventory;
    }

    public void setDepreciableInventory(boolean depreciableInventory) {
        this.depreciableInventory = depreciableInventory;
    }

    public boolean getLocatorStatusId() {
        return locatorStatusId;
    }

    public void setLocatorStatusId(boolean locatorStatusId) {
        this.locatorStatusId = locatorStatusId;
    }

    public boolean getLpnControlled() {
        return lpnControlled;
    }

    public void setLpnControlled(boolean lpnControlled) {
        this.lpnControlled = lpnControlled;
    }

    public boolean getCartonization() {
        return cartonization;
    }

    public void setCartonization(boolean cartonization) {
        this.cartonization = cartonization;
    }

    public boolean getBulkPick() {
        return bulkPick;
    }

    public void setBulkPick(boolean bulkPick) {
        this.bulkPick = bulkPick;
    }

    public boolean getOpportunisticCycleCount() {
        return opportunisticCycleCount;
    }

    public void setOpportunisticCycleCount(boolean opportunisticCycleCount) {
        this.opportunisticCycleCount = opportunisticCycleCount;
    }

    public boolean getStorageFlag() {
        return storageFlag;
    }

    public void setStorageFlag(boolean storageFlag) {
        this.storageFlag = storageFlag;
    }

    public boolean getReceivingFlag() {
        return receivingFlag;
    }

    public void setReceivingFlag(boolean receivingFlag) {
        this.receivingFlag = receivingFlag;
    }

    public boolean getShippingFlag() {
        return shippingFlag;
    }

    public void setShippingFlag(boolean shippingFlag) {
        this.shippingFlag = shippingFlag;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secondaryInventoryId != null ? secondaryInventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecondaryInventory)) {
            return false;
        }
        SecondaryInventory other = (SecondaryInventory) object;
        if ((this.secondaryInventoryId == null && other.secondaryInventoryId != null) || (this.secondaryInventoryId != null && !this.secondaryInventoryId.equals(other.secondaryInventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.inventory.SecondaryInventory[ secondaryInventoryId=" + secondaryInventoryId + " ]";
    }

    @PrePersist
    public void setRequiredValues() {
        lastUpdateDate = new Date();
        creationDate = new Date();
        lastUpdatedBy = 1;
        createdBy = 1;
    }

    public String getEncumbranceAccountName() {
        return encumbranceAccountName;
    }

    public String getExpenseAccountName() {
        return expenseAccountName;
    }

    public String getMaterialAccountName() {
        return materialAccountName;
    }

    public String getMaterialOverheadAccountName() {
        return materialOverheadAccountName;
    }

    public String getResourceAccountName() {
        return resourceAccountName;
    }

    public void setResourceAccountName(String resourceAccountName) {
        this.resourceAccountName = resourceAccountName;
    }

    public String getOverheadAccountName() {
        return overheadAccountName;
    }

    public void setOverheadAccountName(String overheadAccountName) {
        this.overheadAccountName = overheadAccountName;
    }

    public String getOutsideProcessingAccountName() {
        return outsideProcessingAccountName;
    }

    public void setOutsideProcessingAccountName(String outsideProcessingAccountName) {
        this.outsideProcessingAccountName = outsideProcessingAccountName;
    }

    public void setEncumbranceAccountName(String encumbranceAccountName) {
        this.encumbranceAccountName = encumbranceAccountName;
    }

    public void setExpenseAccountName(String expenseAccountName) {
        this.expenseAccountName = expenseAccountName;
    }

    public void setMaterialAccountName(String materialAccountName) {
        this.materialAccountName = materialAccountName;
    }

    public void setMaterialOverheadAccountName(String materialOverheadAccountName) {
        this.materialOverheadAccountName = materialOverheadAccountName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @ManyToOne
    @JoinColumn(name = "COUNTRY", referencedColumnName = "COUNTRY_CODE")
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}
