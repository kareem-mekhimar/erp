/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.clients.Client;
import com.bridge.entities.po.PoHeader;
import com.bridge.enums.ClientType;
import com.bridge.enums.PoStatus;
import com.bridge.services.clients.ClientService;
import com.bridge.services.po.PurchaseOrderService;
import java.io.Serializable;
import java.util.List;
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
public class PoController implements Serializable{
    
    @EJB
    private PurchaseOrderService poService ;
    
    @EJB
    private ClientService clientService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private PoStatus[] poStatus ;
    
    private List<PoHeader> poHeaders ;
    
    private List<PoHeader> selectedPos ;
    
    private List<Client> clients ;
    
    private String currentpoCode ;
    
    private Integer currentSelectedClientId ;
    
    private PoStatus currentPoStatus ;
    
    @PostConstruct
    private void init()
    {
      clients = clientService.findByType(ClientType.SUPPLIER) ;
     
    }
    
    public void filter()
    {
       poHeaders = poService.findByClientOrCodeOrStatus(currentSelectedClientId, currentpoCode,currentPoStatus) ;   
    }
    
    public void reactivatePo(PoHeader po)
    {
       poService.reactivatePo(po);
       
       notificationController.showNotification("PO with code = "+po.getPoHeaderId()+" Reactivated", "success");
    }
    
    public void cancelPo(PoHeader po)
    {
       poService.cancelPo(po);
       
       notificationController.showNotification("PO with code = "+po.getPoHeaderId()+" Cancelled", "success");
    }
    
    public List<PoHeader> getPoHeaders() {
    
        return poHeaders;
    }

    public String getCurrentpoCode() {
        return currentpoCode;
    }


    public void setCurrentpoCode(String currentpoCode) {
        this.currentpoCode = currentpoCode;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Integer getCurrentSelectedClientId() {
        return currentSelectedClientId;
    }

    public void setCurrentSelectedClientId(Integer currentSelectedClientId) {
        this.currentSelectedClientId = currentSelectedClientId;
    }

    public PoStatus[] getPoStatus() {
        return PoStatus.values();
    }

  

    public PoStatus getCurrentPoStatus() {
        return currentPoStatus;
    }

    public void setCurrentPoStatus(PoStatus currentPoStatus) {
        this.currentPoStatus = currentPoStatus;
    }

    public List<PoHeader> getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(List<PoHeader> selectedPos) {
        this.selectedPos = selectedPos;
    }

    
   
}
