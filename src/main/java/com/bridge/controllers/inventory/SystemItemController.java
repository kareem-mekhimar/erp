/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.inventory.ItemTemplate;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.SystemItemType;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.ItemTemplateService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class SystemItemController implements Serializable {

    @EJB
    private SystemItemService service;

    @EJB
    private ItemTemplateService systemItemTemplateService;

    private List<SystemItem> systemItems;

    private String name;

    private int id;

    private String code;

    private Boolean status;

    private SystemItemType itemType;

    private SystemItemType[] itemTypes;

    private ItemTemplate itemTemplate;

    public SystemItemType[] getItemTypes() {
        return SystemItemType.values();
    }

    public SystemItemType getItemType() {
        return itemType;
    }

    public void setItemType(SystemItemType itemType) {
        this.itemType = itemType;
    }

    public List<SystemItem> getSystemItems() {

        return systemItems;
    }

    public void filter() {
        systemItems = service.findMainSystemItems(code, name, itemType, status);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public ItemTemplate getItemTemplate() {
        return itemTemplate;
    }

    public void setItemTemplate(ItemTemplate itemTemplate) {
        this.itemTemplate = itemTemplate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ItemTemplate> completeTemplate(String text) {

       return systemItemTemplateService.findByName(text);
    }

    public String redirect() {

        return "systemItemDetails?faces-redirect=true&tempId=" + itemTemplate.getTemplateId();
    }

    public void resetTemplate() {
        itemTemplate = null;
    }

}
