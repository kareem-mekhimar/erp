/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.organization;

/**
 *
 * @author Administrator
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bridge.entities.hr.Location;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.LocationsService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.bridge.entities.hr.City;
import com.bridge.entities.hr.Country;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.CityService;

@Named
@ViewScoped
public class OrganizationUnitDetailsController implements Serializable {

    @EJB
    private OrganizationUnitService organizationUnitService;
    @EJB
    private LocationsService locatonsService;

    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;

    private List<Country> countryList;

    private List<City> cityList;

    private String countryCode;

    private List<Location> locations;
    private List<OrganizationUnit> businessGroups;
    private List<OrganizationUnit> legalEntities;
    private OrganizationUnit organizationUnit;
    private Integer currentOrganizationId;
    private Integer businessGropId;
    private String target;
    private OrganizationUnit BusinessGroup;
    private OrganizationUnit orgUnit;
    private OrganizationUnitType orgType;

    public String getCountryCode() {

        return countryCode;
    }

    public void setCountryCode(String countryCode) {

        this.countryCode = countryCode;
    }

    public List<Country> getCountryList() {

        return countryList;
    }

    public List<City> getCityList() {

        return cityList;
    }

    public List<Country> completeCountry(String text) {
        countryList = countryService.findCountry(text);
        return countryList;
    }

    public List<String> completeCity(String text) {

        if (organizationUnit.getCountry() == null) {
            return null;
        }

        return cityService.findCityName(organizationUnit.getCountry().getCountryCode(), text);

    }

    public void resetForm() {
        organizationUnit.setTownOrCityId(null);
    }

    public OrganizationUnit getBusinessGroup() {
        return BusinessGroup;
    }

    public void setBusinessGroup(OrganizationUnit BusinessGroup) {
        this.BusinessGroup = BusinessGroup;
    }

    public OrganizationUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrganizationUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public Integer getBusinessGropId() {
        return businessGropId;
    }

    public void setBusinessGropId(Integer businessGropId) {
        this.businessGropId = businessGropId;
    }

    public List<OrganizationUnit> getBusinessGroups() {
        return businessGroups;
    }

    public void setBusinessGroups(List<OrganizationUnit> businessGroups) {
        this.businessGroups = businessGroups;
    }

    public List<OrganizationUnit> getLegalEntities() {
        return legalEntities;
    }

    public void setLegalEntities(List<OrganizationUnit> legalEntities) {
        this.legalEntities = legalEntities;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public OrganizationUnit getOrganizationUnit() {

        if (organizationUnit == null) {
            organizationUnit = new OrganizationUnit();
            organizationUnit.setEnabledFlagId(true);
        }

        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public Integer getCurrentOrganizationId() {
        return currentOrganizationId;
    }

    public void setCurrentOrganizationId(Integer currentOrganizationId) {
        this.currentOrganizationId = currentOrganizationId;
    }

    public void loadOrganizationUnit() {

        if (currentOrganizationId != null) {
            organizationUnit = organizationUnitService.findById(currentOrganizationId);

            if (organizationUnit == null) {
                JSFUtils.redirectTo404("Organization Unit With no = " + currentOrganizationId);
            }
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    @PostConstruct
    public void loadHrLocation() {
        locations = locatonsService.findAll();
        businessGroups = organizationUnitService.findByOrgType(OrganizationUnitType.BUSINESS_GROUP);
        legalEntities = organizationUnitService.findLegalHasLedger();
    }

    public List<Location> getHrLocation() {
        return locations;
    }

    public void setHrLocation(List<Location> locations) {
        this.locations = locations;
    }

    public String save(Integer t) {

        switch (t) {
            case 0:
                organizationUnit.setUnitType(OrganizationUnitType.BUSINESS_GROUP);
                target = "businessGroupsView?faces-redirect=true";
                break;
            case 1:
                organizationUnit.setUnitType(OrganizationUnitType.LEGAL_ENTITY);
                target = "legalEntitiesView?faces-redirect=true";
                break;
            case 2:
                organizationUnit.setUnitType(OrganizationUnitType.OPERATING_UNIT);
                target = "operatingUnitsView?faces-redirect=true";
                break;
        }

        organizationUnitService.update(organizationUnit);
        return target;
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentOrganizationId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (organizationUnitService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentOrganizationId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (organizationUnitService.isCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    // 23/5/2017:
    public OrganizationUnitType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrganizationUnitType orgType) {
        this.orgType = orgType;
    }

    public String save(OrganizationUnitType orgUnitType) {

        switch (orgUnitType) {
            case BUSINESS_GROUP:
                organizationUnit.setUnitType(OrganizationUnitType.BUSINESS_GROUP);
                target = "businessGroupsView?faces-redirect=true";
                break;
            case LEGAL_ENTITY:
                organizationUnit.setUnitType(OrganizationUnitType.LEGAL_ENTITY);
                target = "legalEntitiesView?faces-redirect=true";
                break;
            case OPERATING_UNIT:
                organizationUnit.setUnitType(OrganizationUnitType.OPERATING_UNIT);
                target = "operatingUnitsView?faces-redirect=true";
                break;
        }

        organizationUnitService.update(organizationUnit);
        return target;
    }

    public void chooseType(Integer t) {
        switch (t) {
            case 0:
                setOrgType(OrganizationUnitType.BUSINESS_GROUP) ; 
                break;
            case 1:
                setOrgType(OrganizationUnitType.LEGAL_ENTITY);
                break;
            case 2:
                setOrgType(OrganizationUnitType.OPERATING_UNIT);
                break;
            default:
                setOrgType(null) ;               
        }
    }
    
    public void validateOrgCodeExist(FacesContext context, UIComponent component, Object value) {
        
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentOrganizationId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (organizationUnitService.isOrgCodeExists(orgType, (String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }
    
    public void validateOrgNameExist(FacesContext context, UIComponent component, Object value) {
        
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentOrganizationId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (organizationUnitService.isOrgNameExists(orgType, (String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }
        
}
