/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.Location;
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
public class LocationsService  extends AbstractService<Location>{
        @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public LocationsService() {        
        super(Location.class);
    }
    
    @Override
    public EntityManager getEntityManager() {        
        return entityManager;
    }
    
        public List<Location> findLocation(Integer locId,String code)
    {
        StringBuilder queryString = new StringBuilder("SELECT jo FROM Location jo WHERE 1=1 ") ;
        
        if(locId !=null && locId > 0)
            
            queryString.append(" AND jo.locationId = ").append(locId);  
        
        if(code != null && ! code.isEmpty())
            
            queryString.append(" AND upper(jo.locationCode) LIKE '%").append(code.toUpperCase()).append("%'") ;
        
        TypedQuery<Location> query = entityManager.createQuery(queryString.toString(),Location.class) ;
                
        return query.getResultList() ;
    }
        
        public List<Location> findLocationByCode(String text) {
        return entityManager.createQuery("SELECT s FROM Location s WHERE UPPER(s.locationCode) LIKE :text")
                .setParameter("text", "%" + text.toUpperCase() + "%")
                .getResultList();
    }
    
        public boolean isCodeExists(String string) {
            System.out.println("com.bridge.services.hr.LocationsService.isCodeExists()"+string);
        return ! entityManager.createQuery("select 1 from Location o where upper(o.locationCode)= :code")
                      .setParameter("code", string.toUpperCase())
                      .getResultList().isEmpty() ;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
