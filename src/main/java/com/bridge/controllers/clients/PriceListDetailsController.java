/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.PriceListHeaders;
import com.bridge.entities.clients.PriceListLines;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.FreightTerms;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.PoLineType;
import com.bridge.enums.ListLineType;
import com.bridge.enums.SystemItemType;
import com.bridge.services.clients.PriceListHeaderService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named
@ViewScoped
public class PriceListDetailsController  implements Serializable {
    
    @EJB
    private PriceListHeaderService priceListHeaderService ;

    @EJB
    private OrganizationUnitService unitService ;

    @EJB
    private SystemItemService systemItemService ;
    
    private List<SystemItem> currentAvailbleSystemItems ;
    
    PriceListHeaders currentPriceList;
    
    private Integer currentListHeaderId ;
    
    private List<PriceListLines> priceListLines;
    
    ListLineType[] lineTypes;
    
    private List<String> removedIds=new ArrayList();
    
     public ListLineType[] getLineTypes() {
        return ListLineType.values();
    }
    
    public List<PriceListLines> getPriceListLines() {
        if (priceListLines == null) {
            priceListLines = new ArrayList<PriceListLines>();
        }
        return priceListLines;
    }

    public void setPriceListLines(List<PriceListLines> priceListLines) {
        this.priceListLines = priceListLines;
    }
    

    
    public Integer getCurrentListHeaderId() {
        return currentListHeaderId;
    }

    public void setCurrentListHeaderId(Integer currentListHeaderId) {
        this.currentListHeaderId = currentListHeaderId;
    }
    
    public PaymentTerms[] getPaymentTerms() {
        return PaymentTerms.values() ;
    }
    
    public FreightTerms[] getFreightTerms() {
        return FreightTerms.values() ;
    }
    
    public PriceListHeaders getCurrentPriceList() {
     
        if(currentPriceList == null){
            
            currentPriceList = new PriceListHeaders() ;
            currentPriceList.setActiveFlag(true);
        }
            
        
        return currentPriceList;
    }
    
     public void onItemSelectChange(PriceListLines line)
    {
        line.setListPriceUOM(line.getSystemItem().getPrimaryUomId());
    }
    
    public void loadCurrentPriceList()
    {
       if(currentListHeaderId != null)
       {
           currentPriceList = priceListHeaderService.findHeaderWithLinesByHeaderId(currentListHeaderId) ;
           
           if(currentPriceList == null){
               
               JSFUtils.redirectTo404("Price List");
               
           }
           
               priceListLines = currentPriceList.getPriceListLines();

           
       }
    }
   
       public void removeLine(PriceListLines line) {

        currentPriceList.getPriceListLines().remove(line);
        if (line.getListLineId() != null) {
            removedIds.add(line.getListLineId().toString());
        }
    }
    public void addLine()
    {
        List<PriceListLines> priceListLinesList = currentPriceList.getPriceListLines();
       
        if(priceListLinesList == null || priceListLinesList.isEmpty())
        {
            priceListLinesList = new ArrayList<>() ;
            
            currentPriceList.setPriceListLines(priceListLinesList);
        }
        
        PriceListLines line = new PriceListLines(); 
                
        priceListLinesList.add(line) ;
        
        line.setPriceListHeader(currentPriceList);
    }
    
    public String save()
    {
       priceListHeaderService.update(currentPriceList) ;  
           
        if (!removedIds.isEmpty()) {
            String lineIdsToBeRemoved = removedIds.stream().collect(Collectors.joining(","));

            priceListHeaderService.removeLines(lineIdsToBeRemoved);
        }
       return "priceListsView?faces-redirect=true";
    }

    public List<SystemItem> completeSystemItems(String text)
    {
        PriceListLines line =(PriceListLines) JSFUtils.evaluateValueExpression("#{line}", PriceListLines.class);
        
        ListLineType type =line.getLineType();
         
        if(type==ListLineType.ITEM){
            
            currentAvailbleSystemItems = systemItemService.findSalesProductByName(text);
            
        }else{
            
            currentAvailbleSystemItems = systemItemService.findSalesServiceByName(text);
            
        }
        
       
       
       return currentAvailbleSystemItems ;
    }
 
    public List<OrganizationUnit> completeOperatingUnitsByName(String text)
    {
       return unitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text) ;
    }
    
}
