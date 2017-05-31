/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;


import com.bridge.entities.clients.HzParties;
import com.bridge.entities.clients.HzPartySite;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.enums.CustomerType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
@Stateless
public class HzPartyService  extends AbstractService<HzParties>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public HzPartyService() {       
        super(HzParties.class);
    }
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<HzParties> findCustomers(Integer id, String name,CustomerType type)
    {
        StringBuilder sb = new StringBuilder("SELECT h FROM HzParties h WHERE 1 = 1 ");
        
        if(name!=null && name.trim().length()>0){
            sb.append(" and UPPER(h.partyName) LIKE '%").append(name.toUpperCase()).append("%'");
        }
        if(id != null ){
            sb.append(" and h.partyId = ").append(id);
        }
        if(type != null){
            sb.append(" and h.partyTypeId = :type");
        }
        TypedQuery<HzParties> query = entityManager.createQuery(sb.toString(), HzParties.class);
        if(type != null) query.setParameter("type", type);
        return query .getResultList() ;
    }
    
    
    public HzParties findCustomerWithSitesById(Integer customerId)
    {
            return entityManager.createQuery("  SELECT h FROM HzParties h left join fetch h.hzPartySitesList "
                                           + "  WHERE h.partyId = :customerId " ,HzParties.class)
                  .setParameter("customerId", customerId)
                  .getSingleResult();
    }
    
    
    public boolean isNameExists(String name, CustomerType partyType){
        
        return ! entityManager.createQuery("select 1 from HzParties h where upper(h.partyName)= :name and h.partyTypeId = :type")
                      .setParameter("name", name.toUpperCase())
                      .setParameter("type", partyType )
                      .getResultList().isEmpty() ;                 
    }
    
    public List<HzParties> findByName(String text)
    {
       return entityManager.createQuery("SELECT h FROM HzParties h where upper(h.partyName)like upper(:name)")
               .setParameter("name", "%"+text+"%")
               .getResultList() ;
    }
    
    public List<HzPartySite> findSitesForCustomerByName(HzParties customer,String text)
    {
       return entityManager.createQuery("SELECT hs FROM HzPartySite hs "
               + "WHERE hs.party = :cust "
               + "AND UPPER(hs.partySiteName) LIKE UPPER(:text)")
               .setParameter("cust", customer)
               .setParameter("text", "%"+text+"%")
               .getResultList() ;
    }
    
    public HzPartySite findSiteById(int id)
    {
        return entityManager.find(HzPartySite.class, id) ;
    }
}
