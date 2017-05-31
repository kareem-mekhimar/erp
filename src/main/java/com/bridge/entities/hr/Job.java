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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "PER_JOBS")
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT p FROM Job p")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "job")
    @TableGenerator(name = "job", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "Job", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "JOB_ID")
    private Integer jobId;
    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID", referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroupId;
    @Column(name = "JOB_DEFINITION_ID")
    private Integer jobDefinitionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;
    @Size(max = 2000)
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;
    @Size(max = 240)
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
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

    @Column(name = "JOB_CODE")
    private String jobCode;
    
    @Transient
    private boolean activeJob;

    public Job() {
    }

    public Job(Integer jobId) {
        this.jobId = jobId;
    }

    public Job(Integer jobId, OrganizationUnit businessGroupId, Integer jobDefinitionId, Date dateFrom) {
        this.jobId = jobId;
        this.businessGroupId = businessGroupId;
        this.jobDefinitionId = jobDefinitionId;
        this.dateFrom = dateFrom;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public Integer getJobDefinitionId() {
        return jobDefinitionId;
    }

    public void setJobDefinitionId(Integer jobDefinitionId) {
        this.jobDefinitionId = jobDefinitionId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.hr.PerJob[ jobId=" + jobId + " ]";
    }

    public boolean isActiveJob() {
        // 10/5/2017:
        // new code:
        return (new Date()).compareTo(dateFrom) > 0 ;
        // old code:
        //return activeJob;
    }

    public void setActiveJob(boolean activeJob) {
        this.activeJob = activeJob;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    
    
}
