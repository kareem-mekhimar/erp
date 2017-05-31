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
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.SystemItemType;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.categories.MtlCategoryService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SystemItemService;
import com.bridge.services.inventory.ItemTemplateService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class SystemItemDetailsController implements Serializable {

    @EJB
    private SystemItemService systemItemService;
    @EJB
    private ItemTemplateService systemItemTemplateService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private UomLineService uomLineService;
    @EJB
    private MtlCategoryService categoryService;
    @EJB
    private FaCategoryService aCategoryService;
    @EJB
    private OrganizationUnitService orgUnitService;

    private List<OrganizationUnit> businessGroupList;
    private SystemItem systemItem;
    private SystemItem currentSystemItem;
    private ItemTemplate currentItemTemplate;
    private Integer currentItemId;
    private Integer currentTempId;
    private List<ItemTemplate> templatList;
    private List<MainInventory> mainInventoryList;
    private List<UomLine> unitList;
    private List<MtlCategory> categoryList;
    private MtlCategory currentCategory;
    private SystemItemType[] itemTypes;
    private String flag;
    private String itid;

    public SystemItemType[] getItemTypes() {
        return SystemItemType.values();
    }

    public Integer getCurrentTempId() {
        return currentTempId;
    }

    public void setCurrentTempId(Integer currentTempId) {
        this.currentTempId = currentTempId;
    }

    public List<OrganizationUnit> completeOrgUnit(String text) {

        businessGroupList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.BUSINESS_GROUP, text);

        return businessGroupList;
    }

    public SystemItem getCurrentSystemItem() {
        if (currentSystemItem == null) {
            currentSystemItem = new SystemItem();
            currentSystemItem.setEnabledFlag(true);
        }
        return currentSystemItem;
    }

    public void setCurrentSystemItem(SystemItem currentSystemItem) {
        this.currentSystemItem = currentSystemItem;
    }

    public Integer getCurrentItemId() {
        return currentItemId;
    }

    public void setCurrentItemId(Integer currentItemId) {
        this.currentItemId = currentItemId;
    }

    public SystemItem getSystemItem() {

        return systemItem;
    }

    public void setSystemItem(SystemItem systemItem) {
        this.systemItem = systemItem;
    }

    public MtlCategory getCurrentCategory() {
        return null;
    }

    public void setCurrentCategory(MtlCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

    public SystemItem convertToItem(ItemTemplate template) {
        systemItem = new SystemItem();

        // abdalrahman fathy: 9/4/2017: enforce the user to enter the code/name
        systemItem.setInventoryItemCode(null);
        systemItem.setDescription(null);

        systemItem.setSystemItemType(template.getItemTypeId());
        systemItem.setBusinessGroupId(template.getBusinessGroupId());
        systemItem.setPrimaryUomId(template.getPrimaryUomId());
        systemItem.setInventoryItemFlag(template.getInventoryItemFlag());
        systemItem.setStockEnabledFlag(template.getStockEnabledFlag());
//        systemItem.setAssetCategory(template.getAssetCategory());
        systemItem.setEnabledFlag(true);
        systemItem.setMtlTransactionsEnabledFlag(template.getMtlTransactionsEnabledFlag());
        systemItem.setCycleCountEnabledFlag(template.getCycleCountEnabledFlag());
        systemItem.setBomEnabledFlag(template.getBomEnabledFlag());
        systemItem.setBomItemType(template.getBomItemType());
        systemItem.setCostingEnabledFlag(template.getCostingEnabledFlag());
        systemItem.setPurchasingEnabledFlag(template.getPurchasingEnabledFlag());
        systemItem.setRfqRequiredFlag(template.getRfqRequiredFlag());
        systemItem.setTaxableFlag(template.getTaxableFlag());
        systemItem.setAllowSubstituteReceiptsFlag(template.getAllowSubstituteReceiptsFlag());
        systemItem.setAllowUnorderedReceiptsFlag(template.getAllowUnorderedReceiptsFlag());
        systemItem.setAllowExpressDeliveryFlag(template.getAllowExpressDeliveryFlag());
        systemItem.setUnitWeight(template.getUnitWeight());
        systemItem.setUnitVolume(template.getUnitVolume());
        systemItem.setUnitLength(template.getUnitLength());
        systemItem.setUnitHeight(template.getUnitHeight());
        systemItem.setUnitWidth(template.getUnitWidth());
        systemItem.setServiceItemFlag(false);
        systemItem.setCategoryList(template.getCategoryList());
        systemItem.setCustomerOrderFlag(template.getCustomerOrderFlag());
        return systemItem;

    }

    public List<FaCategory> completeCategory(String text) {

        return aCategoryService.findCategoryByName(text);

    }

    public void addLine() {

        categoryList = currentSystemItem.getCategoryList();
        if (categoryList == null) {
            categoryList = new ArrayList<MtlCategory>();
        }

        categoryList.add(currentCategory);
    }

    public List<MtlCategory> completeCategories(String text) {
        return categoryService.findCategoryByName(text);
    }

    public void loadItem() {

        if (currentTempId != null) {

            currentItemTemplate = systemItemTemplateService.findTemplateWithCategories(currentTempId);

            if (currentItemTemplate == null) {

                JSFUtils.redirectTo404("Item Template");
            }

            currentSystemItem = convertToItem(currentItemTemplate);

        } else if (currentItemId != null) {

            currentSystemItem = systemItemService.findItemWithCategories(currentItemId);

            if (currentSystemItem == null) {

                JSFUtils.redirectTo404("System Item");

            }
        }

    }

    public List<MtlCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<MtlCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public void setCurrentItemTemplate(ItemTemplate currentItemTemplate) {
        this.currentItemTemplate = currentItemTemplate;
    }

    public ItemTemplate getCurrentItemTemplate() {
        return currentItemTemplate;
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

    public List<ItemTemplate> getTemplatList() {
        return templatList;
    }

    public void setTemplatList(List<ItemTemplate> templatList) {
        this.templatList = templatList;
    }

    public List<UomLine> completeWeightUnits(String text) {

        unitList = uomLineService.findUomByName(text);

        return unitList;
    }

    public void validateCodeExist(FacesContext context, UIComponent component, Object value) {

        UIInput input = (UIInput) component;

        if (currentItemId != null) {

            if (input.getValue() != null && input.getValue().equals(value)) {
                return;
            }

        }

        if (systemItemService.isCodeExists((String) value)) {

            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public void validateDescExist(FacesContext context, UIComponent component, Object value) {

        UIInput input = (UIInput) component;

        if (currentItemId != null) {

            if (input.getValue() != null && input.getValue().equals(value)) {
                return;
            }
        }

        if (systemItemService.isDescExists((String) value)) {

            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    @PostConstruct
    public void loadDetails() {
        unitList = uomLineService.findAll();
        mainInventoryList = mainInvService.findAll();
    }

    public String save() {
        currentSystemItem.setPrimaryUomCode(currentSystemItem.getPrimaryUomId().getUomCode());
        systemItemService.update(currentSystemItem);
        return "systemItemView?faces-redirect=true";
    }

    public SystemItem createNewItemOfType(SystemItemType type) {

        systemItem = new SystemItem();

        systemItem.setSystemItemType(type);
        systemItem.setEnabledFlag(true);

        return systemItem;

    }

    public String getItid() {
        return itid;
    }

    public void setItid(String itid) {
        this.itid = itid;
    }

}
