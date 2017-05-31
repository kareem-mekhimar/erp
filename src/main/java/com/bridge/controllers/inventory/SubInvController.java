/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.services.inventory.SubInvService;
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
public class SubInvController implements Serializable {

    @EJB
    private SubInvService subInvService;

    private Integer invId;
    private Boolean statusId;
    private String invName;
    private String invCode;

    private List<SecondaryInventory> subInvList;
    private SecondaryInventory currentSubInv;

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

    public List<SecondaryInventory> getSubInvList() {
        return subInvList;
    }

    public void setSubInvList(List<SecondaryInventory> subInvList) {
        this.subInvList = subInvList;
    }

    public SecondaryInventory getCurrentSubInv() {
        return currentSubInv;
    }

    public void setCurrentSubInv(SecondaryInventory currentSubInv) {
        this.currentSubInv = currentSubInv;
    }

    public Boolean getStatusId() {
        return statusId;
    }

    public void setStatusId(Boolean statusId) {
        this.statusId = statusId;
    }

    public void filter() {
        subInvList = subInvService.findByIdOrStatusOrNameOrCode(invId, statusId, invName, invCode);
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }
}
