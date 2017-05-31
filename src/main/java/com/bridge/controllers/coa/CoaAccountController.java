/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.coa;

import com.bridge.entities.coa.CoaSegmentLine;
import com.bridge.entities.coa.CoaStructure;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.enums.StructureStatus;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.coa.CoaStructureService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */
@Named
@ViewScoped
public class CoaAccountController implements Serializable {

    @EJB
    private COAAccountService accountService;

    @EJB
    private CoaStructureService structureService;

    private List<GlCodeCombination> accounts;

    private Integer coaId;

    private CoaStructure coaStructure;

    private GlCodeCombination currentAccount;

    private GlCodeCombination oldAccount = new GlCodeCombination();

    private String[] lableArray;

    private StructureStatus coaStatus;

    private List<CoaSegmentLine> currentSegmentLines;

    private String separator;

    private List<Integer> oldIds = new ArrayList();

    private List<String> changedItems = new ArrayList();

    public String[] getLableArray() {
        return lableArray;
    }

    public List<CoaSegmentLine> getCurrentSegmentLines() {
        if (currentSegmentLines == null) {
            currentSegmentLines = new ArrayList();
        }
        return currentSegmentLines;
    }

    public void setCurrentSegmentLines(List<CoaSegmentLine> currentSegmentLines) {
        this.currentSegmentLines = currentSegmentLines;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void loadAccountsForCoa() {

        if (coaId != null) {

            coaStructure = structureService.findCoaStructureWithLines(coaId);

            if (coaStructure == null) {

                JSFUtils.redirectTo404("COA Structure");
            }
            
            coaStatus = coaStructure.getCoaStatus();
            separator = coaStructure.getSegmentsSeparator().getValue();
            accounts = accountService.findByCoa(coaStructure);

        } else {
            JSFUtils.redirectTo404("Coa Accounts");
        }
    }

    public void prepareSegmentLines() {

        Integer size = coaStructure.getCoaSegmentList().size();

        if (currentAccount == null) {

            currentAccount = new GlCodeCombination();
            currentAccount.setCombinationSegmentList(new ArrayList());

            for (int i = 0; i < size; i++) {
                currentAccount.getCombinationSegmentList().add(new CoaSegmentLine());
            }

        } else if (currentAccount.getCodeCombinationId() != null && !oldIds.contains(currentAccount.getCodeCombinationId())) {

            oldIds.add(currentAccount.getCodeCombinationId());

            currentAccount.setCombinationSegmentList(accountService
                    .findAccountWithSegments(currentAccount.getCodeCombinationId()).getCombinationSegmentList());

        }

        lableArray = new String[size];

        for (int i = 0; i < size; i++) {
            lableArray[i] = coaStructure.getCoaSegmentList().get(i).getSegmentLable();
        }
        oldAccount.setCombinationSegmentList(new ArrayList());
        for (CoaSegmentLine s : currentAccount.getCombinationSegmentList()) {
            oldAccount.getCombinationSegmentList().add(s);
        }

    }

    public void onDialogOk() {

        String combination = currentAccount.getCombinationSegmentList().stream()
                .map(CoaSegmentLine::getSegmentDetailCode)
                .collect(Collectors.joining(separator));

        if (!isCombinationExistsInTable(combination)) {

            if (changedItems.contains(combination)) {

                changedItems.add(combination);
            }

            currentAccount.setCodeCombination(combination);

            System.out.println("com.bridge.controllers.coa.CoaAccountController.onDialogOk() " + combination);
//            currentAccount.setCombinationSegmentList(currentSegmentLines);

            if (currentAccount.getCoaStructure() == null) {

                accounts.add(currentAccount);

                currentAccount.setCoaStructure(coaStructure);
            }

            RequestContext.getCurrentInstance().execute("modal.hide()");

            RequestContext.getCurrentInstance().update("table");

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Combination already exists",
                            "Combination already exists"));
        }
    }

    public void onDialogCancel() {

        currentAccount.setCombinationSegmentList(oldAccount.getCombinationSegmentList());
    }

    public void validateAlias(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        UIInput input = (UIInput) component;

        if (currentAccount.getCodeCombinationId() != null) {

            if (input.getValue().equals(value)) {
                return;
            }
        }
        if (accountService.isAliasExists((String) value)) {
            throw new ValidatorException(new FacesMessage(value + " already existes.."));
        }
    }

    public String save() {

        accounts.stream().filter(a -> changedItems.contains(a.getCodeCombination())).collect(Collectors.toList());

        if (accounts.size() > 0) {
            accountService.update(accounts);
        }

        return "coaStructureView?faces-redirect=true";
    }

    private boolean isCombinationExistsInTable(String combiantion) {

//        return accounts.stream().filter(a -> a != currentAccount)
//                .map(GlCodeCombination::getCodeCombination).anyMatch(c -> c.equals(combiantion));
        return accounts.stream().filter(a -> a != currentAccount && a.getCodeCombination().equals(combiantion)).count() > 0;
    }

    public List<CoaSegmentLine> completeSegmentLines(String text) {

        int seqNo = (int) JSFUtils.evaluateValueExpression("#{state.index + 1}", Integer.class);

        return structureService.findSegmentLinesBySegmentSeqNoAndStructureIdAndStartsWith(seqNo, coaStructure, text)
                .stream().filter(s -> s.getDependsOnDetailId() == null
                || s.getDependsOnDetailId().equals(currentAccount.getCombinationSegmentList().get(s.getSegment().getDependentOnSegmentSequence() - 1))).collect(Collectors.toList());
    }

    public List<CoaStructure> completeStructure(String text) {
        return structureService.findCoaStructureByName(text);
    }

    public String redirectToAccountDetails() {
        return "coaAccountDetails?faces-redirect=true&coa=" + FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("coaId");
    }

    public List<GlCodeCombination> getAccounts() {
        return accounts;
    }

    public CoaStructure getCoaStructure() {
        return coaStructure;
    }

    public GlCodeCombination getCurrentAccount() {

        return currentAccount;
    }

    public void setCurrentAccount(GlCodeCombination currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public StructureStatus getCoaStatus() {
        return coaStatus;
    }

    public void setCoaStatus(StructureStatus coaStatus) {
        this.coaStatus = coaStatus;
    }

}
