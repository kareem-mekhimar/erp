/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;


import com.bridge.entities.clients.ApSupplier;
import com.bridge.services.clients.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author AIA
 */
@Model
public class ApSuppliersController implements Serializable{
    
    @EJB
    private ApSupplierService apSupplierService;
    private ApSupplier apSupplier;
    private List<ApSupplier> apSupplierList;
    private Integer enabledFlagId;
    private Integer vendorId;
    private String vendorName;
    

    public List<ApSupplier> getApSupplierlist(){        
        return apSupplierList;
    }

    public Integer getEnabledFlagId() {
        return enabledFlagId;
    }

    public void setEnabledFlagId(Integer enabledFlagId) {
        this.enabledFlagId = enabledFlagId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

       public ApSupplier getApSupplier(){
           return apSupplier;
       }
       public void setApSupplier(ApSupplier apSupplier){
           this.apSupplier = apSupplier;
       }
       
    public void filter(){
        
        apSupplierList = apSupplierService.findSuppliers(vendorId, vendorName, enabledFlagId);    
//        apSupplierList.sort(Comparator.comparing(ApSupplier::getVendorId));
    }

   
}
