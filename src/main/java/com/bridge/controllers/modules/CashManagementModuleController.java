/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.modules.CashManagementModuleSetup;
import com.bridge.entities.modules.ReceivableModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.modules.CashManagementModuleService;
import com.bridge.services.modules.ReciveablesConfigurationService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class CashManagementModuleController implements Serializable {

    @EJB
    private CashManagementModuleService configService;

    @EJB
    private COAAccountService accountService;

    @EJB
    private OrganizationUnitService organizationUnitService;

    private OrganizationUnit operatingUnit;

    private CashManagementModuleSetup currentModuleConfug;

    private Integer currentOperatingUnitId;

    private List<FndCurrency> cashCurrencies;
    private List<FndCurrency> miscellaneousExpenseCurrencies;

    @EJB
    private CurrencyService currencyService;

    public void loadModuleConfig() {
        if (currentOperatingUnitId != null) {
            operatingUnit = organizationUnitService.findById(currentOperatingUnitId);

            if (operatingUnit == null) {
                JSFUtils.redirectTo404("OperatingUnit");
            } else {
                currentModuleConfug = configService.findByOperating(operatingUnit);

                if (currentModuleConfug == null) {
                    currentModuleConfug = new CashManagementModuleSetup();

                    currentModuleConfug.setOperatingUnit(operatingUnit);
                }
            }
            loadCashCurrencies();
            loadMiscellaneousExpenseCurrency();
        } else {
            JSFUtils.redirectTo404("OperatingUnit");
        }
    }

    public List<GlCodeCombination> completeAccounts(String text) {

        return accountService.findByAlias(text);
    }

    public String save() {
        configService.update(currentModuleConfug);

        return "modulesview?faces-redirect=true";
    }

    public OrganizationUnit getOperatingUnit() {
        return operatingUnit;
    }

    public CashManagementModuleSetup getCurrentModuleConfug() {
        return currentModuleConfug;
    }

    public Integer getCurrentOperatingUnitId() {
        return currentOperatingUnitId;
    }

    public void setCurrentOperatingUnitId(Integer currentOperatingUnitId) {
        this.currentOperatingUnitId = currentOperatingUnitId;
    }

    public List<FndCurrency> getCashCurrencies() {
        return cashCurrencies;
    }

    public void setCashCurrencies(List<FndCurrency> cashCurrencies) {
        this.cashCurrencies = cashCurrencies;
    }

    public void loadCashCurrencies() {
        cashCurrencies = currencyService.findValidCurrencies(currentModuleConfug.getOperatingUnit().getGlId());
    }

    public void loadMiscellaneousExpenseCurrency() {
        miscellaneousExpenseCurrencies = currencyService.findValidCurrencies(currentModuleConfug.getOperatingUnit().getGlId());
    }

    public List<FndCurrency> getMiscellaneousExpenseCurrencies() {
        return miscellaneousExpenseCurrencies;
    }

    public void setMiscellaneousExpenseCurrencies(List<FndCurrency> miscellaneousExpenseCurrencies) {
        this.miscellaneousExpenseCurrencies = miscellaneousExpenseCurrencies;
    }
    
    
}
