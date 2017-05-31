/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.clients;

/**
 *
 * @author Administrator
 */
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.currency.FndCurrency;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.setup.PaymentMethod;
import com.bridge.enums.ApprovalLevel;
import com.bridge.enums.EnforceShip;
import com.bridge.enums.RcvException;
import com.bridge.enums.RecException;
import com.bridge.enums.RecRouting;
import com.bridge.enums.ClientType;
import com.bridge.services.clients.ClientService;
import com.bridge.services.clients.SiteService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.currency.CurrencyService;
import com.bridge.services.ledger.GlLedgerService;
import com.bridge.services.setup.PaymentMethodService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ClientDetailsController implements Serializable {

    @EJB
    private ClientService clientService;
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

    private Integer currentClientId;
    private Client currentClient;
    private Client oldClient = new Client();
    private ClientSite currentSite;
    private List<ClientSite> siteList;
    private Integer currentSiteId;
    private ClientType[] clientTypes;
    private RcvException[] rcvExceptions;
    private RecException[] recExceptions;
    private EnforceShip[] enforceShipTypes;
    private ApprovalLevel[] approvalLevels;
    private RecRouting[] recRoutings;

    private List<GlLedger> ledgerList;

    public Integer getCurrentClientId() {
        return currentClientId;
    }

    public void setCurrentClientId(Integer currentClientId) {
        this.currentClientId = currentClientId;
    }

    public Client getCurrentClient() {

        if (currentClient == null) {
            currentClient = new Client();
            currentClient.setStatus(true);
        }
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public ClientType[] getClientTypes() {
        return ClientType.values();
    }

    public ClientSite getCurrentSite() {
        return currentSite;
    }

    public void setCurrentSite(ClientSite currentSite) {
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

    public List<ClientSite> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<ClientSite> siteList) {
        this.siteList = siteList;
    }

    public Integer getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(Integer currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    public void loadClient() {

        ledgerList = ledgerService.findAll();

        if (currentClientId != null) {
            currentClient = clientService.findClientDetails(currentClientId);
            
            if (currentClient != null) {
                oldClient.setClientNumber(currentClient.getClientNumber());
                oldClient.setClientName(currentClient.getClientName());
                oldClient.setAliasName(currentClient.getAliasName());
                oldClient.setDisabledOn(currentClient.getDisabledOn());
                oldClient.setStatus(currentClient.getStatus());
                oldClient.setSupplierFlag(currentClient.getSupplierFlag());
                oldClient.setCustomerFlag(currentClient.getCustomerFlag());
            }else{
                JSFUtils.redirectTo404("Client");
            }

        }
    }

    public boolean hasChange() {

        if (currentClient.getClientId() == null) {
            return true;
        }
        if (currentClient.getStatus() != oldClient.getStatus()) {
            return true;
        }
        if (currentClient.getCustomerFlag() != oldClient.getCustomerFlag()) {
            return true;
        }
        if (currentClient.getSupplierFlag() != oldClient.getSupplierFlag()) {
            return true;
        }
        if (currentClient.getClientNumber() == null) {
            return true;
        }
        if (!currentClient.getClientNumber().equals(oldClient.getClientNumber())) {
            return true;
        }
        if (currentClient.getClientName() == null) {
            return true;
        }
        if (!currentClient.getClientName().equals(oldClient.getClientName())) {
            return true;
        }
        if (currentClient.getAliasName() == null && oldClient.getAliasName() != null) {
            return true;
        }
        if (currentClient.getAliasName() != null) {
            if (!currentClient.getAliasName().equals(oldClient.getAliasName())) {
                return true;
            }
        }
        if (currentClient.getDisabledOn() == null && oldClient.getDisabledOn() != null) {
            return true;
        }
        if (currentClient.getDisabledOn() != null) {
            if (!currentClient.getDisabledOn().equals(oldClient.getDisabledOn())) {
                return true;
            }
        }
        return false;
    }

    public List<ClientSite> completeSites(String text) {
        return siteService.findSitesForClientByName(currentClient, text);

    }

    public List<GlCodeCombination> completeCombinationCode(String text) {
        return accountService.findByCombinationCode(text);

    }

    public String save() {

        if (currentClient.getSupplierFlag() != true && currentClient.getCustomerFlag() != true) {

            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please Check Client Type!');");

            return null;
        }

        clientService.update(currentClient);

        return "clientsView?faces-redirect=true";
    }

    public String createSite() {

        if (!hasChange()) {

            return "siteDetails?faces-redirect=true&clientId=" + currentClient.getClientId();
        }

        RequestContext.getCurrentInstance().execute("saveModal1.show()");
        return null;
    }

    public String openSite(ClientSite site) {

        currentSite = site;
        if (!hasChange()) {

            return "siteDetails?faces-redirect=true&id=" + site.getSiteId();
        }

        RequestContext.getCurrentInstance().execute("saveModal2.show()");
        return null;

    }

    public String dialog1Action(Integer state) {

        if (state == 0 && currentClient.getClientId() == null) {
            return null;
        }

        if (currentClient.getSupplierFlag() != true && currentClient.getCustomerFlag() != true) {

            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please Check Client Type!');");

            return null;
        }

        if (state == 1) {
            currentClient = clientService.update(currentClient);
        }

        return "siteDetails?faces-redirect=true&clientId=" + currentClient.getClientId();
    }

    public String dialog2Action(Integer state) {

        if (currentClient.getSupplierFlag() != true && currentClient.getCustomerFlag() != true) {

            RequestContext.getCurrentInstance().execute("UIkit.modal.alert('Please Check Client Type!');");

            return null;
        }

        if (state == 1) {
            currentClient = clientService.update(currentClient);
        }

        return "siteDetails?faces-redirect=true&id=" + currentSite.getSiteId();
    }

    public List<FndCurrency> getAllCurrencies() {
        return currencyService.findValidCurrencies(1);
    }

    public List<PaymentMethod> getAllMethods() {
        return paymentMethodService.findAll();
    }

    public List<GlLedger> getLedgerList() {
        return ledgerList;
    }

    public void setLedgerList(List<GlLedger> ledgerList) {
        this.ledgerList = ledgerList;
    }

}
