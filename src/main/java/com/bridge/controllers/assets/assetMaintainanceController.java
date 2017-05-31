/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.services.assets.AssetService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.ledger.GlLedgerService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
public class assetMaintainanceController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private GlLedgerService ledgerService;
    @EJB
    private SystemItemService systemItemService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private SubInvService subInvService;

    private MainInventory mainInventory;

    private SecondaryInventory subInventory;

    private FaAssets currentAsset;

    private SystemItem sparepart;

    private List<SystemItem> sparepartList;

    private BigDecimal maintainanceCost;

    private Date maintainanceDate;

    public FaAssets getCurrentAsset() {
        return currentAsset;
    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public BigDecimal getMaintainanceCost() {
        return maintainanceCost;
    }

    public void setMaintainanceCost(BigDecimal maintainanceCost) {
        this.maintainanceCost = maintainanceCost;
    }

    public Date getMaintainanceDate() {
        return maintainanceDate;
    }

    public void setMaintainanceDate(Date maintainanceDate) {
        this.maintainanceDate = maintainanceDate;
    }

    public List<FaAssets> completeAsset(String text) {

        return assetService.findAssetByName(text);

    }

    public SystemItem getSparepart() {
        return sparepart;
    }

    public void setSparepart(SystemItem sparepart) {
        this.sparepart = sparepart;
    }

    public MainInventory getMainInventory() {
        return mainInventory;
    }

    public void setMainInventory(MainInventory mainInventory) {
        this.mainInventory = mainInventory;
    }

    public SecondaryInventory getSubInventory() {
        return subInventory;
    }

    public void setSubInventory(SecondaryInventory subInventory) {
        this.subInventory = subInventory;
    }

    public void resetSubInv() {

        subInventory = null;

        sparepart = null;
    }

    public List<MainInventory> completeMainInv(String text) {
        return mainInvService.findByName(text);
    }

    public List<SecondaryInventory> completeSubInv(String text) {
        if (mainInventory == null) {
            return null;
        }

        return subInvService.findByNameForMain(text, mainInventory);
    }

    public List<SystemItem> completeSparePart(String text) {

        if (mainInventory == null || subInventory == null) {
            return null;
        }

        return systemItemService.findSparePartInInventoriesByNameAndMainIdAndSubId(mainInventory.getOrganizationId(),
                subInventory.getSecondaryInventoryId(), text);
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date addDate = (Date) value;

        if (addDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), addDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }
        if (addDate.compareTo(currentAsset.getLastEvaluationDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before evaluation date"));
        }

    }

    public String save() {

        assetService.saveMaintainance(currentAsset, sparepart, maintainanceDate, maintainanceCost);

        return "assetView?faces-redirect=true";
    }

}
