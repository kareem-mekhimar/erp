/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.People;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class PeopleService extends AbstractService<People> {
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public PeopleService() {        
        super(People.class);
    }
    
    @Override
    public EntityManager getEntityManager() {        
        return entityManager;
    }
    
        
    public List<People> findByName(String name)
    {
       return entityManager.createQuery("SELECT p FROM People p WHERE UPPER(p.fullName) LIKE UPPER(:text)")
                           .setParameter("text", "%"+name+"%")
                           .getResultList();
    }
    
    
    
    public List<People> findPeople(String code, String name) {
            
        StringBuilder queryString = new StringBuilder("SELECT p FROM People p WHERE 1=1");

        if (code != null && ! code.isEmpty() ) {
            queryString.append(" AND upper(p.personCode) = '").append(code.toUpperCase()).append("'");
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(p.fullName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<People> query = entityManager.createQuery(queryString.toString(), People.class);
 
        return query.getResultList();
    }
        
        
    public List<People> findAgentPerson(String name) {
            
        String queryString = "SELECT p FROM People p WHERE p.salesPerson = true  AND upper(p.fullName) LIKE UPPER('%" + name + "%')";
        
        TypedQuery<People> query = entityManager.createQuery(queryString, People.class);
        
        return query.getResultList();
    }
    
        
    public People findPeopleDetails(Integer id) {
            
        try {
            
                 return entityManager.createQuery("SELECT f FROM People f WHERE f.personId = :id", People.class)
                .setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            
            return null;
        }
   
    }
            
    public boolean isCodeExists(String code){
      
           return ! entityManager.createQuery("select 1 from People s where upper(s.personCode)= upper(:code)")
                      .setParameter("code", code)
                      .getResultList().isEmpty() ;
                 
    }
    
   

}
