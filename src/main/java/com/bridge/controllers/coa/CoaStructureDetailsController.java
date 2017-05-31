/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.coa;

import com.bridge.entities.coa.CoaSegment;
import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.StructureStatus;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.Separators;
import com.bridge.services.coa.CoaStructureService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class CoaStructureDetailsController implements Serializable {

    @EJB
    private CoaStructureService coaStructureService;
    @Inject
    private OrganizationUnitService orgUnitService;
   
    private CoaStructure currentCoaStructure;
    private CoaSegment currentCoaSegment;
    private Integer currentStructureId;
    private Integer currentSegmentId;
    private List<OrganizationUnit> orgUnitList;
    private Integer sequence;
    private Separators[] separators;
    private StructureStatus[] coaStatus;
    private boolean needToSave ;
    private boolean coaDisabled;
    private int clickedSegmentBeforeSaveIndex ;
    private List<CoaSegment> dependableSegments;
    private Integer roles;
    
    public boolean getCoaDisabled() {
        return coaDisabled;
    }

    public void setCoaDisabled(boolean coaDisabled) {
        this.coaDisabled = coaDisabled;
    }
    
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getCurrentStructureId() {
        return currentStructureId;
    }

    public void setCurrentStructureId(Integer currentStructureId) {
        this.currentStructureId = currentStructureId;
    }

    public Separators[] getSeparators() {
        return Separators.values();
    }

    public StructureStatus[] getCoaStatus() {
        return StructureStatus.values();
    }

    public List<CoaSegment> getDependableSegments() {
      
        return dependableSegments;
    }

    
    public void loadStructure() {

        if (currentStructureId != null) {

            currentCoaStructure = coaStructureService.findCoaStructureWithLines(currentStructureId);
            
            if(currentCoaStructure == null){
                
                JSFUtils.redirectTo404("COA Structure");
                
            }
            
            //This Line For ( Enable / Disable ) Qualifiers Checkboxs & Edit , Add on coaStructureDetails
            if(currentCoaStructure.getCoaStatus().equals(StructureStatus.LOCKED))coaDisabled=true;
            
            currentCoaStructure.getCoaSegmentList()
                    .sort(Comparator.comparing(CoaSegment::getSequenceNumber));
            
//            FacesContext context = FacesContext.getCurrentInstance() ;
//            
//            UIViewRoot viewRoot = context.getViewRoot() ;
//            
//            viewRoot.visitTree(vi, new VisitCallback() {
//                @Override
//                public VisitResult visit(VisitContext context, UIComponent target) {
//                    
//                    return VisitResult.COMPLETE;
//                }
//            } );
        }

    }
    
   
  public int numOfRoles(){
      roles=0;
      List<CoaSegment>list=currentCoaStructure.getCoaSegmentList();
      list.stream().forEach(a -> {if(a.getBalancingSegment()==true){roles+=1;}});
      list.stream().forEach(a -> {if(a.getCostCenterSegment()==true){roles+=1;}});
      list.stream().forEach(a -> {if(a.getNaturalAccountSegment()==true){roles+=1;}});
      return roles;
  }
    
         public void setRole(CoaSegment seg,Integer role){
        
             List<CoaSegment>list = currentCoaStructure.getCoaSegmentList();
             switch (role) {
                 case 1:
                     if(seg.getBalancingSegment()==true){  
                     list.forEach(a -> {if( a != seg){a.setBalancingSegment(false);}});}
                     break;
                 case 2:
                     if(seg.getCostCenterSegment()==true){
                     list.forEach(a -> {if(a != seg){a.setCostCenterSegment(false);}});}
                     break;
                 case 3:
                     if(seg.getNaturalAccountSegment()==true){
                     list.forEach(a -> {if(a != seg){a.setNaturalAccountSegment(false);}});}
                     break;     
             }
             
       
    }

     public void loadDependableSegments(CoaSegment segment){
         
         dependableSegments=new ArrayList<CoaSegment>();
         if(segment==null){
             dependableSegments.addAll(currentCoaStructure.getCoaSegmentList());
         }else{
            dependableSegments.addAll(currentCoaStructure.getCoaSegmentList().stream()
                    .filter(s-> s.getSequenceNumber()<segment.getSequenceNumber())
                    .collect(Collectors.toList()));  
         }

     }    
         
    public void loadModal(CoaSegment segment) {

            currentCoaSegment = segment;
            loadDependableSegments(segment);
    }
  

    public String redirectToSegmentDetails(int rowIndexOfLastSegment)
    {
       this.clickedSegmentBeforeSaveIndex = rowIndexOfLastSegment ;
                  
       if(needToSave)
       {
           RequestContext.getCurrentInstance().execute("saveModal.show();");
           
           return null ;
       }
       
       CoaSegment segment = currentCoaStructure.getCoaSegmentList().get(clickedSegmentBeforeSaveIndex) ;
       
       return "coaSegmentDetails?faces-redirect=true&id="+segment.getSegmentId();
    }
    
    public String saveThenRedirect()
    {
       currentCoaStructure = coaStructureService.update(currentCoaStructure) ;
       
       needToSave = false ;
       
       return redirectToSegmentDetails(clickedSegmentBeforeSaveIndex) ;
     
    }
    
    public CoaSegment getCurrentCoaSegment() {
     
        if(currentCoaSegment == null)
            
            currentCoaSegment = new CoaSegment() ;
        
        return currentCoaSegment;
    }

    public void setCurrentCoaSegment(CoaSegment currentCoaSegment) {
     
         
            
        this.currentCoaSegment = currentCoaSegment;

     
        
    }

    
    public Integer getCurrentSegmentId() {
        return currentSegmentId;
    }

    public void setCurrentSegmentId(Integer currentSegmentId) {
        this.currentSegmentId = currentSegmentId;
    }

    public CoaStructure getCurrentCoaStructure() {
        if (currentCoaStructure == null) {
            currentCoaStructure = new CoaStructure();
            currentCoaStructure.setCoaSegmentList(new ArrayList<>());
            currentCoaStructure.setEnabledFlagId(true);
        }
        return currentCoaStructure;
    }

    public void setCurrentCoaStructure(CoaStructure currentCoaStructure) {
        this.currentCoaStructure = currentCoaStructure;
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        UIInput firstDateComp = (UIInput) component.findComponent("sad");

        Date firstDate = (Date) firstDateComp.getValue();

        Date secondDate = (Date) value;

        if (firstDate == null) {
            return;
        }
        
        // 23/5/2017
        if (secondDate!=null && firstDate.after(secondDate)) {        
        //if (firstDate.after(secondDate)) {
            throw new ValidatorException(new FacesMessage("Invalid Date!"));
        }
    }

    
    public List<OrganizationUnit> completeBusinessGroup(String text) {
        
        orgUnitList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);
        return orgUnitList;
        
    }
    
    public void onNoOnlyClick()
    {
       needToSave = true ;
                
       if(currentCoaSegment.getNumbersOnlyFlagId())
           
           currentCoaSegment.setUppercaseOnlyFlagId(Boolean.FALSE);
    }
    
    public void onUpperCaseClick()
    {
        needToSave = true ;
      
        if(currentCoaSegment.getUppercaseOnlyFlagId())
           
           currentCoaSegment.setNumbersOnlyFlagId(Boolean.FALSE);
    }
    
    
      public void addLine() {
  
//          List<CoaSegment> lines = currentCoaStructure.getCoaSegmentList() ;
//          
//          if(lines == null)
//          {
//              lines = new ArrayList<>() ;
//           
//              currentCoaStructure.setCoaSegmentList(lines);
//          }
          currentCoaSegment.setDependentTypeId(currentCoaSegment.getDependentOnSegmentSequence()!=null);
                  
          sequence = currentCoaStructure.getCoaSegmentList().size();
              
          sequence++;
            
          if(currentCoaSegment.getCoaStructure() == null)
          {     

            currentCoaSegment.setSequenceNumber(sequence);
            
            currentCoaSegment.setCoaStructure(currentCoaStructure);
      
            currentCoaStructure.getCoaSegmentList().add(currentCoaSegment);
            
          }
          
          needToSave = true ;
          
    }
      
      

    public void setNeedToSave(boolean needToSave) {
        this.needToSave = needToSave;
    }

      
    public String save() {
      if(numOfRoles()<3){ 
          
          RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please Check Three Roles!');");
            
            return null ;
      }
        coaStructureService.update(currentCoaStructure);   
        
        return "coaStructureView?faces-redirect=true";
    }
}
