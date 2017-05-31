/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.modules;

import com.bridge.entities.modules.AssetModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.AbstractService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class AssetConfigurationService extends AbstractService<AssetModuleSetup> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public AssetConfigurationService() {
        super(AssetModuleSetup.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public AssetModuleSetup findByOperating(OrganizationUnit organizationUnit)
    {
        try {        
            return entityManager.createQuery("SELECT a FROM AssetModuleSetup a WHERE a.operatingUnit = :o",AssetModuleSetup.class)
                .setParameter("o", organizationUnit)
                .getSingleResult() ;           
        } catch (Exception e) {
            System.out.println("exception: "+e.getMessage());
        }

        return null ;
    }   
    
    
}
