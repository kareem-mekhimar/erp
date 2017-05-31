/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.coa;

import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.enums.LedgerObjectTypeCode;
import com.bridge.services.AbstractService;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */
@Stateless
public class COAAccountService extends AbstractService<GlCodeCombination> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    @Inject
    private CoaStructureService structureService;

    public COAAccountService() {

        super(GlCodeCombination.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public GlCodeCombination findAccountWithSegments(Integer id) {
        
        try {

            return entityManager.createQuery("SELECT a FROM GlCodeCombination a left join fetch a.combinationSegmentList"
                    + " WHERE a.codeCombinationId =:id", GlCodeCombination.class).setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<GlCodeCombination> findByAlias(String text) {
        return entityManager.createQuery("SELECT a FROM GlCodeCombination a "
                + " WHERE UPPER(a.codeCombinationShortName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<GlCodeCombination> findByAliasAndNotIn(String text, String ids) {
        return entityManager.createQuery("SELECT a FROM GlCodeCombination a "
                + " WHERE UPPER(a.codeCombinationShortName) LIKE UPPER(:text) "
                + " AND a.codeCombinationId NOT IN(" + ids + ")")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<GlCodeCombination> findByCombinationCode(String text) {
        return entityManager.createQuery("SELECT a FROM GlCodeCombination a "
                + " WHERE UPPER(a.codeCombinationShortName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<GlCodeCombination> findByAliasOrCoaId(String text, Integer coaId) {
        StringBuilder queryString = new StringBuilder("SELECT a FROM GlCodeCombination a  WHERE 1 = 1 ");

        if (coaId != null) {
            queryString.append(" AND a.coaStructure.coaId = ").append(coaId);
        }

        if (text != null && !text.isEmpty()) {
            queryString.append(" AND UPPER(a.codeCombinationShortName) LIKE UPPER(:text)");
        }

        queryString.append(" order by a");

        TypedQuery<GlCodeCombination> query = entityManager.createQuery(queryString.toString(), GlCodeCombination.class);

        Optional.ofNullable(text).ifPresent(s -> query.setParameter("text", "%" + text + "%"));

        return query.getResultList();
    }

    public List<GlCodeCombination> findByCoa(CoaStructure coaStructure) {
        
        return entityManager.createQuery("SELECT a FROM GlCodeCombination a "
                + "WHERE a.coaStructure = :coa "
                + "ORDER BY a")
                .setParameter("coa", coaStructure)
                .getResultList();
    }

    public List<GlCodeCombination> findByLedgerAndAlias(Integer id, String name) {
        System.out.println("com.bridge.services.coa.COAAccountService.findByLedgerAndAlias()" + id);
        return entityManager.createQuery(
                "SELECT c FROM GlCodeCombination c WHERE UPPER(c.codeCombinationShortName) LIKE UPPER(:name) AND c.coaStructure = "
                + "(SELECT  l.coa FROM GlLedger l WHERE l.ledgerId = :id)")
                .setParameter("id", id)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public boolean isCombinationExists(String combination) {
        try {

            entityManager.createQuery("SELECT 1 FROM GlCodeCombination g "
                    + "WHERE g.codeCombination = :comb")
                    .setParameter("comb", combination)
                    .getSingleResult();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAliasExists(String name) {
        return !entityManager.createQuery("select 1 from GlCodeCombination t where upper(t.codeCombinationShortName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public void update(List<GlCodeCombination> accounts) {

        accounts.forEach(a -> entityManager.merge(a));
    }

}
