/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.periods;

import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.periods.GlPeriod;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.enums.PeriodStatus;
import com.bridge.services.AbstractService;
import com.bridge.services.journal.JournalBatchService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */


@Stateless
public class CalendarService extends AbstractService<GlPeriodSet>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private JournalBatchService batchService ;
    
    public CalendarService() {
    
        super(GlPeriodSet.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {
    
        return entityManager ;
    }
    
    public List<GlPeriodSet> findByNameOrTypeId(String text , Integer periodType )
    {    
        StringBuilder stringBuilder = new StringBuilder("SELECT g FROM GlPeriodSet g WHERE 1 = 1 ") ;
        
        if(text != null)
            
            stringBuilder.append(" AND UPPER(g.periodSetName) LIKE UPPER(:text)") ;
        
        if(periodType != null)
            
            stringBuilder.append(" AND g.periodType.periodTypeId = :typeId ") ;
        
        TypedQuery query = entityManager.createQuery(stringBuilder.toString(),GlPeriodSet.class) ;
        
        Optional.ofNullable(text).ifPresent(t -> query.setParameter("text", "%"+ text+"%"));
                
        Optional.ofNullable(periodType).ifPresent(id -> query.setParameter("typeId", id));
        
        return query.getResultList() ;
    }
    
    public List<GlPeriodStatus> findPeriodsForCalendarAndYear(GlPeriodSet calendar , int year , boolean adjust)
    {
        return entityManager.createQuery("SELECT p FROM GlPeriodStatus p "
                + "WHERE p.periodYear = :year "
                + "AND p.periodSet = :calendar "
                + "AND p.adjustmentPeriodFlag = :adjust "
                + "ORDER BY p.periodNum")
                .setParameter("calendar", calendar)
                .setParameter("year", year)
                .setParameter("adjust", adjust)
                .getResultList() ;
    }
    
    public void updatePeriods(List<GlPeriodStatus> periods)
    {        
        periods.forEach(p -> {
                  
            if(p.getGlPeriodId() == null)
                
                entityManager.persist(p);
            
            if(p.getJournalHeaderIds() != null)
            
                    batchService.createNormalFromRecurring(p.getJournalHeaderIds(),p);
             
               if(p.getPeriodStatus() == PeriodStatus.OPENED && p.getDefaultBatch() == null)
               {
                   GlJeBatch defaultBatch = new GlJeBatch(p) ;
               
                   defaultBatch.setLedger(p.getPeriodSet().getLedger());
               
                   entityManager.persist(defaultBatch);
                   
                   p.setDefaultBatch(defaultBatch);
             
               }
               
               entityManager.merge(p) ;
        }) ;
    }
    
    
    public List<GlPeriodSet> findByName(String text)
    {
        return entityManager.createQuery("SELECT g FROM GlPeriodSet g WHERE UPPER(g.periodSetName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
    }
    
    public List<GlPeriodStatus> findPeriodByNameForCalendar(GlPeriodSet periodSet,String name)
    {
       return entityManager.createQuery("SELECT p FROM GlPeriodStatus p "
               + " WHERE UPPER(p.periodName) LIKE UPPER(:text) "
               + " AND p.periodSet = :calendar")
               .setParameter("calendar",periodSet)
               .setParameter("text", "%"+name+"%")
               .getResultList();
    }
    
    public List<GlPeriodStatus> findPeriodByName(String text)
    {
        return entityManager.createQuery("SELECT p FROM GlPeriodStatus p "
               + " WHERE UPPER(p.periodName) LIKE UPPER(:text) ")     
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
                
    }
    
    public GlPeriodStatus findPeriodById(Integer id)
    {
        return entityManager.find(GlPeriodStatus.class, id) ;
    }
    
    public List<GlPeriodStatus> findPeriodsForCalendar(GlPeriodSet periodSet)
    {
       return entityManager.createQuery("SELECT p FROM GlPeriodStatus p "
               + "WHERE p.periodSet = :calendar")
               .setParameter("calendar", periodSet)
               .getResultList();
    }
    
    
//    public void openPeriods(List<GlPeriod> periods)
//    {
//       String idsDelimited = periods.stream()
//                                    .map(p -> p.getPeriodId()+"")
//                                    .collect(Collectors.joining(",")) ;
//       
//
//       entityManager.createQuery("UPDATE GlPeriod p "
//               + " SET p.status = com.bridge.enums.PeriodStatus.OPENED "
//               + " WHERE p.periodId IN("+idsDelimited+")")
//               .executeUpdate();
//    }
    
}
