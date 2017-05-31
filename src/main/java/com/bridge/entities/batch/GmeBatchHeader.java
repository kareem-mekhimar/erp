/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.batch;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.BatchStatus;
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
@Table(name = "GME_BATCH_HEADER")
@NamedQueries({
    @NamedQuery(name = "GmeBatchHeader.findAll", query = "SELECT g FROM GmeBatchHeader g")})
public class GmeBatchHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "gen")
    @TableGenerator(name = "gen",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Batch",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "BATCH_ID")
    private Integer batchId;
    @Size(max = 4)
    @Column(name = "PLANT_CODE")
    private String plantCode;
    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @Column(name = "BATCH_NO")
    private String batchNo;
    @Basic(optional = false)
    @Column(name = "BATCH_TYPE")
    private int batchType;
    @Column(name = "PROD_ID")
    private Integer prodId;
    @Column(name = "PROD_SEQUENCE")
    private Integer prodSequence;
    @Column(name = "RECIPE_VALIDITY_RULE_ID")
    private Integer recipeValidityRuleId;

    @Column(name = "ROUTING_ID")
    private Integer routingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAN_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date planStartDate;
    @Column(name = "ACTUAL_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualStartDate;
    @Basic(optional = false)
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    
    @Column(name="REQUIRE_COMPLETION_DATE")
    private Date requireComplDate ;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAN_CMPLT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date planCmpltDate;
    @Column(name = "ACTUAL_CMPLT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualCmpltDate;
    
    @Column(name = "BATCH_STATUS")
    private BatchStatus batchStatus;
    
    @Column(name = "PRIORITY_VALUE")
    private Integer priorityValue;
    @Size(max = 4)
    @Column(name = "PRIORITY_CODE")
    private String priorityCode;
    @Column(name = "PRINT_COUNT")
    private Integer printCount;
    @Size(max = 8)
    @Column(name = "FMCONTROL_CLASS")
    private String fmcontrolClass;
    @Size(max = 4)
    @Column(name = "WIP_WHSE_CODE")
    private String wipWhseCode;
    @Column(name = "BATCH_CLOSE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batchCloseDate;
    @Size(max = 1)
    @Column(name = "POC_IND")
    private String pocInd;
    @Size(max = 1)
    @Column(name = "ACTUAL_COST_IND")
    private String actualCostInd;
    @Size(max = 1)
    @Column(name = "UPDATE_INVENTORY_IND")
    private String updateInventoryInd;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "DELETE_MARK")
    private Integer deleteMark;
    @Column(name = "TEXT_CODE")
    private Integer textCode;
    @Column(name = "PARENTLINE_ID")
    private Integer parentlineId;
    @Column(name = "FPO_ID")
    private Integer fpoId;
    @Column(name = "AUTOMATIC_STEP_CALCULATION")
    private Integer automaticStepCalculation;
    @Column(name = "GL_POSTED_IND")
    private Integer glPostedInd;
    @Column(name = "FIRMED_IND")
    private Integer firmedInd;
    @Column(name = "FINITE_SCHEDULED_IND")
    private Integer finiteScheduledInd;
    @Column(name = "ORDER_PRIORITY")
    private Integer orderPriority;
    @Size(max = 1)
    @Column(name = "MIGRATED_BATCH_IND")
    private String migratedBatchInd;
    @Column(name = "ENFORCE_STEP_DEPENDENCY")
    private Integer enforceStepDependency;
    @Column(name = "TERMINATED_IND")
    private Short terminatedInd;
    @Size(max = 1)
    @Column(name = "ENHANCED_PI_IND")
    private String enhancedPiInd;
    @Column(name = "LABORATORY_IND")
    private Integer laboratoryInd;
    @Column(name = "MOVE_ORDER_HEADER_ID")
    private Integer moveOrderHeaderId;
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    @Column(name = "TERMINATE_REASON_ID")
    private Integer terminateReasonId;
    @Size(max = 1)
    @Column(name = "FIXED_PROCESS_LOSS_APPLIED")
    private String fixedProcessLossApplied;
    @Column(name = "JA_OSP_BATCH")
    private Integer jaOspBatch;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BATCH_PLAN_QTY")
    private Integer batchPlanQty;
    
    @Size(max = 200)
    @Column(name = "BATCH_NAME")
    private String batchName;
    
    @Size(max = 25)
    @Column(name = "BATCH_CODE")
    private String batchCode;
   
    private String description ;
    
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroup;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batch")
    private List<GmeMaterialDetail> gmeMaterialDetailList;
    
    @OneToOne
    @JoinColumn(name = "FORMULA_ID")
    private Formula formula;

    public GmeBatchHeader() {
        
        this.batchStatus = BatchStatus.PENDING;
    }

    public GmeBatchHeader(Integer batchId) {
        this.batchId = batchId;
    }

    public GmeBatchHeader(Integer batchId, String batchNo, int batchType, Date planStartDate, Date dueDate, Date planCmpltDate, Integer batchPlanQty) {
        this.batchId = batchId;
        this.batchNo = batchNo;
        this.batchType = batchType;
        this.planStartDate = planStartDate;
        this.dueDate = dueDate;
        this.planCmpltDate = planCmpltDate;
        this.batchPlanQty = batchPlanQty;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getBatchType() {
        return batchType;
    }

    public void setBatchType(int batchType) {
        this.batchType = batchType;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getProdSequence() {
        return prodSequence;
    }

    public void setProdSequence(Integer prodSequence) {
        this.prodSequence = prodSequence;
    }

    public Integer getRecipeValidityRuleId() {
        return recipeValidityRuleId;
    }

    public void setRecipeValidityRuleId(Integer recipeValidityRuleId) {
        this.recipeValidityRuleId = recipeValidityRuleId;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    
    public Integer getRoutingId() {
        return routingId;
    }

    public void setRoutingId(Integer routingId) {
        this.routingId = routingId;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPlanCmpltDate() {
        return planCmpltDate;
    }

    public void setPlanCmpltDate(Date planCmpltDate) {
        this.planCmpltDate = planCmpltDate;
    }

    public Date getActualCmpltDate() {
        return actualCmpltDate;
    }

    public void setActualCmpltDate(Date actualCmpltDate) {
        this.actualCmpltDate = actualCmpltDate;
    }

    public BatchStatus getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(BatchStatus batchStatus) {
        this.batchStatus = batchStatus;
    }
    
    

    public Integer getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(Integer priorityValue) {
        this.priorityValue = priorityValue;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public String getFmcontrolClass() {
        return fmcontrolClass;
    }

    public void setFmcontrolClass(String fmcontrolClass) {
        this.fmcontrolClass = fmcontrolClass;
    }

    public String getWipWhseCode() {
        return wipWhseCode;
    }

    public void setWipWhseCode(String wipWhseCode) {
        this.wipWhseCode = wipWhseCode;
    }

    public Date getBatchCloseDate() {
        return batchCloseDate;
    }

    public void setBatchCloseDate(Date batchCloseDate) {
        this.batchCloseDate = batchCloseDate;
    }

    public String getPocInd() {
        return pocInd;
    }

    public void setPocInd(String pocInd) {
        this.pocInd = pocInd;
    }

    public String getActualCostInd() {
        return actualCostInd;
    }

    public void setActualCostInd(String actualCostInd) {
        this.actualCostInd = actualCostInd;
    }

    public String getUpdateInventoryInd() {
        return updateInventoryInd;
    }

    public void setUpdateInventoryInd(String updateInventoryInd) {
        this.updateInventoryInd = updateInventoryInd;
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

    public Integer getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }

    public Integer getTextCode() {
        return textCode;
    }

    public void setTextCode(Integer textCode) {
        this.textCode = textCode;
    }

    public Integer getParentlineId() {
        return parentlineId;
    }

    public void setParentlineId(Integer parentlineId) {
        this.parentlineId = parentlineId;
    }

    public Integer getFpoId() {
        return fpoId;
    }

    public void setFpoId(Integer fpoId) {
        this.fpoId = fpoId;
    }

    public Integer getAutomaticStepCalculation() {
        return automaticStepCalculation;
    }

    public void setAutomaticStepCalculation(Integer automaticStepCalculation) {
        this.automaticStepCalculation = automaticStepCalculation;
    }

    public Integer getGlPostedInd() {
        return glPostedInd;
    }

    public void setGlPostedInd(Integer glPostedInd) {
        this.glPostedInd = glPostedInd;
    }

    public Integer getFirmedInd() {
        return firmedInd;
    }

    public void setFirmedInd(Integer firmedInd) {
        this.firmedInd = firmedInd;
    }

    public Integer getFiniteScheduledInd() {
        return finiteScheduledInd;
    }

    public void setFiniteScheduledInd(Integer finiteScheduledInd) {
        this.finiteScheduledInd = finiteScheduledInd;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public String getMigratedBatchInd() {
        return migratedBatchInd;
    }

    public void setMigratedBatchInd(String migratedBatchInd) {
        this.migratedBatchInd = migratedBatchInd;
    }

    public Integer getEnforceStepDependency() {
        return enforceStepDependency;
    }

    public void setEnforceStepDependency(Integer enforceStepDependency) {
        this.enforceStepDependency = enforceStepDependency;
    }

    public Short getTerminatedInd() {
        return terminatedInd;
    }

    public void setTerminatedInd(Short terminatedInd) {
        this.terminatedInd = terminatedInd;
    }

    public String getEnhancedPiInd() {
        return enhancedPiInd;
    }

    public void setEnhancedPiInd(String enhancedPiInd) {
        this.enhancedPiInd = enhancedPiInd;
    }

    public Integer getLaboratoryInd() {
        return laboratoryInd;
    }

    public void setLaboratoryInd(Integer laboratoryInd) {
        this.laboratoryInd = laboratoryInd;
    }

    public Integer getMoveOrderHeaderId() {
        return moveOrderHeaderId;
    }

    public void setMoveOrderHeaderId(Integer moveOrderHeaderId) {
        this.moveOrderHeaderId = moveOrderHeaderId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getTerminateReasonId() {
        return terminateReasonId;
    }

    public void setTerminateReasonId(Integer terminateReasonId) {
        this.terminateReasonId = terminateReasonId;
    }

    public String getFixedProcessLossApplied() {
        return fixedProcessLossApplied;
    }

    public void setFixedProcessLossApplied(String fixedProcessLossApplied) {
        this.fixedProcessLossApplied = fixedProcessLossApplied;
    }

    public Integer getJaOspBatch() {
        return jaOspBatch;
    }

    public void setJaOspBatch(Integer jaOspBatch) {
        this.jaOspBatch = jaOspBatch;
    }

    public Integer getBatchPlanQty() {
        return batchPlanQty;
    }

    public void setBatchPlanQty(Integer batchPlanQty) {
        this.batchPlanQty = batchPlanQty;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public OrganizationUnit getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(OrganizationUnit businessGroup) {
        this.businessGroup = businessGroup;
    }
    
    
    public List<GmeMaterialDetail> getGmeMaterialDetailList() {
        return gmeMaterialDetailList;
    }

    public void setGmeMaterialDetailList(List<GmeMaterialDetail> gmeMaterialDetailList) {
        this.gmeMaterialDetailList = gmeMaterialDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchId != null ? batchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GmeBatchHeader)) {
            return false;
        }
        GmeBatchHeader other = (GmeBatchHeader) object;
        if ((this.batchId == null && other.batchId != null) || (this.batchId != null && !this.batchId.equals(other.batchId))) {
            return false;
        }
        return true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequireComplDate(Date requireComplDate) {
        this.requireComplDate = requireComplDate;
    }

    public Date getRequireComplDate() {
        return requireComplDate;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.batch.GmeBatchHeader[ batchId=" + batchId + " ]";
    }

    @PrePersist
    private void onPrePersist()
    {
        lastUpdatedBy = 1 ;
        
        createdBy = 1 ;
        
        lastUpdateDate = new Date() ;
        
        creationDate = new Date() ;
    }
}
