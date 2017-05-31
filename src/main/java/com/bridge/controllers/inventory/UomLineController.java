/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.inventory.UomLine;
import com.bridge.services.inventory.UomLineService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;


/**
 *
 * @author AIA
 */

@Dependent
public class UomLineController implements Serializable{
    
    @EJB
    private UomLineService uomLinesService ;
    
    private List<UomLine> uomLines ;
    
    
    @PostConstruct
    private void init()
    {
       uomLines = uomLinesService.findAll() ;       
    }

    public List<UomLine> getUomLines() {
        return uomLines;
    }

}
