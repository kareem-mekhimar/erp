/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Bridge
 */

@ApplicationPath("resources")
public class RestConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        
        HashSet hashSet = new HashSet() ;
        
        hashSet.add(PurchaseOrderResource.class) ;
        
        return hashSet ;
    }
    
    
}
