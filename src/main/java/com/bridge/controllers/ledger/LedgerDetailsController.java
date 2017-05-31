/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.ledger;

import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlLedgerConfigDetail;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.entities.periods.GlPeriodType;
import com.bridge.enums.GlSetupStatus;
import com.bridge.enums.LedgerObjectTypeCode;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.periods.CalendarService;
import com.bridge.services.periods.PeriodTypeService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class LedgerDetailsController implements Serializable{
    
    private enum NavigateTypes {
      
        OPTIONS , CURRENCY ,ACCOUNTS , CALENDAR;
    }
    
    @EJB
    private GlLedgerService ledgerService ;
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private CalendarService calendarService ;
    
    @EJB
    private PeriodTypeService periodTypeService ;
    
    private Integer currentLedgerId ;

    private GlLedger currentLedger ;
    
    private List<GlLedgerConfigDetail> legalEntities ;
    
    private List<GlLedgerConfigDetail> basicConfigurations ;
    
    private OrganizationUnit currentLegalEntity ;

    private NavigateTypes lastClickedType ;
    
    private GlPeriodSet currentCalendar ;
    
    private boolean needToSave ;
    
    public void loadLedger()
    {
       if(currentLedgerId != null)
       {
           currentLedger = ledgerService.findWithBasicConfigById(currentLedgerId) ;
           
           if(currentLedger == null)
               
               JSFUtils.redirectTo404("Ledger");
           
           else
           {
               List<GlLedgerConfigDetail> details = currentLedger.getConfigDetails() ;
               
               legalEntities = details.stream()
                       .filter(d -> d.getObjectTypeCode() == LedgerObjectTypeCode.LEGAL_ENTITY)
                       .collect(Collectors.toList());
               
               basicConfigurations = details.stream()
                                  .filter(d -> d.getObjectTypeCode() == LedgerObjectTypeCode.CONFIGURATION)
                                  .collect(Collectors.toList()) ;
           }
       }
    }
    
    
     
    public List<GlPeriodType> completePeriods(String text)
    {
       return periodTypeService.findByName(text) ;
    }
    
     
    public void onAddLegal()
    {
       RequestContext requestContext = RequestContext.getCurrentInstance() ;
       
       if(currentLedger.getCompletionStatusCode() != GlSetupStatus.COMPLETED)
       {
           requestContext.execute("UIkit.modal.alert('You Cannot Add Legal Entity For Uncompleted Ledger..');");
       } 
       else
         
           requestContext.execute("legalModal.show();$('#legal_input').focus()");
    }
            
    
    private void addLegalEntity()
    {
       GlLedgerConfigDetail detail = new GlLedgerConfigDetail() ;
       
       detail.setObjectTypeCode(LedgerObjectTypeCode.LEGAL_ENTITY);
       
       legalEntities.add(detail) ;
       
       currentLedger.getConfigDetails().add(detail) ;
       
       detail.setObjectName(currentLegalEntity.getOrgUnitName());
       
       detail.setObjectId(currentLegalEntity.getOrgUnitId());
       
       detail.setLedger(currentLedger);
       
    }

    
    public void setCurrentLegalEntity(OrganizationUnit currentLegalEntity) {
        this.currentLegalEntity = currentLegalEntity;
    }
    
    
    public List<OrganizationUnit> completeLegalEntites(String text)
    {
       if(legalEntities == null || legalEntities.isEmpty())
           
           return organizationUnitService.findByOrgTypeByNameNotHaveLedger(OrganizationUnitType.LEGAL_ENTITY, text) ;
       
       String idsDelimited = legalEntities.stream()
               .map(l -> l.getObjectId()+"")
               .collect(Collectors.joining(",")) ;
       
       return organizationUnitService.findByOrgTypeByNameAndNotIn(OrganizationUnitType.LEGAL_ENTITY, text, idsDelimited) ;
    }
    
    
    
    public String save()
    {
        if(! legalEntities.isEmpty()){
          
            organizationUnitService.setLedgerToLegal(legalEntities.get(0).getObjectId(),currentLedger.getLedgerId());
        }
        
       ledgerService.update(currentLedger) ;

       return "view?faces-redirect=true" ;
    }
    
    public String editAction()
    {
        if(needToSave)
        {
            RequestContext.getCurrentInstance().execute("saveModal.show();");
            
            return null ;
            
        }
        
        if(lastClickedType == NavigateTypes.CALENDAR && currentLedger.getPeriodSet() == null)
        {
           RequestContext.getCurrentInstance().execute("calendarModal.show()");
           
           return null  ;
        }
        
        return getOutcomeBasedOnLastAction() ;       
    }
    
    public void onLegalDialogOk()
    {
       addLegalEntity();
       
       needToSave = true ;
       
       currentLegalEntity = null;
    }
    
    
    public String onCalendarDialogOk()
    {
        calendarService.create(currentCalendar);
            
        currentLedger.setPeriodSet(currentCalendar);
        
        ledgerService.update(currentLedger) ;
        
        return "/periods/calendarDetails?faces-redirect=true&id="+currentLedger.getPeriodSet().getPeriodSetId() ;
    }
    
    
    private String getOutcomeBasedOnLastAction()
    {
       String outcome = "" ;
       
        switch (lastClickedType) {
            case OPTIONS:
                 outcome = "options?faces-redirect=true&id="+currentLedger.getLedgerId() ;
                break;
            case CURRENCY:
                outcome = "reportingcurrencies?faces-redirect=true&id="+currentLedger.getLedgerId() ;
                break;
            case ACCOUNTS:
                outcome = "basicAccounts?faces-redirect=true&id="+currentLedger.getLedgerId() ;
                break;
                
            case CALENDAR :
                outcome = "/periods/calendarDetails?faces-redirect=true&id="+currentLedger.getPeriodSet().getPeriodSetId() ;
        }

       return outcome ;      
    }
    
    public String onSaveDialogOk()
    {
       currentLedger = ledgerService.update(currentLedger) ;
       
       return getOutcomeBasedOnLastAction()+"?faces-redirect=true&id="+currentLedger.getLedgerId() ;
    }
    
    public OrganizationUnit getCurrentLegalEntity() {
        return currentLegalEntity;
    }

    public void setLegalEntities(List<GlLedgerConfigDetail> legalEntities) {
        this.legalEntities = legalEntities;
    }
    
    
    public GlLedger getCurrentLedger() {
        return currentLedger;
    }

    public Integer getCurrentLedgerId() {
        return currentLedgerId;
    }

    public void setCurrentLedgerId(Integer currentLedgerId) {
        this.currentLedgerId = currentLedgerId;
    }

    public void setCurrentLedger(GlLedger currentLedger) {
        this.currentLedger = currentLedger;
    }

  
    public List<GlLedgerConfigDetail> getLegalEntities() {
      
        if(legalEntities == null)
            
            legalEntities = new ArrayList<>() ;
        
        return legalEntities;
    }

    public List<GlLedgerConfigDetail> getBasicConfigurations() {
        return basicConfigurations;
    }

    public GlPeriodSet getCurrentCalendar() {
        
        if(currentCalendar == null)
            
            currentCalendar = new GlPeriodSet() ;
        
        return currentCalendar;
    }

    public void setCurrentCalendar(GlPeriodSet currentCalendar) {
        this.currentCalendar = currentCalendar;
    }

    
    public boolean isNeedToSave() {
        return needToSave;
    }

    public void setNeedToSave(boolean needToSave) {
        this.needToSave = needToSave;
    }

    public NavigateTypes getLastClickedType() {
        return lastClickedType;
    }

    public void setLastClickedType(NavigateTypes lastClickedType) {
        this.lastClickedType = lastClickedType;
    }

    
    
}
