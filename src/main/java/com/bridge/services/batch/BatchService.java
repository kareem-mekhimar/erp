/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.batch;

import com.bridge.entities.batch.FormulaLines;
import com.bridge.entities.batch.GmeBatchHeader;
import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.enums.BatchStatus;
import com.bridge.enums.FormulaLineType;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.AbstractService;
import com.bridge.services.inventory.TransactionService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class BatchService extends AbstractService<GmeBatchHeader>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    @EJB
    private FormulaService formulaService ;
    
    @EJB
    private TransactionService transactionService ;
    
    public BatchService() {
        super(GmeBatchHeader.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }

    @Override
    public void create(GmeBatchHeader entity) {
    
        List<FormulaLines> lines = formulaService.findLinesById(entity.getFormula().getFormulaId());
        
        List<GmeMaterialDetail> details = lines.stream()
                .map(GmeMaterialDetail::new)
                .collect(Collectors.toList());
        
        details.forEach(l -> {
                         
            l.setPlanQty(l.getFormulaQty().multiply(BigDecimal.valueOf(entity.getBatchPlanQty())));
                  
            l.setBatch(entity);
        });
        
        entity.setGmeMaterialDetailList(details);
        
        super.create(entity);
    }
    
   
    public void updateDetails(List<GmeMaterialDetail> details)
    {
       details.forEach(l -> entityManager.merge(l));
    }
    
    
    public List<GmeMaterialDetail> findIngredientLinesById(int batchId)
    {
       return entityManager.createQuery("SELECT g FROM GmeMaterialDetail g WHERE g.batch.batchId = :id "
               + "AND g.lineType = com.bridge.enums.FormulaLineType.INGREDIENT")
               .setParameter("id", batchId)
               .getResultList();
    }
    
    
    public List<GmeBatchHeader> findByName(String text)
    {
        return entityManager.createQuery("SELECT g FROM GmeBatchHeader g "
                + "WHERE UPPER(g.batchName) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
    }

     public List<GmeBatchHeader> findByNoOrNameOrStatus(Integer no,String name,BatchStatus status)
     {
        StringBuilder queryString = new StringBuilder("SELECT g FROM GmeBatchHeader g WHERE 1 = 1 ") ;
        
        if(no != null)
            
            queryString.append(" AND g.batchId = ").append(no) ;
        
        if(name != null)
            
            queryString.append(" AND UPPER(g.batchName) LIKE UPPER('%").append(name).append("%')") ;
        
        if(status != null)
            
            queryString.append(" AND g.batchStatus = :status") ;
        
        TypedQuery<GmeBatchHeader> query = entityManager.createQuery(queryString.toString(),GmeBatchHeader.class) ;
                
        Optional.ofNullable(status).ifPresent(s -> query.setParameter("status", s));
        
        return query.getResultList() ;         
     }

     public GmeBatchHeader findWithLines(Integer id)
     {
         try {
                   
             return entityManager.createQuery("SELECT g FROM GmeBatchHeader g LEFT JOIN FETCH g.gmeMaterialDetailList "
                + "WHERE g.batchId = :id",GmeBatchHeader.class)
                .setParameter("id", id)
                .getSingleResult()  ;
                    
         } catch (Exception e) {
             
             return null ;
         }

     }
     
     public void transactIngredients(List<GmeMaterialDetail> lines,MainInventory mainInventory,SecondaryInventory secondaryInventory,GlCodeCombination account)
     {
        List<InvMaterialTransaction> txs = lines.stream()
                .peek(l -> {
                
                    l.setActualQty(l.getActualQty().add(l.getQuantityNeededToPickOrTransact()));
                   
                    l.setQtyRemainAfterTransactProduct(l.getQtyRemainAfterTransactProduct().add(l.getQuantityNeededToPickOrTransact()));
                    
                    l.setReservedQty(l.getReservedQty().subtract(l.getQuantityNeededToPickOrTransact()));
                })
                .flatMap(l -> {
             
                        InvMaterialTransaction m1 =
                              new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                      mainInventory,secondaryInventory, 
                                      l.getQuantityNeededToPickOrTransact(), l.getReservedQty()
                                      ,l.getInventoryItem(),l.getTxDate(),TxnSourceType.TRANSACT_INGREDIENTS,
                                      "Batch No "+l.getBatch().getBatchId()) ;
                    
                       InvMaterialTransaction m2 =
                              new InvMaterialTransaction(TxTransactionType.RECIEVE,
                                      account,
                                      mainInventory.getOperatingUnit(),
                                      l.getQuantityNeededToPickOrTransact(), l.getReservedQty(),
                                      l.getInventoryItem(),l.getTxDate(),TxnSourceType.TRANSACT_INGREDIENTS,
                                      "Batch No "+l.getBatch().getBatchId()) ;
                    
                       return Arrays.asList(m1,m2).stream() ;              
               })
               .collect(Collectors.toList()) ;  
        
            transactionService.create(txs);
            
            updateDetails(lines);
     }
     
     
     public boolean transactProducts(List<GmeMaterialDetail> lines,MainInventory mainInventory,SecondaryInventory secondaryInventory,GlCodeCombination account,GmeMaterialDetail... products)
     {
         List<InvMaterialTransaction> txs = new ArrayList<>();
         
         List<GmeMaterialDetail> ingLinesToUpdateRemain = new ArrayList<>() ;
         
         for(GmeMaterialDetail product : lines)
         {
             GmeBatchHeader batch = product.getBatch() ;
         
             List<GmeMaterialDetail> currentIngredients = batch.getGmeMaterialDetailList().stream()
                                                           .filter(l -> l.getLineType() == FormulaLineType.INGREDIENT)
                                                           .collect(Collectors.toList()) ;
         
             List<FormulaLines> formulaIngredients = product.getFormulaLine().getFormula().getFormulaLines().stream()
                                                      .filter(l -> l.getLineType() == FormulaLineType.INGREDIENT)
                                                      .collect(Collectors.toList()) ;
         

             BigDecimal qtyToCheckFor = product.getQuantityNeededToPickOrTransact();
         
             BigDecimal productFormulaQty = product.getFormulaQty() ;
         
             for(FormulaLines l : formulaIngredients)
             {
                   BigDecimal ingFormulaQty = l.getQty() ;
              
                   BigDecimal qtyForOneProduct = ingFormulaQty.divide(productFormulaQty,RoundingMode.HALF_UP) ;
              
                   BigDecimal neededToTransactQty = qtyForOneProduct.multiply(qtyToCheckFor) ;
              
                   GmeMaterialDetail lineInBatch = currentIngredients.stream()
                          .filter(ll -> l.getSystemItem().getInventoryItemId().equals(ll.getInventoryItem().getInventoryItemId()))
                          .findAny().orElse(null) ;

                   if(lineInBatch == null)
                  
                         return false ;
              
                   BigDecimal remainedQty = lineInBatch.getQtyRemainAfterTransactProduct() ;

                   if(neededToTransactQty.compareTo(remainedQty) > 0)
                  
                         return false  ;
              
                    lineInBatch.setQtyRemainAfterTransactProduct(lineInBatch.getQtyRemainAfterTransactProduct()
                           .subtract(neededToTransactQty));

                    ingLinesToUpdateRemain.add(lineInBatch) ;
             }
                            
             txs.add(new InvMaterialTransaction(TxTransactionType.RECIEVE, mainInventory,secondaryInventory
                     ,product.getQuantityNeededToPickOrTransact(),BigDecimal.ZERO,product.getInventoryItem()
                     ,product.getTxDate(),TxnSourceType.TRANSACT_PRODUCTS,"Batch No "+product.getBatch().getBatchId())) ;

             txs.add(new InvMaterialTransaction(TxTransactionType.TRANSFER, account,
                     mainInventory.getOperatingUnit(),product.getQuantityNeededToPickOrTransact()
                     ,BigDecimal.ZERO,product.getInventoryItem(),product.getTxDate()
                     ,TxnSourceType.TRANSACT_PRODUCTS,"Batch No "+product.getBatch().getBatchId())) ;  
             
             product.setActualQty(product.getActualQty().add(product.getQuantityNeededToPickOrTransact()));
         }
         
         
         transactionService.create(txs);
         
         lines.addAll(ingLinesToUpdateRemain) ;
         
         updateDetails(lines);
         
         return true;
     }
}
