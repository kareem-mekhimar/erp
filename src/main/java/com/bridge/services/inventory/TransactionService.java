/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.inventory.MtlInventoryItemsQuantity;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.OnhandQty;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.TxTransactionType;
import com.bridge.services.AbstractService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Stateless
public class TransactionService extends AbstractService<InvMaterialTransaction>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    
    public TransactionService() {
        
        super(InvMaterialTransaction.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
    
    public void create(List<InvMaterialTransaction> txs)
    {
       txs.stream().forEach(tx -> create(tx));
    }
    
    public void createFromPersonSales(List<InvMaterialTransaction> txs) //aia
    {
       txs.stream().forEach(tx -> createFromPersonSales(tx));
    }

    @Override
    public void create(InvMaterialTransaction entity) {
       
        TxTransactionType type = entity.getTransactionType() ;
        
        if(type == TxTransactionType.TRANSFER)
        {
            if(entity.getTransferToAccount() == null)
                
                transfer(entity) ;
            
            else
                
                entity.setTransactionQuantity(entity.getTransactionQuantity().negate());
        }
        else
        {
            if(entity.getTransferToAccount() == null)
           
                  receive(entity) ;
            
        }
        
        entityManager.persist(entity);
    }
    
    public void createFromPersonSales(InvMaterialTransaction transaction) { //aia
        
        TxTransactionType type = transaction.getTransactionType() ;
        
        if(type == TxTransactionType.TRANSFER){
            
            updateReservedQty(transaction.getSecondaryInventory(), transaction.getInventoryItem(),transaction.getTransactionQuantity());
            
            transaction.setTransactionQuantity(transaction.getTransactionQuantity().negate());
  
        }
            
              entityManager.persist(transaction);
  
    }
        
    
    public List<MtlInventoryItemsQuantity> findCurrentQuantities(int mainInvId,int subInv,Integer itemId)
    {
        StringBuilder builder = new StringBuilder("SELECT i FROM MtlInventoryItemsQuantity i "
               + "  WHERE i.mainInventoryId = :mainInv "
               + "  AND i.secondaryInventoryId = :subInv "
               + "  AND i.itemQuantity > 0") ;
       
        if(itemId != null)
            
            builder.append(" AND i.systemItem.inventoryItemId = ").append(itemId) ;
        
        return entityManager.createQuery(builder.toString())
               .setParameter("mainInv", mainInvId)
               .setParameter("subInv", subInv)
               .getResultList() ;
 
    }
    
    
    public BigDecimal findActualQuantityInInventory(SystemItem item,SecondaryInventory secondryInventory)
    {
        try {
            
            BigDecimal qty =  entityManager.createQuery("SELECT q.itemQuantity FROM MtlInventoryItemsQuantity q"
                + " WHERE q.systemItem = :item AND q.secondaryInventoryId = :subInvId",BigDecimal.class)
                    .setParameter("item", item)
                    .setParameter("subInvId", secondryInventory.getSecondaryInventoryId())
                    .getSingleResult() ;
            
            return qty ;
            
        }catch(Exception e)
        {
            return BigDecimal.ZERO ;
        }
    }
    
    
    public void receive(InvMaterialTransaction transaction)
    {   
        LocalDate txDate = transaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
        
        OnhandQty onhandQty = new OnhandQty(transaction.getInventoryItem().getInventoryItemId(),
                transaction.getSecondaryInventory().getSecondaryInventoryId(),
                txDate , transaction.getTransactionQuantity());
        
        entityManager.persist(onhandQty);
        
        MtlInventoryItemsQuantity quantity = findCurrentQuantity(transaction.getInventoryItem().getInventoryItemId(),
                transaction.getSecondaryInventory().getSecondaryInventoryId()) ;
        
        if(quantity == null)
           
            createNewQuantity(transaction) ;
        
        else
            
            updateNormalQuantity(transaction) ;
            
    }
    
    
    public void transfer(InvMaterialTransaction transaction)
    {
        int itemId = transaction.getInventoryItem().getInventoryItemId();
        
        int secInvId = transaction.getSecondaryInventory().getSecondaryInventoryId() ;

        LocalDate txDate = transaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ;
        
        BigDecimal txQty = transaction.getTransactionQuantity() ;
        
        //BigDecimal sumUntil = findQuantityUntilDate(itemId, secInvId, txDate) ;
        
        long onHandsCountBeforeTxDate = countOnHand(itemId, secInvId, txDate) ;
        
        int noOfPages = (int) Math.ceil((float) onHandsCountBeforeTxDate / 20) ;
          
        outerLoop:
        for(int page = 0 ; page < noOfPages ; page ++)
        {
            List<OnhandQty> onhandQtys = findOnHandQty(itemId, secInvId, txDate, page * 20, 20) ;
            
            for(OnhandQty onhandQty : onhandQtys)
            {
                BigDecimal currentQty = onhandQty.getQuantity() ;
                
                if(currentQty.compareTo(txQty) > 0)
                {
                    onhandQty.setQuantity(currentQty.subtract(txQty));
                    
                    entityManager.merge(onhandQty) ;
                    
                    break outerLoop ;
                }
                else if(currentQty.compareTo(txQty) == 0)
                {
                    entityManager.remove(onhandQty);
                    
                    break outerLoop ;
                }
                else
                {
                   entityManager.remove(onhandQty);
                   
                   txQty = txQty.subtract(currentQty) ;
                }
            }
        }
        
        transaction.setTransactionQuantity(transaction.getTransactionQuantity().negate()) ;
       
        updateNormalQuantity(transaction);

    }
    
    
    
    
    
    public long countOnHand(int itemId , int subInvId ,LocalDate txDate)
    {
       return entityManager.createQuery("SELECT count(1) FROM OnhandQty o "
               + "WHERE o.txDate <= :txDate "
               + "AND o.itemId = :itemId "
               + "AND o.subInvId = :subInvId",Long.class)
               .setParameter("txDate", txDate)
               .setParameter("itemId", itemId)
               .setParameter("subInvId", subInvId)
               .getSingleResult();
    }
    
    
    public List<OnhandQty> findOnHandQty(int itemId , int subInvId ,LocalDate txDate, int start,int max)
    {
      return entityManager.createQuery("SELECT o FROM OnhandQty o "
               + "WHERE o.txDate <= :txDate "
               + "AND o.itemId = :itemId "
               + "AND o.subInvId = :subInvId "
               + "ORDER BY o.txDate",OnhandQty.class)
               .setParameter("txDate", txDate)
               .setParameter("itemId", itemId)
               .setParameter("subInvId", subInvId)
               .setMaxResults(max)
               .setFirstResult(start)
               .getResultList();       
    }
    

    public BigDecimal findNotReservedQuantityUntilDate(int itemId , int subInvId , LocalDate txDate)
    {
       BigDecimal qty = entityManager.createQuery("SELECT SUM(o.quantity) FROM OnhandQty o "
                + "WHERE o.txDate <= :txDate "
                + "AND o.itemId = :itemId "
                + "AND o.subInvId = :subInvId " , BigDecimal.class)
               .setParameter("txDate", txDate)
               .setParameter("itemId", itemId)
               .setParameter("subInvId", subInvId)
               .getSingleResult() ;
       
       if(qty == null || qty.subtract(findReservedQuantity(itemId, subInvId)).compareTo(BigDecimal.ZERO) < 0)
           
           return BigDecimal.ZERO ;
       
       
       return qty ;
    }
    
    
    private BigDecimal findReservedQuantity(int itemId , int subInvId ) // RESERVED IS FUTURE FOREVER
    {
       return entityManager.createQuery("SELECT m.reservedQuantity FROM MtlInventoryItemsQuantity m "
               + "WHERE m.secondaryInventoryId = :subId "
               + "AND m.systemItem.inventoryItemId = :itemId ",BigDecimal.class)
               .setParameter("itemId", itemId)
               .setParameter("subId",subInvId )
               .getSingleResult();
    }
    
    private MtlInventoryItemsQuantity findCurrentQuantity(int itemId , int subInvId)
    {
        try {
            
            return entityManager.createQuery("SELECT m FROM MtlInventoryItemsQuantity m "
                    + "WHERE m.systemItem.inventoryItemId = :itemId "
                    + "AND m.secondaryInventoryId = :subInvId",MtlInventoryItemsQuantity.class)
                    .setParameter("itemId", itemId)
                    .setParameter("subInvId",subInvId)
                    .getSingleResult();
            
        } catch (Exception e) {
        
            return null ;
        }
    }
    
    
    public void createNewQuantity(InvMaterialTransaction transaction)
    {
        MainInventory mainInventory = transaction.getOrganization() ;
        
        SecondaryInventory secondaryInventory = transaction.getSecondaryInventory() ;
        
        int operatingUnitId = transaction.getOperatingUnit().getOrgUnitId() ;
        
        MtlInventoryItemsQuantity itemsQuantity = new MtlInventoryItemsQuantity(operatingUnitId
                ,mainInventory.getOrganizationId(),secondaryInventory.getSecondaryInventoryId()
                ,transaction.getInventoryItem(),transaction.getTransactionQuantity()) ;
        
        entityManager.persist(itemsQuantity);
    }
    
    
    public void updateNormalQuantity(InvMaterialTransaction transaction)
    {
       entityManager.createQuery("UPDATE MtlInventoryItemsQuantity m set m.itemQuantity = m.itemQuantity + :newQty "
               + "WHERE m.systemItem.inventoryItemId = :item AND m.secondaryInventoryId = :subId")
               .setParameter("item", transaction.getInventoryItem().getInventoryItemId())
               .setParameter("newQty", transaction.getTransactionQuantity())
               .setParameter("subId", transaction.getSecondaryInventory().getSecondaryInventoryId())
               .executeUpdate() ;
    }
    

    
    public void reserveQtyInInventory(SecondaryInventory inv,SystemItem item ,BigDecimal qty){
        
         entityManager.createQuery("UPDATE MtlInventoryItemsQuantity m SET m.itemQuantity = m.itemQuantity - :qty , "
               + " m.reservedQuantity = m.reservedQuantity + :qty "
               + "WHERE m.systemItem = :item AND m.secondaryInventoryId = :inv")
                 
               .setParameter("item", item)
               .setParameter("qty", qty)
               .setParameter("inv", inv.getSecondaryInventoryId())
               .executeUpdate() ; 
    }
    
    public void releaseQtyInInventory(SecondaryInventory inv,SystemItem item ,BigDecimal qty){
        
         entityManager.createQuery("UPDATE MtlInventoryItemsQuantity m SET m.itemQuantity = m.itemQuantity + :qty , "
               + " m.reservedQuantity = m.reservedQuantity - :qty "
               + "WHERE m.systemItem = :item AND m.secondaryInventoryId = :inv")
               .setParameter("item", item)
               .setParameter("qty", qty)
               .setParameter("inv", inv.getSecondaryInventoryId())
               .executeUpdate() ; 
    }

    public void updateReservedQty(SecondaryInventory inv,SystemItem item ,BigDecimal qty){
        
             entityManager.createQuery("UPDATE MtlInventoryItemsQuantity m SET "
               + " m.reservedQuantity = m.reservedQuantity - :qty "
               + "WHERE m.systemItem = :item AND m.secondaryInventoryId = :inv")
               .setParameter("item", item)
               .setParameter("qty", qty)
               .setParameter("inv", inv.getSecondaryInventoryId())
               .executeUpdate() ; 
        
    }
    
}