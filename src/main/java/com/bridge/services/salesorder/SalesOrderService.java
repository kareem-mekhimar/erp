/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.salesorder;

import com.bridge.entities.clients.Client;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.salesorder.OeOrderLine;
import com.bridge.entities.shipment.ShipmentLine;
import com.bridge.enums.PaymentTerms;
import com.bridge.services.AbstractService;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author kareem
 */

@Stateless
public class SalesOrderService extends AbstractService<OeOrderHeader>{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public SalesOrderService() {
    
        super(OeOrderHeader.class);
    }
    
    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }
    
    public List<OeOrderHeader> findByIdOrCustomer(Integer id,Integer customerId)
    {
        StringBuilder builder = new StringBuilder("SELECT o FROM OeOrderHeader o WHERE 1 = 1 ") ;
        
        if(customerId != null)
            
            builder.append(" AND o.soldToCustomers.clientId = ").append(customerId) ;
        
        if(id != null)
            
            builder.append(" AND o.headerId = ").append(id) ;
        
       return entityManager.createQuery(builder.toString()).getResultList() ;
    }
    
    public List<OeOrderHeader> findByIdOrCustomerForPick(Integer id,Integer customerId)
    {
        StringBuilder builder = new StringBuilder("SELECT o FROM OeOrderHeader o WHERE o.flowStatusCode != 'CLOSED' ") ;
        
        if(customerId != null)
            
            builder.append(" AND o.soldToCustomers.clientId = ").append(customerId) ;
        
        if(id != null)
            
            builder.append(" AND o.headerId = ").append(id) ;
        
       return entityManager.createQuery(builder.toString()).getResultList() ;
    }
    
    public OeOrderHeader findWithLines(int id)
    {
        try{
        
            return entityManager.createQuery("SELECT o FROM OeOrderHeader o LEFT JOIN FETCH o.lines "
                + " WHERE o.headerId = :id",OeOrderHeader.class)
                    .setParameter("id", id)
                    .getSingleResult() ;
        
        }catch(Exception e)
        {
            return null ;
        }
    }
   
    
    public void removeLines(String lineIds)
    {
       entityManager.createQuery("DELETE FROM OeOrderLine l WHERE l.lineId IN ("+lineIds+")")
               .executeUpdate();
    }
         
         
     public List<OeOrderHeader> findByShipment(){
              
         return entityManager.createQuery("SELECT o FROM OeOrderHeader o WHERE o IN(SELECT l.header FROM OeOrderLine l "
                     + "WHERE (l.deliveredQuantity - l.returnedQuantity) > 0)",OeOrderHeader.class)
                     .getResultList() ;
     }
     
     
     public List<OeOrderHeader> findFullNeededToInvoiceByClient(Client client , Integer salesOrderId)
     {
         StringBuilder builder = new StringBuilder("SELECT o FROM OeOrderHeader o "
                 + "WHERE EXISTS ( SELECT 1 FROM OeOrderLine l WHERE l.orderedQuantity > l.invoicedQuantity AND l.header = o ) "
                 + "AND o.soldToCustomers = :client ") ;
         
         if(salesOrderId != null)
             
             builder.append(" AND o.headerId = :id") ;
         
         TypedQuery<OeOrderHeader> query = entityManager.createQuery(builder.toString(),OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;
         
         Optional.ofNullable(salesOrderId).ifPresent(i -> query.setParameter("id", salesOrderId));
         
         return query.getResultList() ;
                 
     }
     
     
     public List<OeOrderHeader> findWithLinesFullNeededToInvoiceByClientAndIds(String idsDelimeted , Client client)
     {
         TypedQuery<OeOrderHeader> query = entityManager.createQuery("SELECT DISTINCT o FROM OeOrderHeader o JOIN FETCH o.lines l " 
                 + "WHERE l.orderedQuantity > l.invoicedQuantity "
                 + "AND o.soldToCustomers = :client "
                 + "AND o.headerId IN ("+idsDelimeted+")",OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;

         return query.getResultList() ;
                 
     }
     
     public List<OeOrderHeader> findShippedNeededToInvoiceByClient(Client client , Integer salesOrderId)
     {
         StringBuilder builder = new StringBuilder("SELECT o FROM OeOrderHeader o "
                 + "WHERE EXISTS ( SELECT 1 FROM OeOrderLine l WHERE l.shippedQuantity > l.invoicedQuantity AND l.header = o ) "
                 + "AND o.soldToCustomers = :client ") ;
         
         if(salesOrderId != null)
             
             builder.append(" AND o.headerId = :id") ;
         
         TypedQuery<OeOrderHeader> query = entityManager.createQuery(builder.toString(),OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;
         
         Optional.ofNullable(salesOrderId).ifPresent(i -> query.setParameter("id", salesOrderId));
         
         return query.getResultList() ;
                 
     }
  
     
     public List<OeOrderHeader> findWithLinesShippedNeededToInvoiceByClientAndIds(String idsDelimeted , Client client)
     {
         TypedQuery<OeOrderHeader> query = entityManager.createQuery("SELECT DISTINCT o FROM OeOrderHeader o JOIN FETCH o.lines l " 
                 + "WHERE l.shippedQuantity > l.invoicedQuantity "
                 + "AND o.soldToCustomers = :client "
                 + "AND o.headerId IN ("+idsDelimeted+")",OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;

         return query.getResultList() ;
                 
     }
     
     
     public List<OeOrderHeader> findDeliveredNeededToInvoiceByClient(Client client , Integer salesOrderId)
     {
         StringBuilder builder = new StringBuilder("SELECT  o FROM OeOrderHeader o "
                 + "WHERE EXISTS ( SELECT 1 FROM OeOrderLine l WHERE l.deliveredQuantity > l.invoicedQuantity AND l.header = o ) "
                 + "AND o.soldToCustomers = :client ") ;
         
         if(salesOrderId != null)
             
             builder.append(" AND o.headerId = :id") ;
         
         TypedQuery<OeOrderHeader> query = entityManager.createQuery(builder.toString(),OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;
         
         Optional.ofNullable(salesOrderId).ifPresent(i -> query.setParameter("id", salesOrderId));
         
         return query.getResultList() ;
                 
     }
        
  
     public List<OeOrderHeader> findWithLinesDeliveredNeededToInvoiceByClientAndIds(String idsDelimeted , Client client)
     {
         TypedQuery<OeOrderHeader> query = entityManager.createQuery("SELECT DISTINCT o FROM OeOrderHeader o JOIN FETCH o.lines l " 
                 + "WHERE l.deliveredQuantity > l.invoicedQuantity "
                 + "AND o.soldToCustomers = :client "
                 + "AND o.headerId IN ("+idsDelimeted+")",OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;

         return query.getResultList() ;
                 
     }
     
     
      
     public List<OeOrderHeader> findDueNeededToInvoiceByClient(Client client , Integer salesOrderId)
     {
         StringBuilder builder = new StringBuilder("SELECT  o FROM OeOrderHeader o "
                 + "WHERE EXISTS ( SELECT 1 FROM OeOrderLine l WHERE l.deliveredQuantity > l.invoicedQuantity AND l.header = o AND EXISTS( SELECT 1 FROM ShipmentLine s WHERE s.soLineId = l.lineId AND s.invoiceDate <= CURRENT_DATE ) ) "
                 + "AND o.soldToCustomers = :client ") ;
         
         if(salesOrderId != null)
             
             builder.append(" AND o.headerId = :id") ;
         
         TypedQuery<OeOrderHeader> query = entityManager.createQuery(builder.toString(),OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;
         
         Optional.ofNullable(salesOrderId).ifPresent(i -> query.setParameter("id", salesOrderId));
         
         return query.getResultList() ;
                 
     }
       
     
     public List<OeOrderHeader> findWithLinesDueNeededToInvoiceByClientAndIds(String idsDelimeted , Client client)
     {
         TypedQuery<OeOrderHeader> query = entityManager.createQuery("SELECT DISTINCT o FROM OeOrderHeader o JOIN FETCH o.lines l " 
                 + "WHERE l.deliveredQuantity > l.invoicedQuantity "
                 + "AND EXISTS( SELECT 1 FROM ShipmentLine s WHERE s.soLineId = l.lineId AND s.invoiceDate <= CURRENT_DATE ) "
                 + "AND o.soldToCustomers = :client "
                 + "AND o.headerId IN ("+idsDelimeted+")",OeOrderHeader.class) ;
         
         query.setParameter("client", client) ;

         return query.getResultList() ;
                 
     }

     
     public PaymentTerms findPaymentTermById(int id)
     {
        return entityManager.createQuery("SELECT o.paymentTerm FROM OeOrderHeader o "
                + "WHERE o.headerId = :id",PaymentTerms.class)
                .setParameter("id", id)
                .getSingleResult() ;
     }
}
