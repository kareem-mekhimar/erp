/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.FaAdditionType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.assets.AssetService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class InitAssetController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private OrganizationUnitService orgUnitService;
    @EJB
    private CurrencyService currencyService;

    private Integer adId;

    private FaAdditions currentAddition;

    private List<FndCurrency> currencys;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public FaAdditions getCurrentAddition() {
        if (currentAddition == null) {
            currentAddition = new FaAdditions();
        }
        return currentAddition;
    }

    public void setCurrentAddition(FaAdditions currentAddition) {

        this.currentAddition = currentAddition;
    }

    public List<OrganizationUnit> completeOperatingUnit(String text) {

        return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

    }

    public void loadAddition() {
        if (adId != null) {
            currentAddition = assetService.findAdditionById(adId);
        }

    }

    public String save() {

        assetService.saveInitAsset(currentAddition);

        return "assetReceipts?faces-redirect=true";
    }

    public List<FndCurrency> getCurrencys() {
        return currencys;
    }

    public void loadCurrencies() {

        if (currentAddition.getOperatingUnit() == null) {
            return;
        }

        currencys = currencyService.findValidCurrencies(currentAddition.getOperatingUnit().getGlId());
    }

}
