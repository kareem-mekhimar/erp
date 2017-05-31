/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.City;
import com.bridge.entities.hr.Country;
import com.bridge.entities.hr.People;
import com.bridge.entities.hr.Position;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.CityService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.PeopleService;
import com.bridge.services.hr.PositionService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class PeopleDetailsController implements Serializable {

    @EJB
    private PeopleService peopleService;
    @EJB
    private PositionService positionService;
    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;
    @EJB
    private OrganizationUnitService orgUnitService;

    private List<OrganizationUnit> businessGroupList;
    private Integer currentPersonId;
    private People currentPeople;
    private boolean insurance;

    private UploadedFile image;
    private byte[] imgContent;

    public Integer getCurrentPersonId() {
        return currentPersonId;
    }

    public void setCurrentPersonId(Integer currentPersonId) {
        this.currentPersonId = currentPersonId;
    }

    public People getCurrentPeople() {
        if (currentPeople == null) {
            currentPeople = new People();

        }
        return currentPeople;
    }

    public List<OrganizationUnit> completeOrgUnit(String text) {

        businessGroupList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return businessGroupList;
    }

    public void setCurrentPeople(People currentPeople) {

        this.currentPeople = currentPeople;

    }

    public byte[] getImgContent() {
        return imgContent;
    }

    public void setImgContent(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public void loadPeople() {
        if (currentPersonId != null) {

            currentPeople = peopleService.findPeopleDetails(currentPersonId);
            
            if(currentPeople == null){
                
                JSFUtils.redirectTo404("Person");
                
            }
            
            setInsuranceFlag();
        }
    }

    public void setInsuranceFlag() {

        if (currentPeople.getInsuranceNumber() != null) {
            if (currentPeople.getInsuranceNumber().isEmpty()) {
                setInsurance(false);
            } else {
                setInsurance(true);
            }
        } else {
            setInsurance(false);
        }
    }

    public List<Position> completePosition(String text) {
        return positionService.findPositionByName(text);
    }

    public List<Country> completeCountry(String text) {
        return countryService.findCountry(text);
    }

    public void resetCity() {
        currentPeople.setTownOrCity(null);
    }

    public List<String> completeCity(String text) {
if(currentPeople.getCountry()==null) return null;
        return cityService.findCityName(currentPeople.getCountry().getCountryCode(), text);
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentPersonId != null && input.getValue().equals(value)) {
            return;
        }

        if (peopleService.isCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public String fullName() {
        String fullName, firstName, middleName, lastName;
        firstName = currentPeople.getFirstName();
        middleName = currentPeople.getMiddleNames();
        lastName = currentPeople.getLastName();
        fullName = firstName + " " + lastName;
        if (middleName != null && !middleName.isEmpty()) {
            fullName = firstName + " " + middleName + " " + lastName;
        }
        return fullName;
    }

    public String save() {

        currentPeople.setFullName(fullName());
        currentPeople.setInsuranceFlag(insurance);

        currentPeople = peopleService.update(currentPeople);

        if (imgContent != null) {
            try {

                String fileName = image.getFileName();

                int lastDot = fileName.lastIndexOf('.');

                String s = fileName.substring(0, lastDot) + "_" + currentPeople.getPersonId() + fileName.substring(lastDot);

                String path = System.getProperty("jboss.server.data.dir") + "/imgs/" + s;

                Files.copy(new ByteArrayInputStream(imgContent), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

                currentPeople.setImageFileName(s);

                peopleService.update(currentPeople);

            } catch (IOException ex) {

                Logger.getLogger(PeopleDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return "peopleView?faces-redirect=true";
    }

    ////////////////////// This Class Must Be Destroyed......[kareem]
    public void handleFileUpload(FileUploadEvent uploadEvent) throws IOException {

        this.image = uploadEvent.getFile();

        imgContent = image.getContents();

    }
}
