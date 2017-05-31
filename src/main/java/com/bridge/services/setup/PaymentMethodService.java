/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.setup;

import com.bridge.entities.setup.PaymentMethod;
import com.bridge.services.AbstractService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class PaymentMethodService extends AbstractService<PaymentMethod>{
    
   @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public PaymentMethodService() {
    
        super(PaymentMethod.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }

}
