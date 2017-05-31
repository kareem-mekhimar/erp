/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;
import com.bridge.entities.hr.Department;
import com.bridge.services.hr.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class DepartmentController implements Serializable{
    @EJB
    private DepartmentService departmentservice;
    private Department department;
    private List<Department> departmentslist;
    private Integer status;
    private Integer departmentid;
    private String departmentname;
    private String departmentCode;
    

    public List<Department> getDepartmentslist(){
        
        return departmentslist;
    }
    public Integer getStatus(){
        return status;
    }
        public Integer getDepartmentid(){
        return departmentid;
    }
   
       public String getDepartmentname(){
        return departmentname;
    }
       
       public void setStatus(Integer status){
           this.status=status;
       }
       public void setDepartmentid(Integer departmentid){
           this.departmentid=departmentid;
       }
       public void setDepartmentname(String departmentname){
           this.departmentname=departmentname;
       }
       public Department getDepartment(){
           return department;
       }
       public void setDepartment(Department department){
           this.department=department;
       }
    public void filter(){
        departmentslist = departmentservice.findDepartment(departmentid,departmentname,status, departmentCode);        
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

   
}
