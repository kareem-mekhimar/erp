/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.enums.FormulaLineType;
import com.bridge.services.batch.BatchService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class TransactController implements Serializable{
    
    @EJB
    private BatchService batchService ;

    @EJB
    private MainInvService mainInvService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private COAAccountService accountService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private MainInventory mainInventory ;
    
    private SecondaryInventory secondaryInventory ;
    
    private GlCodeCombination account ;
    
    private List<GmeMaterialDetail> lines ;
    
    private GmeBatchHeader batch ;
    
    private FormulaLineType currentFormulaLineType ;
    
    @PostConstruct
    private void init()
    {
      Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap() ;
      
      lines = (List<GmeMaterialDetail>) map.get("lines") ;
      
      currentFormulaLineType = lines.get(0).getLineType() ;
      
      batch = (GmeBatchHeader) map.get("batch") ;
    }
     
    
    public void validateGtZeroAndNotGtReserved(FacesContext context,UIComponent component ,Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero", "Value Can't Be Zero")) ;
       }
       
       if(currentFormulaLineType == FormulaLineType.INGREDIENT)
       { 
           BigDecimal reservedQty = JSFUtils.evaluateValueExpression("#{line.reservedQty}", BigDecimal.class) ;
           
           if(quantity.compareTo(reservedQty) > 0)
          
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                      "Value Can't Exceed Reserved Quantity", "Value Can't Exceed Reserved Quantity")) ;
       }
                   
    }
    
    
    public void onMessageRecieved()
    {
       FacesContext facesContext = FacesContext.getCurrentInstance() ;
        
       ExternalContext context = facesContext.getExternalContext() ;
       
       String msg = context.getRequestParameterMap().get("data") ;
        
       int targetInvId = Integer.parseInt(msg) ;
       
       if(secondaryInventory != null && secondaryInventory.getSecondaryInventoryId() == targetInvId)
       {
           lines = batchService.findIngredientLinesById(batch.getBatchId()) ;
           
           notificationController.showNotification("Some Quantites has been changed by another user..", msg);
       }
    }
    
    
    public String transact()
    {
        List<GmeMaterialDetail> linesNeeded = lines.stream()
                                 .filter(l -> l.getQuantityNeededToPickOrTransact() != null)
                                 .collect(Collectors.toList()) ;
               
        if(! linesNeeded.isEmpty())
        {   
            if(currentFormulaLineType == FormulaLineType.INGREDIENT)
            
                  batchService.transactIngredients(linesNeeded, secondaryInventory.getMainInv(), secondaryInventory, account);
            
            else if(currentFormulaLineType == FormulaLineType.PRODUCT)
            {
                 boolean done = batchService.transactProducts(linesNeeded,mainInventory, secondaryInventory, account) ;
            
                 if(! done)
                 {
                     RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Error..! You Cannot Transact Products Because Ingredients Doesnot Cover This Quantity');");
                     
                     return null ;
                 }
            }
        }
        
        return "batchDetails?faces-redirect=true&id="+batch.getBatchId() ;
    }
    
    
    public List<MainInventory> completeMainInvByName(String text)
    {
       return mainInvService.findByName(text) ;
    }
    
    public List<SecondaryInventory> completeSecondaryByName(String text)
    {
       if(mainInventory == null)
           
           return null ;
       
       return subInvService.findByNameForMain(text, mainInventory) ;
    }
    
    public List<SecondaryInventory> completeSubInv(String text)
    {
       return subInvService.findForBatchByName(batch, text) ;
    }
    
    public List<GlCodeCombination> completeAccountsByName(String text)
    {
        return accountService.findByAlias(text) ;
    }

    public void resetSubInvSource()
    {
      secondaryInventory = null ; 
    }
     
    public List<GmeMaterialDetail> getLines() {
    
        return lines;
    }
    
    
    public GlCodeCombination getAccount() {
        return account;
    }

    public MainInventory getMainInventory() {
        return mainInventory;
    }

    public SecondaryInventory getSecondaryInventory() {
        return secondaryInventory;
    }

    public void setMainInventory(MainInventory mainInventory) {
        this.mainInventory = mainInventory;
    }

    public void setAccount(GlCodeCombination account) {
        this.account = account;
    }

    public void setSecondaryInventory(SecondaryInventory secondaryInventory) {
        this.secondaryInventory = secondaryInventory;
    }

    public FormulaLineType getCurrentFormulaLineType() {
    
        return currentFormulaLineType;
    }
    
    
    public String save()
    {
        return transact();
    }
}
