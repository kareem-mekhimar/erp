/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.ledger;

import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlLedgerConfigDetail;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.enums.GLSetupSteps;
import com.bridge.enums.GlSetupStatus;
import com.bridge.services.AbstractService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class GlLedgerService extends AbstractService<GlLedger>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public GlLedgerService() {
    
        super(GlLedger.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }

    @Override
    public void create(GlLedger ledger) {
      
        entityManager.persist(ledger);
        
        ArrayList<GlLedgerConfigDetail> list = new ArrayList<>() ;
        
        for(GLSetupSteps step : GLSetupSteps.values())
        {
            GlLedgerConfigDetail detail = new GlLedgerConfigDetail(step ,ledger.getLedgerId() ,ledger.getName(),step.getVal(),step.getDesc() );
            
            list.add(detail) ;
            
            detail.setLedger(ledger);
            
            entityManager.persist(detail);
        }
       
        ledger.setConfigDetails(list);
               
    }
    
    
     public List<GlLedger> findByName(String text)
     {
         return entityManager.createQuery("SELECT g FROM GlLedger g WHERE UPPER(g.name) LIKE UPPER(:text)")
                 .setParameter("text", "%"+text+"%")
                 .getResultList();
     }
     
     
     public List<GlLedger> findByNameOrId(String text , Integer id , GlSetupStatus status)
     {
        StringBuilder builder = new StringBuilder("SELECT g FROM GlLedger g WHERE 1 = 1") ;
        
        if(text != null)
        {
           builder.append(" AND UPPER(g.name) LIKE UPPER(:text)") ;
        }
        
        if(id != null)
        {
           builder.append(" AND g.ledgerId = :id") ;
        }
        
        if(status != null)
        {
            builder.append(" AND g.completionStatusCode = :status") ;
        }
        
        TypedQuery<GlLedger> typedQuery = entityManager.createQuery(builder.toString(), GlLedger.class) ;
        
        Optional.ofNullable(text).ifPresent(t -> typedQuery.setParameter("text", "%"+text+"%"));
        
        Optional.ofNullable(id).ifPresent(t -> typedQuery.setParameter("id", id ));
        
        Optional.ofNullable(status).ifPresent(t -> typedQuery.setParameter("status", status));
        
        return typedQuery.getResultList() ;
    }
      
     
     public List<GlPeriodStatus> findOpenPeriodsByPeriodNameAndLedger(GlLedger ledger , String text)
     {
        return entityManager.createQuery("SELECT g FROM GlPeriodStatus g "
                + " WHERE g.periodStatus = com.bridge.enums.PeriodStatus.OPENED "
                + " AND g.periodSet.ledger.ledgerId = "+ledger.getLedgerId()
                + " AND UPPER(g.periodName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
     }
     
     
    public GlLedger findWithBasicConfigById(int id)
    {
       try{ 
          
           return entityManager.createQuery("SELECT g FROM GlLedger g LEFT JOIN FETCH g.configDetails "
               + " WHERE g.ledgerId = :id",GlLedger.class)
               .setParameter("id", id)
               .getSingleResult() ;
           
       }catch(Exception e)
       {
           return null;
       }
    }
    
    
     public List<GlPeriodStatus> findAssetPeriodsByLedger(Integer ledgerId)
     {
        return entityManager.createQuery("SELECT g FROM GlPeriodStatus g "
                + " WHERE g.faPeriodStatus = 'OPENED'"
                + " AND g.periodSet.ledger.ledgerId = "+ledgerId)
                .getResultList() ;
     }
     
     
     public List<GlPeriodStatus> findPeriodsByName(String text)
     {
         return entityManager.createQuery("SELECT g FROM GlPeriodStatus g "
                + " WHERE UPPER(g.periodName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;         
     }
    
      public List<GlPeriodStatus> findPeriodsByNameAndLedger(GlLedger ledger,String text)
      {
        return entityManager.createQuery("SELECT g FROM GlPeriodStatus g "
                + " WHERE g.periodSet.ledger.ledgerId = "+ledger.getLedgerId()
                + " AND UPPER(g.periodName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;        
     }   
      
     public GlPeriodStatus findPeriodById(int id)
     {
         return entityManager.find(GlPeriodStatus.class, id) ;
     }

     public boolean isInOpenPeriod(int ledgerId,Date txDate)
     {  
         LocalDate date = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         
         return ! entityManager.createQuery("SELECT 1 FROM GlPeriodStatus g "
                 + "WHERE g.periodSet.ledger.ledgerId = :ledgerId "
                 + "AND :txDate BETWEEN g.startDate AND g.endDate "
                 + "AND g.periodStatus = com.bridge.enums.PeriodStatus.OPENED ")
                 .setParameter("txDate", date)
                 .setParameter("ledgerId", ledgerId)
                 .getResultList().isEmpty() ;
     }
     
     public boolean isInOpenAssetPeriod(int ledgerId,Date txDate)
     {  
         LocalDate date = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         
         return ! entityManager.createQuery("SELECT 1 FROM GlPeriodStatus g "
                 + "WHERE g.periodSet.ledger.ledgerId = :ledgerId "
                 + "AND :txDate BETWEEN g.startDate AND g.endDate "
                 + "AND g.faPeriodStatus = 'OPENED' ")
                 .setParameter("txDate", date)
                 .setParameter("ledgerId", ledgerId)
                 .getResultList().isEmpty() ;
     }
     
     public int findDefaultBatchIdForDateAndLedger(Date txDate,int ledgerId)
     {
         LocalDate date = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         
         return entityManager.createQuery("SELECT g.defaultBatch.jeBatchId FROM GlPeriodStatus g "
                 + "WHERE g.periodSet.ledger.ledgerId = :ledgerId "
                 + "AND :txDate BETWEEN g.startDate AND g.endDate "
                 + "AND g.periodStatus = com.bridge.enums.PeriodStatus.OPENED",Integer.class)
                 .setParameter("txDate", date)
                 .setParameter("ledgerId", ledgerId)        
                 .getSingleResult() ;
     }
     
     public FndCurrency findCurrencyById(Integer id)
     {
        return entityManager.createQuery("SELECT l.currency FROM GlLedger l "
                + "WHERE l.ledgerId = :id",FndCurrency.class)
                .setParameter("id", id)
                .getSingleResult();
     }
     
    public GlLedger findWithCurrienciesById(int id)
    {
       try{ 
          
           return entityManager.createQuery("SELECT g FROM GlLedger g LEFT JOIN FETCH g.reportingCurrencys "
               + " WHERE g.ledgerId = :id",GlLedger.class)
               .setParameter("id", id)
               .getSingleResult() ;
           
       }catch(Exception e)
       {
           return null;
       }
    }
    
     public void updatePeriod(GlPeriodStatus period){
       
         entityManager.merge(period);
     }
     
    public void completeOption(GlLedger ledger,GLSetupSteps option)
    { 
       if(option == GLSetupSteps.BASIC_ACCOUNTS && ledger.getCompletionStatusCode() != GlSetupStatus.COMPLETED) 
       {
           ledger.setCompletionStatusCode(GlSetupStatus.COMPLETED);
          
           update(ledger) ;
       }
       
       entityManager.createQuery("UPDATE GlLedgerConfigDetail g "
               + " SET g.statusCode = com.bridge.enums.GlSetupStatus.COMPLETED "
               + " WHERE g.ledger = :ledger "
               + " AND g.setupStepCode = :option")
               .setParameter("ledger", ledger)
               .setParameter("option",option)
               .executeUpdate() ;
    }
    
         
}
