/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.salesorder;

import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.HzParties;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.enums.ClientType;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.HzPartyService;
import com.bridge.services.salesorder.SalesOrderService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author kareem
 */
@Named
@ViewScoped
public class SalesOrderController implements Serializable{
    
    @EJB
    private SalesOrderService service ;
    
    @EJB
    private ClientService clientService ;
    
    private Integer no ;

    private Integer customerId ;
    
    private List<OeOrderHeader> orders ;
    
    public List<Client> completeCustomersByName(String text)
    {
       return clientService.findByName(ClientType.CUSTOMER, text);
    }
    
    public void filter()
    {
       orders = service.findByIdOrCustomer(no, customerId) ;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public List<OeOrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(List<OeOrderHeader> orders) {
        this.orders = orders;
    }
    
    
}
