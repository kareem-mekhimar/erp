/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

import com.bridge.entities.clients.ClPrepaymentsTransaction;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ClientType;
import com.bridge.enums.PaymentTxType;
import com.bridge.services.AbstractService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class PrePaymentService extends AbstractService<ClPrepaymentsTransaction>{
   
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
   
    public PrePaymentService()
    {
        super(ClPrepaymentsTransaction.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
    
        return entityManager ;
    }
    
    public BigDecimal findAmount(OrganizationUnit unit,Client client , ClientSite clientSite , ClientType type , FndCurrency currency)
    {

        BigDecimal sum =  entityManager.createQuery("SELECT SUM(t.transactionAmount) FROM ClPrepaymentsTransaction t "
                + "WHERE t.operatingUnit = :unit "
                + "AND t.client = :client "
                + "AND t.clientSite = :site "
                + "AND t.currency = :currency "
                + "AND t.clientType = :type" , BigDecimal.class)
                .setParameter("unit", unit)
                .setParameter("client", client)
                .setParameter("site", clientSite)
                .setParameter("type", type)
                .setParameter("currency", currency)
                .getSingleResult() ;
        
        if(sum == null)
            
            return BigDecimal.ZERO;
        
        return sum ;
            
    }
    
   
    public List<ClientPrePaymentDTO> findPrePaymentsByOperatingOrClient(OrganizationUnit org , Client client)
    {
        StringBuilder builder = new StringBuilder("SELECT NEW com.bridge.services.clients.ClientPrePaymentDTO(t.operatingUnit.orgUnitName"
               + ",t.client.clientName,t.clientSite.siteName,"
               + "( SUM( CASE WHEN (t.clientType = com.bridge.enums.ClientType.SUPPLIER ) THEN t.transactionAmount ELSE 0 END ) ) ,"
                + "( SUM( CASE WHEN (t.clientType = com.bridge.enums.ClientType.CUSTOMER ) THEN t.transactionAmount ELSE 0 END ) ) ,"
               + "t.currency.currencyCode ) "
               + "FROM ClPrepaymentsTransaction t "
               + "WHERE 1 = 1 ");
        
        if(org != null)
            
            builder.append(" AND t.operatingUnit = :org ") ;
        
        if(client != null)
            
            builder.append(" AND t.client = :client ");
        
        builder.append(" GROUP BY t.operatingUnit.orgUnitName,t.client.clientName,t.clientSite.siteName,t.currency.currencyCode") ;
        
        TypedQuery<ClientPrePaymentDTO> query = entityManager.createQuery(builder.toString(),ClientPrePaymentDTO.class) ;
        
        Optional.ofNullable(org).ifPresent(i -> query.setParameter("org", org) );
       
        Optional.ofNullable(client).ifPresent(i -> query.setParameter("client", client));
       
        return query.getResultList();
    }
}
