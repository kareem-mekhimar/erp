/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.coa;

import com.bridge.entities.coa.CoaSegment;
import com.bridge.entities.coa.CoaSegmentLine;
import com.bridge.entities.coa.CoaStructure;
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
public class CoaStructureService extends AbstractService<CoaStructure> {

    @PersistenceContext(name = "bridge")
    public EntityManager entityManager;

    public CoaStructureService() {
        super(CoaStructure.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<CoaStructure> findCoaStructure(String var1, String var2, Integer var3) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM CoaStructure t WHERE 1=1 ");

        if (var1 != null) {
            queryString.append(" AND upper(t.coaCode) LIKE '%").append(var1.toUpperCase()).append("%'");
        }

        if (var3 != null) {
            queryString.append(" AND t.enabledFlagId = ").append(var3);
        }

        if (var2 != null && !var2.isEmpty()) {
            queryString.append(" AND upper(t.coaName) LIKE '%").append(var2.toUpperCase()).append("%'");
        }

        TypedQuery<CoaStructure> query = entityManager.createQuery(queryString.toString(), CoaStructure.class);

        return query.getResultList();
    }

    public List<CoaStructure> findCoaStructureByName(String text) {
        return entityManager.createQuery("SELECT s FROM CoaStructure s WHERE UPPER(s.coaName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }

    public CoaStructure findCoaStructureWithLines(int id) {

        try {

            return entityManager.createQuery("SELECT p FROM CoaStructure p left join fetch p.coaSegmentList l WHERE p.coaId = :id "
                    + "ORDER BY l.sequenceNumber", CoaStructure.class).setParameter("id", id).getSingleResult();

        } catch (NoResultException exception) {
            
            return null;
            
        }
    }

    public boolean isNameExists(String name) {
        return !entityManager.createQuery("select 1 from CoaStructure t where upper(t.coaName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public boolean isSegmentDetailNameExists(String name) {
        return !entityManager.createQuery("select 1 from CoaSegmentLine t where upper(t.segmentDetailName)= :name")
                .setParameter("name", name.toUpperCase())
                .getResultList().isEmpty();

    }

    public CoaSegment findSegmentWithLines(int id) {
        
        try {

            return entityManager.createQuery("SELECT s FROM CoaSegment s LEFT JOIN FETCH s.coaSegmentLineList"
                    + " WHERE s.segmentId = :id", CoaSegment.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {
            
            return null;
        }

    }

    public void updateSegment(CoaSegment segment) {
        entityManager.merge(segment);
    }

    public List<CoaSegmentLine> findSegmentLinesBySegmentSeqNoAndStructureIdAndStartsWith(Integer seqNo, CoaStructure structure, String text) {

        return entityManager.createQuery("SELECT c FROM CoaSegmentLine c "
                + "WHERE UPPER(c.segmentDetailCode) LIKE UPPER(:text) "
                + "AND c.segment.sequenceNumber = :seqNo "
                + "AND c.segment.coaStructure = :structure")
                .setParameter("structure", structure)
                .setParameter("seqNo", seqNo)
                .setParameter("text", text + "%")
                .getResultList();

    }

    public List<CoaSegmentLine> findSegmentLinesBySegmentSeqNo(Integer seqNo, CoaStructure structure, String text) {

        return entityManager.createQuery("SELECT c FROM CoaSegmentLine c "
                + "WHERE UPPER(c.segmentDetailName) LIKE UPPER(:text) "
                + "AND c.segment.sequenceNumber = :seqNo "
                + "AND c.segment.coaStructure = :structure")
                .setParameter("structure", structure)
                .setParameter("seqNo", seqNo)
                .setParameter("text", "%" + text + "%")
                .getResultList();

    }

    public List<CoaSegmentLine> findSegmentLinesByParentFlag(Integer seqNo, CoaStructure structure, String text) {

        return entityManager.createQuery("SELECT c FROM CoaSegmentLine c "
                + "WHERE UPPER(c.segmentDetailName) LIKE UPPER(:text) "
                + "AND c.segment.sequenceNumber = :seqNo "
                + "AND c.segment.coaStructure = :structure "
                + "AND c.parentAccountFlag = true")
                .setParameter("structure", structure)
                .setParameter("seqNo", seqNo)
                .setParameter("text", "%" + text + "%")
                .getResultList();

    }

    public CoaSegmentLine findCoaSegmentLineById(int id) {
        return entityManager.find(CoaSegmentLine.class, id);
    }

//    public CoaSegmentLine findCoaSegmentLineByCodeAndSegmentSeqAndCoaStructure(String code , int seqNo , CoaStructure structure)
//    {
//        try {
//        
//            return entityManager.createQuery("SELECT c FROM CoaSegmentLine c "
//                    + "WHERE c.segment.coaStructure = :struct "
//                    + "AND c.segment.sequenceNumber = :seqNo "
//                    + "AND c.segmentDetailCode = :code",CoaSegmentLine.class) 
//                .setParameter("code",code)
//                .setParameter("seqNo", seqNo)
//                .setParameter("struct", structure)
//                .getSingleResult() ;
//            
//        }catch(Exception e)
//        {
//            return null ;
//        }
//    }
//    public boolean isCoaSegementLineForSegmentExist(String code ,CoaSegment coaSegment)
//    {
//        return ! entityManager.createQuery("SELECT 1 FROM CoaSegmentLine l "
//                + "WHERE l.segmentDetailCode = :code AND l.segment = :segment")
//                .setParameter("segment", coaSegment)
//                .setParameter("code", code)
//                .getResultList().isEmpty() ;
//    }  
//    public void setHasAccounts(CoaStructure coaStructure,boolean flag)
//    {
//       entityManager.createQuery("UPDATE CoaStructure c SET c.hasAccounts = :flag WHERE c = :coa")
//               .setParameter("coa", coaStructure)
//               .setParameter("flag", flag)
//               .executeUpdate();
//    }
//    public void setInvolvedInAccounts(CoaSegmentLine [] segmentLines,boolean flag)
//    {
//        String idsDelimited = Arrays.stream(segmentLines)
//                .map(line -> line.getSegmentDetailId()+"")
//                .collect(Collectors.joining(",")) ;
//        
//        entityManager.createQuery("UPDATE CoaSegmentLine l SET l.involvedInAccount = :flag "
//                + "WHERE l.segmentDetailId in ("+idsDelimited+")")
//                .setParameter("flag", flag)
//                .executeUpdate() ;
//        
//    }
}
