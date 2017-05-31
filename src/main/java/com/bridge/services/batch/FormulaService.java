/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.batch;

import com.bridge.entities.batch.Formula;
import com.bridge.entities.batch.FormulaLines;
import com.bridge.enums.FormulaStatus;
import com.bridge.services.AbstractService;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class FormulaService extends AbstractService<Formula> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public FormulaService() {
        super(Formula.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Formula> findFormula(String fmcode, String fmname, FormulaStatus status) {
        StringBuilder queryString = new StringBuilder("SELECT fm FROM Formula fm WHERE 1=1 ");

        if (status != null) {
            queryString.append(" AND fm.status = :status");
        }

        if (fmcode != null) {
            queryString.append(" AND upper(fm.formulaNo) LIKE '%").append(fmcode.toUpperCase()).append("%'");
        }

        if (fmname != null && !fmname.isEmpty()) {
            queryString.append(" AND upper(fm.formulaName) LIKE '%").append(fmname.toUpperCase()).append("%'");
        }

        TypedQuery<Formula> query = entityManager.createQuery(queryString.toString(), Formula.class);

        Optional.ofNullable(status).ifPresent(s -> query.setParameter("status", s));

        return query.getResultList();
    }

    public List<Formula> findFormula(Integer formulaId, String fmname, FormulaStatus status, String formulaNo) {
        StringBuilder queryString = new StringBuilder("SELECT fm FROM Formula fm WHERE 1=1 ");

        if (status != null) {
            queryString.append(" AND fm.status = :status");
        }

        if (formulaId != null) {
            queryString.append(" AND upper(fm.formulaId) =").append(formulaId);
        }

        if (fmname != null && !fmname.isEmpty()) {
            queryString.append(" AND upper(fm.formulaName) LIKE '%").append(fmname.toUpperCase()).append("%'");
        }

        if (formulaNo != null && !formulaNo.isEmpty()) {
            queryString.append(" AND upper(fm.formulaNo) LIKE '%").append(formulaNo.toUpperCase()).append("%'");
        }

        TypedQuery<Formula> query = entityManager.createQuery(queryString.toString(), Formula.class);

        Optional.ofNullable(status).ifPresent(s -> query.setParameter("status", s));

        return query.getResultList();
    }

    public List<Formula> findByName(String text) {
        return entityManager.createQuery("SELECT f FROM Formula f "
                + " WHERE UPPER(f.formulaName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<Formula> findApprovedByName(String text) {
        try {

            return entityManager.createQuery("SELECT f FROM Formula f WHERE UPPER(f.formulaName) LIKE UPPER(:text)"
                    + "  AND f.status = com.bridge.enums.FormulaStatus.APPROVED").setParameter("text", "%" + text + "%").getResultList();

        } catch (Exception e) {

            return null;

        }

    }

    public Formula findFormulaWithLines(Integer id) {
        
        try {
            
            return entityManager.createQuery("SELECT f FROM Formula f left join fetch f.formulaLines WHERE f.formulaId = :id", Formula.class)
                    .setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<FormulaLines> findLinesById(Integer id) {
        return entityManager.createQuery("SELECT f FROM FormulaLines f WHERE f.formula.formulaId = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public FormulaLines findLineByLineId(Integer id) {
        return entityManager.find(FormulaLines.class, id);
    }

    public boolean isCodeExists(String code) {
        return !entityManager.createQuery("select 1 from Formula s where upper(s.formulaNo)= :code")
                .setParameter("code", code.toUpperCase())
                .getResultList().isEmpty();

    }

    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from Formula s where upper(s.formulaName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public void approveFormulas(String idsDelmited) {
        entityManager.createQuery("UPDATE Formula f set  f.status = :stat " + "WHERE f.formulaId in (" + idsDelmited + ")")
                .setParameter("stat", FormulaStatus.APPROVED)
                .executeUpdate();
    }

    public void unApproveFormulas(String idsDelmited) {
        entityManager.createQuery("UPDATE Formula f set  f.status = :stat " + "WHERE f.formulaId in (" + idsDelmited + ")")
                .setParameter("stat", FormulaStatus.NEW)
                .executeUpdate();
    }

    public List<Formula> findFormulaByStatus(FormulaStatus stat) {
        return entityManager.createQuery("SELECT f FROM Formula f WHERE f.status = :stat")
                .setParameter("stat", stat)
                .getResultList();
    }

    public List<Formula> findFormulaWithLinesByName(String text) {
        return entityManager.createQuery("SELECT DISTINCT f FROM Formula f JOIN FETCH f.formulaLines l"
                + " WHERE UPPER(f.formulaName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<FormulaLines> findProductLinesForApprovedFormulaBySystemItemNameAndNotIn(String text, String idsDelimited) {

        return entityManager.createQuery("SELECT f FROM FormulaLines f "
                + "WHERE f.formula.status = com.bridge.enums.FormulaStatus.APPROVED "
                + "AND f.lineType = com.bridge.enums.FormulaLineType.PRODUCT "
                + "AND UPPER(f.systemItem.description) LIKE UPPER(:text) "
                + "AND f.systemItem.inventoryItemId NOT IN(" + idsDelimited + ")")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public void removeLines(String lineIds) {
        entityManager.createQuery("DELETE FROM FormulaLines line WHERE line.formulalineId IN (" + lineIds + ")")
                .executeUpdate();
    }
}
