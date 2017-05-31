/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.ledger;

import com.bridge.enums.GLSetupStepStatus;
import com.bridge.enums.GLSetupSteps;
import com.bridge.enums.GlSetupStatus;
import com.bridge.enums.LedgerObjectTypeCode;
import java.io.Serializable;
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
@Table(name = "GL_LEDGER_CONFIG_DETAILS")
@NamedQueries({
    @NamedQuery(name = "GlLedgerConfigDetail.findAll", query = "SELECT g FROM GlLedgerConfigDetail g")})
public class GlLedgerConfigDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "glConfDetails")
    @TableGenerator(name = "glConfDetails",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "GlLedgerConfigDetails",valueColumnName = "CURRENT_VALUE")
    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "CONFIGURATION_ID")
    private GlLedger ledger;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "OBJECT_TYPE_CODE")
    private LedgerObjectTypeCode objectTypeCode;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "OBJECT_ID")
    private Integer objectId;
    
    @Size(min = 1, max = 240)
    @Column(name = "OBJECT_NAME")
    private String objectName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SETUP_STEP_CODE")
    private GLSetupSteps setupStepCode;
    
    @Size(max = 30)
    @Column(name = "NEXT_ACTION_CODE")
    private String nextActionCode;
    
    @Column(name = "STATUS_CODE")
    private GlSetupStatus statusCode = GlSetupStatus.IN_PROGRESS;
   
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 250)
    @Column(name = "SETUP_STEP_SHORT_DESC")
    private String setupStepShortDesc;
    @Size(max = 500)
    @Column(name = "SETUP_STEP_FULL_DESC")
    private String setupStepFullDesc;

    public GlLedgerConfigDetail() {
    }
    
    
    public GlLedgerConfigDetail(GLSetupSteps step,int ledgerId,String configLedgerName,String shortDesc , String fullDesc)
    {
      this.setupStepCode = step ;  
      
      this.objectName = configLedgerName ;
      
      this.objectId = ledgerId ;
      
      this.setupStepShortDesc = shortDesc ;
      
      this.setupStepFullDesc = fullDesc ;
      
      this.objectTypeCode = LedgerObjectTypeCode.CONFIGURATION ;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GlLedger getLedger() {
        return ledger;
    }

    public void setLedger(GlLedger ledger) {
        this.ledger = ledger;
    }

    
    public LedgerObjectTypeCode getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(LedgerObjectTypeCode objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }
    
    
    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public GLSetupSteps getSetupStepCode() {
        return setupStepCode;
    }

    public void setStatusCode(GlSetupStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setSetupStepCode(GLSetupSteps setupStepCode) {
        this.setupStepCode = setupStepCode;
    }

    
    public String getNextActionCode() {
        return nextActionCode;
    }

    public void setNextActionCode(String nextActionCode) {
        this.nextActionCode = nextActionCode;
    }

    public GlSetupStatus getStatusCode() {
        return statusCode;
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

    public String getSetupStepShortDesc() {
        return setupStepShortDesc;
    }

    public void setSetupStepShortDesc(String setupStepShortDesc) {
        this.setupStepShortDesc = setupStepShortDesc;
    }

    public String getSetupStepFullDesc() {
        return setupStepFullDesc;
    }

    public void setSetupStepFullDesc(String setupStepFullDesc) {
        this.setupStepFullDesc = setupStepFullDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlLedgerConfigDetail)) {
            return false;
        }
        GlLedgerConfigDetail other = (GlLedgerConfigDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.ledger.GlLedgerConfigDetail[ id=" + id + " ]";
    }
    
}
