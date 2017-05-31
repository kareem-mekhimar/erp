/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.categories;

import com.bridge.controllers.organization.OrganizationUnitController;
import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.AbstractService;
import java.math.BigDecimal;
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
public class FaCategoryService extends AbstractService<FaCategory> {

    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager;

    public FaCategoryService() {
        super(FaCategory.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<FaCategory> findAllParent() {
        String query = "SELECT m FROM FaCategory m WHERE m.parentCategoryId  is null";
        return entityManager.createQuery(query, FaCategory.class).getResultList();

    }
    
        public boolean isParent(Integer id) {
        String query = "SELECT m FROM FaCategory m WHERE m.parentCategoryId = "+id;
        return ! entityManager.createQuery(query, FaCategory.class).getResultList().isEmpty();

    }

    public List<FaCategory> findCategories(Integer catid, String catname, Integer enflag) {
        StringBuilder queryString = new StringBuilder("SELECT facat FROM FaCategory facat WHERE 1=1 ");

        if (catid != null) {
            queryString.append(" AND facat.categoryId =").append(catid);
        }

        if (enflag != null) {
            queryString.append(" AND facat.enabledFlagId = ").append(enflag);
        }

        if (catname != null && !catname.isEmpty()) {
            queryString.append(" AND upper(facat.categoryName) LIKE '%").append(catname.toUpperCase()).append("%'");
        }

        TypedQuery<FaCategory> query = entityManager.createQuery(queryString.toString(), FaCategory.class);

        return query.getResultList();
    }
    
    public List<FaCategory> findCategoryByName(String catname) {
        String queryString = "SELECT facat FROM FaCategory facat WHERE upper(facat.categoryName) LIKE UPPER('%"+catname+"%')";
        return entityManager.createQuery(queryString, FaCategory.class).getResultList();
    }
    
    public List<FaCategory> findCategoryByOrgAndName(OrganizationUnit org,String catname) {
        String queryString = "SELECT facat FROM FaCategory facat WHERE facat.operatingUnit = :org AND upper(facat.categoryName) LIKE UPPER('%"+catname+"%')";
        return entityManager.createQuery(queryString, FaCategory.class).setParameter("org", org).getResultList();
    }
    
    public List<FaCategory> findCategoryByOrg(OrganizationUnit org) {
        return entityManager.createQuery("SELECT facat FROM FaCategory facat WHERE facat.operatingUnit = :org", 
                FaCategory.class).setParameter("org", org).getResultList();
    }
    


    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from FaCategory s where upper(s.categoryName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public List<FaCategory> findAllCategories(String categoryCode,String catname, Boolean enflag) {
        
        StringBuilder queryString = new StringBuilder("SELECT cat FROM FaCategory cat WHERE 1=1 ");

        if (categoryCode != null) {
            queryString.append(" AND upper(cat.categoryCode) LIKE '%").append(categoryCode.toUpperCase()).append("%'");
        }
        
        if (enflag != null) {
            queryString.append(" AND cat.enabledFlagId = ").append(enflag);
        }

        if (catname != null && !catname.isEmpty()) {
            queryString.append(" AND upper(cat.categoryName) LIKE '%").append(catname.toUpperCase()).append("%'");
        }
        

        TypedQuery<FaCategory> query = entityManager.createQuery(queryString.toString(), FaCategory.class);


        return query.getResultList();
    }
    
    
}
