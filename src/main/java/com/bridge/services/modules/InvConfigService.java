/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.modules;


import com.bridge.entities.modules.InventoryConfguration;
import com.bridge.services.AbstractService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class InvConfigService extends AbstractService<InventoryConfguration>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public InvConfigService() {
        super(InventoryConfguration.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
    
    public InventoryConfguration findByOrgUnitId(Integer id){
        try {
             return entityManager.createQuery("SELECT c FROM InventoryConfguration c WHERE c.operatingUnitId = "+id,
              InventoryConfguration.class).getSingleResult();
        } catch (Exception e) {
            return null;
        }
     
       
    }
    
    
}
