/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import com.bridge.controllers.qualifiers.UserPushNotification;
import com.bridge.entities.hr.UserNotification;
import com.bridge.services.hr.UserNotificationService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Bridge
 */

@Named
@SessionScoped
public class UserNotificationController implements Serializable{
    
    @EJB
    private UserNotificationService notificationService ;
    
    @Inject
    private CurrentUser currentUser ;
    
    public void pushToCurrentUser(@Observes @UserPushNotification String description)
    {
        EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
        
        eventBus.publish("/notification/"+currentUser.getId(),description) ;
        
        notificationService.create(new UserNotification(currentUser.getId(), description));
    }
}
