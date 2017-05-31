/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.physicalStructure;

import com.bridge.entities.physicalStructure.PhysicalLevel;
import com.bridge.entities.physicalStructure.PhysicalLevelDetail;
import com.bridge.entities.physicalStructure.PhysicalStructure;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class PhysicalLevelService extends AbstractService<PhysicalLevel> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PhysicalLevelService() {
        super(PhysicalLevel.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public PhysicalLevelDetail findDetailById(Integer id) {
        
        try {

            return entityManager.createQuery("SELECT p FROM PhysicalLevelDetail p  WHERE p.id = :id", PhysicalLevelDetail.class)
                    .setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public PhysicalLevel findPhysicalLevelWithDetails(int id) {

        try {

            return entityManager.createQuery("SELECT p FROM PhysicalLevel p left join fetch p.LevelDetails WHERE p.levelId = :id", PhysicalLevel.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException exception) {
            
            return null;
        }
    }

    public List<PhysicalLevel> findLevelsByName(String name) {

        return entityManager.createQuery("SELECT l FROM PhysicalLevel l WHERE UPER(l.levelName) LIKE UPER('% :name %')")
                .setParameter("name", name).getResultList();
    }

    public List<PhysicalLevelDetail> findDependsLevelDetail(Integer orderNo, PhysicalStructure structure, String text) {

        return entityManager.createQuery("SELECT c FROM PhysicalLevelDetail c "
                + "WHERE UPPER(c.detailName) LIKE UPPER(:text) "
                + "AND c.levelId.levelOrder = :orderNo "
                + "AND c.levelId.physicalStrId = :structure")
                .setParameter("structure", structure)
                .setParameter("orderNo", orderNo)
                .setParameter("text", "%" + text + "%")
                .getResultList();

    }

}
