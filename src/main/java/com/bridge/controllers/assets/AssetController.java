/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.assets.FaAssets;
import com.bridge.enums.AssetReleaseType;
import com.bridge.enums.AssetStatus;
import com.bridge.services.assets.AssetService;
import com.bridge.services.ledger.GlLedgerService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class AssetController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private GlLedgerService ledgerService;

    private Integer additionId;

    private FaAdditions currentAddition;

    private List<FaAssets> assetList;

    private BigDecimal cost;

    private Date transDate;
    
    private String reason;

    private String code;

    private String name;

    private FaAssets currentAsset;

    private AssetStatus status;

    private AssetStatus[] statusList;

    private AssetReleaseType[] releaseType;

    public FaAssets getCurrentAsset() {
        return currentAsset;
    }

    public void setCurrentAsset(FaAssets currentAsset) {
        this.currentAsset = currentAsset;
    }

    public AssetReleaseType[] getReleaseType() {
        return AssetReleaseType.values();
    }

    public AssetStatus[] getStatusList() {
        return AssetStatus.values();
    }

    public void setStatusList(AssetStatus[] statusList) {
        this.statusList = statusList;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
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

    public List<FaAssets> getAssetList() {
        if (assetList == null) {
            assetList = new ArrayList<>();
        }
        return assetList;
    }

    public void setAssetList(List<FaAssets> assetList) {
        this.assetList = assetList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    

    public String gotoAddAsset() {
        return "addAsset?faces-redirect=true&adId" + additionId;
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return;
        }

        Date date = (Date) value;

        if (date.compareTo(new Date()) > 0) {
            throw new ValidatorException(new FacesMessage("can not add after today"));
        }
        if (date.compareTo(currentAsset.getLastEvaluationDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before Evaluation date"));
        }

        if (currentAsset.getStatus() == AssetStatus.RETIRED && date.compareTo(currentAsset.getRetirmentDate()) < 0) {
            throw new ValidatorException(new FacesMessage("this date before retirment date"));
        }

        if (!ledgerService.isInOpenAssetPeriod(currentAsset.getOperatingUnit().getGlId(), date)) {
            throw new ValidatorException(new FacesMessage("this date not in open period"));
        }

    }

    public void changeServiceAsset() {

        assetService.changeServiceAsset(currentAsset, transDate, cost,reason);
        
        cost = null;
        transDate = null;
        currentAsset = null;
    }

    public void filter() {
        // abdalrahman: 17/5/2017:
        assetList = assetService.findAssets(code, name, status);
        // old code:    
        // assetList = assetService.findAssets(code, name, location);
    }

}
