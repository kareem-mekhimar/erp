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
public class AdjustComponentController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private PhysicalLocationService physicalLocationService;
    @EJB
    private GlLedgerService ledgerService;

    private FaAssets currentComponent;

    private PhysicalLocation newLocation;

    private FaAssets newAsset;

    private FaAssets oldAsset;

    private Date eventDate;

    private BigDecimal addedCost;

    private BigDecimal addedLife;

    public FaAssets getCurrentComponent() {

        return currentComponent;
    }

    public void setCurrentComponent(FaAssets currentComponent) {
        this.currentComponent = currentComponent;
    }

    public void resetForm() {
        
        newLocation=null;
        newAsset = null;
    }
    public void resetParent() {
        newAsset = null;
    }

    public List<FaAssets> completeComponent(String text) {

        return assetService.findComponentByName(text);

    }

    public List<PhysicalLocation> completeLocation(String text) {
        
        if (currentComponent == null) {
            return null;
        }

        return physicalLocationService.findByOrganizationAndDesc(currentComponent.getOperatingUnit().getOrgUnitId(), text);

    }

    public List<FaAssets> completeAsset(String text) {
        if (newLocation == null ) {
            return null;
        }
        return assetService.findAssetByNameAndLocation(newLocation, text)
                .stream().filter(l-> ! l.equals(currentComponent.getMainAsset())).collect(Collectors.toList());

    }

    public PhysicalLocation getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(PhysicalLocation newLocation) {
        this.newLocation = newLocation;
    }

    public FaAssets getNewAsset() {
        return newAsset;
    }

    public void setNewAsset(FaAssets newAsset) {
        this.newAsset = newAsset;
    }

    public FaAssets getOldAsset() {
        return oldAsset;
    }

    public void setOldAsset(FaAssets oldAsset) {
        this.oldAsset = oldAsset;
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
        
        UIInput newAssetInput=(UIInput)component.findComponent("newAsset");
        
        FaAssets asset =(FaAssets)newAssetInput.getValue();
        

        Date adjustDate = (Date) value;

        if (adjustDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not change after today"));
        }

        if (currentComponent == null) {
            return;
        }

        if (adjustDate.compareTo(currentComponent.getReceivingDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before recieving date"));
        }

        if (!ledgerService.isInOpenAssetPeriod(currentComponent.getOperatingUnit().getGlId(), adjustDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }

        
        if (adjustDate.compareTo(currentComponent.getMainAsset().getLastEvaluationDate()) < 0) {

                throw new ValidatorException(new FacesMessage("this date before evaluation date"));
        } 
        
        if (adjustDate.compareTo(asset.getLastEvaluationDate()) < 0) {
                throw new ValidatorException(new FacesMessage("this date before evaluation date"));
        } 

    }

    public String save() {

//        assetService.saveAdjustComponent(currentComponent, newAsset, eventDate, addedCost, addedLife);

        return "assetView?faces-redirect=true";
    }

}
