/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.inventory.MtlCategory;
import com.bridge.entities.inventory.ItemTemplate;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.SystemItemType;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.categories.MtlCategoryService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.ItemTemplateService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class ItemTemplateDetailsController implements Serializable {

    @EJB
    private ItemTemplateService itemTemplateService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private UomLineService uomLineService;
    @EJB
    private MtlCategoryService categoryService;
    @EJB
    private FaCategoryService aCategoryService;

    private ItemTemplate currentItemTemplate;
    private Integer currentItemId;
    private List<MainInventory> mainInventoryList;
    private List<UomLine> unitList;
    private List<MtlCategory> categoryList;
    private SystemItemType[] itemTypes;
    private MtlCategory currentCategory;

    public SystemItemType[] getItemTypes() {
        return SystemItemType.values();
    }

    @EJB
    private OrganizationUnitService orgUnitService;
    private List<OrganizationUnit> businessGroupList;

    public List<OrganizationUnit> completeOrgUnit(String text) {

        businessGroupList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return businessGroupList;
    }

    public ItemTemplate getCurrentItemTemplate() {
        if (currentItemTemplate == null) {
            currentItemTemplate = new ItemTemplate();
            currentItemTemplate.setEnabledFlag(true);
        }
        return currentItemTemplate;
    }

    public void setCurrentItemTemplate(ItemTemplate currentItemTemplate) {
        this.currentItemTemplate = currentItemTemplate;
    }

    public Integer getCurrentItemId() {
        return currentItemId;
    }

    public void setCurrentItemId(Integer currentItemId) {
        this.currentItemId = currentItemId;
    }

    public MtlCategory getCurrentCategory() {
        return null;
    }

    public void setCurrentCategory(MtlCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<FaCategory> completeCategory(String text) {

        return aCategoryService.findCategoryByName(text);

    }

    public void loadItem() {
        
        if (currentItemId != null) {
            
            currentItemTemplate = itemTemplateService.findTemplateWithCategories(currentItemId);
            
            if(currentItemTemplate == null){
                JSFUtils.redirectTo404("Item Template");
            }

        }

    }

    public List<MtlCategory> getCategoryList() {
        return categoryList;
    }

    public List<MainInventory> getMainInventoryList() {
        return mainInventoryList;
    }

    public void setMainInventoryList(List<MainInventory> mainInventoryList) {
        this.mainInventoryList = mainInventoryList;
    }

    public List<UomLine> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<UomLine> unitList) {
        this.unitList = unitList;
    }

    public void addLine() {

        categoryList = currentItemTemplate.getCategoryList();
        if (categoryList == null) {
            categoryList = new ArrayList<MtlCategory>();
        }

        categoryList.add(currentCategory);
    }

    public List<MtlCategory> completeCategories(String text) {
        return categoryService.findCategoryByName(text);
    }

    @PostConstruct
    public void loadDetails() {
        unitList = uomLineService.findAll();
        mainInventoryList = mainInvService.findAll();
    }
//    public List<UomLine> completeWeightUnits(String text) {
//
//        unitList = uomLineService.findUomByName(text);
//
//        return unitList;
//    }
//
//    public List<MainInventory> completeMainInv(String text) {
//
//        mainInventoryList = mainInvService.findByName(text);
//
//        return mainInventoryList;
//    }

    public String save() {
        itemTemplateService.update(currentItemTemplate);
        return "systemItemTemplateView?faces-redirect=true";
    }

}
