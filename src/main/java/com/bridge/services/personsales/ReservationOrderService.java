/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.personsales;

import com.bridge.entities.hr.People;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.personsales.ReservationOrder;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.enums.ReservationLineType;
import com.bridge.enums.ReservationOrderType;
import com.bridge.enums.SalesLineStatus;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.AbstractService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.salesorder.SalesOrderService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class ReservationOrderService extends AbstractService<ReservationOrder> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;
    
    @Inject
    private SalesOrderService salesOrderService;
    
    @Inject
    private TransactionService transService;

    public ReservationOrderService() {
        super(ReservationOrder.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
    
    public List<ReservationOrder> findByNoOrPersonOrStatus(Integer no,People person,ReservationOrderType type){
        
        StringBuilder queryString = new StringBuilder("SELECT r FROM ReservationOrder r WHERE 1=1");

        if (no != null && no > 0) {
            queryString.append(" AND r.reservationId = ").append(no);
        }
        if (person != null) {
            queryString.append(" AND r.salesPersonId =  :person");
        }

        if (type != null) {
            queryString.append(" AND r.status = ").append(type);
        }
        
        TypedQuery<ReservationOrder> query = entityManager.createQuery(queryString.toString(), ReservationOrder.class);
        
        if (person != null) {
            query.setParameter("person", person);
        }

        return query.getResultList();
        
    }
    
    public ReservationOrder findOrderWithLines(Integer id){
        
          try{
        
            return entityManager.createQuery("SELECT o FROM ReservationOrder o LEFT JOIN FETCH o.reservationLines"
                + " WHERE o.reservationId = :id",ReservationOrder.class)
                    .setParameter("id", id)
                    .getSingleResult() ;
        
        }catch(Exception e)
        {
            return null ;
        }
          
    }
   
    
    public void updateReservation(ReservationOrder order){
        
        if(!order.getReservationLines().isEmpty()){
            
            order.getReservationLines().forEach(l->{
                
            if(l.getStatus()==ReservationLineType.NEW){ 
                
            transService.reserveQtyInInventory(l.getSubInvId(), l.getInventoryItemId(), l.getReservedQty());
            
            l.setStatus(ReservationLineType.RESERVED);
            
            }else if(l.getStatus()==ReservationLineType.RELEASED){
                
                transService.releaseQtyInInventory(l.getSubInvId(), l.getInventoryItemId(), l.getReservedQty().subtract(l.getProcessedQty()));
                
                l.setStatus(ReservationLineType.CLOSED);
                
            }    
        });}

        update(order);
    }
    
      public void transferSalesOrder(List<OeOrderLine> lines) {
        
        List<InvMaterialTransaction> txs = lines.stream()
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    l.getSubInv().getMainInv(), l.getSubInv(),l.getOrderedQuantity(), l.getQtyToPick(),
                                    l.getInventoryItem(), l.getHeader().getOrderedDate(),TxnSourceType.SALES_ORDER,"Person Sales");

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            l.getHeader().getInvoiceToSite().getCustomerAcc().getSalesAccountId(),l.getHeader().getOrg(),
                            l.getOrderedQuantity(), l.getQtyToPick(), l.getInventoryItem(),
                            l.getHeader().getOrderedDate(),TxnSourceType.SALES_ORDER,"Person Sales");

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());
        
        transService.createFromPersonSales(txs);

    }
    
    
    public void saveMession(OeOrderHeader sOrder,ReservationOrder rOrder){
        
        sOrder.getLines().removeIf(l -> l.getOrderedQuantity()==null);
        
        if(sOrder.getLines().isEmpty())return;
        
        rOrder.getReservationLines().forEach(l->{if(sOrder.getLines().stream().filter(m-> l.getId().equals(m.getItemReservationId())).count()>0){
        l.setProcessedQty( l.getProcessedQty().add(
                        sOrder.getLines().stream().filter(m-> l.getId().equals(m.getItemReservationId()))
                                .findFirst().get().getOrderedQuantity()));
        }});
        
        rOrder.getReservationLines().forEach(l ->{if(l.getProcessedQty().compareTo(l.getReservedQty())>=0){l.setStatus(ReservationLineType.CLOSED);}});
        
        sOrder.getLines().forEach(l ->{
                l.setShippedQuantity(l.getOrderedQuantity());
                l.setDeliveredQuantity(l.getOrderedQuantity());
                l.setStatus(SalesLineStatus.CLOSED);
        }); 
        
        sOrder.setShipToSite(sOrder.getInvoiceToSite());
        
        
        update(rOrder);
        
        salesOrderService.update(sOrder);
        
        transferSalesOrder(sOrder.getLines());
        
        
    }
}
