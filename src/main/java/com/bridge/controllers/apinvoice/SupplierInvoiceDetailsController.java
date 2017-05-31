/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.apinvoice;

import com.bridge.entities.apinvoice.ApInvoice;
import com.bridge.entities.apinvoice.ApInvoiceLine;
import com.bridge.enums.InvoiceLineType;
import com.bridge.services.apinvoice.ApInvoiceService;
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
public class SupplierInvoiceDetailsController implements Serializable{
    
    @EJB
    private ApInvoiceService invoiceService ;
    
    private Integer currentInvoiceId ;

    private ApInvoice currentInvoice ;

    private List<ApInvoiceLine> newLines ; 

    
    public void loadInvoice()
    {
       if(currentInvoiceId != null)
       {
          currentInvoice = invoiceService.findWithLinesById(currentInvoiceId) ;
          
          if(currentInvoice == null)
              
              JSFUtils.redirectTo404("Ap Invoice");
           
          else
          {
             newLines = currentInvoice.getLines().stream()
                     .filter(l -> l.isAdditionalExpensesFlag())
                     .collect(Collectors.toList()) ;
          }
       }
       else
           
           JSFUtils.redirectTo404("Ap Invoice");
    }
    
    public void addLine()
    {
       ApInvoiceLine line = new ApInvoiceLine() ;
       
       line.setAdditionalExpensesFlag(true);
       
       line.setInvoice(currentInvoice);
       
       newLines.add(line);
       
       currentInvoice.getLines().add(line);
    }
    
    public String save()
    {
       invoiceService.update(currentInvoice) ;
       
       return "view?faces-redirect=true" ;
    }
    
    public void onAmountChange(ApInvoiceLine line)
    {
        BigDecimal newInvoiceAmount = currentInvoice.getLines().stream()
                .map(ApInvoiceLine::getAmount)
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
    
    public ApInvoice getCurrentInvoice() {
        return currentInvoice;
    }

    public void setCurrentInvoice(ApInvoice currentInvoice) {
        this.currentInvoice = currentInvoice;
    }
    
    public Integer getCurrentInvoiceId() {
        return currentInvoiceId;
    }

    public void setCurrentInvoiceId(Integer currentInvoiceId) {
        this.currentInvoiceId = currentInvoiceId;
    }

    public List<ApInvoiceLine> getNewLines() {
        return newLines;
    }
    
    public InvoiceLineType [] getLineTypes()
    {
       return InvoiceLineType.values() ;
    }   
}
