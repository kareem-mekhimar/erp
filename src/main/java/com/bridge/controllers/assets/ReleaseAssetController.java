/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.enums.AssetReleaseType;
import com.bridge.enums.AssetStatus;
import com.bridge.enums.AssetTransactionType;
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
public class ReleaseAssetController implements Serializable {
     @EJB
    private AssetService assetService;
    @EJB
    private PhysicalLocationService physicalLocationService;
    @EJB
    private FaCategoryService categoryService;
    @EJB
    private GlLedgerService ledgerService;

    private FaAssets currentAsset;
    
    private BigDecimal releaseCost;
    
    private BigDecimal assetPrice;
    
    private AssetReleaseType type;
    
    private AssetReleaseType[] types;
    
    

    public BigDecimal getReleaseCost() {
        return releaseCost;
    }

    public void setReleaseCost(BigDecimal releaseCost) {
        this.releaseCost = releaseCost;
    }

    public BigDecimal getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(BigDecimal assetPrice) {
        this.assetPrice = assetPrice;
    }


    public FaAssets getCurrentAsset() {
        return currentAsset;
    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public AssetReleaseType getType() {
        return type;
    }

    public void setType(AssetReleaseType type) {
        this.type = type;
    }

    public AssetReleaseType[] getTypes() {
        return AssetReleaseType.values();
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

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }
        
        if(currentAsset == null){
            return;
        }

        Date date = (Date) value;

        if (date.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (date.compareTo(currentAsset.getLastEvaluationDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before Evaluation date"));
        }
        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), date)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }

    }

    public String save() {

        assetService.releasedAsset(currentAsset, type, releaseCost, assetPrice);

        return "assetView?faces-redirect=true";

    }

}
