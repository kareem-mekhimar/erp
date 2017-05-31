/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.entities.hr;

import com.bridge.entities.organization.OrganizationUnit;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Bridge
 */
@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "emp")
    @TableGenerator(name = "emp",table = "SYSTEM_SEQUENCES_GENERATOR",allocationSize = 20
            ,pkColumnName = "SEQUENCE_NAME",pkColumnValue = "Emp",valueColumnName = "CURRENT_VALUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIRST_NAME_EN")
    private String firstNameEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SECOND_NAME_EN")
    private String secondNameEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "THIRD_NAME_EN")
    private String thirdNameEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "LAST_NAME_EN")
    private String lastNameEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIRST_NAME_AR")
    private String firstNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SECOND_NAME_AR")
    private String secondNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "THIRD_NAME_AR")
    private String thirdNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "LAST_NAME_AR")
    private String lastNameAr;
    @Column(name = "STATUS")
    private boolean status;

    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email Pattern")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hireDate = new Date();
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "MANAGER_ID")
    private Integer managerId;
    
    @ManyToOne
    @JoinColumn(name = "BUSINESS_GROUP_ID",referencedColumnName = "ORG_UNIT_ID")
    private OrganizationUnit businessGroup;
    
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    @ManyToOne
    private Department department;
    
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
    @ManyToOne(optional = false)
    private Job job;

    public Employee() {
    }

    public Employee(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(Integer employeeId, String firstNameEn, String secondNameEn, String thirdNameEn, String lastNameEn, String firstNameAr, String secondNameAr, String thirdNameAr, String lastNameAr, String email, Date hireDate) {
        this.employeeId = employeeId;
        this.firstNameEn = firstNameEn;
        this.secondNameEn = secondNameEn;
        this.thirdNameEn = thirdNameEn;
        this.lastNameEn = lastNameEn;
        this.firstNameAr = firstNameAr;
        this.secondNameAr = secondNameAr;
        this.thirdNameAr = thirdNameAr;
        this.lastNameAr = lastNameAr;
        this.email = email;
        this.hireDate = hireDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getSecondNameEn() {
        return secondNameEn;
    }

    public void setSecondNameEn(String secondNameEn) {
        this.secondNameEn = secondNameEn;
    }

    public String getThirdNameEn() {
        return thirdNameEn;
    }

    public void setThirdNameEn(String thirdNameEn) {
        this.thirdNameEn = thirdNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getFirstNameAr() {
        return firstNameAr;
    }

    public void setFirstNameAr(String firstNameAr) {
        this.firstNameAr = firstNameAr;
    }

    public String getSecondNameAr() {
        return secondNameAr;
    }

    public void setSecondNameAr(String secondNameAr) {
        this.secondNameAr = secondNameAr;
    }

    public String getThirdNameAr() {
        return thirdNameAr;
    }

    public void setThirdNameAr(String thirdNameAr) {
        this.thirdNameAr = thirdNameAr;
    }

    public String getLastNameAr() {
        return lastNameAr;
    }

    public void setLastNameAr(String lastNameAr) {
        this.lastNameAr = lastNameAr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }


    public OrganizationUnit getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(OrganizationUnit businessGroup) {
        this.businessGroup = businessGroup;
    }

    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    
        public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeId != null ? employeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bridge.entities.Employee[ employeeId=" + employeeId + " ]";
    }



    
}
