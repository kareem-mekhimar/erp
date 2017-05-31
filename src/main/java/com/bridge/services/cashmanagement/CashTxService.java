/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.cashmanagement;

import com.bridge.entities.cashmanagement.CashTransaction;
import com.bridge.services.AbstractService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class CashTxService extends AbstractService<CashTransaction>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public CashTxService() {
        super(CashTransaction.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }    
    
    
}
