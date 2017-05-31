/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.coa;

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
@Table(name = "GL_CODE_COMBINATIONS")
public class GlCodeCombination implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "codeCombination")
    @TableGenerator(name = "codeCombination", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "CoaCodeCombination", valueColumnName = "CURRENT_VALUE")    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE_COMBINATION_ID")
    private Integer codeCombinationId;
    
    @Size(max = 100)
    @Column(name = "CODE_COMBINATION_SHORT_NAME")
    private String codeCombinationShortName;
    
    @Size(max = 250)
    @Column(name = "CODE_COMBINATION")
    private String codeCombination;
    
    @Size(max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    
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
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_TYPE_ID")
    private Integer accountTypeId = 1;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENABLED_FLAG_ID")
    private boolean enabledFlag = true;

    @ManyToOne
    @JoinColumn(name = "CHART_OF_ACCOUNTS_ID")
    private CoaStructure coaStructure ;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTL_POSTING_ALLOWED_FLAG_ID")
    private boolean dtlPostingAllowedFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTL_BUDGETING_ALLOWED_FLAG_ID")
    private boolean dtlBudgetingAllowedFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMMARY_FLAG_ID")
    private boolean summaryFlag;
    @Column(name = "TEMPLATE_ID")
    private Integer templateId;
    @Column(name = "ALLOCATION_CREATE_FLAG_ID")
    private Integer allocationCreateFlagId;
    @Size(max = 150)
    @Column(name = "CONTEXT")
    private String context;
    @Column(name = "JGZZ_RECON_FLAG_ID")
    private Integer jgzzReconFlagId;
    @Size(max = 30)
    @Column(name = "JGZZ_RECON_CONTEXT")
    private String jgzzReconContext;
    @Column(name = "PRESERVE_FLAG_ID")
    private Integer preserveFlagId;
    @Column(name = "REFRESH_FLAG_ID")
    private Integer refreshFlagId;
    @Column(name = "IGI_BALANCED_BUDGET_FLAG_ID")
    private Integer igiBalancedBudgetFlagId;
    @Column(name = "COMPANY_COST_CENTER_ORG_ID")
    private Integer companyCostCenterOrgId;
    @Column(name = "REVALUATION_ID")
    private Integer revaluationId;
    @Size(max = 20)
    @Column(name = "LEDGER_SEGMENT")
    private String ledgerSegment;
    @Column(name = "LEDGER_TYPE_CODE_ID")
    private Integer ledgerTypeCodeId;
    @Column(name = "ALTERNATE_CODE_COMBINATION_ID")
    private Integer alternateCodeCombinationId;
   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="GL_CODE_COMBINATION_DETAILS",joinColumns = @JoinColumn(name = "CODE_COMBINATION_ID",referencedColumnName = "CODE_COMBINATION_ID"),inverseJoinColumns =@JoinColumn(name = "SEGMENT_DETAIL_ID",referencedColumnName = "SEGMENT_DETAIL_ID") )
    private List<CoaSegmentLine> combinationSegmentList;
    
    public GlCodeCombination() {
    }

    public GlCodeCombination(Integer codeCombinationId) {
        this.codeCombinationId = codeCombinationId;
    }

    public Integer getCodeCombinationId() {
        return codeCombinationId;
    }

    public void setCodeCombinationId(Integer codeCombinationId) {
        this.codeCombinationId = codeCombinationId;
    }

    public String getCodeCombinationShortName() {
        return codeCombinationShortName;
    }

    public void setCodeCombinationShortName(String codeCombinationShortName) {
        this.codeCombinationShortName = codeCombinationShortName;
    }

    public String getCodeCombination() {
        return codeCombination;
    }

    public void setCodeCombination(String codeCombination) {
        this.codeCombination = codeCombination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }


    public CoaStructure getCoaStructure() {
        return coaStructure;
    }

    public void setCoaStructure(CoaStructure coaStructure) {
        this.coaStructure = coaStructure;
    }


    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getAllocationCreateFlagId() {
        return allocationCreateFlagId;
    }

    public void setAllocationCreateFlagId(Integer allocationCreateFlagId) {
        this.allocationCreateFlagId = allocationCreateFlagId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getJgzzReconFlagId() {
        return jgzzReconFlagId;
    }

    public void setJgzzReconFlagId(Integer jgzzReconFlagId) {
        this.jgzzReconFlagId = jgzzReconFlagId;
    }

    public String getJgzzReconContext() {
        return jgzzReconContext;
    }

    public void setJgzzReconContext(String jgzzReconContext) {
        this.jgzzReconContext = jgzzReconContext;
    }

    public Integer getPreserveFlagId() {
        return preserveFlagId;
    }

    public void setPreserveFlagId(Integer preserveFlagId) {
        this.preserveFlagId = preserveFlagId;
    }

    public Integer getRefreshFlagId() {
        return refreshFlagId;
    }

    public void setRefreshFlagId(Integer refreshFlagId) {
        this.refreshFlagId = refreshFlagId;
    }

    public Integer getIgiBalancedBudgetFlagId() {
        return igiBalancedBudgetFlagId;
    }

    public void setIgiBalancedBudgetFlagId(Integer igiBalancedBudgetFlagId) {
        this.igiBalancedBudgetFlagId = igiBalancedBudgetFlagId;
    }

    public Integer getCompanyCostCenterOrgId() {
        return companyCostCenterOrgId;
    }

    public void setCompanyCostCenterOrgId(Integer companyCostCenterOrgId) {
        this.companyCostCenterOrgId = companyCostCenterOrgId;
    }

    public Integer getRevaluationId() {
        return revaluationId;
    }

    public void setRevaluationId(Integer revaluationId) {
        this.revaluationId = revaluationId;
    }

    public String getLedgerSegment() {
        return ledgerSegment;
    }

    public void setLedgerSegment(String ledgerSegment) {
        this.ledgerSegment = ledgerSegment;
    }

    public Integer getLedgerTypeCodeId() {
        return ledgerTypeCodeId;
    }

    public void setLedgerTypeCodeId(Integer ledgerTypeCodeId) {
        this.ledgerTypeCodeId = ledgerTypeCodeId;
    }

    public Integer getAlternateCodeCombinationId() {
        return alternateCodeCombinationId;
    }

    public void setAlternateCodeCombinationId(Integer alternateCodeCombinationId) {
        this.alternateCodeCombinationId = alternateCodeCombinationId;
    }

    public boolean isDtlBudgetingAllowedFlag() {
        return dtlBudgetingAllowedFlag;
    }

    public boolean isDtlPostingAllowedFlag() {
        return dtlPostingAllowedFlag;
    }

    public boolean isEnabledFlag() {
        return enabledFlag;
    }

    public boolean isSummaryFlag() {
        return summaryFlag;
    }

    public void setDtlBudgetingAllowedFlag(boolean dtlBudgetingAllowedFlag) {
        this.dtlBudgetingAllowedFlag = dtlBudgetingAllowedFlag;
    }

    public List<CoaSegmentLine> getCombinationSegmentList() {
        return combinationSegmentList;
    }

    public void setCombinationSegmentList(List<CoaSegmentLine> combinationSegmentList) {
        this.combinationSegmentList = combinationSegmentList;
    }

 

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeCombinationId != null ? codeCombinationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlCodeCombination)) {
            return false;
        }
        GlCodeCombination other = (GlCodeCombination) object;
        if ((this.codeCombinationId == null && other.codeCombinationId != null) || (this.codeCombinationId != null && !this.codeCombinationId.equals(other.codeCombinationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.coa.GlCodeCombination[ codeCombinationId=" + codeCombinationId + " ]";
    }
    
    @PrePersist
    public void onPrePersist()
    {
        lastUpdateDate = new Date() ;
        
        lastUpdatedBy = 1 ;
        
        createdBy = 1 ;
       
    }
    
}
