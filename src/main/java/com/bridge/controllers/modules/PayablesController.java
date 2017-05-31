/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.modules;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.modules.PayableModuleConfiguration;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.enums.AutomaticOffsetMethod;
import com.bridge.enums.BillsPayableAccountSource;
import com.bridge.enums.DiscountMethods;
import com.bridge.enums.InterestOptions;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.PaymentDateBasis;
import com.bridge.enums.PaymentGroup;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.PrepaymentAccountOptions;
import com.bridge.enums.TermsDateBasis;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.modules.PayableModuleConfigurationService;
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
 * @author Bridge
 */

@Named
@ViewScoped
public class PayablesController implements Serializable{
    
    @EJB
    private OrganizationUnitService organizationUnitService ;
    
    @EJB
    private PayableModuleConfigurationService modulesConfigurationService ;
    
    @EJB
    private COAAccountService accountService ;
    
    @EJB
    private CurrencyService currencyService ;
        
    private List<FndCurrency> currencys ;
    
    private OrganizationUnit operatingUnit ;
    
    private PayableModuleConfiguration currentModuleConfug ;
    
    private Integer currentOperatingUnitId ;

    @PostConstruct
    private void init()
    {
       currencys = currencyService.findValidCurrencies(1) ;
    }
    
    public void loadModuleConfig()
    {
      if(currentOperatingUnitId != null)
      {
         operatingUnit = organizationUnitService.findById(currentOperatingUnitId) ;
         
         if(operatingUnit == null)
             
             JSFUtils.redirectTo404("OperatingUnit") ;
         
         else
         {
             currentModuleConfug = modulesConfigurationService.findByOperating(operatingUnit) ;
             
             if(currentModuleConfug == null)
             {
                 currentModuleConfug = new PayableModuleConfiguration() ;
              
                 currentModuleConfug.setOperatingUnit(operatingUnit);
             }
             
             // abdalrahman 4/5/2017:
             loadCurrencies();
             
             
         }
      }
      else
          
           JSFUtils.redirectTo404("OperatingUnit") ;
    }

    public List<GlCodeCombination> completeAccounts(String text) {
        
        return accountService.findByAlias(text) ;
    }
    
    public String save()
    {
       modulesConfigurationService.update(currentModuleConfug) ;
       
       return "modulesview?faces-redirect=true" ;
    }

    public List<FndCurrency> getCurrencys() {
        return currencys;
    }
    
    
    public PayableModuleConfiguration getCurrentModuleConfug() {
        return currentModuleConfug;
    }

    public void setCurrentModuleConfug(PayableModuleConfiguration currentModuleConfug) {
        this.currentModuleConfug = currentModuleConfug;
    }
    
    
    public Integer getCurrentOperatingUnitId() {
        return currentOperatingUnitId;
    }

    public void setCurrentOperatingUnitId(Integer currentOperatingUnitId) {
        this.currentOperatingUnitId = currentOperatingUnitId;
    }
    
    public BillsPayableAccountSource [] getBillsPayableAccountSources()
    {
       return BillsPayableAccountSource.values() ;
    }
    
    public AutomaticOffsetMethod [] getAutomaticOffsetMethods()
    {
       return AutomaticOffsetMethod.values() ;
    }
    
    public InterestOptions [] getInterestOptions()
    {
       return InterestOptions.values() ;
    }
    
    public PrepaymentAccountOptions [] getPrepaymentAccountOptions()
    {
       return PrepaymentAccountOptions.values() ;
    }
    
    public DiscountMethods [] getDiscountMethods()
    {
       return DiscountMethods.values() ;
    }
    
    public InvoiceMatchingOptions [] getInvoiceMatchingOptionses()
    {
        return InvoiceMatchingOptions.values() ;
    }
    
    public PaymentGroup [] getPaymentGroups()
    {
       return PaymentGroup.values() ;
    }
    
    public TermsDateBasis [] getTermDateBasises()
    {
      return TermsDateBasis.values() ;
    }
    
    public PaymentDateBasis [] getPaymentDateBasises()
    {
        return PaymentDateBasis.values() ;
    }
    
    public PaymentTerms [] getPaymentTermses()
    {
       return PaymentTerms.values() ;
    }
    
    public void loadCurrencies() {
        
        if(currentModuleConfug.getOperatingUnit() ==null){
            return;
        }
        
        currencys = currencyService.findValidCurrencies(currentModuleConfug.getOperatingUnit().getGlId());
    }
    
}
