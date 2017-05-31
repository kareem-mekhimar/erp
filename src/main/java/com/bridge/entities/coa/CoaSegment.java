/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.coa;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "GL_COA_STRUCTURE_SEGMENTS")
@NamedQueries({
    @NamedQuery(name = "CoaSegment.findAll", query = "SELECT c FROM CoaSegment c")})
public class CoaSegment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CoaSeg")
    @TableGenerator(name = "CoaSeg", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "CoaSegment", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEGMENT_ID")
    private Integer  segmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "SEGMENT_NAME")
    private String segmentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "SEGMENT_CODE")
    private String segmentCode;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQUENCE_NUMBER")
    private Integer sequenceNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXIMUM_SIZE")
    private Integer maximumSize;
   
    @Column(name = "MINIMUM_VALUE")
    private Integer minimumValue ;
  
    @Column(name = "MAXIMUM_VALUE")
    private Integer maximumValue;
    @Column(name = "NUMBERS_ONLY_FLAG_ID")
    private boolean  numbersOnlyFlagId;
    @Column(name = "UPPERCASE_ONLY_FLAG_ID")
    private boolean  uppercaseOnlyFlagId;
    @Column(name = "RIGHT_JUSTIFY_ZERO_FILL_NUM")
    private boolean  rightJustifyZeroFillNum;
    @Column(name = "DEPENDENT_TYPE_ID")
    private boolean dependentTypeId;
  
    @Column(name = "DEPENDENT_ON_SEGMENT_ID")
    private Integer  dependentOnSegmentId;
    
    @Column(name="BALANCING_SEGMENT")
    private boolean balancingSegment;
    @Column(name="COST_CENTER_SEGMENT")   
    private boolean costCenterSegment;
    @Column(name="INTERCOMPANY_SEGMENT")    
    private boolean intercompanySegment;
    @Column(name="MANAGEMENT_SEGMENT")    
    private boolean managementSegment;
    @Column(name="NATURAL_ACCOUNT_SEGMENT")    
    private boolean naturalAccountSegment;
    @Column(name="SECONDARY_TRACKING_SEGMENT")
    private boolean secondaryTrackingSegment;

    @Column(name="DEPENDENT_ON_SEGMENT_SEQUENCE")
    private Integer dependentOnSegmentSequence;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SEGMENT_LABLE")
    private String segmentLable;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "COA_ID", referencedColumnName = "COA_ID")
    private CoaStructure coaStructure;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "segment")
    private List<CoaSegmentLine> coaSegmentLineList;

    @Column(name = "HAS_CHILDREN")
    private boolean hasChildren ;
    
    
    
    
    public CoaSegment() {
    }

    public CoaSegment(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public CoaSegment(Integer segmentId, String segmentName, String segmentCode, Integer sequenceNumber, Integer maximumSize, String segmentLable) {
        this.segmentId = segmentId;
        this.segmentName = segmentName;
        this.segmentCode = segmentCode;
        this.sequenceNumber = sequenceNumber;
        this.maximumSize = maximumSize;
        this.segmentLable = segmentLable;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Integer maximumSize) {
        this.maximumSize = maximumSize;
    }

    public Integer getMaximumValue() {
        return maximumValue;
    }

    public Integer getMinimumValue() {
        return minimumValue;
    }

    public void setMaximumValue(Integer maximumValue) {
        this.maximumValue = maximumValue;
    }

    public void setMinimumValue(Integer minimumValue) {
        this.minimumValue = minimumValue;
    }

    
    public boolean getNumbersOnlyFlagId() {
        return numbersOnlyFlagId;
    }

    public void setNumbersOnlyFlagId(boolean numbersOnlyFlagId) {
        this.numbersOnlyFlagId = numbersOnlyFlagId;
    }

    public boolean getUppercaseOnlyFlagId() {
        return uppercaseOnlyFlagId;
    }

    public void setUppercaseOnlyFlagId(boolean uppercaseOnlyFlagId) {
        this.uppercaseOnlyFlagId = uppercaseOnlyFlagId;
    }

    public boolean getRightJustifyZeroFillNum() {
        return rightJustifyZeroFillNum;
    }

    public void setRightJustifyZeroFillNum(boolean rightJustifyZeroFillNum) {
        this.rightJustifyZeroFillNum = rightJustifyZeroFillNum;
    }

    public boolean getDependentTypeId() {
        return dependentTypeId;
    }

    public void setDependentTypeId(boolean dependentTypeId) {
        this.dependentTypeId = dependentTypeId;
    }

    public Integer  getDependentOnSegmentId() {
        return dependentOnSegmentId;
    }

    public void setDependentOnSegmentId(Integer dependentOnSegmentId) {
        this.dependentOnSegmentId = dependentOnSegmentId;
    }

    public String getSegmentLable() {
        return segmentLable;
    }

    public void setSegmentLable(String segmentLable) {
        this.segmentLable = segmentLable;
    }

    public CoaStructure getCoaStructure() {
        return coaStructure;
    }

    public void setCoaStructure(CoaStructure coaStructure) {
        this.coaStructure = coaStructure;
    }

    public List<CoaSegmentLine> getCoaSegmentLineList() {
        return coaSegmentLineList;
    }

    public void setCoaSegmentLineList(List<CoaSegmentLine> coaSegmentLineList) {
        this.coaSegmentLineList = coaSegmentLineList;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean getBalancingSegment() {
        return balancingSegment;
    }

    public void setBalancingSegment(boolean balancingSegment) {
        this.balancingSegment = balancingSegment;
    }

    public boolean getCostCenterSegment() {
        return costCenterSegment;
    }

    public void setCostCenterSegment(boolean costCenterSegment) {
        this.costCenterSegment = costCenterSegment;
    }

    public boolean getIntercompanySegment() {
        return intercompanySegment;
    }

    public void setIntercompanySegment(boolean intercompanySegment) {
        this.intercompanySegment = intercompanySegment;
    }

    public boolean getManagementSegment() {
        return managementSegment;
    }

    public void setManagementSegment(boolean managementSegment) {
        this.managementSegment = managementSegment;
    }

    public boolean getNaturalAccountSegment() {
        return naturalAccountSegment;
    }

    public void setNaturalAccountSegment(boolean naturalAccountSegment) {
        this.naturalAccountSegment = naturalAccountSegment;
    }

    public boolean getSecondaryTrackingSegment() {
        return secondaryTrackingSegment;
    }

    public void setSecondaryTrackingSegment(boolean secondaryTrackingSegment) {
        this.secondaryTrackingSegment = secondaryTrackingSegment;
    }

    public Integer getDependentOnSegmentSequence() {
        return dependentOnSegmentSequence;
    }

    public void setDependentOnSegmentSequence(Integer dependentOnSegmentSequence) {
        this.dependentOnSegmentSequence = dependentOnSegmentSequence;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (segmentId != null ? segmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoaSegment)) {
            return false;
        }
        CoaSegment other = (CoaSegment) object;
        if ((this.segmentId == null && other.segmentId != null) || (this.segmentId != null && !this.segmentId.equals(other.segmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.coa.CoaSegment[ segmentId=" + segmentId + " ]";
    }
    
}
