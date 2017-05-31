/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Bridge
 * @param <T>
 */

public abstract class AbstractService<T> {
    
    private final Class<T> entityClass ;

    public AbstractService(Class<T> entityClass) {
    
        this.entityClass = entityClass;
    }
    
    
    public abstract EntityManager getEntityManager() ;
    
    public void create(T entity)
    {
        getEntityManager().persist(entity);
    }
    
    public List<T> findAll()
    {
        EntityManager em = getEntityManager() ;
        
        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass) ;
        
        Root<T> root = criteriaQuery.from(entityClass) ;
        
        return em.createQuery(criteriaQuery).getResultList();
    } 
    
    public T findById(Object id)
    {
       return getEntityManager().find(entityClass,id ) ;
    }
    
    public void remove(T entity)
    {
       EntityManager em = getEntityManager();
       
       em.remove(em.merge(entity));
    }
    
    public T update(T entity)
    {
       return getEntityManager().merge(entity) ;
    }
}
