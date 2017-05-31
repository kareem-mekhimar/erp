/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.security.account;

import com.bridge.entities.hr.People;
import com.bridge.entities.security.UserAccount;
import com.bridge.services.hr.PeopleService;
import com.bridge.services.security.AccountsService;
import com.bridge.utils.SecurityUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class AccountsDetailsControllers implements Serializable{
    
    @EJB
    private AccountsService accountsService ;
    
    private Integer currentEmpId ;

    private UserAccount currentAccount ;
    
    public void loadEmployee()
    {
       if(currentEmpId != null)
       {
           currentAccount = accountsService.findByEmployee(currentEmpId) ;
       }
    }
    
    public List<People> completeEmployeeByName(String text)
    {
        return accountsService.findEmployeeByNameWithoutAccount(text) ;
    }
    
    public Integer getCurrentEmpId() {
        return currentEmpId;
    }

    public void setCurrentEmpId(Integer currentEmpId) {
        this.currentEmpId = currentEmpId;
    }
    

    public UserAccount getCurrentAccount() {
    
        if(currentAccount == null)
            
            currentAccount = new UserAccount() ;
        
        return currentAccount;
    }
    
    
    public void validateNotExists(FacesContext context , UIComponent component , Object value)
    {
        String oldIfExists = currentAccount.getUserName() ;
        
        String newName = (String) value ;

        if(oldIfExists != null && newName.equalsIgnoreCase(oldIfExists))
            
            return; 
        
        if(accountsService.isExists(newName))
        {
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR
                   ,"User Name already exists..",
                   "User Name already exists..")) ;
        }
    }
    
    
    public String save()
    {
        //[ password , salt ]
       String [] passwordAndSalt = SecurityUtils.digest(currentAccount.getPassword()) ;
       
       currentAccount.setPassword(passwordAndSalt[0]);
       
       currentAccount.setSalt(passwordAndSalt[1]);
       
       accountsService.update(currentAccount) ;
       
       return "view?faces-redirect=true" ;
    }
}
