/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author Bridge
 */

@Named
@SessionScoped
public class CurrentUser implements Serializable{
    
    private int id ;
    
    private String fullName ;
    
    private String imageFileName ;
    
    private int businessGroupId ;
    
    private HashMap<String ,Boolean> permMap = new HashMap<>() ;
    
    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public int getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(int businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }


    public void setId(int id) {
        this.id = id;
    }
   
    public boolean hasPermission(String permission)
    {
        if(! permMap.containsKey(permission))
        {   
          boolean test = SecurityUtils.getSubject().isPermitted(permission) ;
         
          permMap.put(permission, test) ;
        }
        
        return permMap.get(permission) ;
    }
    
    public boolean hasRole(String role)
    {
       return SecurityUtils.getSubject().hasRole(role) ;
    }
    
    public String logout()
    {
        SecurityUtils.getSubject().logout();
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "/login?faces-redirect=true" ;
    }
}
