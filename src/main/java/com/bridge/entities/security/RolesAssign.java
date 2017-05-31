/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.security;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "ROLES_ASSIGN")
@NamedQueries({
    @NamedQuery(name = "RolesAssign.findAll", query = "SELECT r FROM RolesAssign r")})
public class RolesAssign implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "rassign")
    @TableGenerator(name = "rassign", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "RoleAssign", valueColumnName = "CURRENT_VALUE")
    @Column(name = "ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private UserAccount account;
    
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private SystemRole role;

    public RolesAssign() {
    }

    public RolesAssign(UserAccount account, SystemRole role) {

        this.account = account;
       
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public SystemRole getRole() {
        return role;
    }

    public void setRole(SystemRole role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RolesAssign other = (RolesAssign) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "com.bridge.entities.security.RolesAssign[ id=" + id + " ]";
    }
    
}
