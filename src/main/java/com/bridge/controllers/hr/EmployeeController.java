/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;


import com.bridge.entities.hr.Department;
import com.bridge.entities.hr.Employee;

import com.bridge.entities.hr.Job;
import com.bridge.services.hr.DepartmentService;
import com.bridge.services.hr.EmployeeService;
import com.bridge.services.hr.JobService;
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
public class EmployeeController implements Serializable{
    
    @EJB
    private EmployeeService employeeService ;
    
    @EJB
    private DepartmentService departmentService ;
    
    @EJB
    private JobService jobService ;
    
    private String currentName ;
    
    private List<Employee> employees ;
    
    private List<Job> jobs ;
    
    private List<Department> departments ;
    
    private Integer currentDeptId ;
    
    private Integer currentJobId ;
    
    @PostConstruct
    private void init()
    {
      employees = employeeService.findAll() ;
      
      departments = departmentService.findAll() ;
      
      jobs = jobService.findAll() ;
    }

    public void filter()
    {
      employees = employeeService.findByNameOrDeptOrJob(currentName, currentDeptId, currentJobId) ;
    }
    
    public List<Employee> getEmployees() {
    
        return employees;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Integer getCurrentDeptId() {
        return currentDeptId;
    }

    public Integer getCurrentJobId() {
        return currentJobId;
    }

    public void setCurrentDeptId(Integer currentDeptId) {
        this.currentDeptId = currentDeptId;
    }

    public void setCurrentJobId(Integer currentJobId) {
        this.currentJobId = currentJobId;
    }

    
    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
    
    
}
