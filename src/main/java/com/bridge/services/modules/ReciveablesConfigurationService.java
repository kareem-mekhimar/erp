/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.modules.PayableModuleConfiguration;
import com.bridge.entities.modules.ReceivableModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.AbstractService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class ReciveablesConfigurationService extends AbstractService<ReceivableModuleSetup>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public ReciveablesConfigurationService () {
        super(ReceivableModuleSetup.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    
    public ReceivableModuleSetup findByOperating(OrganizationUnit organizationUnit)
    {
        try {
        
            return entityManager.createQuery("SELECT g FROM ReceivableModuleSetup g WHERE g.operatingUnit = :o",ReceivableModuleSetup.class)
                .setParameter("o", organizationUnit)
                .getSingleResult() ;           
        } catch (Exception e) {
            System.out.println("exception: "+e.getMessage());
        }

        return null ;
    }   
    
    
    public GlCodeCombination findCashAccountByOperating(OrganizationUnit operating)
    {
        try {
            
           return entityManager.createQuery("SELECT g.cashAcc FROM ReceivableModuleSetup g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;
    }
    
    
    public GlCodeCombination findRecievableAccountByOperating(OrganizationUnit operating)
    {
        try {
            
           return entityManager.createQuery("SELECT g.receivableAcc FROM ReceivableModuleSetup g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;
    }  
    
    
    public GlCodeCombination findRevenueAccountByOperating(OrganizationUnit operating)
    {
        try {
            
           return entityManager.createQuery("SELECT g.revenueAcc FROM ReceivableModuleSetup g "
                   + "WHERE g.operatingUnit = :o" , GlCodeCombination.class)
                 .setParameter("o", operating)
                 .getSingleResult() ;    
        } catch (Exception e) {
           
        }
        
        return null;
    }      
}


