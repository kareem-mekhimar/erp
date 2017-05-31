/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.physicalStructure;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.StructureStatus;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "ORG_PHYSICAL_STRUCTURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhysicalStructure.findAll", query = "SELECT p FROM PhysicalStructure p")})
public class PhysicalStructure implements Serializable {


    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
        @GeneratedValue(strategy = GenerationType.TABLE,generator = "physicalSructure")
    @TableGenerator(name = "physicalSructure",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PhysicalSructure",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID")
    private OrganizationUnit businessGroupId;
    @Size(max = 150)
    @Column(name = "PH_STR_NAME")
    private String physicalStrName;
     @ManyToOne
    @JoinColumn(name = "LEGAL_ENTITY_ID")
    private OrganizationUnit legalEntityId;
      @ManyToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnitId;
      
    @OneToMany(mappedBy = "physicalStrId",cascade = CascadeType.ALL)
    private List<PhysicalLevel> physicalLevels;

    @Column(name = "LEVELS_DETAILS_NO")
    private Integer levelsDetailsNo;
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private StructureStatus Status;
    
    public PhysicalStructure() {
    }

    public PhysicalStructure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getPhysicalStrName() {
        return physicalStrName;
    }

    public void setPhysicalStrName(String physicalStrName) {
        this.physicalStrName = physicalStrName;
    }

    public OrganizationUnit getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(OrganizationUnit legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public OrganizationUnit getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(OrganizationUnit operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public List<PhysicalLevel> getPhysicalLevels() {
        return physicalLevels;
    }

    public void setPhysicalLevels(List<PhysicalLevel> physicalLevels) {
        this.physicalLevels = physicalLevels;
    }

    public StructureStatus getStatus() {
        return Status;
    }

    public void setStatus(StructureStatus Status) {
        this.Status = Status;
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
        if (!(object instanceof PhysicalStructure)) {
            return false;
        }
        PhysicalStructure other = (PhysicalStructure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.physicalStructure.PhysicalStructure[ id=" + id + " ]";
    }

    public Integer getLevelsDetailsNo() {
        return levelsDetailsNo;
    }

    public void setLevelsDetailsNo(Integer levelsDetailsNo) {
        this.levelsDetailsNo = levelsDetailsNo;
    }

    @PrePersist
    private void prePresist()
    {
        if(this.levelsDetailsNo == null){
            setLevelsDetailsNo(0);
        }
    }
    
}
