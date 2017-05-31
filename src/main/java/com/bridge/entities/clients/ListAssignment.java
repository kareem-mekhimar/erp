/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.enums.AssignmentType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "QP_LIST_ASSIGNMENT")
@NamedQueries({
    @NamedQuery(name = "ListAssignment.findAll", query = "SELECT q FROM ListAssignment q")})
public class ListAssignment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "LIST_ID")
    private PriceListHeaders listId;
    @OneToOne
    @JoinColumn(name = "ASSIGNED_TO")
    private Client assignedTo;
    @Column(name = "ASSIGNMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignmentDate;
    @Column(name = "DISABLED_FLAG")
    private Integer disabledFlag;
    @Column(name = "DISABLED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "ASSIGNMENT_TYPE")
    private AssignmentType assignmentType;

    public ListAssignment() {
    }

    public ListAssignment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PriceListHeaders getListId() {
        return listId;
    }

    public void setListId(PriceListHeaders listId) {
        this.listId = listId;
    }

    public Client getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Client assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Integer getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(Integer disabledFlag) {
        this.disabledFlag = disabledFlag;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public AssignmentType getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(AssignmentType assignmentType) {
        this.assignmentType = assignmentType;
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
        if (!(object instanceof ListAssignment)) {
            return false;
        }
        ListAssignment other = (ListAssignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.QpListAssignment[ id=" + id + " ]";
    }
    
}
