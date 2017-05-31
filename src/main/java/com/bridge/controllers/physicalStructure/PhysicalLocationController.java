/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.physicalStructure;

import com.bridge.entities.physicalStructure.PhysicalLevelDetail;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.services.physicalStructure.PhysicalLocationService;
import com.bridge.services.physicalStructure.PhysicalStructureService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class PhysicalLocationController implements Serializable {

    @EJB
    private PhysicalStructureService structureService;
    @EJB
    private PhysicalLocationService locationService;

    private List<PhysicalLocation> physicalLocations;

    private PhysicalStructure currentPhysicalStructure;

    private PhysicalLocation currentPhysicalLocation;

    private List<Integer> oldIds = new ArrayList<Integer>();

    private String[] lableArray;

    private Integer orgId;

    private List<String> changedItems=new ArrayList<String>();

    private PhysicalLocation oldPhysicalLocation =new PhysicalLocation();

    public void loadStructure() {

        if (orgId != null) {
            currentPhysicalStructure = structureService.findStructureWithLevel(orgId);

            if (currentPhysicalStructure == null) {
                JSFUtils.redirectTo404("Physical Structure");
            } else {

                physicalLocations = locationService.findAll();
            }

        } else {
            JSFUtils.redirectTo404("Physical Accounts");
        }

        physicalLocations = locationService.findAll();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<PhysicalLocation> getPhysicalLocations() {

        return physicalLocations;
    }

    public void setPhysicalLocations(List<PhysicalLocation> physicalLocations) {
        this.physicalLocations = physicalLocations;
    }

    public PhysicalLocation getCurrentPhysicalLocation() {

        return currentPhysicalLocation;
    }

    public void setCurrentPhysicalLocation(PhysicalLocation currentPhysicalLocation) {
        this.currentPhysicalLocation = currentPhysicalLocation;
    }


    public void prepareLocationLines() {

        Integer size = currentPhysicalStructure.getPhysicalLevels().size();

        if (currentPhysicalLocation == null) {

            currentPhysicalLocation = new PhysicalLocation();

            currentPhysicalLocation.setLevelDetails(new ArrayList());

            for (int i = 0; i < size; i++) {
                currentPhysicalLocation.getLevelDetails().add(new PhysicalLevelDetail());
            }

        } else if (currentPhysicalLocation.getLocationId() != null && !oldIds.contains(currentPhysicalLocation.getLocationId())) {

            oldIds.add(currentPhysicalLocation.getLocationId());

            currentPhysicalLocation.setLevelDetails(locationService
                    .findLocationWithDetails(currentPhysicalLocation.getLocationId()).getLevelDetails());

        }

        lableArray = new String[size];

        for (int i = 0; i < size; i++) {
            lableArray[i] = currentPhysicalStructure.getPhysicalLevels().get(i).getLevelName();
        }

        oldPhysicalLocation.setLevelDetails(new ArrayList());
        for(PhysicalLevelDetail l : currentPhysicalLocation.getLevelDetails()){
            oldPhysicalLocation.getLevelDetails().add(l);
        }
        

    }

    public List<PhysicalLevelDetail> completeLevelDetail(String text) {

        int orderNo = (int) JSFUtils.evaluateValueExpression("#{state.index + 1}", Integer.class);

        return locationService.findLevelDetailsByOrderAndStructureAndConntains(orderNo, currentPhysicalStructure, text)
                .stream().filter(d -> d.getDependsOnLevel() == null
                || d.getDependsOnLevel().equals(currentPhysicalLocation.getLevelDetails()
                        .get(d.getLevelId().getDependOnLevelOrder() - 1))).collect(Collectors.toList());
    }

    public void onDialogOk() {
        String location = currentPhysicalLocation.getLevelDetails().stream()
                .map(PhysicalLevelDetail::getDetailName)
                .collect(Collectors.joining(","));

        if (!isLocationExists(location)) {
            if(! changedItems.contains(location))changedItems.add(location);

            currentPhysicalLocation.setLocationName(location);

            if (currentPhysicalLocation.getOrganizationId() == null) {

                physicalLocations.add(currentPhysicalLocation);

                currentPhysicalLocation.setOrganizationId(orgId);
            }

            RequestContext.getCurrentInstance().execute("modal.hide()");

            RequestContext.getCurrentInstance().update("table");

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Location already exists",
                            "Location already exists"));
        }

    }

    
    
    public void onDialgCancel() {
       
        currentPhysicalLocation.setLevelDetails(oldPhysicalLocation.getLevelDetails());
        
    }

    private boolean isLocationExists(String name) {
        return physicalLocations.stream().filter(a -> a != currentPhysicalLocation && a.getLocationName().equals(name)).count() > 0;
    }

    private boolean isDescriptionExists(String desc) {
        return physicalLocations.stream().filter(a -> a != currentPhysicalLocation && a.getLocationDescription().equals(desc)).count() > 0;
    }

    public void validateDescription(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        if (isDescriptionExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public String save() {

        physicalLocations = physicalLocations.stream().filter(l-> changedItems.contains(l.getLocationName())).collect(Collectors.toList());
        
      if(physicalLocations.size() >0 )  locationService.update(physicalLocations);

        return "physicalStructureView?faces-redirect=true";
    }

    public PhysicalStructure getCurrentPhysicalStructure() {
        return currentPhysicalStructure;
    }

    public void setCurrentPhysicalStructure(PhysicalStructure currentPhysicalStructure) {
        this.currentPhysicalStructure = currentPhysicalStructure;
    }

    public String[] getLableArray() {
        return lableArray;
    }

    public void loadLocations() {
        loadStructure();
        physicalLocations = locationService.findLocationsForOperatingUnit(orgId);
    }

}
