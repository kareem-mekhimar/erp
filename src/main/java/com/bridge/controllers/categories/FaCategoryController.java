/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.categories;

import com.bridge.entities.assets.FaCategory;
import com.bridge.services.categories.FaCategoryService;
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
public class FaCategoryController implements Serializable{

    @EJB
    private FaCategoryService categoriesService;
    private List<FaCategory> categoriesList;
    private String categoryCode;
    private String categoriesName;
    private Boolean enabledflag;

    public Boolean getEnabledflag() {
        return enabledflag;
    }

    public void setEnabledflag(Boolean enabledflag) {
        this.enabledflag = enabledflag;
    }

    
    public List<FaCategory> getCategoriesList() {

        return categoriesList;
    }
    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public void filter() {
        categoriesList = categoriesService.findAllCategories(categoryCode, categoriesName,enabledflag);
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

}
