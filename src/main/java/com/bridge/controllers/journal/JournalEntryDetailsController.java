/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.journal;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.entities.journal.GlJeLine;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class JournalEntryDetailsController implements Serializable{
    
    @EJB
    private JournalBatchService batchService ;
    
    @EJB
    private COAAccountService accountService ;
    
    private Integer currentHeaderId ;
    
    private Integer currentBatchId ;
    
    private GlJeBatch currentBatch ;
    
    private GlJeHeader currentHeader ;

    private int maxNo ;
    
    private String warningText ;
    
    public void loadHeader()
    {
        if(currentBatchId != null)
        {
           currentBatch = batchService.findById(currentBatchId) ;
          
           if(currentBatch == null)
           {
              JSFUtils.redirectTo404("Journal Entry");  
          
              return;
           }    
           
           if(currentHeaderId != null)
           {
               currentHeader = batchService.findHeaderWithLinesByIdAndBatchId(currentHeaderId, currentBatch) ;
            
               maxNo = currentHeader.getLines().size() ;
           
           }
           
        }
        else
            
            JSFUtils.redirectTo404("Journal Entry");
        
    }
    
    public List<GlCodeCombination> completeAccounts(String text)
    {
       List<GlJeLine> lines = currentHeader.getLines() ;
       
       GlJeLine currentLine = JSFUtils.evaluateValueExpression("#{line}", GlJeLine.class) ;
       
       if(lines.size() > 1)
       {
          String idsDelimited = lines.stream()
                                     .filter(l -> l != currentLine)
                                     .map(l -> l.getAccount().getCodeCombinationId()+"")
                                     .collect(Collectors.joining(",")) ;
          
          return accountService.findByAliasAndNotIn(text, idsDelimited) ;
       }
       
       return accountService.findByAlias(text) ;
    }
    
    public void onDistributedCheck()
    {
       List<GlJeLine> lines = currentHeader.getLines() ;
       
       if(lines != null)
       {
          lines.stream().forEach(l -> {
          
              l.setCreditFlag(false);
              
              l.setAccountedCr(null);
              
              l.setAccountedDr(null);
          });
          
          currentHeader.setRunningTotalCr(null);
          
          currentHeader.setRunningTotalDr(null);
       }
    }
    
    
    public void onDebitChange(GlJeLine line)
    {     
        BigDecimal debit = calculateDebit() ;
        
        if( ! currentHeader.isDistributedFlag())
        {
            BigDecimal currentLineCredit = line.getAccountedCr() ;
        
            if(currentLineCredit != null)
            {
                line.setAccountedCr(null);
            
                currentHeader.setRunningTotalCr(currentHeader.getRunningTotalCr().subtract(currentLineCredit));
            }
            
        }
        else
        {
            if(debit.compareTo(BigDecimal.valueOf(100)) > 0)
            {
                RequestContext.getCurrentInstance()
                        .execute("UIkit.modal.alert(\"Debit Can't Exceed 100% ...\") ;");
                
                return ; 
            }
            
            if(line.isCreditFlag())
            {
                line.setCreditFlag(false);
                
                currentHeader.setAtLeastCreditCheck(false);
            }
        }
        
        currentHeader.setRunningTotalDr(debit);
    }
    
    public void onCreditChange(GlJeLine line)
    {
        currentHeader.setRunningTotalCr(calculateCredit());
       
        BigDecimal currentLineDebit = line.getAccountedDr() ;
        
        if(currentLineDebit != null)
        {
            line.setAccountedDr(null);
            
            currentHeader.setRunningTotalDr(calculateDebit());
        }        
    }
    
    
    private BigDecimal calculateCredit()
    {
      return currentHeader.getLines().stream()
              .filter(l -> l.getAccountedCr() != null)
              .map(GlJeLine::getAccountedCr)
              .reduce(BigDecimal.ZERO, BigDecimal::add) ;
    }
    
    
    private BigDecimal calculateDebit()
    {
      return currentHeader.getLines().stream()
              .filter(l -> l.getAccountedDr() != null)
              .map(GlJeLine::getAccountedDr)
              .reduce(BigDecimal.ZERO, BigDecimal::add) ;        
    }
    
    
    public void onCreditCheck(GlJeLine line)
    {
        BigDecimal currentLineDebit = line.getAccountedDr() ;
        
        if(currentLineDebit != null)
        {
            line.setAccountedDr(null);
            
            currentHeader.setRunningTotalDr(calculateDebit());
        }    
        
       if(line.isCreditFlag())
       {
             currentHeader.getLines()
                          .stream()
                          .filter(l -> l != line)
                          .forEach(l -> {
                    
                               if(line.isCreditFlag())
                               {
                                   l.setCreditFlag(false);
                               }
                          });
          
             currentHeader.setAtLeastCreditCheck(true);
       }
       else
           
           currentHeader.setAtLeastCreditCheck(false);
    }
    
    public String save()
    {
       if(currentHeader.isDistributedFlag())
       {
          String errorString = "" ;
          
          List<GlJeLine> lines = currentHeader.getLines() ;
       
          if(lines != null && ! lines.isEmpty() && ! currentHeader.isAtLeastCreditCheck())
          {
             errorString += "At least One Credit Must be Selected .. <br/>" ; 
          }
          
          BigDecimal debit = currentHeader.getRunningTotalDr() ;
           
          if(debit == null || (debit.compareTo(BigDecimal.valueOf(100)) != 0))
          {
              errorString += "Total Debit Must Be 100 %..." ;
          }
          
          if(! errorString.isEmpty())
          {
             RequestContext.getCurrentInstance().execute("UIkit.modal.alert(\""+errorString+"\");");
             
             return null ;
          }
       }
       else
       {
          List<GlJeLine> lines = currentHeader.getLines() ;
       
          if(lines != null && ! lines.isEmpty())
          {
               boolean valuesFilled = lines
                       .stream()
                       .allMatch(l -> (l.getAccountedCr() == null && l.getAccountedDr() != null) || (l.getAccountedDr() == null && l.getAccountedCr() != null)  ) ;
       
               currentHeader.setValuesFilled(valuesFilled);
       
               warningText = null ;
       
               if(valuesFilled)
               {
                  if(currentHeader.getRunningTotalCr().compareTo(currentHeader.getRunningTotalDr()) == 0)  
              
                       currentHeader.setBalanced(true);
          
                  else
                  {
                     currentHeader.setBalanced(false);
              
                     warningText = "Credit Total Not Equal To Debit Total ... " ;
                  }
                }
                else
           
                    warningText = "Some Entry Lines Don't Have Credit Nor Debit Values" ;
           
                if(warningText != null)
                {
                  RequestContext requestContext = RequestContext.getCurrentInstance() ;
           
                  requestContext.update("warn");

                  requestContext.execute("modal.show()");
             
                  return null ;
                }
           }
       }
       
       updateHeader();
       
       return "viewjournalentries?faces-redirect=true" ;
    }
    
    
    private void updateHeader()
    {
       currentHeader.setBatch(currentBatch);
       
       batchService.updateHeader(currentHeader) ;
    }
    
    public String onDialogOk()
    {
        updateHeader();
       
        return "viewjournalentries?faces-redirect=true" ;       
    }
    
    public GlJeHeader getCurrentHeader() {
        
        if(currentHeader == null)
            
            currentHeader = new GlJeHeader() ;
        
        return currentHeader;
    }

    public GlJeBatch getCurrentBatch() {
        return currentBatch;
    }

    
    public void addLine()
    {
       List<GlJeLine> lines = currentHeader.getLines() ;
       
       if(lines == null)
       {
           lines = new ArrayList<>() ;
           
           currentHeader.setLines(lines);
       }
       
       GlJeLine line = new GlJeLine() ;
       
       line.setHeader(currentHeader);
       
       lines.add(line) ;
       
       line.setJeLineNum( ++ maxNo);
    }
    
    public Integer getCurrentBatchId() {
        return currentBatchId;
    }

    public void setCurrentBatchId(Integer currentBatchId) {
        this.currentBatchId = currentBatchId;
    }

    
    public void setCurrentHeader(GlJeHeader currentHeader) {
        this.currentHeader = currentHeader;
    }

    public Integer getCurrentHeaderId() {
        return currentHeaderId;
    }

    public void setCurrentHeaderId(Integer currentHeaderId) {
        this.currentHeaderId = currentHeaderId;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    
    
}
