/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.shipment;

import com.bridge.entities.shipment.ShipmentHeader;
import com.bridge.entities.shipment.ShipmentLine;
import com.bridge.enums.ShipmentLineStatus;
import com.bridge.services.shipment.ShipmentService;
import com.bridge.utils.JSFUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class ShipmentDetailsController implements Serializable{
    
     @EJB
    private ShipmentService shipmentService;
     
     
    private Integer currentShipmentId;
    
    private ShipmentHeader currentShipment;

    public Integer getCurrentShipmentId() {
        return currentShipmentId;
    }

    public void setCurrentShipmentId(Integer currentShipmentId) {
        this.currentShipmentId = currentShipmentId;
    }

    public ShipmentHeader getCurrentShipment() {
        return currentShipment;
    }

    public void setCurrentShipment(ShipmentHeader currentShipment) {
        this.currentShipment = currentShipment;
    }
    
    public void vaildateQuantity(FacesContext context , UIComponent component , Object value)
    {
        
        if(value == null)
  
            return;
        
        ShipmentLine line=(ShipmentLine) JSFUtils.evaluateValueExpression("#{line}", ShipmentLine.class);
      
        BigDecimal shippedQty = line.getShippedQuantity();
        
        BigDecimal quantity = (BigDecimal) value ;
        
//        if(quantity.compareTo(BigDecimal.ZERO) == 0)
//            
//            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Value Can't Be Zero", "Value Can't Be Zero")) ;
        
        if(quantity.compareTo(shippedQty) > 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Value Over Shipped Qty", "Value Over Shipped Qty")) ;
        
    }
    
     
    public void vaildateDate(FacesContext context , UIComponent component , Object value)
    {
        
        if(value == null)
  
            return;
        
        ShipmentLine line=(ShipmentLine) JSFUtils.evaluateValueExpression("#{line}", ShipmentLine.class);
      
 
        Date  shipDate = line.getShippedDate() ;
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy") ;
        
        String dateStr = format.format(shipDate) ;
        
         try {
             shipDate = format.parse(dateStr) ;
         } catch (ParseException ex) {
             Logger.getLogger(ShipmentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        Date deliverDate = (Date) value ;

        if(deliverDate.compareTo(new Date()) > 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Deliver Date Can't be after today!", "Deliver Date Can't be after today!")) ;
        
        if(deliverDate.compareTo(shipDate) < 0)
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Deliver Date Can't be before Shipping Date "+shipDate, "Deliver Date Can't be before Shipping Date "+shipDate)) ;
        
    }
    
    
    public void loadShipment(){
        
        if(currentShipmentId!=null){
            
        currentShipment=shipmentService.findShipmentWithLines(currentShipmentId);
        
        if(currentShipment==null){
            JSFUtils.redirectTo404("Shipmen");
        }
        
        }
        else{
            currentShipment=new ShipmentHeader();
            currentShipment.setShipmentLines(new ArrayList<ShipmentLine>());
        }
        currentShipment.getShipmentLines().forEach(l->l.setDeliveredDate(l.getShippedDate()));
        
    }
    
    public String save(){
        
        
        shipmentService.updateShipment(currentShipment);
        
        return "shipmentView?faces-redirect=true";
        
    }
}
