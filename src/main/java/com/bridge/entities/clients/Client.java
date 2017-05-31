/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.enums.ClientType;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "CL_CLIENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "client")
    @TableGenerator(name = "client", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "Client", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_ID")
    private Integer clientId;
    @Size(max = 30)
    @Column(name = "CLIENT_NUMBER")
    private String clientNumber;
    @Size(max = 360)
    @Column(name = "CLIENT_NAME")
    private String clientName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CLIENT_TYPE")
    private ClientType clientType;
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Size(max = 50)
    @Column(name = "TAX_REFERENCE")
    private String taxReference;
    @Size(max = 60)
    @Column(name = "TAX_NAME")
    private String taxName;
    @Size(max = 30)
    @Column(name = "PERSON_PRE_NAME_ADJUNCT")
    private String personPreNameAdjunct;
    @Size(max = 150)
    @Column(name = "PERSON_FIRST_NAME")
    private String personFirstName;
    @Size(max = 60)
    @Column(name = "PERSON_MIDDLE_NAME")
    private String personMiddleName;
    @Size(max = 150)
    @Column(name = "PERSON_LAST_NAME")
    private String personLastName;
    @Size(max = 30)
    @Column(name = "PERSON_NAME_SUFFIX")
    private String personNameSuffix;
    @Size(max = 60)
    @Column(name = "PERSON_TITLE")
    private String personTitle;
    @Size(max = 30)
    @Column(name = "PERSON_ACADEMIC_TITLE")
    private String personAcademicTitle;
    @Size(max = 150)
    @Column(name = "PERSON_PREVIOUS_LAST_NAME")
    private String personPreviousLastName;
    @Size(max = 240)
    @Column(name = "KNOWN_AS")
    private String knownAs;
    @Size(max = 30)
    @Column(name = "PERSON_IDEN_TYPE")
    private String personIdenType;
    @Size(max = 60)
    @Column(name = "PERSON_IDENTIFIER")
    private String personIdentifier;
    @Size(max = 60)
    @Column(name = "COUNTRY_ID")
    private String countryId;
    @Size(max = 240)
    @Column(name = "ADDRESS1")
    private String address1;
    @Size(max = 240)
    @Column(name = "ADDRESS2")
    private String address2;
    @Size(max = 240)
    @Column(name = "ADDRESS3")
    private String address3;
    @Size(max = 240)
    @Column(name = "ADDRESS4")
    private String address4;
    @Size(max = 60)
    @Column(name = "CITY_ID")
    private String cityId;
    @Size(max = 60)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 60)
    @Column(name = "STATE")
    private String state;
    @Column(name = "STATUS")
    private boolean status;
    @Size(max = 60)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 30)
    @Column(name = "SIC_CODE_TYPE")
    private String sicCodeType;
    @Column(name = "TOTAL_NUM_OF_ORDERS")
    private Integer totalNumOfOrders;
    @Column(name = "TOTAL_ORDERED_AMOUNT")
    private Integer totalOrderedAmount;
    @Column(name = "LAST_ORDERED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOrderedDate;
    @Size(max = 2000)
    @Column(name = "URL")
    private String url;
    @Size(max = 2000)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "DO_NOT_MAIL_FLAG")
    private Integer doNotMailFlag;
    @Size(max = 5)
    @Column(name = "ANALYSIS_FY")
    private String analysisFy;
    @Size(max = 30)
    @Column(name = "FISCAL_YEAREND_MONTH")
    private String fiscalYearendMonth;
    @Column(name = "EMPLOYEES_TOTAL")
    private Integer employeesTotal;
    @Column(name = "CURR_FY_POTENTIAL_REVENUE")
    private Integer currFyPotentialRevenue;
    @Column(name = "NEXT_FY_POTENTIAL_REVENUE")
    private Integer nextFyPotentialRevenue;
    @Column(name = "YEAR_ESTABLISHED")
    private Short yearEstablished;
    @Column(name = "GSA_INDICATOR_FLAG")
    private Integer gsaIndicatorFlag;
    @Size(max = 2000)
    @Column(name = "MISSION_STATEMENT")
    private String missionStatement;
    @Size(max = 320)
    @Column(name = "ORGANIZATION_NAME_PHONETIC")
    private String organizationNamePhonetic;
    @Size(max = 60)
    @Column(name = "PERSON_FIRST_NAME_PHONETIC")
    private String personFirstNamePhonetic;
    @Size(max = 60)
    @Column(name = "PERSON_LAST_NAME_PHONETIC")
    private String personLastNamePhonetic;
    @Size(max = 4)
    @Column(name = "LANGUAGE_NAME")
    private String languageName;
    @Size(max = 30)
    @Column(name = "CATEGORY_CODE")
    private String categoryCode;
    @Column(name = "REFERENCE_USE_FLAG")
    private Integer referenceUseFlag;
    @Column(name = "THIRD_CLIENT_FLAG")
    private Integer thirdClientFlag;
    @Column(name = "COMPETITOR_FLAG")
    private Integer competitorFlag;
    @Column(name = "PRIMARY_PHONE_CONTACT_PT_ID")
    private Integer primaryPhoneContactPtId;
    @Size(max = 30)
    @Column(name = "PRIMARY_PHONE_PURPOSE")
    private String primaryPhonePurpose;
    @Size(max = 30)
    @Column(name = "PRIMARY_PHONE_LINE_TYPE")
    private String primaryPhoneLineType;
    @Size(max = 10)
    @Column(name = "PRIMARY_PHONE_COUNTRY_CODE")
    private String primaryPhoneCountryCode;
    @Size(max = 10)
    @Column(name = "PRIMARY_PHONE_AREA_CODE")
    private String primaryPhoneAreaCode;
    @Size(max = 40)
    @Column(name = "PRIMARY_PHONE_NUMBER")
    private String primaryPhoneNumber;
    @Size(max = 20)
    @Column(name = "PRIMARY_PHONE_EXTENSION")
    private String primaryPhoneExtension;
    @Size(max = 30)
    @Column(name = "CERTIFICATION_LEVEL")
    private String certificationLevel;
    @Size(max = 30)
    @Column(name = "CERT_REASON_CODE")
    private String certReasonCode;
    @Size(max = 30)
    @Column(name = "PREFERRED_CONTACT_METHOD")
    private String preferredContactMethod;
    @Column(name = "CLIENT_TYPE_ID")
    private Integer clientTypeId;
    @Column(name = "CLIENT_CLASSIFICATION_ID")
    private Integer clientClassificationId;
    @Size(max = 100)
    @Column(name = "ALIAS_NAME")
    private String aliasName;
    @Column(name = "RELATIONSHIP_TYPE")
    private Integer relationshipType;
    @Column(name = "DISABLED_ON")
    private Date disabledOn;
    @Column(name = "SUPPLIER_FLAG")
    private boolean supplierFlag;
    @Column(name = "CUSTOMER_FLAG")
    private boolean customerFlag;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    private List<ClientSite> siteList;

    @Column(name = "GL_ID")
    private Integer glId;

    public Client() {
    }

    public Client(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getTaxReference() {
        return taxReference;
    }

    public void setTaxReference(String taxReference) {
        this.taxReference = taxReference;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getPersonPreNameAdjunct() {
        return personPreNameAdjunct;
    }

    public void setPersonPreNameAdjunct(String personPreNameAdjunct) {
        this.personPreNameAdjunct = personPreNameAdjunct;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonMiddleName() {
        return personMiddleName;
    }

    public void setPersonMiddleName(String personMiddleName) {
        this.personMiddleName = personMiddleName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonNameSuffix() {
        return personNameSuffix;
    }

    public void setPersonNameSuffix(String personNameSuffix) {
        this.personNameSuffix = personNameSuffix;
    }

    public String getPersonTitle() {
        return personTitle;
    }

    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }

    public String getPersonAcademicTitle() {
        return personAcademicTitle;
    }

    public void setPersonAcademicTitle(String personAcademicTitle) {
        this.personAcademicTitle = personAcademicTitle;
    }

    public String getPersonPreviousLastName() {
        return personPreviousLastName;
    }

    public void setPersonPreviousLastName(String personPreviousLastName) {
        this.personPreviousLastName = personPreviousLastName;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }

    public String getPersonIdenType() {
        return personIdenType;
    }

    public void setPersonIdenType(String personIdenType) {
        this.personIdenType = personIdenType;
    }

    public String getPersonIdentifier() {
        return personIdentifier;
    }

    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSicCodeType() {
        return sicCodeType;
    }

    public void setSicCodeType(String sicCodeType) {
        this.sicCodeType = sicCodeType;
    }

    public Integer getTotalNumOfOrders() {
        return totalNumOfOrders;
    }

    public void setTotalNumOfOrders(Integer totalNumOfOrders) {
        this.totalNumOfOrders = totalNumOfOrders;
    }

    public Integer getTotalOrderedAmount() {
        return totalOrderedAmount;
    }

    public void setTotalOrderedAmount(Integer totalOrderedAmount) {
        this.totalOrderedAmount = totalOrderedAmount;
    }

    public Date getLastOrderedDate() {
        return lastOrderedDate;
    }

    public void setLastOrderedDate(Date lastOrderedDate) {
        this.lastOrderedDate = lastOrderedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getDoNotMailFlag() {
        return doNotMailFlag;
    }

    public void setDoNotMailFlag(Integer doNotMailFlag) {
        this.doNotMailFlag = doNotMailFlag;
    }

    public String getAnalysisFy() {
        return analysisFy;
    }

    public void setAnalysisFy(String analysisFy) {
        this.analysisFy = analysisFy;
    }

    public String getFiscalYearendMonth() {
        return fiscalYearendMonth;
    }

    public void setFiscalYearendMonth(String fiscalYearendMonth) {
        this.fiscalYearendMonth = fiscalYearendMonth;
    }

    public Integer getEmployeesTotal() {
        return employeesTotal;
    }

    public void setEmployeesTotal(Integer employeesTotal) {
        this.employeesTotal = employeesTotal;
    }

    public Integer getCurrFyPotentialRevenue() {
        return currFyPotentialRevenue;
    }

    public void setCurrFyPotentialRevenue(Integer currFyPotentialRevenue) {
        this.currFyPotentialRevenue = currFyPotentialRevenue;
    }

    public Integer getNextFyPotentialRevenue() {
        return nextFyPotentialRevenue;
    }

    public void setNextFyPotentialRevenue(Integer nextFyPotentialRevenue) {
        this.nextFyPotentialRevenue = nextFyPotentialRevenue;
    }

    public Short getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(Short yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public Integer getGsaIndicatorFlag() {
        return gsaIndicatorFlag;
    }

    public void setGsaIndicatorFlag(Integer gsaIndicatorFlag) {
        this.gsaIndicatorFlag = gsaIndicatorFlag;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }

    public String getOrganizationNamePhonetic() {
        return organizationNamePhonetic;
    }

    public void setOrganizationNamePhonetic(String organizationNamePhonetic) {
        this.organizationNamePhonetic = organizationNamePhonetic;
    }

    public String getPersonFirstNamePhonetic() {
        return personFirstNamePhonetic;
    }

    public void setPersonFirstNamePhonetic(String personFirstNamePhonetic) {
        this.personFirstNamePhonetic = personFirstNamePhonetic;
    }

    public String getPersonLastNamePhonetic() {
        return personLastNamePhonetic;
    }

    public void setPersonLastNamePhonetic(String personLastNamePhonetic) {
        this.personLastNamePhonetic = personLastNamePhonetic;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getReferenceUseFlag() {
        return referenceUseFlag;
    }

    public void setReferenceUseFlag(Integer referenceUseFlag) {
        this.referenceUseFlag = referenceUseFlag;
    }

    public Integer getThirdClientFlag() {
        return thirdClientFlag;
    }

    public void setThirdClientFlag(Integer thirdClientFlag) {
        this.thirdClientFlag = thirdClientFlag;
    }

    public Integer getCompetitorFlag() {
        return competitorFlag;
    }

    public void setCompetitorFlag(Integer competitorFlag) {
        this.competitorFlag = competitorFlag;
    }

    public Integer getPrimaryPhoneContactPtId() {
        return primaryPhoneContactPtId;
    }

    public void setPrimaryPhoneContactPtId(Integer primaryPhoneContactPtId) {
        this.primaryPhoneContactPtId = primaryPhoneContactPtId;
    }

    public String getPrimaryPhonePurpose() {
        return primaryPhonePurpose;
    }

    public void setPrimaryPhonePurpose(String primaryPhonePurpose) {
        this.primaryPhonePurpose = primaryPhonePurpose;
    }

    public String getPrimaryPhoneLineType() {
        return primaryPhoneLineType;
    }

    public void setPrimaryPhoneLineType(String primaryPhoneLineType) {
        this.primaryPhoneLineType = primaryPhoneLineType;
    }

    public String getPrimaryPhoneCountryCode() {
        return primaryPhoneCountryCode;
    }

    public void setPrimaryPhoneCountryCode(String primaryPhoneCountryCode) {
        this.primaryPhoneCountryCode = primaryPhoneCountryCode;
    }

    public String getPrimaryPhoneAreaCode() {
        return primaryPhoneAreaCode;
    }

    public void setPrimaryPhoneAreaCode(String primaryPhoneAreaCode) {
        this.primaryPhoneAreaCode = primaryPhoneAreaCode;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getPrimaryPhoneExtension() {
        return primaryPhoneExtension;
    }

    public void setPrimaryPhoneExtension(String primaryPhoneExtension) {
        this.primaryPhoneExtension = primaryPhoneExtension;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public String getCertReasonCode() {
        return certReasonCode;
    }

    public void setCertReasonCode(String certReasonCode) {
        this.certReasonCode = certReasonCode;
    }

    public String getPreferredContactMethod() {
        return preferredContactMethod;
    }

    public void setPreferredContactMethod(String preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }

    public Integer getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(Integer clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public Integer getClientClassificationId() {
        return clientClassificationId;
    }

    public void setClientClassificationId(Integer clientClassificationId) {
        this.clientClassificationId = clientClassificationId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(Integer relationshipType) {
        this.relationshipType = relationshipType;
    }

    public List<ClientSite> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<ClientSite> siteList) {
        this.siteList = siteList;
    }

    public Date getDisabledOn() {
        return disabledOn;
    }

    public void setDisabledOn(Date disabledOn) {
        this.disabledOn = disabledOn;
    }

    public boolean getSupplierFlag() {
        return supplierFlag;
    }

    public void setSupplierFlag(boolean supplierFlag) {
        this.supplierFlag = supplierFlag;
    }

    public boolean getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(boolean customerFlag) {
        this.customerFlag = customerFlag;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.Client[ clientId=" + clientId + " ]";
    }

    public Integer getGlId() {
        return glId;
    }

    public void setGlId(Integer glId) {
        this.glId = glId;
    }

}
