/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MtlInventoryItemsQuantity;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.po.PurchaseOrderService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Components;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class PoTransferController implements Serializable{

    @EJB
    private PurchaseOrderService purchaseOrderService ;
    
    @EJB
    private MainInvService mainInvService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private TransactionService transactionService ;
    
    @EJB
    private COAAccountService accountService ;
    
    @EJB
    private SystemItemService systemItemService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private MainInventory sourceMainInventory ;
    
    private MainInventory targetMainInventory ;
    
    private SecondaryInventory sourceSecondaryInventory ;
    
    private SecondaryInventory targetSecondaryInventory ;
    
    private GlCodeCombination targetAccount ;
    
    private SystemItem systemItem ;

    private List<MtlInventoryItemsQuantity> currentQuantitys ;
       
    private List<MtlInventoryItemsQuantity> selectedQuantitys ;
    
    public List<MainInventory> completeMainInvByName(String text)
    {
       return mainInvService.findByName(text) ;
    }
    
    public List<SecondaryInventory> completeSecondaryForSourceByName(String text)
    {
       if(sourceMainInventory == null)
           
           return null ;
       
       return subInvService.findByNameForMain(text, sourceMainInventory) ;
    }
    
    
    public List<SecondaryInventory> completeSecondaryForTargetByName(String text)
    {
        if(targetMainInventory == null)
            
            return null ;
        
       return subInvService.findByNameForMain(text, targetMainInventory)
               .stream().filter(s -> {
               
                   if(sourceSecondaryInventory == null)
                       
                       return true ;
                   
                    return ! s.getSecondaryInventoryId().equals(sourceSecondaryInventory.getSecondaryInventoryId()) ;
               })
               .collect(Collectors.toList()) ; 
    }
    
    
    public List<SystemItem> completeSystemItemInInventoriesByName(String text)
    {
        if(sourceMainInventory == null || sourceSecondaryInventory == null)
            
            return null;
        
       return systemItemService.findInInventoriesByNameAndMainIdAndSubId(sourceMainInventory.getOrganizationId(), 
               sourceSecondaryInventory.getSecondaryInventoryId(), text) ;
    }
    
    
    public List<GlCodeCombination> completeAccountsByName(String text)
    {
        return accountService.findByAlias(text) ;
    }
    
    public void resetSubInvSource()
    {
      sourceSecondaryInventory = null ; 
      
      systemItem = null ;
    }
    
    public void resetSubInvTarget()
    {
      targetSecondaryInventory = null;  
      
      systemItem = null ;
      
      currentQuantitys = null ;
    }
    
    
    public void filter()
    {
        currentQuantitys = transactionService.findCurrentQuantities(sourceMainInventory.getOrganizationId(),
                sourceSecondaryInventory.getSecondaryInventoryId()
               ,systemItem == null ? null : systemItem.getInventoryItemId() ) ;
    }
    
    
    public void initTransactionsForTransfer()
    {
        if(currentQuantitys == null)
            
              return; 
        
        selectedQuantitys = currentQuantitys.stream()
                .filter(mtl -> mtl.getTxQuantity() != null)
                .collect(Collectors.toList()) ;
        
        if(! selectedQuantitys.isEmpty())
            
            RequestContext.getCurrentInstance().addCallbackParam("filled", true);
    }
    
    
     
    public void onDateChange(MtlInventoryItemsQuantity itemsQuantity)
    {
        LocalDate newTxDate = itemsQuantity.getTxDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
        
        BigDecimal qtyInThisDate = transactionService.findNotReservedQuantityUntilDate(itemsQuantity.getSystemItem().getInventoryItemId(),
                itemsQuantity.getSecondaryInventoryId(), newTxDate) ;

        if(qtyInThisDate == null) // No Records To SUM
            
            qtyInThisDate = BigDecimal.ZERO ;
        
        itemsQuantity.setQtyUntilDate(qtyInThisDate);
    }
    
    
    public void vaildateTxQuantity(FacesContext context , UIComponent component , Object value)
    {
        if(value == null)
  
            return;
 
        BigDecimal currentQuantity = (BigDecimal) ((UIOutput) component.findComponent("quantity")).getValue() ;
        
        BigDecimal txQuantity = (BigDecimal) value ;
        
        if(txQuantity.compareTo(BigDecimal.ZERO) == 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't Be Zero", "Value Can't Be Zero")) ;
        
        if(txQuantity.compareTo(currentQuantity) > 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Can't exceed current amount", "Value Can't exceed current amount")) ;
        
    }
    
    
    public String transfer()
    {      
        String outcome ;
  
        EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
        
        if(targetAccount == null)
        {
            purchaseOrderService.transferLinesToInventory(selectedQuantitys, sourceMainInventory, sourceSecondaryInventory, targetMainInventory, targetSecondaryInventory);
  
            eventBus.publish("/subInvs",sourceSecondaryInventory.getSecondaryInventoryId()+","+targetSecondaryInventory.getSecondaryInventoryId()) ;
  
            outcome = "transfer?faces-redirect=true" ;
        }
        else
        {
            purchaseOrderService.transferLinesToAccount(selectedQuantitys, sourceMainInventory, sourceSecondaryInventory, targetAccount);
       
            eventBus.publish("/subInvs",sourceSecondaryInventory.getSecondaryInventoryId()) ;
            
            outcome = "issue?faces-redirect=true" ; 
        }
        
        return outcome ;
    }
    
    
    public void onMessageRecieved()
    { 
        FacesContext facesContext = FacesContext.getCurrentInstance() ;
        
        ExternalContext context = facesContext.getExternalContext() ;
       
        String msg = context.getRequestParameterMap().get("data") ;
        
        int sourceId = 0 , targetId = 0 ;
        
        if(msg.contains(","))
        {    
           String [] ids = msg.split(",") ;
        
           sourceId = Integer.parseInt(ids[0]) ;
        
           targetId = Integer.parseInt(ids[1]) ;
        }
        else  // RECIEVE ONLY
        {
           targetId = Integer.parseInt(msg) ;
        }
        
        if(sourceSecondaryInventory != null && !currentQuantitys.isEmpty() && 
                (sourceSecondaryInventory.getSecondaryInventoryId() == sourceId || sourceSecondaryInventory.getSecondaryInventoryId() == targetId))
        {
             currentQuantitys.stream().forEach(q -> {
             
                 Date txDate = q.getTxDate() ;
                 
                 if(txDate == null)
                     
                     return ;
                 
                 LocalDate localDate = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
                 
                 BigDecimal qty = transactionService.findNotReservedQuantityUntilDate(q.getSystemItem().getInventoryItemId(),
                        sourceSecondaryInventory.getSecondaryInventoryId(),localDate) ;
                 
                 if(qty == null)
                     
                     qty = BigDecimal.ZERO ;
                 
                 q.setQtyUntilDate(qty);
             });
             
             notificationController.showNotification("Some Quantities has been updated by another user...", msg);
             
        }
    }
    
    
    public MainInventory getSourceMainInventory() {
        return sourceMainInventory;
    }

    public SecondaryInventory getSourceSecondaryInventory() {
        return sourceSecondaryInventory;
    }

    public SystemItem getSystemItem() {
        return systemItem;
    }

    public MainInventory getTargetMainInventory() {
        return targetMainInventory;
    }

    public SecondaryInventory getTargetSecondaryInventory() {
        return targetSecondaryInventory;
    }

    public void setSourceMainInventory(MainInventory sourceMainInventory) {
        this.sourceMainInventory = sourceMainInventory;
    }

    public void setSourceSecondaryInventory(SecondaryInventory sourceSecondaryInventory) {
        this.sourceSecondaryInventory = sourceSecondaryInventory;
    }

    public void setTargetMainInventory(MainInventory targetMainInventory) {
        this.targetMainInventory = targetMainInventory;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }

    public void setTargetSecondaryInventory(SecondaryInventory targetSecondaryInventory) {
        this.targetSecondaryInventory = targetSecondaryInventory;
    }

    public List<MtlInventoryItemsQuantity> getCurrentQuantitys() {
        return currentQuantitys;
    }

    public GlCodeCombination getTargetAccount() {
        return targetAccount;
    }
  
    public void setTargetAccount(GlCodeCombination targetAccount) {
        this.targetAccount = targetAccount;
    }
}
