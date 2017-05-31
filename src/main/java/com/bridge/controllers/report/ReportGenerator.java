/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.report;

import com.bridge.controllers.qualifiers.Report;
import com.bridge.enums.ReportFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 *
 * @author Bridge
 */


public class ReportGenerator {

    @Resource(lookup = "java:/BridgeDS")
    private DataSource dataSource ;
    
    public void inventoryItemCreditReport(@Observes @Report(Report.ReportType.INVENTORY_CREDIT) ReportPayload payload) throws SQLException, JRException, IOException
    {
        try(Connection connection = dataSource.getConnection()){
            
            FacesContext facesContext = FacesContext.getCurrentInstance() ;
            
            InputStream reportStream = facesContext.getExternalContext()
                                                   .getResourceAsStream("/WEB-INF/reports/inventory_credit.jasper")  ;
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, payload.getParams(),connection) ;
            
            writeToStream(jasperPrint,payload.getReportFormat(),payload.getName());
        }
            
    }
    
     
    public void assetViewReport(@Observes @Report(Report.ReportType.ASSET_VIEW) ReportPayload payload) throws SQLException, JRException, IOException
    {
        try(Connection connection = dataSource.getConnection()){
            
            FacesContext facesContext = FacesContext.getCurrentInstance() ;
            
            InputStream reportStream = facesContext.getExternalContext()
                                                   .getResourceAsStream("/WEB-INF/reports/allassets.jasper")  ;
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, payload.getParams(),connection) ;
            
            writeToStream(jasperPrint,payload.getReportFormat(),payload.getName());
        }
            
    }
    
    public void transactionsReport(@Observes @Report(Report.ReportType.TRANSACTIONS) ReportPayload payload) throws SQLException, JRException, IOException
    {
        try(Connection connection = dataSource.getConnection()){
            
            FacesContext facesContext = FacesContext.getCurrentInstance() ;
            
            InputStream reportStream = facesContext.getExternalContext()
                                                   .getResourceAsStream("/WEB-INF/reports/material_transactions.jasper")  ;
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, payload.getParams(),connection) ;
            
            writeToStream(jasperPrint,payload.getReportFormat(),payload.getName());
        }        
    }
    
    
    private void writeToStream(JasperPrint jasperPrint,ReportFormat format,String name )throws IOException, JRException
    {
       FacesContext facesContext = FacesContext.getCurrentInstance() ;
       
       HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse() ;        
           
       OutputStream responseOutputStream = null ;
       
       switch(format)
       {
           case PDF :
               
               response.addHeader("Content-disposition", "attachment; filename="+name+".pdf") ;
               
               response.setContentType("application/pdf");
               
               responseOutputStream = response.getOutputStream() ;
               
               JasperExportManager.exportReportToPdfStream(jasperPrint,responseOutputStream);
               
               break;
               
            case XLS :
                      
                response.addHeader("Content-disposition", "attachment; filename="+name+".xlsx") ;
                
                JRXlsxExporter exporter = new JRXlsxExporter();
               
                responseOutputStream = response.getOutputStream() ;

                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
               
                exporter.exportReport();

                break;
                
            
            case WORD : 
                
                response.addHeader("Content-disposition", "attachment; filename="+name+".docx") ;
                 
                JRDocxExporter wordExporter = new JRDocxExporter() ;
                
                responseOutputStream = response.getOutputStream() ;

                wordExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                
                wordExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
               
                wordExporter.exportReport();  
                
                break;
                
            case CSV : 
                
                response.addHeader("Content-disposition", "attachment; filename="+name+".csv") ;
                
                JRCsvExporter csvExporter = new JRCsvExporter() ;
                
                responseOutputStream = response.getOutputStream() ;

                csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                
                csvExporter.setExporterOutput(new SimpleWriterExporterOutput(responseOutputStream));
               
                csvExporter.exportReport();   
                
                break;
                
            default:
                
               response.setContentType("text/html");
               
               responseOutputStream = response.getOutputStream() ;

               HtmlExporter htmlExporter = new HtmlExporter() ;
               
               htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
               
               htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(responseOutputStream));
               
               htmlExporter.exportReport();
               
               break;
                
       }
       
       responseOutputStream.flush();
       
       facesContext.responseComplete();
    }
}
