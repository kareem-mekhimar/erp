/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.cashmanagement;

import com.bridge.entities.bank.BankAccount;
import com.bridge.entities.cashmanagement.BankAccountTransaction;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.PaymentTxType;
import com.bridge.services.AbstractService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.modules.CashManagementModuleService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class BankTxService extends AbstractService<BankAccountTransaction>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private JournalBatchService batchService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    @EJB
    private CashManagementModuleService cashManagementModuleService ;
    
    @EJB
    private GlLedgerService ledgerService ;
            
    public BankTxService() {
        super(BankAccountTransaction.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }    
     
    public List<BankAccountTransaction> findPayBankByAccountId(Integer bankAccountId)
    {
        // 28/5/2017: modified
        return entityManager.createQuery("SELECT t FROM BankAccountTransaction t "
               + "WHERE t.bankAccountId = :id "
               + "AND t.transactionType = com.bridge.enums.PaymentTxType.PAY "
               + "AND t.cleared IS FALSE and t.checkStatus = 'CLEARED' ")
               .setParameter("id", bankAccountId)
               .getResultList() ;
    }
    
      
    public List<BankAccountTransaction> findRecieveByBankAccountId(Integer bankAccountId)
    {
       return entityManager.createQuery("SELECT t FROM BankAccountTransaction t "
               + "WHERE t.bankAccountId = :id "
               + "AND t.transactionType = com.bridge.enums.PaymentTxType.RECIEVE "
               + "AND t.cleared IS FALSE and t.checkStatus = 'CLEARED' ")
               .setParameter("id", bankAccountId)
               .getResultList() ;
    }   
    
    public void clear(List<BankAccountTransaction> transactions , Date date ,OrganizationUnit operating ,BankAccount account , PaymentTxType type)
    {
        GlCodeCombination debit = account.getCashClearing() ;
        
        GlCodeCombination credit = cashManagementModuleService.findCashAccountByOperating(operating) ;
        
        FndCurrency ledgerCurrency = ledgerService.findCurrencyById(operating.getGlId()) ;
        
        BigDecimal totalAmount = transactions.stream()
                .peek(t -> { 
                      
                    t.setCleared(true) ;
                    
                    entityManager.merge(t) ;
                 })
                .map((BankAccountTransaction tx) -> {
                
                    BigDecimal txAmount = tx.getTransactionAmount() ;
                    
                    FndCurrency from = tx.getCurrency() ;
                    
                    if(! ledgerCurrency.equals(from))
                    {
                        txAmount = currencyService.convert(txAmount, from, ledgerCurrency) ;
                    }
                    
                    return txAmount ;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add) ;
        
        String description = "Bank Tx Clearing" ;
       
        if(type == PaymentTxType.PAY)
            
            batchService.createJournalsForDate(operating.getGlId(), description,date , totalAmount, debit, credit) ;
        
        else
            
            batchService.createJournalsForDate(operating.getGlId(), description,date , totalAmount, credit, debit) ;

    }
}
