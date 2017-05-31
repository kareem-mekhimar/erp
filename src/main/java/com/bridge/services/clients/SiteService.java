/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.ApSupplierSite;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientContact;
import com.bridge.entities.clients.ClientSite;
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
public class SiteService extends AbstractService<ClientSite> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public SiteService() {

        super(ClientSite.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public ClientSite findSiteDetail(Integer id) {
        
        try {
            
                  return entityManager.createQuery("SELECT s FROM ClientSite s left join fetch s.contactList"
                + " WHERE s.siteId = :id", ClientSite.class).setParameter("id", id).getSingleResult();
                  
        } catch (Exception e) {
            
            return null;
            
        }
  
    }
    

    public void removeLines(String lineIds) {
        entityManager.createQuery("DELETE FROM ClientContact line WHERE line.contactId IN (" + lineIds + ")")
                .executeUpdate();
    }
    
    
    public List<ClientSite> findSitesForClientByName(Client client,String text)
    {
       return entityManager.createQuery("SELECT s FROM ClientSite s WHERE s.clientId = :client "
               + "AND UPPER(s.siteName) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("client", client)
               .getResultList();
    }
    
    public List<ClientSite> findSitesForClient(Client client)
    {
       return entityManager.createQuery("SELECT s FROM ClientSite s WHERE s.clientId = :client")
               .setParameter("client", client)
               .getResultList() ;
    }
    
    public List<ClientContact> findContactsForSiteByName(ClientSite site,String text)
    {
       return entityManager.createQuery("SELECT c FROM ClientContact c WHERE c.siteId = :site "
               + "AND UPPER(c.contactName) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("site", site)
               .getResultList();
    }
    
    public List<ClientSite> findPaymentSitesByClient(Client client)
    {
       return entityManager.createQuery("SELECT s FROM ClientSite s "
               + "WHERE s.supplierAcc.paymentFlag IS TRUE "
               + "AND s.clientId = :client")
               .setParameter("client", client)
               .getResultList() ;
    }
    
    
    public ClientSite findSiteById(int id)
    {
       return entityManager.find(ClientSite.class, id) ;
    }
    
    public ClientContact findContactById(int id)
    {
       return entityManager.find(ClientContact.class, id) ;
    }
    
    public List<ClientSite> findSupplingSitesForClientByName(Client client,String text)
    {
  
        return entityManager.createQuery(
                "   SELECT s "
                + " FROM ClientSite s "
                + " WHERE s.clientId = :client and s.supplierAcc.purchasingFlag = true  "
                + " AND UPPER(s.siteName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .setParameter("client", client)
                .getResultList();
    }
    public List<ClientSite> findPaymentSitesForClientByName(Client client,String text)
    {
  
        return entityManager.createQuery(
                "   SELECT s "
                + " FROM ClientSite s "
                + " WHERE s.clientId = :client and s.supplierAcc.paymentFlag = true  "
                + " AND UPPER(s.siteName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .setParameter("client", client)
                .getResultList();
    }
    
}
