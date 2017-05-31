/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.bank;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "BANKS_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")})
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
        @GeneratedValue(strategy = GenerationType.TABLE,generator = "bank")
    @TableGenerator(name = "bank",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Bank",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANK_ID")
    private Integer bankId;
    @Size(max = 250)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "bankId",cascade = CascadeType.ALL)
    private List<BankBranch> branchList;
    
    public Bank() {
    }

    public Bank(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BankBranch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<BankBranch> branchList) {
        this.branchList = branchList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankId != null ? bankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.bankId == null && other.bankId != null) || (this.bankId != null && !this.bankId.equals(other.bankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.bank.Bank[ bankId=" + bankId + " ]";
    }
    
}
