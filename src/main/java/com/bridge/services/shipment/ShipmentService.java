/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.shipment;

import com.bridge.entities.clients.Client;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.moveorder.MtlTxnRequestHeader;
import com.bridge.entities.salesorder.OeOrderHeader;
import com.bridge.entities.shipment.ShipmentHeader;
import com.bridge.entities.shipment.ShipmentLine;
import com.bridge.enums.PaymentTerms;
import com.bridge.enums.ShipmentHeaderStatus;
import com.bridge.enums.ShipmentLineStatus;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.AbstractService;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.SubInvService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.salesorder.SalesOrderService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ShipmentService extends AbstractService<ShipmentHeader> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    @Inject
    private SalesOrderService salesOrderService;
    @Inject
    private TransactionService transactionService;
    @Inject
    private SubInvService subInvService;
    @Inject
    private COAAccountService accountService;

    public ShipmentService() {
        super(ShipmentHeader.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public ShipmentHeader findHeaderByMoveOrder(Integer moveOrderId) {
        try {

            return entityManager.createQuery("SELECT h FROM ShipmentHeader h WHERE h.moveOrderId = " + moveOrderId, ShipmentHeader.class)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public ShipmentHeader findShipmentWithLines(int id) {

        try {

            return entityManager.createQuery("SELECT s FROM ShipmentHeader s left join fetch s.shipmentLines "
                    + "WHERE s.headerId = :id", ShipmentHeader.class).setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<ShipmentHeader> findShipmentBySalesAndMoveOrder(Integer salesOrder, Integer moveOrder) {

        StringBuilder queryString = new StringBuilder("SELECT s FROM ShipmentHeader s WHERE 1 = 1");

        if (salesOrder != null) {
            queryString.append(" AND s.salesOrderId = ").append(salesOrder);
        }

        if (moveOrder != null) {
            queryString.append(" AND s.moveOrderId = ").append(moveOrder);
        }

        TypedQuery<ShipmentHeader> query = entityManager.createQuery(queryString.toString(), ShipmentHeader.class);

        return query.getResultList();
    }

    public void updateSalesOrder(Integer salesOrderId, Map<Integer, BigDecimal> map) {

        OeOrderHeader salesOrder = salesOrderService.findWithLines(salesOrderId);

        salesOrder.getLines().forEach(l -> {
            if (map.containsKey(l.getLineId())) {
                l.setDeliveredQuantity(l.getDeliveredQuantity().add(map.get(l.getLineId())));
                l.setQtyToPick(map.get(l.getLineId()));
            }
        });

        salesOrderService.update(salesOrder);

    }

    public void createTransaction(List<ShipmentLine> lines) {

        ShipmentHeader header = lines.get(0).getHeader();

        SecondaryInventory shippedInventory = subInvService.findById(header.getFromSubInventoryId());

        GlCodeCombination targetAccount = accountService.findById(header.getToAccountId());

        //==============  release quantities before transact ==================
        lines.forEach(l -> transactionService.releaseQtyInInventory(shippedInventory, l.getItemId(), l.getShippedQuantity()));

        List<InvMaterialTransaction> txs = lines.stream()
                .flatMap(l -> {
                    InvMaterialTransaction m1
                            = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                                    shippedInventory.getMainInv(), shippedInventory,
                                    l.getDeliveredQuantity(), l.getShippedQuantity(),
                                    l.getItemId(), l.getDeliveredDate(),
                                    TxnSourceType.SHIPMENT, "Delivered Sales Order No " + header.getSalesOrderId());

                    InvMaterialTransaction m2 = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                            targetAccount, shippedInventory.getMainInv().getOperatingUnit(),
                            l.getDeliveredQuantity(), l.getShippedQuantity(),
                            l.getItemId(), l.getDeliveredDate(),
                            TxnSourceType.SHIPMENT, "Delivered Sales Order No " + header.getSalesOrderId());

                    return Arrays.asList(m1, m2).stream();
                })
                .collect(Collectors.toList());

        transactionService.create(txs);

    }

    public void updateShipment(ShipmentHeader shipment) {

        HashMap map = new HashMap();

        List<ShipmentLine> shipmentLines = new ArrayList();

        if (shipment.getShipmentLines().stream().noneMatch(l -> l.getDeliveredQuantity() != null)) {
            return;
        }

        PaymentTerms salesOrderTerm = salesOrderService.findPaymentTermById(shipment.getSalesOrderId());

        shipment.getShipmentLines().forEach(l -> {

            if (l.getDeliveredQuantity() != null) {

                l.setRefusedQuantity(l.getShippedQuantity().subtract(l.getDeliveredQuantity()));
                l.setStatus(ShipmentLineStatus.DELIVERED);
                shipmentLines.add(l);
                map.put(l.getSoLineId(), l.getDeliveredQuantity());

                calculateInvoiceDate(l, salesOrderTerm);

            }
        });

//        Map<Integer, BigDecimal> map = shipment.getShipmentLines().stream().filter(l->l.getQuantity() != null)
//                .collect(Collectors.toMap(ShipmentLine::getSoLineId, ShipmentLine::getQuantity));
        if (shipment.getShipmentLines().stream().noneMatch(l -> l.getStatus() == ShipmentLineStatus.NEW)) {

            shipment.setStatus(ShipmentHeaderStatus.DELEVERED);

        }

        createTransaction(shipmentLines);

        updateSalesOrder(shipment.getSalesOrderId(), map);

        update(shipment);

    }

    private void calculateInvoiceDate(ShipmentLine line, PaymentTerms paymentTerms) {
        LocalDate deliveredDate = line.getDeliveredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        switch (paymentTerms) {
            case THIRTY:

                line.setInvoiceDate(deliveredDate.plusDays(30));

                break;

            case FOURTYFIVE:

                line.setInvoiceDate(deliveredDate.plusDays(45));

                break;

            case SIXTY:

                line.setInvoiceDate(deliveredDate.plusDays(60));

                break;

            case FIRST_NEXT_MONTH:

                line.setInvoiceDate(deliveredDate.with(TemporalAdjusters.firstDayOfNextMonth()));

                break;

            default:

                line.setInvoiceDate(deliveredDate);
        }
    }

    public List<ShipmentToInvoiceDTO> findInvoiceDTOLinesBySalesOrderIdAndClient(int salesOrderId, Client client) {
        return null;
        //return entityManager.createQuery("SELECT NEW com.bridge. FROM ")
    }
}
