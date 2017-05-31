/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.inventory.ItemTemplate;
import com.bridge.enums.SystemItemType;
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
public class SystemItemTemplateController implements Serializable {
    
    @EJB
    private ItemTemplateService service ;
    
    private List<ItemTemplate> itemTemplates ;
    
    private String name ;
    
    private int id ;
    
    private Boolean status;
    
    private SystemItemType itemType;
    
    private SystemItemType[] itemTypes;

    public SystemItemType[] getItemTypes() {
        return SystemItemType.values();
    }

    public SystemItemType getItemType() {
        return itemType;
    }

    public void setItemType(SystemItemType itemType) {
        this.itemType = itemType;
    }

    public List<ItemTemplate> getItemTemplates() {
        
        return itemTemplates;
    }
    
    public void filter()
    {
        itemTemplates = service.findItemTemplate( name,itemType, status) ;
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


    
}
