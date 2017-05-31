/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.City;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class CityService extends AbstractService<City> {
       
     @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
    
    public CityService() {
        super(City.class);
    }

    @Override
    public EntityManager getEntityManager() {
   
        return entityManager ;
    }
    
    public List<City> findCity(String country , String text)
    {
       StringBuilder queryBuilder = new StringBuilder("SELECT a FROM City a WHERE 1 = 1 ")  ;
       if(country != null)
           queryBuilder.append(" AND a.countryCode = '").append(country).append("'") ;
       if(text != null)
           queryBuilder.append(" AND UPPER(a.cityName) LIKE UPPER('%").append(text).append("%')") ;
       
       return entityManager.createQuery(queryBuilder.toString(),City.class).getResultList() ;
    }
    
    public List<String> findCityName(String code , String text)
    {
       String queryString = "SELECT c.cityName FROM City c "
               + "WHERE UPPER(c.countryCode) = UPPER('"+code+"') AND UPPER(c.cityName) LIKE UPPER('%"+text+"%')" ;
       
       return entityManager.createQuery(queryString,String.class).getResultList() ;
    }
    
    public List<City> findCityList(String code)
    {
       String queryString = "SELECT a FROM City a WHERE UPPER(a.countryCode) = UPPER('"+code+"')" ;
       
       return entityManager.createQuery(queryString,City.class).getResultList() ;
    }
    

}
