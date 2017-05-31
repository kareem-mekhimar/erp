/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.Client;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ClientType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.clients.ClientPrePaymentDTO;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.PrePaymentService;
import com.bridge.services.organization.OrganizationUnitService;
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
public class PrePaymentController implements Serializable{
    
    @EJB
    private PrePaymentService prePaymentService ;
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB    
    private ClientService clientService ;
    
    private OrganizationUnit currentOrg ;
    
    private Client currentClient ;
    
    private List<ClientPrePaymentDTO> prePaymentDTOs ;
    
       
    public List<OrganizationUnit> completeOperatingUnit(String text)
    {
       return organizationUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text) ;
    }
    
      
    public List<Client> completeClient(String text) {
       
        return clientService.findByName(text);
    }
        
    public void filter()
    {
       prePaymentDTOs = prePaymentService.findPrePaymentsByOperatingOrClient(currentOrg, currentClient) ;
    }
    
    public Client getCurrentClient() {
        return currentClient;
    }

    public List<ClientPrePaymentDTO> getPrePaymentDTOs() {
    
        return prePaymentDTOs;
    }

    
    public OrganizationUnit getCurrentOrg() {
        return currentOrg;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public void setCurrentOrg(OrganizationUnit currentOrg) {
        this.currentOrg = currentOrg;
    }
    
    
}
