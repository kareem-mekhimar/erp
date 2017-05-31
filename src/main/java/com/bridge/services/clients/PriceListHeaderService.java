/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.clients;

/**
 *
 * @author Administrator
 */
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.clients.PriceListHeaders;
import com.bridge.entities.clients.PriceListLines;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.AssignmentType;

import com.bridge.services.AbstractService;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PriceListHeaderService extends AbstractService<PriceListHeaders> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PriceListHeaderService() {

        super(PriceListHeaders.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<PriceListHeaders> findPricingListsHeaders(Integer listHeaderId, String listHeaderName, Integer activeFlag) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT q FROM PriceListHeaders q WHERE q.listTypeId = 5 ");

        if (listHeaderName != null && !listHeaderName.isEmpty()) {
            sb.append(" AND UPPER(q.listHeaderName) LIKE UPPER('%").append(listHeaderName).append("%') ");
        }
        if (listHeaderId != null) {
            sb.append(" AND q.listHeaderId = ").append(listHeaderId);
        }
        if (activeFlag != null) {
            sb.append(" AND q.activeFlag = ").append(activeFlag);
        }

        System.err.println("Query: " + sb.toString());
        return entityManager.createQuery(sb.toString()).getResultList();

    }

    public List<PriceListHeaders> findPricingListsByName(String text) {
        return entityManager.createQuery("SELECT q FROM PriceListHeaders q WHERE q.listTypeId = :listTypeId and UPPER(q.listHeaderName) LIKE UPPER(:text)")
                .setParameter("listTypeId", 5)
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public PriceListHeaders findHeaderWithLinesByHeaderId(Integer headerId) {
        try {

            return entityManager.createQuery("  SELECT q FROM PriceListHeaders q left join fetch q.priceListLines "
                    + "  WHERE q.listHeaderId = :headerId and q.listTypeId = :listTypeId ", PriceListHeaders.class)
                    .setParameter("listTypeId", 5).setParameter("headerId", headerId).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<SystemItem> findItemsInPriceList(Integer site, String name) {

        return entityManager.createQuery("SELECT q.systemItem FROM PriceListLines q  WHERE UPPER(q.systemItem.description) LIKE UPPER(:name) AND "
                + "q.priceListHeader IN (SELECT s.customerAcc.priceListId FROM ClientSite s WHERE s.siteId = :site)", SystemItem.class)
                .setParameter("name", "'%" + name + "%'")
                .setParameter("site", site)
                .getResultList();
    }

    public BigDecimal findItemPrice(Integer site, SystemItem item) {
        
        try {
            
            return entityManager.createQuery("SELECT q.systemItem FROM PriceListLines q  WHERE UPPER(q.systemItem.description) = :item AND "
                    + "q.priceListHeader IN (SELECT s.customerAcc.priceListId FROM ClientSite s WHERE s.siteId = :site)", BigDecimal.class)
                    .setParameter("item", item).setParameter("site", site).getSingleResult();
            
        } catch (Exception e) {
            
            return BigDecimal.ZERO;
            
        }

    }

    public List<PriceListLines> findPriceListLines(Integer site) {

        return entityManager.createQuery("SELECT q FROM PriceListLines q  WHERE q.priceListHeader IN "
                + "(SELECT s.customerAcc.priceListId FROM ClientSite s WHERE s.siteId = :site)", PriceListLines.class)
                .setParameter("site", site)
                .getResultList();
    }

    public void removeLines(String lineIds) {
        entityManager.createQuery("DELETE FROM PriceListLines line WHERE line.listLineId IN (" + lineIds + ")")
                .executeUpdate();
    }

}
