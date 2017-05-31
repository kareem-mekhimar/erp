/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.hr.Country;
import com.bridge.entities.hr.Location;
import com.bridge.entities.hr.City;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.UomLine;
import com.bridge.enums.AvailabilityType;
import com.bridge.enums.InventoryType;
import com.bridge.enums.LocatorControlType;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.LocationsService;
import com.bridge.services.hr.CityService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class SubInvDetailsController implements Serializable {

    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;
    @EJB
    private SubInvService subInvService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private UomLineService uomLineService;
    @EJB
    private COAAccountService accountService;
    @EJB
    private LocationsService locationService;
    
    
    private SecondaryInventory currentSubInventory;
    private List<Location> locationList;
    private Integer currentInventoryId;
    private List<MainInventory> mainInventoryList;
    private List<UomLine> uomList;
    private List<GlCodeCombination> AccountList;
    private LocatorControlType[] locatorTypes;
    private AvailabilityType[] availabilityTypes;
    private List<GlCodeCombination> accounts;
    private List<Location> locations;
    private List<UomLine> uoms;
    private boolean notShip;
    @PostConstruct
    private void init() {
        accounts = accountService.findAll();
        locations = locationService.findAll();
        uoms = uomLineService.findAll();
    }

    private List<Country> countryList;

    private List<City> cityList;

    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public SecondaryInventory getCurrentSubInventory() {
        if (currentSubInventory == null) {
            currentSubInventory = new SecondaryInventory();
            currentSubInventory.setStatusId(true);
        }
        return currentSubInventory;
    }

    public void setCurrentSubInventory(SecondaryInventory currentSubInventory) {
        this.currentSubInventory = currentSubInventory;
    }

    public List<UomLine> getUoms() {
        return uoms;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setSubInventoryList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<GlCodeCombination> getAccounts() {
        return accounts;
    }

    public Integer getCurrentInventoryId() {
        return currentInventoryId;
    }

    public void setCurrentInventoryId(Integer currentInventoryId) {
        this.currentInventoryId = currentInventoryId;
    }

    public List<MainInventory> getMainInventoryList() {
        return mainInventoryList;
    }

    public void setMainInventoryList(List<MainInventory> mainInventoryList) {
        this.mainInventoryList = mainInventoryList;
    }

    public List<UomLine> getVolumeUnitList() {
        return uomList;
    }

    public void setVolumeUnitList(List<UomLine> volumeUnitList) {
        this.uomList = volumeUnitList;
    }

    public List<GlCodeCombination> getAccountList() {
        return AccountList;
    }

    public LocatorControlType[] getLocatorTypes() {
        return LocatorControlType.values();
    }

    public AvailabilityType[] getAvailabilityTypes() {
        return AvailabilityType.values();
    }

    public boolean getNotShip() {
        return notShip;
    }

    public void setNotShip(boolean notShip) {
        this.notShip = notShip;
    }



    public void setAccountList(List<GlCodeCombination> AccountList) {
        this.AccountList = AccountList;
    }

    public List<Location> completeLocation(String text) {

        locationList = locationService.findLocationByCode(text);
        return locationList;
    }

    public List<UomLine> completeUom(String text) {

        uomList = uomLineService.findUomByName(text);

        return uomList;
    }

    public List<GlCodeCombination> completeAccounts(String text) {
        return accountService.findByAlias(text);

    }

    public List<MainInventory> completeMainInv(String text) {
        return mainInvService.findByName(text);

    }

    public List<Location> getLocations() {
        return locations;
    }

    
    public void loadInventory() {
        
        if (currentInventoryId != null) {
            
            currentSubInventory = subInvService.findById(currentInventoryId);
            
            if(currentSubInventory == null){
                
                JSFUtils.redirectTo404("Sub Inventory");
                
            }
            
            currentSubInventory.setOldShipping(currentSubInventory.getShippingFlag());
            
            if(currentSubInventory.getShippingFlag()==false && currentSubInventory.getMainInv().getShippingSubInventory()!=null){
                notShip = true ;
 
            }
           
        }
    }

    public String save() {

        subInvService.updateInv(currentSubInventory);
        return "subInventoryView?faces-redirect=true";

    }

    public List<Country> completeCountry(String text) {
        System.out.println("county is: " + text);
        countryList = countryService.findCountry(text);
        return countryList;
    }

    public List<String> completeCity(String text) {
        
        if (currentSubInventory.getCountry() == null) {
            return null;
        }

        return cityService.findCityName(currentSubInventory.getCountry().getCountryCode(), text);

    }

    public void resetForm() {
        currentSubInventory.setTownOrCityId(null);
    }

    public void resetInvType() {
        
        notShip=false;
        currentSubInventory.setShippingFlag(false);
        if(currentSubInventory.getMainInv().getShippingSubInventory()!=null){
          notShip=true;  
        }
    }

}
