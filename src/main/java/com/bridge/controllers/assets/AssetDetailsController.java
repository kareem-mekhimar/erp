/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.enums.AssetStatus;
import com.bridge.enums.AssetType;
import com.bridge.services.assets.AssetService;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.physicalStructure.PhysicalLocationService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class AssetDetailsController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private PhysicalLocationService physicalLocationService;
    @EJB
    private FaCategoryService categoryService;
    @EJB
    private GlLedgerService ledgerService;

    private Integer assetId;

    private Integer additionId;

    private FaAdditions currentAddition;

    private FaAssets currentAsset;

    private AssetType[] assetTypes;

    private boolean newState;
    private boolean typeState;
    private boolean catState;
    private boolean parentState;
    private boolean preservState;

    public boolean isPreservState() {
        return preservState;
    }

    public void setPreservState(boolean preservState) {
        this.preservState = preservState;
    }

    public boolean isNewState() {
        return newState;
    }

    public void setNewState(boolean newState) {
        this.newState = newState;
    }

    public boolean isTypeState() {
        return typeState;
    }

    public void setTypeState(boolean typeState) {
        this.typeState = typeState;
    }

    public boolean isCatState() {
        return catState;
    }

    public void setCatState(boolean catState) {
        this.catState = catState;
    }

    public boolean isParentState() {
        return parentState;
    }

    public void setParentState(boolean parentState) {
        this.parentState = parentState;
    }

    public void setState() {

        if (!currentAsset.getSubAsset()) {

            if (currentAsset.getAssetId() == null) {
                if (currentAsset.getAssetType() == AssetType.CAPITALIZED) {
                    newState = true;
                    typeState = true;
                    catState = true;
                    parentState = false;
                    preservState = true;
                } else {
                    newState = true;
                    typeState = true;
                    catState = true;
                    parentState = false;
                    preservState = false;
                }

            } else {

                if (currentAsset.getStatus() == AssetStatus.PRESERVICE) {

                    if (currentAsset.getAssetType() == AssetType.CAPITALIZED) {
                        newState = false;
                        typeState = true;
                        catState = false;
                        parentState = false;
                        preservState = true;
                    } else {
                        newState = false;
                        typeState = true;
                        catState = false;
                        parentState = false;
                        preservState = false;
                    }

                } else {
                    newState = false;
                    typeState = false;
                    catState = false;
                    parentState = false;
                    preservState = false;
                }

            }
        } else {
            if (currentAsset.getAssetId() == null) {
                newState = true;
                typeState = false;
                catState = false;
                parentState = true;
                preservState = false;
            } else {
                newState = false;
                typeState = false;
                catState = false;
                parentState = false;
                preservState = false;
            }
        }

    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getAdditionId() {
        return additionId;
    }

    public void setAdditionId(Integer additionId) {
        this.additionId = additionId;
    }

    public FaAdditions getCurrentAddition() {
        return currentAddition;
    }

    public void setCurrentAddition(FaAdditions currentAddition) {
        this.currentAddition = currentAddition;
    }

    public FaAssets getCurrentAsset() {
        if (currentAsset == null) {
            currentAsset = new FaAssets();
            currentAsset.setOperatingUnit(currentAddition.getOperatingUnit());
            currentAsset.setReceivingDate(currentAddition.getReceivingDate());
     
        }
        return currentAsset;
    }

    public void resetForm() {

        currentAsset.setAssetCategory(null);
        currentAsset.setOnserviceDate(null);
        currentAsset.setMainAsset(null);
        setState();
    }

    public void resetOnService() {
        currentAsset.setOnserviceDate(null);
        setState();
    }

    public void resetParent() {

        currentAsset.setMainAsset(null);

    }

    public void resetDate() {

        currentAsset.setAdditionDate(new Date());

    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public AssetType[] getAssetTypes() {
        return AssetType.values();
    }

    public List<PhysicalLocation> completeLocation(String text) {
        if (currentAsset.getOperatingUnit() == null) {
            return null;
        }
        return physicalLocationService.findByOrganizationAndDesc(currentAsset.getOperatingUnit().getOrgUnitId(), text);

    }

    public List<FaAssets> completeParent(String text) {
        if (currentAsset.getAssetLocation() == null) {
            return null;
        }
        return assetService.findAssetByNameAndLocation(currentAsset.getAssetLocation(), text);

    }

    public List<FaCategory> completeCategory(String text) {
        if (currentAsset.getOperatingUnit() == null) {
            return null;
        }

        return categoryService.findCategoryByOrgAndName(currentAsset.getOperatingUnit(), text);

    }

    public void loadAsset() {

        if (assetId != null) {
            currentAsset = assetService.findById(assetId);
        }

        if (additionId != null) {
            currentAddition = assetService.findAdditionById(additionId);
        }

        if (currentAsset == null && currentAddition == null) {
            JSFUtils.redirectTo404("add asset");
        }
        if (currentAddition != null) {
            if (currentAddition.getAvailableQuantity().compareTo(BigDecimal.ZERO) == 0) {
                JSFUtils.redirectTo404("add asset");
            }
        }
        if (currentAsset != null) {
            setState();
        }else{
            newState = true;
            typeState = true;
            catState = true;
            parentState = false;
            preservState = false;
        }

    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date addDate = (Date) value;

        if (addDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (addDate.compareTo(currentAsset.getReceivingDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before recieving date"));
        }
        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), addDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }
        if (currentAsset.getSubAsset()) {
            if (addDate.compareTo(currentAsset.getMainAsset().getLastEvaluationDate()) < 0) {
                throw new ValidatorException(new FacesMessage("this date before asset evaluation date"));
            }
        }

    }

    public void validateServiceDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        UIInput addDate = (UIInput) component.findComponent("addDate");

        Date date = (Date) addDate.getValue();

        Date serviceDate = (Date) value;

        if (serviceDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (serviceDate.compareTo(currentAsset.getReceivingDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before recieving date"));
        }
        if (serviceDate.compareTo(date) < 0) {
            throw new ValidatorException(new FacesMessage("this date before add Date"));
        }
        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), serviceDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));

        }

    }

    public void validateCode(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;
        if (currentAsset.getAssetId() != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (assetService.codeExists(value.toString())) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentAsset.getAssetId() != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (assetService.nameExists(value.toString())) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public String save() {

        assetService.saveAssets(currentAsset, currentAddition);

        return "assetView?faces-redirect=true";
    }

}
