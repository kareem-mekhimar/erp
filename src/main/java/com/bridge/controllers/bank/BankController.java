/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.bank;

import com.bridge.entities.bank.Bank;
import com.bridge.services.bank.BankService;
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
public class BankController implements Serializable{
    
    @EJB
    private BankService bankService;
    
    private List<Bank> bankList;
    
    private String bankName;
    private Boolean status;

    public List<Bank> getBankList() {
        return bankList;
    }

    public String getBankName() {
        return bankName;
    }

    public Boolean getStatus() {
        return status;
    }

    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
    public void filter(){
        bankList=bankService.findBank(bankName, status);
    }
    
}
