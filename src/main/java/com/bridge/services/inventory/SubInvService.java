/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.InventoryType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */
@Stateless
public class SubInvService extends AbstractService<SecondaryInventory> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;
    
    @Inject
    private MainInvService mainInvService;
    

    public SubInvService() {
        super(SecondaryInventory.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    
    public List<SecondaryInventory> findByIdOrStatusOrName(Integer id, Boolean status, String name) {
        
        StringBuilder queryString = new StringBuilder("SELECT t FROM SecondaryInventory t WHERE 1=1 ");

        if (id != null && id > 0) {
            
            queryString.append(" AND t.secondaryInventoryId = ").append(id);
        }

        if (status != null) {
            queryString.append(" AND t.statusId = ").append(status);
        }

        if (name != null) {
            queryString.append(" AND upper(t.secondaryInventoryName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<SecondaryInventory> query = entityManager.createQuery(queryString.toString(), SecondaryInventory.class);

//        EntityGraph graph = entityManager.getEntityGraph("subInvViewGraph") ;
// 
//        query.setHint("javax.persistence.fetchgraph", graph) ;
//        
        return query.getResultList();
    }
    

    public List<SecondaryInventory> findByName(String name) {
        return entityManager.createQuery("SELECT i FROM SecondaryInventory i WHERE UPPER(i.secondaryInventoryName) LIKE UPPER(:text)")
                .setParameter("text", "%" + name + "%")
                .getResultList();
    }

    public List<SecondaryInventory> findByNameForMain(String text, MainInventory mainInv) {
        return entityManager.createQuery("SELECT s FROM SecondaryInventory s "
                + "WHERE s.mainInv = :mainInv AND UPPER(s.secondaryInventoryName) like UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .setParameter("mainInv", mainInv)
                .getResultList();
    }

    public List<SecondaryInventory> findByNameAndMainId(String text, int mainInvId) {
        return entityManager.createQuery("SELECT s FROM SecondaryInventory s "
                + "WHERE s.mainInv.organizationId = :mainInvId "
                + "AND UPPER(s.secondaryInventoryName) like UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .setParameter("mainInvId", mainInvId)
                .getResultList();
    }
    
    public List<SecondaryInventory> findForBatchByName(GmeBatchHeader batch,String text)
    {  
        return entityManager.createQuery("SELECT DISTINCT l.toSubinventory FROM MtlTxnRequestLine "
                + "l WHERE l.gmeMaterialDetail.batch = :batch "
                + "AND UPPER(l.toSubinventory.secondaryInventoryName) LIKE UPPER(:text)" )
                .setParameter("batch", batch)
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
                
    }
    
    public List<SecondaryInventory> findByOperatingUnitAndName(int operatingId , String text)
    {
       return entityManager.createQuery("SELECT s FROM SecondaryInventory s "
               + "WHERE s.mainInv.operatingUnit.orgUnitId = :id "
               + "AND UPPER(secondaryInventoryName) LIKE UPPER(:text)")
               .setParameter("id", operatingId)
               .setParameter("text", "%"+text+"%")
               .getResultList() ;
    }

    
    public void updateInv(SecondaryInventory inv){
        
       inv.setGlId(inv.getMainInv().getGlId());

        SecondaryInventory subInv = update(inv);
        
        MainInventory mainInv = inv.getMainInv();
        
       if(inv.getShippingFlag()){
           
            mainInv.setShippingSubInventory(subInv.getSecondaryInventoryId());
            
       }else if(! inv.getShippingFlag() && inv.getOldShipping()){
           
          mainInv.setShippingSubInventory(null);
       }
       
        mainInvService.update(mainInv);



    }

    public List<SecondaryInventory> findByIdOrStatusOrNameOrCode(Integer id, Boolean status, String name, String code) {
        
        StringBuilder queryString = new StringBuilder("SELECT t FROM SecondaryInventory t WHERE 1=1 ");

        if (id != null && id > 0) {
            
            queryString.append(" AND t.secondaryInventoryId = ").append(id);
        }

        if (status != null) {
            queryString.append(" AND t.statusId = ").append(status);
        }

        if (name != null) {
            queryString.append(" AND upper(t.secondaryInventoryName) LIKE '%").append(name.toUpperCase()).append("%'");
        }
        
        if (code != null) {
            queryString.append(" AND upper(t.organizationCode) LIKE '%").append(code.toUpperCase()).append("%'");
        }

        TypedQuery<SecondaryInventory> query = entityManager.createQuery(queryString.toString(), SecondaryInventory.class);

//        EntityGraph graph = entityManager.getEntityGraph("subInvViewGraph") ;
// 
//        query.setHint("javax.persistence.fetchgraph", graph) ;
//        
        return query.getResultList();
    }
    

}
