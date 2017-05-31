/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.PriceListHeaders;
import com.bridge.services.clients.PriceListHeaderService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author Administrator
 */

@Model
public class PriceListController {
    
    @EJB
    private PriceListHeaderService priceListHeaderService;
    
    private PriceListHeaders priceListHeaders;
    private List<PriceListHeaders> priceListHeadersList;
    
    private Integer activeFlag;
    private Integer listHeaderId;
    private String listHeaderName;

    public PriceListHeaders getPriceListHeaders() {
        return priceListHeaders;
    }

    public void setPriceListHeaders(PriceListHeaders priceListHeaders) {
        this.priceListHeaders = priceListHeaders;
    }

    public List<PriceListHeaders> getPriceListHeadersList() {
        return priceListHeadersList;
    }

    public void setPriceListHeadersList(List<PriceListHeaders> priceListHeadersList) {
        this.priceListHeadersList = priceListHeadersList;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getListHeaderId() {
        return listHeaderId;
    }

    public void setListHeaderId(Integer listHeaderId) {
        this.listHeaderId = listHeaderId;
    }

    public String getListHeaderName() {
        return listHeaderName;
    }

    public void setListHeaderName(String listHeaderName) {
        this.listHeaderName = listHeaderName;
    }
    
    public void filter(){
        priceListHeadersList = priceListHeaderService.findPricingListsHeaders(listHeaderId, listHeaderName, activeFlag);        
    }
    
}
