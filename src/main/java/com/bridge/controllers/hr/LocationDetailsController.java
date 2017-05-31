/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

/**
 *
 * @author Administrator
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bridge.entities.hr.City;
import com.bridge.entities.hr.Country;
import com.bridge.entities.hr.Location;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.hr.CityService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.LocationsService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
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
public class LocationDetailsController implements Serializable {

    @EJB
    private LocationsService locationsService;
    @EJB
    private OrganizationUnitService orgUnitService;
    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;

    private List<OrganizationUnit> businessGroupList;

    private List<Country> countryList;

    private List<City> cityList;

    private String countryCode;

    private Location currentLocation;

    private Integer currentLocationId;

    
    public String getCountryCode() {
        
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        
        this.countryCode = countryCode;
    }

    public List<OrganizationUnit> getBusinessGroupList() {
        return businessGroupList;
    }

    public List<Country> getCountryList() {
        
        return countryList;
    }

    public List<City> getCityList() {

        return cityList;
    }

    public Location getCurrentLocation() {

        if (currentLocation == null) {
            currentLocation = new Location();
            
        }

        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {

        this.currentLocation = currentLocation;
    }

    public Integer getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(Integer currentLocationId) {
        this.currentLocationId = currentLocationId;
    }

    public void loadLocation() {

        if (currentLocationId != null) {
            currentLocation = locationsService.findById(currentLocationId);

            if (currentLocation == null) {
                JSFUtils.redirectTo404("Location With no = " + currentLocationId);
            }
        }
    }
    

    public List<OrganizationUnit> completeOrgUnit(String text) {

        businessGroupList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return businessGroupList;
    }

    public List<Country> completeCountry(String text) {
        countryList = countryService.findCountry(text);
        return countryList;
    }

    public List<String> completeCity(String text) {
        
        if(currentLocation.getCountry()==null) return null;
        
       return cityService.findCityName(currentLocation.getCountry().getCountryCode(), text);
        
       
    }
    
    public void resetForm(){
        currentLocation.setTownOrCityId(null);
    }

    public String save() {

        locationsService.update(currentLocation);

        return "locationsView?faces-redirect=true";
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;
       
        if (currentLocationId != null) {
        
            if (input.getValue().equals(value)) {
        
                return;
            }
        }
      
        if (locationsService.isCodeExists((String) value)) {
       
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

}
