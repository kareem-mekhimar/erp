/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;
import com.bridge.entities.inventory.UomClass;
import com.bridge.services.inventory.UomClassService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class UomClassController implements Serializable{
    @EJB
    private UomClassService uomClassService;
    private UomClass uomClass;
    private List<UomClass> uomClassList;
    private Integer classStatus;
    private Integer classId;
    private String className;
    private String classCode;
    
 
    public List<UomClass> getUomClassList(){
        
        return uomClassList;
    }
       public UomClass getUomClass(){
           return uomClass;
       }
       public void setUomClass(UomClass uomClass){
           this.uomClass=uomClass;
       }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
       
    public void filter(){
        uomClassList = uomClassService.findUomClass(classId, className, classStatus, classCode);     
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

   
}
