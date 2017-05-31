/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.report;

import com.bridge.enums.ReportFormat;
import java.util.HashMap;

/**
 *
 * @author Bridge
 */
public class ReportPayload {
    
    private HashMap params ;
    
    private ReportFormat reportFormat ;

    private String name ;
    
    public ReportPayload(HashMap params, ReportFormat reportFormat , String name) {
        this.params = params;
        this.reportFormat = reportFormat;
        this.name = name ;
    }

    public HashMap getParams() {
        return params;
    }

    public ReportFormat getReportFormat() {
        return reportFormat;
    }

    public String getName() {
        return name;
    }
    
    
}
