/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.categories;

import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AssetInterval;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class FaCategoryDetailsController implements Serializable {

    @EJB
    private FaCategoryService categoryService;
    @EJB
    private OrganizationUnitService orgUnitService;

    @EJB
    private COAAccountService accountService;

    private FaCategory category;

    private List<FaCategory> parentCategories;

    private Integer currentCategoryId;

    private AssetInterval[] intervals;

    private boolean parent;

    public FaCategory getCategory() {

        if (category == null) {
            category = new FaCategory();
            category.setEnabledFlagId(true);
        }
        return category;
    }

    public AssetInterval[] getIntervals() {
        return AssetInterval.values();
    }

    public void setCategory(FaCategory category) {
        this.category = category;
    }

    public List<FaCategory> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<FaCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public boolean getParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public void setCurrentCategoryId(Integer currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }

    public Integer getCurrentCategoryId() {
        return currentCategoryId;
    }

    public void loadCategories() {

        if (currentCategoryId != null) {
            category = categoryService.findById(currentCategoryId);
            if(category == null){
                
                JSFUtils.redirectTo404("Category");
                
            }
            parent = categoryService.isParent(currentCategoryId);
        }
        parentCategories = categoryService.findAllParent().stream().filter(l -> !l.equals(category)).collect(Collectors.toList());

    }

    public List<OrganizationUnit> completeOperatingUnit(String text) {

       return orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

    }

    public List<GlCodeCombination> completeAccounts(String text) {

        if (category.getOperatingUnit() == null) {
            return null;
        }
        return accountService.findByLedgerAndAlias(category.getOperatingUnit().getGlId(), text);

    }

    public String save() {

        categoryService.update(category);

        return "assetCategoryView?faces-redirect=true";
    }

    public void validateNameExist(FacesContext context, UIComponent component, Object value) {
        UIInput input = (UIInput) component;

        if (currentCategoryId != null) {
            if (input.getValue().equals(value)) {
                return;
            }
        }

        if (categoryService.isNameExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

}
