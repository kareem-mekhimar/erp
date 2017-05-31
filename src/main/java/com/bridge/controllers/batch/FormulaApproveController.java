/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.batch.Formula;
import com.bridge.enums.FormulaStatus;
import com.bridge.services.batch.FormulaService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class FormulaApproveController implements Serializable {

    @EJB
    private FormulaService formulaService;
    @Inject
    private NotificationController notificationController;
    private List<Formula> selectedFormulas;
    private List<Formula> formulaList;
    private String formulaName;
    private Integer formulaId;
    private String formulaNo;
    private FormulaStatus formulaStatus;
    private Integer formulaType;

    public Integer getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(Integer formulaType) {
        this.formulaType = formulaType;
    }

    public List<Formula> getFormulaList() {
        return formulaList;
    }

    public List<Formula> getSelectedFormulas() {
        return selectedFormulas;
    }

    public void setFormulaList(List<Formula> formulas) {
        this.formulaList = formulas;
    }

    public void setSelectedFormulas(List<Formula> selectedFormulas) {
        this.selectedFormulas = selectedFormulas;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Integer getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Integer formulaId) {
        this.formulaId = formulaId;
    }

    public FormulaStatus getFormulaStatus() {
        return formulaStatus;
    }

    public void setFormulaStatus(FormulaStatus formulaStatus) {
        this.formulaStatus = formulaStatus;
    }

    public void filter() {
        
        if (formulaStatus == null) {
            formulaList = null;
        } else {
            formulaList = formulaService.findFormula(formulaNo, formulaName, formulaStatus);
        }
        
    }

    public void approveSelectedFormulas() {

        String ids = selectedFormulas.stream().map(p -> p.getFormulaId().toString()).collect(Collectors.joining(","));

        formulaService.approveFormulas(ids);

        filter();

        notificationController.showNotification("Formula(s)with No(s) = " + ids + " Approved.!", NotificationController.SUCCESS);

        selectedFormulas = null;
    }

    public void unApproveSelectedFormulas() {
        String ids = selectedFormulas.stream().map(p -> p.getFormulaId().toString()).collect(Collectors.joining(","));

        formulaService.unApproveFormulas(ids);

        filter();

        notificationController.showNotification("Formula(s)with No(s) = " + ids + " Unapproved.!", NotificationController.SUCCESS);

        selectedFormulas = null;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }
    
    

}
