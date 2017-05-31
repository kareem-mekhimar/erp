/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.Job;
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
public class JobService extends AbstractService<Job> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public JobService() {
        super(Job.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Job> findJob(Integer jobid, String name, Integer stat) {
        StringBuilder queryString = new StringBuilder("SELECT jo FROM Job jo WHERE 1=1 ");

        if (jobid != null && jobid > 0) {
            queryString.append(" AND jo.jobId = ").append(jobid);
        }

        if (stat != null) {
            queryString.append(" AND jo.status = ").append(stat);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(jo.jobName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<Job> query = entityManager.createQuery(queryString.toString(), Job.class);

        return query.getResultList();
    }

    public List<Job> findByName(String text) {
        return entityManager.createQuery("SELECT o FROM Job o WHERE UPPER(o.jobName) LIKE UPPER(:text)", Job.class)
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from Job s where upper(s.jobName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();
    }

    public boolean isCodeExists(String code) {
        return !entityManager.createQuery("select 1 from Job s where upper(s.jobCode)= :code")
                .setParameter("code", code.toUpperCase())
                .getResultList().isEmpty();
    }

    public List<Job> findJob(Integer jobid, String name, Integer stat, String jobCode) {

        StringBuilder queryString = new StringBuilder("SELECT jo FROM Job jo WHERE 1=1 ");

        if (jobid != null && jobid > 0) {
            queryString.append(" AND jo.jobId = ").append(jobid);
        }

        if (stat != null) {
            queryString.append(" AND jo.status = ").append(stat);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(jo.jobName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        if (jobCode != null && !jobCode.isEmpty()) {
            queryString.append(" AND upper(jo.jobCode) LIKE '").append(jobCode.toUpperCase()).append("'");
        }

        TypedQuery<Job> query = entityManager.createQuery(queryString.toString(), Job.class);

        return query.getResultList();
    }

}
