/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Department;
import com.bridge.entities.hr.Employee;
import com.bridge.entities.hr.Job;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.DepartmentService;
import com.bridge.services.hr.EmployeeService;
import com.bridge.services.hr.JobService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class EmployeeDetailsController implements Serializable{
    
    @EJB
    private EmployeeService employeeService ;
    
    @EJB
    private JobService jobService ;
    
    @EJB
    private DepartmentService departmentService ;
    
    @EJB
    private OrganizationUnitService organizationUnitService ;

    private List<Job> jobs ;
    
    private List<Department> departments ;
    
    private List<OrganizationUnit> businessGroups ;
    
    private Employee currentEmployee ;

    private Integer currentEmpId ;

    @PostConstruct
    private void init()
    {
      departments = departmentService.findAll() ;
      
      jobs = jobService.findAll() ;
      
      businessGroups = organizationUnitService.findByOrgType(OrganizationUnitType.BUSINESS_GROUP) ;
      
    }
    
    public Integer getCurrentEmpId() {
        
        return currentEmpId;
    }

    public void setCurrentEmpId(Integer currentEmpId) {
    
        this.currentEmpId = currentEmpId;
    }
    
    public void loadEmployee()
    {
       if(currentEmpId != null)
           
           currentEmployee = employeeService.findById(currentEmpId) ;
    }
    
    public Employee getCurrentEmployee() {
    
        if(currentEmployee == null)
            
            currentEmployee = new Employee() ;
        
        return currentEmployee;
    }
    
    public String save()
    {
        employeeService.update(currentEmployee) ;
        
        return "view?faces-redirect=true" ;
    }

    public List<OrganizationUnit> getBusinessGroups() {
        return businessGroups;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
