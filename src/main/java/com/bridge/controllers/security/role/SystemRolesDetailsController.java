/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.security.role;

import com.bridge.entities.security.RolesPermission;
import com.bridge.entities.security.SystemPermission;
import com.bridge.entities.security.SystemRole;
import com.bridge.enums.SystemPermissionDomain;
import com.bridge.services.categories.SystemRolesService;
import com.bridge.services.security.ResponsablityService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */

@Named
@ViewScoped
public class SystemRolesDetailsController implements Serializable{

    @EJB
    private ResponsablityService responsablityService;
   
    private SystemRole currentSystemRole ;
    
    private Integer currentSystemRoleId ;
    
    private List<SystemPermission> permissionsByDomain ;
    
    private SystemPermissionDomain selectedDomain ;
    
    private SystemPermission newSelectedPermission ;
    
    private SystemPermissionDomain [] domains = SystemPermissionDomain.values()  ;
    
    private List<Integer> toBeRemovedPermissionIds ;
    
    public void loadSystemRole()
    {
        if(currentSystemRoleId != null) 
        {
            currentSystemRole = responsablityService.findWithLinesById(currentSystemRoleId);
            
            if(currentSystemRole == null)
                
                JSFUtils.redirectTo404("Role");
           
        }
    }
    
    public String save() {
        
        responsablityService.update(currentSystemRole) ;
        
        if(toBeRemovedPermissionIds != null)
        {
           String idsDelimited = toBeRemovedPermissionIds.stream()
                   .map(i -> i.toString())
                   .collect(Collectors.joining(",")) ;
           
           responsablityService.removeRolesPermissionsByIds(idsDelimited);
        }
        
        return "systemRolesView?faces-redirect=true" ;
    }
    
    
    public void validateNameExist(FacesContext context,UIComponent component , Object value)
    {
        if(value == null)
            
            return; 

        if( ! value.toString().equalsIgnoreCase(currentSystemRole.getName())
                && responsablityService.isNameExists((String) value) )
        {
             throw new ValidatorException(new FacesMessage(value + " already exists..")) ;
        }
    }

    public void onDomainChange()
    {
        List<RolesPermission> permissions = currentSystemRole.getPermissions() ;
        
        if(permissions == null || permissions.isEmpty())
        {
            permissionsByDomain = responsablityService.findSystemPermissonByDomainType(selectedDomain) ;
        }
        else
        { 
           String idsDelimited = permissions.stream().map(r -> r.getSystemPermission().getId()+"")
                                                     .collect(Collectors.joining(",")) ;
           
           permissionsByDomain = responsablityService.findSystemPermissonByDomainTypeAndNotIn(selectedDomain, idsDelimited) ;
          
        }
    }
    
    
    public void addNewPermission()
    {
        RolesPermission rolesPermission = new RolesPermission(currentSystemRole, newSelectedPermission) ;
        
        List<RolesPermission> permissions = currentSystemRole.getPermissions() ;
        
        if(permissions == null)
        {
            permissions = new ArrayList<>() ;
        
            currentSystemRole.setPermissions(permissions);
        }
        
        permissions.add(rolesPermission) ;
        
        onCancel();
    }
    
    public void onCancel()
    {
        newSelectedPermission = null ;
       
        selectedDomain = null ;
       
        permissionsByDomain = null ;       
    }
    
    public void onRemove(RolesPermission permission)
    {
        if(permission.getId() != null)
        {
           if(toBeRemovedPermissionIds == null)
               
               toBeRemovedPermissionIds = new ArrayList<>() ;
           
           toBeRemovedPermissionIds.add(permission.getId()) ;
        }
        
        currentSystemRole.getPermissions().remove(permission) ;
    }
    
    public SystemRole getCurrentSystemRole() {
        
        if(currentSystemRole == null)
            
            currentSystemRole = new SystemRole() ;
        
        return currentSystemRole;
    }

    
    public Integer getCurrentSystemRoleId() {
        return currentSystemRoleId;
    }

    public void setCurrentSystemRoleId(Integer currentSystemRoleId) {
        this.currentSystemRoleId = currentSystemRoleId;
    }

    public SystemPermission getNewSelectedPermission() {
        return newSelectedPermission;
    }

    public void setNewSelectedPermission(SystemPermission newSelectedPermission) {
        this.newSelectedPermission = newSelectedPermission;
    }

    public SystemPermissionDomain[] getDomains() {
        return domains;
    }

    
    public List<SystemPermission> getPermissionsByDomain() {
        return permissionsByDomain;
    }

    public SystemPermissionDomain getSelectedDomain() {
        return selectedDomain;
    }

    public void setSelectedDomain(SystemPermissionDomain selectedDomain) {
        this.selectedDomain = selectedDomain;
    }

    
    
}
