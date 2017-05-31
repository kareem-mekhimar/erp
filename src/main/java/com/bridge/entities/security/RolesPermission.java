/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.security;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "ROLES_PERMISSIONS")
@NamedQueries({
    @NamedQuery(name = "RolesPermission.findAll", query = "SELECT r FROM RolesPermission r")})
public class RolesPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "rolePerm")
    @TableGenerator(name = "rolePerm", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "RolePermission", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 255)
    @Column(name = "PERMISSION")
    private String combinedPermission;
    
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private SystemRole role;
    
    @ManyToOne
    @JoinColumn(name = "SYS_PERM_ID")
    private SystemPermission systemPermission ;

    public RolesPermission() {
    }

    public RolesPermission(SystemRole role , SystemPermission permission) {
        
        this.role = role ;
        
        this.systemPermission = permission ;
        
        this.combinedPermission = systemPermission.getDomain()+":"+systemPermission.getAction();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCombinedPermission() {
        return combinedPermission;
    }

    public void setCombinedPermission(String combinedPermission) {
        this.combinedPermission = combinedPermission;
    }

    public SystemRole getRole() {
        return role;
    }

    public void setRole(SystemRole role) {
        this.role = role;
    }

    public SystemPermission getSystemPermission() {
        return systemPermission;
    }

    public void setSystemPermission(SystemPermission systemPermission) {
        this.systemPermission = systemPermission;
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
        if (!(object instanceof RolesPermission)) {
            return false;
        }
        RolesPermission other = (RolesPermission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.security.RolesPermission[ id=" + id + " ]";
    }
    
}
