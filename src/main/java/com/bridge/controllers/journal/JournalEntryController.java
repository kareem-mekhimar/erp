/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.journal;

import com.bridge.controllers.NotificationController;
import com.bridge.entities.journal.GlJeBatch;
import com.bridge.entities.journal.GlJeHeader;
import com.bridge.services.journal.JournalBatchService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Bridge
 */

@Named
@ViewScoped
public class JournalEntryController implements Serializable{
    
    @EJB
    private JournalBatchService batchService ;
    
    @Inject
    private NotificationController notificationController ;
    
    private String currentName ;

    private List<GlJeHeader> headers ;
    
    private Integer currentBatchId ;
    
    private GlJeHeader headerTobeReversed ;
    
    public List<GlJeBatch> completeBatch(String text)
    {
       return batchService.findByNAme(text) ;
    }
    
    public void filter()
    {
      headers = batchService.findHeaderByName(currentName);
      
    }
    
    public void reverseHeader()
    {
        batchService.reverseHeader(headerTobeReversed);
        
        notificationController.showNotification(headerTobeReversed.getJeHeaderName()+" Reversed ...!", NotificationController.SUCCESS);
        
        filter() ;
        
        headerTobeReversed = null ;
    }
    
    
    public String redirectToDetails()
    {
       return "journalentrydetails?faces-redirect=true&batch="+currentBatchId ;
    }
    
    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public List<GlJeHeader> getHeaders() {
        return headers;
    }

    public Integer getCurrentBatchId() {
        return currentBatchId;
    }

    public void setCurrentBatchId(Integer currentBatchId) {
        this.currentBatchId = currentBatchId;
    }

    public GlJeHeader getHeaderTobeReversed() {
        return headerTobeReversed;
    }

    public void setHeaderTobeReversed(GlJeHeader headerTobeReversed) {
        this.headerTobeReversed = headerTobeReversed;
    }
    
    
    
}
