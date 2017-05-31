/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.coa.GlCodeCombination;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "CL_CLIENT_CUSTOMER_ACC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAcc.findAll", query = "SELECT c FROM CustomerAcc c")})
public class CustomerAcc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer")
    @TableGenerator(name = "customer", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "Customer", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "SITE_ID")
    private ClientSite siteId;
    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client clientId;
    @Column(name = "SHIP_TO_FLAG")
    private boolean shipToFlag;
    @Column(name = "BILL_TO_FLAG")
    private boolean billToFlag;
    @Column(name = "PRICE_LIST_ID")
    private Integer priceListId;
    @OneToOne
    @JoinColumn(name = "SALES_ACCOUNT_ID")
    private GlCodeCombination salesAccountId;

    @ManyToOne
    @JoinColumn(name = "RECEIVABLE_ACC")
    private GlCodeCombination receivableAcc;

    public CustomerAcc() {

    }

    public ClientSite getSiteId() {
        return siteId;
    }

    public void setSiteId(ClientSite siteId) {
        this.siteId = siteId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public boolean getShipToFlag() {
        return shipToFlag;
    }

    public void setShipToFlag(boolean shipToFlag) {
        this.shipToFlag = shipToFlag;
    }

    public boolean getBillToFlag() {
        return billToFlag;
    }

    public void setBillToFlag(boolean billToFlag) {
        this.billToFlag = billToFlag;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public GlCodeCombination getSalesAccountId() {
        return salesAccountId;
    }

    public void setSalesAccountId(GlCodeCombination salesAccountId) {
        this.salesAccountId = salesAccountId;
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
        if (!(object instanceof CustomerAcc)) {
            return false;
        }
        CustomerAcc other = (CustomerAcc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.CustomerAcc[ id=" + id + " ]";
    }

    public GlCodeCombination getReceivableAcc() {
        return receivableAcc;
    }

    public void setReceivableAcc(GlCodeCombination receivableAcc) {
        this.receivableAcc = receivableAcc;
    }

}
