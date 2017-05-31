/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.Department;
import com.bridge.entities.po.PoHeader;
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
public class DepartmentService extends AbstractService<Department> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public DepartmentService() {
        super(Department.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<Department> findDepartment(Integer departid, String name, Integer stat) {
        StringBuilder queryString = new StringBuilder("SELECT dep FROM Department dep WHERE 1=1 ");

        if (departid != null && departid > 0) {
            queryString.append(" AND dep.departmentId = ").append(departid);
        }

        if (stat != null) {
            queryString.append(" AND dep.status = ").append(stat);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(dep.departmentName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<Department> query = entityManager.createQuery(queryString.toString(), Department.class);

        return query.getResultList();
    }

    public boolean isNameExists(String name) {

        return !entityManager.createQuery("select 1 from Department s where upper(s.departmentName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();
    }

    public boolean isDepartmentCodeExists(String code) {

        return !entityManager.createQuery("select 1 from Department s where upper(s.departmentCode)= :code")
                .setParameter("code", code.toUpperCase())
                .getResultList().isEmpty();
    }

    public List<Department> findDepartment(Integer departid, String name, Integer stat, String departmentCode) {
        StringBuilder queryString = new StringBuilder("SELECT dep FROM Department dep WHERE 1=1 ");

        if (departid != null && departid > 0) {
            queryString.append(" AND dep.departmentId = ").append(departid);
        }

        if (stat != null) {
            queryString.append(" AND dep.status = ").append(stat);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(dep.departmentName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        if (departmentCode != null && !departmentCode.isEmpty()) {
            queryString.append(" AND upper(dep.departmentCode) LIKE '%").append(departmentCode.toUpperCase()).append("%'");
        }

        TypedQuery<Department> query = entityManager.createQuery(queryString.toString(), Department.class);

        return query.getResultList();
    }
}
