/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.Country;
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
public class CountryService extends AbstractService<Country> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public CountryService() {
        super(Country.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public Country findCountryByCode(String code) {
        
        try {
            
            String query = "SELECT a FROM Country a WHERE a.countryCode = '" + code + "'";

            return entityManager.createQuery(query, Country.class).getSingleResult();
            
        } catch (Exception e) {
            
            return null;
        }

    }

    public List<Country> findCountry(String text) {
        StringBuilder queryBuilder = new StringBuilder("SELECT a FROM Country a WHERE 1 = 1 ");

        if (text != null) {
            queryBuilder.append(" AND UPPER(a.countryName) LIKE upper('%").append(text).append("%')");
        }

        return entityManager.createQuery(queryBuilder.toString(), Country.class).getResultList();
    }

}
