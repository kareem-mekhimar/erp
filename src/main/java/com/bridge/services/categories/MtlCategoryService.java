/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.categories;

import com.bridge.entities.inventory.MtlCategory;
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
public class MtlCategoryService extends AbstractService<MtlCategory> {

    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager;

    public MtlCategoryService() {
        super(MtlCategory.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<MtlCategory> findAllParent() {
        String query = "SELECT m FROM MtlCategory m WHERE m.parentCategoryId  is null";
        return entityManager.createQuery(query, MtlCategory.class).getResultList();

    }
    
    public boolean isParent(Integer id) {
        String query = "SELECT m FROM MtlCategory m WHERE m.parentCategoryId = "+id;
        return ! entityManager.createQuery(query, MtlCategory.class).getResultList().isEmpty();

    }
    

    public List<MtlCategory> findCategories(Integer catid, String catname, Integer enflag) {
        StringBuilder queryString = new StringBuilder("SELECT mtlcat FROM MtlCategory mtlcat WHERE 1=1 ");

        if (catid != null) {
            queryString.append(" AND mtlcat.categoryId =").append(catid);
        }

        if (enflag != null) {
            queryString.append(" AND mtlcat.enabledFlagId = ").append(enflag);
        }

        if (catname != null && !catname.isEmpty()) {
            queryString.append(" AND upper(mtlcat.categoryName) LIKE '%").append(catname.toUpperCase()).append("%'");
        }

        TypedQuery<MtlCategory> query = entityManager.createQuery(queryString.toString(), MtlCategory.class);

        return query.getResultList();
    }
    
    public List<MtlCategory> findCategoryByName(String catname) {
        String queryString = "SELECT mtlcat FROM MtlCategory mtlcat WHERE upper(mtlcat.categoryName) LIKE UPPER('%"+catname+"%')";
        return entityManager.createQuery(queryString, MtlCategory.class).getResultList();
    }

    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from MtlCategory s where upper(s.categoryName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public List<MtlCategory> findAllCategories(String categoryCode,String catname, Boolean enflag) {
        
        StringBuilder queryString = new StringBuilder("SELECT cat FROM MtlCategory cat WHERE 1=1 ");

        if (categoryCode != null) {
            queryString.append(" AND upper(cat.categoryCode) LIKE '%").append(categoryCode.toUpperCase()).append("%'");
        }
        
        if (enflag != null) {
            queryString.append(" AND cat.enabledFlagId = ").append(enflag);
        }

        if (catname != null && !catname.isEmpty()) {
            queryString.append(" AND upper(cat.categoryName) LIKE '%").append(catname.toUpperCase()).append("%'");
        }
        

        TypedQuery<MtlCategory> query = entityManager.createQuery(queryString.toString(), MtlCategory.class);


        return query.getResultList();
    }
    
    
}
