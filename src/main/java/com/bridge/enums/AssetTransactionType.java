/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.enums;

/**
 *
 * @author Administrator
 */
public enum AssetTransactionType {

    ADDITION("Asset Added"),
    UPDATE("Asset Update"),
    COMPONENT_ADDITION("Add Child Asset to Asset"),
    COMPONENT_REMOVING("Remove Child Asset from Asset"),
    CATEGORY_CHANGE("Asset Category Change"),
    LOCATION_CHANGE("Asset Location Change"),
    PARENT_CHANGE("Asset Parent Change"),
    RETIREMENT("Asset Retirment"),
    RELEASE("Asset Released"),
    SALE("Asset Released"),
    DEPRECIATION("Asset Depreciate"),
    MAINTENANCE("Asset Maintane"),
    COST_ADDITION("Asset Cost Added"),
    RE_SERVICE("Asset Re service"),
    RE_ADJUSTMENT("Asset Re adjustment");

    private String name;

    public String getName() {
        return name;
    }

    AssetTransactionType(String name) {
        this.name = name;

    }
}
