/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.bridge.enums.UOMClassType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "MTL_UOM_CLASSES")
@NamedQueries({
    @NamedQuery(name = "UomClass.findAll", query = "SELECT m FROM UomClass m")})
public class UomClass implements Serializable {



    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "uomClass")
    @TableGenerator(name = "uomClass", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "UomClass", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASS_ID")
    private Integer classId;
    @Size(max = 100)
    @Column(name = "CLASS_NAME")
    private String className;
    @Size(max = 250)
    @Column(name = "CLASS_DESCRIPTION")
    private String classDescription;
    @Size(max = 100)
    @Column(name = "BASE_UNIT")
    private String baseUnit;
    @Size(max = 10)
    @Column(name = "BASE_UNIT_CODE")
    private String baseUnitCode;
      @Column(name = "ACTIVE_ON")
    @Temporal(TemporalType.TIMESTAMP)
      private Date activeOn;
    @Column(name = "INACTIVE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveOn;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
  
    @Enumerated(EnumType.STRING)
    @Column(name = "CLASS_TYPE")
    private UOMClassType classType;
    
    @Size(max = 10)
    @Column(name="CLASS_CODE")
    private String classCode;

    public UOMClassType getClassType() {
        return classType;
    }

    public void setClassType(UOMClassType classType) {
        this.classType = classType;
    }
    
    
    
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "LAST_UPDATE_BY")
    private Integer lastUpdateBy;
    @Column(name = "CLASS_STATUS")
    private boolean  classStatus;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "uomClass")
    private List<UomLine> uomLines ;
    
    public UomClass() {
    }

    public UomClass(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getBaseUnitCode() {
        return baseUnitCode;
    }

    public void setBaseUnitCode(String baseUnitCode) {
        this.baseUnitCode = baseUnitCode;
    }

    public Date getActiveOn() {
        return activeOn;
    }

    public void setActiveOn(Date activeOn) {
        this.activeOn = activeOn;
    }

    public Date getInactiveOn() {
        return inactiveOn;
    }

    public void setInactiveOn(Date inactiveOn) {
        this.inactiveOn = inactiveOn;
    }

    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer  createdBy) {

        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer  getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Integer  lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(boolean classStatus) {
        this.classStatus = classStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classId != null ? classId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UomClass)) {
            return false;
        }
        UomClass other = (UomClass) object;
        if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
            return false;
        }
        return true;
    }

    public List<UomLine> getUomLines() {
        return uomLines;
    }

    public void setUomLines(List<UomLine> uomLines) {
        this.uomLines = uomLines;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.inventory.UomClass[ classId=" + classId + " ]";
    }

}
