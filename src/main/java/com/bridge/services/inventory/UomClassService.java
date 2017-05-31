/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.inventory.UomClass;
import com.bridge.services.AbstractService;
import java.util.Date;
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
public class UomClassService extends AbstractService<UomClass> {

    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager;

    public UomClassService() {
        super(UomClass.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<UomClass> findUomClass(Integer var1, String var2, Integer var3) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM UomClass t WHERE 1=1 ");

        if (var1 != null && var1 > 0) {
            queryString.append(" AND t.classId = ").append(var1);
        }

        if (var3 != null) {
            queryString.append(" AND t.classStatus = ").append(var3);
        }

        if (var2 != null && !var2.isEmpty()) {
            queryString.append(" AND upper(t.className) LIKE '%").append(var2.toUpperCase()).append("%'");
        }

        TypedQuery<UomClass> query = entityManager.createQuery(queryString.toString(), UomClass.class);

        return query.getResultList();
    }

    public List<UomClass> findUomClass(Integer var1, String var2, Integer var3, String classCode) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM UomClass t WHERE 1=1 ");

        if (var1 != null && var1 > 0) {
            queryString.append(" AND t.classId = ").append(var1);
        }

        if (var3 != null) {
            queryString.append(" AND t.classStatus = ").append(var3);
        }

        if (var2 != null && !var2.isEmpty()) {
            queryString.append(" AND upper(t.className) LIKE '%").append(var2.toUpperCase()).append("%'");
        }
        
        if (classCode != null && !classCode.isEmpty()) {
            queryString.append(" AND upper(t.classCode) LIKE '%").append(classCode.toUpperCase()).append("%'");
        }

        TypedQuery<UomClass> query = entityManager.createQuery(queryString.toString(), UomClass.class);

        return query.getResultList();
    }

    
    public UomClass findUomWithLines(int id) {
        
        try {
            
                 return entityManager.createQuery("SELECT p FROM UomClass p left join fetch p.uomLines WHERE p.classId = :id", UomClass.class)
                .setParameter("id", id).getSingleResult();  
                 
        } catch (Exception e) {
            
            return null;
            
        }
 
    }

    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from UomClass t where upper(t.className)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }
    public boolean isUomNameExists(String name) {
        return !entityManager.createQuery("select 1 from UomClass t where upper(t.className)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }
    public boolean isUomCodeExists(String name) {
        return !entityManager.createQuery("select 1 from UomClass t where upper(t.className)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

}
