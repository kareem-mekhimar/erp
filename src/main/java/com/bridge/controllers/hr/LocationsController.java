/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Location;
import com.bridge.services.hr.LocationsService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named
@ViewScoped
public class LocationsController implements Serializable{
    private Integer status;
    private Integer locationId;
    private String locationCode;
    private List<Location> locationsList;
    
    @EJB
    private LocationsService locationsService;
    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public List<Location> getLocationsList() {
        return locationsList;
    }

 
    public void filter(){
        locationsList  = locationsService.findLocation(locationId, locationCode);        
    }
    
}
