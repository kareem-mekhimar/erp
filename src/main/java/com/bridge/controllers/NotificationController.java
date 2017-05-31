/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import javax.enterprise.context.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@RequestScoped
public class NotificationController {
    
    public static final String SUCCESS = "success" ;
    
    public void showNotification(String msg , String status)
    {
        RequestContext.getCurrentInstance().execute("UIkit.notify('"+msg+"', {status:'"+status+"',timeout:2500})"); 
    }
}
