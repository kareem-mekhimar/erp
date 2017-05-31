/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;

import com.bridge.entities.batch.Formula;
import com.bridge.enums.FormulaStatus;
import com.bridge.services.batch.FormulaService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class FormulaController implements Serializable{
    
    @EJB
    private FormulaService formulaService;
    
    private FormulaStatus [] statuses ;
    private List<Formula> formulaList;
    private Integer formulaId;
    private String formulaNo;
    private String formulaName;
    private FormulaStatus formulaStatus;
    private List<Formula> availableFormula;



    public List<Formula> getAvailableFormula() {
        return availableFormula;
    }

    public void setAvailableFormula(List<Formula> availableFormula) {
        this.availableFormula = availableFormula;
    }
    
    public FormulaService getFormulaService() {
        return formulaService;
    }

    public void setFormulaService(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    public List<Formula> getFormulaList() {
        return formulaList;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    
    public void setFormulaList(List<Formula> formulaList) {
        this.formulaList = formulaList;
    }

    public Integer getFormulaId() {
        return formulaId;
    }

    public FormulaStatus getFormulaStatus() {
        return formulaStatus;
    }

    public void setFormulaStatus(FormulaStatus formulaStatus) {
        this.formulaStatus = formulaStatus;
    }

    
    public void setFormulaId(Integer formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

        public List<Formula> completeFormula(String text)
    {
        availableFormula = formulaService.findByName(text);
       
       return availableFormula ;
    }
    public void filter(){
        formulaList = formulaService.findFormula(formulaId, formulaName,formulaStatus, formulaNo);
    }

    public FormulaStatus[] getStatuses() {
        return FormulaStatus.values() ;
    }
    
    
}
