/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.inventory.MainInventory;
import com.bridge.services.AbstractService;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kareem
 */
@Stateless
public class MainInvService extends AbstractService<MainInventory> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public MainInvService() {
        super(MainInventory.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<MainInventory> findMainInv(Integer var1, String var2, String var3) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM MainInventory t WHERE 1=1 ");

        if (var1 != null && var1 > 0) {
            queryString.append(" AND t.organizationId = ").append(var1);
        }

        if (var2 != null) {
            queryString.append(" AND upper(t.organizationCode) LIKE '%").append(var2.toUpperCase()).append("%'");
        }

        if (var3 != null && !var3.isEmpty()) {
            queryString.append(" AND upper(t.organizationName) LIKE '%").append(var3.toUpperCase()).append("%'");
        }

        TypedQuery<MainInventory> query = entityManager.createQuery(queryString.toString(), MainInventory.class);

        return query.getResultList();
    }

    public List<MainInventory> findByName(String name) {
        return entityManager.createQuery("SELECT i FROM MainInventory i WHERE UPPER(i.organizationName) LIKE UPPER(:text)")
                .setParameter("text", "%" + name + "%")
                .getResultList();
    }

    public MainInventory findByNameEquals(String name) {
        try {

            return entityManager.createQuery("SELECT i FROM MainInventory i WHERE i.organizationName = :text", MainInventory.class)
                    .setParameter("text", name)
                    .getSingleResult();

        } catch (Exception e) {

            return null;
        }

    }

    public MainInventory findMainWithSubInvs(Integer id) {
        try {

            return entityManager.createQuery("SELECT i FROM MainInventory i LEFT JOIN FETCH i.secondaryInventoryList "
                    + "WHERE i.organizationId = :id", MainInventory.class).setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public MainInventory findById(Object id) {

        HashMap map = new HashMap();

        EntityGraph entityGraph = entityManager.getEntityGraph("mainInvGraph");

        map.put("javax.persistence.fetchgraph", entityGraph);

        return entityManager.find(MainInventory.class, id, map);
    }

    @Override
    public MainInventory update(MainInventory inv) {
        inv.setGlId(inv.getOperatingUnit().getGlId());
        return entityManager.merge(inv);
    }

    public List<MainInventory> findByNameAndParentOperatingUnitId(String name, int operatingId) {
        return entityManager.createQuery("SELECT m FROM MainInventory m "
                + "WHERE m.operatingUnit.orgUnitId = :operatingId "
                + "AND UPPER(m.organizationName) LIKE UPPER(:text)")
                .setParameter("text", "%" + name + "%")
                .setParameter("operatingId", operatingId)
                .getResultList();
    }
}
