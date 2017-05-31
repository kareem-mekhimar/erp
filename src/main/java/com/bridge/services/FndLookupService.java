/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services;



import com.bridge.entities.FndLookupDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class FndLookupService {
       
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
    
    private final int PO_STATUS = 8 ;
    
    public List<FndLookupDetail> findPoStatus()
    {
        return entityManager.createNamedQuery("FndLookupDetail.findStatusForDomainObject")
                .setParameter("statusId",PO_STATUS)
                .getResultList() ;
    }
    
}
