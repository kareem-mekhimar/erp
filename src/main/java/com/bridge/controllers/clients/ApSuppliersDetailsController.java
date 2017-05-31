/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.clients.ApSupplier;
import com.bridge.entities.clients.ApSupplierSite;
import com.bridge.entities.clients.SiteAccount;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.setup.PaymentMethod;
import com.bridge.enums.ApprovalLevel;
import com.bridge.enums.EnforceShip;
import com.bridge.enums.OrganizationUnitType;
import com.bridge.enums.RcvException;
import com.bridge.enums.RecException;
import com.bridge.enums.RecRouting;
import com.bridge.enums.ClientType;
import com.bridge.services.clients.ApSupplierService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.organization.OrganizationUnitService;
import com.bridge.services.setup.PaymentMethodService;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class ApSuppliersDetailsController implements Serializable {

    @EJB
    private ApSupplierService supplierService;
    @EJB
    private CurrencyService currencyService;
    @EJB
    private PaymentMethodService paymentMethodService;
    @EJB
    private COAAccountService accountService;
    @EJB
    private SiteService siteService;
    @EJB
    private GlLedgerService ledgerService;
    @EJB
    private OrganizationUnitService orgUnitService;
            
    private Integer currentSupplierId;
    private ApSupplier currentSupplier;
    private ApSupplierSite currentSite;
    private SiteAccount currentSiteAccount;
    private Integer ledgerId;
    private Integer legalEntityId;
    private Integer operatingUnitId;
    private List<ApSupplierSite> siteList;
    private List<GlLedger> ledgerList;
    private List<OrganizationUnit> legalentityList;
    private List<OrganizationUnit> operatingUnitList;
    
    
    private ClientType[] supplierTypes;
    private RcvException[] rcvExceptions;
    private RecException[] recExceptions;
    private EnforceShip[] enforceShipTypes;
    private ApprovalLevel[] approvalLevels;
    private RecRouting[] recRoutings;

    public Integer getCurrentSupplierId() {
        return currentSupplierId;
    }

    public void setCurrentSupplierId(Integer currentSupplierId) {
        this.currentSupplierId = currentSupplierId;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    public void setLegalEntityId(Integer legalEntityId) {
        this.legalEntityId = legalEntityId;
    }

    public Integer getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Integer operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }
    
    

    public ApSupplier getCurrentSupplier() {

        if (currentSupplier == null) {
            currentSupplier = new ApSupplier();
            currentSupplier.setEnabledFlagId(true);
            currentSupplier.setSupplierSiteAcounts(new LinkedHashSet());
        }
        return currentSupplier;
    }

    public void setCurrentSupplier(ApSupplier currentSupplier) {
        this.currentSupplier = currentSupplier;
    }

    public ClientType[] getSupplierTypes() {
        return ClientType.values();
    }

    public ApSupplierSite getCurrentSite() {
        return currentSite;
    }

    public void setCurrentSite(ApSupplierSite currentSite) {
        this.currentSite = currentSite;
    }


    public RcvException[] getRcvExceptions() {
        return RcvException.values();
    }

    public RecException[] getRecExceptions() {
        return RecException.values();
    }

    public EnforceShip[] getEnforceShipTypes() {
        return EnforceShip.values();
    }

    public ApprovalLevel[] getApprovalLevels() {
        return ApprovalLevel.values();
    }

    public RecRouting[] getRecRoutings() {
        return RecRouting.values();
    }

    public SiteAccount getCurrentSiteAccount() {
        return currentSiteAccount;
    }

    public void setCurrentSiteAccount(SiteAccount currentSiteAccount) {
        this.currentSiteAccount = currentSiteAccount;
    }

    public List<ApSupplierSite> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<ApSupplierSite> siteList) {
        this.siteList = siteList;
    }

    public List<GlLedger> getLedgerList() {
        return ledgerList;
    }

    public void setLedgerList(List<GlLedger> ledgerList) {
        this.ledgerList = ledgerList;
    }

    public List<OrganizationUnit> getLegalentityList() {
        return legalentityList;
    }

    public void setLegalentityList(List<OrganizationUnit> legalentityList) {
        this.legalentityList = legalentityList;
    }

    public List<OrganizationUnit> getOperatingUnitList() {
        return operatingUnitList;
    }

    public void setOperatingUnitList(List<OrganizationUnit> operatingUnitList) {
        this.operatingUnitList = operatingUnitList;
    }

    public void loadLists(){
        loadLedger();
        loadLegalEntity();
        loadOperatingUnit();
    }
     public void clearLists(){
     if(currentSiteAccount != null) currentSiteAccount=null;
     if(ledgerList != null) ledgerList.clear();
     if(legalentityList != null) legalentityList.clear();
     if(operatingUnitList != null) operatingUnitList.clear();
    }
     
    public void loadLedger(){
               ledgerList=ledgerService.findAll(); 
    }
    
    public void loadLegalEntity(){
//        legalentityList=orgUnitService.findByOrgType(OrganizationUnitType.LEGAL_ENTITY);        
        legalentityList=orgUnitService.findLegalInLedger(currentSiteAccount.getLedgerId());
    }
    public void loadOperatingUnit(){
        operatingUnitList=orgUnitService.findByOrgTypeAndOrgParent(OrganizationUnitType.LEGAL_ENTITY, currentSiteAccount.getLegalEntityId());
    }
    
    
    
    
    
    public void initSiteAccount(){
        
            currentSiteAccount=new SiteAccount();
            loadLedger();
    }
    
    public void addSiteAccount(){
        
        if(currentSiteAccount.getAccountingId()==null){
            currentSiteAccount.setVendor(currentSupplier);
            currentSupplier.getSupplierSiteAcounts().add(currentSiteAccount);
        }
        
        clearLists();
    }
    
    public void loadSupplier() {

        if (currentSupplierId != null) {
            currentSupplier = supplierService.findSupplierDetails(currentSupplierId);
        }
    }

//    public List<ApSupplierSite> completeSites(String text) {
//        return siteService.findBySiteName(currentSupplier,text);
//
//    }
    public List<GlCodeCombination> completeCombinationCode(String text) {
        return accountService.findByCombinationCode(text);

    }

    public String save() {
        
         supplierService.update(currentSupplier);

        return "supplierView?faces-redirect=true";
    }
    
    public String createSite(){
         RequestContext requestContext = RequestContext.getCurrentInstance() ;
         if(currentSupplier.getVendorId()!=null){
              
             return "siteDetails?faces-redirect=true&supId="+currentSupplier.getVendorId();
         }
        requestContext.execute("saveModal.show()");
        return null;
    }
    
    public String saveForSite(){
        currentSupplier=supplierService.update(currentSupplier);
        return "siteDetails?faces-redirect=true&supId="+currentSupplier.getVendorId();
    }

    public List<FndCurrency> getAllCurrencies() {
        return currencyService.findValidCurrencies(1); //not valid code
    }

    public List<PaymentMethod> getAllMethods() {
        return paymentMethodService.findAll();
    }
}
