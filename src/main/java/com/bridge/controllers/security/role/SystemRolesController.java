/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.security.role;
import com.bridge.entities.security.SystemRole;
import com.bridge.services.categories.SystemRolesService;
import com.bridge.services.security.ResponsablityService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class SystemRolesController implements Serializable  {
    
    @EJB
    private ResponsablityService responsablityService;
   
    private List<SystemRole> systemRolesList;
    
    private String currentName ;

    public void filter(){
      
        systemRolesList = responsablityService.findAllOrByName(currentName) ;
        
    }

 
    public List<SystemRole> getSystemRolesList(){
        
        return systemRolesList;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
       
    
}

