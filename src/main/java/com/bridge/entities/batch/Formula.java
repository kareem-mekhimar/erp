/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.batch;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.FormulaStatus;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
@Table(name = "FM_FORM_MST")
@NamedQueries({
    @NamedQuery(name = "Formula.findAll", query = "SELECT f FROM Formula f")})
public class Formula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "form")
    @TableGenerator(name = "form",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Formula",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMULA_ID")
    private Integer formulaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "FORMULA_NO")
    private String formulaNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMULA_VERS")
    private Integer formulaVers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMULA_TYPE")
    private int formulaType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCALE_TYPE")
    private int scaleType;
    @Column(name = "IN_USE")
    private Long inUse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INACTIVE_IND")
    private int inactiveInd;
    @Column(name = "TEXT_CODE")
    private Long textCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DELETE_MARK")
    private int deleteMark;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private long createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private long lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;
    @Size(max = 4)
    @Column(name = "FORMULA_UOM")
    private String formulaUom;
    @Column(name = "TOTAL_INPUT_QTY")
    private BigInteger totalInputQty;
    @Column(name = "TOTAL_OUTPUT_QTY")
    private BigInteger totalOutputQty;
    @Size(max = 30)
    @Column(name = "FORMULA_STATUS")
    private String formulaStatus;
    @Basic(optional = false)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "OWNER_ORGANIZATION_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit ownerOrganizationId;
    @Column(name = "MASTER_FORMULA_ID")
    private Long masterFormulaId;
    @Size(max = 3)
    @Column(name = "YIELD_UOM")
    private String yieldUom;
    @Size(max = 1)
    @Column(name = "AUTO_PRODUCT_CALC")
    private String autoProductCalc;
    @Size(max = 70)
    @Column(name = "FORMULA_NAME")
    private String formulaName;
    @Column(name = "ENABLED_FLAG")
    private Character enabledFlag;
   
    @Column(name = "FORMULA_STATUS_ID")
    private FormulaStatus status;
    
    @Size(max = 250)
    @Column(name = "FORMULA_DESCRIPTION")
    private String formulaDescription;
    @Column(name = "ENABLED_FLAG_ID")
    private Integer  enabledFlagId;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "formula",fetch = FetchType.EAGER)
    private List<FormulaLines> formulaLines;

    @ManyToOne
    @JoinColumn(name = "FORMULA_CLASS_ID")
    private FormulaClass formulaClass;
        
    public Formula() {
    }

    public List<FormulaLines> getFormulaLines() {
        return formulaLines;
    }

    public void setFormulaLines(List<FormulaLines> formulaLines) {
        this.formulaLines = formulaLines;
    }


    public Integer getFormulaId() {

        return formulaId;
    }


    public void setFormulaId(Integer formulaId) {

        this.formulaId = formulaId;
    }

    
    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public Integer getFormulaVers() {
        return formulaVers;
    }

    public void setFormulaVers(Integer formulaVers) {
        this.formulaVers = formulaVers;
    }

    public int getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(int formulaType) {
        this.formulaType = formulaType;
    }

    public int getScaleType() {
        return scaleType;
    }

    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
    }   

    public Long getInUse() {
        return inUse;
    }

    public void setInUse(Long inUse) {
        this.inUse = inUse;
    }

    public int getInactiveInd() {
        return inactiveInd;
    }

    public void setInactiveInd(int inactiveInd) {
        this.inactiveInd = inactiveInd;
    }

    public Long getTextCode() {
        return textCode;
    }

    public void setTextCode(Long textCode) {
        this.textCode = textCode;
    }

    public int getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(int deleteMark) {
        this.deleteMark = deleteMark;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
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

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getFormulaUom() {
        return formulaUom;
    }

    public void setFormulaUom(String formulaUom) {
        this.formulaUom = formulaUom;
    }

    public BigInteger getTotalInputQty() {
        return totalInputQty;
    }

    public void setTotalInputQty(BigInteger totalInputQty) {
        this.totalInputQty = totalInputQty;
    }

    public BigInteger getTotalOutputQty() {
        return totalOutputQty;
    }

    public void setTotalOutputQty(BigInteger totalOutputQty) {
        this.totalOutputQty = totalOutputQty;
    }

    public String getFormulaStatus() {
        return formulaStatus;
    }

    public void setFormulaStatus(String formulaStatus) {
        this.formulaStatus = formulaStatus;
    }

    public OrganizationUnit getOwnerOrganizationId() {
        return ownerOrganizationId;
    }

    public void setOwnerOrganizationId(OrganizationUnit ownerOrganizationId) {
        this.ownerOrganizationId = ownerOrganizationId;
    }

    public Long getMasterFormulaId() {
        return masterFormulaId;
    }

    public void setMasterFormulaId(Long masterFormulaId) {
        this.masterFormulaId = masterFormulaId;
    }

    public String getYieldUom() {
        return yieldUom;
    }

    public void setYieldUom(String yieldUom) {
        this.yieldUom = yieldUom;
    }

    public String getAutoProductCalc() {
        return autoProductCalc;
    }

    public void setAutoProductCalc(String autoProductCalc) {
        this.autoProductCalc = autoProductCalc;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Character getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Character enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public FormulaStatus getStatus() {
        return status;
    }

    public void setStatus(FormulaStatus status) {
        this.status = status;
    }
    
    

    public String getFormulaDescription() {
        return formulaDescription;
    }

    public void setFormulaDescription(String formulaDescription) {
        this.formulaDescription = formulaDescription;
    }

    public Integer   getEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(Integer  enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formulaId != null ? formulaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formula)) {
            return false;
        }
        Formula other = (Formula) object;
        if ((this.formulaId == null && other.formulaId != null) || (this.formulaId != null && !this.formulaId.equals(other.formulaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.controllers.Formula[ formulaId=" + formulaId + " ]";
    }

    public FormulaClass getFormulaClass() {
        return formulaClass;
    }

    public void setFormulaClass(FormulaClass formulaClass) {
        this.formulaClass = formulaClass;
    }
    
    @PrePersist
    public void setNullValues(){
        //setFormulaNo("1");
        setFormulaType(1);
        setInactiveInd(1);   //required
        setScaleType(1);
        setDeleteMark(1);
        setDeleteMark(1);
        setCreatedBy(1);
        setCreationDate(new Date());
        setLastUpdatedBy(1);
        setLastUpdateDate(new Date());
    }

}
