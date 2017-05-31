/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.periods;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "GL_PERIOD_SETS")
@NamedQueries({
    @NamedQuery(name = "GlPeriodSet.findAll", query = "SELECT g FROM GlPeriodSet g")})
public class GlPeriodSet implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "periodSet")
    @TableGenerator(name = "periodSet",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PeriodSet",valueColumnName = "CURRENT_VALUE")
    @Column(name = "PERIOD_SET_ID")
    private Integer periodSetId;
    
    @Size(max = 100)
    @Column(name = "PERIOD_SET_NAME")
    private String periodSetName;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Size(max = 64)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Size(max = 64)
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Size(max = 32)
    @Column(name = "LAST_UPDATE_LOGIN")
    private String lastUpdateLogin;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 1)
    @Column(name = "SECURITY_FLAG")
    private String securityFlag;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;
    @Column(name = "ENTERPRISE_ID")
    private Integer enterpriseId;
    
    @ManyToOne
    @JoinColumn(name = "PERIOD_TYPE_ID")
    private GlPeriodType periodType;

    @OneToMany(mappedBy = "periodSet")
    private List<GlPeriodStatus> periods ;

    @OneToOne(mappedBy = "periodSet")
    private GlLedger ledger;
    
    public GlPeriodSet() {
    }

    public GlPeriodSet(Integer periodSetId) {
        this.periodSetId = periodSetId;
    }

    public Integer getPeriodSetId() {
        return periodSetId;
    }

    public void setPeriodSetId(Integer periodSetId) {
        this.periodSetId = periodSetId;
    }

    public String getPeriodSetName() {
        return periodSetName;
    }

    public void setPeriodSetName(String periodSetName) {
        this.periodSetName = periodSetName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurityFlag() {
        return securityFlag;
    }

    public void setSecurityFlag(String securityFlag) {
        this.securityFlag = securityFlag;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public GlPeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(GlPeriodType periodType) {
        this.periodType = periodType;
    }

    public GlLedger getLedger() {
        return ledger;
    }

    public void setLedger(GlLedger ledger) {
        this.ledger = ledger;
    }

    public List<GlPeriodStatus> getPeriods() {
        return periods;
    }

    public void setPeriods(List<GlPeriodStatus> periods) {
        this.periods = periods;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodSetId != null ? periodSetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlPeriodSet)) {
            return false;
        }
        GlPeriodSet other = (GlPeriodSet) object;
        if ((this.periodSetId == null && other.periodSetId != null) || (this.periodSetId != null && !this.periodSetId.equals(other.periodSetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.periods.GlPeriodSet[ periodSetId=" + periodSetId + " ]";
    }
    
}
