/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.entities.batch.Formula;
import com.bridge.entities.batch.FormulaClass;
import com.bridge.entities.batch.FormulaLines;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.FormulaLineType;
import com.bridge.enums.FormulaStatus;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.SystemItemType;
import com.bridge.services.batch.FormulaClassService;
import com.bridge.services.batch.FormulaService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
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
public class FormulaDetailsController implements Serializable {

    
    @EJB
    private SystemItemService systemItemService;
    
    @EJB
    private FormulaService formulaService;

    @Inject
    private FormulaClassService formulaClassService;
    @Inject
    private OrganizationUnitService orgUnitService;
;

    private List<FormulaLines> productLines;
    private List<FormulaLines> byProductLines;
    private List<FormulaLines> ingredientLines;
    private List<FormulaClass> formulaClassList;
    
    private List<FormulaLines> linesToBeRemoved = new ArrayList<>() ;
    private Boolean readonly=false;
    private List<OrganizationUnit> operatingUnitList;
    private Formula currentFormula;
    private Integer currentFormulaId;
    private int  pnum, bnum, inum;


    public void setFormulaClassService(FormulaClassService formulaClassService) {
        this.formulaClassService = formulaClassService;
    }

    public Formula getCurrentFormula() {

        if (currentFormula == null) {

            currentFormula = new Formula();
            currentFormula.setStatus(FormulaStatus.NEW);
            currentFormula.setFormulaVers(1);
            currentFormula.setFormulaLines(new ArrayList<FormulaLines>());

        }

        return currentFormula;
    }

    public void setCurrentFormula(Formula currentFormula) {

        this.currentFormula = currentFormula;

    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }
    

    public Integer getCurrentFormulaId() {
        return currentFormulaId;
    }

    public void setCurrentFormulaId(Integer currentFormulaId) {
        this.currentFormulaId = currentFormulaId;
    }

    public List<FormulaClass> getFormulaClassList() {
        return formulaClassList;
    }

    public void setFormulaClassList(List<FormulaClass> formulaClassList) {
        this.formulaClassList = formulaClassList;
    }

    public List<OrganizationUnit> getOperatingUnitList() {
        return operatingUnitList;
    }

    public void setOperatingUnitList(List<OrganizationUnit> operatingUnitList) {
        this.operatingUnitList = operatingUnitList;
    }

    public FormulaStatus[] getStatuses() {
        return FormulaStatus.values();
    }

    public List<FormulaLines> getProductLines() {
        if (productLines == null) {
            productLines = new ArrayList<FormulaLines>();
      
        }
        return productLines;
    }

    public void setProductLines(List<FormulaLines> productLines) {
        this.productLines = productLines;
    }

    public List<FormulaLines> getByProductLines() {
        if (byProductLines == null) {
            byProductLines = new ArrayList<FormulaLines>();
              
        }
        return byProductLines;
    }

    public void setByProductLines(List<FormulaLines> byProductLines) {
        this.byProductLines = byProductLines;
    }

    public List<FormulaLines> getIngredientLines() {
        if (ingredientLines == null) {
            ingredientLines = new ArrayList<FormulaLines>();

        }

        return ingredientLines;
    }

    public void setIngredientLines(List<FormulaLines> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public Integer getPnum() {

        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    
    public List<FormulaClass> completeFormulaClass(String text) {
        formulaClassList = formulaClassService.findClassByName(text);

        return formulaClassList;
    }

    public void loadFormula() {

        if (currentFormulaId != null) {
            currentFormula = formulaService.findFormulaWithLines(currentFormulaId);
            if(currentFormula != null){
            productLines = currentFormula.getFormulaLines().stream().filter(l -> l.getLineType().equals(FormulaLineType.PRODUCT)).collect(Collectors.toList());
            byProductLines = currentFormula.getFormulaLines().stream().filter(l -> l.getLineType().equals(FormulaLineType.BYPRODUCT)).collect(Collectors.toList());
            ingredientLines = currentFormula.getFormulaLines().stream().filter(l -> l.getLineType().equals(FormulaLineType.INGREDIENT)).collect(Collectors.toList());
            pnum = productLines.size();
            bnum = byProductLines.size();
            inum = ingredientLines.size();
           if( currentFormula.getStatus() == FormulaStatus.APPROVED){
               readonly=true;
           }  
            }else{
                JSFUtils.redirectTo404("Formula");
            }
            
        }
    }

    public void addProductLine() {
  
        //currentFormula.setFormulaLines(productLines);
        FormulaLines line = new FormulaLines();
        line.setLineNo(++pnum);
        line.setLineType(FormulaLineType.PRODUCT);
        productLines.add(line);
        line.setFormula(currentFormula);
        currentFormula.getFormulaLines().add(line) ;
        

    }

    public void addByProductLine() {

        FormulaLines line = new FormulaLines();
        line.setLineNo(++bnum);
        line.setLineType(FormulaLineType.BYPRODUCT);
        byProductLines.add(line);
        line.setFormula(currentFormula);
        currentFormula.getFormulaLines().add(line) ;
    }

    public void addIngredientLine() {
  
        FormulaLines line = new FormulaLines();
        line.setLineNo(++inum);
        line.setLineType(FormulaLineType.INGREDIENT);
        ingredientLines.add(line);
        line.setFormula(currentFormula);
        currentFormula.getFormulaLines().add(line) ;

    }

    public void removeProductLine(FormulaLines line) {
        pnum--;
        if(line.getFormulalineId()!=null){
            
        linesToBeRemoved.add(line) ;

        }
        productLines.remove(line);

    }

    public void removeByProductLine(FormulaLines line) {
        bnum--;
       if(line.getFormulalineId()!=null){
           
        linesToBeRemoved.add(line) ;
       }

        byProductLines.remove(line);
    }

    public void removeIngredientLine(FormulaLines line) {
        inum--;
      if(line.getFormulalineId()!=null){
          
        linesToBeRemoved.add(line) ;
      }
 
        ingredientLines.remove(line);
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;

        if (currentFormulaId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (formulaService.isCodeExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;

        if (currentFormulaId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (formulaService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void switchTab()
    {
        String id = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tabId");

        RequestContext.getCurrentInstance().addCallbackParam("tabId", id);
    }
    
    public void onItemSelectChange(FormulaLines line) {
        
        line.setDetailUomCode(line.getSystemItem().getPrimaryUomCode());
    }

    public List<SystemItem> completeProducts(String text) {

        return systemItemService.findUnFormedProductsByName(text);
   
    }

    public List<SystemItem> completeByProducts(String text) {

        List<SystemItem> systemItems = systemItemService.findByNameAndType(text, SystemItemType.BY_PRODUCT) ;
        
        return systemItems.stream()
                .filter(s -> byProductLines
                        .stream()
                        .filter(ss -> ss.getSystemItem() != null)
                        .noneMatch(ss -> ss.getSystemItem().getInventoryItemId().equals(s.getInventoryItemId())))
                .collect(Collectors.toList());
    }

       public List<OrganizationUnit> completeOperatingUnit(String text) {

        operatingUnitList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

        return operatingUnitList;
    }
    
    public List<SystemItem> completeIngredients(String text) {

        List<SystemItem> systemItems = systemItemService.findIngredientByName(text) ;
        
        return systemItems.stream()
                .filter(s -> ingredientLines
                        .stream()
                        .filter(ss -> ss.getSystemItem() != null)
                        .noneMatch(ss -> ss.getSystemItem().getInventoryItemId().equals(s.getInventoryItemId())))
                .collect(Collectors.toList());
    }
    
    public String save() {

        if(pnum < 1 || inum < 1)
        {
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please add Product and Atleast one Ingredient!');");
            
            return null ;
        }
        
        formulaService.update(currentFormula);
        
        if(! linesToBeRemoved.isEmpty())
        {
            String lineIdsToBeRemoved = linesToBeRemoved.stream()
                .map(l -> String.valueOf(l.getFormulalineId()))
                .collect(Collectors.joining(",")) ;

            formulaService.removeLines(lineIdsToBeRemoved);
        }

        return "formulasView?faces-redirect=true";
    }

}
