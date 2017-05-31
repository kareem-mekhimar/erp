/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * @author kareem
 */
@Entity
@Table(name = "INV_MATERIAL_TRANSACTIONS")
@NamedQueries({
    @NamedQuery(name = "InvMaterialTransaction.findAll", query = "SELECT i FROM InvMaterialTransaction i")})
public class InvMaterialTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "tx")
    @TableGenerator(name = "tx",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Tx",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    
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
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;

    @ManyToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID")
    private SystemItem inventoryItem;

   
    @Size(max = 10)
    @Column(name = "SUBINVENTORY_CODE")
    private String subinventoryCode;
    @Column(name = "LOCATOR_ID")
    private Integer locatorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_TYPE_ID")
    private TxTransactionType transactionType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_ACTION_ID")
    private Integer transactionActionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_SOURCE_TYPE_ID")
    private Integer transactionSourceTypeId;
    @Column(name = "TRANSACTION_SOURCE_ID")
    private Integer transactionSourceId;
    @Size(max = 80)
    @Column(name = "TRANSACTION_SOURCE_NAME")
    private String transactionSourceName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_QUANTITY")
    private BigDecimal transactionQuantity;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TRANSACTION_UOM")
    private String transactionUom;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIMARY_QUANTITY")
    private BigDecimal primaryQuantity;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();
    @Column(name = "VARIANCE_AMOUNT")
    private Integer varianceAmount;
    @Column(name = "ACCT_PERIOD_ID")
    private Integer acctPeriodId;
    @Size(max = 240)
    @Column(name = "TRANSACTION_REFERENCE")
    private String transactionReference;
    @Column(name = "REASON_ID")
    private Integer reasonId;
    @Column(name = "DISTRIBUTION_ACCOUNT_ID")
    private Integer distributionAccountId;
    @Column(name = "ENCUMBRANCE_ACCOUNT")
    private Integer encumbranceAccount;
    @Column(name = "ENCUMBRANCE_AMOUNT")
    private Integer encumbranceAmount;
    @Column(name = "COST_UPDATE_ID")
    private Integer costUpdateId;
    @Size(max = 1)
    @Column(name = "COSTED_FLAG")
    private String costedFlag;
    @Column(name = "TRANSACTION_GROUP_ID")
    private Integer transactionGroupId;
    @Size(max = 1)
    @Column(name = "INVOICED_FLAG")
    private String invoicedFlag;
    @Column(name = "ACTUAL_COST")
    private Integer actualCost;
    @Column(name = "TRANSACTION_COST")
    private Integer transactionCost;
    @Column(name = "PRIOR_COST")
    private Integer priorCost;
    @Column(name = "NEW_COST")
    private Integer newCost;
    @Size(max = 10)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "CURRENCY_CONVERSION_RATE")
    private Integer currencyConversionRate;
    @Size(max = 30)
    @Column(name = "CURRENCY_CONVERSION_TYPE")
    private String currencyConversionType;
    @Column(name = "CURRENCY_CONVERSION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currencyConversionDate;
    @Column(name = "QUANTITY_ADJUSTED")
    private Integer quantityAdjusted;
    @Size(max = 10)
    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;
    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;
    @Column(name = "OPERATION_SEQ_NUM")
    private Integer operationSeqNum;
    @Size(max = 10)
    @Column(name = "RECEIVING_DOCUMENT")
    private String receivingDocument;
    @Column(name = "PICKING_LINE_ID")
    private Integer pickingLineId;
    @Column(name = "TRX_SOURCE_LINE_ID")
    private Integer trxSourceLineId;
    @Column(name = "TRX_SOURCE_DELIVERY_ID")
    private Integer trxSourceDeliveryId;
    @Column(name = "PHYSICAL_ADJUSTMENT_ID")
    private Integer physicalAdjustmentId;
    @Column(name = "CYCLE_COUNT_ID")
    private Integer cycleCountId;
    @Column(name = "RMA_LINE_ID")
    private Integer rmaLineId;
    @Column(name = "TRANSFER_TRANSACTION_ID")
    private Integer transferTransactionId;
    @Column(name = "TRANSACTION_SET_ID")
    private Integer transactionSetId;
    @Column(name = "RCV_TRANSACTION_ID")
    private Integer rcvTransactionId;
    @Column(name = "MOVE_TRANSACTION_ID")
    private Integer moveTransactionId;
    @Column(name = "COMPLETION_TRANSACTION_ID")
    private Integer completionTransactionId;
    @Column(name = "SHORTAGE_PROCESS_CODE")
    private Integer shortageProcessCode;
    @Size(max = 30)
    @Column(name = "SOURCE_CODE")
    private String sourceCode;
   
    @ManyToOne
    @JoinColumn(name = "SOURCE_LINE_ID",referencedColumnName = "PO_LINE_ID")
    private PoLine poLine;
    
    @Size(max = 30)
    @Column(name = "VENDOR_LOT_NUMBER")
    private String vendorLotNumber;
    
    @Column(name = "TRANSFER_ORGANIZATION_ID")
    private Integer transferOrganizationId;
    
    @Size(max = 10)
    @Column(name = "TRANSFER_SUBINVENTORY")
    private String transferSubinventory;
    
    @Column(name = "TRANSFER_LOCATOR_ID")
    private Integer transferLocatorId;
    
    @Size(max = 30)
    @Column(name = "SHIPMENT_NUMBER")
    private String shipmentNumber;
    @Column(name = "TRANSFER_COST")
    private Integer transferCost;
    @Column(name = "TRANSPORTATION_DIST_ACCOUNT")
    private Integer transportationDistAccount;
    @Column(name = "TRANSPORTATION_COST")
    private Integer transportationCost;
    @Column(name = "TRANSFER_COST_DIST_ACCOUNT")
    private Integer transferCostDistAccount;
    @Size(max = 30)
    @Column(name = "WAYBILL_AIRBILL")
    private String waybillAirbill;
    @Size(max = 30)
    @Column(name = "FREIGHT_CODE")
    private String freightCode;
    @Column(name = "VALUE_CHANGE")
    private Integer valueChange;
    @Column(name = "PERCENTAGE_CHANGE")
    private Integer percentageChange;
    @Column(name = "MOVEMENT_ID")
    private Integer movementId;
    @Column(name = "TASK_ID")
    private Long taskId;
    @Column(name = "TO_TASK_ID")
    private Long toTaskId;
    @Column(name = "PROJECT_ID")
    private Long projectId;
    @Column(name = "TO_PROJECT_ID")
    private Long toProjectId;
    @Column(name = "SOURCE_PROJECT_ID")
    private Integer sourceProjectId;
    @Column(name = "SOURCE_TASK_ID")
    private Integer sourceTaskId;
    @Size(max = 30)
    @Column(name = "EXPENDITURE_TYPE")
    private String expenditureType;
    @Column(name = "PRIOR_COSTED_QUANTITY")
    private Integer priorCostedQuantity;
    @Size(max = 1)
    @Column(name = "SHIPMENT_COSTED")
    private String shipmentCosted;
    @Column(name = "TRANSFER_PERCENTAGE")
    private Integer transferPercentage;
    @Column(name = "MATERIAL_ACCOUNT")
    private Integer materialAccount;
    @Column(name = "MATERIAL_OVERHEAD_ACCOUNT")
    private Integer materialOverheadAccount;
    @Column(name = "RESOURCE_ACCOUNT")
    private Integer resourceAccount;
    @Column(name = "OUTSIDE_PROCESSING_ACCOUNT")
    private Integer outsideProcessingAccount;
    @Column(name = "OVERHEAD_ACCOUNT")
    private Integer overheadAccount;
    @Column(name = "COST_GROUP_ID")
    private Integer costGroupId;
    @Column(name = "TRANSFER_COST_GROUP_ID")
    private Integer transferCostGroupId;
    @Size(max = 1)
    @Column(name = "FLOW_SCHEDULE")
    private String flowSchedule;
    @Column(name = "QA_COLLECTION_ID")
    private Integer qaCollectionId;
    @Column(name = "COMMON_BOM_SEQ_ID")
    private Integer commonBomSeqId;
    @Column(name = "COMMON_ROUTING_SEQ_ID")
    private Integer commonRoutingSeqId;
    @Column(name = "ORG_COST_GROUP_ID")
    private Integer orgCostGroupId;
    @Column(name = "COST_TYPE_ID")
    private Integer costTypeId;
    @Column(name = "PERIODIC_PRIMARY_QUANTITY")
    private Integer periodicPrimaryQuantity;
    @Column(name = "MOVE_ORDER_LINE_ID")
    private Integer moveOrderLineId;
    @Column(name = "PICK_SLIP_NUMBER")
    private Integer pickSlipNumber;
    @Column(name = "LPN_ID")
    private Integer lpnId;
    @Column(name = "TRANSFER_LPN_ID")
    private Integer transferLpnId;
    @Column(name = "PICK_STRATEGY_ID")
    private Integer pickStrategyId;
    @Column(name = "PICK_RULE_ID")
    private Integer pickRuleId;
    @Column(name = "PUT_AWAY_STRATEGY_ID")
    private Integer putAwayStrategyId;
    @Column(name = "PUT_AWAY_RULE_ID")
    private Integer putAwayRuleId;
    @Column(name = "PICK_SLIP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickSlipDate;
    @Column(name = "COST_CATEGORY_ID")
    private Integer costCategoryId;
    @Column(name = "ORGANIZATION_TYPE")
    private Integer organizationType;
    @Column(name = "TRANSFER_ORGANIZATION_TYPE")
    private Integer transferOrganizationType;
    @Column(name = "OWNING_ORGANIZATION_ID")
    private Integer owningOrganizationId;
    @Column(name = "OWNING_TP_TYPE")
    private Integer owningTpType;
    @Column(name = "PLANNING_ORGANIZATION_ID")
    private Integer planningOrganizationId;
    @Column(name = "PLANNING_TP_TYPE")
    private Integer planningTpType;
    @Size(max = 3)
    @Column(name = "SECONDARY_UOM_CODE")
    private String secondaryUomCode;
    @Column(name = "SECONDARY_TRANSACTION_QUANTITY")
    private Integer secondaryTransactionQuantity;
    @Column(name = "TRANSACTION_GROUP_SEQ")
    private Integer transactionGroupSeq;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Column(name = "RESERVATION_ID")
    private Integer reservationId;
    @Column(name = "TRANSACTION_MODE")
    private Integer transactionMode;
    @Column(name = "TRANSACTION_BATCH_ID")
    private Integer transactionBatchId;
    @Column(name = "TRANSACTION_BATCH_SEQ")
    private Integer transactionBatchSeq;
    @Column(name = "INTRANSIT_ACCOUNT")
    private Integer intransitAccount;
    @Column(name = "FOB_POINT")
    private Integer fobPoint;
    @Column(name = "PARENT_TRANSACTION_ID")
    private Integer parentTransactionId;
    @Column(name = "LOGICAL_TRX_TYPE_CODE")
    private Integer logicalTrxTypeCode;
    @Column(name = "TRX_FLOW_HEADER_ID")
    private Integer trxFlowHeaderId;
    @Column(name = "LOGICAL_TRANSACTIONS_CREATED")
    private Integer logicalTransactionsCreated;
    @Column(name = "LOGICAL_TRANSACTION")
    private Integer logicalTransaction;
    @Column(name = "INTERCOMPANY_COST")
    private Integer intercompanyCost;
    @Column(name = "INTERCOMPANY_PRICING_OPTION")
    private Integer intercompanyPricingOption;
    @Size(max = 15)
    @Column(name = "INTERCOMPANY_CURRENCY_CODE")
    private String intercompanyCurrencyCode;
    @Column(name = "ORIGINAL_TRANSACTION_TEMP_ID")
    private Integer originalTransactionTempId;
    @Column(name = "TRANSFER_PRICE")
    private Integer transferPrice;
    @Column(name = "EXPENSE_ACCOUNT_ID")
    private Integer expenseAccountId;
    @Column(name = "COGS_RECOGNITION_PERCENT")
    private Integer cogsRecognitionPercent;
    @Column(name = "SO_ISSUE_ACCOUNT_TYPE")
    private Integer soIssueAccountType;
    @Size(max = 1)
    @Column(name = "OPM_COSTED_FLAG")
    private String opmCostedFlag;
    @Column(name = "MATERIAL_EXPENSE_ACCOUNT")
    private Integer materialExpenseAccount;
   

    @Column(name = "SOURCE_HEADER_ID")
    private Integer sourceHeaderId;
   
    @Column(name = "TRANSACTION_UOM_ID")
    private Integer transactionUomId;
    @Size(max = 3)
    @Column(name = "TRANSACTION_UOM_CODE")
    private String transactionUomCode;

    @Size(max = 100)
    @Column(name = "TRANSFER_TO_ACCOUNT_ALIASE")
    private String transferToAccountAliase;
    
       
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID",referencedColumnName = "ORGANIZATION_ID")
    private MainInventory organization;
    
    @ManyToOne
    @JoinColumn(name = "SUBINVENTORY_ID",referencedColumnName = "SECONDARY_INVENTORY_ID")
    private SecondaryInventory secondaryInventory;

    @ManyToOne
    @JoinColumn(name = "TRANSFER_TO_ACCOUNT_ID",referencedColumnName = "CODE_COMBINATION_ID")
    private GlCodeCombination transferToAccount;
    
    @ManyToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")    
    private OrganizationUnit operatingUnit ;
   
    private String description ;
   
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_SOURCE_TYPE")
    private TxnSourceType sourceType ;
    
    public InvMaterialTransaction() {
    }
   
    
    public InvMaterialTransaction(TxTransactionType type,MainInventory mainInv,SecondaryInventory subInv,BigDecimal txQuantity,BigDecimal primaryQuantity,SystemItem systemItem,Date txDate,TxnSourceType sourceType,String description)
    {
        this(systemItem, txQuantity, primaryQuantity, txDate);
        this.transactionType = type ;
        this.secondaryInventory = subInv;
        this.organization = mainInv;
        this.operatingUnit = this.organization.getOperatingUnit() ;
        this.sourceType = sourceType ;
        this.description = description ;
    }
    
     public InvMaterialTransaction(TxTransactionType type,GlCodeCombination account,OrganizationUnit operatingUnit,BigDecimal txQuantity,BigDecimal primaryQuantity,SystemItem systemItem,Date txDate,TxnSourceType sourceType,String description)
    {
        this(systemItem, txQuantity, primaryQuantity, txDate);
        this.transactionType = type ;
        this.transferToAccount = account ;
        this.operatingUnit = operatingUnit ;
        this.sourceType = sourceType ;
        this.description = description ;
    }
    
    public InvMaterialTransaction(SystemItem sysItem,BigDecimal txQuantity , BigDecimal primaryQuantity,Date txDate)
    {
        this(sysItem);
        
        this.transactionQuantity = txQuantity ;
        
        this.transactionDate = txDate ;
       
        this.primaryQuantity = primaryQuantity;
    }
    
    public InvMaterialTransaction(PoLine poLine) {
    
        this(poLine.getSystemItem());
        
        this.poLine = poLine ;
        
        this.transactionType = TxTransactionType.RECIEVE;
        
        this.sourceType = TxnSourceType.PURCHASE_ORDER_RECIEVE ;
        
        this.description = "Po no "+poLine.getPoHeader().getPoHeaderId();
        
        this.transactionQuantity = poLine.getTxQuantity();
        
        this.transactionDate = poLine.getTxDate() ;
        
        this.organization = poLine.getTagertMainInv() ;
        
        this.operatingUnit = this.organization.getOperatingUnit() ;
        
        this.secondaryInventory = poLine.getTargetSecInv() ;
    }

    
    public InvMaterialTransaction(MainInventory org,SecondaryInventory subInv,PoLine poLine) {
     
        this(poLine.getSystemItem()) ;
        
        this.organization = org ;

        this.secondaryInventory = subInv ;
        
        this.poLine = poLine ;
        
        this.transactionType = TxTransactionType.RECIEVE;

    }

    public InvMaterialTransaction(SystemItem systemItem)
    {
        inventoryItem = systemItem ;
        
        transactionUom = systemItem.getPrimaryUomCode();  
 
        setTransactionActionId(1);
           
        setTransactionSourceId(1);
           
        setTransactionSourceTypeId(1);
    }
    
    public InvMaterialTransaction(GmeMaterialDetail materialDetail)
    {
       this(materialDetail.getInventoryItem());
       
       this.primaryQuantity = materialDetail.getPlanQty() ;
       
       this.transactionQuantity = materialDetail.getPlanQty() ;
       
       this.transactionDate = new Date();
    }
    
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public SystemItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(SystemItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    
    public MainInventory getOrganization() {
        return organization;
    }

    

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public Integer getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Integer locatorId) {
        this.locatorId = locatorId;
    }

    public TxTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TxTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    
    public Integer getTransactionActionId() {
        return transactionActionId;
    }

    public void setTransactionActionId(Integer transactionActionId) {
        this.transactionActionId = transactionActionId;
    }

    public Integer getTransactionSourceTypeId() {
        return transactionSourceTypeId;
    }

    public void setTransactionSourceTypeId(Integer transactionSourceTypeId) {
        this.transactionSourceTypeId = transactionSourceTypeId;
    }

    public Integer getTransactionSourceId() {
        return transactionSourceId;
    }

    public void setTransactionSourceId(Integer transactionSourceId) {
        this.transactionSourceId = transactionSourceId;
    }

    public String getTransactionSourceName() {
        return transactionSourceName;
    }

    public void setTransactionSourceName(String transactionSourceName) {
        this.transactionSourceName = transactionSourceName;
    }

    public BigDecimal getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setPrimaryQuantity(BigDecimal primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public void setTransactionQuantity(BigDecimal transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }


    public String getTransactionUom() {
        return transactionUom;
    }

    public void setTransactionUom(String transactionUom) {
        this.transactionUom = transactionUom;
    }

    public BigDecimal getPrimaryQuantity() {
        return primaryQuantity;
    }

    
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getVarianceAmount() {
        return varianceAmount;
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(OrganizationUnit operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    
    public void setVarianceAmount(Integer varianceAmount) {
        this.varianceAmount = varianceAmount;
    }

    public Integer getAcctPeriodId() {
        return acctPeriodId;
    }

    public void setAcctPeriodId(Integer acctPeriodId) {
        this.acctPeriodId = acctPeriodId;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public Integer getDistributionAccountId() {
        return distributionAccountId;
    }

    public void setDistributionAccountId(Integer distributionAccountId) {
        this.distributionAccountId = distributionAccountId;
    }

    public Integer getEncumbranceAccount() {
        return encumbranceAccount;
    }

    public void setEncumbranceAccount(Integer encumbranceAccount) {
        this.encumbranceAccount = encumbranceAccount;
    }

    public Integer getEncumbranceAmount() {
        return encumbranceAmount;
    }

    public void setEncumbranceAmount(Integer encumbranceAmount) {
        this.encumbranceAmount = encumbranceAmount;
    }

    public Integer getCostUpdateId() {
        return costUpdateId;
    }

    public void setCostUpdateId(Integer costUpdateId) {
        this.costUpdateId = costUpdateId;
    }

    public String getCostedFlag() {
        return costedFlag;
    }

    public void setCostedFlag(String costedFlag) {
        this.costedFlag = costedFlag;
    }

    public Integer getTransactionGroupId() {
        return transactionGroupId;
    }

    public void setTransactionGroupId(Integer transactionGroupId) {
        this.transactionGroupId = transactionGroupId;
    }

    public String getInvoicedFlag() {
        return invoicedFlag;
    }

    public void setInvoicedFlag(String invoicedFlag) {
        this.invoicedFlag = invoicedFlag;
    }

    public Integer getActualCost() {
        return actualCost;
    }

    public void setActualCost(Integer actualCost) {
        this.actualCost = actualCost;
    }

    public Integer getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionCost(Integer transactionCost) {
        this.transactionCost = transactionCost;
    }

    public Integer getPriorCost() {
        return priorCost;
    }

    public void setPriorCost(Integer priorCost) {
        this.priorCost = priorCost;
    }

    public Integer getNewCost() {
        return newCost;
    }

    public void setNewCost(Integer newCost) {
        this.newCost = newCost;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(Integer currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public String getCurrencyConversionType() {
        return currencyConversionType;
    }

    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }

    public Date getCurrencyConversionDate() {
        return currencyConversionDate;
    }

    public void setCurrencyConversionDate(Date currencyConversionDate) {
        this.currencyConversionDate = currencyConversionDate;
    }

    public Integer getQuantityAdjusted() {
        return quantityAdjusted;
    }

    public void setQuantityAdjusted(Integer quantityAdjusted) {
        this.quantityAdjusted = quantityAdjusted;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOperationSeqNum() {
        return operationSeqNum;
    }

    public void setOperationSeqNum(Integer operationSeqNum) {
        this.operationSeqNum = operationSeqNum;
    }

    public String getReceivingDocument() {
        return receivingDocument;
    }

    public void setReceivingDocument(String receivingDocument) {
        this.receivingDocument = receivingDocument;
    }

    public Integer getPickingLineId() {
        return pickingLineId;
    }

    public void setPickingLineId(Integer pickingLineId) {
        this.pickingLineId = pickingLineId;
    }

    public Integer getTrxSourceLineId() {
        return trxSourceLineId;
    }

    public void setTrxSourceLineId(Integer trxSourceLineId) {
        this.trxSourceLineId = trxSourceLineId;
    }

    public Integer getTrxSourceDeliveryId() {
        return trxSourceDeliveryId;
    }

    public void setTrxSourceDeliveryId(Integer trxSourceDeliveryId) {
        this.trxSourceDeliveryId = trxSourceDeliveryId;
    }

    public Integer getPhysicalAdjustmentId() {
        return physicalAdjustmentId;
    }

    public void setPhysicalAdjustmentId(Integer physicalAdjustmentId) {
        this.physicalAdjustmentId = physicalAdjustmentId;
    }

    public Integer getCycleCountId() {
        return cycleCountId;
    }

    public void setCycleCountId(Integer cycleCountId) {
        this.cycleCountId = cycleCountId;
    }

    public Integer getRmaLineId() {
        return rmaLineId;
    }

    public void setRmaLineId(Integer rmaLineId) {
        this.rmaLineId = rmaLineId;
    }

    public Integer getTransferTransactionId() {
        return transferTransactionId;
    }

    public void setTransferTransactionId(Integer transferTransactionId) {
        this.transferTransactionId = transferTransactionId;
    }

    public Integer getTransactionSetId() {
        return transactionSetId;
    }

    public void setTransactionSetId(Integer transactionSetId) {
        this.transactionSetId = transactionSetId;
    }

    public Integer getRcvTransactionId() {
        return rcvTransactionId;
    }

    public void setRcvTransactionId(Integer rcvTransactionId) {
        this.rcvTransactionId = rcvTransactionId;
    }

    public Integer getMoveTransactionId() {
        return moveTransactionId;
    }

    public void setMoveTransactionId(Integer moveTransactionId) {
        this.moveTransactionId = moveTransactionId;
    }

    public Integer getCompletionTransactionId() {
        return completionTransactionId;
    }

    public void setCompletionTransactionId(Integer completionTransactionId) {
        this.completionTransactionId = completionTransactionId;
    }

    public Integer getShortageProcessCode() {
        return shortageProcessCode;
    }

    public void setShortageProcessCode(Integer shortageProcessCode) {
        this.shortageProcessCode = shortageProcessCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public PoLine getPoLine() {
        return poLine;
    }

    public void setPoLine(PoLine poLine) {
        this.poLine = poLine;
    }

    
    public String getVendorLotNumber() {
        return vendorLotNumber;
    }

    public void setVendorLotNumber(String vendorLotNumber) {
        this.vendorLotNumber = vendorLotNumber;
    }

    public Integer getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(Integer transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
    }

    public String getTransferSubinventory() {
        return transferSubinventory;
    }

    public void setTransferSubinventory(String transferSubinventory) {
        this.transferSubinventory = transferSubinventory;
    }

    public Integer getTransferLocatorId() {
        return transferLocatorId;
    }

    public void setTransferLocatorId(Integer transferLocatorId) {
        this.transferLocatorId = transferLocatorId;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public Integer getTransferCost() {
        return transferCost;
    }

    public void setTransferCost(Integer transferCost) {
        this.transferCost = transferCost;
    }

    public Integer getTransportationDistAccount() {
        return transportationDistAccount;
    }

    public void setTransportationDistAccount(Integer transportationDistAccount) {
        this.transportationDistAccount = transportationDistAccount;
    }

    public Integer getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(Integer transportationCost) {
        this.transportationCost = transportationCost;
    }

    public Integer getTransferCostDistAccount() {
        return transferCostDistAccount;
    }

    public void setTransferCostDistAccount(Integer transferCostDistAccount) {
        this.transferCostDistAccount = transferCostDistAccount;
    }

    public String getWaybillAirbill() {
        return waybillAirbill;
    }

    public void setWaybillAirbill(String waybillAirbill) {
        this.waybillAirbill = waybillAirbill;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public Integer getValueChange() {
        return valueChange;
    }

    public void setValueChange(Integer valueChange) {
        this.valueChange = valueChange;
    }

    public Integer getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(Integer percentageChange) {
        this.percentageChange = percentageChange;
    }

    public Integer getMovementId() {
        return movementId;
    }

    public void setMovementId(Integer movementId) {
        this.movementId = movementId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getToTaskId() {
        return toTaskId;
    }

    public void setToTaskId(Long toTaskId) {
        this.toTaskId = toTaskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getToProjectId() {
        return toProjectId;
    }

    public void setToProjectId(Long toProjectId) {
        this.toProjectId = toProjectId;
    }

    public Integer getSourceProjectId() {
        return sourceProjectId;
    }

    public void setSourceProjectId(Integer sourceProjectId) {
        this.sourceProjectId = sourceProjectId;
    }

    public Integer getSourceTaskId() {
        return sourceTaskId;
    }

    public void setSourceTaskId(Integer sourceTaskId) {
        this.sourceTaskId = sourceTaskId;
    }

    public String getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(String expenditureType) {
        this.expenditureType = expenditureType;
    }

    public Integer getPriorCostedQuantity() {
        return priorCostedQuantity;
    }

    public void setPriorCostedQuantity(Integer priorCostedQuantity) {
        this.priorCostedQuantity = priorCostedQuantity;
    }

    public String getShipmentCosted() {
        return shipmentCosted;
    }

    public void setShipmentCosted(String shipmentCosted) {
        this.shipmentCosted = shipmentCosted;
    }

    public Integer getTransferPercentage() {
        return transferPercentage;
    }

    public void setTransferPercentage(Integer transferPercentage) {
        this.transferPercentage = transferPercentage;
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

    public Integer getOutsideProcessingAccount() {
        return outsideProcessingAccount;
    }

    public void setOutsideProcessingAccount(Integer outsideProcessingAccount) {
        this.outsideProcessingAccount = outsideProcessingAccount;
    }

    public Integer getOverheadAccount() {
        return overheadAccount;
    }

    public void setOverheadAccount(Integer overheadAccount) {
        this.overheadAccount = overheadAccount;
    }

    public Integer getCostGroupId() {
        return costGroupId;
    }

    public void setCostGroupId(Integer costGroupId) {
        this.costGroupId = costGroupId;
    }

    public Integer getTransferCostGroupId() {
        return transferCostGroupId;
    }

    public void setTransferCostGroupId(Integer transferCostGroupId) {
        this.transferCostGroupId = transferCostGroupId;
    }

    public String getFlowSchedule() {
        return flowSchedule;
    }

    public void setFlowSchedule(String flowSchedule) {
        this.flowSchedule = flowSchedule;
    }

    public Integer getQaCollectionId() {
        return qaCollectionId;
    }

    public void setQaCollectionId(Integer qaCollectionId) {
        this.qaCollectionId = qaCollectionId;
    }

    public Integer getCommonBomSeqId() {
        return commonBomSeqId;
    }

    public void setCommonBomSeqId(Integer commonBomSeqId) {
        this.commonBomSeqId = commonBomSeqId;
    }

    public Integer getCommonRoutingSeqId() {
        return commonRoutingSeqId;
    }

    public void setCommonRoutingSeqId(Integer commonRoutingSeqId) {
        this.commonRoutingSeqId = commonRoutingSeqId;
    }

    public Integer getOrgCostGroupId() {
        return orgCostGroupId;
    }

    public void setOrgCostGroupId(Integer orgCostGroupId) {
        this.orgCostGroupId = orgCostGroupId;
    }

    public Integer getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Integer costTypeId) {
        this.costTypeId = costTypeId;
    }

    public Integer getPeriodicPrimaryQuantity() {
        return periodicPrimaryQuantity;
    }

    public void setPeriodicPrimaryQuantity(Integer periodicPrimaryQuantity) {
        this.periodicPrimaryQuantity = periodicPrimaryQuantity;
    }

    public Integer getMoveOrderLineId() {
        return moveOrderLineId;
    }

    public void setMoveOrderLineId(Integer moveOrderLineId) {
        this.moveOrderLineId = moveOrderLineId;
    }

    public Integer getPickSlipNumber() {
        return pickSlipNumber;
    }

    public void setPickSlipNumber(Integer pickSlipNumber) {
        this.pickSlipNumber = pickSlipNumber;
    }

    public Integer getLpnId() {
        return lpnId;
    }

    public void setLpnId(Integer lpnId) {
        this.lpnId = lpnId;
    }

    public Integer getTransferLpnId() {
        return transferLpnId;
    }

    public void setTransferLpnId(Integer transferLpnId) {
        this.transferLpnId = transferLpnId;
    }

    public Integer getPickStrategyId() {
        return pickStrategyId;
    }

    public void setPickStrategyId(Integer pickStrategyId) {
        this.pickStrategyId = pickStrategyId;
    }

    public Integer getPickRuleId() {
        return pickRuleId;
    }

    public void setPickRuleId(Integer pickRuleId) {
        this.pickRuleId = pickRuleId;
    }

    public Integer getPutAwayStrategyId() {
        return putAwayStrategyId;
    }

    public void setPutAwayStrategyId(Integer putAwayStrategyId) {
        this.putAwayStrategyId = putAwayStrategyId;
    }

    public Integer getPutAwayRuleId() {
        return putAwayRuleId;
    }

    public void setPutAwayRuleId(Integer putAwayRuleId) {
        this.putAwayRuleId = putAwayRuleId;
    }

    public Date getPickSlipDate() {
        return pickSlipDate;
    }

    public void setPickSlipDate(Date pickSlipDate) {
        this.pickSlipDate = pickSlipDate;
    }

    public Integer getCostCategoryId() {
        return costCategoryId;
    }

    public void setCostCategoryId(Integer costCategoryId) {
        this.costCategoryId = costCategoryId;
    }

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getTransferOrganizationType() {
        return transferOrganizationType;
    }

    public void setTransferOrganizationType(Integer transferOrganizationType) {
        this.transferOrganizationType = transferOrganizationType;
    }

    public Integer getOwningOrganizationId() {
        return owningOrganizationId;
    }

    public void setOwningOrganizationId(Integer owningOrganizationId) {
        this.owningOrganizationId = owningOrganizationId;
    }

    public Integer getOwningTpType() {
        return owningTpType;
    }

    public void setOwningTpType(Integer owningTpType) {
        this.owningTpType = owningTpType;
    }

    public Integer getPlanningOrganizationId() {
        return planningOrganizationId;
    }

    public void setPlanningOrganizationId(Integer planningOrganizationId) {
        this.planningOrganizationId = planningOrganizationId;
    }

    public Integer getPlanningTpType() {
        return planningTpType;
    }

    public void setPlanningTpType(Integer planningTpType) {
        this.planningTpType = planningTpType;
    }

    public String getSecondaryUomCode() {
        return secondaryUomCode;
    }

    public void setSecondaryUomCode(String secondaryUomCode) {
        this.secondaryUomCode = secondaryUomCode;
    }

    public Integer getSecondaryTransactionQuantity() {
        return secondaryTransactionQuantity;
    }

    public void setSecondaryTransactionQuantity(Integer secondaryTransactionQuantity) {
        this.secondaryTransactionQuantity = secondaryTransactionQuantity;
    }

    public Integer getTransactionGroupSeq() {
        return transactionGroupSeq;
    }

    public void setTransactionGroupSeq(Integer transactionGroupSeq) {
        this.transactionGroupSeq = transactionGroupSeq;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(Integer transactionMode) {
        this.transactionMode = transactionMode;
    }

    public Integer getTransactionBatchId() {
        return transactionBatchId;
    }

    public void setTransactionBatchId(Integer transactionBatchId) {
        this.transactionBatchId = transactionBatchId;
    }

    public Integer getTransactionBatchSeq() {
        return transactionBatchSeq;
    }

    public void setTransactionBatchSeq(Integer transactionBatchSeq) {
        this.transactionBatchSeq = transactionBatchSeq;
    }

    public Integer getIntransitAccount() {
        return intransitAccount;
    }

    public void setIntransitAccount(Integer intransitAccount) {
        this.intransitAccount = intransitAccount;
    }

    public Integer getFobPoint() {
        return fobPoint;
    }

    public void setFobPoint(Integer fobPoint) {
        this.fobPoint = fobPoint;
    }

    public Integer getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(Integer parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public Integer getLogicalTrxTypeCode() {
        return logicalTrxTypeCode;
    }

    public void setLogicalTrxTypeCode(Integer logicalTrxTypeCode) {
        this.logicalTrxTypeCode = logicalTrxTypeCode;
    }

    public Integer getTrxFlowHeaderId() {
        return trxFlowHeaderId;
    }

    public void setTrxFlowHeaderId(Integer trxFlowHeaderId) {
        this.trxFlowHeaderId = trxFlowHeaderId;
    }

    public Integer getLogicalTransactionsCreated() {
        return logicalTransactionsCreated;
    }

    public void setLogicalTransactionsCreated(Integer logicalTransactionsCreated) {
        this.logicalTransactionsCreated = logicalTransactionsCreated;
    }

    public Integer getLogicalTransaction() {
        return logicalTransaction;
    }

    public void setLogicalTransaction(Integer logicalTransaction) {
        this.logicalTransaction = logicalTransaction;
    }

    public Integer getIntercompanyCost() {
        return intercompanyCost;
    }

    public void setIntercompanyCost(Integer intercompanyCost) {
        this.intercompanyCost = intercompanyCost;
    }

    public Integer getIntercompanyPricingOption() {
        return intercompanyPricingOption;
    }

    public void setIntercompanyPricingOption(Integer intercompanyPricingOption) {
        this.intercompanyPricingOption = intercompanyPricingOption;
    }

    public String getIntercompanyCurrencyCode() {
        return intercompanyCurrencyCode;
    }

    public void setIntercompanyCurrencyCode(String intercompanyCurrencyCode) {
        this.intercompanyCurrencyCode = intercompanyCurrencyCode;
    }

    public Integer getOriginalTransactionTempId() {
        return originalTransactionTempId;
    }

    public void setOriginalTransactionTempId(Integer originalTransactionTempId) {
        this.originalTransactionTempId = originalTransactionTempId;
    }

    public Integer getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(Integer transferPrice) {
        this.transferPrice = transferPrice;
    }

    public Integer getExpenseAccountId() {
        return expenseAccountId;
    }

    public void setExpenseAccountId(Integer expenseAccountId) {
        this.expenseAccountId = expenseAccountId;
    }

    public Integer getCogsRecognitionPercent() {
        return cogsRecognitionPercent;
    }

    public void setCogsRecognitionPercent(Integer cogsRecognitionPercent) {
        this.cogsRecognitionPercent = cogsRecognitionPercent;
    }

    public Integer getSoIssueAccountType() {
        return soIssueAccountType;
    }

    public void setSoIssueAccountType(Integer soIssueAccountType) {
        this.soIssueAccountType = soIssueAccountType;
    }

    public String getOpmCostedFlag() {
        return opmCostedFlag;
    }

    public void setOpmCostedFlag(String opmCostedFlag) {
        this.opmCostedFlag = opmCostedFlag;
    }

    public Integer getMaterialExpenseAccount() {
        return materialExpenseAccount;
    }

    public void setMaterialExpenseAccount(Integer materialExpenseAccount) {
        this.materialExpenseAccount = materialExpenseAccount;
    }

    public SecondaryInventory getSecondaryInventory() {
        return secondaryInventory;
    }

    public void setOrganization(MainInventory organization) {
        this.organization = organization;
    }

    public void setSecondaryInventory(SecondaryInventory secondaryInventory) {
        this.secondaryInventory = secondaryInventory;
    }

    

    public Integer getSourceHeaderId() {
        return sourceHeaderId;
    }

    public void setSourceHeaderId(Integer sourceHeaderId) {
        this.sourceHeaderId = sourceHeaderId;
    }

    public Integer getTransactionUomId() {
        return transactionUomId;
    }

    public void setTransactionUomId(Integer transactionUomId) {
        this.transactionUomId = transactionUomId;
    }

    public String getTransactionUomCode() {
        return transactionUomCode;
    }

    public void setTransactionUomCode(String transactionUomCode) {
        this.transactionUomCode = transactionUomCode;
    }

    public GlCodeCombination getTransferToAccount() {
        return transferToAccount;
    }

    public void setTransferToAccount(GlCodeCombination transferToAccount) {
        this.transferToAccount = transferToAccount;
    }

    

    public String getTransferToAccountAliase() {
        return transferToAccountAliase;
    }

    public void setTransferToAccountAliase(String transferToAccountAliase) {
        this.transferToAccountAliase = transferToAccountAliase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvMaterialTransaction)) {
            return false;
        }
        InvMaterialTransaction other = (InvMaterialTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.inventory.InvMaterialTransaction[ transactionId=" + transactionId + " ]";
    }
    
    @PrePersist
    private void onPersist()
    {
        lastUpdateDate = new Date() ;
        
        lastUpdatedBy = 1 ;
        
        createdBy = 1 ;
        
        creationDate = new Date() ;
    }
}
