/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

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
@Table(name = "CL_CLIENT_CONTACTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientContact.findAll", query = "SELECT c FROM ClientContact c")})
public class ClientContact implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
               @GeneratedValue(strategy = GenerationType.TABLE,generator = "contact")
    @TableGenerator(name = "contact",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Contact",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTACT_ID")
    private Integer contactId;
    @Size(max = 250)
    @Column(name = "CONTACT_NAME")
    private String contactName;
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @ManyToOne
    @JoinColumn(name = "SITE_ID")
    private ClientSite siteId;
    @Size(max = 50)
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Size(max = 250)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "DISABLED_FLAG")
    private boolean disabledFlag;

    public ClientContact() {
    }

    public ClientContact(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public ClientSite getSiteId() {
        return siteId;
    }

    public void setSiteId(ClientSite siteId) {
        this.siteId = siteId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(boolean disabledFlag) {
        this.disabledFlag = disabledFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientContact)) {
            return false;
        }
        ClientContact other = (ClientContact) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.ClientContact[ contactId=" + contactId + " ]";
    }
    
}
