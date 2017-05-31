/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.physicalStructure;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "ORG_PHYSICAL_STR_LVL_DTL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhysicalLevelDetail.findAll", query = "SELECT p FROM PhysicalLevelDetail p")})
public class PhysicalLevelDetail implements Serializable {



    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
        @GeneratedValue(strategy = GenerationType.TABLE,generator = "physicalLevelDetail")
    @TableGenerator(name = "physicalLevelDetail",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PhysicalLevelDetail",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "LEVEL_ID")
    private PhysicalLevel levelId;
    @Size(max = 150)
    @Column(name = "DETAIL_NAME")
    private String detailName;
    @Column(name = "DETAIL_STATUS")
    private boolean detailStatus;
    @Column(name="USED_IN_LOCATIONS")
    private Integer usedInLocations;
    @ManyToOne
    @JoinColumn(name="DEPENDS_ON_LEVEL")
    private PhysicalLevelDetail dependsOnLevel;
    
    
    
    public PhysicalLevelDetail() {
    }

    public PhysicalLevelDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhysicalLevel getLevelId() {
        return levelId;
    }

    public void setLevelId(PhysicalLevel levelId) {
        this.levelId = levelId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public PhysicalLevelDetail getDependsOnLevel() {
        return dependsOnLevel;
    }

    public void setDependsOnLevel(PhysicalLevelDetail dependsOnLevel) {
        this.dependsOnLevel = dependsOnLevel;
    }

    public Integer getUsedInLocations() {
        return usedInLocations;
    }

    public void setUsedInLocations(Integer usedInLocations) {
        this.usedInLocations = usedInLocations;
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
        if (!(object instanceof PhysicalLevelDetail)) {
            return false;
        }
        PhysicalLevelDetail other = (PhysicalLevelDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.physicalStructure.PhysicalLevelDetail[ id=" + id + " ]";
    }

    public boolean getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(boolean detailStatus) {
        this.detailStatus = detailStatus;
    }
    
}
