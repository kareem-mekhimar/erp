/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

import com.bridge.entities.bank.BankAccount;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientContact;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.clients.CustomerAcc;
import com.bridge.entities.clients.PriceListHeaders;
import com.bridge.entities.clients.SupplierAcc;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.hr.Country;
import com.bridge.entities.setup.PaymentMethod;
import com.bridge.enums.ApprovalLevel;
import com.bridge.enums.ClientType;
import com.bridge.enums.EnforceShip;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.RcvException;
import com.bridge.enums.RecException;
import com.bridge.enums.RecRouting;
import com.bridge.services.bank.AccountService;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.PriceListHeaderService;
import com.bridge.services.hr.CityService;
import com.bridge.services.hr.CountryService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.setup.PaymentMethodService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class siteDetailsController implements Serializable {

    @EJB
    private SiteService siteService;
    @EJB
    private COAAccountService coaAccountService;
    @EJB
    private ClientService clientService;
    @EJB
    private CountryService countryService;
    @EJB
    private CityService cityService;
    @EJB
    private CurrencyService currencyService;
    @EJB
    private PaymentMethodService paymentMethodService;
    @EJB
    private PriceListHeaderService priceListService;
    @EJB
    private AccountService accountService;

    private Client currentClient;
    private ClientSite currentSite;
    private Integer currentSiteId;
    private Integer currentClientId;
    private List<Country> countryList;
    private List<String> stateList;
    private final List<ClientContact> removedLines = new ArrayList();
    private List<PriceListHeaders> priceLists;
    private SupplierAcc currentSupplierAcc;
    private CustomerAcc currentCustomerAcc;
    private List<FndCurrency> currencies;
    private ClientType[] supplierTypes;
    private RcvException[] rcvExceptions;
    private RecException[] recExceptions;
    private EnforceShip[] enforceShipTypes;
    private ApprovalLevel[] approvalLevels;
    private InvoiceMatchingOptions[] invoiceMatchingOption;
    private RecRouting[] recRoutings;
    private List<BankAccount> bankAccounts;

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public ClientSite getCurrentSite() {

        if (currentSite == null) {
            currentSite = new ClientSite();
            currentSite.setStatus(true);
            currentSite.setClientId(currentClient);
            currentSite.setContactList(new ArrayList<>());
            if (currentClient.getSupplierFlag()) {
                currentSite.setSupplierAcc(new SupplierAcc());
            }
            if (currentClient.getCustomerFlag()) {
                currentSite.setCustomerAcc(new CustomerAcc());
            }
        }
        return currentSite;
    }

    public void setCurrentSite(ClientSite currentSite) {
        this.currentSite = currentSite;
    }

    public Integer getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(Integer currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    public Integer getCurrentClientId() {
        return currentClientId;
    }

    public void setCurrentClientId(Integer currentClientId) {
        this.currentClientId = currentClientId;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public List<String> getStateList() {
        return stateList;
    }

    public ClientType[] getSupplierTypes() {
        return ClientType.values();
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

    public InvoiceMatchingOptions[] getInvoiceMatchingOptions() {
        return InvoiceMatchingOptions.values();
    }

    public RecRouting[] getRecRoutings() {
        return RecRouting.values();
    }

    public List<FndCurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<FndCurrency> currencies) {
        this.currencies = currencies;
    }

    public void loadCurrencies() {
        if (currentClient == null) {
            return;
        }
        currencies = currencyService.findValidCurrencies(currentClient.getGlId());
    }

    public List<PaymentMethod> getAllMethods() {
        return paymentMethodService.findAll();
    }

    public SupplierAcc getCurrentSupplierAcc() {
        if (currentSupplierAcc == null) {
            currentSupplierAcc = new SupplierAcc();
        }
        return currentSupplierAcc;
    }

    public void setCurrentSupplierAcc(SupplierAcc currentSupplierAcc) {
        this.currentSupplierAcc = currentSupplierAcc;
    }

    public List<GlCodeCombination> completeCombinationCode(String text) {
        return coaAccountService.findByCombinationCode(text);

    }

    public CustomerAcc getCurrentCustomerAcc() {
        if (currentCustomerAcc == null) {
            currentCustomerAcc = new CustomerAcc();
        }
        return currentCustomerAcc;
    }

    public void setCurrentCustomerAcc(CustomerAcc currentCustomerAcc) {
        this.currentCustomerAcc = currentCustomerAcc;
    }

    public List<PriceListHeaders> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceListHeaders> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Country> completeCountry(String text) {
        countryList = countryService.findCountry(text);
        return countryList;
    }

    public List<String> completeState(String text) {

        stateList = cityService.findCityName(currentSite.getCountryCode().getCountryCode(), text);
        return stateList;
    }

    public List<PriceListHeaders> completePriceList(String text) {

        return priceListService.findPricingListsByName(text);
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addLine() {

        ClientContact contact = new ClientContact();
        contact.setSiteId(currentSite);
        currentSite.getContactList().add(contact);

    }

    public void removeContact(ClientContact line) {
        if (line.getContactId() != null) {
            removedLines.add(line);
        }

        currentSite.getContactList().remove(line);

    }

    public void loadAccounts() {
        bankAccounts = accountService.findAll();
    }

    public void loadpriceList() {
        priceLists = priceListService.findAll();
    }

    public void loadClient() {

        if (currentClientId != null) {
            
            currentClient = clientService.findById(currentClientId);
        }

    }

    public void loadSite() {
        
        loadClient();
        loadAccounts();
        loadpriceList();
        
        if (currentSiteId != null) {

            currentSite = siteService.findSiteDetail(currentSiteId);
            
            if(currentSite != null){
                
             currentClient = currentSite.getClientId();
            
            if (currentClient.getSupplierFlag()) {
                currentSupplierAcc = currentSite.getSupplierAcc();
            }
            if (currentClient.getCustomerFlag()) {
                currentCustomerAcc = currentSite.getCustomerAcc();
            }
            }else{
                JSFUtils.redirectTo404("Client Site");
            }
      
        }
        if(currentClient == null){
             JSFUtils.redirectTo404("Client Site");
        }
        
        loadCurrencies();

    }

    public String save() {

        if (currentClient == null && currentSiteId == null) {
            // abdalrahman fathy: 25/3/2017
            return "clientsView?faces-redirect=true";
            //return "supplierView?faces-redirect=true";
        }

        if (currentClient.getSupplierFlag()) {
            currentSupplierAcc.setSiteId(currentSite);
            currentSupplierAcc.setClientId(currentClient);
            currentSite.setSupplierAcc(currentSupplierAcc);
        }

        if (currentClient.getCustomerFlag()) {
            currentCustomerAcc.setSiteId(currentSite);
            currentCustomerAcc.setClientId(currentClient);
            currentSite.setCustomerAcc(currentCustomerAcc);
        }

        siteService.update(currentSite);

        if (!removedLines.isEmpty()) {
            String removedLinesIds = removedLines.stream()
                    .map(l -> String.valueOf(l.getContactId()))
                    .collect(Collectors.joining(","));

            siteService.removeLines(removedLinesIds);
        }

        return "clientDetails?faces-redirect=true&id=" + currentSite.getClientId().getClientId();
    }

}
