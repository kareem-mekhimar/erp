/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "HR_ALL_ORGANIZATION_UNITS")
@NamedQueries({
    @NamedQuery(name = "HrOrganizationUnit.findAll", query = "SELECT h FROM HrOrganizationUnit h")})
public class HrOrganizationUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @GeneratedValue(strategy = GenerationType.TABLE,generator = "hrOrg")
    @TableGenerator(name = "hrOrg",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "HrOrg",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORGANIZATION_ID")
    private BigDecimal organizationId;
    @Size(max = 32)
    @Column(name = "MODULE_ID")
    private String moduleId;
    @Column(name = "EFFECTIVE_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveStartDate;
    @Column(name = "EFFECTIVE_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveEndDate;
    @Column(name = "ACTION_OCCURRENCE_ID")
    private BigInteger actionOccurrenceId;
    @Column(name = "BUSINESS_GROUP_ID")
    private BigInteger businessGroupId;
    @Column(name = "LEGAL_ENTITY_ID")
    private BigInteger legalEntityId;
    @Column(name = "ESTABLISHMENT_ID")
    private BigInteger establishmentId;
    @Column(name = "INTERNAL_EXTERNAL_FLAG")
    private BigInteger internalExternalFlag;
    @Column(name = "ORGANIZATION_TYPE")
    private BigInteger organizationType;
    @Size(max = 100)
    @Column(name = "INTERNAL_ADDRESS_LINE")
    private String internalAddressLine;
    @Column(name = "COST_ALLOCATION_KEYFLEX_ID")
    private BigInteger costAllocationKeyflexId;
    @Column(name = "LOCATION_ID")
    private BigInteger locationId;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private BigInteger objectVersionNumber;
    @Size(max = 50)
    @Column(name = "ORGANIZATION_CODE")
    private String organizationCode;
    @Size(max = 100)
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Column(name = "CREATED_BY")
    private BigInteger createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATED_BY")
    private BigInteger lastUpdatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATE_LOGIN")
    private BigInteger lastUpdateLogin;

    public HrOrganizationUnit() {
    }

    public HrOrganizationUnit(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public BigInteger getActionOccurrenceId() {
        return actionOccurrenceId;
    }

    public void setActionOccurrenceId(BigInteger actionOccurrenceId) {
        this.actionOccurrenceId = actionOccurrenceId;
    }

    public BigInteger getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(BigInteger businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public BigInteger getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(BigInteger legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public BigInteger getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(BigInteger establishmentId) {
        this.establishmentId = establishmentId;
    }

    public BigInteger getInternalExternalFlag() {
        return internalExternalFlag;
    }

    public void setInternalExternalFlag(BigInteger internalExternalFlag) {
        this.internalExternalFlag = internalExternalFlag;
    }

    public BigInteger getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(BigInteger organizationType) {
        this.organizationType = organizationType;
    }

    public String getInternalAddressLine() {
        return internalAddressLine;
    }

    public void setInternalAddressLine(String internalAddressLine) {
        this.internalAddressLine = internalAddressLine;
    }

    public BigInteger getCostAllocationKeyflexId() {
        return costAllocationKeyflexId;
    }

    public void setCostAllocationKeyflexId(BigInteger costAllocationKeyflexId) {
        this.costAllocationKeyflexId = costAllocationKeyflexId;
    }

    public BigInteger getLocationId() {
        return locationId;
    }

    public void setLocationId(BigInteger locationId) {
        this.locationId = locationId;
    }

    public BigInteger getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(BigInteger objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigInteger getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigInteger lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationId != null ? organizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrOrganizationUnit)) {
            return false;
        }
        HrOrganizationUnit other = (HrOrganizationUnit) object;
        if ((this.organizationId == null && other.organizationId != null) || (this.organizationId != null && !this.organizationId.equals(other.organizationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.hr.HrAllOrganizationUnits[ organizationId=" + organizationId + " ]";
    }
    
}
