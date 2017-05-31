/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.organization;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */
@Stateless
public class OrganizationUnitService extends AbstractService<OrganizationUnit> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public OrganizationUnitService() {

        super(OrganizationUnit.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<OrganizationUnit> findOrgUnit(Integer id, String name, Integer status, Integer parent, OrganizationUnitType unitType) {

        StringBuilder queryString = new StringBuilder("SELECT t FROM OrganizationUnit t WHERE t.unitType = :type");

        if (id != null && id > 0) {
            queryString.append(" AND t.orgUnitId = ").append(id);
        }
        if (parent != null) {
            queryString.append(" AND t.parentUnitId =  :parent");
        }

        if (status != null) {
            queryString.append(" AND t.enabledFlagId = ").append(status);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(t.orgUnitName) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<OrganizationUnit> query = entityManager.createQuery(queryString.toString(), OrganizationUnit.class);

        query.setParameter("type", unitType);

        if (parent != null) {
            query.setParameter("parent", parent);
        }

        return query.getResultList();
    }

    public List<OrganizationUnit> findOrganizationByName(String text) {
        return entityManager.createQuery("SELECT s FROM OrganizationUnit s WHERE UPPER(s.orgUnitName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public List<OrganizationUnit> findByOrgType(OrganizationUnitType type) {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type", OrganizationUnit.class)
                .setParameter("type", type)
                .getResultList();
    }
    
    public List<OrganizationUnit> findLegalHasLedger() {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type AND o.glId > 0", OrganizationUnit.class)
                .setParameter("type", OrganizationUnitType.LEGAL_ENTITY)
                .getResultList();
    }
    
    //not used
    public List<OrganizationUnit> findLegalInLedger(Integer ledgerId) {
        // test query in OrganizationUnit 
        //wait ledger Config finish
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type AND o.orgUnitId IN "
                + "(SELECT a.objectId FROM GlLedgerConfigDetail a WHERE a.configurationId IN "
                + "(SELECT b.configurationId FROM GlLedgerConfigDetail b WHERE b.objectId = :id))", OrganizationUnit.class)
                .setParameter("id", ledgerId)
                .setParameter("type", OrganizationUnitType.LEGAL_ENTITY)
                .getResultList();
    }
    
    public List<OrganizationUnit> findOperatingInLedger(Integer ledgerId) {

        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type AND o.glId = :id", OrganizationUnit.class)
                .setParameter("id", ledgerId)
                .setParameter("type", OrganizationUnitType.OPERATING_UNIT)
                .getResultList();
    }
    
    
    public List<OrganizationUnit> findByOrgTypeAndOrgParent(OrganizationUnitType type,Integer id) {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE"
                +" o.parentUnitId = :id", OrganizationUnit.class)
//                .setParameter("type", type)
                .setParameter("id", id)
                .getResultList();
    }
    
    public List<OrganizationUnit> findByOrgNameAndOrgParent(String name,Integer id) {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE"
                +" UPPER(o.orgUnitName) LIKE UPPER(:name) AND"
                +" o.parentUnitId = :id", OrganizationUnit.class)
                .setParameter("name", "%"+name+"%")
                .setParameter("id", id)
                .getResultList();
    }

    public boolean isNameExists(String string) {

        return !entityManager.createQuery("select 1 from OrganizationUnit o where upper(o.orgUnitName)= :name")
                .setParameter("name", string.toUpperCase())
                .getResultList().isEmpty();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isCodeExists(String string) {

        return !entityManager.createQuery("select 1 from OrganizationUnit o where upper(o.orgCode)= :name")
                .setParameter("name", string.toUpperCase())
                .getResultList().isEmpty();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<OrganizationUnit> findByOrgTypeByName(OrganizationUnitType type, String text) {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type "
                + " AND UPPER(o.orgUnitName) LIKE UPPER(:text)", OrganizationUnit.class)
                .setParameter("type", type)
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }
    
    
    public List<OrganizationUnit> findByOrgTypeByNameNotHaveLedger(OrganizationUnitType type, String text) {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type "
                + " AND UPPER(o.orgUnitName) LIKE UPPER(:text) AND o.glId IS NULL", OrganizationUnit.class)
                .setParameter("type", type)
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }
    
    public List<OrganizationUnit> findByOrgTypeByNameAndNotIn(OrganizationUnitType type, String text,String ids) {
       
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o WHERE o.unitType = :type "
                + " AND UPPER(o.orgUnitName) LIKE UPPER(:text) "
                + " AND o.orgUnitId NOT IN ("+ids+")", OrganizationUnit.class)
                .setParameter("type", type)
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }
    
    public Integer findGlIdById(Integer id){
        return entityManager.createQuery("SELECT o.glId FROM OrganizationUnit o WHERE o.orgUnitId = :id ", Integer.class)
                .setParameter("id", id).getSingleResult();
    }
    
    @Override
    public OrganizationUnit update(OrganizationUnit org){
        // ahmed ibrahem: 10/4/2017
        if(org.getUnitType()== OrganizationUnitType.OPERATING_UNIT) org.setGlId(findGlIdById(org.getParentUnitId()));
        // old code:
        //org.setGlId(findGlIdById(org.getParentUnitId()));
        return entityManager.merge(org);
    }

    public void setLedgerToLegal(Integer legalId,Integer ledgerId){
        entityManager.createQuery("UPDATE OrganizationUnit o SET o.glId="+ledgerId+" WHERE o.orgUnitId="+legalId).executeUpdate();
    }
    
    public List<OrganizationUnit> findByTypeAndNameByParentId(String text,OrganizationUnitType type,int parentId)
    {
        return entityManager.createQuery("SELECT o FROM OrganizationUnit o "
                + "WHERE o.unitType = :type "
                + "AND o.parentUnitId = :parentId "
                + "AND UPPER(o.orgUnitName) LIKE UPPER(:text)")
                .setParameter("type", type)
                .setParameter("parentId", parentId)
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
    }
    
    public List<OrganizationUnit> findOrgUnit(Integer id, String name, Integer status, Integer parent, OrganizationUnitType unitType, String orgCode) {

        StringBuilder queryString = new StringBuilder("SELECT t FROM OrganizationUnit t WHERE t.unitType = :type");

        if (id != null && id > 0) {
            queryString.append(" AND t.orgUnitId = ").append(id);
        }
        if (parent != null) {
            queryString.append(" AND t.parentUnitId =  :parent");
        }

        if (status != null) {
            queryString.append(" AND t.enabledFlagId = ").append(status);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(t.orgUnitName) LIKE '%").append(name.toUpperCase()).append("%'");
        }
        
        if (orgCode != null && !orgCode.isEmpty()) {
            queryString.append(" AND upper(t.orgCode) LIKE '%").append(orgCode.toUpperCase()).append("%'");
        }

        TypedQuery<OrganizationUnit> query = entityManager.createQuery(queryString.toString(), OrganizationUnit.class);

        query.setParameter("type", unitType);

        if (parent != null) {
            query.setParameter("parent", parent);
        }

        return query.getResultList();
    }

    public List<OrganizationUnit> findOperatingAssignedToLedgerByName(String name)
    {
       return entityManager.createQuery("SELECT o FROM OrganizationUnit o "
               + "WHERE o.glId IS NOT NULL "
               + "AND o.unitType = com.bridge.enums.OrganizationUnitType.OPERATING_UNIT "
               + "AND UPPER(o.orgUnitName) LIKE UPPER(:text)")
               .setParameter("text", "%"+name+"%")
               .getResultList() ;
    }
    
    
    public List<OrganizationUnit> findOperatingHavePayableSetupByName(String name)
    {
       return entityManager.createQuery("SELECT o FROM OrganizationUnit o "
               + "WHERE UPPER(o.orgUnitName) LIKE UPPER(:text) "
               + "AND EXISTS ( SELECT 1 FROM PayableModuleConfiguration c WHERE c.operatingUnit = o ) ") 
               .setParameter("text", "%"+name+"%")
               .getResultList() ;
    }
    
    public boolean isOrgCodeExists(OrganizationUnitType unitType, String string) {

        return !entityManager.createQuery("select 1 from OrganizationUnit o where o.unitType = :type and upper(o.orgCode)= :name")
                .setParameter("name", string.toUpperCase())
                .setParameter("type", unitType)
                .getResultList().isEmpty();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    public boolean isOrgNameExists(OrganizationUnitType unitType, String string) {

        return !entityManager.createQuery("select 1 from OrganizationUnit o where o.unitType = :type and upper(o.orgUnitName)= :name")
                .setParameter("name", string.toUpperCase())
                .setParameter("type", unitType)
                .getResultList().isEmpty();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
