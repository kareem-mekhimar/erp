/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.inventory;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.hr.Country;
import com.bridge.entities.hr.City;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.UomLine;
import com.bridge.entities.modules.InventoryConfguration;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.ControlType;
import com.bridge.enums.CostMethod;
import com.bridge.enums.LotGenerationType;
import com.bridge.enums.LotUniqueType;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.SerialGenerationType;
import com.bridge.enums.SerialUniqueType;
import com.bridge.enums.SourceType;
import com.bridge.enums.TransferChargeType;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.hr.CityService;
import com.bridge.services.modules.InvConfigService;
import com.bridge.services.inventory.MainInvService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.UomLineService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class MainInvDetailsController implements Serializable {

    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;
    @EJB
    private MainInvService mainInvService;
    @EJB
    private InvConfigService invConfigService;
    @EJB
    private SubInvService subInvService;
    @EJB
    private OrganizationUnitService orgUnitService;
    @EJB
    private UomLineService uomLineService;
    @EJB
    private COAAccountService accountService;

    private List<Country> countryList;

    private List<City> cityList;

    private String countryCode;

    private List<OrganizationUnit> operatingUnitList;
    private MainInventory currentMainInventory;
    private Integer currentOrganizationId;
    private ControlType[] controlTypes;
    private CostMethod[] costMethod;
    private LotUniqueType[] lotUnique;
    private LotGenerationType[] lotGeneration;
    private SerialUniqueType[] serialUnique;
    private SourceType[] sourceType;
    private SerialGenerationType[] serialGeneration;
    private TransferChargeType[] chargeType;

    private List<MainInventory> mainInventoryList;

    private List<UomLine> weightUnitList;
    private List<UomLine> volumeUnitList;
    private List<GlCodeCombination> AccountList;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public MainInventory getCurrentMainInventory() {
        if (currentMainInventory == null) {
            currentMainInventory = new MainInventory();
        }
        return currentMainInventory;
    }

    public void setCurrentMainInventory(MainInventory currentMainInventory) {
        this.currentMainInventory = currentMainInventory;
    }

    public Integer getCurrentOrganizationId() {
        return currentOrganizationId;
    }

    public void setCurrentOrganizationId(Integer currentOrganizationId) {
        this.currentOrganizationId = currentOrganizationId;
    }

    public List<OrganizationUnit> getOperatingUnitList() {
        return operatingUnitList;
    }

    public void setOperatingUnitList(List<OrganizationUnit> operatingUnitList) {
        this.operatingUnitList = operatingUnitList;
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

    public SerialGenerationType[] getSerialGeneration() {
        return SerialGenerationType.values();
    }

    public SourceType[] getSourceType() {
        return SourceType.values();
    }

    public TransferChargeType[] getChargeType() {
        return TransferChargeType.values();
    }

    public List<GlCodeCombination> completeAccounts(String text) {

        if (currentMainInventory.getOperatingUnit() == null) {
            return null;
        }
        return accountService.findByLedgerAndAlias(currentMainInventory.getOperatingUnit().getGlId(), text);

    }

    public List<OrganizationUnit> completeOperatingUnit(String text) {

        operatingUnitList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, text);

        return operatingUnitList;
    }

    public List<UomLine> completeWeightUnits(String text) {

        weightUnitList = uomLineService.findUomByName(text);

        return weightUnitList;
    }

    public List<UomLine> completeVolumeUnits(String text) {

        volumeUnitList = uomLineService.findUomByName(text);

        return volumeUnitList;
    }

    public List<MainInventory> completeMainInv(String text) {

        mainInventoryList = mainInvService.findByName(text);

        return mainInventoryList;
    }

    public void setAccounts(List<GlCodeCombination> Accounts) {
        this.AccountList = Accounts;
    }

    public List<GlCodeCombination> getAccounts() {
        return AccountList;
    }

    public List<MainInventory> getMainInventoryList() {
        return mainInventoryList;
    }

    public List<UomLine> getWeightUnitList() {
        return weightUnitList;
    }

    public List<UomLine> getVolumeUnitList() {
        return volumeUnitList;
    }

    public List<GlCodeCombination> getAccountList() {
        return AccountList;
    }

    @PostConstruct
    public void loadOperatingUnit() {

        operatingUnitList = orgUnitService.findByOrgTypeByName(OrganizationUnitType.OPERATING_UNIT, "%");
    }

    public void loadInventory() {
        
        if (currentOrganizationId != null) {
            
            currentMainInventory = mainInvService.findById(currentOrganizationId);
            
            if(currentMainInventory == null){
                
                JSFUtils.redirectTo404("Main Inventory");
                
            }
        }
    }

    public void fillInvData() {
        InventoryConfguration invConfig = invConfigService.findByOrgUnitId(currentMainInventory.getOperatingUnit().getOrgUnitId());
        if (invConfig != null) {
            currentMainInventory.setLocatorControlType(invConfig.getLocatorControlType());
            currentMainInventory.setCalendarCode(invConfig.getCalendarCode());
            currentMainInventory.setOrgMaxWeight(invConfig.getOrgMaxWeight());
            currentMainInventory.setWeightUnitMeasure(invConfig.getWeightUnitMeasure());
            currentMainInventory.setOrgMaxWeight(invConfig.getOrgMaxWeight());
            currentMainInventory.setVolumeUnitMeasure(invConfig.getVolumeUnitMeasure());
            currentMainInventory.setPrimaryCostMethod(invConfig.getPrimaryCostMethod());
            currentMainInventory.setTransferToGlFlag(invConfig.getTransferToGlFlag());
            currentMainInventory.setEncumbranceReversalFlag(invConfig.getEncumbranceReversalFlag());
            currentMainInventory.setProjectReferenceEnabled(invConfig.getProjectReferenceEnabled());
            currentMainInventory.setDeferLogicalTransactions(invConfig.getDeferLogicalTransactions());
            currentMainInventory.setMaterialAccount(invConfig.getMaterialAccount());
            currentMainInventory.setOutsideProcessingAccount(invConfig.getOutsideProcessingAccount());
            currentMainInventory.setMaterialOverheadAccount(invConfig.getMaterialOverheadAccount());
            currentMainInventory.setOverheadAccount(invConfig.getOverheadAccount());
            currentMainInventory.setResourceAccount(invConfig.getResourceAccount());
            currentMainInventory.setExpenseAccount(invConfig.getExpenseAccount());
            currentMainInventory.setLotNumberUniqueness(invConfig.getLotNumberUniqueness());
            currentMainInventory.setLotNumberGeneration(invConfig.getLotNumberGeneration());
            currentMainInventory.setAutoLotAlphaPrefix(invConfig.getAutoLotAlphaPrefix());
            currentMainInventory.setLotNumberLength(invConfig.getLotNumberLength());
            currentMainInventory.setLotNumberZeroPadding(invConfig.getLotNumberZeroPadding());
            currentMainInventory.setSerialNumberType(invConfig.getSerialNumberType());
            currentMainInventory.setSerialNumberGeneration(invConfig.getSerialNumberGeneration());
            currentMainInventory.setAutoSerialAlphaPrefix(invConfig.getAutoSerialAlphaPrefix());
            currentMainInventory.setStartAutoSerialNumber(invConfig.getStartAutoSerialNumber());
            currentMainInventory.setAllocateSerialFlag(invConfig.getAllocateSerialFlag());
            currentMainInventory.setSourceType(invConfig.getSourceType());
            currentMainInventory.setDistributedOrganizationFlag(invConfig.getDistributedOrganizationFlag());
            currentMainInventory.setMatlInterorgTransferCode(invConfig.getMatlInterorgTransferCode());
            currentMainInventory.setInterorgTrnsfrChargePercent(invConfig.getInterorgTrnsfrChargePercent());
            currentMainInventory.setInterorgTransferCrAccount(invConfig.getInterorgTransferCrAccount());
            currentMainInventory.setInterorgPriceVarAccount(invConfig.getInterorgPriceVarAccount());
            currentMainInventory.setInterorgReceivablesAccount(invConfig.getInterorgReceivablesAccount());
            currentMainInventory.setInterorgPayablesAccount(invConfig.getInterorgPayablesAccount());
            currentMainInventory.setIntransitInvAccount(invConfig.getIntransitInvAccount());
            currentMainInventory.setPurchasePriceVarAccount(invConfig.getPurchasePriceVarAccount());
            currentMainInventory.setInvoicePriceVarAccount(invConfig.getInvoicePriceVarAccount());
            currentMainInventory.setApAccrualAccount(invConfig.getApAccrualAccount());
            currentMainInventory.setEncumbranceAccount(invConfig.getEncumbranceAccount());
            currentMainInventory.setCostOfSalesAccount(invConfig.getCostOfSalesAccount());
        }
    }

    public String save() {
        mainInvService.update(currentMainInventory);
        return "mainInventoryView?faces-redirect=true";

    }

    public List<Country> completeCountry(String text) {
        System.out.println("county is: " + text);
        countryList = countryService.findCountry(text);
        return countryList;
    }

    public List<String> completeCity(String text) {
 
        if (currentMainInventory.getCountry() == null) {
            return null;
        }

        return cityService.findCityName(currentMainInventory.getCountry().getCountryCode(), text);

    }

    public void resetForm() {
        currentMainInventory.setTownOrCityId(null);
    }

}
