/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.bank;

import com.bridge.entities.hr.Country;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AIA
 */
@Entity
@Table(name = "BANK_BRANCHES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankBranch.findAll", query = "SELECT b FROM BankBranch b")})
public class BankBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
        @GeneratedValue(strategy = GenerationType.TABLE,generator = "bankBranch")
    @TableGenerator(name = "bankBranch",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "BankBranch",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANK_BRANCH_ID")
    private Integer bankBranchId;
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bankId;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private BigInteger lastUpdatedBy;
    @Size(max = 60)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Size(max = 60)
    @Column(name = "BANK_BRANCH_NAME")
    private String bankBranchName;
    @Size(max = 240)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 35)
    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;
    @Size(max = 35)
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;
    @Size(max = 35)
    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;
    @Size(max = 25)
    @Column(name = "CITY")
    private String city;
    @Size(max = 25)
    @Column(name = "STATE")
    private String state;
    @Size(max = 20)
    @Column(name = "ZIP")
    private String zip;
    @Size(max = 25)
    @Column(name = "PROVINCE")
    private String province;
    @OneToOne
    @JoinColumn(name = "COUNTRY",referencedColumnName = "COUNTRY_CODE")
    private Country country;
    @Size(max = 10)
    @Column(name = "AREA_CODE")
    private String areaCode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "CONTACT_NAME")
    private String contactName;
    @Size(max = 15)
    @Column(name = "CONTACT_FIRST_NAME")
    private String contactFirstName;
    @Size(max = 15)
    @Column(name = "CONTACT_MIDDLE_NAME")
    private String contactMiddleName;
    @Size(max = 20)
    @Column(name = "CONTACT_LAST_NAME")
    private String contactLastName;
    @Size(max = 5)
    @Column(name = "CONTACT_PREFIX")
    private String contactPrefix;
    @Size(max = 30)
    @Column(name = "CONTACT_TITLE")
    private String contactTitle;
    @Size(max = 25)
    @Column(name = "BANK_NUM")
    private String bankNum;
    @Column(name = "LAST_UPDATE_LOGIN")
    private BigInteger lastUpdateLogin;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private BigInteger createdBy;
    @Size(max = 25)
    @Column(name = "INSTITUTION_TYPE")
    private String institutionType;
    @Column(name = "CLEARING_HOUSE_ID")
    private BigInteger clearingHouseId;
    @Column(name = "TRANSMISSION_PROGRAM_ID")
    private BigInteger transmissionProgramId;
    @Column(name = "PRINTING_PROGRAM_ID")
    private BigInteger printingProgramId;
    @Column(name = "REQUEST_ID")
    private BigInteger requestId;
    @Column(name = "PROGRAM_APPLICATION_ID")
    private BigInteger programApplicationId;
    @Column(name = "PROGRAM_ID")
    private BigInteger programId;
    @Column(name = "PROGRAM_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programUpdateDate;
    @Size(max = 30)
    @Column(name = "ADDRESS_STYLE")
    private String addressStyle;
    @Size(max = 30)
    @Column(name = "BANK_NUMBER")
    private String bankNumber;
    @Size(max = 35)
    @Column(name = "ADDRESS_LINE4")
    private String addressLine4;
    @Size(max = 25)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 30)
    @Column(name = "EFT_USER_NUMBER")
    private String eftUserNumber;
    @Size(max = 12)
    @Column(name = "EFT_SWIFT_CODE")
    private String eftSwiftCode;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 30)
    @Column(name = "EDI_ID_NUMBER")
    private String ediIdNumber;
    @Size(max = 25)
    @Column(name = "BANK_BRANCH_TYPE")
    private String bankBranchType;
    @Size(max = 320)
    @Column(name = "BANK_NAME_ALT")
    private String bankNameAlt;
    @Size(max = 320)
    @Column(name = "BANK_BRANCH_NAME_ALT")
    private String bankBranchNameAlt;
    @Size(max = 560)
    @Column(name = "ADDRESS_LINES_ALT")
    private String addressLinesAlt;
    @Column(name = "ACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;
    @Column(name = "TP_HEADER_ID")
    private BigInteger tpHeaderId;
    @Size(max = 60)
    @Column(name = "ECE_TP_LOCATION_CODE")
    private String eceTpLocationCode;
    @Column(name = "PAYROLL_BANK_ACCOUNT_ID")
    private BigInteger payrollBankAccountId;
    @Size(max = 15)
    @Column(name = "RFC_IDENTIFIER")
    private String rfcIdentifier;
    @Size(max = 255)
    @Column(name = "BANK_ADMIN_EMAIL")
    private String bankAdminEmail;

    public BankBranch() {
    }

    public BankBranch(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Integer getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(Integer bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public Bank getBankId() {
        return bankId;
    }

    public void setBankId(Bank bankId) {
        this.bankId = bankId;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactMiddleName() {
        return contactMiddleName;
    }

    public void setContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactPrefix() {
        return contactPrefix;
    }

    public void setContactPrefix(String contactPrefix) {
        this.contactPrefix = contactPrefix;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public BigInteger getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigInteger lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
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

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public BigInteger getClearingHouseId() {
        return clearingHouseId;
    }

    public void setClearingHouseId(BigInteger clearingHouseId) {
        this.clearingHouseId = clearingHouseId;
    }

    public BigInteger getTransmissionProgramId() {
        return transmissionProgramId;
    }

    public void setTransmissionProgramId(BigInteger transmissionProgramId) {
        this.transmissionProgramId = transmissionProgramId;
    }

    public BigInteger getPrintingProgramId() {
        return printingProgramId;
    }

    public void setPrintingProgramId(BigInteger printingProgramId) {
        this.printingProgramId = printingProgramId;
    }

    public BigInteger getRequestId() {
        return requestId;
    }

    public void setRequestId(BigInteger requestId) {
        this.requestId = requestId;
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

    public String getAddressStyle() {
        return addressStyle;
    }

    public void setAddressStyle(String addressStyle) {
        this.addressStyle = addressStyle;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEftUserNumber() {
        return eftUserNumber;
    }

    public void setEftUserNumber(String eftUserNumber) {
        this.eftUserNumber = eftUserNumber;
    }

    public String getEftSwiftCode() {
        return eftSwiftCode;
    }

    public void setEftSwiftCode(String eftSwiftCode) {
        this.eftSwiftCode = eftSwiftCode;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEdiIdNumber() {
        return ediIdNumber;
    }

    public void setEdiIdNumber(String ediIdNumber) {
        this.ediIdNumber = ediIdNumber;
    }

    public String getBankBranchType() {
        return bankBranchType;
    }

    public void setBankBranchType(String bankBranchType) {
        this.bankBranchType = bankBranchType;
    }

    public String getBankNameAlt() {
        return bankNameAlt;
    }

    public void setBankNameAlt(String bankNameAlt) {
        this.bankNameAlt = bankNameAlt;
    }

    public String getBankBranchNameAlt() {
        return bankBranchNameAlt;
    }

    public void setBankBranchNameAlt(String bankBranchNameAlt) {
        this.bankBranchNameAlt = bankBranchNameAlt;
    }

    public String getAddressLinesAlt() {
        return addressLinesAlt;
    }

    public void setAddressLinesAlt(String addressLinesAlt) {
        this.addressLinesAlt = addressLinesAlt;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public BigInteger getTpHeaderId() {
        return tpHeaderId;
    }

    public void setTpHeaderId(BigInteger tpHeaderId) {
        this.tpHeaderId = tpHeaderId;
    }

    public String getEceTpLocationCode() {
        return eceTpLocationCode;
    }

    public void setEceTpLocationCode(String eceTpLocationCode) {
        this.eceTpLocationCode = eceTpLocationCode;
    }

    public BigInteger getPayrollBankAccountId() {
        return payrollBankAccountId;
    }

    public void setPayrollBankAccountId(BigInteger payrollBankAccountId) {
        this.payrollBankAccountId = payrollBankAccountId;
    }

    public String getRfcIdentifier() {
        return rfcIdentifier;
    }

    public void setRfcIdentifier(String rfcIdentifier) {
        this.rfcIdentifier = rfcIdentifier;
    }

    public String getBankAdminEmail() {
        return bankAdminEmail;
    }

    public void setBankAdminEmail(String bankAdminEmail) {
        this.bankAdminEmail = bankAdminEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankBranchId != null ? bankBranchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankBranch)) {
            return false;
        }
        BankBranch other = (BankBranch) object;
        if ((this.bankBranchId == null && other.bankBranchId != null) || (this.bankBranchId != null && !this.bankBranchId.equals(other.bankBranchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.bank.BankBranch[ bankBranchId=" + bankBranchId + " ]";
    }
    
}
