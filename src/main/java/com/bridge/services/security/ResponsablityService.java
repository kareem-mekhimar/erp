/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.security;

import com.bridge.entities.security.RolesAssign;
import com.bridge.entities.security.SystemPermission;
import com.bridge.entities.security.SystemRole;
import com.bridge.entities.security.UserAccount;
import com.bridge.enums.SystemPermissionDomain;
import com.bridge.services.AbstractService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class ResponsablityService extends AbstractService<SystemRole> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public ResponsablityService() {
    
        super(SystemRole.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<SystemRole> findAllOrByName(String text)
    {
        StringBuilder builder = new StringBuilder("SELECT r FROM SystemRole r ") ;
        
        if(text != null)
            
            builder.append(" WHERE UPPER(r.name) LIKE UPPER(:text) ") ;
        
        TypedQuery<SystemRole> query = entityManager.createQuery(builder.toString(),SystemRole.class) ;
        
        Optional.ofNullable(text).ifPresent(i -> query.setParameter("text", i));
        
        return query.getResultList() ;
    }
    
    public SystemRole findWithLinesById(int id)
    {
        try {
   
            return entityManager.createQuery("SELECT r FROM SystemRole r LEFT JOIN FETCH r.permissions "
                + "WHERE r.id = :id" , SystemRole.class)
                .setParameter("id", id)
                .getSingleResult() ;
     
        } catch (Exception e) {
        }

        return null ;
    }
    
   
     public boolean isNameExists(String name){
    
          return ! entityManager.createQuery("SELECT 1 from SystemRole s "
                 + " WHERE UPPER(s.name) = UPPER(:name)")
                 .setParameter("name",name)
                 .getResultList().isEmpty() ;
                 
     }  
     
         
     public List<SystemPermission> findSystemPermissonByDomainType(SystemPermissionDomain domain)
     {
         return entityManager.createQuery("SELECT p FROM SystemPermission p "
                 + "WHERE p.systemPermissionDomain = :domain ")
                 .setParameter("domain", domain)
                 .getResultList();
     }
          
     
     public List<SystemPermission> findSystemPermissonByDomainTypeAndNotIn(SystemPermissionDomain domain , String idsDelimited)
     {
         return entityManager.createQuery("SELECT p FROM SystemPermission p "
                 + "WHERE p.systemPermissionDomain = :domain "
                 + "AND p.id NOT IN("+idsDelimited+")")
                 .setParameter("domain", domain)
                 .getResultList();
     }
     
     public SystemPermission findSystemPermissionById(int id)
     {
         return entityManager.find(SystemPermission.class, id);
     }
     
     public void removeRolesPermissionsByIds(String idsDelimited)
     {
         entityManager.createQuery("DELETE FROM RolesPermission p WHERE p.id IN("+idsDelimited+")") 
                      .executeUpdate();
     }
     
     public List<UserAccount> findAccountsNotAssignedToRoleId(int roleId)
     {
         return entityManager.createQuery("SELECT u FROM UserAccount u"
                 + " WHERE NOT EXISTS ( SELECT 1 FROM RolesAssign p WHERE p.role.id = :id AND p.account = u )")
                 .setParameter("id", roleId)
                 .getResultList();
     }
     
     public List<UserAccount> findAssignedAccountsToRole(int roleId)
     {
         return entityManager.createQuery("SELECT p.account FROM RolesAssign p WHERE p.role.id = :id")
                             .setParameter("id", roleId)
                             .getResultList();
     }
     
     public void createAssignments(int roleId,List<UserAccount> accounts)
     {
         accounts.forEach(a -> {
         
             entityManager.persist(new RolesAssign(a,entityManager.getReference(SystemRole.class, roleId))) ;
         });
     }
     
     public void removeAssignments(int roleId , List<UserAccount> accounts)
     {
        String idsDelimited = accounts.stream().map(a -> a.getId()+"")
                                               .collect(Collectors.joining(",")) ;
        
        entityManager.createQuery("DELETE FROM RolesAssign a "
                + "WHERE a.role.id = :id "
                + "AND a.account.id IN ("+idsDelimited+")")
                .setParameter("id", roleId)
                .executeUpdate();
     }
}
