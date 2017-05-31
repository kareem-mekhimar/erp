/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.security.account;

import com.bridge.entities.hr.People;
import com.bridge.services.security.AccountsService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class AccountsController implements Serializable{

    @EJB
    private AccountsService accountsService ;

    private String name ;

    private List<People> employeesWithAccounts ;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void filter()
    {
       employeesWithAccounts = accountsService.findEmployeesHaveAccountByName(name) ;
    }

    public List<People> getEmployeesWithAccounts() {
        
        return employeesWithAccounts;
    }
    
}
