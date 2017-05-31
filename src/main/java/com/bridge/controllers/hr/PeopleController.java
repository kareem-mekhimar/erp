/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.People;
import com.bridge.services.hr.PeopleService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class PeopleController implements Serializable {

    @EJB
    private PeopleService peopleService;

    private List<People> peopleList;
    private String personCode;
    private String fullName;
    
    
    
    
    
    public List<People> getPeopleList() {
        
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    public void filter() {
        
        peopleList = peopleService.findPeople(personCode, fullName);
        

    }

    

}


