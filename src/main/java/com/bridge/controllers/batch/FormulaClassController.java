/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.batch;
import com.bridge.entities.batch.FormulaClass;
import com.bridge.services.batch.FormulaClassService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author AIA
 */
@Model
public class FormulaClassController  {
    
    @EJB
    private FormulaClassService formulaClassService;
    
    private List<FormulaClass> formulaClassesList;
    private Integer status;
    private Integer formulaClassId;
    private String formulaClassName;
    private String formulaClassCode;
    

    public List<FormulaClass> getFormulaClassesList(){
        
        return formulaClassesList;
    }

    public Integer getStatus(){
        return status;
    }
        public Integer getFormulaClassId(){
        return formulaClassId;
    }
   
       public String getFormulaClassName(){
        return formulaClassName;
    }
       
         public String getFormulaClassCode(){
        return formulaClassCode;
    }
       public void setStatus(Integer status){
           this.status=status;
       }
       public void setFormulaClassId(Integer formulaClassId){
           this.formulaClassId=formulaClassId;
       }
       public void setFormulaClassName(String formulaClassName){
           this.formulaClassName=formulaClassName;
       }
         public void setFormulaClassCode(String formulaClassCode){
           this.formulaClassCode=formulaClassCode;
       }
    public void filter(){
        formulaClassesList=formulaClassService.findFormulaClass(formulaClassCode, formulaClassName, status);
    }
 
}
