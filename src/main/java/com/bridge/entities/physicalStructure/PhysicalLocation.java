/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.physicalStructure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "ORG_PHYSICAL_LOCATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhysicalLocation.findAll", query = "SELECT o FROM PhysicalLocation o")})
public class PhysicalLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "PhysicalLocation")
    @TableGenerator(name = "PhysicalLocation",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "PhysicalLocation",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @Size(max = 100)
    @Column(name = "LOCATION_NAME")
    private String locationName;
    @Column(name = "STATUS")
    private boolean status;
    @Size(max = 500)
    @Column(name = "LOCATION_DESCRIPTION")
    private String locationDescription;
    @Column(name = "ORGANIZATION_TYPE_ID")
    private Integer organizationTypeId;
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="ORG_PHYSICAL_LOCATIONS_DTL",
            joinColumns = @JoinColumn(name = "LOCATION_ID",referencedColumnName = "LOCATION_ID")
            ,inverseJoinColumns =@JoinColumn(name = "PHYSICAL_DTL_ID",referencedColumnName = "ID") )
    private List<PhysicalLevelDetail> levelDetails;

    public PhysicalLocation() {
    }

    public PhysicalLocation(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Integer getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(Integer organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public List<PhysicalLevelDetail> getLevelDetails() {
        return levelDetails;
    }

    public void setLevelDetails(List<PhysicalLevelDetail> levelDetails) {
        this.levelDetails = levelDetails;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhysicalLocation)) {
            return false;
        }
        PhysicalLocation other = (PhysicalLocation) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.inventory.PhysicalLocation[ locationId=" + locationId + " ]";
    }
    
}
