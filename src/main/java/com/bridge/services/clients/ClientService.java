/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientContact;
import com.bridge.entities.clients.ClientSite;
import com.bridge.enums.ClientType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class ClientService extends AbstractService<Client> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public ClientService() {

        super(Client.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<Client> findClients(Integer clientId, String clientName, Integer status) {
        StringBuilder sb = new StringBuilder("SELECT s FROM Client s WHERE 1 = 1 ");

        if (clientName != null && !clientName.isEmpty()) {
            sb.append(" AND UPPER(s.clientName) LIKE '%").append(clientName.toUpperCase()).append("%'");
        }
        if (clientId != null) {
            sb.append(" AND s.clientId = ").append(clientId);
        }
        if (status != null) {
            sb.append(" AND s.status = ").append(status);
        }

        sb.append(" ORDER BY s.clientId");

        TypedQuery<Client> query = entityManager.createQuery(sb.toString(), Client.class);

        return query.getResultList();
    }

    public Client findClientDetails(Integer id) {
        try {
            
            return entityManager.createQuery("SELECT s FROM Client s left join fetch s.siteList"
                    + " WHERE s.clientId = :id", Client.class).setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            
            return null;
            
        }

    }

    public List<Client> findByName(ClientType type, String text) {
        String s = "";
        if (type == ClientType.CUSTOMER) {
            s = " AND s.customerFlag = true";
        }
        if (type == ClientType.SUPPLIER) {
            s = " AND s.supplierFlag = true";
        }

        return entityManager.createQuery("SELECT s FROM Client s WHERE 1=1"
                + s
                + " AND UPPER(s.clientName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<Client> findByName(String text)
    {
       return entityManager.createQuery("SELECT c FROM Client c "
               + "WHERE UPPER(c.clientName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();

    }
    
    
    public List<Client> findByType(ClientType type) {
        String s = "";
        if (type == ClientType.CUSTOMER) {
            s = " AND s.customerFlag = true";
        }
        if (type == ClientType.SUPPLIER) {
            s = " AND s.supplierFlag = true";
        }

        return entityManager.createQuery("SELECT s FROM Client s WHERE 1=1" + s)
                .getResultList();
    }

}
