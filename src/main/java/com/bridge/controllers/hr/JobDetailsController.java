/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Job;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.JobService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
public class JobDetailsController implements Serializable {

    @EJB
    private JobService jobService;
    @EJB
    private OrganizationUnitService orgUnitService;

    private List<OrganizationUnit> businessGroupList;

    private Job currentJob;

    private Integer currentJobId;

    public Job getCurrentJob() {

        if (currentJob == null) {
            currentJob = new Job();
            currentJob.setDateFrom(new Date());
        }

        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public List<OrganizationUnit> getBusinessGroupList() {
        return businessGroupList;
    }

    public void setBusinessGroupList(List<OrganizationUnit> businessGroupList) {
        this.businessGroupList = businessGroupList;
    }

    public void setCurrentJobId(Integer currentJobId) {
        this.currentJobId = currentJobId;
    }

    public Integer getCurrentJobId() {
        return currentJobId;
    }

    public void loadJob() {

        if (currentJobId != null) {
            currentJob = jobService.findById(currentJobId);

        }
    }

    public List<OrganizationUnit> completeOrgUnit(String text) {

        businessGroupList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return businessGroupList;
    }

    public String save() {

        jobService.update(currentJob);

        return "jobsView?faces-redirect=true";
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        UIInput firstDateComp = (UIInput) component.findComponent("sad");

        Date firstDate = (Date) firstDateComp.getValue();

        Date secondDate = (Date) value;

        if (firstDate == null || secondDate == null) {
            return;
        }

        if (firstDate.after(secondDate)) {
            throw new ValidatorException(new FacesMessage("Invalid Date!"));
        }
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentJobId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (jobService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentJobId != null) {
            if (input.getValue()!=null && input.getValue().equals(value)) {
                return;
            }
        }
        if (jobService.isCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

}
