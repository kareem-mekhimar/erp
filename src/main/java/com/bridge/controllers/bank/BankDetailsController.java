/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.bank;

import com.bridge.entities.bank.Bank;
import com.bridge.entities.bank.BankBranch;
import com.bridge.entities.hr.Country;
import com.bridge.services.bank.BankService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.CityService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class BankDetailsController implements Serializable {

    @EJB
    private BankService bankService;
    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;

    private Integer currentBankId;
    private Bank currentBank;
    private BankBranch curreBranch;
    private BankBranch oldBranch = new BankBranch();
    private List<String> removedIds = new ArrayList();

    public Integer getCurrentBankId() {
        return currentBankId;
    }

    public void setCurrentBankId(Integer currentBankId) {
        this.currentBankId = currentBankId;
    }

    public BankBranch getCurreBranch() {

        return curreBranch;
    }

    public void setCurreBranch(BankBranch curreBranch) {
        this.curreBranch = curreBranch;
    }

    public Bank getCurrentBank() {
        if (currentBank == null) {
            currentBank = new Bank();
            currentBank.setBranchList(new ArrayList());
        }
        return currentBank;
    }

    public void setCurrentBank(Bank currentBank) {
        this.currentBank = currentBank;
    }

    public List<Country> completeCountry(String text) {
        return countryService.findCountry(text);

    }

    public List<String> completeState(String text) {

        return cityService.findCityName(curreBranch.getCountry().getCountryCode(), text);

    }

    public void loadBank() {
        if (currentBankId != null) {
            currentBank = bankService.findBankWithBranches(currentBankId);
        }

    }

    public void loadDialog(BankBranch branch) {

        curreBranch = branch;
        if (curreBranch == null) {
            curreBranch = new BankBranch();
        }
    }

    public void onDialogOk() {

        if (curreBranch.getBankId() == null) {
            curreBranch.setBankId(currentBank);
            currentBank.getBranchList().add(curreBranch);
        }
    }

    public void removeLine(BankBranch branch) {

        currentBank.getBranchList().remove(branch);

        if (branch.getBankBranchId() != null) {
            removedIds.add(branch.getBankBranchId().toString());
        }

    }

    public String save() {

        bankService.update(currentBank);
        if (!removedIds.isEmpty()) {

            String linesToBeRemoved = removedIds.stream().collect(Collectors.joining(","));

            bankService.removeBankBranches(linesToBeRemoved);
        }

        return "bankView?faces-redirect=true";
    }

}
