/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.po;

import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "ERP_SYSTEM_CLIENTS")
@NamedQueries({
    @NamedQuery(name = "ErpSystemClient.findAll", query = "SELECT e FROM ErpSystemClient e")})
public class ErpSystemClient implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @Size(max = 100)
    @Column(name = "CLIENT_CODE")
    private String clientCode;
    @Size(max = 200)
    @Column(name = "CLIENT_EN_NAME")
    private String clientEnName;
    @Size(max = 200)
    @Column(name = "CLIENT_AR_NAME")
    private String clientArName;
    @Column(name = "CLIENT_STATUS")
    private Character clientStatus;
    @OneToMany(mappedBy = "erpSystemClientId")
    private List<OrganizationUnit> organizationUnitList;

    public ErpSystemClient() {
    }

    public ErpSystemClient(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientEnName() {
        return clientEnName;
    }

    public void setClientEnName(String clientEnName) {
        this.clientEnName = clientEnName;
    }

    public String getClientArName() {
        return clientArName;
    }

    public void setClientArName(String clientArName) {
        this.clientArName = clientArName;
    }

    public Character getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(Character clientStatus) {
        this.clientStatus = clientStatus;
    }

    public List<OrganizationUnit> getOrganizationUnitList() {
        return organizationUnitList;
    }

    public void setOrganizationUnitList(List<OrganizationUnit> organizationUnitList) {
        this.organizationUnitList = organizationUnitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErpSystemClient)) {
            return false;
        }
        ErpSystemClient other = (ErpSystemClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.po.ErpSystemClient[ clientId=" + clientId + " ]";
    }
    
}
