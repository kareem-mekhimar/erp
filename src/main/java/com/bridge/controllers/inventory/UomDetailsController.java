/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

/**
 *
 * @author AIA
 */
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.inventory.UomClass;
import com.bridge.enums.UOMClassType;
import com.bridge.services.inventory.UomClassService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class UomDetailsController implements Serializable {

    @EJB
    private UomClassService uomClassService;

    @EJB
    private UomLineService uomLineService;

    private UomClass currentClass;

    private Integer currentUomClassId;

    public UomClass getCurrentClass() {

        if (currentClass == null) {
            currentClass = new UomClass();
            currentClass.setClassStatus(true);
        }

        return currentClass;
    }

    public void addLine() {

        List<UomLine> uomLines = currentClass.getUomLines();

        if (uomLines == null) {
            uomLines = new ArrayList<>();

            currentClass.setUomLines(uomLines);
        }

        UomLine line = new UomLine();
        line.setUomClass(currentClass);
        uomLines.add(line);

    }

    public void loadUomClass() {
        
        if (currentUomClassId != null) {
            
            currentClass = uomClassService.findUomWithLines(currentUomClassId);
            
            if(currentClass==null){
                
                JSFUtils.redirectTo404("UOM Class");
                
            }
        }

    }

    public String save() {

        if (currentClass.getUomLines() == null) {
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please add at least one unit!');");
            return null;
        } else if (currentClass.getUomLines().stream().filter(l -> l.getBaseUomFlagId()).count() != 1) {
            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please choose One base unit!');");
            return null;
        }
        uomClassService.update(currentClass);

        return "uomClassView?faces-redirect=true";
    }

    public void setCurrentClass(UomClass currentClass) {
        this.currentClass = currentClass;
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {

        UIInput input = (UIInput) component;

        if (currentUomClassId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (uomClassService.isNameExists((String) value)) {

            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateUomNameExist(FacesContext context, UIComponent component, Object value) {

        UIInput input = (UIInput) component;

        UomLine line = (UomLine) JSFUtils.evaluateValueExpression("#{line}", UomLine.class);

        if (line.getUomId() != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (uomLineService.isNameExists((String) value)) {

            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateUomCodeExist(FacesContext context, UIComponent component, Object value) {

        UIInput input = (UIInput) component;

        UomLine line = (UomLine) JSFUtils.evaluateValueExpression("#{line}", UomLine.class);

        if (line.getUomId() != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (uomLineService.isCodeExists((String) value)) {

            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void selectChange(UomLine line) {
        double o, r;
        o = line.getValuebybase();
        r = 1 / o;
        if (line.getBaseUomFlagId()) {
            currentClass.getUomLines().forEach(l -> {
                if (l != line) {
                    l.setBaseUomFlagId(false);
                }
                l.setValuebybase(l.getValuebybase() * r);
            });
            line.setValuebybase(1.0);
            line.setDisableFlagId(false);
        }

    }

    public Integer getCurrentUomClassId() {
        return currentUomClassId;
    }

    public void setCurrentUomClassId(Integer currentUomClassId) {
        this.currentUomClassId = currentUomClassId;
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {

        UIInput firstDateComp = (UIInput) component.findComponent("sad");

        Date firstDate = (Date) firstDateComp.getValue();

        Date secondDate = (Date) value;

        if (firstDate == null) {
            return;
        }

        if (firstDate.after(secondDate)) {
            throw new ValidatorException(new FacesMessage("Invalid Date!"));
        }
    }

    public UOMClassType[] getClassType() {        
        return UOMClassType.values() ;        
    }
    
}
