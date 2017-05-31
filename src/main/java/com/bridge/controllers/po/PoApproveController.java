/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.Client;
import com.bridge.entities.po.PoHeader;
import com.bridge.enums.ClientType;
import com.bridge.enums.PoStatus;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.po.PurchaseOrderService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class PoApproveController implements Serializable{
     
    @EJB
    private PurchaseOrderService poService ;
    
    @EJB
    private ClientService clientService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private String currentpoCode ;
    
    private Integer currentSelectedClientId ;
    
    private List<PoHeader> poHeaders ;
    
    private List<PoHeader> selectedPos ;
    
    private List<Client> clients ;
    
    @PostConstruct
    private void init()
    {
      clients = clientService.findByType(ClientType.SUPPLIER);
    }
    
    public void filter()
    {
       poHeaders = poService.findByClientOrCodeOrStatus(currentSelectedClientId,currentpoCode,PoStatus.INCOMPLETE) ;       
    }
        
    public void approveSelectedPos()
    {
        String ids = selectedPos.stream().map(p -> p.getPoHeaderId().toString()).collect(Collectors.joining(",")) ;
        
        poService.approvePos(ids);
       
        filter();
        
        notificationController.showNotification("Po(s)with No(s) = "+ids+" Approved.!", NotificationController.SUCCESS);
    }
    
    public void rejectSelectedPos()
    {
        String ids = selectedPos.stream().map(p -> p.getPoHeaderId().toString()).collect(Collectors.joining(",")) ;
        
        poService.rejectPos(ids);
      
        filter();
        
        notificationController.showNotification("Po(s)with No(s) = "+ids+" Rejected.!", NotificationController.SUCCESS);
    }
    
    public String getCurrentpoCode() {
        return currentpoCode;
    }

    public void setCurrentpoCode(String currentpoCode) {
        this.currentpoCode = currentpoCode;
    }

    public Integer getCurrentSelectedClientId() {
        return currentSelectedClientId;
    }

    public void setCurrentSelectedClientId(Integer currentSelectedClientId) {
        this.currentSelectedClientId = currentSelectedClientId;
    }

    public List<PoHeader> getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(List<PoHeader> selectedPos) {
        this.selectedPos = selectedPos;
    }

    public List<PoHeader> getPoHeaders() {
        return poHeaders;
    }

    public List<Client> getClients() {
        return clients;
    }
    
}
