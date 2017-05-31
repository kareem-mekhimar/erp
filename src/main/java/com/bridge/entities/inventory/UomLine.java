/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.inventory;

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
@Table(name = "MTL_UNITS_OF_MEASURE")
@NamedQueries({
@NamedQuery(name = "UomLine.findAll", query = "SELECT m FROM UomLine m")})
public class UomLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "uom")
    @TableGenerator(name = "uom", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "Uom", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "UOM_ID")       
    private Integer uomId;

    @Size(max = 10)
    @Column(name = "UOM_CODE")
    private String uomCode;
    @Size(max = 100)
    @Column(name = "UOM_NAME")
    private String uomName;
     @Column(name = "BASE_UOM_FLAG")
    private Character  baseUomFlag;  
    @Column(name = "BASE_UOM_FLAG_ID")
    private boolean  baseUomFlagId;
    @Column(name = "UOM_STATUS")
    private boolean  uomStatus;
    @Column(name = "ENABLED_FLAG_ID")
    private boolean  enabledFlagId;
    @Column(name = "DISABLE_FLAG_ID")
    private boolean  disableFlagId;
    @Column(name = "VALUE_BY_BASE")
    private double valuebybase;
    @Size(max = 250)
    @Column(name = "UOM_DESCRIPTION")
    private String uomDescription;
    @Column(name = "DISABLE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disableDate;
    
    @ManyToOne
    @JoinColumn(name="UOM_CLASS_ID",referencedColumnName = "CLASS_ID")
    private UomClass uomClass ;
    
    @OneToMany(mappedBy = "weightUnitMeasure")
    private List<MainInventory> MainInvWeights;
    @OneToMany(mappedBy = "weightUnitMeasure")
    private List<MainInventory> MainInvVolumes;
    
    
    
    public UomLine() {
    }

    public UomLine(Integer uomId) {
        this.uomId = uomId;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer uomId) {
        this.uomId = uomId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }


    public String getUomDescription() {
        return uomDescription;
    }

    public void setUomDescription(String uomDescription) {
        this.uomDescription = uomDescription;
    }

    public boolean  getUomStatus() {
        return uomStatus;
    }

    public void setUomStatus(boolean  uomStatus) {
        this.uomStatus = uomStatus;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public UomClass getUomClass() {
        return uomClass;
    }

    public void setUomClass(UomClass uomClass) {
        this.uomClass = uomClass;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomId != null ? uomId.hashCode() : 0);
        return hash;
    }

    public void setBaseUomFlag(Character baseUomFlag) {
        this.baseUomFlag = baseUomFlag;
    }

    public Character getBaseUomFlag() {
        return baseUomFlag;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UomLine)) {
            return false;
        }
        UomLine other = (UomLine) object;
        if ((this.uomId == null && other.uomId != null) || (this.uomId != null && !this.uomId.equals(other.uomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.UomLine[ uomId=" + uomId + " ]";
    }

    public boolean  getBaseUomFlagId() {
        return baseUomFlagId;
    }

    public void setBaseUomFlagId(boolean  baseUomFlagId) {
        this.baseUomFlagId = baseUomFlagId;
    }

    public boolean isEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(boolean enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }

    public boolean isBaseUomFlagId() {
        return baseUomFlagId;
    }
   

    public boolean isUomStatus() {
        return uomStatus;
    }

    public boolean isDisableFlagId() {
        return disableFlagId;
    }

    public void setDisableFlagId(boolean disableFlagId) {
        this.disableFlagId = disableFlagId;
    }

    public double getValuebybase() {
        return valuebybase;
    }

    public void setValuebybase(double valuebybase) {
        this.valuebybase = valuebybase;
    }

    public List<MainInventory> getMainInvWeights() {
        return MainInvWeights;
    }

    public void setMainInvWeights(List<MainInventory> MainInvWeights) {
        this.MainInvWeights = MainInvWeights;
    }

    public List<MainInventory> getMainInvVolumes() {
        return MainInvVolumes;
    }

    public void setMainInvVolumes(List<MainInventory> MainInvVolumes) {
        this.MainInvVolumes = MainInvVolumes;
    }
    
    
}
