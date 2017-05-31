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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class InventoryCreditController implements Serializable{
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private MainInvService mainInvService ;
    
    @EJB
    private SubInvService subInvService ;
    
    @EJB
    private SystemItemService systemItemService ;
    
    @Inject
    @Report(Report.ReportType.INVENTORY_CREDIT)
    private Event<ReportPayload> reportEvent ;
    
    private OrganizationUnit businessGroup ;
    
    private OrganizationUnit legal ;
    
    private OrganizationUnit operating ;
    
    private MainInventory mainInv ;
    
    private SecondaryInventory secInv ;
    
    private SystemItem item;
    
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
    
    public List<MainInventory> completeMainInv(String text)
    {
        if(operating != null)
            
            return mainInvService.findByNameAndParentOperatingUnitId(text,operating.getOrgUnitId() ) ;
        
        return null ;
    }

    public List<SecondaryInventory> completeSubInv(String text)
    {
        if(mainInv != null)
            
            return subInvService.findByNameAndMainId(text, mainInv.getOrganizationId()) ;
        
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
       
       mainInv = null ;
       
       secInv = null ;
    }
    
    public void onLegalChange()
    {
       operating = null ;
       
       mainInv = null ;
       
       secInv = null ;
    }
    
    public void onOperatingChange()
    {
        mainInv = null ;
        
        secInv = null ;
    }
    
    public void onMainInvChange()
    {
        secInv = null ;
    }
    
    public void generateReport(ReportFormat format)
    {
        try {
           
            HashMap<String,Object> map = new HashMap<>() ;
            
            map.put("imagePath", FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/imgs/bridge-logo3.png")) ;
                    
            map.put("operatingUnitId", operating.getOrgUnitId()) ;
            
            if(mainInv != null)
                
                map.put("mainInvId", mainInv.getOrganizationId()) ;
            
            if(secInv != null)
                
                map.put("secInvId", secInv.getSecondaryInventoryId()) ;
            
            if(item != null)
                
                map.put("itemId", item.getInventoryItemId()) ;
            
             reportEvent.fire(new ReportPayload(map, format,"Inventory Organization"));
                
        } catch (Exception ex) {
            Logger.getLogger(InventoryCreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
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

    public MainInventory getMainInv() {
        return mainInv;
    }

    public void setMainInv(MainInventory mainInv) {
        this.mainInv = mainInv;
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
    
    
}
