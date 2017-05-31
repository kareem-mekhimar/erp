/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.moveorder;

import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.FormulaLineType;
import com.bridge.enums.MoveHeaderStatus;
import com.bridge.enums.MoveLineStatus;
import com.bridge.enums.MoveOrderTransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author Bridge
 */
@Entity
@Table(name = "MTL_TXN_REQUEST_LINES")
@NamedQueries({
    @NamedQuery(name = "MtlTxnRequestLine.findAll", query = "SELECT m FROM MtlTxnRequestLine m")})
public class MtlTxnRequestLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "MoveLine")
    @TableGenerator(name = "MoveLine",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "MoveLine",valueColumnName = "CURRENT_VALUE")
    @NotNull
    @Column(name = "LINE_ID")
    private Integer lineId;
    @Column(name = "LINE_NUMBER")
    private Integer lineNumber;
    @Size(max = 3)
    @Column(name = "REVISION")
    private String revision;
    @Size(max = 10)
    @Column(name = "FROM_SUBINVENTORY_CODE")
    private String fromSubinventoryCode;
    @Column(name = "FROM_LOCATOR_ID")
    private Integer fromLocatorId;
    @Size(max = 10)
    @Column(name = "TO_SUBINVENTORY_CODE")
    private String toSubinventoryCode;
    @Column(name = "TO_LOCATOR_ID")
    private Integer toLocatorId;
    
    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT_ID")
    private GlCodeCombination toAccount;
    @Size(max = 80)
    @Column(name = "LOT_NUMBER")
    private String lotNumber;
    @Size(max = 30)
    @Column(name = "SERIAL_NUMBER_START")
    private String serialNumberStart;
    @Size(max = 30)
    @Column(name = "SERIAL_NUMBER_END")
    private String serialNumberEnd;
    @Size(min = 1, max = 3)
    @Column(name = "UOM_CODE")
    private String uomCode;
    @Basic(optional = false)
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @Column(name = "QUANTITY_DELIVERED")
    private BigDecimal quantityDelivered = BigDecimal.ZERO;    
    @Column(name = "QUANTITY_DETAILED")
    private Integer quantityDetailed;
    @Column(name = "DATE_REQUIRED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRequired;
    @Column(name = "REASON_ID")
    private Integer reasonId;
    @Size(max = 240)
    @Column(name = "REFERENCE")
    private String reference;
    @Column(name = "REFERENCE_TYPE_CODE")
    private Integer referenceTypeCode;
    @Column(name = "REFERENCE_ID")
    private Integer referenceId;
    @Column(name = "PROJECT_ID")
    private Integer projectId;
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "TRANSACTION_HEADER_ID")
    private Integer transactionHeaderId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "LINE_STATUS")
    private MoveLineStatus lineStatus;
    
    @Column(name = "STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate = new Date();
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "TXN_SOURCE_ID")
    private Integer txnSourceId;
    @Column(name = "TXN_SOURCE_LINE_ID")
    private Integer txnSourceLineId;
    @Column(name = "TXN_SOURCE_LINE_DETAIL_ID")
    private Integer txnSourceLineDetailId;
    @Column(name = "TRANSACTION_TYPE_ID")
    private MoveOrderTransactionType transactionType;    
    @Column(name = "TRANSACTION_SOURCE_TYPE_ID")
    private Integer transactionSourceTypeId;    
    @Column(name = "PRIMARY_QUANTITY")
    private Integer primaryQuantity;
    @Column(name = "PUT_AWAY_STRATEGY_ID")
    private Integer putAwayStrategyId;
    @Column(name = "PICK_STRATEGY_ID")
    private Integer pickStrategyId;
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    @Size(max = 30)
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;
    @Column(name = "REFERENCE_DETAIL_ID")
    private Integer referenceDetailId;
    @Column(name = "ASSIGNMENT_ID")
    private Integer assignmentId;
    @Column(name = "FROM_COST_GROUP_ID")
    private Integer fromCostGroupId;
    @Column(name = "TO_COST_GROUP_ID")
    private Integer toCostGroupId;
    @Column(name = "LPN_ID")
    private Integer lpnId;
    @Column(name = "TO_LPN_ID")
    private Integer toLpnId;
    @Column(name = "PICK_SLIP_NUMBER")
    private Integer pickSlipNumber;
    @Column(name = "PICK_SLIP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickSlipDate;
    @Column(name = "INSPECTION_STATUS")
    private Integer inspectionStatus;
    @Column(name = "PICK_METHODOLOGY_ID")
    private Integer pickMethodologyId;
    @Column(name = "CONTAINER_ITEM_ID")
    private Integer containerItemId;
    @Column(name = "CARTON_GROUPING_ID")
    private Integer cartonGroupingId;
    @Column(name = "BACKORDER_DELIVERY_DETAIL_ID")
    private Integer backorderDeliveryDetailId;
    @Column(name = "WMS_PROCESS_FLAG")
    private Integer wmsProcessFlag;
    @Column(name = "SHIP_SET_ID")
    private Integer shipSetId;
    @Column(name = "SHIP_MODEL_ID")
    private Integer shipModelId;
    @Column(name = "MODEL_QUANTITY")
    private Integer modelQuantity;
    @Column(name = "CROSSDOCK_TYPE")
    private Integer crossdockType;
    @Column(name = "REQUIRED_QUANTITY")
    private Integer requiredQuantity;
    @Size(max = 150)
    @Column(name = "GRADE_CODE")
    private String gradeCode;
    @Column(name = "SECONDARY_QUANTITY")
    private Integer secondaryQuantity;
    @Column(name = "SECONDARY_QUANTITY_DELIVERED")
    private Integer secondaryQuantityDelivered;
    @Column(name = "SECONDARY_QUANTITY_DETAILED")
    private Integer secondaryQuantityDetailed;
    @Column(name = "SECONDARY_REQUIRED_QUANTITY")
    private Integer secondaryRequiredQuantity;
    @Size(max = 3)
    @Column(name = "SECONDARY_UOM_CODE")
    private String secondaryUomCode;
    @Column(name = "WIP_ENTITY_ID")
    private Integer wipEntityId;
    @Column(name = "REPETITIVE_SCHEDULE_ID")
    private Integer repetitiveScheduleId;
    @Column(name = "OPERATION_SEQ_NUM")
    private Integer operationSeqNum;
    @Column(name = "WIP_SUPPLY_TYPE")
    private Integer wipSupplyType;
    @Column(name="SALES_LINE_ID")
    private Integer salesLineId;    
    @Column(name = "LINE_TYPE")
    private FormulaLineType lineType;   
    @Column(name = "UOM_ID")
    private Integer uomId;   
    @JoinColumn(name = "HEADER_ID", referencedColumnName = "HEADER_ID")
    @ManyToOne(optional = false)
    private MtlTxnRequestHeader header;
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private MainInventory fromOrganization;
    @ManyToOne
    @JoinColumn(name = "INVENTORY_ITEM_ID")
    private SystemItem inventoryItem;    
    @ManyToOne
    @JoinColumn(name = "FROM_SUBINVENTORY_ID")
    private SecondaryInventory fromSubinventory;    
    @ManyToOne
    @JoinColumn(name = "TO_SUBINVENTORY_ID")
    private SecondaryInventory toSubinventory;
    @ManyToOne
    @JoinColumn(name = "TO_ORGANIZATION_ID")
    private MainInventory toOrganization;   
    @OneToOne
    @JoinColumn(name = "SOURCE_LINE_ID")
    private GmeMaterialDetail gmeMaterialDetail ;
   
//    @OneToOne
//    @JoinColumns({@JoinColumn(name = "FROM_SUBINVENTORY_ID",referencedColumnName = "SECONDARY_INVENTORY_ID"),
//                 @JoinColumn(name = "INVENTORY_ITEM_ID",referencedColumnName = "INVENTORY_ITEM_ID")})
//    private MtlInventoryItemsQuantity inventoryItemsQuantity ;
    
   
    @Transient
    private BigDecimal actualQtyInInventory ;
    
    @Transient
    private Date txDateForMoTransfer = new Date();
        
    @Transient
    private BigDecimal quantityThatWillTransferInTx ;
    
    public MtlTxnRequestLine() {
    }

    public MtlTxnRequestLine(Integer lineId) {
        this.lineId = lineId;
    }


    public MtlTxnRequestLine(GmeMaterialDetail materialDetail)
    {
       this.gmeMaterialDetail = materialDetail ;
       
       this.lineNumber = materialDetail.getLineNo() ;
       
       this.inventoryItem = materialDetail.getInventoryItem() ;
       
       this.uomCode = inventoryItem.getPrimaryUomCode() ;
       
       this.lineStatus = MoveLineStatus.INCOMPELETE ;
       
       this.lineType = materialDetail.getLineType() ;
     
       this.transactionType = MoveOrderTransactionType.JOB_SCHEDULE ;
       
       this.dateRequired = materialDetail.getTxDate() ;
       
       this.quantity = materialDetail.getQuantityNeededToPickOrTransact();
       
    }
    
       
    public MtlTxnRequestLine(OeOrderLine orderLine)
    {
       this.lineNumber = orderLine.getLineNumber() ;
       
       this.inventoryItem = orderLine.getInventoryItem() ;
       
       this.uomCode = inventoryItem.getPrimaryUomCode() ;
       
       this.lineStatus = MoveLineStatus.INCOMPELETE ;
       
       this.dateRequired = orderLine.getTxDate() ;
       
       this.quantity = orderLine.getQtyToPick() ;
       
       this.salesLineId=orderLine.getLineId();
    }
        
    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public MainInventory getFromOrganization() {
        return fromOrganization;
    }

    public SecondaryInventory getFromSubinventory() {
        return fromSubinventory;
    }

    public SystemItem getInventoryItem() {
        return inventoryItem;
    }

    
    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFromSubinventoryCode() {
        return fromSubinventoryCode;
    }

    public void setFromSubinventoryCode(String fromSubinventoryCode) {
        this.fromSubinventoryCode = fromSubinventoryCode;
    }

    public Integer getFromLocatorId() {
        return fromLocatorId;
    }

    public void setFromLocatorId(Integer fromLocatorId) {
        this.fromLocatorId = fromLocatorId;
    }

    public String getToSubinventoryCode() {
        return toSubinventoryCode;
    }

    public void setToSubinventoryCode(String toSubinventoryCode) {
        this.toSubinventoryCode = toSubinventoryCode;
    }

    public Integer getToLocatorId() {
        return toLocatorId;
    }

    public void setToLocatorId(Integer toLocatorId) {
        this.toLocatorId = toLocatorId;
    }

    public GlCodeCombination getToAccount() {
        return toAccount;
    }

    public void setToAccount(GlCodeCombination toAccount) {
        this.toAccount = toAccount;
    }

    
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSerialNumberStart() {
        return serialNumberStart;
    }

    public void setSerialNumberStart(String serialNumberStart) {
        this.serialNumberStart = serialNumberStart;
    }

    public String getSerialNumberEnd() {
        return serialNumberEnd;
    }

    public void setSerialNumberEnd(String serialNumberEnd) {
        this.serialNumberEnd = serialNumberEnd;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(BigDecimal quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    
    public Integer getQuantityDetailed() {
        return quantityDetailed;
    }

    public void setQuantityDetailed(Integer quantityDetailed) {
        this.quantityDetailed = quantityDetailed;
    }

    public Date getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Date dateRequired) {
        this.dateRequired = dateRequired;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getReferenceTypeCode() {
        return referenceTypeCode;
    }

    public void setReferenceTypeCode(Integer referenceTypeCode) {
        this.referenceTypeCode = referenceTypeCode;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTransactionHeaderId() {
        return transactionHeaderId;
    }

    public void setTransactionHeaderId(Integer transactionHeaderId) {
        this.transactionHeaderId = transactionHeaderId;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
    
    public Integer getTxnSourceId() {
        return txnSourceId;
    }

    public void setTxnSourceId(Integer txnSourceId) {
        this.txnSourceId = txnSourceId;
    }

    public Integer getTxnSourceLineId() {
        return txnSourceLineId;
    }

    public void setTxnSourceLineId(Integer txnSourceLineId) {
        this.txnSourceLineId = txnSourceLineId;
    }

    public Integer getTxnSourceLineDetailId() {
        return txnSourceLineDetailId;
    }

    public void setTxnSourceLineDetailId(Integer txnSourceLineDetailId) {
        this.txnSourceLineDetailId = txnSourceLineDetailId;
    }

    public MoveOrderTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(MoveOrderTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    
    public Integer getTransactionSourceTypeId() {
        return transactionSourceTypeId;
    }

    public void setTransactionSourceTypeId(Integer transactionSourceTypeId) {
        this.transactionSourceTypeId = transactionSourceTypeId;
    }

    public Integer getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(Integer primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }


    public Integer getPutAwayStrategyId() {
        return putAwayStrategyId;
    }

    public void setPutAwayStrategyId(Integer putAwayStrategyId) {
        this.putAwayStrategyId = putAwayStrategyId;
    }

    public Integer getPickStrategyId() {
        return pickStrategyId;
    }

    public void setPickStrategyId(Integer pickStrategyId) {
        this.pickStrategyId = pickStrategyId;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getReferenceDetailId() {
        return referenceDetailId;
    }

    public void setReferenceDetailId(Integer referenceDetailId) {
        this.referenceDetailId = referenceDetailId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getFromCostGroupId() {
        return fromCostGroupId;
    }

    public void setFromCostGroupId(Integer fromCostGroupId) {
        this.fromCostGroupId = fromCostGroupId;
    }

    public Integer getToCostGroupId() {
        return toCostGroupId;
    }

    public void setToCostGroupId(Integer toCostGroupId) {
        this.toCostGroupId = toCostGroupId;
    }

    public Integer getLpnId() {
        return lpnId;
    }

    public void setLpnId(Integer lpnId) {
        this.lpnId = lpnId;
    }

    public Integer getToLpnId() {
        return toLpnId;
    }

    public void setToLpnId(Integer toLpnId) {
        this.toLpnId = toLpnId;
    }

    public Integer getPickSlipNumber() {
        return pickSlipNumber;
    }

    public void setPickSlipNumber(Integer pickSlipNumber) {
        this.pickSlipNumber = pickSlipNumber;
    }

    public Date getPickSlipDate() {
        return pickSlipDate;
    }

    public void setPickSlipDate(Date pickSlipDate) {
        this.pickSlipDate = pickSlipDate;
    }

    public Integer getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Integer inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public Integer getPickMethodologyId() {
        return pickMethodologyId;
    }

    public void setPickMethodologyId(Integer pickMethodologyId) {
        this.pickMethodologyId = pickMethodologyId;
    }

    public Integer getContainerItemId() {
        return containerItemId;
    }

    public void setContainerItemId(Integer containerItemId) {
        this.containerItemId = containerItemId;
    }

    public Integer getCartonGroupingId() {
        return cartonGroupingId;
    }

    public void setCartonGroupingId(Integer cartonGroupingId) {
        this.cartonGroupingId = cartonGroupingId;
    }

    public Integer getBackorderDeliveryDetailId() {
        return backorderDeliveryDetailId;
    }

    public void setBackorderDeliveryDetailId(Integer backorderDeliveryDetailId) {
        this.backorderDeliveryDetailId = backorderDeliveryDetailId;
    }

    public Integer getWmsProcessFlag() {
        return wmsProcessFlag;
    }

    public void setWmsProcessFlag(Integer wmsProcessFlag) {
        this.wmsProcessFlag = wmsProcessFlag;
    }

    public Integer getShipSetId() {
        return shipSetId;
    }

    public void setShipSetId(Integer shipSetId) {
        this.shipSetId = shipSetId;
    }

    public Integer getShipModelId() {
        return shipModelId;
    }

    public void setShipModelId(Integer shipModelId) {
        this.shipModelId = shipModelId;
    }

    public Integer getModelQuantity() {
        return modelQuantity;
    }

    public void setModelQuantity(Integer modelQuantity) {
        this.modelQuantity = modelQuantity;
    }



    public Integer getCrossdockType() {
        return crossdockType;
    }

    public void setCrossdockType(Integer crossdockType) {
        this.crossdockType = crossdockType;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public Integer getSecondaryQuantity() {
        return secondaryQuantity;
    }

    public void setSecondaryQuantity(Integer secondaryQuantity) {
        this.secondaryQuantity = secondaryQuantity;
    }

    public Integer getSecondaryQuantityDelivered() {
        return secondaryQuantityDelivered;
    }

    public void setSecondaryQuantityDelivered(Integer secondaryQuantityDelivered) {
        this.secondaryQuantityDelivered = secondaryQuantityDelivered;
    }

    public Integer getSecondaryQuantityDetailed() {
        return secondaryQuantityDetailed;
    }

    public void setSecondaryQuantityDetailed(Integer secondaryQuantityDetailed) {
        this.secondaryQuantityDetailed = secondaryQuantityDetailed;
    }

    public Integer getSecondaryRequiredQuantity() {
        return secondaryRequiredQuantity;
    }

    public void setSecondaryRequiredQuantity(Integer secondaryRequiredQuantity) {
        this.secondaryRequiredQuantity = secondaryRequiredQuantity;
    }

    public String getSecondaryUomCode() {
        return secondaryUomCode;
    }

    public void setSecondaryUomCode(String secondaryUomCode) {
        this.secondaryUomCode = secondaryUomCode;
    }

    public Integer getWipEntityId() {
        return wipEntityId;
    }

    public void setWipEntityId(Integer wipEntityId) {
        this.wipEntityId = wipEntityId;
    }

    public Integer getRepetitiveScheduleId() {
        return repetitiveScheduleId;
    }

    public void setRepetitiveScheduleId(Integer repetitiveScheduleId) {
        this.repetitiveScheduleId = repetitiveScheduleId;
    }

    public Integer getOperationSeqNum() {
        return operationSeqNum;
    }

    public void setOperationSeqNum(Integer operationSeqNum) {
        this.operationSeqNum = operationSeqNum;
    }

    public Integer getWipSupplyType() {
        return wipSupplyType;
    }

    public void setWipSupplyType(Integer wipSupplyType) {
        this.wipSupplyType = wipSupplyType;
    }

    public MoveLineStatus getLineStatus() {
        return lineStatus;
    }

    public FormulaLineType getLineType() {
        return lineType;
    }

    public MainInventory getToOrganization() {
        return toOrganization;
    }

    public SecondaryInventory getToSubinventory() {
        return toSubinventory;
    }

    public void setFromOrganization(MainInventory fromOrganization) {
        this.fromOrganization = fromOrganization;
    }

    public void setFromSubinventory(SecondaryInventory fromSubinventory) {
        this.fromSubinventory = fromSubinventory;
    }

    public void setInventoryItem(SystemItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public void setLineStatus(MoveLineStatus lineStatus) {
        this.lineStatus = lineStatus;
    }

    public void setLineType(FormulaLineType lineType) {
        this.lineType = lineType;
    }

    public void setToOrganization(MainInventory toOrganization) {
        this.toOrganization = toOrganization;
    }

    public void setToSubinventory(SecondaryInventory toSubinventory) {
        this.toSubinventory = toSubinventory;
    }

    

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public MtlTxnRequestHeader getHeader() {
        return header;
    }

    public void setHeader(MtlTxnRequestHeader header) {
        this.header = header;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineId != null ? lineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MtlTxnRequestLine)) {
            return false;
        }
        MtlTxnRequestLine other = (MtlTxnRequestLine) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.batch.MtlTxnRequestLine[ lineId=" + lineId + " ]";
    }

    public GmeMaterialDetail getGmeMaterialDetail() {
        return gmeMaterialDetail;
    }

    public void setGmeMaterialDetail(GmeMaterialDetail gmeMaterialDetail) {
        this.gmeMaterialDetail = gmeMaterialDetail;
    }

    public BigDecimal getQuantityThatWillTransferInTx() {
        return quantityThatWillTransferInTx;
    }

    public void setQuantityThatWillTransferInTx(BigDecimal quantityThatWillTransferInTx) {
        this.quantityThatWillTransferInTx = quantityThatWillTransferInTx;
    }

    public Date getTxDateForMoTransfer() {
        return txDateForMoTransfer;
    }

    public void setTxDateForMoTransfer(Date txDateForMoTransfer) {
        this.txDateForMoTransfer = txDateForMoTransfer;
    }

    public BigDecimal getActualQtyInInventory() {
        return actualQtyInInventory;
    }

    public void setActualQtyInInventory(BigDecimal actualQtyInInventory) {
        this.actualQtyInInventory = actualQtyInInventory;
    }

    public Integer getSalesLineId() {
        return salesLineId;
    }

    public void setSalesLineId(Integer salesLineId) {
        this.salesLineId = salesLineId;
    }
    
    
    @PrePersist
    private void onPrePersist()
    {
       this.createdBy = 1 ;
       
       this.creationDate = new Date() ;
       
       this.lastUpdateDate = new Date() ;
       
       this.lastUpdatedBy = 1 ;
    }
}
