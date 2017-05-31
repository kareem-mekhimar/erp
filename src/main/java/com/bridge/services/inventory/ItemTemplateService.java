/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.inventory.ItemTemplate;
import com.bridge.enums.SystemItemType;
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
public class ItemTemplateService extends AbstractService<ItemTemplate> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public ItemTemplateService() {
        super(ItemTemplate.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<ItemTemplate> findItemTemplate(String name, SystemItemType type, Boolean status) {
        StringBuilder queryString = new StringBuilder("SELECT s FROM ItemTemplate s WHERE 1=1 ");

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(s.templateName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        if (type != null) {
            queryString.append(" AND upper(s.itemTypeId) = :type");
        }

        if (status != null) {
            queryString.append(" AND s.enabledFlagId = ").append(status);
        }

        TypedQuery<ItemTemplate> query = entityManager.createQuery(queryString.toString(), ItemTemplate.class);

        if (type != null) {
            query.setParameter("type", type);
        }

        return query.getResultList();
    }

    public List<ItemTemplate> findByName(String text) {
        return entityManager.createQuery("SELECT s FROM ItemTemplate s WHERE UPPER(s.templateName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<ItemTemplate> findByNameAndType(String text, SystemItemType itemType) {
        return entityManager.createQuery("SELECT s FROM ItemTemplate s "
                + "WHERE UPPER(s.templateName) LIKE UPPER(:text) AND s.itemType = :type", ItemTemplate.class)
                .setParameter("text", "%" + text + "%")
                .setParameter("type", itemType)
                .getResultList();
    }

    public ItemTemplate findTemplateWithCategories(Integer id) {
        try {
            
            return entityManager.createQuery("SELECT s FROM ItemTemplate s left join fetch s.categoryList "
                + "WHERE s.templateId =:id", ItemTemplate.class).setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            
            return null;
            
        }
        
    }

    public List<ItemTemplate> findTemplatesByNameAndType(String text, SystemItemType itemType) {
        return entityManager.createQuery("SELECT s FROM ItemTemplate s "
                + "WHERE UPPER(s.templateName) LIKE UPPER(:text) AND s.itemTypeId = :type", ItemTemplate.class)
                .setParameter("text", "%" + text + "%")
                .setParameter("type", itemType)
                .getResultList();
    }

}
