/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.HzParties;
import com.bridge.entities.clients.HzPartySites;
import com.bridge.enums.CustomerType;
import com.bridge.services.clients.HzPartyService;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
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
 * @author Administrator
 */
@Named
@ViewScoped
public class ArCustomerDetailsController implements Serializable {
    
    @EJB
    private HzPartyService customerService;
    
    private HzParties currentCustomer;
    private Integer currentCustomerId ;
    
    private HzPartySites currentCustomerSite;
    private List<HzPartySites> customerSitesList;
    private Integer currentCustomerSiteId;
    
    public List<HzPartySites> getCustomerSitesList() {
        return customerSitesList;
    }

//***********
    public void setCustomerSitesList(List<HzPartySites> customerSitesList) {    
        this.customerSitesList = customerSitesList;
    }

    public Integer getCurrentCustomerId() {
        return currentCustomerId;
    }

    public void setCurrentCustomerId(Integer currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
    }
    
    public HzParties getCurrentCustomer() {
        if (currentCustomer == null) {
            currentCustomer = new HzParties();
        }
        return currentCustomer;
    }

    public void setCurrentCustomer(HzParties currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
    
    public void loadCurrentCustomerSite(HzPartySites customerSite) {
 
            currentCustomerSite = customerSite;

    }

    public HzPartySites getCurrentCustomerSite() {
     
        if(currentCustomerSite == null)
            
            currentCustomerSite = new HzPartySites() ;
        
        return currentCustomerSite;
    }

    public void setCurrentCustomerSite(HzPartySites currentCustomerSite) {
     
        this.currentCustomerSite = currentCustomerSite;
        
    }

    public Integer getCurrentCustomerSiteId() {
        return currentCustomerSiteId;
    }

    public void setCurrentCustomerSiteId(Integer currentCustomerSiteId) {
        this.currentCustomerSiteId = currentCustomerSiteId;
    }
    
//***********
    
    public void loadCustomer() {      
        if (currentCustomerId != null) {
            currentCustomer = customerService.findCustomerWithSitesById(currentCustomerId);
            currentCustomer.getHzPartySitesList().sort(Comparator.comparing(HzPartySites::getPartySiteId));
            customerSitesList = currentCustomer.getHzPartySitesList();            
        }
    }
    
    public void validateNameExist(FacesContext context,UIComponent component , Object value)
    {
        UIInput input = (UIInput) component ;
        
        if(value == null || value.toString().trim().length() > 0)
        {                
            return;
        }
        
        if(currentCustomerId != null)
        {
            if(input.getValue().equals(value))                
            return;
        }
                
        if( customerService.isNameExists(  (String) value   , currentCustomer.getPartyTypeId() ))
        {
            throw new ValidatorException(new FacesMessage(value + " already existes..")) ;
        }
        
    }
    
    public String save()
    {
       customerService.update(currentCustomer) ;       
       return "customersView?faces-redirect=true";
    }
    
    public void addCustomerSite() {          
            
        if(currentCustomerSite.getPartySiteId() == null)
        {     
            currentCustomerSite.setParty(currentCustomer);      
            currentCustomer.getHzPartySitesList().add(currentCustomerSite);            
        }
          
    }
    
}
