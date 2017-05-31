/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.coa;

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
import javax.persistence.OneToOne;
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
@Table(name = "GL_COA_SEGMENT_DETAILS")
@NamedQueries({
    @NamedQuery(name = "CoaSegmentLine.findAll", query = "SELECT c FROM CoaSegmentLine c")})
public class CoaSegmentLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SegLine")
    @TableGenerator(name = "SegLine", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "SegmentLine", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEGMENT_DETAIL_ID")
    private Integer segmentDetailId;
    
    @Size(max = 25)
    @Column(name = "SEGMENT_NAME")
    private String segmentDetailName;
    
    @Size(max = 25)
    @Column(name = "SEGMENT_CODE")
    private String segmentDetailCode;
    
    @Size(max = 250)
    @Column(name = "SEGEMTN_DESCRIPTION")
    private String segemtnDetailDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENABLED_FLAG")
    private boolean enabledFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARENT_ACCOUNT_FLAG")
    private boolean parentAccountFlag;
    @ManyToOne
    @JoinColumn(name = "PARENT_ACCOUNT_ID",referencedColumnName = "SEGMENT_DETAIL_ID")
    private CoaSegmentLine parentAccountId;
    @ManyToOne
    @JoinColumn(name = "DEPENDS_ON_DETAIL_ID",referencedColumnName = "SEGMENT_DETAIL_ID")
    private CoaSegmentLine dependsOnDetailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALLOW_BUDGET")
    private boolean allowBudgetFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALLOW_POST")
    private boolean allowPostFlag;
    @Column(name = "ACCOUNT_TYPE")
    private Integer accountType;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Column(name="THIRD_PARTY_CONTROL_ACCOUNT")
    private boolean thirdPartyControlAccount;
    @Column(name="RECONCILE")
    private boolean reconcile;
    @JoinColumn(name = "SEGMENT_ID", referencedColumnName = "SEGMENT_ID")
    @ManyToOne(optional = false)
    private CoaSegment segment;

    @Column(name = "INVOLVED_IN_ACCOUNT")
    private boolean involvedInAccount ;
    
    @Column(name="USED_IN_ACCOUNTS")
    private Integer usedInAccounts = 0;
    
    public CoaSegmentLine() {
    }

    public CoaSegmentLine(Integer  segmentDetailId) {
        this.segmentDetailId = segmentDetailId;
    }


    public Integer  getSegmentDetailId() {
        return segmentDetailId;
    }

    public void setSegmentDetailId(Integer  segmentDetailId) {
        this.segmentDetailId = segmentDetailId;
    }

    public String getSegmentDetailName() {
        return segmentDetailName;
    }

    public void setSegmentDetailName(String segmentDetailName) {
        this.segmentDetailName = segmentDetailName;
    }

    public String getSegmentDetailCode() {
        return segmentDetailCode;
    }

    public void setSegmentDetailCode(String segmentDetailCode) {
        this.segmentDetailCode = segmentDetailCode;
    }

    public String getSegemtnDetailDescription() {
        return segemtnDetailDescription;
    }

    public void setSegemtnDetailDescription(String segemtnDetailDescription) {
        this.segemtnDetailDescription = segemtnDetailDescription;
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

    public Integer getAccountType() {
        return accountType;
    }

    public CoaSegment getSegment() {
        return segment;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public void setAllowBudgetFlag(boolean allowBudgetFlag) {
        this.allowBudgetFlag = allowBudgetFlag;
    }

    public void setAllowPostFlag(boolean allowPostFlag) {
        this.allowPostFlag = allowPostFlag;
    }

    public void setSegment(CoaSegment segment) {
        this.segment = segment;
    }

    
    public void setEnabledFlag(boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public void setParentAccountFlag(boolean parentAccountFlag) {
        this.parentAccountFlag = parentAccountFlag;
    }

    public CoaSegmentLine getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(CoaSegmentLine parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public CoaSegmentLine getDependsOnDetailId() {
        return dependsOnDetailId;
    }

    public void setDependsOnDetailId(CoaSegmentLine dependsOnDetailId) {
        this.dependsOnDetailId = dependsOnDetailId;
    }
    
    
    
    

    public boolean getAllowBudgetFlag() {
        return allowBudgetFlag;
    }

    public boolean getAllowPostFlag() {
        return allowPostFlag;
    }

    public boolean getEnabledFlag() {
        return enabledFlag;
    }

    public boolean getParentAccountFlag() {
        return parentAccountFlag;
    }

    public boolean getInvolvedInAccount() {
        return involvedInAccount;
    }

    public void setInvolvedInAccount(boolean involvedInAccount) {
        this.involvedInAccount = involvedInAccount;
    }

    public boolean getThirdPartyControlAccount() {
        return thirdPartyControlAccount;
    }

    public void setThirdPartyControlAccount(boolean thirdPartyControlAccount) {
        this.thirdPartyControlAccount = thirdPartyControlAccount;
    }

    public boolean getReconcile() {
        return reconcile;
    }

    public void setReconcile(boolean reconcile) {
        this.reconcile = reconcile;
    }

    public Integer getUsedInAccounts() {
        return usedInAccounts;
    }

    public void setUsedInAccounts(Integer usedInAccounts) {
        this.usedInAccounts = usedInAccounts;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (segmentDetailId != null ? segmentDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoaSegmentLine)) {
            return false;
        }
        CoaSegmentLine other = (CoaSegmentLine) object;
        if ((this.segmentDetailId == null && other.segmentDetailId != null) || (this.segmentDetailId != null && !this.segmentDetailId.equals(other.segmentDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.coa.CoaSegmentLine[ segmentDetailId=" + segmentDetailId + " ]";
    }
    
}
