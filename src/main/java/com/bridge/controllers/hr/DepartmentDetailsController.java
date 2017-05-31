/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Department;
import com.bridge.entities.hr.Employee;
import com.bridge.entities.hr.People;
import com.bridge.services.hr.DepartmentService;
import com.bridge.services.hr.EmployeeService;
import com.bridge.services.hr.PeopleService;
import com.bridge.utils.JSFUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class DepartmentDetailsController implements Serializable {

    @EJB
    private DepartmentService departmentService;
    
    @EJB
    private PeopleService peopleService;

    private Department department;

    private Integer currentDepartmentId;

    public List<People> completeManager(String text) {
        return peopleService.findByName(text);
    }

    public Department getDepartment() {

        if (department == null) {
            department = new Department();
        }

        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setCurrentDepartmentId(Integer currentDepartmentId) {
        this.currentDepartmentId = currentDepartmentId;
    }

    public Integer getCurrentDepartmentId() {
        return currentDepartmentId;
    }

    public void loadDepartment() {

        if (currentDepartmentId != null) {
            department = departmentService.findById(currentDepartmentId);

            if (department == null) {
                JSFUtils.redirectTo404("Department With no = " + currentDepartmentId);
            }
        }
    }

    public String save() {

        departmentService.update(department);

        return "departmentsView?faces-redirect=true";
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentDepartmentId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (departmentService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateDepartmentCodeExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentDepartmentId != null) {
            if (input.getValue()!=null && input.getValue().equals(value)) {
                return;
            }
        }

        if (departmentService.isDepartmentCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

}
