/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers;

import com.bridge.entities.hr.People;
import com.bridge.services.security.AccountsService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

/**
 *
 * @author Bridge
 */

@Model
public class LoginController {
    
    @Inject
    private CurrentUser currentUser ;
    
    @EJB
    private AccountsService accountsService ;
    
    private String userName ;
    
    private String password ;
    
    
    public void login()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        
        ExternalContext externalContext = facesContext.getExternalContext() ;
        
        try{
        
           Subject subject = SecurityUtils.getSubject() ;
        
           subject.login(new UsernamePasswordToken(userName, password, false));

           SavedRequest savedRequest = WebUtils.getAndClearSavedRequest((ServletRequest) externalContext.getRequest()) ;
          
           System.out.println("SAVED REQUEST URI"+(savedRequest != null ? savedRequest.getRequestURI():"null"));
           
           initCurrentUser();
           
           try {
              
               facesContext.getExternalContext()
                           .redirect(savedRequest != null ? savedRequest.getRequestURI()
                                                          :facesContext.getExternalContext().getRequestContextPath()
                                                          +"/faces/home.xhtml");

           } catch (IOException ex) {
              
              ex.printStackTrace();
          }       
        
        }catch(AuthenticationException e) {            
           
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown User name or Password","Unknown User name or Password"));
        }
    
    }

    private void initCurrentUser()
    {
        People people = accountsService.findEmployeeByUsername(userName) ;
   
        currentUser.setId(people.getPersonId());
        
        currentUser.setImageFileName(people.getImageFileName());
    }
    
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
