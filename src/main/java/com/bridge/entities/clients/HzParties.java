/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.clients;

import com.bridge.enums.CustomerType;
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
@Table(name = "HZ_PARTIES")
@NamedQueries({
    @NamedQuery(name = "HzParties.findAll", query = "SELECT h FROM HzParties h")})
public class HzParties implements Serializable {

    private static final Integer serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen")
    @TableGenerator(name = "gen", table = "SYSTEM_SEQUENCES_GENERATOR", allocationSize = 20,
             pkColumnName = "SEQUENCE_NAME", pkColumnValue = "HzParty", valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARTY_ID")
    private Integer partyId;
            
            
    

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PARTY_NUMBER")
    private String partyNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 360)
    @Column(name = "PARTY_NAME")
    private String partyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PARTY_TYPE")
    private String partyType;
    @Column(name = "VALIDATED_FLAG")
    private boolean validatedFlag;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;
    @Column(name = "REQUEST_ID")
    private Long requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private Long programApplicationId;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "PROGRAM_ID")
    private Long programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Column(name = "WH_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whUpdateDate;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 240)
    @Column(name = "ORIG_SYSTEM_REFERENCE")
    private String origSystemReference;
    @Size(max = 30)
    @Column(name = "SIC_CODE")
    private String sicCode;
    @Size(max = 30)
    @Column(name = "HQ_BRANCH_IND")
    private String hqBranchInd;
    @Size(max = 500)
    @Column(name = "CUSTOMER_KEY")
    private String customerKey;
    @Size(max = 50)
    @Column(name = "TAX_REFERENCE")
    private String taxReference;
    @Size(max = 20)
    @Column(name = "JGZZ_FISCAL_CODE")
    private String jgzzFiscalCode;
    @Column(name = "DUNS_NUMBER")
    private BigInteger dunsNumber;
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
    @Size(max = 30)
    @Column(name = "GROUP_TYPE")
    private String groupType;
    @Size(max = 60)
    @Column(name = "COUNTRY")
    private String country;
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
    @Column(name = "CITY")
    private String city;
    @Size(max = 60)
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Size(max = 60)
    @Column(name = "STATE")
    private String state;
    @Size(max = 60)
    @Column(name = "PROVINCE")
    private String province;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private boolean status;
    @Size(max = 60)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 30)
    @Column(name = "SIC_CODE_TYPE")
    private String sicCodeType;
    @Column(name = "TOTAL_NUM_OF_ORDERS")
    private BigInteger totalNumOfOrders;
    @Column(name = "TOTAL_ORDERED_AMOUNT")
    private BigInteger totalOrderedAmount;
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
    private boolean doNotMailFlag;
    @Size(max = 5)
    @Column(name = "ANALYSIS_FY")
    private String analysisFy;
    @Size(max = 30)
    @Column(name = "FISCAL_YEAREND_MONTH")
    private String fiscalYearendMonth;
    @Column(name = "EMPLOYEES_TOTAL")
    private BigInteger employeesTotal;
    @Column(name = "CURR_FY_POTENTIAL_REVENUE")
    private BigInteger currFyPotentialRevenue;
    @Column(name = "NEXT_FY_POTENTIAL_REVENUE")
    private BigInteger nextFyPotentialRevenue;
    @Column(name = "YEAR_ESTABLISHED")
    private Short yearEstablished;
    @Column(name = "GSA_INDICATOR_FLAG")
    private boolean gsaIndicatorFlag;
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
    private boolean referenceUseFlag;
    @Column(name = "THIRD_PARTY_FLAG")
    private boolean thirdPartyFlag;
    @Column(name = "COMPETITOR_FLAG")
    private boolean competitorFlag;
    @Size(max = 60)
    @Column(name = "SALUTATION")
    private String salutation;
    @Size(max = 240)
    @Column(name = "KNOWN_AS2")
    private String knownAs2;
    @Size(max = 240)
    @Column(name = "KNOWN_AS3")
    private String knownAs3;
    @Size(max = 240)
    @Column(name = "KNOWN_AS4")
    private String knownAs4;
    @Size(max = 240)
    @Column(name = "KNOWN_AS5")
    private String knownAs5;
    @Size(max = 30)
    @Column(name = "DUNS_NUMBER_C")
    private String dunsNumberC;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private BigInteger objectVersionNumber;
    @Size(max = 150)
    @Column(name = "CREATED_BY_MODULE")
    private String createdByModule;
    @Column(name = "APPLICATION_ID")
    private BigInteger applicationId;
    @Column(name = "PRIMARY_PHONE_CONTACT_PT_ID")
    private Long primaryPhoneContactPtId;
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
    @Size(max = 2)
    @Column(name = "HOME_COUNTRY")
    private String homeCountry;
    @Column(name = "PERSON_BO_VERSION")
    private Long personBoVersion;
    @Column(name = "ORG_BO_VERSION")
    private Long orgBoVersion;
    @Column(name = "PERSON_CUST_BO_VERSION")
    private Long personCustBoVersion;
    @Column(name = "ORG_CUST_BO_VERSION")
    private Long orgCustBoVersion;
    @Column(name = "PARTY_TYPE_ID")
    private CustomerType partyTypeId;
    @Column(name = "CATEGORY_CODE_ID")
    private BigInteger categoryCodeId;
    @Column(name = "PARTY_CLASSIFICATION_ID")
    private BigInteger partyClassificationId;
    @Column(name = "ALIAS_NAME")
    private String aliasName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hzParty")
    private List<HzPartySites> hzPartySitesList;

    
    public List<HzPartySites> getHzPartySitesList() {
        return hzPartySitesList;
    }

    public void setHzPartySitesList(List<HzPartySites> hzPartySitesList) {
        this.hzPartySitesList = hzPartySitesList;
    }
    
    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    
    public HzParties() {
    }

    public HzParties(Integer partyId) {
        this.partyId = partyId;
    }

    public HzParties(Integer partyId, String partyNumber, String partyName, String partyType, Integer lastUpdatedBy, Date creationDate, Integer createdBy, Date lastUpdateDate, String origSystemReference, boolean status) {
        this.partyId = partyId;
        this.partyNumber = partyNumber;
        this.partyName = partyName;
        this.partyType = partyType;
        this.lastUpdatedBy = lastUpdatedBy;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.origSystemReference = origSystemReference;
        this.status = status;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public boolean getValidatedFlag() {
        return validatedFlag;
    }

    public void setValidatedFlag(boolean validatedFlag) {
        this.validatedFlag = validatedFlag;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(Long programApplicationId) {
        this.programApplicationId = programApplicationId;
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

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Date getWhUpdateDate() {
        return whUpdateDate;
    }

    public void setWhUpdateDate(Date whUpdateDate) {
        this.whUpdateDate = whUpdateDate;
    }

    public String getOrigSystemReference() {
        return origSystemReference;
    }

    public void setOrigSystemReference(String origSystemReference) {
        this.origSystemReference = origSystemReference;
    }

    public String getSicCode() {
        return sicCode;
    }

    public void setSicCode(String sicCode) {
        this.sicCode = sicCode;
    }

    public String getHqBranchInd() {
        return hqBranchInd;
    }

    public void setHqBranchInd(String hqBranchInd) {
        this.hqBranchInd = hqBranchInd;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getTaxReference() {
        return taxReference;
    }

    public void setTaxReference(String taxReference) {
        this.taxReference = taxReference;
    }

    public String getJgzzFiscalCode() {
        return jgzzFiscalCode;
    }

    public void setJgzzFiscalCode(String jgzzFiscalCode) {
        this.jgzzFiscalCode = jgzzFiscalCode;
    }

    public BigInteger getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(BigInteger dunsNumber) {
        this.dunsNumber = dunsNumber;
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

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public BigInteger getTotalNumOfOrders() {
        return totalNumOfOrders;
    }

    public void setTotalNumOfOrders(BigInteger totalNumOfOrders) {
        this.totalNumOfOrders = totalNumOfOrders;
    }

    public BigInteger getTotalOrderedAmount() {
        return totalOrderedAmount;
    }

    public void setTotalOrderedAmount(BigInteger totalOrderedAmount) {
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

    public boolean getDoNotMailFlag() {
        return doNotMailFlag;
    }

    public void setDoNotMailFlag(boolean doNotMailFlag) {
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

    public BigInteger getEmployeesTotal() {
        return employeesTotal;
    }

    public void setEmployeesTotal(BigInteger employeesTotal) {
        this.employeesTotal = employeesTotal;
    }

    public BigInteger getCurrFyPotentialRevenue() {
        return currFyPotentialRevenue;
    }

    public void setCurrFyPotentialRevenue(BigInteger currFyPotentialRevenue) {
        this.currFyPotentialRevenue = currFyPotentialRevenue;
    }

    public BigInteger getNextFyPotentialRevenue() {
        return nextFyPotentialRevenue;
    }

    public void setNextFyPotentialRevenue(BigInteger nextFyPotentialRevenue) {
        this.nextFyPotentialRevenue = nextFyPotentialRevenue;
    }

    public Short getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(Short yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public boolean getGsaIndicatorFlag() {
        return gsaIndicatorFlag;
    }

    public void setGsaIndicatorFlag(boolean gsaIndicatorFlag) {
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

    public boolean getReferenceUseFlag() {
        return referenceUseFlag;
    }

    public void setReferenceUseFlag(boolean referenceUseFlag) {
        this.referenceUseFlag = referenceUseFlag;
    }

    public boolean getThirdPartyFlag() {
        return thirdPartyFlag;
    }

    public void setThirdPartyFlag(boolean thirdPartyFlag) {
        this.thirdPartyFlag = thirdPartyFlag;
    }

    public boolean getCompetitorFlag() {
        return competitorFlag;
    }

    public void setCompetitorFlag(boolean competitorFlag) {
        this.competitorFlag = competitorFlag;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getKnownAs2() {
        return knownAs2;
    }

    public void setKnownAs2(String knownAs2) {
        this.knownAs2 = knownAs2;
    }

    public String getKnownAs3() {
        return knownAs3;
    }

    public void setKnownAs3(String knownAs3) {
        this.knownAs3 = knownAs3;
    }

    public String getKnownAs4() {
        return knownAs4;
    }

    public void setKnownAs4(String knownAs4) {
        this.knownAs4 = knownAs4;
    }

    public String getKnownAs5() {
        return knownAs5;
    }

    public void setKnownAs5(String knownAs5) {
        this.knownAs5 = knownAs5;
    }

    public String getDunsNumberC() {
        return dunsNumberC;
    }

    public void setDunsNumberC(String dunsNumberC) {
        this.dunsNumberC = dunsNumberC;
    }

    public BigInteger getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(BigInteger objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getCreatedByModule() {
        return createdByModule;
    }

    public void setCreatedByModule(String createdByModule) {
        this.createdByModule = createdByModule;
    }

    public BigInteger getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(BigInteger applicationId) {
        this.applicationId = applicationId;
    }

    public Long getPrimaryPhoneContactPtId() {
        return primaryPhoneContactPtId;
    }

    public void setPrimaryPhoneContactPtId(Long primaryPhoneContactPtId) {
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

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public Long getPersonBoVersion() {
        return personBoVersion;
    }

    public void setPersonBoVersion(Long personBoVersion) {
        this.personBoVersion = personBoVersion;
    }

    public Long getOrgBoVersion() {
        return orgBoVersion;
    }

    public void setOrgBoVersion(Long orgBoVersion) {
        this.orgBoVersion = orgBoVersion;
    }

    public Long getPersonCustBoVersion() {
        return personCustBoVersion;
    }

    public void setPersonCustBoVersion(Long personCustBoVersion) {
        this.personCustBoVersion = personCustBoVersion;
    }

    public Long getOrgCustBoVersion() {
        return orgCustBoVersion;
    }

    public void setOrgCustBoVersion(Long orgCustBoVersion) {
        this.orgCustBoVersion = orgCustBoVersion;
    }

    public CustomerType getPartyTypeId() {
        return partyTypeId;
    }

    public void setPartyTypeId(CustomerType partyTypeId) {
        this.partyTypeId = partyTypeId;
    }

    public BigInteger getCategoryCodeId() {
        return categoryCodeId;
    }

    public void setCategoryCodeId(BigInteger categoryCodeId) {
        this.categoryCodeId = categoryCodeId;
    }

    public BigInteger getPartyClassificationId() {
        return partyClassificationId;
    }

    public void setPartyClassificationId(BigInteger partyClassificationId) {
        this.partyClassificationId = partyClassificationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partyId != null ? partyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HzParties)) {
            return false;
        }
        HzParties other = (HzParties) object;
        if ((this.partyId == null && other.partyId != null) || (this.partyId != null && !this.partyId.equals(other.partyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.clients.HzParties[ partyId=" + partyId + " ]";
    }
    
}
