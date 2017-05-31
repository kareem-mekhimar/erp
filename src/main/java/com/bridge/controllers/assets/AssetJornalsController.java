/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.assets;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.enums.PeriodStatus;
import com.bridge.services.assets.AssetService;
import com.bridge.services.ledger.GlLedgerService;
import java.io.Serializable;
import java.util.Comparator;
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
public class AssetJornalsController implements Serializable {

    @EJB
    private AssetService assetService;
    @EJB
    private GlLedgerService ledgerService;

    private GlLedger currentLedger;

    private GlPeriodStatus currentOpenPeriod;
    
    private boolean toClose;

    public boolean isToClose() {
        return toClose;
    }

    public void setToClose(boolean toClose) {
        this.toClose = toClose;
    }
    
    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public void setCurrentLedger(GlLedger currentLedger) {
        this.currentLedger = currentLedger;
    }

    public GlPeriodStatus getCurrentOpenPeriod() {
        return currentOpenPeriod;
    }

    public void setCurrentOpenPeriod(GlPeriodStatus currentOpenPeriod) {
        this.currentOpenPeriod = currentOpenPeriod;
    }

    public List<GlLedger> completeLedger(String text) {
        return ledgerService.findByName(text);
    }

    public List<GlPeriodStatus> completePeriods(String text) {
        return ledgerService.findPeriodsByNameAndLedger(currentLedger, text);
    }

    public void setPeriod() {
        if (!ledgerService.findAssetPeriodsByLedger(currentLedger.getLedgerId()).isEmpty()) {
            currentOpenPeriod = ledgerService.findAssetPeriodsByLedger(currentLedger.getLedgerId())
                    .stream().sorted(Comparator.comparing(GlPeriodStatus::getStartDate)).findFirst().get();
        }
    }

    public void createJournals() {

        if (currentOpenPeriod != null) {
            assetService.createAssetJornals(currentLedger, currentOpenPeriod);
            if(toClose){
                currentOpenPeriod.setFaPeriodStatus(PeriodStatus.CLOSED);
                ledgerService.updatePeriod(currentOpenPeriod);
            }
        }
        
        toClose=false;

        currentLedger = null;

        currentOpenPeriod = null;
    }
}
