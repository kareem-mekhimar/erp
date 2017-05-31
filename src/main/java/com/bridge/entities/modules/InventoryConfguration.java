/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.modules;

import com.bridge.entities.inventory.*;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ControlType;
import com.bridge.enums.CostMethod;
import com.bridge.enums.LotGenerationType;
import com.bridge.enums.LotUniqueType;
import com.bridge.enums.SerialGenerationType;
import com.bridge.enums.SerialUniqueType;
import com.bridge.enums.SourceType;
import com.bridge.enums.TransferChargeType;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "INVENTORY_MODULE_CONFGURATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryConfguration.findAll", query = "SELECT i FROM InventoryConfguration i")})
public class InventoryConfguration implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
           @GeneratedValue(strategy = GenerationType.TABLE, generator = "InvConfig")
    @TableGenerator(name = "InvConfig", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "InvConfig", valueColumnName = "CURRENT_VALUE")
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
     @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID", referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit operatingUnitId;
    @Size(max = 100)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PRIMARY_COST_METHOD")
    private CostMethod primaryCostMethod;
    @Column(name = "COST_ORGANIZATION_ID")
    private Integer costOrganizationId;
    @Column(name = "DEFAULT_MATERIAL_COST_ID")
    private Integer defaultMaterialCostId;
    @Size(max = 10)
    @Column(name = "CALENDAR_CODE")
    private String calendarCode;
    @Column(name = "GENERAL_LEDGER_UPDATE_CODE")
    private Integer generalLedgerUpdateCode;
    @Column(name = "NEGATIVE_INV_RECEIPT_CODE")
    private Integer negativeInvReceiptCode;
       @OneToOne
    @JoinColumn(name = "MATERIAL_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination materialAccount;
    @OneToOne
    @JoinColumn(name = "MATERIAL_OVERHEAD_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination materialOverheadAccount;
    @Column(name = "MATL_OVHD_ABSORPTION_ACCT")
    private Integer matlOvhdAbsorptionAcct;
    @OneToOne
    @JoinColumn(name = "RESOURCE_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination resourceAccount;
  @OneToOne
    @JoinColumn(name = "PURCHASE_PRICE_VAR_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination purchasePriceVarAccount;
    @OneToOne
    @JoinColumn(name = "AP_ACCRUAL_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination apAccrualAccount;
  @OneToOne
    @JoinColumn(name = "OVERHEAD_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination overheadAccount;
      @OneToOne
    @JoinColumn(name = "OUTSIDE_PROCESSING_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination outsideProcessingAccount;
    @OneToOne
    @JoinColumn(name = "INTRANSIT_INV_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination intransitInvAccount;
   @OneToOne
    @JoinColumn(name = "INTERORG_RECEIVABLES_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination interorgReceivablesAccount;
     @OneToOne
    @JoinColumn(name = "INTERORG_PRICE_VAR_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination interorgPriceVarAccount;
   @OneToOne
    @JoinColumn(name = "INTERORG_PAYABLES_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination interorgPayablesAccount;
        @OneToOne
    @JoinColumn(name = "COST_OF_SALES_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination costOfSalesAccount;
    @OneToOne
    @JoinColumn(name = "ENCUMBRANCE_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination encumbranceAccount;
    @Column(name = "PROJECT_COST_ACCOUNT")
    private Integer projectCostAccount;
       @OneToOne
    @JoinColumn(name = "INTERORG_TRANSFER_CR_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination interorgTransferCrAccount;
    @Column(name = "MATL_INTERORG_TRANSFER_CODE")
    private TransferChargeType matlInterorgTransferCode;
    @Column(name = "INTERORG_TRNSFR_CHARGE_PERCENT")
    private Integer interorgTrnsfrChargePercent;
    @Column(name = "SOURCE_ORGANIZATION_ID")
    private Integer sourceOrganizationId;
    @Size(max = 10)
    @Column(name = "SOURCE_SUBINVENTORY")
    private String sourceSubinventory;
    @Column(name = "SOURCE_TYPE")
    private SourceType sourceType;
    @Column(name = "ORG_MAX_WEIGHT")
    private Integer orgMaxWeight;
    @Size(max = 3)
    @Column(name = "ORG_MAX_WEIGHT_UOM_CODE")
    private String orgMaxWeightUomCode;
    @Column(name = "ORG_MAX_VOLUME")
    private Integer orgMaxVolume;
    @Size(max = 3)
    @Column(name = "ORG_MAX_VOLUME_UOM_CODE")
    private String orgMaxVolumeUomCode;
    @Column(name = "SERIAL_NUMBER_TYPE")
    private SerialUniqueType serialNumberType;
    @Size(max = 30)
    @Column(name = "AUTO_SERIAL_ALPHA_PREFIX")
    private String autoSerialAlphaPrefix;
    @Size(max = 30)
    @Column(name = "START_AUTO_SERIAL_NUMBER")
    private String startAutoSerialNumber;
    @Size(max = 30)
    @Column(name = "AUTO_LOT_ALPHA_PREFIX")
    private String autoLotAlphaPrefix;
    @Column(name = "LOT_NUMBER_UNIQUENESS")
    private LotUniqueType lotNumberUniqueness;
    @Column(name = "LOT_NUMBER_GENERATION")
    private LotGenerationType lotNumberGeneration;
    @Column(name = "LOT_NUMBER_ZERO_PADDING")
    private boolean lotNumberZeroPadding;
    @Column(name = "LOT_NUMBER_LENGTH")
    private Integer lotNumberLength;
    @Size(max = 3)
    @Column(name = "STARTING_REVISION")
    private String startingRevision;
    @Column(name = "ENCUMBRANCE_REVERSAL_FLAG")
    private boolean encumbranceReversalFlag;
    @Column(name = "MAINTAIN_FIFO_QTY_STACK_TYPE")
    private Integer maintainFifoQtyStackType;
     @OneToOne
    @JoinColumn(name = "INVOICE_PRICE_VAR_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination invoicePriceVarAccount;
    @Column(name = "AVERAGE_COST_VAR_ACCOUNT")
    private Integer averageCostVarAccount;
    @OneToOne
    @JoinColumn(name = "EXPENSE_ACCOUNT",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination expenseAccount;
    @Column(name = "SERIAL_NUMBER_GENERATION")
    private SerialGenerationType serialNumberGeneration;
    @Column(name = "MAT_OVHD_COST_TYPE_ID")
    private Integer matOvhdCostTypeId;
    @Column(name = "PROJECT_REFERENCE_ENABLED")
    private boolean projectReferenceEnabled;
    @Column(name = "PM_COST_COLLECTION_ENABLED")
    private Integer pmCostCollectionEnabled;
    @Column(name = "PROJECT_CONTROL_LEVEL")
    private Integer projectControlLevel;
    @Column(name = "AVG_RATES_COST_TYPE_ID")
    private Integer avgRatesCostTypeId;
    @Column(name = "TXN_APPROVAL_TIMEOUT_PERIOD")
    private Integer txnApprovalTimeoutPeriod;
    @Column(name = "MO_SOURCE_REQUIRED")
    private Integer moSourceRequired;
    @Column(name = "MO_PICK_CONFIRM_REQUIRED")
    private Integer moPickConfirmRequired;
    @Column(name = "MO_APPROVAL_TIMEOUT_ACTION")
    private Integer moApprovalTimeoutAction;
    @Column(name = "BORRPAY_MATL_VAR_ACCOUNT")
    private Integer borrpayMatlVarAccount;
    @Column(name = "BORRPAY_MOH_VAR_ACCOUNT")
    private Integer borrpayMohVarAccount;
    @Column(name = "BORRPAY_RES_VAR_ACCOUNT")
    private Integer borrpayResVarAccount;
    @Column(name = "BORRPAY_OSP_VAR_ACCOUNT")
    private Integer borrpayOspVarAccount;
    @Column(name = "BORRPAY_OVH_VAR_ACCOUNT")
    private Integer borrpayOvhVarAccount;
    @Column(name = "PROCESS_ENABLED_FLAG")
    private boolean processEnabledFlag;
    @Size(max = 4)
    @Column(name = "PROCESS_ORGN_CODE")
    private String processOrgnCode;
    @Column(name = "WSM_ENABLED_FLAG")
    private boolean wsmEnabledFlag;
    @Column(name = "DEFAULT_COST_GROUP_ID")
    private Integer defaultCostGroupId;
    @Column(name = "WMS_ENABLED_FLAG")
    private boolean wmsEnabledFlag;
    @Column(name = "PREGEN_PUTAWAY_TASKS_FLAG")
    private boolean pregenPutawayTasksFlag;
    @Column(name = "REGENERATION_INTERVAL")
    private Integer regenerationInterval;
    @Column(name = "TIMEZONE_ID")
    private Integer timezoneId;
    @Column(name = "MAX_PICKS_BATCH")
    private Integer maxPicksBatch;
    @Column(name = "DEFAULT_WMS_PICKING_RULE_ID")
    private Integer defaultWmsPickingRuleId;
    @Column(name = "DEFAULT_PUT_AWAY_RULE_ID")
    private Integer defaultPutAwayRuleId;
    @Column(name = "CARTONIZATION_FLAG")
    private boolean cartonizationFlag;
    @Column(name = "ENABLE_COSTING_BY_CATEGORY")
    private Integer enableCostingByCategory;
    @Column(name = "COST_GROUP_ACCOUNTING")
    private Integer costGroupAccounting;
    @Column(name = "ALLOCATE_SERIAL_FLAG")
    private boolean allocateSerialFlag;
    @Column(name = "MAINT_ORGANIZATION_ID")
    private Integer maintOrganizationId;
    @Column(name = "DISTRIBUTED_ORGANIZATION_FLAG")
    private boolean distributedOrganizationFlag;
    @Column(name = "DISTRIBUTION_ACCOUNT_ID")
    private Integer distributionAccountId;
    @Column(name = "DIRECT_SHIPPING_ALLOWED")
    private Integer directShippingAllowed;
    @Column(name = "DEFAULT_PICK_OP_PLAN_ID")
    private Integer defaultPickOpPlanId;
    @Column(name = "MAX_CLUSTERS_ALLOWED")
    private Integer maxClustersAllowed;
    @Column(name = "CONSIGNED_FLAG")
    private boolean consignedFlag;
    @Column(name = "CARTONIZE_SALES_ORDERS")
    private Integer cartonizeSalesOrders;
    @Column(name = "CARTONIZE_MANUFACTURING")
    private Integer cartonizeManufacturing;
    @Column(name = "DEFER_LOGICAL_TRANSACTIONS")
    private boolean deferLogicalTransactions;
    @Column(name = "DEFERRED_COGS_ACCOUNT")
    private Integer deferredCogsAccount;
    @Size(max = 30)
    @Column(name = "COMPANY_PREFIX")
    private String companyPrefix;
    @Size(max = 30)
    @Column(name = "COMPANY_PREFIX_INDEX")
    private String companyPrefixIndex;
    @Size(max = 30)
    @Column(name = "COMMERCIAL_GOVT_ENTITY_NUMBER")
    private String commercialGovtEntityNumber;
    @Column(name = "DEFAULT_STATUS_ID")
    private Integer defaultStatusId;
    @Column(name = "ALLOCATE_LOT_FLAG")
    private boolean allocateLotFlag;
    @Column(name = "DAILY_CAL_START_TIME")
    private Integer dailyCalStartTime;
    @Column(name = "DAILY_CAL_END_TIME")
    private Integer dailyCalEndTime;
    @Size(max = 250)
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Size(max = 30)
    @Column(name = "DEMAND_CLASS")
    private String demandClass;
    @Column(name="LOCATOR_CONTROL_TYPE_ID")
    private ControlType locatorControlType;
     @OneToOne
    @JoinColumn(name = "ORG_MAX_WEIGHT_UOM_CODE_ID", referencedColumnName = "UOM_ID")
    private UomLine weightUnitMeasure;
  @OneToOne
    @JoinColumn(name = "ORG_MAX_VOLUME_UOM_CODE_ID", referencedColumnName = "UOM_ID")
    private UomLine volumeUnitMeasure;
    @Column(name = "TRANSFER_TO_GL_FLAG_ID")
    private Integer transferToGlFlagId;
    @Column(name = "ENCUMBRANCE_REVERSAL_FLAG_ID")
    private Integer encumbranceReversalFlagId;
    @Column(name = "ALLOCATE_SERIAL_FLAG_ID")
    private Integer allocateSerialFlagId;
    @Column(name = "SOURCE_SUBINVENTORY_ID")
    private Integer sourceSubinventoryId;
    @Column(name = "MATL_INTERORG_TRANSFER_CODE_ID")
    private Integer matlInterorgTransferCodeId;
    @Column(name = "TRANSFER_TO_GL_FLAG")
    private boolean transferToGlFlag;
    @Column(name = "GL_ID")
    private Integer glId;

    public InventoryConfguration() {
    }

    public InventoryConfguration(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationUnit getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(OrganizationUnit operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public CostMethod getPrimaryCostMethod() {
        return primaryCostMethod;
    }

    public void setPrimaryCostMethod(CostMethod primaryCostMethod) {
        this.primaryCostMethod = primaryCostMethod;
    }

    public Integer getCostOrganizationId() {
        return costOrganizationId;
    }

    public void setCostOrganizationId(Integer costOrganizationId) {
        this.costOrganizationId = costOrganizationId;
    }

    public Integer getDefaultMaterialCostId() {
        return defaultMaterialCostId;
    }

    public void setDefaultMaterialCostId(Integer defaultMaterialCostId) {
        this.defaultMaterialCostId = defaultMaterialCostId;
    }

    public String getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    public Integer getGeneralLedgerUpdateCode() {
        return generalLedgerUpdateCode;
    }

    public void setGeneralLedgerUpdateCode(Integer generalLedgerUpdateCode) {
        this.generalLedgerUpdateCode = generalLedgerUpdateCode;
    }

    public Integer getNegativeInvReceiptCode() {
        return negativeInvReceiptCode;
    }

    public void setNegativeInvReceiptCode(Integer negativeInvReceiptCode) {
        this.negativeInvReceiptCode = negativeInvReceiptCode;
    }

    public GlCodeCombination getMaterialAccount() {
        return materialAccount;
    }

    public void setMaterialAccount(GlCodeCombination materialAccount) {
        this.materialAccount = materialAccount;
    }

    public GlCodeCombination getMaterialOverheadAccount() {
        return materialOverheadAccount;
    }

    public void setMaterialOverheadAccount(GlCodeCombination materialOverheadAccount) {
        this.materialOverheadAccount = materialOverheadAccount;
    }

    public Integer getMatlOvhdAbsorptionAcct() {
        return matlOvhdAbsorptionAcct;
    }

    public void setMatlOvhdAbsorptionAcct(Integer matlOvhdAbsorptionAcct) {
        this.matlOvhdAbsorptionAcct = matlOvhdAbsorptionAcct;
    }

    public GlCodeCombination getResourceAccount() {
        return resourceAccount;
    }

    public void setResourceAccount(GlCodeCombination resourceAccount) {
        this.resourceAccount = resourceAccount;
    }

    public GlCodeCombination getPurchasePriceVarAccount() {
        return purchasePriceVarAccount;
    }

    public void setPurchasePriceVarAccount(GlCodeCombination purchasePriceVarAccount) {
        this.purchasePriceVarAccount = purchasePriceVarAccount;
    }

    public GlCodeCombination getApAccrualAccount() {
        return apAccrualAccount;
    }

    public void setApAccrualAccount(GlCodeCombination apAccrualAccount) {
        this.apAccrualAccount = apAccrualAccount;
    }

    public GlCodeCombination getOverheadAccount() {
        return overheadAccount;
    }

    public void setOverheadAccount(GlCodeCombination overheadAccount) {
        this.overheadAccount = overheadAccount;
    }

    public GlCodeCombination getOutsideProcessingAccount() {
        return outsideProcessingAccount;
    }

    public void setOutsideProcessingAccount(GlCodeCombination outsideProcessingAccount) {
        this.outsideProcessingAccount = outsideProcessingAccount;
    }

    public GlCodeCombination getIntransitInvAccount() {
        return intransitInvAccount;
    }

    public void setIntransitInvAccount(GlCodeCombination intransitInvAccount) {
        this.intransitInvAccount = intransitInvAccount;
    }

    public GlCodeCombination getInterorgReceivablesAccount() {
        return interorgReceivablesAccount;
    }

    public void setInterorgReceivablesAccount(GlCodeCombination interorgReceivablesAccount) {
        this.interorgReceivablesAccount = interorgReceivablesAccount;
    }

    public GlCodeCombination getInterorgPriceVarAccount() {
        return interorgPriceVarAccount;
    }

    public void setInterorgPriceVarAccount(GlCodeCombination interorgPriceVarAccount) {
        this.interorgPriceVarAccount = interorgPriceVarAccount;
    }

    public GlCodeCombination getInterorgPayablesAccount() {
        return interorgPayablesAccount;
    }

    public void setInterorgPayablesAccount(GlCodeCombination interorgPayablesAccount) {
        this.interorgPayablesAccount = interorgPayablesAccount;
    }

    public GlCodeCombination getCostOfSalesAccount() {
        return costOfSalesAccount;
    }

    public void setCostOfSalesAccount(GlCodeCombination costOfSalesAccount) {
        this.costOfSalesAccount = costOfSalesAccount;
    }

    public GlCodeCombination getEncumbranceAccount() {
        return encumbranceAccount;
    }

    public void setEncumbranceAccount(GlCodeCombination encumbranceAccount) {
        this.encumbranceAccount = encumbranceAccount;
    }

    public Integer getProjectCostAccount() {
        return projectCostAccount;
    }

    public void setProjectCostAccount(Integer projectCostAccount) {
        this.projectCostAccount = projectCostAccount;
    }

    public GlCodeCombination getInterorgTransferCrAccount() {
        return interorgTransferCrAccount;
    }

    public void setInterorgTransferCrAccount(GlCodeCombination interorgTransferCrAccount) {
        this.interorgTransferCrAccount = interorgTransferCrAccount;
    }

    public TransferChargeType getMatlInterorgTransferCode() {
        return matlInterorgTransferCode;
    }

    public void setMatlInterorgTransferCode(TransferChargeType matlInterorgTransferCode) {
        this.matlInterorgTransferCode = matlInterorgTransferCode;
    }

    public Integer getInterorgTrnsfrChargePercent() {
        return interorgTrnsfrChargePercent;
    }

    public void setInterorgTrnsfrChargePercent(Integer interorgTrnsfrChargePercent) {
        this.interorgTrnsfrChargePercent = interorgTrnsfrChargePercent;
    }

    public Integer getSourceOrganizationId() {
        return sourceOrganizationId;
    }

    public void setSourceOrganizationId(Integer sourceOrganizationId) {
        this.sourceOrganizationId = sourceOrganizationId;
    }

    public String getSourceSubinventory() {
        return sourceSubinventory;
    }

    public void setSourceSubinventory(String sourceSubinventory) {
        this.sourceSubinventory = sourceSubinventory;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getOrgMaxWeight() {
        return orgMaxWeight;
    }

    public void setOrgMaxWeight(Integer orgMaxWeight) {
        this.orgMaxWeight = orgMaxWeight;
    }

    public String getOrgMaxWeightUomCode() {
        return orgMaxWeightUomCode;
    }

    public void setOrgMaxWeightUomCode(String orgMaxWeightUomCode) {
        this.orgMaxWeightUomCode = orgMaxWeightUomCode;
    }

    public Integer getOrgMaxVolume() {
        return orgMaxVolume;
    }

    public void setOrgMaxVolume(Integer orgMaxVolume) {
        this.orgMaxVolume = orgMaxVolume;
    }

    public String getOrgMaxVolumeUomCode() {
        return orgMaxVolumeUomCode;
    }

    public void setOrgMaxVolumeUomCode(String orgMaxVolumeUomCode) {
        this.orgMaxVolumeUomCode = orgMaxVolumeUomCode;
    }

    public SerialUniqueType getSerialNumberType() {
        return serialNumberType;
    }

    public void setSerialNumberType(SerialUniqueType serialNumberType) {
        this.serialNumberType = serialNumberType;
    }

    public String getAutoSerialAlphaPrefix() {
        return autoSerialAlphaPrefix;
    }

    public void setAutoSerialAlphaPrefix(String autoSerialAlphaPrefix) {
        this.autoSerialAlphaPrefix = autoSerialAlphaPrefix;
    }

    public String getStartAutoSerialNumber() {
        return startAutoSerialNumber;
    }

    public void setStartAutoSerialNumber(String startAutoSerialNumber) {
        this.startAutoSerialNumber = startAutoSerialNumber;
    }

    public String getAutoLotAlphaPrefix() {
        return autoLotAlphaPrefix;
    }

    public void setAutoLotAlphaPrefix(String autoLotAlphaPrefix) {
        this.autoLotAlphaPrefix = autoLotAlphaPrefix;
    }

    public LotUniqueType getLotNumberUniqueness() {
        return lotNumberUniqueness;
    }

    public void setLotNumberUniqueness(LotUniqueType lotNumberUniqueness) {
        this.lotNumberUniqueness = lotNumberUniqueness;
    }

    public LotGenerationType getLotNumberGeneration() {
        return lotNumberGeneration;
    }

    public void setLotNumberGeneration(LotGenerationType lotNumberGeneration) {
        this.lotNumberGeneration = lotNumberGeneration;
    }

    public boolean getLotNumberZeroPadding() {
        return lotNumberZeroPadding;
    }

    public void setLotNumberZeroPadding(boolean lotNumberZeroPadding) {
        this.lotNumberZeroPadding = lotNumberZeroPadding;
    }

    public Integer getLotNumberLength() {
        return lotNumberLength;
    }

    public void setLotNumberLength(Integer lotNumberLength) {
        this.lotNumberLength = lotNumberLength;
    }

    public String getStartingRevision() {
        return startingRevision;
    }

    public void setStartingRevision(String startingRevision) {
        this.startingRevision = startingRevision;
    }

    public boolean getEncumbranceReversalFlag() {
        return encumbranceReversalFlag;
    }

    public void setEncumbranceReversalFlag(boolean encumbranceReversalFlag) {
        this.encumbranceReversalFlag = encumbranceReversalFlag;
    }

    public Integer getMaintainFifoQtyStackType() {
        return maintainFifoQtyStackType;
    }

    public void setMaintainFifoQtyStackType(Integer maintainFifoQtyStackType) {
        this.maintainFifoQtyStackType = maintainFifoQtyStackType;
    }

    public GlCodeCombination getInvoicePriceVarAccount() {
        return invoicePriceVarAccount;
    }

    public void setInvoicePriceVarAccount(GlCodeCombination invoicePriceVarAccount) {
        this.invoicePriceVarAccount = invoicePriceVarAccount;
    }

    public Integer getAverageCostVarAccount() {
        return averageCostVarAccount;
    }

    public void setAverageCostVarAccount(Integer averageCostVarAccount) {
        this.averageCostVarAccount = averageCostVarAccount;
    }

    public GlCodeCombination getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(GlCodeCombination expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    public SerialGenerationType getSerialNumberGeneration() {
        return serialNumberGeneration;
    }

    public void setSerialNumberGeneration(SerialGenerationType serialNumberGeneration) {
        this.serialNumberGeneration = serialNumberGeneration;
    }

    public Integer getMatOvhdCostTypeId() {
        return matOvhdCostTypeId;
    }

    public void setMatOvhdCostTypeId(Integer matOvhdCostTypeId) {
        this.matOvhdCostTypeId = matOvhdCostTypeId;
    }

    public boolean getProjectReferenceEnabled() {
        return projectReferenceEnabled;
    }

    public void setProjectReferenceEnabled(boolean projectReferenceEnabled) {
        this.projectReferenceEnabled = projectReferenceEnabled;
    }

    public Integer getPmCostCollectionEnabled() {
        return pmCostCollectionEnabled;
    }

    public void setPmCostCollectionEnabled(Integer pmCostCollectionEnabled) {
        this.pmCostCollectionEnabled = pmCostCollectionEnabled;
    }

    public Integer getProjectControlLevel() {
        return projectControlLevel;
    }

    public void setProjectControlLevel(Integer projectControlLevel) {
        this.projectControlLevel = projectControlLevel;
    }

    public Integer getAvgRatesCostTypeId() {
        return avgRatesCostTypeId;
    }

    public void setAvgRatesCostTypeId(Integer avgRatesCostTypeId) {
        this.avgRatesCostTypeId = avgRatesCostTypeId;
    }

    public Integer getTxnApprovalTimeoutPeriod() {
        return txnApprovalTimeoutPeriod;
    }

    public void setTxnApprovalTimeoutPeriod(Integer txnApprovalTimeoutPeriod) {
        this.txnApprovalTimeoutPeriod = txnApprovalTimeoutPeriod;
    }

    public Integer getMoSourceRequired() {
        return moSourceRequired;
    }

    public void setMoSourceRequired(Integer moSourceRequired) {
        this.moSourceRequired = moSourceRequired;
    }

    public Integer getMoPickConfirmRequired() {
        return moPickConfirmRequired;
    }

    public void setMoPickConfirmRequired(Integer moPickConfirmRequired) {
        this.moPickConfirmRequired = moPickConfirmRequired;
    }

    public Integer getMoApprovalTimeoutAction() {
        return moApprovalTimeoutAction;
    }

    public void setMoApprovalTimeoutAction(Integer moApprovalTimeoutAction) {
        this.moApprovalTimeoutAction = moApprovalTimeoutAction;
    }

    public Integer getBorrpayMatlVarAccount() {
        return borrpayMatlVarAccount;
    }

    public void setBorrpayMatlVarAccount(Integer borrpayMatlVarAccount) {
        this.borrpayMatlVarAccount = borrpayMatlVarAccount;
    }

    public Integer getBorrpayMohVarAccount() {
        return borrpayMohVarAccount;
    }

    public void setBorrpayMohVarAccount(Integer borrpayMohVarAccount) {
        this.borrpayMohVarAccount = borrpayMohVarAccount;
    }

    public Integer getBorrpayResVarAccount() {
        return borrpayResVarAccount;
    }

    public void setBorrpayResVarAccount(Integer borrpayResVarAccount) {
        this.borrpayResVarAccount = borrpayResVarAccount;
    }

    public Integer getBorrpayOspVarAccount() {
        return borrpayOspVarAccount;
    }

    public void setBorrpayOspVarAccount(Integer borrpayOspVarAccount) {
        this.borrpayOspVarAccount = borrpayOspVarAccount;
    }

    public Integer getBorrpayOvhVarAccount() {
        return borrpayOvhVarAccount;
    }

    public void setBorrpayOvhVarAccount(Integer borrpayOvhVarAccount) {
        this.borrpayOvhVarAccount = borrpayOvhVarAccount;
    }

    public boolean getProcessEnabledFlag() {
        return processEnabledFlag;
    }

    public void setProcessEnabledFlag(boolean processEnabledFlag) {
        this.processEnabledFlag = processEnabledFlag;
    }

    public String getProcessOrgnCode() {
        return processOrgnCode;
    }

    public void setProcessOrgnCode(String processOrgnCode) {
        this.processOrgnCode = processOrgnCode;
    }

    public boolean getWsmEnabledFlag() {
        return wsmEnabledFlag;
    }

    public void setWsmEnabledFlag(boolean wsmEnabledFlag) {
        this.wsmEnabledFlag = wsmEnabledFlag;
    }

    public Integer getDefaultCostGroupId() {
        return defaultCostGroupId;
    }

    public void setDefaultCostGroupId(Integer defaultCostGroupId) {
        this.defaultCostGroupId = defaultCostGroupId;
    }

    public boolean getWmsEnabledFlag() {
        return wmsEnabledFlag;
    }

    public void setWmsEnabledFlag(boolean wmsEnabledFlag) {
        this.wmsEnabledFlag = wmsEnabledFlag;
    }

    public boolean getPregenPutawayTasksFlag() {
        return pregenPutawayTasksFlag;
    }

    public void setPregenPutawayTasksFlag(boolean pregenPutawayTasksFlag) {
        this.pregenPutawayTasksFlag = pregenPutawayTasksFlag;
    }

    public Integer getRegenerationInterval() {
        return regenerationInterval;
    }

    public void setRegenerationInterval(Integer regenerationInterval) {
        this.regenerationInterval = regenerationInterval;
    }

    public Integer getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(Integer timezoneId) {
        this.timezoneId = timezoneId;
    }

    public Integer getMaxPicksBatch() {
        return maxPicksBatch;
    }

    public void setMaxPicksBatch(Integer maxPicksBatch) {
        this.maxPicksBatch = maxPicksBatch;
    }

    public Integer getDefaultWmsPickingRuleId() {
        return defaultWmsPickingRuleId;
    }

    public void setDefaultWmsPickingRuleId(Integer defaultWmsPickingRuleId) {
        this.defaultWmsPickingRuleId = defaultWmsPickingRuleId;
    }

    public Integer getDefaultPutAwayRuleId() {
        return defaultPutAwayRuleId;
    }

    public void setDefaultPutAwayRuleId(Integer defaultPutAwayRuleId) {
        this.defaultPutAwayRuleId = defaultPutAwayRuleId;
    }

    public boolean getCartonizationFlag() {
        return cartonizationFlag;
    }

    public void setCartonizationFlag(boolean cartonizationFlag) {
        this.cartonizationFlag = cartonizationFlag;
    }

    public Integer getEnableCostingByCategory() {
        return enableCostingByCategory;
    }

    public void setEnableCostingByCategory(Integer enableCostingByCategory) {
        this.enableCostingByCategory = enableCostingByCategory;
    }

    public Integer getCostGroupAccounting() {
        return costGroupAccounting;
    }

    public void setCostGroupAccounting(Integer costGroupAccounting) {
        this.costGroupAccounting = costGroupAccounting;
    }

    public boolean getAllocateSerialFlag() {
        return allocateSerialFlag;
    }

    public void setAllocateSerialFlag(boolean allocateSerialFlag) {
        this.allocateSerialFlag = allocateSerialFlag;
    }

    public Integer getMaintOrganizationId() {
        return maintOrganizationId;
    }

    public void setMaintOrganizationId(Integer maintOrganizationId) {
        this.maintOrganizationId = maintOrganizationId;
    }

    public boolean getDistributedOrganizationFlag() {
        return distributedOrganizationFlag;
    }

    public void setDistributedOrganizationFlag(boolean distributedOrganizationFlag) {
        this.distributedOrganizationFlag = distributedOrganizationFlag;
    }

    public Integer getDistributionAccountId() {
        return distributionAccountId;
    }

    public void setDistributionAccountId(Integer distributionAccountId) {
        this.distributionAccountId = distributionAccountId;
    }

    public Integer getDirectShippingAllowed() {
        return directShippingAllowed;
    }

    public void setDirectShippingAllowed(Integer directShippingAllowed) {
        this.directShippingAllowed = directShippingAllowed;
    }

    public Integer getDefaultPickOpPlanId() {
        return defaultPickOpPlanId;
    }

    public void setDefaultPickOpPlanId(Integer defaultPickOpPlanId) {
        this.defaultPickOpPlanId = defaultPickOpPlanId;
    }

    public Integer getMaxClustersAllowed() {
        return maxClustersAllowed;
    }

    public void setMaxClustersAllowed(Integer maxClustersAllowed) {
        this.maxClustersAllowed = maxClustersAllowed;
    }

    public boolean getConsignedFlag() {
        return consignedFlag;
    }

    public void setConsignedFlag(boolean consignedFlag) {
        this.consignedFlag = consignedFlag;
    }

    public Integer getCartonizeSalesOrders() {
        return cartonizeSalesOrders;
    }

    public void setCartonizeSalesOrders(Integer cartonizeSalesOrders) {
        this.cartonizeSalesOrders = cartonizeSalesOrders;
    }

    public Integer getCartonizeManufacturing() {
        return cartonizeManufacturing;
    }

    public void setCartonizeManufacturing(Integer cartonizeManufacturing) {
        this.cartonizeManufacturing = cartonizeManufacturing;
    }

    public boolean getDeferLogicalTransactions() {
        return deferLogicalTransactions;
    }

    public void setDeferLogicalTransactions(boolean deferLogicalTransactions) {
        this.deferLogicalTransactions = deferLogicalTransactions;
    }

    public Integer getDeferredCogsAccount() {
        return deferredCogsAccount;
    }

    public void setDeferredCogsAccount(Integer deferredCogsAccount) {
        this.deferredCogsAccount = deferredCogsAccount;
    }

    public String getCompanyPrefix() {
        return companyPrefix;
    }

    public void setCompanyPrefix(String companyPrefix) {
        this.companyPrefix = companyPrefix;
    }

    public String getCompanyPrefixIndex() {
        return companyPrefixIndex;
    }

    public void setCompanyPrefixIndex(String companyPrefixIndex) {
        this.companyPrefixIndex = companyPrefixIndex;
    }

    public String getCommercialGovtEntityNumber() {
        return commercialGovtEntityNumber;
    }

    public void setCommercialGovtEntityNumber(String commercialGovtEntityNumber) {
        this.commercialGovtEntityNumber = commercialGovtEntityNumber;
    }

    public Integer getDefaultStatusId() {
        return defaultStatusId;
    }

    public void setDefaultStatusId(Integer defaultStatusId) {
        this.defaultStatusId = defaultStatusId;
    }

    public boolean getAllocateLotFlag() {
        return allocateLotFlag;
    }

    public void setAllocateLotFlag(boolean allocateLotFlag) {
        this.allocateLotFlag = allocateLotFlag;
    }

    public Integer getDailyCalStartTime() {
        return dailyCalStartTime;
    }

    public void setDailyCalStartTime(Integer dailyCalStartTime) {
        this.dailyCalStartTime = dailyCalStartTime;
    }

    public Integer getDailyCalEndTime() {
        return dailyCalEndTime;
    }

    public void setDailyCalEndTime(Integer dailyCalEndTime) {
        this.dailyCalEndTime = dailyCalEndTime;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDemandClass() {
        return demandClass;
    }

    public void setDemandClass(String demandClass) {
        this.demandClass = demandClass;
    }

    public ControlType getLocatorControlType() {
        return locatorControlType;
    }

    public void setLocatorControlType(ControlType locatorControlType) {
        this.locatorControlType = locatorControlType;
    }

 

    public UomLine getWeightUnitMeasure() {
        return weightUnitMeasure;
    }

    public void setWeightUnitMeasure(UomLine weightUnitMeasure) {
        this.weightUnitMeasure = weightUnitMeasure;
    }

    public UomLine getVolumeUnitMeasure() {
        return volumeUnitMeasure;
    }

    public void setVolumeUnitMeasure(UomLine volumeUnitMeasure) {
        this.volumeUnitMeasure = volumeUnitMeasure;
    }

  



    public Integer getTransferToGlFlagId() {
        return transferToGlFlagId;
    }

    public void setTransferToGlFlagId(Integer transferToGlFlagId) {
        this.transferToGlFlagId = transferToGlFlagId;
    }

    public Integer getEncumbranceReversalFlagId() {
        return encumbranceReversalFlagId;
    }

    public void setEncumbranceReversalFlagId(Integer encumbranceReversalFlagId) {
        this.encumbranceReversalFlagId = encumbranceReversalFlagId;
    }

    public Integer getAllocateSerialFlagId() {
        return allocateSerialFlagId;
    }

    public void setAllocateSerialFlagId(Integer allocateSerialFlagId) {
        this.allocateSerialFlagId = allocateSerialFlagId;
    }

    public Integer getSourceSubinventoryId() {
        return sourceSubinventoryId;
    }

    public void setSourceSubinventoryId(Integer sourceSubinventoryId) {
        this.sourceSubinventoryId = sourceSubinventoryId;
    }

    public Integer getMatlInterorgTransferCodeId() {
        return matlInterorgTransferCodeId;
    }

    public void setMatlInterorgTransferCodeId(Integer matlInterorgTransferCodeId) {
        this.matlInterorgTransferCodeId = matlInterorgTransferCodeId;
    }

    public boolean getTransferToGlFlag() {
        return transferToGlFlag;
    }

    public void setTransferToGlFlag(boolean transferToGlFlag) {
        this.transferToGlFlag = transferToGlFlag;
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryConfguration)) {
            return false;
        }
        InventoryConfguration other = (InventoryConfguration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.inventory.InventoryConfguration[ id=" + id + " ]";
    }
    
}
