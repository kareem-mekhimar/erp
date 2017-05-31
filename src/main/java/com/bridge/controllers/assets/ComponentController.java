/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAssets;
import com.bridge.services.assets.AssetService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
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
public class ComponentController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private GlLedgerService ledgerService;

    private FaAssets currentAsset;

    private List<FaAssets> componentList;

    private Integer assetId;

    public FaAssets getCurrentAsset() {
        return currentAsset;
    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public List<FaAssets> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<FaAssets> componentList) {
        this.componentList = componentList;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public void loadComponent() {
        if (assetId != null) {

            componentList = assetService.findComponentByAssetId(assetId);

        } else {

            JSFUtils.redirectTo404("Asset");
        }
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date orderDate = (Date) value;

        if (orderDate.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (orderDate.compareTo(currentAsset.getLastEvaluationDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before Evaluation date"));
        }
        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), orderDate)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }

    }

    public void changeServiceAsset() {

       

    }

}
