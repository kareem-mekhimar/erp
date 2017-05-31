/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.physicalStructure.PhysicalLevel;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.services.physicalStructure.PhysicalLevelService;
import com.bridge.services.physicalStructure.PhysicalLocationService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author AIA
 */
@Model
public class PhysicalLocationConverter implements Converter {

    @EJB
    private PhysicalLocationService locationService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }

        return locationService.findById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        PhysicalLocation location = (PhysicalLocation) value;

        return location.getLocationId() + "";
    }
}
