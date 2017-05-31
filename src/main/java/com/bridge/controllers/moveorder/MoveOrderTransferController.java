/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.moveorder;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.entities.shipment.ShipmentHeader;
import com.bridge.enums.MoveOrderTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.moveorder.MoveOrderService;
import com.bridge.services.shipment.ShipmentService;
import com.bridge.utils.JSFUtils;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class MoveOrderTransferController implements Serializable {

    @EJB
    private MoveOrderService moveOrderService;

    @EJB
    private TransactionService transactionService;
    
    @EJB
    private ShipmentService shipmentService;

    @Inject
    private NotificationController notificationController ;
    
    private ShipmentHeader shipmentHeader;

    private Integer moveOrderId;

    private MtlTxnRequestHeader currentMoveOrder;
    
    private List<MtlTxnRequestLine> linesNeeded;

    private MainInventory sourceMainInventory;

    private MainInventory targetMainInventory;

    private SecondaryInventory targetSecondaryInventory;

    private SecondaryInventory sourceSecondaryInventory;

    private GlCodeCombination sourceAccount;
    
    private GlCodeCombination targetAccount;
    
    private String target;
    private String source;

    public void loadMoveOrder() {
      
        if (moveOrderId != null) {
          
            currentMoveOrder = moveOrderService.findWithLines(moveOrderId);

            if (currentMoveOrder == null) {
             
                JSFUtils.redirectTo404("Move Order");
           
            } else {
              
                sourceAccount=currentMoveOrder.getFromAccountId();
                targetAccount=currentMoveOrder.getToAccountId();

                sourceMainInventory=currentMoveOrder.getFromMainInventoryId();
                targetMainInventory=currentMoveOrder.getToMainInventoryId();
                
                sourceSecondaryInventory=currentMoveOrder.getFromSubinventoryId();
                targetSecondaryInventory=currentMoveOrder.getToSubinventoryId();
                
                setSenario(currentMoveOrder.getTransactionType());
                
                if(currentMoveOrder.getTransactionType()== MoveOrderTransactionType.RETURN_ORDER){
                        currentMoveOrder.getMtlTxnRequestLines().forEach(l -> {
                    
                    BigDecimal qty = transactionService.findNotReservedQuantityUntilDate(l.getInventoryItem().getInventoryItemId(),
                            targetSecondaryInventory.getSecondaryInventoryId(), LocalDate.now()) ;
                    
                    l.setActualQtyInInventory(qty == null ? BigDecimal.ZERO : qty);
                    
                });
                }
                if(currentMoveOrder.getTransactionType()== MoveOrderTransactionType.SALES_ORDER){
                        currentMoveOrder.getMtlTxnRequestLines().forEach(l -> {
                    
                    BigDecimal qty = transactionService.findNotReservedQuantityUntilDate(l.getInventoryItem().getInventoryItemId(),
                            sourceSecondaryInventory.getSecondaryInventoryId(), LocalDate.now()) ;
                    
                    l.setActualQtyInInventory(qty == null ? BigDecimal.ZERO : qty);
                    
                });
                }

            
            }
        }
    }

    
   
     
    public void onDateChange(MtlTxnRequestLine line)
    {
        LocalDate newTxDate = line.getTxDateForMoTransfer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
        
        BigDecimal qtyInThisDate = transactionService.findNotReservedQuantityUntilDate(line.getInventoryItem().getInventoryItemId(),
                sourceSecondaryInventory.getSecondaryInventoryId(), newTxDate) ;

        if(qtyInThisDate == null) // No Records To SUM
            
            qtyInThisDate = BigDecimal.ZERO ;
        
        line.setActualQtyInInventory(qtyInThisDate);
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
        
        if(currentMoveOrder == null) // TO BE CHANGED
            
            return; 
        
        List<MtlTxnRequestLine> lines = currentMoveOrder.getMtlTxnRequestLines() ;
        
        if(sourceSecondaryInventory != null && ! lines.isEmpty() && 
                (sourceSecondaryInventory.getSecondaryInventoryId() == sourceId || sourceSecondaryInventory.getSecondaryInventoryId() == targetId))
        {
             lines.stream().forEach(q -> {
             
                 Date txDate = q.getTxDateForMoTransfer() ;
                 
                 if(txDate == null)
                     
                     return ;
                 
                 LocalDate localDate = txDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
                 
                 BigDecimal qty = transactionService.findNotReservedQuantityUntilDate(q.getInventoryItem().getInventoryItemId(),
                        sourceSecondaryInventory.getSecondaryInventoryId(),localDate) ;
                 
                 if(qty == null)
                     
                     qty = BigDecimal.ZERO ;
                 
                 q.setActualQtyInInventory(qty);
             });
             
             notificationController.showNotification("Some Quantities has been updated by another user...", msg);
             
        }
    }
    
        
    public void validateQtyGtZeroOrNotAvailableInInv(FacesContext context , UIComponent component , Object value)
    {
        
        MtlTxnRequestLine line  =(MtlTxnRequestLine) JSFUtils.evaluateValueExpression("#{line}", MtlTxnRequestLine.class) ;
        
       BigDecimal actualQty= line.getActualQtyInInventory();
       
      MoveOrderTransactionType type = line.getHeader().getTransactionType();
                
       BigDecimal quantity = (BigDecimal) value;
       
       if(quantity == null)
           
           return ;
       
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero", "Value Can't Be Zero")) ;
       }   
       
       if((type==MoveOrderTransactionType.SALES_ORDER || type==MoveOrderTransactionType.RETURN_ORDER) 
               && line.getQuantity().subtract(line.getQuantityDelivered()).compareTo(quantity)<0){
           
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Over Available Qty to Transact", "Value Over Available Qty to Transact")) ;
       }

       if(quantity.compareTo(actualQty) > 0){
           
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Exceed Inventory Quantity", "Value Can't Exceed Inventory Value")) ;
           
       }

    }
    
    
    public String save()
    {
       linesNeeded = currentMoveOrder.getMtlTxnRequestLines().stream()
                .filter(l -> l.getQuantityThatWillTransferInTx() != null)
                .collect(Collectors.toList());
        
        if (!linesNeeded.isEmpty()) {

            EventBus eventBus = EventBusFactory.getDefault().eventBus() ;
            
            switch (currentMoveOrder.getTransactionType()) {
               
                case SALES_ORDER:
                  
                    moveOrderService.transferMoveOrderToShipping(linesNeeded,TxnSourceType.SALES_ORDER,
                            "Move Order FOR Sales Order No "+currentMoveOrder.getSalesOrder().getHeaderId());
                    
                    eventBus.publish("/subInvs",sourceSecondaryInventory.getSecondaryInventoryId()) ;
                  
                    break;
                
                case MOVE_ORDER:

                    moveOrderService.transferMoveOrderToInventory(linesNeeded, 
                             sourceMainInventory, sourceSecondaryInventory, 
                             targetMainInventory, targetSecondaryInventory,
                             TxnSourceType.MOVE_ORDER,"Move Order No "+currentMoveOrder.getHeaderId());
                
                    eventBus.publish("/subInvs",sourceSecondaryInventory.getSecondaryInventoryId()+","+targetSecondaryInventory.getSecondaryInventoryId()) ;

                    eventBus.publish("/batch",targetSecondaryInventory.getSecondaryInventoryId()) ;                  
                   
                    break;
                    
                case JOB_SCHEDULE :
                    
                    moveOrderService.transferMoveOrderToInventory(linesNeeded, 
                             sourceMainInventory, sourceSecondaryInventory, 
                             targetMainInventory, targetSecondaryInventory,
                             TxnSourceType.BATCH,"Move Order For Batch "+currentMoveOrder.getMtlTxnRequestLines().get(0).getGmeMaterialDetail().getBatch().getBatchId());
                
                    eventBus.publish("/subInvs",sourceSecondaryInventory.getSecondaryInventoryId()+","+targetSecondaryInventory.getSecondaryInventoryId()) ;

                    eventBus.publish("/batch",targetSecondaryInventory.getSecondaryInventoryId()) ;                  
                   
                    break;                    
               
                case RETURN_ORDER:
     
                    moveOrderService.transferReturnOrder(linesNeeded);
                    
                    break;
 

            }
        }

        return "view?faces-redirect=true";
    }
    

    public GlCodeCombination getTargetAccount() {
        return targetAccount;
    }

    public GlCodeCombination getSourceAccount() {
        return sourceAccount;
    }

    public Integer getMoveOrderId() {

        return moveOrderId;
    }

    public void setMoveOrderId(Integer moveOrderId) {

        this.moveOrderId = moveOrderId;
    }

    public MtlTxnRequestHeader getCurrentMoveOrder() {
        return currentMoveOrder;
    }

    public void setCurrentMoveOrder(MtlTxnRequestHeader currentMoveOrder) {
        this.currentMoveOrder = currentMoveOrder;
    }

    public MainInventory getSourceMainInventory() {
        return sourceMainInventory;
    }

    public MainInventory getTargetMainInventory() {
        return targetMainInventory;
    }

    public SecondaryInventory getTargetSecondaryInventory() {
        return targetSecondaryInventory;
    }

    public SecondaryInventory getSourceSecondaryInventory() {
        return sourceSecondaryInventory;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }






    public void setSenario(MoveOrderTransactionType type) {

        switch (type) {
            case MOVE_ORDER:
                this.source="inv";
                this.target="inv";
                break;
            case SALES_ORDER:
             this.source="inv";
                this.target="";
                break;
            case PURCHASE_ORDER:
                  this.source="inv";
                this.target="inv";
                break;
            case RETURN_ORDER:
                  this.source="acc";
                this.target="inv";
                break;
            case JOB_SCHEDULE:
                  this.source="inv";
                this.target="inv";
                break;
            case INTERNAL_ORDER:
                  this.source="inv";
                this.target="inv";
                break;
            case ACCOUNT_ALIAS:
                  this.source="inv";
                this.target="inv";
                break;
            case ACCOUNT:
                  this.source="inv";
                this.target="inv";
                break;
            case SHIPMENT_ORDER:
                  this.source="inv";
                this.target="acc";
                break;
         
        }
    }


    
}
