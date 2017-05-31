/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.journal;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.journal.GlJeLine;
import com.bridge.entities.journal.GlJeRecBatchStatus;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.periods.GlPeriod;
import com.bridge.enums.JournalBatchType;
import com.bridge.services.AbstractService;
import com.bridge.services.ledger.GlLedgerService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
public class JournalBatchService extends AbstractService<GlJeBatch>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private GlLedgerService ledgerService ;
    
    public JournalBatchService() {
    
        super(GlJeBatch.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    
    public GlJeHeader updateHeader(GlJeHeader header)
    {
       return entityManager.merge(header) ; 
    }
    
    public List<GlJeBatch> findByNameOrLedgerOrPeriod(String text , GlLedger ledger , GlPeriodStatus period)
    {
        StringBuilder builder = new StringBuilder("SELECT j FROM GlJeBatch j WHERE 1 = 1 ") ;

        if(ledger != null)
            
            builder.append(" AND j.ledger = :ledger") ;
        
        if(text != null)
            
            builder.append(" AND UPPER(j.batchName) LIKE UPPER(:text)") ;
        
        if(period != null)
            
            builder.append(" AND j.period = :period") ;
        
        TypedQuery<GlJeBatch> query = entityManager.createQuery(builder.toString(),GlJeBatch.class) ;
        
        Optional.ofNullable(ledger).ifPresent(i -> query.setParameter("ledger", ledger));
        
        Optional.ofNullable(text).ifPresent(i -> query.setParameter("text","%"+text+"%" ));

        Optional.ofNullable(period).ifPresent(i -> query.setParameter("period", period));
        
        return query.getResultList() ;
    }

    public List<GlJeBatch> findByNAme(String text)
    {
        return entityManager.createQuery("SELECT  j FROM GlJeBatch j "
                + "WHERE UPPER(j.batchName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
    }
    
    public GlJeHeader findHeaderById(int id)
    {
       return entityManager.find(GlJeHeader.class, id) ;
    }
    
    
    public List<GlJeBatch> findByNameOrTypeAndLedger(String text,JournalBatchType type,GlLedger ledger)
    {
        StringBuilder builder = new StringBuilder("SELECT DISTINCT j FROM GlJeBatch j JOIN FETCH j.headers "
                + "WHERE j.batchType = :type "
                + "AND j.ledger = :ledger") ;
        
        if(text != null)
            
            builder.append(" AND UPPER(j.batchName) LIKE UPPER(:text)") ;
        
        TypedQuery<GlJeBatch> query = entityManager.createQuery(builder.toString(),GlJeBatch.class)
                .setParameter("ledger", ledger)
                .setParameter("type", type);
        
        Optional.ofNullable(text).ifPresent(t -> query.setParameter("text", "%"+text+"%"));
        
        return query.getResultList() ;
        
    }
    
    public List<GlJeHeader> findHeaderByName(String text)
    {
        StringBuilder builder = new StringBuilder("SELECT h FROM GlJeHeader h WHERE 1 = 1 ") ;
        
        if(text != null)
            
            builder.append(" AND UPPER(h.jeHeaderName) LIKE UPPER(:text)") ;
        
        TypedQuery<GlJeHeader> query =  entityManager.createQuery(builder.toString(),GlJeHeader.class);
        
        Optional.ofNullable(text).ifPresent(i -> query.setParameter("text","%"+ text+"%"));
        
        return query.getResultList() ;
    }
    
    public GlJeHeader findHeaderWithLinesByIdAndBatchId(int id , GlJeBatch batch)
    {
        try {
          
            return entityManager.createQuery("SELECT h FROM GlJeHeader h LEFT JOIN FETCH h.lines "
               + "WHERE h.jeHeaderId = :id "
               + "AND h.batch = :batch ",GlJeHeader.class)
               .setParameter("id", id)
               .setParameter("batch",batch )
               .getSingleResult() ;
            
        } catch (Exception e) {
      
            return null ;
        }
    }
    
    public boolean areRecurringExistForLedger(GlLedger ledger)
    {
        return ! entityManager.createQuery("SELECT 1 FROM GlJeBatch j "
                + "WHERE j.batchType = :type "
                + "AND j.ledger = :ledger")
                .setParameter("ledger", ledger)
                .setParameter("type", JournalBatchType.RECURRING)
                .getResultList().isEmpty() ;
    }   
    
    public void createNormalFromRecurring(String journalIdsDelimited , GlPeriodStatus period)
    {
        String [] ids = journalIdsDelimited.split(",") ;
       
        HashMap<Integer,GlJeBatch> oldToNewBatchesMap = new HashMap<>() ;
        
        for(String id : ids)
        {       
            int count = 0 ; 
            
            int oldJournalId = Integer.parseInt(id) ;
            
            GlJeHeader header = entityManager.find(GlJeHeader.class, oldJournalId) ;
            
            GlJeBatch batch = header.getBatch() ;
            
            batch.setHeaders(null);
            
            entityManager.detach(batch);

            int oldBatchId = batch.getJeBatchId() ;
            
            if(! oldToNewBatchesMap.containsKey(oldBatchId))
            {
               batch.setJeBatchId(null);
           
               batch.setBatchType(JournalBatchType.NORMAL);
            
               batch.setPeriod(period);
            
               batch.setBatchName(batch.getBatchName() +" - "+period.getPeriodName());
               
               entityManager.persist(batch);  
              
               count ++ ;
               
               oldToNewBatchesMap.put(oldBatchId, batch) ;
            }
            else
                
                batch = oldToNewBatchesMap.get(oldBatchId) ;
  
            long linesCount = countLinesForHeader(header);
            
            if(batch.isRunOncePeriod())
            {
                entityManager.persist(new GlJeRecBatchStatus(period.getGlPeriodId(), oldBatchId, oldJournalId));
                
                count ++ ;
            }
            
            entityManager.detach(header);
                
            header.setJeHeaderId(null);
                
            header.setLines(null);
            
            header.setBatch(batch);
                
            entityManager.persist(header);
                 
            count ++ ;     
                 
            int linesPageCount = (int) Math.ceil((float) linesCount / 10) ;

            for(int page = 0 ; page < linesPageCount ; page ++)
            {
                List<GlJeLine> lines = findLinesForHeader(oldJournalId, page * 10, 10) ;
                    
                for(int j = 0 ; j < lines.size() ; j++)
                {
                   GlJeLine line = lines.get(j) ;
                   
                   entityManager.detach(line);
                   
                   line.setJeLineId(null);

                   line.setHeader(header);
                   
                   entityManager.persist(line);
                              
                   count ++ ;
                       
                   if(count >= 0)
                   {
                      entityManager.flush();
                           
                      entityManager.clear();
                           
                      count = 0 ;
                    }
                    
                   } // END LINES FOR
                    
                } // END LINES PAGINATION
                 
                 if(count >= 20)
                 {
                    entityManager.flush();
                           
                    entityManager.clear();
                           
                    count = 0 ;
                 }                
               
          } // END HEADERS FOR
      
    }
            
    
    public List<GlJeHeader> findJournalHeadersForBatch(int batchId,int start,int max)
    {
       return entityManager.createQuery("SELECT j FROM GlJeHeader j "
               + " WHERE j.batch.jeBatchId = :id")
               .setParameter("id", batchId)
               .setFirstResult(start)
               .setMaxResults(max)
               .getResultList() ;
    }
    

    public long countJournalHeadersForBatch(int batchId)
    {
       return entityManager.createQuery("SELECT count(1) from GlJeHeader j "
               + "WHERE j.batch.jeBatchId = :id" , Long.class)
               .setParameter("id", batchId)
               .getSingleResult() ;
       
    }
    
    public long countLinesForHeader(GlJeHeader header)
    {
       return entityManager.createQuery("SELECT count(1) FROM GlJeLine j "
               + "WHERE j.header = :header",Long.class)
               .setParameter("header", header)
               .getSingleResult() ;
    }
     
    public List<GlJeLine> findLinesForHeader(int headerId , int start , int max)
    {
       return entityManager.createQuery("SELECT j FROM GlJeLine j "
               + "WHERE j.header.jeHeaderId = :header")
               .setParameter("header",headerId)
               .setMaxResults(max)
               .setFirstResult(start)
               .getResultList() ;
    }
    
    
    public GlJeHeader createJournalsForDate(int ledgerId ,String description, Date date , BigDecimal amount,GlCodeCombination debitAccount,GlCodeCombination creditAccount)
    {
       int defaultBatchId = ledgerService.findDefaultBatchIdForDateAndLedger(date, ledgerId) ;
       
       SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy") ;
       
       String dateFormated = format.format(date) ;
       
       GlJeHeader header = new GlJeHeader(description +" - "+dateFormated , amount) ;
       
       header.setBatch(entityManager.getReference(GlJeBatch.class, defaultBatchId));
       
       GlJeLine debitLine = new GlJeLine(debitAccount, amount, true , 1);
       
       debitLine.setHeader(header);
       
       GlJeLine creditLine = new GlJeLine(creditAccount, amount, false , 2) ;
       
       creditLine.setHeader(header);
       
       entityManager.persist(header);
       
       entityManager.persist(debitLine);
       
       entityManager.persist(creditLine);
       
       return header ;
    }
    
    
    public void reverseHeader(GlJeHeader header)
    {
       int originalHeaderId = header.getJeHeaderId() ;
     
       long linesCount = countLinesForHeader(header);
       
       header.setReversedFlag(true);
       
       entityManager.merge(header) ;

       entityManager.flush();
       
       entityManager.detach(header);
       
       header.setLines(null);
       
       header.setJeHeaderId(null);

       header.setJeHeaderName(header.getJeHeaderName()+" - REVERSED");
       
       header.setReversedJeHeaderId(originalHeaderId);
       
       entityManager.persist(header);
       
       int linesPageCount = (int) Math.ceil((float) linesCount / 10) ;

       for(int page = 0 ; page < linesPageCount ; page ++)
       {
           List<GlJeLine> lines = findLinesForHeader(originalHeaderId, page * 10, 10) ;
                    
           for(int j = 0 ; j < lines.size() ; j++)
           {
                GlJeLine line = lines.get(j) ;
                   
                entityManager.detach(line);
                   
                line.setJeLineId(null);

                line.setHeader(header);
                   
                BigDecimal debit = line.getAccountedDr() ;
                
                BigDecimal credit = line.getAccountedCr() ;
                
                if(debit != null)
                {
                   line.setAccountedDr(null);
                   
                   line.setAccountedCr(debit);
                }
                else if(credit != null)
                {
                   line.setAccountedCr(null);
                   
                   line.setAccountedDr(credit);
                }
                
                entityManager.persist(line);

                if(j % 10 == 0)
                {
                    entityManager.flush();
                       
                    entityManager.clear();
                }
                
           } //END LINES FOR
           
       } // END PAGINATION FOR
                            
    }
}
