/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;

import com.bridge.entities.hr.UserNotification;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class UserNotificationService extends AbstractService<UserNotification>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public UserNotificationService() {
    
        super(UserNotification.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<String> findDescNotSeenByUserId(int userId)
    {
        return entityManager.createQuery("SELECT u.description FROM UserNotification u "
                + "WHERE u.seenFlag IS FALSE "
                + "AND u.userId = :id "
                + "ORDER BY u DESC")
                .setParameter("id", userId)
                .getResultList() ;
    }
    
}
