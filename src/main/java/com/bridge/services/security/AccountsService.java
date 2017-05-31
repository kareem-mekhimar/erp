/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.security;

import com.bridge.entities.hr.Employee;
import com.bridge.entities.hr.People;
import com.bridge.entities.security.UserAccount;
import com.bridge.services.AbstractService;
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
public class AccountsService extends AbstractService<UserAccount>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
    
    public AccountsService() {
        super(UserAccount.class);
    }

    @Override
    public EntityManager getEntityManager() {
   
        return entityManager ;
    }
    
    public List<People> findEmployeesHaveAccountByName(String text)
    {
       StringBuilder queryBuilder = new StringBuilder("SELECT a.employee FROM UserAccount a WHERE 1 = 1 ")  ;
       
       if(text != null)
           
           queryBuilder.append(" AND UPPER(a.employee.fullName) LIKE UPPER(:text)") ;
       
       TypedQuery<People> typedQuery = entityManager.createQuery(queryBuilder.toString(),People.class) ;
       
       Optional.ofNullable(text).ifPresent(t -> typedQuery.setParameter("text","%"+text+"%" ));
       
       return typedQuery.getResultList() ;
    }
    
    
    public UserAccount findByEmployee(int id)
    {
        try {
       
            return entityManager.createQuery("SELECT a FROM UserAccount a WHERE a.employee.personId = :id",UserAccount.class)
                                .setParameter("id", id).getSingleResult() ;     
            
        } catch (Exception e) {
        
            return null ;
        }
    }
    
    public boolean isExists(String userName)
    {
        return ! entityManager.createQuery("SELECT 1 FROM UserAccount a WHERE a.userName = :name")
                                .setParameter("name", userName)
                                .getResultList().isEmpty() ; 
    }
    
     
    public List<People> findEmployeeByNameWithoutAccount(String name)
    {
        return entityManager.createQuery("SELECT p FROM People p "
                + "WHERE p NOT IN (SELECT u.employee FROM UserAccount u)"
                + " AND UPPER(p.fullName) LIKE UPPER(:text)")
                .setParameter("text", "%"+name+"%")
                .getResultList() ;
    }
   
    public People findEmployeeByUsername(String userName)
    {
        try {
               return entityManager.createQuery("SELECT u.employee FROM UserAccount u "
                + "WHERE u.userName = :userName",People.class)
                .setParameter("userName", userName)
                .getSingleResult() ;
               
        } catch (Exception e) {
            
            return null;
        }
     
    }
}
