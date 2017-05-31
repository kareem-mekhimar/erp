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
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.bank.AccountService;
import com.bridge.services.bank.BankService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
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
public class BankAccountDetailsController implements Serializable{
    
    @EJB
    private AccountService accountService ;
    
    @EJB
    private BankService bankService ;
    
    @EJB
    private COAAccountService coaAccountService ;
    
    @EJB
    private OrganizationUnitService orgUnitService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    private List<FndCurrency> currencies ;
    
    private List<BankBranch> branches ;
    
    private BankAccount currentAccount ;
    
    private Bank currentBank ;
    
    private Integer currentBankId ;
    
    private Integer currentBankAccountId ;
    
    public void loadAccount()
    {
       if(currentBankId != null)
       {
           currentBank = bankService.findBankWithBranches(currentBankId) ;
           
           if(currentBank != null)
           {
               if(currentBankAccountId != null)
               {
                   currentAccount = accountService.findById(currentBankAccountId) ;
                   
                   if(currentAccount == null)
                       
                       JSFUtils.redirectTo404("Bank Account") ;
               }
           }
           else
               
               JSFUtils.redirectTo404("Bank");
               
       }
       else
           
           JSFUtils.redirectTo404("Bank");
    }

       
    public List<OrganizationUnit> completeOperatingUnit(String text) {

        return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

    }
    
    public List<GlCodeCombination> completeAccounts(String text) {

        OrganizationUnit operatingUnit = currentAccount.getOrgUnitId() ;
        
        if(operatingUnit == null)
            
               return null;

        return coaAccountService.findByLedgerAndAlias(operatingUnit.getGlId(), text); 
    }
    
        
    public BankAccount getCurrentAccount() {
    
        if(currentAccount == null)
        {
            currentAccount = new BankAccount() ;
        
            currentAccount.setBank(currentBank);
        }
        
        return currentAccount;
    }

    public String save()
    {
       if(currentAccount.getBankAccountId() == null)
       {
           currentAccount.setCurrentAmount(currentAccount.getInitialAmount());  
       }
       
       accountService.update(currentAccount) ;
       
       return "bankAccounts?faces-redirect=true&id="+currentBank.getBankId() ;
    }
    
    public Integer getCurrentBankAccountId() {
        return currentBankAccountId;
    }

    public void setCurrentBankAccountId(Integer currentBankAccountId) {
        this.currentBankAccountId = currentBankAccountId;
    }

    public void setCurrentBankId(Integer currentBankId) {
        this.currentBankId = currentBankId;
    }

    public Integer getCurrentBankId() {
        return currentBankId;
    }

    public Bank getCurrentBank() {
        return currentBank;
    }

    public List<FndCurrency> getCurrencies() {
    
        if(currencies == null)
            
            currencies = currencyService.findAll() ;
        
        return currencies;
    }
    
    
    
}
