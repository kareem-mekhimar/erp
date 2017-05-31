/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hr;

import com.bridge.entities.organization.OrganizationUnit;
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
@Table(name = "PER_ALL_POSITIONS")
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p")})
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "pos")
    @TableGenerator(name = "pos",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Position",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSITION_ID")
    private Integer positionId;
    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID",referencedColumnName ="ORG_UNIT_ID" )
    private OrganizationUnit businessGroupId;
    @Basic(optional = false)
    @NotNull
    @OneToOne
    @JoinColumn(name = "JOB_ID",referencedColumnName = "JOB_ID")
    private Job jobId;
    
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    @Column(name = "SUCCESSOR_POSITION_ID")
    private Integer successorPositionId;
    @Column(name = "RELIEF_POSITION_ID")
    private Integer reliefPositionId;
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID",referencedColumnName ="LOCATION_ID" )
    private Location locationId;
    @Column(name = "POSITION_DEFINITION_ID")
    private Integer positionDefinitionId;
    @Column(name = "DATE_EFFECTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEffective;
    @Size(max = 2000)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DATE_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @Column(name = "FREQUENCY")
    private Integer frequency;
    @Size(max = 240)
    @Column(name = "POSITION_NAME")
    private String positionName;
    @Column(name = "PROBATION_PERIOD")
    private Integer probationPeriod;
    @Size(max = 30)
    @Column(name = "PROBATION_PERIOD_UNITS")
    private String probationPeriodUnits;
    @Size(max = 30)
    @Column(name = "REPLACEMENT_REQUIRED_FLAG")
    private String replacementRequiredFlag;
    @Size(max = 5)
    @Column(name = "TIME_NORMAL_FINISH")
    private String timeNormalFinish;
    @Size(max = 5)
    @Column(name = "TIME_NORMAL_START")
    private String timeNormalStart;
    @Column(name = "WORKING_HOURS")
    private Integer workingHours;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "POSITION_CODE")
    private String positionCode;
    
    
    public Position() {
    }

    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    public Position(Integer positionId, OrganizationUnit businessGroupId, Job jobId) {
        this.positionId = positionId;
        this.businessGroupId = businessGroupId;
        this.jobId = jobId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSuccessorPositionId() {
        return successorPositionId;
    }

    public void setSuccessorPositionId(Integer successorPositionId) {
        this.successorPositionId = successorPositionId;
    }

    public Integer getReliefPositionId() {
        return reliefPositionId;
    }

    public void setReliefPositionId(Integer reliefPositionId) {
        this.reliefPositionId = reliefPositionId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public Integer getPositionDefinitionId() {
        return positionDefinitionId;
    }

    public void setPositionDefinitionId(Integer positionDefinitionId) {
        this.positionDefinitionId = positionDefinitionId;
    }

    public Date getDateEffective() {
        return dateEffective;
    }

    public void setDateEffective(Date dateEffective) {
        this.dateEffective = dateEffective;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getProbationPeriod() {
        return probationPeriod;
    }

    public void setProbationPeriod(Integer probationPeriod) {
        this.probationPeriod = probationPeriod;
    }

    public String getProbationPeriodUnits() {
        return probationPeriodUnits;
    }

    public void setProbationPeriodUnits(String probationPeriodUnits) {
        this.probationPeriodUnits = probationPeriodUnits;
    }

    public String getReplacementRequiredFlag() {
        return replacementRequiredFlag;
    }

    public void setReplacementRequiredFlag(String replacementRequiredFlag) {
        this.replacementRequiredFlag = replacementRequiredFlag;
    }

    public String getTimeNormalFinish() {
        return timeNormalFinish;
    }

    public void setTimeNormalFinish(String timeNormalFinish) {
        this.timeNormalFinish = timeNormalFinish;
    }

    public String getTimeNormalStart() {
        return timeNormalStart;
    }

    public void setTimeNormalStart(String timeNormalStart) {
        this.timeNormalStart = timeNormalStart;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
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

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.hr.Position[ positionId=" + positionId + " ]";
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    
    
    
}
