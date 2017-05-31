/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import com.bridge.utils.JSFUtils;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Bridge
 */

@Model
public class PermissionCheckerController {
    
    @Inject
    private CurrentUser currentUser ;
    
    public void checkPermission(String perm)
    {
        if(! currentUser.hasPermission(perm))
            
            JSFUtils.redirectTo403();
    }
}
