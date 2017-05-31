/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.currency;

import com.bridge.entities.currency.CurrencyConversion;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.services.AbstractService;
import java.math.BigDecimal;
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
public class CurrencyService extends AbstractService<FndCurrency>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public CurrencyService() {
        super(FndCurrency.class);
    }

    @Override
    public EntityManager getEntityManager() {
  
        return entityManager ;
    }
    
    public List<FndCurrency> findValidCurrencies(Integer glId){
        
         return entityManager.createQuery("SELECT DISTINCT c.fromCurrency FROM CurrencyConversion c WHERE c.glId = "+glId,FndCurrency.class)
               .getResultList();
    }

    public List<FndCurrency> findByCodeOrNameOrStatus(String code , String name , Boolean status)
    {
        StringBuilder queryString = new StringBuilder("SELECT e FROM FndCurrency e WHERE 1 = 1") ;
        
        if(code != null)
            
            queryString.append(" AND e.currencyCode = :code ") ;
        
        if(name != null)
            
            queryString.append(" AND UPPER(e.description) LIKE UPPER(:text)") ;

        if(status != null)
    
            queryString.append(" AND e.enabled is :status") ;
        
        queryString.append(" order by e") ;
        
        TypedQuery<FndCurrency> query = entityManager.createQuery(queryString.toString() ,FndCurrency.class) ;
                
        Optional.ofNullable(code).ifPresent(s -> query.setParameter("code", code));
        
        Optional.ofNullable(status).ifPresent(s -> query.setParameter("status", status));
        
        Optional.ofNullable(name).ifPresent(s -> query.setParameter("text", "%"+name+"%"));
        
        return query.getResultList() ;                
    }
    
    public boolean isCodeExists(String code)
    {
        try {
           
           entityManager.createQuery("SELECT 1 FROM FndCurrency c WHERE c.currencyCode = :code")
                       .setParameter("code", code)
                       .getSingleResult() ;
                    
           return true ;
           
        } catch (Exception e) {
       
            return false ;
        }

    }
    
    public List<FndCurrency> findAllAndNotIn(String ids)
    {
       return entityManager.createQuery("SELECT c FROM FndCurrency c "
               + "WHERE c.currencyId NOT IN("+ids+")")
               .getResultList();
    }
    
    public List<CurrencyConversion> findConversionByCurrency(Integer first,Integer second){
        
    StringBuilder queryString = new StringBuilder("SELECT c FROM CurrencyConversion c WHERE c.defaultCurrency = false") ;
        
        if(first != null)
            
            queryString.append(" AND c.fromCurrency.currencyId = :first") ;
        
        if(second != null)
            
            queryString.append(" AND c.toCurrency.currencyId = :second") ;

        queryString.append(" order by c") ;
        
        TypedQuery<CurrencyConversion> query = entityManager.createQuery(queryString.toString() ,CurrencyConversion.class) ;
                
        if(first != null) query.setParameter("first", first);
        
         if(second != null) query.setParameter("second", second);
        
        return query.getResultList() ;        
    }
    
    
    private BigDecimal findNewestRate(FndCurrency from , FndCurrency to)
    {
        return entityManager.createQuery("SELECT c.conversionRate FROM CurrencyConversion c "
                + "WHERE c.fromCurrency =:from AND c.toCurrency = :to ORDER BY c.dayDate DESC" , BigDecimal.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(1)
                .getSingleResult() ;
               
    }
    
    
    public CurrencyConversion update(CurrencyConversion conversion){
        return entityManager.merge(conversion);
    }
    
    public void removeConversion(CurrencyConversion conversion){
        entityManager.createQuery("DELETE FROM CurrencyConversion c Where c.id = " + conversion.getId()).executeUpdate();
    }
    
    public boolean isConversionExists(FndCurrency fromCurrency,FndCurrency toCurrency,Date date){
        
        return ! entityManager.createQuery("SELECT 1 FROM CurrencyConversion c WHERE c.fromCurrency = :fc AND c.toCurrency = :tc AND c.dayDate = :cdate")
                .setParameter("fc",fromCurrency)
                .setParameter("tc",toCurrency)
                .setParameter("cdate",date)
                .getResultList().isEmpty();
    }
    
    public BigDecimal convert(BigDecimal amount , FndCurrency from , FndCurrency to)
    {
       BigDecimal rate = findNewestRate(from, to) ;
       
       return rate.multiply(amount) ;
    }
    
}
