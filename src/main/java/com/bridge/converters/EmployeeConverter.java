/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.converters;

import com.bridge.entities.hr.Employee;
import com.bridge.services.hr.EmployeeService;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author AIA
 */

@Model
public class EmployeeConverter implements Converter{
    
    @EJB
    private EmployeeService employeeService ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

     
        if(value == null)
            
            return null ;
        
        return employeeService.findById(Integer.parseInt(value)) ;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
           
        if(value == null)
            
            return null ;
        
        Employee employee = (Employee) value ;
        
        System.out.println("com.bridge.converters.EmployeeConverter.getAsString() "+employee);
        
        return String.valueOf(employee.getEmployeeId()) ;
    }
    
    
}
