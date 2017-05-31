/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Location;
import com.bridge.entities.hr.Job;
import com.bridge.entities.hr.Position;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.JobService;
import com.bridge.services.hr.LocationsService;
import com.bridge.services.hr.PositionService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
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
public class positionDetailsController implements Serializable {

    @EJB
    private PositionService positionService;
    @EJB
    private JobService jobService;
    @EJB
    private LocationsService locationService;
    @EJB
    private OrganizationUnitService orgUnitService;

    private Position currentPosition;
    private Integer currentPositionId;
    private List<OrganizationUnit> orgUnitList;
    private List<Job> jobList;
    private List<Location> locationList;

    public Position getCurrentPosition() {
        if (currentPosition == null) {
            currentPosition = new Position();
        }
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Integer getCurrentPositionId() {
        return currentPositionId;
    }

    public void setCurrentPositionId(Integer currentPositionId) {
        this.currentPositionId = currentPositionId;
    }

    public List<OrganizationUnit> getOrgUnitList() {
        return orgUnitList;
    }

    public void setOrgUnitList(List<OrganizationUnit> orgUnitList) {
        this.orgUnitList = orgUnitList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<OrganizationUnit> completeOrgUnit(String text) {

        orgUnitList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return orgUnitList;
    }

    public List<Job> completeJob(String text) {

        jobList = jobService.findByName(text);

        return jobList;
    }

    public List<Location> completeLocation(String text) {

        locationList = locationService.findLocationByCode(text);

        return locationList;
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;

        if (currentPositionId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (positionService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void loadPosition() {

        if (currentPositionId != null) {

            currentPosition = positionService.findById(currentPositionId);

        }
    }

    public String save() {
        positionService.update(currentPosition);
        return "positionView?faces-redirect=true";
    }

    public void validatePositionCodeExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;

        if (currentPositionId != null) {
            if (input.getValue()!=null && input.getValue().equals(value)) {
                return;
            }
        }
        if (positionService.isPositionCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

}
