/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.modules.CashManagementModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.AbstractService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class CashManagementModuleService extends AbstractService<CashManagementModuleSetup>{
 
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private JournalBatchService journalBatchService ;
    
    @EJB
    private CurrencyService currencyService ;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    public CashManagementModuleService() {
        super(CashManagementModuleSetup.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public CashManagementModuleSetup findByOperating(OrganizationUnit organizationUnit)
    {
        try {
        
            return entityManager.createQuery("SELECT g FROM CashManagementModuleSetup g WHERE g.operatingUnit = :o",CashManagementModuleSetup.class)
                .setParameter("o", organizationUnit)
                .getSingleResult() ;           
        } catch (Exception e) {
           
        }

        return null ;
    }   
 
    
    public GlCodeCombination findCashAccountByOperating(OrganizationUnit operating)
    {
        try {
            
           return entityManager.createQuery("SELECT g.cashAcc FROM CashManagementModuleSetup g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;
    }
    
    public List<CashManagementModuleSetup> findByOperatingUnitName(String text)
    {
       return entityManager.createQuery("SELECT s FROM CashManagementModuleSetup s "
               + " WHERE UPPER(s.operatingUnit.orgUnitName) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .getResultList() ;
    }
    
    public void registerExpenses(CashManagementModuleSetup cashManagementModuleSetup,String description,Date date,BigDecimal neededAmount)
    {
        cashManagementModuleSetup.setMiscellaneousExpenseAmount(cashManagementModuleSetup.getMiscellaneousExpenseAmount().subtract(neededAmount)) ;
        
        update(cashManagementModuleSetup) ;
        
        FndCurrency from = cashManagementModuleSetup.getMiscellaneousExpenseCurrency() ;
        
        FndCurrency to =  ledgerService.findCurrencyById(cashManagementModuleSetup.getOperatingUnit().getGlId()) ;
        
        if(! from.equals(to))
        {
            neededAmount = currencyService.convert(neededAmount, from, to) ;
        }     
        
        GlCodeCombination debit = cashManagementModuleSetup.getVariousExpensesAcc() ;
        
        GlCodeCombination credit = cashManagementModuleSetup.getMiscellaneousExpenseAcc() ;
        
        journalBatchService.createJournalsForDate(cashManagementModuleSetup.getOperatingUnit().getGlId(), description, date, neededAmount, debit, credit) ;
    }
    
    
    public void resetExpense(CashManagementModuleSetup cashManagementModuleSetup,String description,Date date)
    {
        BigDecimal limit = cashManagementModuleSetup.getMiscellaneousExpenseLimit() ;
        
        BigDecimal current = cashManagementModuleSetup.getMiscellaneousExpenseAmount() ;
        
        BigDecimal change = limit.subtract(current) ;
        
        cashManagementModuleSetup.setMiscellaneousExpenseAmount(limit);
        
        update(cashManagementModuleSetup) ;
        
        FndCurrency from = cashManagementModuleSetup.getMiscellaneousExpenseCurrency() ;
        
        FndCurrency to =  ledgerService.findCurrencyById(cashManagementModuleSetup.getOperatingUnit().getGlId()) ;
        
        if(! from.equals(to))
        {
            change = currencyService.convert(change, from, to) ;
        }
        
        GlCodeCombination credit = cashManagementModuleSetup.getCashAcc() ;
        
        GlCodeCombination debit = cashManagementModuleSetup.getMiscellaneousExpenseAcc() ;
       
        journalBatchService.createJournalsForDate(cashManagementModuleSetup.getOperatingUnit().getGlId(), 
                description, date, change, debit, credit) ;
    }
}
