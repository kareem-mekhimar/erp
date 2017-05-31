/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.services.assets.AssetService;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.physicalStructure.PhysicalLocationService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
public class AdjustAssetController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private PhysicalLocationService physicalLocationService;
    @EJB
    private FaCategoryService categoryService;
    @EJB
    private GlLedgerService ledgerService;

    private FaAssets currentAsset;

    private PhysicalLocation newLocation;

    private PhysicalLocation oldLocation;

    private FaCategory newCategory;

    private FaCategory oldCategory;

    private Date eventDate;

    private BigDecimal addedCost;

    private BigDecimal addedLife;

    public FaAssets getCurrentAsset() {

        return currentAsset;
    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public void resetForm() {

        newLocation = null;
    }

    public void resetCategory() {
        newCategory = null;
    }

    public List<FaAssets> completeAsset(String text) {

        return assetService.findAssetByName(text);

    }

    public List<PhysicalLocation> completeLocation(String text) {

        if (currentAsset == null) {
            return null;
        }

        return physicalLocationService.findByOrganizationAndDesc(currentAsset.getOperatingUnit().getOrgUnitId(), text)
                .stream().filter(l -> !l.equals(currentAsset.getAssetLocation())).collect(Collectors.toList());

    }

    public List<FaCategory> completeCategory(String text) {
        if (currentAsset == null) {
            return null;
        }

        return categoryService.findCategoryByOrgAndName(currentAsset.getOperatingUnit(), text)
                .stream().filter(l -> !l.equals(currentAsset.getAssetCategory())).collect(Collectors.toList());

    }

    public PhysicalLocation getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(PhysicalLocation newLocation) {
        this.newLocation = newLocation;
    }

    public FaCategory getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(FaCategory newCategory) {
        this.newCategory = newCategory;
    }

    public FaCategory getOldCategory() {
        return oldCategory;
    }

    public void setOldCategory(FaCategory oldCategory) {
        this.oldCategory = oldCategory;
    }

    public PhysicalLocation getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(PhysicalLocation oldLocation) {
        this.oldLocation = oldLocation;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getAddedCost() {
        return addedCost;
    }

    public void setAddedCost(BigDecimal addedCost) {
        this.addedCost = addedCost;
    }

    public BigDecimal getAddedLife() {
        return addedLife;
    }

    public void setAddedLife(BigDecimal addedLife) {
        this.addedLife = addedLife;
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date adjustDate = (Date) value;

        if (adjustDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not change after today"));
        }

        if (currentAsset == null) {
            return;
        }

        if (adjustDate.compareTo(currentAsset.getReceivingDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before recieving date"));
        }

        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), adjustDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }

        if (adjustDate.compareTo(currentAsset.getLastEvaluationDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before evaluation date"));
        }

    }

    public String save() {

        oldCategory = currentAsset.getAssetCategory();
        oldLocation = currentAsset.getAssetLocation();
        currentAsset.setAssetLocation(newLocation);
        currentAsset.setAssetCategory(newCategory);

        assetService.saveAdjustAssets(currentAsset, oldCategory, oldLocation, eventDate, addedCost, addedLife);

        return "assetView?faces-redirect=true";
    }

}
