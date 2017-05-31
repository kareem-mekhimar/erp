/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.categories;
import com.bridge.entities.security.SystemRole;
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
public class SystemRolesService extends AbstractService<SystemRole> {
    
    
    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager;    
    
    public SystemRolesService(){
        super(SystemRole.class);
    }
    @Override
    public EntityManager getEntityManager() {
     return entityManager;
    }

     public List<SystemRole> findSystemRoles(Integer srid,String srname, Integer srstat)
    {
        StringBuilder queryString = new StringBuilder("SELECT sysrol FROM SystemRoles sysrol WHERE 1=1 ") ;
           
          if(srstat !=null )
            
            queryString.append(" AND sysrol.status = ").append(srstat);
          
          if(srid != null )
            
            queryString.append(" AND sysrol.id =").append(srid) ;
        
        if(srname != null && ! srname.isEmpty())
            
            queryString.append(" AND upper(sysrol.name) LIKE '%").append(srname.toUpperCase()).append("%'") ;
        
        TypedQuery<SystemRole> query = entityManager.createQuery(queryString.toString(),SystemRole.class) ;
                
        return query.getResultList() ;
    }


    
}
