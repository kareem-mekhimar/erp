/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.inventory.MainInventory;
import com.bridge.services.inventory.MainInvService;
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
public class MainInvController implements Serializable{
    
    @EJB
    private MainInvService mainInvService;
    
    private Integer  invId;
    private String invCode;
    private String invName;
    
    private List<MainInventory> mainInvList;
    private MainInventory currentMainInv;

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public List<MainInventory> getMainInvList() {
        return mainInvList;
    }

    public void setMainInvList(List<MainInventory> mainInvList) {
        this.mainInvList = mainInvList;
    }

    public MainInventory getCurrentMainInv() {
        return currentMainInv;
    }

    public void setCurrentMainInv(MainInventory currentMainInv) {
        this.currentMainInv = currentMainInv;
    }
    
    public void filter() {
        mainInvList=mainInvService.findMainInv(invId, invCode, invName);
    }
    
}
