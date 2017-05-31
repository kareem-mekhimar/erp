/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.coa;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.StructureStatus;
import com.bridge.enums.Separators;
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
@Table(name = "GL_COA_STRUCTURE")
@NamedQueries({
    @NamedQuery(name = "CoaStructure.findAll", query = "SELECT c FROM CoaStructure c")})
public class CoaStructure implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CoaStr")
    @TableGenerator(name = "CoaStr", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "CoaStructure", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "COA_ID")
    private Integer coaId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COA_NAME")
    private String coaName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENABLED_FLAG_ID")
    private boolean enabledFlagId;
    @Size(max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Size(max = 10)
    @Column(name = "COA_CODE")
    private String coaCode;
    @Column(name="SEGMENTS_SEPARATOR")
    private Separators segmentsSeparator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coaStructure")
    private List<CoaSegment> coaSegmentList;
    @Column(name="COA_STATUS")
    private StructureStatus coaStatus;
    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit organization;

    @Column(name = "HAS_CHILDS_FLAG")
    private boolean hasAccounts ;

    
    public CoaStructure() {
    }

    public CoaStructure(Integer  coaId) {
        this.coaId = coaId;
    }

    public CoaStructure(Integer coaId, String coaName, boolean enabledFlagId) {
        this.coaId = coaId;
        this.coaName = coaName;
        this.enabledFlagId = enabledFlagId;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public boolean getEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
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

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public List<CoaSegment> getCoaSegmentList() {
        return coaSegmentList;
    }

    public void setCoaSegmentList(List<CoaSegment> coaSegmentList) {
        this.coaSegmentList = coaSegmentList;
    }

    public OrganizationUnit getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationUnit organization) {
        this.organization = organization;
    }

    public Separators getSegmentsSeparator() {
        return segmentsSeparator;
    }

    public void setSegmentsSeparator(Separators segmentsSeparator) {
        this.segmentsSeparator = segmentsSeparator;
    }

    public StructureStatus getCoaStatus() {
        return coaStatus;
    }

    public void setCoaStatus(StructureStatus coaStatus) {
        this.coaStatus = coaStatus;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coaId != null ? coaId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoaStructure)) {
            return false;
        }
        CoaStructure other = (CoaStructure) object;
        if ((this.coaId == null && other.coaId != null) || (this.coaId != null && !this.coaId.equals(other.coaId))) {
            return false;
        }
        return true;
    }

    public boolean isHasAccounts() {
        return hasAccounts;
    }

    public void setHasAccounts(boolean hasAccounts) {
        this.hasAccounts = hasAccounts;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.coa.CoaStructure[ coaId=" + coaId + " ]";
    }
    
}
