/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.security.role;

import com.bridge.controllers.CurrentUser;
import com.bridge.controllers.NotificationController;
import com.bridge.entities.security.SystemRole;
import com.bridge.entities.security.UserAccount;
import com.bridge.services.security.ResponsablityService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class RoleAssignmentController implements Serializable{
    
    @EJB
    private ResponsablityService responsablityService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private DualListModel<UserAccount> accounts = new DualListModel<>();
   
    private List<SystemRole> systemRoles ;

    private List<UserAccount> toBeAdded ;
    
    private List<UserAccount> toBeDeleted ;
    
    private Integer selectedRoleId ;
    
    @PostConstruct
    private void init()
    {
       systemRoles = responsablityService.findAll() ;
    }
    
    public void onRoleChange()
    {
       List<UserAccount> sourceAccounts = responsablityService.findAccountsNotAssignedToRoleId(selectedRoleId);
       
       List<UserAccount> targetAccounts = responsablityService.findAssignedAccountsToRole(selectedRoleId) ;
       
       accounts.setSource(sourceAccounts);
       
       accounts.setTarget(targetAccounts);
       
       toBeAdded = new ArrayList<>() ;
       
       toBeDeleted = new ArrayList<>() ;
    }

    public void save()
    { 
       if(! toBeAdded.isEmpty())
       {
          responsablityService.createAssignments(selectedRoleId, toBeAdded);
       }
       
       if(! toBeDeleted.isEmpty())
       {
          responsablityService.removeAssignments(selectedRoleId, toBeDeleted);
       }
       
       notificationController.showNotification("Changes Saved..", NotificationController.SUCCESS);
    }

    public void onTransfer(TransferEvent e)
    {
        if(e.isAdd())
        {
            e.getItems().forEach((a) -> {
            
                UserAccount account = (UserAccount) a ;
                
                if(toBeDeleted.contains(account))
                    
                    toBeDeleted.remove(account) ;
                
                else
                    
                    toBeAdded.add(account) ;
            });
        }
        else
        {
            e.getItems().forEach((a) -> {
            
                UserAccount account = (UserAccount) a ;
                
                if(toBeAdded.contains(account))
                    
                    toBeAdded.remove(account) ;
                
                else
                    
                    toBeDeleted.add(account) ;
            });            
        }
    }
    
    public DualListModel<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(DualListModel<UserAccount> accounts) {
        this.accounts = accounts;
    }
    

    public Integer getSelectedRoleId() {
        return selectedRoleId;
    }

    public void setSelectedRoleId(Integer selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

    public List<SystemRole> getSystemRoles() {
        return systemRoles;
    }

    public List<UserAccount> getToBeAdded() {
        return toBeAdded;
    }

    public List<UserAccount> getToBeDeleted() {
        return toBeDeleted;
    }

    
}
