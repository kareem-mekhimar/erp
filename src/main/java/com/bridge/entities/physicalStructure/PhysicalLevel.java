/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.physicalStructure;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "ORG_PHYSICAL_STR_LEVELS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhysicalLevel.findAll", query = "SELECT p FROM PhysicalLevel p")})
public class PhysicalLevel implements Serializable {

 

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
          @GeneratedValue(strategy = GenerationType.TABLE,generator = "physicalLevel")
    @TableGenerator(name = "physicalLevel",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PhysicalLevel",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEVEL_ID")
    private Integer levelId;
    @ManyToOne
    @JoinColumn(name = "PHYSICAL_STR_ID")
    private PhysicalStructure physicalStrId;
    @Size(max = 150)
    @Column(name = "LEVEL_NAME")
    private String levelName;
    @Column(name = "LEVEL_ORDER")
    private Integer levelOrder;
    @Column(name = "LEVEL_STATUS")
    private boolean levelStatus;
    @Column(name = "DEPENDENT_TYPE_ID")
    private boolean dependentTypeId;
    @Column(name = "DEPEND_ON_LEVEL_ORDER")
    private Integer dependOnLevelOrder;
    @OneToMany(mappedBy = "levelId",cascade = CascadeType.ALL)
    private List<PhysicalLevelDetail> LevelDetails;

    public PhysicalLevel() {
    }

    public PhysicalLevel(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public PhysicalStructure getPhysicalStrId() {
        return physicalStrId;
    }

    public void setPhysicalStrId(PhysicalStructure physicalStrId) {
        this.physicalStrId = physicalStrId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(Integer levelOrder) {
        this.levelOrder = levelOrder;
    }

    public boolean getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(boolean levelStatus) {
        this.levelStatus = levelStatus;
    }

    public boolean getDependentTypeId() {
        return dependentTypeId;
    }

    public void setDependentTypeId(boolean dependentTypeId) {
        this.dependentTypeId = dependentTypeId;
    }

    public Integer getDependOnLevelOrder() {
        return dependOnLevelOrder;
    }

    public void setDependOnLevelOrder(Integer dependOnLevelOrder) {
        this.dependOnLevelOrder = dependOnLevelOrder;
    }

    public List<PhysicalLevelDetail> getLevelDetails() {
        return LevelDetails;
    }

    public void setLevelDetails(List<PhysicalLevelDetail> LevelDetails) {
        this.LevelDetails = LevelDetails;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelId != null ? levelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhysicalLevel)) {
            return false;
        }
        PhysicalLevel other = (PhysicalLevel) object;
        if ((this.levelId == null && other.levelId != null) || (this.levelId != null && !this.levelId.equals(other.levelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.physicalStructure.PhysicalLevel[ levelId=" + levelId + " ]";
    }


    
}
