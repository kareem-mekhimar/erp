/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.services.batch.BatchService;
import com.bridge.services.moveorder.MoveOrderService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class PickController implements Serializable{
    
    @EJB
    private MoveOrderService moveOrderService ;
    
    @EJB
    private TransactionService transactionService ;
    
    @EJB
    private MainInvService mainInvService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private COAAccountService accountService ;
    
    private MainInventory sourceMainInventory ;
    
    private MainInventory targetMainInventory ;
    
    private SecondaryInventory sourceSecondaryInventory ;
    
    private SecondaryInventory targetSecondaryInventory ;
    
    private GlCodeCombination targetAccount ;

    private List<GmeMaterialDetail> lines ;
    
    private GmeBatchHeader batch ;
    
    private Integer newMoveOrderId ;
    
    private OrganizationUnit organizationOfBatch ;
    
    @PostConstruct
    private void init()
    {
       Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap() ;
        
       organizationOfBatch = (OrganizationUnit) map.get("org") ;
       
       lines = (List<GmeMaterialDetail>) map.get("lines") ;
       
       batch = (GmeBatchHeader) map.get("batch") ;

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
               .stream().filter( s -> ! s.getSecondaryInventoryId().equals(sourceSecondaryInventory.getSecondaryInventoryId()))
               .collect(Collectors.toList()) ; 
    }
    
    
    public List<GlCodeCombination> completeAccountsByName(String text)
    {
        return accountService.findByAlias(text) ;
    }
    
    public void resetSubInvSource()
    {
      sourceSecondaryInventory = null ; 
      
      lines.forEach(l -> {
         l.setQuantityNeededToPickOrTransact(null);
      });
    }
    
    public void onSourceSubInvItemSelect()
    {
       resetSubInvTarget();
       
       lines.forEach(l -> {
       
           l.setQuantityNeededToPickOrTransact(null);
           
           l.setActualQtyInInventory(transactionService.findActualQuantityInInventory(l.getInventoryItem(), 
                   sourceSecondaryInventory));
       });
    }
    
    public void resetSubInvTarget()
    {
      targetSecondaryInventory = null;  
    }

    public List<GmeMaterialDetail> getLines() {
    
        return lines;
    }

    public MainInventory getTargetMainInventory() {
        return targetMainInventory;
    }

    public MainInventory getSourceMainInventory() {
        return sourceMainInventory;
    }

    public void setSourceSecondaryInventory(SecondaryInventory sourceSecondaryInventory) {
        this.sourceSecondaryInventory = sourceSecondaryInventory;
    }

    public void setSourceMainInventory(MainInventory sourceMainInventory) {
        this.sourceMainInventory = sourceMainInventory;
    }

    
    public GlCodeCombination getTargetAccount() {
        return targetAccount;
    }

    public SecondaryInventory getSourceSecondaryInventory() {
        return sourceSecondaryInventory;
    }

    public SecondaryInventory getTargetSecondaryInventory() {
        return targetSecondaryInventory;
    }

    public void setTargetMainInventory(MainInventory targetMainInventory) {
        this.targetMainInventory = targetMainInventory;
    }

    public void setTargetAccount(GlCodeCombination targetAccount) {
        this.targetAccount = targetAccount;
    }

    public void setTargetSecondaryInventory(SecondaryInventory targetSecondaryInventory) {
        this.targetSecondaryInventory = targetSecondaryInventory;
    }

    public GmeBatchHeader getBatch() {
        return batch;
    }

    
    public Integer getNewMoveOrderId() {
        return newMoveOrderId;
    }
       
    
    public void save()
    {        
        List<MtlTxnRequestLine> requestLines = lines.stream()
                .filter(l -> l.getQuantityNeededToPickOrTransact()!= null)
                .map(MtlTxnRequestLine::new)
                .collect(Collectors.toCollection(ArrayList::new));
        
        if(! requestLines.isEmpty())
        {
           MtlTxnRequestHeader header = new MtlTxnRequestHeader(batch,organizationOfBatch);
     
           header.setMtlTxnRequestLines(requestLines);
      
           moveOrderService.create(header,sourceMainInventory, targetMainInventory, sourceSecondaryInventory, targetSecondaryInventory);
           
           newMoveOrderId = header.getHeaderId() ;
        }
        
        RequestContext.getCurrentInstance().execute("modal.show()");
    }
    
   
    public void validateDate(FacesContext context , UIComponent component , Object value)
    {
        UIInput quantityInput = (UIInput) component.findComponent("q") ;
        
        BigDecimal quantity = (BigDecimal) quantityInput.getValue() ;
        
        if(quantity != null && quantity.compareTo(BigDecimal.ZERO) > 0)
        {
           Date val = (Date) value;
           
           if(val == null)
               
               throw new ValidatorException(new FacesMessage("Date Can't be Null")) ;
        }
    }
    
    public void validateQuantityGtZero(FacesContext context , UIComponent component , Object value)
    {
       BigDecimal quantity = (BigDecimal) value;
       
       if(quantity == null)
           
           return ;
       
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero", "Value Can't Be Zero")) ;
       }
                  
    }
    
    public void onChangeCheckQuantityCover(GmeMaterialDetail materialDetail)
    {
        BigDecimal qtyNeeded = materialDetail.getQuantityNeededToPickOrTransact();
        
        UIComponent comp = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()) ;
        
        if(qtyNeeded == null)
        {
            return;
        }
        
        BigDecimal actualQtyInInv = materialDetail.getActualQtyInInventory() ;
        
        if(qtyNeeded.compareTo(actualQtyInInv) > 0)
        {
            String clientId = comp.getClientId();
        
            RequestContext.getCurrentInstance().addCallbackParam("warn", true);
            
            FacesContext.getCurrentInstance().addMessage(clientId, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Current Quantity Doesn't Cover This Quantity", null));
        }
    }
    
    public String onDialogOk()
    {
        return "batchDetails?faces-redirect=true&id="+batch.getBatchId();
    }
}
