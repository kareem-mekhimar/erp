/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.arinvoice;

import com.bridge.entities.arinvoice.ArInvoice;
import com.bridge.entities.arinvoice.ArInvoiceLine;
import com.bridge.enums.InvoiceLineType;
import com.bridge.services.arinvoice.ArInvoiceService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class CustomerInvoiceDetailsController implements Serializable{
 
    @EJB
    private ArInvoiceService invoiceService ;
    
    private Integer currentInvoiceId ;

    private ArInvoice currentInvoice ;

    private List<ArInvoiceLine> newLines ; 
   
    public void loadInvoice()
    {
       if(currentInvoiceId != null)
       {
          currentInvoice = invoiceService.findById(currentInvoiceId) ;
          
          if(currentInvoice == null)
              
              JSFUtils.redirectTo404("Ar Invoice");
           
          else
          {
             currentInvoice.setInvoiceLines(invoiceService.findLinesById(currentInvoiceId));
             
             newLines = currentInvoice.getInvoiceLines().stream()
                     .filter(l -> l.isAdditionalExpensesFlag())
                     .collect(Collectors.toList()) ;
          }
       }
       else
           
           JSFUtils.redirectTo404("Ar Invoice");
    }
    
    
    public void addLine()
    {
       ArInvoiceLine line = new ArInvoiceLine() ;
       
       line.setAdditionalExpensesFlag(true);
       
       line.setInvoice(currentInvoice);
       
       newLines.add(line);
       
       currentInvoice.getInvoiceLines().add(line);
    }
    
    public String save()
    {
       invoiceService.update(currentInvoice) ;
       
       return "view?faces-redirect=true" ;
    }
    
    public void onAmountChange(ArInvoiceLine line)
    {
        BigDecimal newInvoiceAmount = currentInvoice.getInvoiceLines().stream()
                .map(ArInvoiceLine::getAmount)
                .collect(Collectors.reducing(BigDecimal.ZERO,BigDecimal::add)) ;
        
        currentInvoice.setInvoiceAmount(newInvoiceAmount);
    }
    
    
    public void validateAmount(FacesContext context , UIComponent component , Object value)
    {
        BigDecimal quantity = (BigDecimal) value ;
        
        if(quantity == null)
            
            return;
        
       if(quantity.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) == 0)
       {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                  "Value Can't Be Zero Or Less", "Value Can't Be Zero Or Less")) ;
       }       
    }
    
    public ArInvoice getCurrentInvoice() {
        return currentInvoice;
    }

    public void setCurrentInvoice(ArInvoice currentInvoice) {
        this.currentInvoice = currentInvoice;
    }
    
    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    public List<ArInvoiceLine> getNewLines() {
        return newLines;
    }
 
    public InvoiceLineType [] getLineTypes()
    {
       return InvoiceLineType.values() ;
    }
}
