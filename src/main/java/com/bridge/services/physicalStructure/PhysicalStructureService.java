/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.physicalStructure;

import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class PhysicalStructureService extends AbstractService<PhysicalStructure> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PhysicalStructureService() {
        super(PhysicalStructure.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<PhysicalStructure> findPhysicalStructure(String name, OrganizationUnit orgUnit) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM PhysicalStructure t WHERE 1=1 ");

        if (orgUnit != null) {
            queryString.append(" AND t.operatingUnitId = :unit");
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(t.physicalStrName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<PhysicalStructure> query = entityManager.createQuery(queryString.toString(), PhysicalStructure.class);

        if (orgUnit != null) {
            query.setParameter("unit", orgUnit);
        }

        return query.getResultList();
    }

    public PhysicalStructure findPhysicalStructureWithLevels(int id) {

        try {

            return entityManager.createQuery("SELECT p FROM PhysicalStructure p left join fetch p.physicalLevels l "
                    + "WHERE p.id = :id "
                    + "ORDER BY l.levelOrder", PhysicalStructure.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public PhysicalStructure findStructureWithLevel(int id) {

        try {

            return entityManager.createQuery("SELECT p FROM PhysicalStructure p left join fetch p.physicalLevels l "
                    + "WHERE p.operatingUnitId.orgUnitId = :id "
                    + "ORDER BY l.levelOrder", PhysicalStructure.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
