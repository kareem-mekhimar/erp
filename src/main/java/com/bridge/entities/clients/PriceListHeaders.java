/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "QP_LIST_HEADERS")
@NamedQueries({
    @NamedQuery(name = "PriceListHeaders.findAll", query = "SELECT q FROM PriceListHeaders q")
})
public class PriceListHeaders implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
     
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen")
    @TableGenerator(name = "gen", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "QpListHeader", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIST_HEADER_ID")
    private Integer  listHeaderId;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY")
    private BigInteger createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private BigInteger lastUpdatedBy;
    @Column(name = "LAST_UPDATE_LOGIN")
    private BigInteger lastUpdateLogin;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private BigInteger programApplicationId;
    @Column(name = "PROGRAM_ID")
    private BigInteger programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "REQUEST_ID")
    private BigInteger requestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LIST_TYPE_CODE")
    private String listTypeCode;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Size(max = 1)
    @Column(name = "AUTOMATIC_FLAG")
    private String automaticFlag;
    @Size(max = 30)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "ROUNDING_FACTOR")
    private BigInteger roundingFactor;
    @Size(max = 30)
    @Column(name = "SHIP_METHOD_CODE")
    private String shipMethodCode;
    @Size(max = 30)
    @Column(name = "FREIGHT_TERMS_CODE")
    private String freightTermsCode;
    @Column(name = "TERMS_ID")
    private BigInteger termsId;
    @Size(max = 2000)
    @Column(name = "COMMENTS")
    private String comments;
    @Size(max = 1)
    @Column(name = "DISCOUNT_LINES_FLAG")
    private String discountLinesFlag;
    @Size(max = 1)
    @Column(name = "GSA_INDICATOR")
    private String gsaIndicator;
    @Size(max = 30)
    @Column(name = "PRORATE_FLAG")
    private String prorateFlag;
    @Size(max = 30)
    @Column(name = "SOURCE_SYSTEM_CODE")
    private String sourceSystemCode;
    @Size(max = 1)
    @Column(name = "ASK_FOR_FLAG")
    private String askForFlag;
    @Column(name = "ACTIVE_FLAG")
    private boolean activeFlag;
    @Column(name = "PARENT_LIST_HEADER_ID")
    private Integer parentListHeaderId;
    @Column(name = "START_DATE_ACTIVE_FIRST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActiveFirst;
    @Column(name = "END_DATE_ACTIVE_FIRST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActiveFirst;
    @Size(max = 30)
    @Column(name = "ACTIVE_DATE_FIRST_TYPE")
    private String activeDateFirstType;
    @Column(name = "START_DATE_ACTIVE_SECOND")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActiveSecond;
    @Column(name = "END_DATE_ACTIVE_SECOND")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActiveSecond;
    @Size(max = 30)
    @Column(name = "ACTIVE_DATE_SECOND_TYPE")
    private String activeDateSecondType;
    @Size(max = 1)
    @Column(name = "LIMIT_EXISTS_FLAG")
    private String limitExistsFlag;
    @Column(name = "MOBILE_DOWNLOAD")
    private boolean mobileDownload;
    @Column(name = "CURRENCY_HEADER_ID")
    private BigInteger currencyHeaderId;
    @Size(max = 30)
    @Column(name = "PTE_CODE")
    private String pteCode;
    @Size(max = 30)
    @Column(name = "LIST_SOURCE_CODE")
    private String listSourceCode;
    @Size(max = 50)
    @Column(name = "ORIG_SYSTEM_HEADER_REF")
    private String origSystemHeaderRef;
    @Column(name = "ORIG_ORG_ID")
    private BigInteger origOrgId;
    @Size(max = 1)
    @Column(name = "GLOBAL_FLAG")
    private String globalFlag;
    @Size(max = 1)
    @Column(name = "SHAREABLE_FLAG")
    private String shareableFlag;
    @Column(name = "SOLD_TO_ORG_ID")
    private BigInteger soldToOrgId;
    @Column(name = "LOCKED_FROM_LIST_HEADER_ID")
    private BigInteger lockedFromListHeaderId;
    @Column(name = "LIST_TYPE_ID")
    private Integer listTypeId;
    @Size(max = 30)
    @Column(name = "LIST_HEADER_NAME")
    private String listHeaderName;
    @Column(name = "CURRENCY_ID")
    private BigInteger currencyId;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORG_UNIT_ID")
    @ManyToOne
    private OrganizationUnit org;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceListHeader")
    private List<PriceListLines> priceListLines;

    public List<PriceListLines> getPriceListLines() {
        return priceListLines;
    }

    public void setPriceListLines(List<PriceListLines> priceListLines) {
        this.priceListLines = priceListLines;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public PriceListHeaders() {
    }

    public PriceListHeaders(Integer listHeaderId) {
        this.listHeaderId = listHeaderId;
    }

    public PriceListHeaders(Integer listHeaderId, Date creationDate, BigInteger createdBy, Date lastUpdateDate, BigInteger lastUpdatedBy, String listTypeCode) {
        this.listHeaderId = listHeaderId;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.listTypeCode = listTypeCode;
    }

    public Integer getListHeaderId() {
        return listHeaderId;
    }

    public void setListHeaderId(Integer listHeaderId) {
        this.listHeaderId = listHeaderId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigInteger getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public BigInteger getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigInteger lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public BigInteger getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(BigInteger programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public BigInteger getProgramId() {
        return programId;
    }

    public void setProgramId(BigInteger programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public BigInteger getRequestId() {
        return requestId;
    }

    public void setRequestId(BigInteger requestId) {
        this.requestId = requestId;
    }

    public String getListTypeCode() {
        return listTypeCode;
    }

    public void setListTypeCode(String listTypeCode) {
        this.listTypeCode = listTypeCode;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getAutomaticFlag() {
        return automaticFlag;
    }

    public void setAutomaticFlag(String automaticFlag) {
        this.automaticFlag = automaticFlag;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigInteger getRoundingFactor() {
        return roundingFactor;
    }

    public void setRoundingFactor(BigInteger roundingFactor) {
        this.roundingFactor = roundingFactor;
    }

    public String getShipMethodCode() {
        return shipMethodCode;
    }

    public void setShipMethodCode(String shipMethodCode) {
        this.shipMethodCode = shipMethodCode;
    }

    public String getFreightTermsCode() {
        return freightTermsCode;
    }

    public void setFreightTermsCode(String freightTermsCode) {
        this.freightTermsCode = freightTermsCode;
    }

    public BigInteger getTermsId() {
        return termsId;
    }

    public void setTermsId(BigInteger termsId) {
        this.termsId = termsId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDiscountLinesFlag() {
        return discountLinesFlag;
    }

    public void setDiscountLinesFlag(String discountLinesFlag) {
        this.discountLinesFlag = discountLinesFlag;
    }

    public String getGsaIndicator() {
        return gsaIndicator;
    }

    public void setGsaIndicator(String gsaIndicator) {
        this.gsaIndicator = gsaIndicator;
    }

    public String getProrateFlag() {
        return prorateFlag;
    }

    public void setProrateFlag(String prorateFlag) {
        this.prorateFlag = prorateFlag;
    }

    public String getSourceSystemCode() {
        return sourceSystemCode;
    }

    public void setSourceSystemCode(String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }

    public String getAskForFlag() {
        return askForFlag;
    }

    public void setAskForFlag(String askForFlag) {
        this.askForFlag = askForFlag;
    }

    public boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getParentListHeaderId() {
        return parentListHeaderId;
    }

    public void setParentListHeaderId(Integer parentListHeaderId) {
        this.parentListHeaderId = parentListHeaderId;
    }

    public Date getStartDateActiveFirst() {
        return startDateActiveFirst;
    }

    public void setStartDateActiveFirst(Date startDateActiveFirst) {
        this.startDateActiveFirst = startDateActiveFirst;
    }

    public Date getEndDateActiveFirst() {
        return endDateActiveFirst;
    }

    public void setEndDateActiveFirst(Date endDateActiveFirst) {
        this.endDateActiveFirst = endDateActiveFirst;
    }

    public String getActiveDateFirstType() {
        return activeDateFirstType;
    }

    public void setActiveDateFirstType(String activeDateFirstType) {
        this.activeDateFirstType = activeDateFirstType;
    }

    public Date getStartDateActiveSecond() {
        return startDateActiveSecond;
    }

    public void setStartDateActiveSecond(Date startDateActiveSecond) {
        this.startDateActiveSecond = startDateActiveSecond;
    }

    public Date getEndDateActiveSecond() {
        return endDateActiveSecond;
    }

    public void setEndDateActiveSecond(Date endDateActiveSecond) {
        this.endDateActiveSecond = endDateActiveSecond;
    }

    public String getActiveDateSecondType() {
        return activeDateSecondType;
    }

    public void setActiveDateSecondType(String activeDateSecondType) {
        this.activeDateSecondType = activeDateSecondType;
    }

    public String getLimitExistsFlag() {
        return limitExistsFlag;
    }

    public void setLimitExistsFlag(String limitExistsFlag) {
        this.limitExistsFlag = limitExistsFlag;
    }

    public boolean getMobileDownload() {
        return mobileDownload;
    }

    public void setMobileDownload(boolean mobileDownload) {
        this.mobileDownload = mobileDownload;
    }

    public BigInteger getCurrencyHeaderId() {
        return currencyHeaderId;
    }

    public void setCurrencyHeaderId(BigInteger currencyHeaderId) {
        this.currencyHeaderId = currencyHeaderId;
    }

    public String getPteCode() {
        return pteCode;
    }

    public void setPteCode(String pteCode) {
        this.pteCode = pteCode;
    }

    public String getListSourceCode() {
        return listSourceCode;
    }

    public void setListSourceCode(String listSourceCode) {
        this.listSourceCode = listSourceCode;
    }

    public String getOrigSystemHeaderRef() {
        return origSystemHeaderRef;
    }

    public void setOrigSystemHeaderRef(String origSystemHeaderRef) {
        this.origSystemHeaderRef = origSystemHeaderRef;
    }

    public BigInteger getOrigOrgId() {
        return origOrgId;
    }

    public void setOrigOrgId(BigInteger origOrgId) {
        this.origOrgId = origOrgId;
    }

    public String getGlobalFlag() {
        return globalFlag;
    }

    public void setGlobalFlag(String globalFlag) {
        this.globalFlag = globalFlag;
    }

    public String getShareableFlag() {
        return shareableFlag;
    }

    public void setShareableFlag(String shareableFlag) {
        this.shareableFlag = shareableFlag;
    }

    public BigInteger getSoldToOrgId() {
        return soldToOrgId;
    }

    public void setSoldToOrgId(BigInteger soldToOrgId) {
        this.soldToOrgId = soldToOrgId;
    }

    public BigInteger getLockedFromListHeaderId() {
        return lockedFromListHeaderId;
    }

    public void setLockedFromListHeaderId(BigInteger lockedFromListHeaderId) {
        this.lockedFromListHeaderId = lockedFromListHeaderId;
    }

    public Integer getListTypeId() {
        return listTypeId;
    }

    public void setListTypeId(Integer listTypeId) {
        this.listTypeId = listTypeId;
    }

    public String getListHeaderName() {
        return listHeaderName;
    }

    public void setListHeaderName(String listHeaderName) {
        this.listHeaderName = listHeaderName;
    }

    public BigInteger getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(BigInteger currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listHeaderId != null ? listHeaderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceListHeaders)) {
            return false;
        }
        PriceListHeaders other = (PriceListHeaders) object;
        if ((this.listHeaderId == null && other.listHeaderId != null) || (this.listHeaderId != null && !this.listHeaderId.equals(other.listHeaderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.QpListHeaders[ listHeaderId=" + listHeaderId + " ]";
    }
    
    public OrganizationUnit getOrg() {
        return org;
    }

    public void setOrg(OrganizationUnit org) {
        this.org = org;
    }
    
}
