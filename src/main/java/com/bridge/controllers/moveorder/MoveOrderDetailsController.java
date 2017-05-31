/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.moveorder;

import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.MtlInventoryItemsQuantity;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.enums.FormulaLineType;
import com.bridge.enums.MoveHeaderStatus;
import com.bridge.enums.MoveLineStatus;
import com.bridge.enums.MoveOrderTransactionType;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.moveorder.MoveOrderService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class MoveOrderDetailsController implements Serializable{
    
    @EJB
    private MoveOrderService moveOrderService ;
 
    @EJB
    private MainInvService mainInvService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private SystemItemService systemItemService ;
    
    @EJB    
    private TransactionService transactionService ;
    
    private Integer currentHeaderId ;

    private MtlTxnRequestHeader currentHeader ;
    
    private MainInventory sourceMainInventory ;
    
    private MainInventory targetMainInventory ;
    
    private SecondaryInventory sourceSecondaryInventory ;
    
    private SecondaryInventory targetSecondaryInventory ;

    private List<MtlInventoryItemsQuantity> currentQuantitys ;
       
    private int maxNo ;
    
    public void loadHeader()
    {
        if(currentHeaderId != null)
        {
           currentHeader = moveOrderService.findWithLines(currentHeaderId) ;
           
           if(currentHeader == null)
           {
               JSFUtils.redirectTo404("Move Order");
           }
           else
           {
              List<MtlTxnRequestLine> lines = currentHeader.getMtlTxnRequestLines() ;
              
              MtlTxnRequestLine line = lines.get(0) ;
              
              sourceMainInventory = line.getFromOrganization() ;
              
              targetMainInventory = line.getToOrganization() ;
              
              sourceSecondaryInventory = line.getFromSubinventory() ;
              
              targetSecondaryInventory = line.getToSubinventory() ;
              
              lines.forEach(l -> {
               
                 l.setActualQtyInInventory(transactionService.findActualQuantityInInventory(l.getInventoryItem(),
                           l.getFromSubinventory()));
              }); 
              
              maxNo = lines.size() ;
           }
           
        }
        
    }
    
 
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
    
    
    public List<SystemItem> completeSystemItems(String text)
    {
        List<MtlTxnRequestLine> lines = currentHeader.getMtlTxnRequestLines() ;

        MtlTxnRequestLine currentLine = JSFUtils.evaluateValueExpression("#{line}", MtlTxnRequestLine.class) ;
        
        if(lines.size() > 1)
        {
           String idsDelemited = lines.stream()
                                      .filter(l -> l != currentLine)
                                      .map(l -> l.getInventoryItem().getInventoryItemId()+"")
                                      .collect(Collectors.joining(",")) ;
                   
           return systemItemService.findGoodsByNameAndNotIn(text, idsDelemited) ;
        }
        
        return systemItemService.findGoodsByName(text) ;
    }
       
    public void resetSubInvSource()
    {
      sourceSecondaryInventory = null ;
    }
    
    public void resetSubInvTarget()
    {
      targetSecondaryInventory = null ;  
    }
    
    
    public void onSourceSubInvChange()
    {
       resetSubInvTarget();
       
       List<MtlTxnRequestLine> lines = currentHeader.getMtlTxnRequestLines() ;
       
       if(lines != null && ! lines.isEmpty())
       {
            //   asas
       }
    }
    
    
    public void onChangeCheckQuantityCover(MtlTxnRequestLine line)
    {
        if(sourceSecondaryInventory == null)
            
            return; 
        
        checkQtyAndDisplayWarning(line);
    }
    
    
    private void checkQtyAndDisplayWarning(MtlTxnRequestLine line)
    {
        BigDecimal qtyNeeded = line.getQuantity() ;
        
        UIComponent comp = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()) ;
        
        if(qtyNeeded == null)
        {
            return;
        }
        
        BigDecimal actualQtyInInv = line.getActualQtyInInventory();
        
        if(qtyNeeded.compareTo(actualQtyInInv) > 0)
        {
            String clientId = comp.getClientId();

            FacesContext.getCurrentInstance().addMessage(clientId, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Current Quantity Doesn't Cover This Quantity", null));
        }
    }
    
    
    public void onItemChange(MtlTxnRequestLine line)
    {
       SystemItem systemItem = line.getInventoryItem() ;
       
       line.setUomCode(systemItem.getPrimaryUomCode());
    
       if(sourceSecondaryInventory != null)
       {
          line.setActualQtyInInventory(transactionService.findActualQuantityInInventory(systemItem, sourceSecondaryInventory));
       }
    }
    
    
    public void addLine()
    {
        List<MtlTxnRequestLine> lines = currentHeader.getMtlTxnRequestLines() ;
       
        if(lines == null)
           
           lines = new ArrayList<>() ;
       
        MtlTxnRequestLine line = new MtlTxnRequestLine() ;
         
        lines.add(line) ;
        
        line.setLineNumber(++ maxNo);
        
        line.setHeader(currentHeader);

        currentHeader.setMtlTxnRequestLines(lines);
    }
    
      
    public String save()
    {
        List<MtlTxnRequestLine> lines = currentHeader.getMtlTxnRequestLines() ;
        
        if(lines == null || lines.isEmpty())
        {
            RequestContext.getCurrentInstance().execute("UIkit.modal.confirm(\"This Move Order has no lines and willn't be saved .. Continue ..?\", function(){back();});");
            
            return null ;
        }
        
        currentHeader.setTransactionType(MoveOrderTransactionType.MOVE_ORDER);
        
        lines.forEach(l -> {
  
               l.setFromOrganization(sourceMainInventory);
               
               l.setFromSubinventory(sourceSecondaryInventory);
               
               l.setToOrganization(targetMainInventory);
               
               l.setToSubinventory(targetSecondaryInventory);  
               
               l.setLineStatus(MoveLineStatus.INCOMPELETE);
               
               l.setTransactionType(MoveOrderTransactionType.MOVE_ORDER);
        });
        
        moveOrderService.update(currentHeader) ;
        
        return "view?faces-redirect=true" ;
    }
    
    
    public MainInventory getSourceMainInventory() {
        return sourceMainInventory;
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

    public MainInventory getTargetMainInventory() {
        return targetMainInventory;
    }

    public SecondaryInventory getTargetSecondaryInventory() {
        return targetSecondaryInventory;
    }

    public void setTargetSecondaryInventory(SecondaryInventory targetSecondaryInventory) {
        this.targetSecondaryInventory = targetSecondaryInventory;
    }

    public void setTargetMainInventory(MainInventory targetMainInventory) {
        this.targetMainInventory = targetMainInventory;
    }
    
    public Integer getCurrentHeaderId() {
        return currentHeaderId;
    }

    public void setCurrentHeaderId(Integer currentHeaderId) {
        this.currentHeaderId = currentHeaderId;
    }

    public List<MtlInventoryItemsQuantity> getCurrentQuantitys() {
    
        return currentQuantitys;
    }

    public MtlTxnRequestHeader getCurrentHeader() {
    
        if(currentHeader == null)
            
            currentHeader = new MtlTxnRequestHeader() ;
        
        return currentHeader;
    }
    
    
    
}
