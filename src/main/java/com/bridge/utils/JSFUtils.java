/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import com.bridge.controllers.hr.DepartmentDetailsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bridge
 */
public class JSFUtils {

    public static void redirectTo404(String msg) {

        try {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            externalContext.getRequestMap().put("msg.error", msg);

            externalContext.dispatch("/faces/errors/404.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(DepartmentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
    public static void redirectTo403() {

        try {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            externalContext.dispatch("/faces/errors/403.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(DepartmentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public static  <T> T evaluateValueExpression(String exp , Class<T> resultClass) {
    
        FacesContext context = FacesContext.getCurrentInstance();
      
        ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
       
        ELContext elContext = context.getELContext();
       
        ValueExpression vex = expressionFactory.createValueExpression(elContext, exp, resultClass);
       
        T result = (T) vex.getValue(elContext);

        return result;
    }
}
