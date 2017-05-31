/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hzPartiesTypes;

import com.bridge.entities.hzPartiesTypes.HzPartiesTypes;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */

@Stateless
public class HzPartiesTypesService extends AbstractService<HzPartiesTypes>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
        
    public HzPartiesTypesService() {
        super(HzPartiesTypes.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
      
        return entityManager ;
    }
    
    public List<HzPartiesTypes> findHzPartiesType(Integer typeId,String name, Integer stat, Integer enabledFlag)
    {
        StringBuilder queryString = new StringBuilder("SELECT pt FROM HzPartiesTypes pt WHERE 1=1 ") ;
        
        if(typeId !=null && typeId > 0)
            
            queryString.append(" AND pt.typeId = ").append(typeId);
        
        if(stat !=null )
            
            queryString.append(" AND pt.typeStatus = ").append(stat);
        
        if(enabledFlag !=null )
            
            queryString.append(" AND pt.enabledFlagId = ").append(enabledFlag);
        
        if(name != null && ! name.isEmpty())
            
            queryString.append(" AND upper(pt.typeEnName) LIKE '%").append(name.toUpperCase()).append("%'") ;
        
        TypedQuery<HzPartiesTypes> query = entityManager.createQuery(queryString.toString(),HzPartiesTypes.class) ;
                
        return query.getResultList() ;
    }   
    
    public boolean isNameExists(String name){
        return ! entityManager.createQuery("select 1 from HzPartiesTypes h where upper(h.typeEnName)= :name")
                      .setParameter("name", name.toUpperCase())
                      .getResultList().isEmpty() ;
                 
     }
    
}
