/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.modules.PayableModuleConfiguration;
import com.bridge.entities.organization.OrganizationUnit;
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
public class PayableModuleConfigurationService extends AbstractService<PayableModuleConfiguration>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public PayableModuleConfigurationService() {
        super(PayableModuleConfiguration.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public PayableModuleConfiguration findByOperating(OrganizationUnit organizationUnit)
    {
        try {
        
            return entityManager.createQuery("SELECT g FROM PayableModuleConfiguration g WHERE g.operatingUnit = :o",PayableModuleConfiguration.class)
                .setParameter("o", organizationUnit)
                .getSingleResult() ;           
        } catch (Exception e) {
           
        }

        return null ;
    }   
 
    public GlCodeCombination findChargingAccountByOperating(OrganizationUnit operating)
    {
        try {
            
           return entityManager.createQuery("SELECT g.chargeAccount FROM PayableModuleConfiguration g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;
    }
    
    
    public GlCodeCombination findCashClearingAccountByOperating(OrganizationUnit operating)
    {
         try {
            
           return entityManager.createQuery("SELECT g.cachClearingAcc FROM PayableModuleConfiguration g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;       
    }
    
    
}
