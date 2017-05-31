/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.bank;

import com.bridge.entities.bank.Bank;
import com.bridge.entities.bank.BankAccount;
import com.bridge.entities.bank.BankBranch;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.bank.AccountService;
import com.bridge.services.bank.BankService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
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
public class BankAccountsController implements Serializable{
    
    @EJB
    private AccountService accountService;
        
    @EJB
    private OrganizationUnitService orgUnitService;
    
    @EJB
    private BankService bankService;
    
    private List<BankAccount> accountList;
    
    private Integer currentBankId;

    private Bank currentBank ;
    
    public void loadAccounts()
    {
        if(currentBankId != null)
        {
           currentBank = bankService.findById(currentBankId) ;
           
           if(currentBank != null)
           {
              accountList = accountService.findByBank(currentBank) ;
           }
           else
             
               JSFUtils.redirectTo404("Bank");
        }
        else
            
            JSFUtils.redirectTo404("Bank");
    }
    

    public List<BankAccount> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<BankAccount> accountList) {
        this.accountList = accountList;
    }

    public Integer getCurrentBankId() {
        return currentBankId;
    }

    public void setCurrentBankId(Integer currentBankId) {
        this.currentBankId = currentBankId;
    }
    
    
    
}
