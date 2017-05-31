/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.bank.BankAccount;
import com.bridge.entities.cashmanagement.BankAccountTransaction;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.PaymentTxType;
import com.bridge.services.bank.AccountService;
import com.bridge.services.cashmanagement.BankTxService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class SupplierTxClearController implements Serializable{
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private BankTxService bankTxService ;
    
    @EJB
    private AccountService accountService ;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private OrganizationUnit currentOperatingUnit ;
    
    private BankAccount currentBankAccount ;
    
    private List<BankAccountTransaction> transactions ;

    private List<BankAccountTransaction> selectedTxs ;
    
    
    public List<OrganizationUnit> completeOperatingUnit(String text)
    {
       return organizationUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text) ;
    }
    
    
    public List<BankAccount> completeBankAccounts(String text)
    {
       if(currentOperatingUnit == null)
           
           return null ;
       
       return accountService.findByNameAndOperatingId(text, currentOperatingUnit.getOrgUnitId()) ;
    }
    
    public void onOperatingChange()
    {
       this.currentBankAccount = null ;  
    }
    
    public void filter()
    {
       transactions = bankTxService.findPayBankByAccountId(currentBankAccount.getBankAccountId()) ;
    }
    
    public void clearTransactions()
    {
       Date date = new Date() ;
       
       if(! ledgerService.isInOpenPeriod(currentOperatingUnit.getGlId(), date))
       {          
           RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Today isnot in an open period..');");
    
           return;
       }
       
       bankTxService.clear(transactions,date,currentOperatingUnit, currentBankAccount,PaymentTxType.PAY);
       
       notificationController.showNotification("Transactions Cleared...!", NotificationController.SUCCESS);
       
       filter();
    }
    
    public List<BankAccountTransaction> getTransactions() {
    
        return transactions;
    }

    public List<BankAccountTransaction> getSelectedTxs() {
        return selectedTxs;
    }

    public void setSelectedTxs(List<BankAccountTransaction> selectedTxs) {
        this.selectedTxs = selectedTxs;
    }

    public BankAccount getCurrentBankAccount() {
        return currentBankAccount;
    }

    public void setCurrentBankAccount(BankAccount currentBankAccount) {
        this.currentBankAccount = currentBankAccount;
    }

    public void setCurrentOperatingUnit(OrganizationUnit currentOperatingUnit) {
        this.currentOperatingUnit = currentOperatingUnit;
    }

    public OrganizationUnit getCurrentOperatingUnit() {
        return currentOperatingUnit;
    }
    
    
}
