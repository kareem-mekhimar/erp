/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author Bridge
 */

@Singleton
@PushEndpoint("/notification/{id}")
public class NotificationsEndpoint {
    
    @OnMessage(encoders = JSONEncoder.class)
    public String onMessage(String msg)
    {
        System.out.println("com.bridge.controllers.NotificationsEndpoint.onMessage()");
        
        return msg ;
    }    
}
