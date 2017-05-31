/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.entities.batch.FormulaLines;
import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.FormulaLineType;
import com.bridge.services.batch.BatchService;
import com.bridge.services.batch.FormulaService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
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
public class BatchDetailsController implements Serializable{
    
    private enum BatchActions
    {
        PICK , TRANSACT;
    }
    
    @EJB
    private BatchService batchService ;
    
    @EJB
    private FormulaService formulaService ;
    
    @EJB
    private SystemItemService systemItemService ;
    
    private GmeBatchHeader currentBatch ;

    private Integer currentBatchId ;
    
    private List<GmeMaterialDetail> productsLines ;
    
    private List<GmeMaterialDetail> byProductsLines ;
    
    private List<GmeMaterialDetail> ingLines ;
    
    private int pNum , byNum,ingNum ;
    
    private boolean needToSave ;
    
    private FormulaLineType lastPickLineType ;
    
    private BatchActions lastAction ;
    
    public void loadBatch()
    {
       if(currentBatchId != null)
       {
           currentBatch = batchService.findWithLines(currentBatchId) ;
           
          if(currentBatch == null)
           
             JSFUtils.redirectTo404("Batch");
                  
           initLines();
       }
       else
           
           JSFUtils.redirectTo404("Batch");
    }
    

    public List<FormulaLines> completeProductFormulaLinesBySystemItem(String text)
    {   
       String idsDelimited = productsLines.stream()
                                         .filter(l -> l.getFormulaLine() != null)
                                         .map(l -> String.valueOf(l.getFormulaLine().getSystemItem().getInventoryItemId())) 
                                         .collect(Collectors.joining(",")) ;
       
       return formulaService.findProductLinesForApprovedFormulaBySystemItemNameAndNotIn(text, idsDelimited);
    }
    
    
    public List<SystemItem> completeSystemItemByTypeAndName(String text)
    {
       GmeMaterialDetail currentLine = JSFUtils.evaluateValueExpression("#{line}", GmeMaterialDetail.class) ;
       
       List<GmeMaterialDetail> neededList ; 
       
       switch(currentLine.getLineType())
       {
           case BYPRODUCT :
               
               neededList = byProductsLines ;
               
               break;
               
           default :
               
               neededList = ingLines ;
       }
       
       String delemitedIds = neededList.stream()
                                       .filter(l -> l.getInventoryItem() != null)
                                       .map(l -> String.valueOf(l.getInventoryItem().getInventoryItemId()))
                                       .collect(Collectors.joining(",")) ;
       
       return systemItemService.findByNameAndTypeAndNotIn(text,neededList.get(0).getInventoryItem().getSystemItemType(), delemitedIds);
    }
    
    private void initLines()
    {
       List<GmeMaterialDetail> lines = currentBatch.getGmeMaterialDetailList() ;
       
       productsLines = lines.stream().filter(l -> l.getLineType() == FormulaLineType.PRODUCT)
               .sorted(Comparator.comparing(GmeMaterialDetail::getLineNo))
               .collect(Collectors.toList()) ;

       byProductsLines = lines.stream().filter(l -> l.getLineType() == FormulaLineType.BYPRODUCT)
               .sorted(Comparator.comparing(GmeMaterialDetail::getLineNo))
               .collect(Collectors.toList()) ;
       
       ingLines = lines.stream().filter(l -> l.getLineType() == FormulaLineType.INGREDIENT)
               .sorted(Comparator.comparing(GmeMaterialDetail::getLineNo))
               .collect(Collectors.toList()) ;
       
       pNum = productsLines.stream().mapToInt(GmeMaterialDetail::getLineNo).max().orElse(0) ;
       
       byNum = byProductsLines.stream().mapToInt(GmeMaterialDetail::getLineNo).max().orElse(0) ;
       
       ingNum = ingLines.stream().mapToInt(GmeMaterialDetail::getLineNo).max().orElse(0) ;
    }
    
    public void onProductItemSelectChange(GmeMaterialDetail line)
    {
       line.initFromFormulaLine(line.getFormulaLine());
    }
    
    public void onSystemItemSelectChange(GmeMaterialDetail line)
    {
       line.setDtlUm(line.getInventoryItem().getPrimaryUomCode());
    }
    
    public void addProductLine()
    {
       productsLines.add(createLine(FormulaLineType.PRODUCT, ++pNum)) ;
    }
    
    public void addByProductLine()
    {
       byProductsLines.add(createLine(FormulaLineType.BYPRODUCT, ++byNum)) ;
    }
    
    public void addIngLine()
    {
       ingLines.add(createLine(FormulaLineType.INGREDIENT, ++ingNum)) ; 
    }
    
    private GmeMaterialDetail createLine(FormulaLineType type , int lineNo)
    {
       GmeMaterialDetail line = new GmeMaterialDetail() ;
      
       line.setEditable(true); 
       
       line.setLineNo(lineNo);
       
       line.setLineType(type);
       
       line.setBatch(currentBatch);
       
       currentBatch.getGmeMaterialDetailList().add(line) ;
       
       needToSave = true ;
       
       return line ;
    }
    
    public GmeBatchHeader getCurrentBatch() {
       
        if(currentBatch == null)
             
            currentBatch = new GmeBatchHeader() ;
        
        return currentBatch;
    }

    public void setCurrentBatch(GmeBatchHeader currentBatch) {
        this.currentBatch = currentBatch;
    }

    public List<GmeMaterialDetail> getIngLines() {
        return ingLines;
    }

    public List<GmeMaterialDetail> getProductsLines() {
        return productsLines;
    }

    public List<GmeMaterialDetail> getByProductsLines() {
        return byProductsLines;
    }

    public Integer getCurrentBatchId() {
        return currentBatchId;
    }

    public void setCurrentBatchId(Integer currentBatchId) {
        this.currentBatchId = currentBatchId;
    }
    
    public String pick(FormulaLineType type) // if there are new lines then validate , if ok save the go to pick
    {
       this.lastPickLineType = type ;
       
       this.lastAction = BatchActions.PICK ;
       
       if(needToSave)
       {
           RequestContext.getCurrentInstance().execute("modal.show()");
           
           return null;
       }
       
       Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap() ;
       
       requestMap.put("batch",currentBatch) ;
       
       requestMap.put("org", currentBatch.getBusinessGroup()) ;

       requestMap.put("lines", ingLines) ;
               
       return "pick" ;
    }
    
      
    public String transact(FormulaLineType type)
    {
       this.lastPickLineType = type ;
       
       this.lastAction = BatchActions.TRANSACT ;
       
       if(needToSave)
       {
           RequestContext.getCurrentInstance().execute("modal.show()");
           
           return null;
       } 
       
       Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap() ;
       
       requestMap.put("batch", currentBatch) ;
       
       switch(type)
       {
           case PRODUCT :
              
               requestMap.put("lines", productsLines) ;
               
               break;
               
           case BYPRODUCT :
               
               requestMap.put("lines",byProductsLines) ;
               
               break;
               
           default:
               
               requestMap.put("lines", ingLines) ;
       }
       
       return "transact" ;
    }
    
    public void switchTab()
    {
        String id = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tabId");

        RequestContext.getCurrentInstance().addCallbackParam("tabId", id);
    }
    
    public String saveBatchThenDoAction()
    {
      saveBatch();
      
      if(lastAction == BatchActions.PICK)
          
          return pick(lastPickLineType) ;
      
      else
          
          return transact(lastPickLineType) ;
    }
    
    private void saveBatch()
    {
      batchService.update(currentBatch) ;  
      
      needToSave = false ;
    }

    public void setNeedToSave(boolean needToSave) {
        
        this.needToSave = needToSave;
    }

    public boolean isNeedToSave() {
        return needToSave;
    }
    
    
    public String save()
    {
       saveBatch();
        
       return "batchView?faces-redirect=true" ;
    }
    
}
