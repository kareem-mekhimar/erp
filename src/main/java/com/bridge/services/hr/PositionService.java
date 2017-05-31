/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.Location;
import com.bridge.entities.hr.Position;
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
public class PositionService extends AbstractService<Position> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PositionService() {
        super(Position.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Position> findPosition(Integer posId, String name) {
        StringBuilder queryString = new StringBuilder("SELECT pos FROM Position pos WHERE 1=1 ");

        if (posId != null && posId > 0) {
            queryString.append(" AND pos.positionId = ").append(posId);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(pos.positionName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<Position> query = entityManager.createQuery(queryString.toString(), Position.class);

        return query.getResultList();
    }

    public List<Position> findPositionByName(String name) {
        return entityManager.createQuery("SELECT s FROM Position s WHERE UPPER(s.positionName) LIKE UPPER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public boolean isNameExists(String name) {

        return !entityManager.createQuery("select 1 from Position o where upper(o.positionName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();
    }

    public List<Position> findPosition(Integer posId, String name, String positionCode) {
        StringBuilder queryString = new StringBuilder("SELECT pos FROM Position pos WHERE 1=1 ");

        if (posId != null && posId > 0) {
            queryString.append(" AND pos.positionId = ").append(posId);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(pos.positionName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        if (positionCode != null && !positionCode.isEmpty()) {
            queryString.append(" AND upper(pos.positionCode) LIKE '%").append(positionCode.toUpperCase()).append("%'");
        }

        TypedQuery<Position> query = entityManager.createQuery(queryString.toString(), Position.class);

        return query.getResultList();
    }

    public boolean isPositionCodeExists(String positionCode) {
        return !entityManager.createQuery("select 1 from Position o where upper(o.positionCode)= :positionCode")
                .setParameter("positionCode", positionCode.toUpperCase())
                .getResultList().isEmpty();
    }

}
