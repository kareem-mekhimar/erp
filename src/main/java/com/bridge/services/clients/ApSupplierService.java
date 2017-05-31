/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.ApSupplierContact;
import com.bridge.entities.clients.ApSupplierSite;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class ApSupplierService extends AbstractService<ApSupplier>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public ApSupplierService() {
       
        super(ApSupplier.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<ApSupplier> findSuppliers(Integer vendorId, String vendorName, Integer enabledFlagId)
    {
        StringBuilder sb = new StringBuilder("SELECT s FROM ApSupplier s WHERE 1 = 1 ");
        
        if(vendorName != null && ! vendorName.isEmpty()){
            sb.append(" and UPPER(s.vendorName) LIKE '%").append(vendorName.toUpperCase()).append("%'");
        }
        if(vendorId != null ){
            sb.append(" and s.vendorId = ").append(vendorId);
        }
        if(enabledFlagId != null ){
            sb.append(" and s.enabledFlagId = ").append(enabledFlagId);
        }
        
        sb.append(" ORDER BY s.vendorId") ;
        
         TypedQuery<ApSupplier> query = entityManager.createQuery(sb.toString(), ApSupplier.class);
         
        return query.getResultList() ;
    }
    
         public ApSupplier findSupplierDetails(Integer id) {
            
        return entityManager.createQuery("SELECT s FROM ApSupplier s"
                + " left join fetch s.supplierSites"
                + " left join fetch s.supplierSiteAcounts"
                + " WHERE s.vendorId = :id", ApSupplier.class)
                .setParameter("id", id)
                .getSingleResult();
    }
        
    public List<ApSupplier> findByName(String text)
    {
      return entityManager.createQuery("SELECT s FROM ApSupplier s WHERE UPPER(s.vendorName) LIKE UPPER(:text)")
              .setParameter("text", "%"+text+"%")
              .getResultList() ;
    }
    
    public List<ApSupplierSite> findSitesForSupplierByName(ApSupplier supplier,String text)
    {
       return entityManager.createQuery("SELECT s FROM ApSupplierSite s WHERE s.vendor = :vendor "
               + "AND UPPER(s.vendorSiteName) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("vendor", supplier)
               .getResultList();
    }
    
    public List<ApSupplierSite> findSitesForSupplier(ApSupplier supplier)
    {
       return entityManager.createQuery("SELECT s FROM ApSupplierSite s WHERE s.vendor = :vendor")
               .setParameter("vendor", supplier)
               .getResultList() ;
    }
    
    public List<ApSupplierContact> findContactsForSiteByName(ApSupplierSite site,String text)
    {
       return entityManager.createQuery("SELECT c FROM ApSupplierContact c WHERE c.site = :site "
               + "AND UPPER(c.vendorContactName) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("site", site)
               .getResultList();
    }
    
    
    public ApSupplierSite findSiteById(int id)
    {
       return entityManager.find(ApSupplierSite.class, id) ;
    }
    
    public ApSupplierContact findContactById(int id)
    {
       return entityManager.find(ApSupplierContact.class, id) ;
    }
    
    
    
}
