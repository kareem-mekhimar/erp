/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.assets;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.AssetTransactionType;
import com.bridge.enums.AssetTransactionStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "FA_TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaTransactions.findAll", query = "SELECT a FROM FaTransactions a")})
public class FaTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FaHistory")
    @TableGenerator(name = "FaHistory", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
            pkColumnName = "SEQUENCE_NAME", pkColumnValue = "FaHistory", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private AssetTransactionType transactionType;
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @OneToOne
    @JoinColumn(name = "ASSET_ID")
    private FaAssets asset;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private FaCategory category;
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @OneToOne
    @JoinColumn(name = "OLD_CATEGORY_ID")
    private FaCategory oldCategory;
    @Column(name = "OLD_LOCATION_ID")
    private Integer oldLocationId;
    @OneToOne
    @JoinColumn(name="SPARE_PART_ID")
    private SystemItem sparePart;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "JOURNAL_STATUS")
    private AssetTransactionStatus journalStatus;

    public FaTransactions() {
    }

    public FaTransactions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssetTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(AssetTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AssetTransactionStatus getJournalStatus() {
        return journalStatus;
    }

    public void setJournalStatus(AssetTransactionStatus journalStatus) {
        this.journalStatus = journalStatus;
    }

    public FaAssets getAsset() {
        return asset;
    }

    public void setAsset(FaAssets asset) {
        this.asset = asset;
    }

    public FaCategory getCategory() {
        return category;
    }

    public void setCategory(FaCategory category) {
        this.category = category;
    }

    public FaCategory getOldCategory() {
        return oldCategory;
    }

    public void setOldCategory(FaCategory oldCategory) {
        this.oldCategory = oldCategory;
    }

    public Integer getOldLocationId() {
        return oldLocationId;
    }

    public void setOldLocationId(Integer oldLocationId) {
        this.oldLocationId = oldLocationId;
    }

    public SystemItem getSparePart() {
        return sparePart;
    }

    public void setSparePart(SystemItem sparePart) {
        this.sparePart = sparePart;
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
        if (!(object instanceof FaTransactions)) {
            return false;
        }
        FaTransactions other = (FaTransactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.assets.AssetHistory[ id=" + id + " ]";
    }

}
