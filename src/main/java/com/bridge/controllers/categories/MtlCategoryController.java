/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.categories;

import com.bridge.entities.inventory.MtlCategory;
import com.bridge.services.categories.MtlCategoryService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class MtlCategoryController implements Serializable{

    @EJB
    private MtlCategoryService categoryService;
    private List<MtlCategory> categoryList;
    private String categoryCode;
    private String categoryName;
    private Boolean enabledflag;

    public Boolean getEnabledflag() {
        return enabledflag;
    }

    public void setEnabledflag(Boolean enabledflag) {
        this.enabledflag = enabledflag;
    }

    
    public List<MtlCategory> getCategoryList() {

        return categoryList;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void filter() {
        categoryList = categoryService.findAllCategories(categoryCode, categoryName,enabledflag);
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

}
