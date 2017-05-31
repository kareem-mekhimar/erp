/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.periods;

import com.bridge.entities.periods.GlPeriod;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class PeriodTypeService extends AbstractService<GlPeriodType>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public PeriodTypeService() {
        super(GlPeriodType.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<GlPeriodType> findByName(String text)
    {
        return entityManager.createQuery("SELECT g FROM GlPeriodType g "
                + "WHERE UPPER(g.periodTypeName) LIKE UPPER(:text) ")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
    }
    
}
