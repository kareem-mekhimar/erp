/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.SystemItemType;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@Table(name = "SYSTEM_ITEMS")
@NamedQueries({
    @NamedQuery(name = "SystemItem.findAll", query = "SELECT s FROM SystemItem s")})
public class SystemItem implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sysItem")
    @TableGenerator(name = "sysItem", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "SysItem", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "INVENTORY_ITEM_ID")
    private Integer inventoryItemId;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Basic(optional = false)
    
    @Column(name = "ENABLED_FLAG")
    private boolean enabledFlag;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ACCOUNTING_RULE_ID")
    private Integer accountingRuleId;
    @Size(max = 40)
    @Column(name = "SEGMENT1")
    private String segment1;
    @Size(max = 40)
    @Column(name = "SEGMENT2")
    private String segment2;
    @Size(max = 40)
    @Column(name = "SEGMENT3")
    private String segment3;
    @Size(max = 40)
    @Column(name = "SEGMENT4")
    private String segment4;
    @Size(max = 40)
    @Column(name = "SEGMENT5")
    private String segment5;
    @Size(max = 40)
    @Column(name = "SEGMENT6")
    private String segment6;
    @Size(max = 40)
    @Column(name = "SEGMENT7")
    private String segment7;
    @Size(max = 40)
    @Column(name = "SEGMENT8")
    private String segment8;
    @Size(max = 40)
    @Column(name = "SEGMENT9")
    private String segment9;
    @Basic(optional = false)

    @Column(name = "PURCHASING_ITEM_FLAG")
    private boolean purchasingItemFlag;
    @Basic(optional = false)
    
    @Column(name = "SHIPPABLE_ITEM_FLAG")
    private boolean shippableItemFlag;
    @Basic(optional = false)
    
    @Column(name = "CUSTOMER_ORDER_FLAG")
    private boolean customerOrderFlag;
    @Basic(optional = false)
    
    @Column(name = "INTERNAL_ORDER_FLAG")
    private boolean internalOrderFlag;
    @Basic(optional = false)
    
    @Column(name = "SERVICE_ITEM_FLAG")
    private boolean serviceItemFlag;
    @Basic(optional = false)
    
    @Column(name = "INVENTORY_ITEM_FLAG")
    private boolean inventoryItemFlag;
    @Basic(optional = false)
        
    @Column(name = "ENG_ITEM_FLAG")
    private boolean engItemFlag;
    @Basic(optional = false)
         
    @Column(name = "INVENTORY_ASSET_FLAG")
    private boolean inventoryAssetFlag;
    @Basic(optional = false)
         
    @Column(name = "PURCHASING_ENABLED_FLAG")
    private boolean purchasingEnabledFlag;
    @Basic(optional = false)
         
    @Column(name = "CUSTOMER_ORDER_ENABLED_FLAG")
    private boolean customerOrderEnabledFlag;
    @Basic(optional = false)
        
    @Column(name = "INTERNAL_ORDER_ENABLED_FLAG")
    private boolean internalOrderEnabledFlag;
    @Basic(optional = false)
         
    @Column(name = "SO_TRANSACTIONS_FLAG")
    private boolean soTransactionsFlag;
    @Basic(optional = false)
         
    @Column(name = "MTL_TRANSACTIONS_ENABLED_FLAG")
    private boolean mtlTransactionsEnabledFlag;
    @Basic(optional = false)
         
    @Column(name = "STOCK_ENABLED_FLAG")
    private boolean stockEnabledFlag;
    @Basic(optional = false)
         
    @Column(name = "BOM_ENABLED_FLAG")
    private boolean bomEnabledFlag;
    @Basic(optional = false)
         
    @Column(name = "BUILD_IN_WIP_FLAG")
    private boolean buildInWipFlag;
    @Column(name = "ITEM_CATALOG_GROUP_ID")
    private Integer itemCatalogGroupId;      
    @Column(name = "CATALOG_STATUS_FLAG")
    private boolean catalogStatusFlag;      
    @Column(name = "RETURNABLE_FLAG")
    private boolean returnableFlag;
    @Column(name = "DEFAULT_SHIPPING_ORG")
    private Integer defaultShippingOrg;      
    @Column(name = "TAXABLE_FLAG")
    private boolean taxableFlag;
    @Size(max = 25)
    @Column(name = "QTY_RCV_EXCEPTION_CODE")
    private String qtyRcvExceptionCode;  
    @Column(name = "ALLOW_ITEM_DESC_UPDATE_FLAG")
    private boolean allowItemDescUpdateFlag;   
    @Column(name = "INSPECTION_REQUIRED_FLAG")
    private boolean inspectionRequiredFlag;
    @Column(name = "RECEIPT_REQUIRED_FLAG")
    private boolean receiptRequiredFlag;
    @Column(name = "MARKET_PRICE")
    private Integer marketPrice;
    @Column(name = "HAZARD_CLASS_ID")
    private Integer hazardClassId;  
    @Column(name = "RFQ_REQUIRED_FLAG")
    private boolean rfqRequiredFlag;
    @Column(name = "QTY_RCV_TOLERANCE")
    private Integer qtyRcvTolerance;
    @Column(name = "LIST_PRICE_PER_UNIT")
    private Integer listPricePerUnit;
    @Column(name = "UN_NUMBER_ID")
    private Integer unNumberId;
    @Column(name = "PRICE_TOLERANCE_PERCENT")
    private Integer priceTolerancePercent;
//    @OneToOne
//    @JoinColumn(name = "ASSET_CATEGORY_ID")
//    private FaCategory assetCategory;
    @Size(max = 25)
    @Column(name = "UNIT_OF_ISSUE")
    private String unitOfIssue;
    @Size(max = 25)
    @Column(name = "ENFORCE_SHIP_TO_LOCATION_CODE")
    private String enforceShipToLocationCode;
    @Column(name = "ALLOW_SUBSTITUTE_RECEIPTS_FLAG")
    private boolean allowSubstituteReceiptsFlag; 
    @Column(name = "ALLOW_UNORDERED_RECEIPTS_FLAG")
    private boolean allowUnorderedReceiptsFlag;
    @Column(name = "ALLOW_EXPRESS_DELIVERY_FLAG")
    private boolean allowExpressDeliveryFlag;
    @Column(name = "DAYS_EARLY_RECEIPT_ALLOWED")
    private Integer daysEarlyReceiptAllowed;
    @Column(name = "DAYS_LATE_RECEIPT_ALLOWED")
    private Integer daysLateReceiptAllowed;
    @Size(max = 25)
    @Column(name = "RECEIPT_DAYS_EXCEPTION_CODE")
    private String receiptDaysExceptionCode;
    @Column(name = "RECEIVING_ROUTING_ID")
    private Integer receivingRoutingId;
    @Column(name = "INVOICE_CLOSE_TOLERANCE")
    private Integer invoiceCloseTolerance;
    @Column(name = "RECEIVE_CLOSE_TOLERANCE")
    private Integer receiveCloseTolerance;
    @Size(max = 30)
    @Column(name = "AUTO_LOT_ALPHA_PREFIX")
    private String autoLotAlphaPrefix;
    @Size(max = 30)
    @Column(name = "START_AUTO_LOT_NUMBER")
    private String startAutoLotNumber;
    @Column(name = "LOT_CONTROL_CODE")
    private Integer lotControlCode;
    @Column(name = "SHELF_LIFE_CODE")
    private Integer shelfLifeCode;
    @Column(name = "SHELF_LIFE_DAYS")
    private Integer shelfLifeDays;
    @Column(name = "SERIAL_NUMBER_CONTROL_CODE")
    private Integer serialNumberControlCode;
    @Size(max = 30)
    @Column(name = "START_AUTO_SERIAL_NUMBER")
    private String startAutoSerialNumber;
    @Size(max = 30)
    @Column(name = "AUTO_SERIAL_ALPHA_PREFIX")
    private String autoSerialAlphaPrefix;
    @Column(name = "EXPENSE_ACCOUNT")
    private Integer expenseAccount;
    @Column(name = "ENCUMBRANCE_ACCOUNT")
    private Integer encumbranceAccount;
    @Column(name = "RESTRICT_SUBINVENTORIES_CODE")
    private Integer restrictSubinventoriesCode;
    @Column(name = "UNIT_WEIGHT")
    private Integer unitWeight;
    @Size(max = 3)
    @Column(name = "WEIGHT_UOM_CODE")
    private String weightUomCode;
    @Size(max = 3)
    @Column(name = "VOLUME_UOM_CODE")
    private String volumeUomCode;
    @Column(name = "UNIT_VOLUME")
    private Integer unitVolume;
    @Column(name = "RESTRICT_LOCATORS_CODE")
    private Integer restrictLocatorsCode;
    @Column(name = "LOCATION_CONTROL_CODE")
    private Integer locationControlCode;
    @Column(name = "SHRINKAGE_RATE")
    private Integer shrinkageRate;
    @Column(name = "ACCEPTABLE_EARLY_DAYS")
    private Integer acceptableEarlyDays;
    @Column(name = "PLANNING_TIME_FENCE_CODE")
    private Integer planningTimeFenceCode;
    @Column(name = "DEMAND_TIME_FENCE_CODE")
    private Integer demandTimeFenceCode;
    @Column(name = "LEAD_TIME_LOT_SIZE")
    private Integer leadTimeLotSize;
    @Column(name = "STD_LOT_SIZE")
    private Integer stdLotSize;
    @Column(name = "CUM_MANUFACTURING_LEAD_TIME")
    private Integer cumManufacturingLeadTime;
    @Column(name = "OVERRUN_PERCENTAGE")
    private Integer overrunPercentage;
    @Column(name = "MRP_CALCULATE_ATP_FLAG")
    private boolean mrpCalculateAtpFlag;
    @Column(name = "ACCEPTABLE_RATE_INCREASE")
    private Integer acceptableRateIncrease;
    @Column(name = "ACCEPTABLE_RATE_DECREASE")
    private Integer acceptableRateDecrease;
    @Column(name = "CUMULATIVE_TOTAL_LEAD_TIME")
    private Integer cumulativeTotalLeadTime;
    @Column(name = "PLANNING_TIME_FENCE_DAYS")
    private Integer planningTimeFenceDays;
    @Column(name = "DEMAND_TIME_FENCE_DAYS")
    private Integer demandTimeFenceDays;
    @Column(name = "END_ASSEMBLY_PEGGING_FLAG")
    private boolean endAssemblyPeggingFlag;
    @Column(name = "REPETITIVE_PLANNING_FLAG")
    private boolean repetitivePlanningFlag;
    @Size(max = 10)
    @Column(name = "PLANNING_EXCEPTION_SET")
    private String planningExceptionSet;
    @Column(name = "BOM_ITEM_TYPE")
    private boolean bomItemType;
    @Column(name = "PICK_COMPONENTS_FLAG")
    private boolean pickComponentsFlag;
    @Column(name = "REPLENISH_TO_ORDER_FLAG")
    private boolean replenishToOrderFlag;
    @Column(name = "BASE_ITEM_ID")
    private Integer baseItemId;
    @Column(name = "ATP_COMPONENTS_FLAG")
    private boolean atpComponentsFlag;
    @Column(name = "ATP_FLAG")
    private boolean atpFlag;
    @Column(name = "FIXED_LEAD_TIME")
    private Integer fixedLeadTime;
    @Column(name = "VARIABLE_LEAD_TIME")
    private Integer variableLeadTime;
    @Column(name = "WIP_SUPPLY_LOCATOR_ID")
    private Integer wipSupplyLocatorId;
    @Column(name = "WIP_SUPPLY_TYPE")
    private Integer wipSupplyType;
    @Size(max = 10)
    @Column(name = "WIP_SUPPLY_SUBINVENTORY")
    private String wipSupplySubinventory;
    @Size(max = 3)
    @Column(name = "PRIMARY_UOM_CODE")
    private String primaryUomCode;
    @Size(max = 25)
    @Column(name = "PRIMARY_UOM")
    private String primaryUom;
    @Column(name = "ALLOWED_UNITS_LOOKUP_CODE")
    private Integer allowedUnitsLookupCode;
    @Column(name = "COST_OF_SALES_ACCOUNT")
    private Integer costOfSalesAccount;
    @Column(name = "SALES_ACCOUNT")
    private Integer salesAccount;
    @Column(name = "MIN_MINMAX_QUANTITY")
    private Integer minMinmaxQuantity;
    @Column(name = "MAX_MINMAX_QUANTITY")
    private Integer maxMinmaxQuantity;
    @Column(name = "MINIMUM_ORDER_QUANTITY")
    private Integer minimumOrderQuantity;
    @Column(name = "MAXIMUM_ORDER_QUANTITY")
    private Integer maximumOrderQuantity;
    @Column(name = "PAYMENT_TERMS_ID")
    private Integer paymentTermsId;
    @Basic(optional = false)
    
    @Column(name = "INVOICEABLE_ITEM_FLAG")
    private boolean invoiceableItemFlag;
    @Size(max = 50)
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Basic(optional = false)
    
    @Column(name = "INVOICE_ENABLED_FLAG")
    private boolean invoiceEnabledFlag;
    @Basic(optional = false)
    
    @Column(name = "COSTING_ENABLED_FLAG")
    private boolean costingEnabledFlag;
    @Basic(optional = false)
    
    @Column(name = "CYCLE_COUNT_ENABLED_FLAG")
    private boolean cycleCountEnabledFlag;
    @Size(max = 30)
    @Column(name = "ITEM_TYPE")
    private String itemType;
    @Column(name = "PRODUCT_FAMILY_ITEM_ID")
    private Integer productFamilyItemId;
    @Column(name = "CHECK_SHORTAGES_FLAG")
    private boolean checkShortagesFlag;
    @Size(max = 30)
    @Column(name = "ASSET_CREATION_CODE")
    private String assetCreationCode;
    @Column(name = "UNIT_LENGTH")
    private Integer unitLength;
    @Column(name = "UNIT_WIDTH")
    private Integer unitWidth;
    @Column(name = "UNIT_HEIGHT")
    private Integer unitHeight;
    @Column(name = "BULK_PICKED_FLAG")
    private boolean bulkPickedFlag;
    @Column(name = "LOT_STATUS_ENABLED")
    private String lotStatusEnabled;
    @Column(name = "DEFAULT_LOT_STATUS_ID")
    private Integer defaultLotStatusId;
      
    @Column(name = "SERIAL_STATUS_ENABLED")
    private String serialStatusEnabled;
    @Size(max = 3)
    @Column(name = "SECONDARY_UOM_CODE")
    private String secondaryUomCode;
    @Size(max = 30)
    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;
    @Size(max = 32)
    @Column(name = "EXPIRATION_ACTION_CODE")
    private String expirationActionCode;
    @Column(name = "EXPIRATION_ACTION_INTERVAL")
    private Integer expirationActionInterval;
    @Column(name = "GRADE_CONTROL_FLAG")
    private boolean gradeControlFlag;
    @Column(name = "HAZARDOUS_MATERIAL_FLAG")
    private boolean hazardousMaterialFlag;
    @Column(name = "HOLD_DAYS")
    private Integer holdDays;
    @Column(name = "MATURITY_DAYS")
    private Integer maturityDays;
    @Column(name = "PROCESS_COSTING_ENABLED_FLAG")
    private boolean processCostingEnabledFlag;
    @Column(name = "PROCESS_QUALITY_ENABLED_FLAG")
    private boolean processQualityEnabledFlag;
    @Column(name = "RECIPE_ENABLED_FLAG")
    private boolean recipeEnabledFlag;
    @Column(name = "RETEST_INTERVAL")
    private Integer retestInterval;
    @Column(name = "OUTSOURCED_ASSEMBLY")
    private Integer outsourcedAssembly;
    @Column(name = "DEFAULT_MATERIAL_STATUS_ID")
    private Integer defaultMaterialStatusId;
   
    @ManyToOne
    @JoinColumn(name = "PRIMARY_UOM_ID",referencedColumnName = "UOM_ID")
    private UomLine primaryUomId;
  
    @Size(max = 100)
    @Column(name = "INVENTORY_ITEM_CODE")
    private String inventoryItemCode;
    
    @Column(name = "ITEM_TYPE_ID")
    private SystemItemType systemItemType ;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="MTL_ITEM_CATEGORIES",joinColumns = @JoinColumn(name = "ITEM_ID",referencedColumnName = "INVENTORY_ITEM_ID"),inverseJoinColumns =@JoinColumn(name = "CATEGORY_ID",referencedColumnName = "CATEGORY_ID") )
    private List<MtlCategory> categoryList;
    
    public SystemItem() {
    }

    public SystemItem(Integer inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

   

    public Integer getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Integer inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }


    public List<MtlCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<MtlCategory> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroupId;
    
    
    
    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
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

    public boolean getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountingRuleId() {
        return accountingRuleId;
    }

    public void setAccountingRuleId(Integer accountingRuleId) {
        this.accountingRuleId = accountingRuleId;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment4() {
        return segment4;
    }

    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    public String getSegment5() {
        return segment5;
    }

    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    public String getSegment6() {
        return segment6;
    }

    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }

    public String getSegment7() {
        return segment7;
    }

    public void setSegment7(String segment7) {
        this.segment7 = segment7;
    }

    public String getSegment8() {
        return segment8;
    }

    public void setSegment8(String segment8) {
        this.segment8 = segment8;
    }

    public String getSegment9() {
        return segment9;
    }

    public void setSegment9(String segment9) {
        this.segment9 = segment9;
    }

    public boolean getPurchasingItemFlag() {
        return purchasingItemFlag;
    }

    public void setPurchasingItemFlag(boolean purchasingItemFlag) {
        this.purchasingItemFlag = purchasingItemFlag;
    }

    public boolean getShippableItemFlag() {
        return shippableItemFlag;
    }

    public void setShippableItemFlag(boolean shippableItemFlag) {
        this.shippableItemFlag = shippableItemFlag;
    }

    public boolean getCustomerOrderFlag() {
        return customerOrderFlag;
    }

    public void setCustomerOrderFlag(boolean customerOrderFlag) {
        this.customerOrderFlag = customerOrderFlag;
    }

    public boolean getInternalOrderFlag() {
        return internalOrderFlag;
    }

    public void setInternalOrderFlag(boolean internalOrderFlag) {
        this.internalOrderFlag = internalOrderFlag;
    }

    public boolean getServiceItemFlag() {
        return serviceItemFlag;
    }

    public void setServiceItemFlag(boolean serviceItemFlag) {
        this.serviceItemFlag = serviceItemFlag;
    }

    public boolean getInventoryItemFlag() {
        return inventoryItemFlag;
    }

    public void setInventoryItemFlag(boolean inventoryItemFlag) {
        this.inventoryItemFlag = inventoryItemFlag;
    }

    public boolean getEngItemFlag() {
        return engItemFlag;
    }

    public void setEngItemFlag(boolean engItemFlag) {
        this.engItemFlag = engItemFlag;
    }

    public boolean getInventoryAssetFlag() {
        return inventoryAssetFlag;
    }

    public void setInventoryAssetFlag(boolean inventoryAssetFlag) {
        this.inventoryAssetFlag = inventoryAssetFlag;
    }

    public boolean getPurchasingEnabledFlag() {
        return purchasingEnabledFlag;
    }

    public void setPurchasingEnabledFlag(boolean purchasingEnabledFlag) {
        this.purchasingEnabledFlag = purchasingEnabledFlag;
    }

    public boolean getCustomerOrderEnabledFlag() {
        return customerOrderEnabledFlag;
    }

    public void setCustomerOrderEnabledFlag(boolean customerOrderEnabledFlag) {
        this.customerOrderEnabledFlag = customerOrderEnabledFlag;
    }

    public boolean getInternalOrderEnabledFlag() {
        return internalOrderEnabledFlag;
    }

    public void setInternalOrderEnabledFlag(boolean internalOrderEnabledFlag) {
        this.internalOrderEnabledFlag = internalOrderEnabledFlag;
    }

    public boolean getSoTransactionsFlag() {
        return soTransactionsFlag;
    }

    public void setSoTransactionsFlag(boolean soTransactionsFlag) {
        this.soTransactionsFlag = soTransactionsFlag;
    }

    public boolean getMtlTransactionsEnabledFlag() {
        return mtlTransactionsEnabledFlag;
    }

    public void setMtlTransactionsEnabledFlag(boolean mtlTransactionsEnabledFlag) {
        this.mtlTransactionsEnabledFlag = mtlTransactionsEnabledFlag;
    }

    public boolean getStockEnabledFlag() {
        return stockEnabledFlag;
    }

    public void setStockEnabledFlag(boolean stockEnabledFlag) {
        this.stockEnabledFlag = stockEnabledFlag;
    }

    public boolean getBomEnabledFlag() {
        return bomEnabledFlag;
    }

    public void setBomEnabledFlag(boolean bomEnabledFlag) {
        this.bomEnabledFlag = bomEnabledFlag;
    }

    public boolean getBuildInWipFlag() {
        return buildInWipFlag;
    }

    public void setBuildInWipFlag(boolean buildInWipFlag) {
        this.buildInWipFlag = buildInWipFlag;
    }

    public Integer getItemCatalogGroupId() {
        return itemCatalogGroupId;
    }

    public void setItemCatalogGroupId(Integer itemCatalogGroupId) {
        this.itemCatalogGroupId = itemCatalogGroupId;
    }

    public boolean getCatalogStatusFlag() {
        return catalogStatusFlag;
    }

    public void setCatalogStatusFlag(boolean catalogStatusFlag) {
        this.catalogStatusFlag = catalogStatusFlag;
    }

    public boolean getReturnableFlag() {
        return returnableFlag;
    }

    public void setReturnableFlag(boolean returnableFlag) {
        this.returnableFlag = returnableFlag;
    }

    public Integer getDefaultShippingOrg() {
        return defaultShippingOrg;
    }

    public void setDefaultShippingOrg(Integer defaultShippingOrg) {
        this.defaultShippingOrg = defaultShippingOrg;
    }

    public void setTaxableFlag(boolean taxableFlag) {
        this.taxableFlag = taxableFlag;
    }

    public boolean getTaxableFlag() {
        return taxableFlag;
    }

    
    public String getQtyRcvExceptionCode() {
        return qtyRcvExceptionCode;
    }

    public void setQtyRcvExceptionCode(String qtyRcvExceptionCode) {
        this.qtyRcvExceptionCode = qtyRcvExceptionCode;
    }

    public boolean getAllowItemDescUpdateFlag() {
        return allowItemDescUpdateFlag;
    }

    public void setAllowItemDescUpdateFlag(boolean allowItemDescUpdateFlag) {
        this.allowItemDescUpdateFlag = allowItemDescUpdateFlag;
    }

    public boolean getInspectionRequiredFlag() {
        return inspectionRequiredFlag;
    }

    public void setInspectionRequiredFlag(boolean inspectionRequiredFlag) {
        this.inspectionRequiredFlag = inspectionRequiredFlag;
    }

    public boolean getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(boolean receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getHazardClassId() {
        return hazardClassId;
    }

    public void setHazardClassId(Integer hazardClassId) {
        this.hazardClassId = hazardClassId;
    }

    public boolean getRfqRequiredFlag() {
        return rfqRequiredFlag;
    }

    public void setRfqRequiredFlag(boolean rfqRequiredFlag) {
        this.rfqRequiredFlag = rfqRequiredFlag;
    }

    public Integer getQtyRcvTolerance() {
        return qtyRcvTolerance;
    }

    public void setQtyRcvTolerance(Integer qtyRcvTolerance) {
        this.qtyRcvTolerance = qtyRcvTolerance;
    }

    public Integer getListPricePerUnit() {
        return listPricePerUnit;
    }

    public void setListPricePerUnit(Integer listPricePerUnit) {
        this.listPricePerUnit = listPricePerUnit;
    }

    public Integer getUnNumberId() {
        return unNumberId;
    }

    public void setUnNumberId(Integer unNumberId) {
        this.unNumberId = unNumberId;
    }

    public Integer getPriceTolerancePercent() {
        return priceTolerancePercent;
    }

    public void setPriceTolerancePercent(Integer priceTolerancePercent) {
        this.priceTolerancePercent = priceTolerancePercent;
    }

//    public FaCategory getAssetCategory() {
//        return assetCategory;
//    }
//
//    public void setAssetCategory(FaCategory assetCategory) {
//        this.assetCategory = assetCategory;
//    }

    public String getUnitOfIssue() {
        return unitOfIssue;
    }

    public void setUnitOfIssue(String unitOfIssue) {
        this.unitOfIssue = unitOfIssue;
    }

    public String getEnforceShipToLocationCode() {
        return enforceShipToLocationCode;
    }

    public void setEnforceShipToLocationCode(String enforceShipToLocationCode) {
        this.enforceShipToLocationCode = enforceShipToLocationCode;
    }

    public boolean getAllowSubstituteReceiptsFlag() {
        return allowSubstituteReceiptsFlag;
    }

    public void setAllowSubstituteReceiptsFlag(boolean allowSubstituteReceiptsFlag) {
        this.allowSubstituteReceiptsFlag = allowSubstituteReceiptsFlag;
    }

    public boolean getAllowUnorderedReceiptsFlag() {
        return allowUnorderedReceiptsFlag;
    }

    public void setAllowUnorderedReceiptsFlag(boolean allowUnorderedReceiptsFlag) {
        this.allowUnorderedReceiptsFlag = allowUnorderedReceiptsFlag;
    }

    public boolean getAllowExpressDeliveryFlag() {
        return allowExpressDeliveryFlag;
    }

    public void setAllowExpressDeliveryFlag(boolean allowExpressDeliveryFlag) {
        this.allowExpressDeliveryFlag = allowExpressDeliveryFlag;
    }

    public Integer getDaysEarlyReceiptAllowed() {
        return daysEarlyReceiptAllowed;
    }

    public void setDaysEarlyReceiptAllowed(Integer daysEarlyReceiptAllowed) {
        this.daysEarlyReceiptAllowed = daysEarlyReceiptAllowed;
    }

    public Integer getDaysLateReceiptAllowed() {
        return daysLateReceiptAllowed;
    }

    public void setDaysLateReceiptAllowed(Integer daysLateReceiptAllowed) {
        this.daysLateReceiptAllowed = daysLateReceiptAllowed;
    }

    public String getReceiptDaysExceptionCode() {
        return receiptDaysExceptionCode;
    }

    public void setReceiptDaysExceptionCode(String receiptDaysExceptionCode) {
        this.receiptDaysExceptionCode = receiptDaysExceptionCode;
    }

    public Integer getReceivingRoutingId() {
        return receivingRoutingId;
    }

    public void setReceivingRoutingId(Integer receivingRoutingId) {
        this.receivingRoutingId = receivingRoutingId;
    }

    public Integer getInvoiceCloseTolerance() {
        return invoiceCloseTolerance;
    }

    public void setInvoiceCloseTolerance(Integer invoiceCloseTolerance) {
        this.invoiceCloseTolerance = invoiceCloseTolerance;
    }

    public Integer getReceiveCloseTolerance() {
        return receiveCloseTolerance;
    }

    public void setReceiveCloseTolerance(Integer receiveCloseTolerance) {
        this.receiveCloseTolerance = receiveCloseTolerance;
    }

    public String getAutoLotAlphaPrefix() {
        return autoLotAlphaPrefix;
    }

    public void setAutoLotAlphaPrefix(String autoLotAlphaPrefix) {
        this.autoLotAlphaPrefix = autoLotAlphaPrefix;
    }

    public String getStartAutoLotNumber() {
        return startAutoLotNumber;
    }

    public void setStartAutoLotNumber(String startAutoLotNumber) {
        this.startAutoLotNumber = startAutoLotNumber;
    }

    public Integer getLotControlCode() {
        return lotControlCode;
    }

    public void setLotControlCode(Integer lotControlCode) {
        this.lotControlCode = lotControlCode;
    }

    public Integer getShelfLifeCode() {
        return shelfLifeCode;
    }

    public void setShelfLifeCode(Integer shelfLifeCode) {
        this.shelfLifeCode = shelfLifeCode;
    }

    public Integer getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(Integer shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }

    public Integer getSerialNumberControlCode() {
        return serialNumberControlCode;
    }

    public void setSerialNumberControlCode(Integer serialNumberControlCode) {
        this.serialNumberControlCode = serialNumberControlCode;
    }

    public String getStartAutoSerialNumber() {
        return startAutoSerialNumber;
    }

    public void setStartAutoSerialNumber(String startAutoSerialNumber) {
        this.startAutoSerialNumber = startAutoSerialNumber;
    }

    public String getAutoSerialAlphaPrefix() {
        return autoSerialAlphaPrefix;
    }

    public void setAutoSerialAlphaPrefix(String autoSerialAlphaPrefix) {
        this.autoSerialAlphaPrefix = autoSerialAlphaPrefix;
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

    public Integer getRestrictSubinventoriesCode() {
        return restrictSubinventoriesCode;
    }

    public void setRestrictSubinventoriesCode(Integer restrictSubinventoriesCode) {
        this.restrictSubinventoriesCode = restrictSubinventoriesCode;
    }

    public Integer getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(Integer unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getWeightUomCode() {
        return weightUomCode;
    }

    public void setWeightUomCode(String weightUomCode) {
        this.weightUomCode = weightUomCode;
    }

    public String getVolumeUomCode() {
        return volumeUomCode;
    }

    public void setVolumeUomCode(String volumeUomCode) {
        this.volumeUomCode = volumeUomCode;
    }

    public Integer getUnitVolume() {
        return unitVolume;
    }

    public void setUnitVolume(Integer unitVolume) {
        this.unitVolume = unitVolume;
    }

    public Integer getRestrictLocatorsCode() {
        return restrictLocatorsCode;
    }

    public void setRestrictLocatorsCode(Integer restrictLocatorsCode) {
        this.restrictLocatorsCode = restrictLocatorsCode;
    }

    public Integer getLocationControlCode() {
        return locationControlCode;
    }

    public void setLocationControlCode(Integer locationControlCode) {
        this.locationControlCode = locationControlCode;
    }

    public Integer getShrinkageRate() {
        return shrinkageRate;
    }

    public void setShrinkageRate(Integer shrinkageRate) {
        this.shrinkageRate = shrinkageRate;
    }

    public Integer getAcceptableEarlyDays() {
        return acceptableEarlyDays;
    }

    public void setAcceptableEarlyDays(Integer acceptableEarlyDays) {
        this.acceptableEarlyDays = acceptableEarlyDays;
    }

    public Integer getPlanningTimeFenceCode() {
        return planningTimeFenceCode;
    }

    public void setPlanningTimeFenceCode(Integer planningTimeFenceCode) {
        this.planningTimeFenceCode = planningTimeFenceCode;
    }

    public Integer getDemandTimeFenceCode() {
        return demandTimeFenceCode;
    }

    public void setDemandTimeFenceCode(Integer demandTimeFenceCode) {
        this.demandTimeFenceCode = demandTimeFenceCode;
    }

    public Integer getLeadTimeLotSize() {
        return leadTimeLotSize;
    }

    public void setLeadTimeLotSize(Integer leadTimeLotSize) {
        this.leadTimeLotSize = leadTimeLotSize;
    }

    public Integer getStdLotSize() {
        return stdLotSize;
    }

    public void setStdLotSize(Integer stdLotSize) {
        this.stdLotSize = stdLotSize;
    }

    public Integer getCumManufacturingLeadTime() {
        return cumManufacturingLeadTime;
    }

    public void setCumManufacturingLeadTime(Integer cumManufacturingLeadTime) {
        this.cumManufacturingLeadTime = cumManufacturingLeadTime;
    }

    public Integer getOverrunPercentage() {
        return overrunPercentage;
    }

    public void setOverrunPercentage(Integer overrunPercentage) {
        this.overrunPercentage = overrunPercentage;
    }

    public boolean getMrpCalculateAtpFlag() {
        return mrpCalculateAtpFlag;
    }

    public void setMrpCalculateAtpFlag(boolean mrpCalculateAtpFlag) {
        this.mrpCalculateAtpFlag = mrpCalculateAtpFlag;
    }

    public Integer getAcceptableRateIncrease() {
        return acceptableRateIncrease;
    }

    public void setAcceptableRateIncrease(Integer acceptableRateIncrease) {
        this.acceptableRateIncrease = acceptableRateIncrease;
    }

    public Integer getAcceptableRateDecrease() {
        return acceptableRateDecrease;
    }

    public void setAcceptableRateDecrease(Integer acceptableRateDecrease) {
        this.acceptableRateDecrease = acceptableRateDecrease;
    }

    public Integer getCumulativeTotalLeadTime() {
        return cumulativeTotalLeadTime;
    }

    public void setCumulativeTotalLeadTime(Integer cumulativeTotalLeadTime) {
        this.cumulativeTotalLeadTime = cumulativeTotalLeadTime;
    }

    public Integer getPlanningTimeFenceDays() {
        return planningTimeFenceDays;
    }

    public void setPlanningTimeFenceDays(Integer planningTimeFenceDays) {
        this.planningTimeFenceDays = planningTimeFenceDays;
    }

    public Integer getDemandTimeFenceDays() {
        return demandTimeFenceDays;
    }

    public void setDemandTimeFenceDays(Integer demandTimeFenceDays) {
        this.demandTimeFenceDays = demandTimeFenceDays;
    }

    public boolean getEndAssemblyPeggingFlag() {
        return endAssemblyPeggingFlag;
    }

    public void setEndAssemblyPeggingFlag(boolean endAssemblyPeggingFlag) {
        this.endAssemblyPeggingFlag = endAssemblyPeggingFlag;
    }

    public boolean getRepetitivePlanningFlag() {
        return repetitivePlanningFlag;
    }

    public void setRepetitivePlanningFlag(boolean repetitivePlanningFlag) {
        this.repetitivePlanningFlag = repetitivePlanningFlag;
    }

    public String getPlanningExceptionSet() {
        return planningExceptionSet;
    }

    public void setPlanningExceptionSet(String planningExceptionSet) {
        this.planningExceptionSet = planningExceptionSet;
    }

    public boolean getBomItemType() {
        return bomItemType;
    }

    public void setBomItemType(boolean bomItemType) {
        this.bomItemType = bomItemType;
    }

    public boolean getPickComponentsFlag() {
        return pickComponentsFlag;
    }

    public void setPickComponentsFlag(boolean pickComponentsFlag) {
        this.pickComponentsFlag = pickComponentsFlag;
    }

    public boolean getReplenishToOrderFlag() {
        return replenishToOrderFlag;
    }

    public void setReplenishToOrderFlag(boolean replenishToOrderFlag) {
        this.replenishToOrderFlag = replenishToOrderFlag;
    }

    public Integer getBaseItemId() {
        return baseItemId;
    }

    public void setBaseItemId(Integer baseItemId) {
        this.baseItemId = baseItemId;
    }

    public boolean getAtpComponentsFlag() {
        return atpComponentsFlag;
    }

    public void setAtpComponentsFlag(boolean atpComponentsFlag) {
        this.atpComponentsFlag = atpComponentsFlag;
    }

    public boolean getAtpFlag() {
        return atpFlag;
    }

    public void setAtpFlag(boolean atpFlag) {
        this.atpFlag = atpFlag;
    }

    public Integer getFixedLeadTime() {
        return fixedLeadTime;
    }

    public void setFixedLeadTime(Integer fixedLeadTime) {
        this.fixedLeadTime = fixedLeadTime;
    }

    public Integer getVariableLeadTime() {
        return variableLeadTime;
    }

    public void setVariableLeadTime(Integer variableLeadTime) {
        this.variableLeadTime = variableLeadTime;
    }

    public Integer getWipSupplyLocatorId() {
        return wipSupplyLocatorId;
    }

    public void setWipSupplyLocatorId(Integer wipSupplyLocatorId) {
        this.wipSupplyLocatorId = wipSupplyLocatorId;
    }

    public Integer getWipSupplyType() {
        return wipSupplyType;
    }

    public void setWipSupplyType(Integer wipSupplyType) {
        this.wipSupplyType = wipSupplyType;
    }

    public String getWipSupplySubinventory() {
        return wipSupplySubinventory;
    }

    public void setWipSupplySubinventory(String wipSupplySubinventory) {
        this.wipSupplySubinventory = wipSupplySubinventory;
    }

    public String getPrimaryUomCode() {
        return primaryUomCode;
    }

    public void setPrimaryUomCode(String primaryUomCode) {
        this.primaryUomCode = primaryUomCode;
    }

    public String getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        this.primaryUom = primaryUom;
    }

    public Integer getAllowedUnitsLookupCode() {
        return allowedUnitsLookupCode;
    }

    public void setAllowedUnitsLookupCode(Integer allowedUnitsLookupCode) {
        this.allowedUnitsLookupCode = allowedUnitsLookupCode;
    }

    public Integer getCostOfSalesAccount() {
        return costOfSalesAccount;
    }

    public void setCostOfSalesAccount(Integer costOfSalesAccount) {
        this.costOfSalesAccount = costOfSalesAccount;
    }

    public Integer getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(Integer salesAccount) {
        this.salesAccount = salesAccount;
    }

    public Integer getMinMinmaxQuantity() {
        return minMinmaxQuantity;
    }

    public void setMinMinmaxQuantity(Integer minMinmaxQuantity) {
        this.minMinmaxQuantity = minMinmaxQuantity;
    }

    public Integer getMaxMinmaxQuantity() {
        return maxMinmaxQuantity;
    }

    public void setMaxMinmaxQuantity(Integer maxMinmaxQuantity) {
        this.maxMinmaxQuantity = maxMinmaxQuantity;
    }

    public Integer getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public Integer getMaximumOrderQuantity() {
        return maximumOrderQuantity;
    }

    public void setMaximumOrderQuantity(Integer maximumOrderQuantity) {
        this.maximumOrderQuantity = maximumOrderQuantity;
    }

    public Integer getPaymentTermsId() {
        return paymentTermsId;
    }

    public void setPaymentTermsId(Integer paymentTermsId) {
        this.paymentTermsId = paymentTermsId;
    }

    public boolean getInvoiceableItemFlag() {
        return invoiceableItemFlag;
    }

    public void setInvoiceableItemFlag(boolean invoiceableItemFlag) {
        this.invoiceableItemFlag = invoiceableItemFlag;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public boolean getInvoiceEnabledFlag() {
        return invoiceEnabledFlag;
    }

    public void setInvoiceEnabledFlag(boolean invoiceEnabledFlag) {
        this.invoiceEnabledFlag = invoiceEnabledFlag;
    }

    public boolean getCostingEnabledFlag() {
        return costingEnabledFlag;
    }

    public void setCostingEnabledFlag(boolean costingEnabledFlag) {
        this.costingEnabledFlag = costingEnabledFlag;
    }

    public boolean getCycleCountEnabledFlag() {
        return cycleCountEnabledFlag;
    }

    public void setCycleCountEnabledFlag(boolean cycleCountEnabledFlag) {
        this.cycleCountEnabledFlag = cycleCountEnabledFlag;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getProductFamilyItemId() {
        return productFamilyItemId;
    }

    public void setProductFamilyItemId(Integer productFamilyItemId) {
        this.productFamilyItemId = productFamilyItemId;
    }

    public boolean getCheckShortagesFlag() {
        return checkShortagesFlag;
    }

    public void setCheckShortagesFlag(boolean checkShortagesFlag) {
        this.checkShortagesFlag = checkShortagesFlag;
    }

    public String getAssetCreationCode() {
        return assetCreationCode;
    }

    public void setAssetCreationCode(String assetCreationCode) {
        this.assetCreationCode = assetCreationCode;
    }

    public Integer getUnitLength() {
        return unitLength;
    }

    public void setUnitLength(Integer unitLength) {
        this.unitLength = unitLength;
    }

    public Integer getUnitWidth() {
        return unitWidth;
    }

    public void setUnitWidth(Integer unitWidth) {
        this.unitWidth = unitWidth;
    }

    public Integer getUnitHeight() {
        return unitHeight;
    }

    public void setUnitHeight(Integer unitHeight) {
        this.unitHeight = unitHeight;
    }

    public boolean getBulkPickedFlag() {
        return bulkPickedFlag;
    }

    public void setBulkPickedFlag(boolean bulkPickedFlag) {
        this.bulkPickedFlag = bulkPickedFlag;
    }

    public String getLotStatusEnabled() {
        return lotStatusEnabled;
    }

    public void setLotStatusEnabled(String lotStatusEnabled) {
        this.lotStatusEnabled = lotStatusEnabled;
    }

    public Integer getDefaultLotStatusId() {
        return defaultLotStatusId;
    }

    public void setDefaultLotStatusId(Integer defaultLotStatusId) {
        this.defaultLotStatusId = defaultLotStatusId;
    }

    public String getSerialStatusEnabled() {
        return serialStatusEnabled;
    }

    public void setSerialStatusEnabled(String serialStatusEnabled) {
        this.serialStatusEnabled = serialStatusEnabled;
    }

    public String getSecondaryUomCode() {
        return secondaryUomCode;
    }

    public void setSecondaryUomCode(String secondaryUomCode) {
        this.secondaryUomCode = secondaryUomCode;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getExpirationActionCode() {
        return expirationActionCode;
    }

    public void setExpirationActionCode(String expirationActionCode) {
        this.expirationActionCode = expirationActionCode;
    }

    public Integer getExpirationActionInterval() {
        return expirationActionInterval;
    }

    public void setExpirationActionInterval(Integer expirationActionInterval) {
        this.expirationActionInterval = expirationActionInterval;
    }

    public boolean getGradeControlFlag() {
        return gradeControlFlag;
    }

    public void setGradeControlFlag(boolean gradeControlFlag) {
        this.gradeControlFlag = gradeControlFlag;
    }

    public boolean getHazardousMaterialFlag() {
        return hazardousMaterialFlag;
    }

    public void setHazardousMaterialFlag(boolean hazardousMaterialFlag) {
        this.hazardousMaterialFlag = hazardousMaterialFlag;
    }

    public Integer getHoldDays() {
        return holdDays;
    }

    public void setHoldDays(Integer holdDays) {
        this.holdDays = holdDays;
    }

    public Integer getMaturityDays() {
        return maturityDays;
    }

    public void setMaturityDays(Integer maturityDays) {
        this.maturityDays = maturityDays;
    }

    public boolean getProcessCostingEnabledFlag() {
        return processCostingEnabledFlag;
    }

    public void setProcessCostingEnabledFlag(boolean processCostingEnabledFlag) {
        this.processCostingEnabledFlag = processCostingEnabledFlag;
    }

    public boolean getProcessQualityEnabledFlag() {
        return processQualityEnabledFlag;
    }

    public void setProcessQualityEnabledFlag(boolean processQualityEnabledFlag) {
        this.processQualityEnabledFlag = processQualityEnabledFlag;
    }

    public boolean getRecipeEnabledFlag() {
        return recipeEnabledFlag;
    }

    public void setRecipeEnabledFlag(boolean recipeEnabledFlag) {
        this.recipeEnabledFlag = recipeEnabledFlag;
    }

    public Integer getRetestInterval() {
        return retestInterval;
    }

    public void setRetestInterval(Integer retestInterval) {
        this.retestInterval = retestInterval;
    }

    public Integer getOutsourcedAssembly() {
        return outsourcedAssembly;
    }

    public void setOutsourcedAssembly(Integer outsourcedAssembly) {
        this.outsourcedAssembly = outsourcedAssembly;
    }

    public Integer getDefaultMaterialStatusId() {
        return defaultMaterialStatusId;
    }

    public void setDefaultMaterialStatusId(Integer defaultMaterialStatusId) {
        this.defaultMaterialStatusId = defaultMaterialStatusId;
    }

    public String getInventoryItemCode() {
        return inventoryItemCode;
    }

    public void setInventoryItemCode(String inventoryItemCode) {
        this.inventoryItemCode = inventoryItemCode;
    }

    public UomLine getPrimaryUomId() {
        return primaryUomId;
    }

    public void setPrimaryUomId(UomLine primaryUomId) {
        this.primaryUomId = primaryUomId;
    }

    public SystemItemType getSystemItemType() {
        return systemItemType;
    }

    public void setSystemItemType(SystemItemType systemItemType) {
        this.systemItemType = systemItemType;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryItemId != null ? inventoryItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemItem)) {
            return false;
        }
        SystemItem other = (SystemItem) object;
        if ((this.inventoryItemId == null && other.inventoryItemId != null) || (this.inventoryItemId != null && !this.inventoryItemId.equals(other.inventoryItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.SystemItem[ inventoryItemId=" + inventoryItemId + " ]";
    }
    
    @PrePersist
    public void setRequiredValues(){
       lastUpdateDate=new Date();
       lastUpdateLogin=1;
       lastUpdatedBy=1;
       createdBy=1;
       creationDate=new Date();
    }
    
}
