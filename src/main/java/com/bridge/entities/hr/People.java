/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hr;

import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "PER_ALL_PEOPLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "People.findAll", query = "SELECT p FROM People p")})
public class People implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "people")
    @TableGenerator(name = "people",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "People",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERSON_ID")
    private Integer personId;
    @Column(name = "EFFECTIVE_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveStartDate;
    @Column(name = "EFFECTIVE_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveEndDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Size(max = 4)
    @Column(name = "CHAR_SET_CONTEXT")
    private String charSetContext;
    @Size(max = 240)
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Size(max = 150)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 2000)
    @Column(name = "FULL_NAME")
    private String fullName;
    @Size(max = 80)
    @Column(name = "HONORS")
    private String honors;
    @Size(max = 80)
    @Column(name = "KNOWN_AS")
    private String knownAs;
    @Size(max = 150)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 4)
    @Column(name = "LEGISLATION_CODE")
    private String legislationCode;
    @Size(max = 240)
    @Column(name = "LIST_NAME")
    private String listName;
    @Size(max = 80)
    @Column(name = "MIDDLE_NAMES")
    private String middleNames;
    @Size(max = 80)
    @Column(name = "MILITARY_RANK")
    private String militaryRank;
    @Size(max = 30)
    @Column(name = "NAME_TYPE")
    private String nameType;
    @Size(max = 240)
    @Column(name = "ORDER_NAME")
    private String orderName;
    @Column(name = "PERSON_CODE")
    private String personCode;
    @Size(max = 150)
    @Column(name = "PRE_NAME_ADJUNCT")
    private String preNameAdjunct;
    @Size(max = 150)
    @Column(name = "PREVIOUS_LAST_NAME")
    private String previousLastName;
    @Column(name = "PRIMARY_EMAIL")
    private String primaryEmail;
    @Column(name = "PRIMARY_PHONE")
    private String primaryPhone;
    @Size(max = 80)
    @Column(name = "SUFFIX")
    private String suffix;
    @Size(max = 30)
    @Column(name = "TITLE")
    private String title; 
    @Size(max = 150)
    @Column(name = "INSURANCE_NUMBER")
    private String insuranceNumber;
    @Column(name = "INSURANCE_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insuranceStartDate;
    @Size(max = 150)
    @Column(name = "BANK_ACOUNT_NUMBER")
    private String bankAcountNumber;
    @Size(max = 150)
    @Column(name = "BANK_CARD_NUMBER_1")
    private String bankCardNumber1;
    @Size(max = 150)
    @Column(name = "BANK_CARD_NUMBER_2")
    private String bankCardNumber2;
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroupId;
    @OneToOne
    @JoinColumn(name="POSITION_ID",referencedColumnName ="POSITION_ID" )
    private Position position;
    @OneToOne
    @JoinColumn(name="ORGANIZATION_ID",referencedColumnName ="ORGANIZATION_ID" )
    private HrOrganizationUnit hrOrgUnit;
      @Column(name = "APPLICANT_NUMBER")
    private Integer applicantNumber;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATION_DATE")
    private Integer creationDate;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;
    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;
    @Column(name = "MAILING_ADDRESS_ID")
    private Integer mailingAddressId;
    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;
    @Column(name = "PERSON_NUMBER")
    private Integer personNumber;
    @Column(name = "PRIMARY_NID_ID")
    private Integer primaryNidId;
    @Column(name = "INSURANCE_FLAG")
    private boolean insuranceFlag;
    @Column(name = "INSURANCE_DEGREE")
    private Integer insuranceDegree;
    @Column(name = "BASIC_SALARY")
    private Integer basicSalary;
    @Column(name = "VARIABLE_SALARY")
    private Integer variableSalary;
    @Column(name = "ALLOWANCES_SALARY")
    private Integer allowancesSalary;
    @Column(name = "OVERTIME_SALARY")
    private Integer overtimeSalary;
    @Column(name = "LIFE_INSURANCE_INSTALMENT")
    private Integer lifeInsuranceInstalment;
    @Column(name = "FELLOWSHIP_FUND")
    private Integer fellowshipFund;
    @Column(name = "MOBILE_EXPENSES")
    private Integer mobileExpenses;
    @Column(name = "OLD_EXPENSES_INSURANCE")
    private Integer oldExpensesInsurance;
    @Column(name = "LIFE_INSURANCE")
    private Integer lifeInsurance;
    @Column(name = "SOCIAL_COOPERATION")
    private Integer socialCooperation;
    @Column(name = "SALES_PERSON")
    private boolean salesPerson;
    @OneToOne
    @JoinColumn(name = "COUNTRY")
    private Country country;
    @Column(name = "COUNTRY_ID")
    private Integer countryId;
    @Size(max = 60)
    @Column(name = "TOWN_OR_CITY")
    private String townOrCity;
    @Column(name = "TOWN_OR_CITY_ID")
    private Integer townOrCityId;
    @Size(max = 70)
    @Column(name = "REGION")
    private String region;
    @Size(max = 60)
    @Column(name = "ADDRESS_DETAILS")
    private String addressDetails;

    
    @Column(name = "IMAGE_URL")
    private String imageFileName ;
    
    public People() {
    }

    public People(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getApplicantNumber() {
        return applicantNumber;
    }

    public void setApplicantNumber(Integer applicantNumber) {
        this.applicantNumber = applicantNumber;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public Integer getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public Integer getPrimaryNidId() {
        return primaryNidId;
    }

    public void setPrimaryNidId(Integer primaryNidId) {
        this.primaryNidId = primaryNidId;
    }

    public boolean isSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(boolean salesPerson) {
        this.salesPerson = salesPerson;
    }
    
    
  
    public OrganizationUnit getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(OrganizationUnit businessGroupId) {
        this.businessGroupId = businessGroupId;
    }
    
    
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public HrOrganizationUnit getHrOrgUnit() {
        return hrOrgUnit;
    }

    public void setHrOrgUnit(HrOrganizationUnit hrOrgUnit) {
        this.hrOrgUnit = hrOrgUnit;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof People)) {
            return false;
        }
        People other = (People) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.hr.People[ personId=" + personId + " ]";
    }

    public String getCharSetContext() {
        return charSetContext;
    }

    public void setCharSetContext(String charSetContext) {
        this.charSetContext = charSetContext;
    }



    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHonors() {
        return honors;
    }

    public void setHonors(String honors) {
        this.honors = honors;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getLegislationCode() {
        return legislationCode;
    }

    public void setLegislationCode(String legislationCode) {
        this.legislationCode = legislationCode;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getMilitaryRank() {
        return militaryRank;
    }

    public void setMilitaryRank(String militaryRank) {
        this.militaryRank = militaryRank;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }



    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

 

    public String getPreNameAdjunct() {
        return preNameAdjunct;
    }

    public void setPreNameAdjunct(String preNameAdjunct) {
        this.preNameAdjunct = preNameAdjunct;
    }

    public String getPreviousLastName() {
        return previousLastName;
    }

    public void setPreviousLastName(String previousLastName) {
        this.previousLastName = previousLastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }
    public boolean getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(boolean insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Integer getInsuranceDegree() {
        return insuranceDegree;
    }

    public void setInsuranceDegree(Integer insuranceDegree) {
        this.insuranceDegree = insuranceDegree;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getVariableSalary() {
        return variableSalary;
    }

    public void setVariableSalary(Integer variableSalary) {
        this.variableSalary = variableSalary;
    }

    public Integer getAllowancesSalary() {
        return allowancesSalary;
    }

    public void setAllowancesSalary(Integer allowancesSalary) {
        this.allowancesSalary = allowancesSalary;
    }

    public Integer getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(Integer overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public Integer getLifeInsuranceInstalment() {
        return lifeInsuranceInstalment;
    }

    public void setLifeInsuranceInstalment(Integer lifeInsuranceInstalment) {
        this.lifeInsuranceInstalment = lifeInsuranceInstalment;
    }

    public String getBankAcountNumber() {
        return bankAcountNumber;
    }

    public void setBankAcountNumber(String bankAcountNumber) {
        this.bankAcountNumber = bankAcountNumber;
    }

    public String getBankCardNumber1() {
        return bankCardNumber1;
    }

    public void setBankCardNumber1(String bankCardNumber1) {
        this.bankCardNumber1 = bankCardNumber1;
    }

    public String getBankCardNumber2() {
        return bankCardNumber2;
    }

    public void setBankCardNumber2(String bankCardNumber2) {
        this.bankCardNumber2 = bankCardNumber2;
    }

    public Integer getFellowshipFund() {
        return fellowshipFund;
    }

    public void setFellowshipFund(Integer fellowshipFund) {
        this.fellowshipFund = fellowshipFund;
    }

    public Integer getMobileExpenses() {
        return mobileExpenses;
    }

    public void setMobileExpenses(Integer mobileExpenses) {
        this.mobileExpenses = mobileExpenses;
    }

    public Integer getOldExpensesInsurance() {
        return oldExpensesInsurance;
    }

    public void setOldExpensesInsurance(Integer oldExpensesInsurance) {
        this.oldExpensesInsurance = oldExpensesInsurance;
    }

    public Integer getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(Integer lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public Integer getSocialCooperation() {
        return socialCooperation;
    }

    public void setSocialCooperation(Integer socialCooperation) {
        this.socialCooperation = socialCooperation;
    }


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public Integer getTownOrCityId() {
        return townOrCityId;
    }

    public void setTownOrCityId(Integer townOrCityId) {
        this.townOrCityId = townOrCityId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }
    
    
}
