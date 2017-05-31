/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.HzParties;
import com.bridge.enums.CustomerType;
import com.bridge.services.clients.HzPartyService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author Administrator
 */
@Model
public class ArCustomerController implements Serializable{
    
    @EJB
    private HzPartyService customerService;
    private HzParties hzParties;
    private List<HzParties> customersList;
    
    private Integer customerId;
    private String customerName;
    private String aliasName;
    private CustomerType customerType;
    
    CustomerType[] customerTypes;
    
    

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public HzParties getHzParties() {
        return hzParties;
    }

    public void setHzParties(HzParties hzParties) {
        this.hzParties = hzParties;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public List<HzParties> getCustomersList(){        
        return customersList;
    }
    public void filterCustomers(){

        customersList = customerService.findCustomers(customerId, customerName, customerType);        
    }
    
    public CustomerType[] getCustomerTypes(){
        
        return CustomerType.values();
    }
    
}
