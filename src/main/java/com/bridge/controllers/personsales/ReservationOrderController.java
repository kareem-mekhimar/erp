/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.personsales;

import com.bridge.entities.hr.People;
import com.bridge.entities.personsales.ReservationLines;
import com.bridge.entities.personsales.ReservationOrder;
import com.bridge.enums.ReservationLineType;
import com.bridge.enums.ReservationOrderType;
import com.bridge.services.hr.PeopleService;
import com.bridge.services.personsales.ReservationOrderService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class ReservationOrderController implements Serializable {

    @EJB
    private ReservationOrderService reservationOrderService;
    @EJB
    private PeopleService peopleService;

    private Integer orderNo;

    private People person;

    private ReservationOrderType type;

    private List<ReservationOrder> orderList;
    
    private ReservationOrder releasedOrder;
    
    

    public ReservationOrder getReleasedOrder() {
        return releasedOrder;
    }

    public void setReleasedOrder(ReservationOrder releasedOrder) {
        this.releasedOrder = releasedOrder;
    }
    
    
    

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public ReservationOrderType getType() {
        return type;
    }

    public void setType(ReservationOrderType type) {
        this.type = type;
    }

    public List<ReservationOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ReservationOrder> orderList) {
        this.orderList = orderList;
    }

    public List<People> completePerson(String text) {
        return peopleService.findAgentPerson(text);
    }

    public void releaseOrder() {

        releasedOrder.setStatus(ReservationOrderType.CLOSED);

        releasedOrder = reservationOrderService.findOrderWithLines(releasedOrder.getReservationId());

        if (!releasedOrder.getReservationLines().isEmpty()) {
            releasedOrder.getReservationLines().forEach(l -> {
                if (l.getStatus() == ReservationLineType.RESERVED) {
                    l.setStatus(ReservationLineType.RELEASED);
                    l.setReturnedQty(l.getReservedQty().subtract(l.getProcessedQty()));
                }
            });
        }
        
        releasedOrder.setStatus(ReservationOrderType.CLOSED);
        
        reservationOrderService.updateReservation(releasedOrder);

        filter();
    }

    public void filter() {
        orderList = reservationOrderService.findByNoOrPersonOrStatus(orderNo, person, type);
    }

}
