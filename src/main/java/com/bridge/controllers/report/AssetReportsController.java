/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.report;

import com.bridge.controllers.qualifiers.Report;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.ReportFormat;
import com.bridge.services.organization.OrganizationUnitService;
import java.io.Serializable;
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

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class AssetReportsController implements Serializable {

    @EJB
    private OrganizationUnitService organizationUnitService;

    @Inject
    @Report(Report.ReportType.ASSET_VIEW)
    private Event<ReportPayload> reportEvent ;
      
    private OrganizationUnit businessGroup;

    private OrganizationUnit legal;

    private OrganizationUnit operating;
    

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

    public List<OrganizationUnit> completeBusinessGroup(String text) {
        return organizationUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);
    }

    public List<OrganizationUnit> completeLegalEntity(String text) {
        if (businessGroup != null) {
            return organizationUnitService.findByTypeAndNameByParentId(text, OrganizationUnitType.LEGAL_ENTITY, businessGroup.getOrgUnitId());
        }

        return null;
    }

    public List<OrganizationUnit> completeOperatingunit(String text) {
        if (legal != null) {
            return organizationUnitService.findByTypeAndNameByParentId(text, OrganizationUnitType.OPERATING_UNIT, legal.getOrgUnitId());
        }

        return null;
    }
    
      
    public void generateReport(ReportFormat format)
    {
        try {
           
            HashMap<String,Object> map = new HashMap<>() ;
            
            map.put("imagePath", FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/imgs/bridge-logo3.png")) ;
                    
            map.put("operating", operating.getOrgUnitId()) ;

             reportEvent.fire(new ReportPayload(map, format,"Assets View"));
                
        } catch (Exception ex) {
            Logger.getLogger(AssetReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
    }

}
