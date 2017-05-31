/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.moveorder;

import com.bridge.services.shipment.ShipmentService;
import com.bridge.entities.batch.GmeMaterialDetail;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.moveorder.MtlTxnRequestLine;
import com.bridge.entities.shipment.ShipmentHeader;
import com.bridge.entities.shipment.ShipmentLine;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.enums.MoveHeaderStatus;
import com.bridge.enums.MoveLineStatus;
import com.bridge.enums.MoveOrderTransactionType;
import com.bridge.enums.MoveOrderType;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.AbstractService;
import com.bridge.services.batch.BatchService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.salesorder.SalesOrderService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */
@Stateless
public class MoveOrderService extends AbstractService<MtlTxnRequestHeader> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    @EJB
    private BatchService batchService;

    @EJB
    private TransactionService transactionService;

    @Inject
    private ShipmentService shipmentService;
    @Inject
    private SalesOrderService salesOrderService;
    

    public MoveOrderService() {
        super(MtlTxnRequestHeader.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void create(MtlTxnRequestHeader header, MainInventory sourceMain, MainInventory targetMain, SecondaryInventory sourceSub, SecondaryInventory targetSub) {

        header.getMtlTxnRequestLines().forEach(l -> {

            l.setHeader(header);

            l.setFromOrganization(sourceMain);

            l.setFromSubinventory(sourceSub);

            l.setToOrganization(targetMain);

            l.setToSubinventory(targetSub);
        });

        super.create(header);
    }

    public void transferMoveOrderToAccount(List<MtlTxnRequestLine> lines, GlCodeCombination targetAccount, MainInventory sourceMainInventory, SecondaryInventory sourceSecondaryInventory,TxnSourceType sourceType , String description) {
        
        List<InvMaterialTransaction> txs = lines.stream()
                .peek(l -> {

                    l.setQuantityDelivered(l.getQuantityDelivered().add(l.getQuantityThatWillTransferInTx()));
                })
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    sourceMainInventory, sourceSecondaryInventory,
                                    l.getQuantityThatWillTransferInTx(), l.getQuantity(),
                                    l.getInventoryItem(), l.getTxDateForMoTransfer(),
                                    sourceType , description);

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            targetAccount,sourceMainInventory.getOperatingUnit(),
                            l.getQuantityThatWillTransferInTx(), l.getQuantity(), 
                            l.getInventoryItem(), l.getTxDateForMoTransfer(),
                            sourceType , description);

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);

        update(lines);
    }
    
    
    
    public void transferMoveOrderToInventory(List<MtlTxnRequestLine> lines, MainInventory sourceMainInventory, SecondaryInventory sourceSecondaryInventory, MainInventory targetMainInventory, SecondaryInventory targetSecondaryInventory,TxnSourceType sourceType , String description) {
       
        MtlTxnRequestHeader header = lines.get(0).getHeader()  ;
        
        List<InvMaterialTransaction> txs = lines.stream()
                .peek(l -> {
                    
                    if(header.getTransactionType() == MoveOrderTransactionType.JOB_SCHEDULE) // To be REMOVED
                    {
                       GmeMaterialDetail detail = l.getGmeMaterialDetail();

                       detail.setReservedQty(detail.getReservedQty().add(l.getQuantityThatWillTransferInTx()));

                       l.setQuantityDelivered(l.getQuantityDelivered().add(l.getQuantityThatWillTransferInTx()));
                    }
                })
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    sourceMainInventory, sourceSecondaryInventory,
                                    l.getQuantityThatWillTransferInTx(), l.getQuantity(),
                                    l.getInventoryItem(), l.getTxDateForMoTransfer(),
                                    sourceType,description);

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            targetMainInventory, targetSecondaryInventory,
                            l.getQuantityThatWillTransferInTx(), l.getQuantity(), 
                            l.getInventoryItem(), l.getTxDateForMoTransfer(),
                            sourceType,description);

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);

        update(lines);

        List<GmeMaterialDetail> batchLinesToBeUpdated = lines.stream()
                .map(MtlTxnRequestLine::getGmeMaterialDetail)
                .collect(Collectors.toList());

        if(! batchLinesToBeUpdated.isEmpty())        
        
            batchService.updateDetails(batchLinesToBeUpdated);
    }
    
    public void transferMoveOrderToShipping(List<MtlTxnRequestLine> moveOrderLines,TxnSourceType sourceType , String description) {
       
        MtlTxnRequestHeader moveOrderHeader = moveOrderLines.get(0).getHeader()  ;
        
        List<InvMaterialTransaction> txs = moveOrderLines.stream()
                   .peek(l -> {

                    l.setQuantityDelivered(l.getQuantityDelivered().add(l.getQuantityThatWillTransferInTx()));
                })
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    moveOrderHeader.getFromMainInventoryId(), moveOrderHeader.getFromSubinventoryId(),
                                    l.getQuantityThatWillTransferInTx(), l.getQuantity(),
                                    l.getInventoryItem(), l.getTxDateForMoTransfer(),
                                    sourceType,description);

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            moveOrderHeader.getToMainInventoryId(), moveOrderHeader.getToSubinventoryId(),
                            l.getQuantityThatWillTransferInTx(), l.getQuantity(), 
                            l.getInventoryItem(), l.getTxDateForMoTransfer(),
                            sourceType,description);

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);
        
        
        // ============= reserve quantities after transact ===============
        
        moveOrderLines.forEach(l-> transactionService.reserveQtyInInventory(moveOrderHeader.getToSubinventoryId(), l.getInventoryItem(),l.getQuantityThatWillTransferInTx()));

        update(moveOrderLines);
        
        ShipmentHeader shipmentHeader = shipmentService.findHeaderByMoveOrder(moveOrderHeader.getHeaderId()) ;

        if (shipmentHeader == null) {
            shipmentHeader = new ShipmentHeader(moveOrderHeader);
        }
        
        createShipment(shipmentHeader,moveOrderHeader, moveOrderLines);

        Map<Integer, BigDecimal> map = moveOrderLines.stream().collect(Collectors.toMap(MtlTxnRequestLine::getSalesLineId, MtlTxnRequestLine::getQuantityThatWillTransferInTx));
        
        updateSalesLines(moveOrderHeader.getSalesOrder(), map, "Shipped");
        
    }
    
        public void transferShippingToCustomer(List<MtlTxnRequestLine> lines,TxnSourceType sourceType , String description) {
            
            MtlTxnRequestHeader header = lines.get(0).getHeader()  ;
        
        List<InvMaterialTransaction> txs = lines.stream()
                .peek(l -> {

                    l.setQuantityDelivered(l.getQuantityDelivered().add(l.getQuantityThatWillTransferInTx()));
                })
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    header.getFromMainInventoryId(), header.getFromSubinventoryId(),
                                    l.getQuantityThatWillTransferInTx(), l.getQuantity(),
                                    l.getInventoryItem(), l.getTxDateForMoTransfer(),
                                    sourceType , description);

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            header.getToAccountId(),header.getOrganization(),
                            l.getQuantityThatWillTransferInTx(), l.getQuantity(), 
                            l.getInventoryItem(), l.getTxDateForMoTransfer(),
                            sourceType , description);

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);

        update(lines);
        
        Map<Integer, BigDecimal> map = lines.stream().collect(Collectors.toMap(MtlTxnRequestLine::getSalesLineId, MtlTxnRequestLine::getQuantityThatWillTransferInTx));
        
        updateSalesLines(header.getSalesOrder(), map, "Delivered");
        
    }
        
        public void transferReturnOrder(List<MtlTxnRequestLine> lines) {

        MtlTxnRequestHeader header=lines.get(0).getHeader();
        
        OeOrderHeader salesOrder = header.getSalesOrder() ;
        
        List<InvMaterialTransaction> txs = lines.stream()
                .peek(l -> {

                    l.setQuantityDelivered(l.getQuantityDelivered().add(l.getQuantityThatWillTransferInTx()));
                })
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                                    header.getToMainInventoryId(), header.getToSubinventoryId(),
                                    l.getQuantityThatWillTransferInTx(), l.getQuantity(), l.getInventoryItem(),
                                    l.getTxDateForMoTransfer(),
                                    TxnSourceType.RETURN_ORDER,"Return Order From Sales Order No "+salesOrder.getHeaderId());

                    InvMaterialTransaction m2
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    header.getFromAccountId(), header.getOrganization() ,l.getQuantityThatWillTransferInTx(),
                                    l.getQuantity(), l.getInventoryItem(), l.getTxDateForMoTransfer(),
                                    TxnSourceType.RETURN_ORDER,"Return Order From Sales Order No "+salesOrder.getHeaderId());

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);

        update(lines);

        Map<Integer, BigDecimal> map = lines.stream().collect(Collectors.toMap(MtlTxnRequestLine::getSalesLineId, MtlTxnRequestLine::getQuantityThatWillTransferInTx));
       
        updateSalesLines(salesOrder, map,"Returned");
            
    }

    
    public List<MtlTxnRequestHeader> findByIdOrBatchId(Integer moveOrderId, Integer batchId) {
        StringBuilder queryBuilder = new StringBuilder("SELECT m FROM MtlTxnRequestHeader m WHERE m.headerStatus != 'CLOSED'");

        if (moveOrderId != null) {
            queryBuilder.append(" AND m.headerId = :mtlId ");
        }

        if (batchId != null) {
            queryBuilder.append(" AND m.reference.batchId = :batchId");
        }

        TypedQuery<MtlTxnRequestHeader> typedQuery = entityManager.createQuery(queryBuilder.toString(), MtlTxnRequestHeader.class);

        Optional.ofNullable(moveOrderId).ifPresent(o -> typedQuery.setParameter("mtlId", moveOrderId));

        Optional.ofNullable(batchId).ifPresent(o -> typedQuery.setParameter("batchId", batchId));

        return typedQuery.getResultList();
    }

    public List<MtlTxnRequestHeader> findNormalById(Integer id) {

        StringBuilder queryBuilder = new StringBuilder("SELECT m FROM MtlTxnRequestHeader m "
                + "WHERE m.transactionType = com.bridge.enums.MoveOrderTransactionType.MOVE_ORDER ");

        if (id != null) {
            queryBuilder.append(" AND m.headerId = :mtlId ");
        }

        TypedQuery<MtlTxnRequestHeader> typedQuery = entityManager.createQuery(queryBuilder.toString(), MtlTxnRequestHeader.class);

        Optional.ofNullable(id).ifPresent(o -> typedQuery.setParameter("mtlId", id));

        return typedQuery.getResultList();

    }

    public MtlTxnRequestHeader findWithLines(Integer id) {
        try {

            return entityManager.createQuery("SELECT m FROM MtlTxnRequestHeader m JOIN FETCH m.mtlTxnRequestLines "
                    + "WHERE m.headerId = :id", MtlTxnRequestHeader.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
    public List<MtlTxnRequestHeader> findBySalesOrder(OeOrderHeader so) {
        try {

            return entityManager.createQuery("SELECT m FROM MtlTxnRequestHeader m WHERE m.salesOrder = :so", MtlTxnRequestHeader.class)
                    .setParameter("so", so)
                    .getResultList();

        } catch (Exception e) {
            return null;
        }
    }

   

    public void createShipment(ShipmentHeader shipmentHeader,MtlTxnRequestHeader moveOrderHeader, List<MtlTxnRequestLine> moveOrderLines) {

//        create shipment header for moveorder header (once);
//        create shipmentline for transaction done on moveorder line (one or many);
        
        List<ShipmentLine> shipmentLines = moveOrderLines.stream()
                .map(l -> new ShipmentLine(l,shipmentHeader))
                .collect(Collectors.toCollection(ArrayList::new));

        shipmentHeader.setShipmentLines(shipmentLines);
        shipmentService.update(shipmentHeader);
    }

    public void updateSalesLines(OeOrderHeader header, Map<Integer, BigDecimal> valueMap,String flag) {

        OeOrderHeader salesOrder = salesOrderService.findWithLines(header.getHeaderId());
        
        switch (flag) {
            case "Shipped":
                   salesOrder.getLines().forEach(l -> {
                if (valueMap.containsKey(l.getLineId())) {
                    l.setShippedQuantity(l.getShippedQuantity().add(valueMap.get(l.getLineId())));
                }
            });
                break;
            case "Delivered":
                     salesOrder.getLines().forEach(l -> {
                if (valueMap.containsKey(l.getLineId())) {
                    l.setDeliveredQuantity(l.getDeliveredQuantity().add(valueMap.get(l.getLineId())));
                }
            });
                break;
            case "Returned":
                     salesOrder.getLines().forEach(l -> {
                if (valueMap.containsKey(l.getLineId())) {
                    l.setReturnedQuantity(l.getReturnedQuantity().add(valueMap.get(l.getLineId())));
                }
            });
                break;
      
        }
        
        salesOrderService.update(salesOrder);
    }
    
    
    @Override
    public void create(MtlTxnRequestHeader header){
        
      getEntityManager().persist(header);  
      
      if(header.getMoveOrderType()==MoveOrderType.PICKWAVE){
          
          header.setHeaderStatus(MoveHeaderStatus.CLOSED);
          
          header.getMtlTxnRequestLines().forEach(l->{
              
              l.setLineStatus(MoveLineStatus.CLOSED);
          l.setQuantityThatWillTransferInTx(l.getQuantity());
          
          });
          
      this.transferMoveOrderToShipping(header.getMtlTxnRequestLines(), TxnSourceType.SALES_ORDER, "Move Order Pick Slip FOR Sales Order No "
              +header.getSalesOrder().getHeaderId());
      
      }
      
      if(header.getSalesOrder() != null){
          
          salesOrderService.update(header.getSalesOrder());
      }
      
    }
    public void update(List<MtlTxnRequestLine> lines) {
        lines.forEach(l -> entityManager.merge(l));
    }
}
