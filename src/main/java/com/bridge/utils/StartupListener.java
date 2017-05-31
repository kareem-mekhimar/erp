/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 *
 * @author Bridge
 */


public class StartupListener implements SystemEventListener{

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {

        try {
            // Create Imgs folder in Jboss Data Dir
            
            Path jbossImgDir = Paths.get(System.getProperty("jboss.server.data.dir")+"/imgs") ;
            
            if(! Files.exists(jbossImgDir))
                
                Files.createDirectory(jbossImgDir) ;
        
        } catch (IOException ex) {
        
            Logger.getLogger(StartupListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isListenerForSource(Object source) {

        return source instanceof Application ; 
    }
    
    
}
