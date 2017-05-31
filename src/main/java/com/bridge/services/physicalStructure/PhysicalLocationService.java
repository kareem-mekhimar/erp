/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.physicalStructure;

import com.bridge.entities.physicalStructure.PhysicalLevelDetail;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class PhysicalLocationService extends AbstractService<PhysicalLocation> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PhysicalLocationService() {
        super(PhysicalLocation.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<PhysicalLocation> findByOrganizationAndName(Integer id, String name) {

        return entityManager.createQuery("SELECT i FROM PhysicalLocation i WHERE i.organizationId = " + id
                + " AND UPPER(i.locationName) LIKE UPPER(:text)")
                .setParameter("text", "%" + name + "%")
                .getResultList();

    }

    public List<PhysicalLocation> findByOrganizationAndDesc(Integer id, String name) {

        return entityManager.createQuery("SELECT i FROM PhysicalLocation i WHERE i.organizationId = " + id
                + " AND UPPER(i.locationDescription) LIKE UPPER(:text)")
                .setParameter("text", "%" + name + "%")
                .getResultList();

    }

    public PhysicalLocation findLocationWithDetails(Integer id) {

        try {

            return entityManager.createQuery("SELECT a FROM PhysicalLocation a left join fetch a.levelDetails"
                    + " WHERE a.locationId =:id", PhysicalLocation.class).setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<PhysicalLevelDetail> findLevelDetailsByOrderAndStructureAndConntains(Integer orderNo, PhysicalStructure struc, String text) {

        return entityManager.createQuery("SELECT l FROM PhysicalLevelDetail l "
                + "WHERE UPPER(l.detailName) LIKE UPPER(:text) "
                + "AND l.levelId.levelOrder = :orderNo "
                + "AND l.levelId.physicalStrId = :struc")
                .setParameter("struc", struc)
                .setParameter("orderNo", orderNo)
                .setParameter("text", "%" + text + "%")
                .getResultList();

    }

    public List<PhysicalLocation> findLocationsForOperatingUnit(Integer id) {

        return entityManager.createQuery("SELECT i FROM PhysicalLocation i WHERE i.organizationId = " + id, PhysicalLocation.class)
                .getResultList();

    }

    public void update(List<PhysicalLocation> locations) {

        locations.forEach(a -> entityManager.merge(a));
    }
}
