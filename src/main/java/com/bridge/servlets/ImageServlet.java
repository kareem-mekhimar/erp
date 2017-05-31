/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.servlets;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.servlet.FileServlet;

/**
 *
 * @author Bridge
 */


@WebServlet(asyncSupported = true,value="/images/*")
public class ImageServlet extends FileServlet{

    @Override
    protected File getFile(HttpServletRequest hsr) throws IllegalArgumentException {
          
        String pathInfo = hsr.getPathInfo() ;

        String path = System.getProperty("jboss.server.data.dir")+"/imgs/"+pathInfo ;
        
        if(! Files.exists(Paths.get(path)) )
            
            return getBlankFile(hsr) ;
        
        return new File(path) ;
    }
    
    
    public File getBlankFile(HttpServletRequest hsr)
    {
        try {
              
            return new File(hsr.getServletContext().getResource("/resources/imgs/blank.png").getFile()) ;
      
        } catch (MalformedURLException ex) {
             
            Logger.getLogger(ImageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        return null ;
    }
}
