/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.po;

import com.bridge.entities.po.PoHeader;
import com.bridge.entities.po.PoLine;
import com.bridge.entities.po.PoLineLocation;
import com.bridge.enums.PoStatus;
import com.bridge.services.AbstractService;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class PoLineLocationService extends AbstractService<PoLineLocation> {
    
        @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public PoLineLocationService() {
        super(PoLineLocation.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
    
}
