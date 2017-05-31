/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.personsales;

import com.bridge.entities.hr.People;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ReservationOrderType;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "OE_RESERVATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservationOrder.findAll", query = "SELECT s FROM ReservationOrder s")})
public class ReservationOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
                 @GeneratedValue(strategy = GenerationType.TABLE,generator = "ReservationOrder")
    @TableGenerator(name = "ReservationOrder",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "ReservationOrder",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESERVATION_ID")
    private Integer reservationId;
    @Column(name = "RESERVATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;
    @OneToOne
    @JoinColumn(name = "OPERATING_UNIT_ID")
    private OrganizationUnit operatingUnitId;
    @OneToOne
    @JoinColumn(name = "MAIN_INV_ID")
    private MainInventory mainInvId;
    @OneToOne
    @JoinColumn(name = "SUB_INV_ID")
    private SecondaryInventory subInvId;
    @OneToOne
    @JoinColumn(name = "SALES_PERSON_ID")
    private People salesPersonId;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ReservationOrderType status;
    @OneToMany(mappedBy = "reservationId",cascade = CascadeType.ALL)
    private List<ReservationLines> reservationLines;

    public ReservationOrder() {
    }

    public ReservationOrder(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public OrganizationUnit getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(OrganizationUnit operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public MainInventory getMainInvId() {
        return mainInvId;
    }

    public void setMainInvId(MainInventory mainInvId) {
        this.mainInvId = mainInvId;
    }

    public SecondaryInventory getSubInvId() {
        return subInvId;
    }

    public void setSubInvId(SecondaryInventory subInvId) {
        this.subInvId = subInvId;
    }

    public People getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(People salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public ReservationOrderType getStatus() {
        return status;
    }

    public void setStatus(ReservationOrderType status) {
        this.status = status;
    }

    public List<ReservationLines> getReservationLines() {
        return reservationLines;
    }

    public void setReservationLines(List<ReservationLines> reservationLines) {
        this.reservationLines = reservationLines;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationOrder)) {
            return false;
        }
        ReservationOrder other = (ReservationOrder) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.personsales.SalesPerson[ reservationId=" + reservationId + " ]";
    }
    
}
