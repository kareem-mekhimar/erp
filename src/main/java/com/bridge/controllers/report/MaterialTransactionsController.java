/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.report;

import com.bridge.controllers.qualifiers.Report;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.ReportFormat;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class MaterialTransactionsController implements Serializable{
   
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private SystemItemService systemItemService ;
    
    @Inject
    @Report(Report.ReportType.TRANSACTIONS)
    private Event<ReportPayload> reportEvent ;
    
    private OrganizationUnit businessGroup ;
    
    private OrganizationUnit legal ;
    
    private OrganizationUnit operating ;

    private SecondaryInventory secInv ;
    
    private SystemItem item;
        
    private Date startDate ;
    
    private Date endDate ;

    public List<OrganizationUnit> completeBusinessGroup(String text)
    {
       return organizationUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text) ;
    }

    public List<OrganizationUnit> completeLegalEntity(String text)
    {
        if(businessGroup != null)
            
            return organizationUnitService.findByTypeAndNameByParentId(text, OrganizationUnitType.LEGAL_ENTITY, businessGroup.getOrgUnitId()) ;
        
        return null ;
    }
    
    public List<OrganizationUnit> completeOperatingunit(String text)
    {
       if(legal != null)
           
            return organizationUnitService.findByTypeAndNameByParentId(text, OrganizationUnitType.OPERATING_UNIT, legal.getOrgUnitId()) ;

       return null ;
    }
    

    public List<SecondaryInventory> completeSubInv(String text)
    {
        if(operating != null)
            
            return subInvService.findByOperatingUnitAndName(operating.getOrgUnitId(), text) ;
        
        return null ;
    }    
    
    public List<SystemItem> completeSystemItems(String text)
    {
       return systemItemService.findByName(text) ;
    }
    
    public void onBusinessGroupChange()
    {
       legal = null ;
       
       operating = null ;

       secInv = null ;
    }
    
    public void onLegalChange()
    {
       operating = null ;
       
       secInv = null ;
    }
    
    public void onOperatingChange()
    {
        secInv = null ;
    }
 
 
    public void generateReport(ReportFormat format)
    {
        HashMap<String,Object> map = new HashMap<>() ;
            
        map.put("logo", FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/imgs/bridge-logo3.png")) ;
                    
        map.put("operatingUnitId", operating.getOrgUnitId()) ;
            
        map.put("startDate", startDate) ;
        
        map.put("endDate", endDate) ;
        
        if(secInv != null)
                
                map.put("subInvId", secInv.getSecondaryInventoryId()) ;
            
        if(item != null)
                
                map.put("itemId", item.getInventoryItemId()) ;
                              
        reportEvent.fire(new ReportPayload(map, format,"Material Transactions"));
    }
    
    
    public void validateEndDate(FacesContext context , UIComponent component , Object value)
    {
        if(startDate == null)
            
            return;
        
        Date endDate = (Date) value ;
        
        if(endDate.compareTo(startDate) < 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Invalid Date"
                       ,"Invalid Date" )) ;           
            
    }
    
    public Event<ReportPayload> getReportEvent() {
        return reportEvent;
    }

    public void setReportEvent(Event<ReportPayload> reportEvent) {
        this.reportEvent = reportEvent;
    }

    public OrganizationUnit getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(OrganizationUnit businessGroup) {
        this.businessGroup = businessGroup;
    }

    public OrganizationUnit getLegal() {
        return legal;
    }

    public void setLegal(OrganizationUnit legal) {
        this.legal = legal;
    }

    public OrganizationUnit getOperating() {
        return operating;
    }

    public void setOperating(OrganizationUnit operating) {
        this.operating = operating;
    }


    public SecondaryInventory getSecInv() {
        return secInv;
    }

    public void setSecInv(SecondaryInventory secInv) {
        this.secInv = secInv;
    }

    public SystemItem getItem() {
        return item;
    }

    public void setItem(SystemItem item) {
        this.item = item;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
