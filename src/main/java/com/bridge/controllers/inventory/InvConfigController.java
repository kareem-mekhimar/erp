/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.modules.InventoryConfguration;
import com.bridge.enums.ControlType;
import com.bridge.enums.CostMethod;
import com.bridge.enums.LotGenerationType;
import com.bridge.enums.LotUniqueType;
import com.bridge.enums.SerialGenerationType;
import com.bridge.enums.SerialUniqueType;
import com.bridge.enums.SourceType;
import com.bridge.enums.TransferChargeType;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.modules.InvConfigService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.services.organization.OrganizationUnitService;
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
public class InvConfigController implements Serializable {

    @EJB
    private InvConfigService invConfigService;
    @EJB
    private UomLineService uomLineService;
    @EJB
    private COAAccountService accountService;
    @EJB
    private OrganizationUnitService orgUnitService;

    private InventoryConfguration invConfig;

    private Integer orgUnitId;

    private ControlType[] controlTypes;
    
    private CostMethod[] costMethod;
    
    private LotUniqueType[] lotUnique;
    
    private LotGenerationType[] lotGeneration;
    
    private SerialUniqueType[] serialUnique;
    
    private SourceType[] sourceType;
    
    private SerialGenerationType[] serialGeneration;
    
    private TransferChargeType[] chargeType;

    public InventoryConfguration getInvConfig() {
        
          if (invConfig == null) {
            invConfig = new InventoryConfguration();
            
            if(orgUnitId!=null)
            invConfig.setOperatingUnitId(orgUnitService.findById(orgUnitId));
        }
        return invConfig;
    }

    public void setInvConfig(InventoryConfguration invConfig) {
        this.invConfig = invConfig;
    }


    public Integer getOrgUnitId() {
        
        return orgUnitId;
    }

    public void setOrgUnitId(Integer orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public ControlType[] getControlTypes() {
        return ControlType.values();
    }

    public CostMethod[] getCostMethod() {
        return CostMethod.values();
    }

    public LotUniqueType[] getLotUnique() {
        return LotUniqueType.values();
    }

    public LotGenerationType[] getLotGeneration() {
        return LotGenerationType.values();
    }

    public SerialUniqueType[] getSerialUnique() {
        return SerialUniqueType.values();
    }

    public SourceType[] getSourceType() {
        return SourceType.values();
    }

    public SerialGenerationType[] getSerialGeneration() {
        return SerialGenerationType.values();
    }

    public TransferChargeType[] getChargeType() {
        return TransferChargeType.values();
    }

    public List<UomLine> completeUom(String text) {
        return uomLineService.findUomByName(text);

    }


    public List<GlCodeCombination> completeAccounts(String text) {

        if(invConfig.getOperatingUnitId() == null)
            
                 return null;

            return accountService.findByLedgerAndAlias(invConfig.getOperatingUnitId().getGlId(), text); 
    }

    public void loadConfig() {

        if (orgUnitId != null) {
            invConfig = invConfigService.findByOrgUnitId(orgUnitId);
           
        }
        
        

    }

    public String save() {

        invConfigService.update(invConfig);
        return "modulesview?faces-redirect=true";
    }

}
