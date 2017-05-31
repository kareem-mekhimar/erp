/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.periods;

import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.periods.GlPeriodSet;
import com.bridge.enums.GLSetupSteps;
import com.bridge.enums.JournalBatchType;
import com.bridge.enums.PeriodStatus;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.periods.CalendarService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CalendarDetailsController implements Serializable{
    
    @EJB
    private CalendarService calendarService ;
    
    @EJB
    private JournalBatchService batchService;
    
    @EJB
    private GlLedgerService ledgerService ;
    
    private UIInput yearMenu ;
    
    private Integer currentCalendarId ;
    
    private GlPeriodSet currentCalendar ;
    
    private List<GlPeriodStatus> normalPeriods ;
    
    private List<GlPeriodStatus> adjustPeriods ;
    
    private Integer currentYear  ;
    
    private Integer newYear ;
    
    private int maxNormalNo ;
    
    private int maxAdjustNo ;
    
    private boolean needToSave ;

    ///////////////////////////////////////////////////////////
    
    private TreeNode [] selectedBatchOrJournals ;
    
    private TreeNode currentRecurringBatches  ;
    
    private GlPeriodStatus selectedPeriodStatus ;
    
    private String searchName ;
    
    public void loadCalendar()
    {
      if(currentCalendarId != null)
      {
          currentCalendar = calendarService.findById(currentCalendarId) ;
          
          if(currentCalendar == null)
              
              JSFUtils.redirectTo404("Calendar");
          
          else
          {
              currentYear = Year.now().getValue();

              initPeriods(currentYear);
          }
      }
    }
    

    private void initPeriods(int year)
    {
        normalPeriods = calendarService.findPeriodsForCalendarAndYear(currentCalendar, year,false) ;
              
        adjustPeriods = calendarService.findPeriodsForCalendarAndYear(currentCalendar, year,true) ;

        maxNormalNo = normalPeriods.size() ;
        
        maxAdjustNo = adjustPeriods.size() ;
    }
    
    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public void onYearValueChange(ValueChangeEvent event)
    {
       Integer newValue = (Integer) event.getNewValue() ;
       
       Integer oldValue = (Integer) event.getOldValue() ;

       if(newValue == null)
       {
          return;
       }
       
       RequestContext requestContext = RequestContext.getCurrentInstance() ;
       
       if(needToSave)
       {
           this.currentYear = oldValue ;
           
           this.newYear = newValue ;

           requestContext.execute("saveModal.show()");

           UIInput input = (UIInput) event.getComponent() ;
           
           input.setValue(oldValue);
           
           FacesContext.getCurrentInstance().renderResponse();

           return;
       }
             
        initPeriods(newValue);
        
        requestContext.update("normalTable");
        
        requestContext.update("adjustTable");
    }

    
    public void onOpenPeriod(GlPeriodStatus periodStatus)
    {
       this.selectedPeriodStatus = periodStatus ; 
       
       if(batchService.areRecurringExistForLedger(currentCalendar.getLedger()))
       {
           currentRecurringBatches = new CheckboxTreeNode(null,null) ;
           
           RequestContext.getCurrentInstance().update("batchesDialogContent");
           
           RequestContext.getCurrentInstance().execute("batchesModal.show()");
       }
       else
       {
          selectedPeriodStatus.setPeriodStatus(PeriodStatus.OPENED);

         if(selectedPeriodStatus.isAdjustmentPeriodFlag())
              
            RequestContext.getCurrentInstance().update("adjustTable");
          
         else
              
            RequestContext.getCurrentInstance().update("normalTable");   
       
          selectedPeriodStatus = null ;        
          
          needToSave = true ;
          
       }
   
    }

    
    public void onBatchesDialogOk()
    {
       if(selectedBatchOrJournals != null && selectedBatchOrJournals.length > 0)
           
            selectedPeriodStatus.setJournalHeaderIds(Arrays.stream(selectedBatchOrJournals)
                                .filter(n -> n.isLeaf())
                                .map(n -> ((GlJeHeader) n.getData()).getJeHeaderId() +"")
                                .collect(Collectors.joining(",")));
     
       selectedPeriodStatus.setPeriodStatus(PeriodStatus.OPENED);
       
       needToSave = true ;

       if(selectedPeriodStatus.isAdjustmentPeriodFlag())
              
            RequestContext.getCurrentInstance().update("adjustTable");
          
       else
              
           RequestContext.getCurrentInstance().update("normalTable");   
       
       selectedPeriodStatus = null ;
       
       currentRecurringBatches = null ;
       
       selectedBatchOrJournals = null ;
       
       searchName = null ;
    }

        
    public void onSaveDialogOk()
    {
        normalPeriods.addAll(adjustPeriods) ;
        
        calendarService.updatePeriods(normalPeriods);
        
        this.currentYear = this.newYear ;
        
        this.newYear = null ;
        
        initPeriods(currentYear) ;
        
        needToSave = false ;
        
        yearMenu.setValue(currentYear);
    }


    public void filterBatches()
    {
       List<GlJeBatch> batches = batchService.findByNameOrTypeAndLedger(searchName, JournalBatchType.RECURRING,currentCalendar.getLedger()) ;
    
       initTreeTable(batches);
    }
     
    private void initTreeTable(List<GlJeBatch> batches)
    {
        currentRecurringBatches = new CheckboxTreeNode(null, null) ;
        
        batches.forEach(b -> {
        
            TreeNode batchNode = new CheckboxTreeNode("batch",b, currentRecurringBatches) ;
            
            batchNode.setExpanded(true);
            
            b.getHeaders().forEach(h -> {
            
                TreeNode headerNode = new CheckboxTreeNode("header" ,h,batchNode) ;
                
                headerNode.setExpanded(true);
            });
        });
    }

    
    public String save()
    {
        if(isAdjustPeriodsIntersect())
        {
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Adjust Periods Overlap..!');");
            
            return null ;
        }
        
        normalPeriods.addAll(adjustPeriods) ;
        
        calendarService.updatePeriods(normalPeriods);
        
        ledgerService.completeOption(currentCalendar.getLedger(), GLSetupSteps.CALENDAR);
        
        return "/ledger/details?faces-redirect=true&id="+currentCalendar.getLedger().getLedgerId() ;
    }
    
    private boolean isAdjustPeriodsIntersect()
    {
       return adjustPeriods.stream().anyMatch(p -> {
           
           return adjustPeriods.stream()
                   .filter(innerP -> ! innerP.getPeriodNum().equals(p.getPeriodNum()))
                   .anyMatch(innerP -> areDatesIntersect(p, innerP)) ;
       }) ;
    }
    
    
    private boolean areDatesIntersect(GlPeriodStatus p1 , GlPeriodStatus p2)
    {
       LocalDate startDate1 = p1.getStartDate() ;
       
       LocalDate endDate1 = p1.getEndDate() ;
       
       LocalDate startDate2 = p2.getStartDate() ;
       
       LocalDate endDate2 = p2.getEndDate() ;
       
       return (startDate1.isEqual(endDate2) || startDate1.isBefore(endDate2)) && (startDate2.isEqual(endDate1) || startDate2.isBefore(endDate1)) ;
    }
    
    public void test()
    {
        System.out.println("Current = "+currentYear+" , newyear = "+newYear);
    }
    
    public void onToDateChange(GlPeriodStatus period)
    { 
       if(period.getPeriodNum() != normalPeriods.size())
       {
          GlPeriodStatus afterPeriod = normalPeriods.get(period.getPeriodNum()) ;
          
          LocalDate newStartDate = period.getEndDate().plus(1, ChronoUnit.DAYS) ;
          
          afterPeriod.setStartDate(newStartDate);
          
          LocalDate endDate = afterPeriod.getEndDate() ;
          
          if(endDate.equals(newStartDate) || newStartDate.isAfter(endDate))
              
              afterPeriod.setEndDate(null);
       }
       
       needToSave = true ;   
    }
    
    public void validateNormalToDate(FacesContext facesContext,UIComponent component , Object value)
    {
       if(value == null)
           
           return;
       
       LocalDate date = (LocalDate) value ;
       
       GlPeriodStatus period = JSFUtils.evaluateValueExpression("#{period}", GlPeriodStatus.class) ;
       
       LocalDate lastYearDate = Year.of(currentYear).atDay(1).with(TemporalAdjusters.lastDayOfYear()) ;
       
       if(period.getPeriodNum().equals(currentCalendar.getPeriodType().getAccountingPeriods()))
       {
           if(! lastYearDate.equals(date)) 
               
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "This Date must be "+lastYearDate
                       ,"This Date must be "+lastYearDate )) ;
       }
       else
       {
           if(lastYearDate.equals(date))
               
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "You Can't End Year here"
                       ,"You Can't End Year here" )) ;
       }
               
       if(currentYear != date.getYear())
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Must Be in The Same Year"
                       ,"Must Be in The Same Year" )) ;            
       }
       
       if(date.isBefore(period.getStartDate()) || date.isEqual(period.getStartDate()))
       {                   
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Must Be After "+period.getStartDate()
                       ,"Must Be After "+period.getStartDate())) ;
       }
        
       
    }
    
    public void validateAdjustToDate(FacesContext facesContext,UIComponent component , Object value)
    {
       if(value == null)
           
           return;
       
       LocalDate date = (LocalDate) value ;
               
       GlPeriodStatus period = JSFUtils.evaluateValueExpression("#{period}", GlPeriodStatus.class) ;
         
       if(currentYear != date.getYear())
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Must Be in The Same Year"
                       ,"Must Be in The Same Year" )) ;            
       }
              
       if(date.isBefore(period.getStartDate()))
       {                   
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Must Be After "+period.getStartDate()
                       ,"Must Be After "+period.getStartDate())) ;
       }           
    }
   
   public void validateAdjustFromDate(FacesContext facesContext,UIComponent component , Object value)
   {       
       if(value == null)
           
           return;
       
       LocalDate date = (LocalDate) value ;

       if(currentYear != date.getYear())
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Must Be in The Same Year"
                       ,"Must Be in The Same Year" )) ;            
       }
                  
   }
          
    public List<Integer> getAllYears()
    {
       Year todayYear = Year.now() ;
        
       Year start = todayYear.minusYears(20) ;
       
       Year end = todayYear.plusYears(20) ;
       
       List<Integer> allYears = new ArrayList<>() ;

       for(int i = start.getValue() ; i < todayYear.getValue() ; i++)
           
             allYears.add(i) ;
       
       for(int i = todayYear.getValue() ; i <= end.getValue() ; i++)
       
             allYears.add(i) ;
       
       return allYears;
    }
    
    public void addNormalPeriod()
    {
        GlPeriodStatus period = new GlPeriodStatus(currentCalendar, currentYear,false) ;
        
        if(maxNormalNo == 0)
            
            period.setStartDate(LocalDate.of(currentYear, Month.JANUARY, 1));
        
        else if(normalPeriods.size() > 0 )
        {
           GlPeriodStatus beforePeriod = normalPeriods.get(maxNormalNo - 1) ;
           
           period.setStartDate(beforePeriod.getEndDate().plus(1, ChronoUnit.DAYS));
        }
        
        period.setEndDate(period.getStartDate().with(TemporalAdjusters.lastDayOfMonth()));
        
        period.setPeriodNum(++ maxNormalNo);
        
        normalPeriods.add(period) ;
        
        needToSave = true ;
    }

    public void addAdjustPeriod()
    {
        GlPeriodStatus adjustPeriod = new GlPeriodStatus(currentCalendar, currentYear,true) ;
        
        adjustPeriods.add(adjustPeriod) ;
        
        adjustPeriod.setPeriodNum(++ maxAdjustNo); ;
        
        needToSave = true ;
    }
    
    public void setNeedToSave(boolean needToSave) {
        this.needToSave = needToSave;
    }

    public boolean isNeedToSave() {
        return needToSave;
    }
    
    
    public GlPeriodSet getCurrentCalendar() {
        return currentCalendar;
    }

    public Integer getCurrentCalendarId() {
        return currentCalendarId;
    }

    
    public GlPeriodStatus getSelectedPeriodStatus() {
        return selectedPeriodStatus;
    }

    public void setSelectedPeriodStatus(GlPeriodStatus selectedPeriodStatus) {
        this.selectedPeriodStatus = selectedPeriodStatus;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public TreeNode[] getSelectedBatchOrJournals() {
        return selectedBatchOrJournals;
    }

    public void setSelectedBatchOrJournals(TreeNode[] selectedBatchOrJournals) {
        this.selectedBatchOrJournals = selectedBatchOrJournals;
    }

    public TreeNode getCurrentRecurringBatches() {
        return currentRecurringBatches;
    }

    public void setCurrentRecurringBatches(TreeNode currentRecurringBatches) {
        this.currentRecurringBatches = currentRecurringBatches;
    }
    
    
    
    public void setCurrentCalendarId(Integer currentCalendarId) {
        this.currentCalendarId = currentCalendarId;
    }

    public List<GlPeriodStatus> getAdjustPeriods() {
        return adjustPeriods;
    }

    public int getMaxAdjustNo() {
        return maxAdjustNo;
    }

    public int getMaxNormalNo() {
        return maxNormalNo;
    }

    public List<GlPeriodStatus> getNormalPeriods() {
        return normalPeriods;
    }


    public UIInput getYearMenu() {
        return yearMenu;
    }

    public void setYearMenu(UIInput yearMenu) {
        this.yearMenu = yearMenu;
    }
    
    
}
