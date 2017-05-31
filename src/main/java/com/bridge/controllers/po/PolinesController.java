/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.po;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.PoLineType;
import com.bridge.enums.MatchBasis;
import com.bridge.enums.SystemItemType;
import com.bridge.services.inventory.SystemItemService;
import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Bridge
 */

@Dependent
public class PolinesController implements Serializable{
    
    @EJB
    private SystemItemService systemItemService ;
    
    private List<SystemItem> currentAvailbleSystemItems ;
    
    private PoLineType [] lineTypes ;
     
    private MatchBasis [] matchBasises ;
    
    @PostConstruct
    private void init()
    {
      currentAvailbleSystemItems = systemItemService.findAll() ;
    }
    
//    public void onItemSelectChange(PoLine line)
//    {
//        line.setPrimaryUom(line.getSystemItem().getPrimaryUomId());
//        
//        line.setTaxable(line.getSystemItem().getTaxableFlag());
//    }
//    
//    public List<SystemItem> getCurrentAvailbleSystemItems() {
//     
//        return currentAvailbleSystemItems;
//    }
//         
//    public PoLineType[] getLineTypes() {
//        return PoLineType.values();
//    }
//
//    public MatchBasis[] getMatchBasises() {
//    
//        return MatchBasis.values();
//    }
    
    
}
