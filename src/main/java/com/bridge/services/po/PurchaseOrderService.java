/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.po;

import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.clients.Client;
import com.bridge.entities.clients.ClientSite;
import com.bridge.entities.clients.SupplierAcc;
import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.InvMaterialTransaction;
import com.bridge.entities.inventory.MainInventory;
import com.bridge.entities.inventory.MtlInventoryItemsQuantity;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.entities.po.PoHeader;
import com.bridge.entities.po.PoLine;
import com.bridge.entities.po.PoLineLocation;
import com.bridge.enums.FaAdditionType;
import com.bridge.enums.InvoiceMatchingOptions;
import com.bridge.enums.PoLineType;
import com.bridge.enums.PoStatus;
import com.bridge.enums.TxTransactionType;
import com.bridge.enums.TxnSourceType;
import com.bridge.services.AbstractService;
import com.bridge.services.inventory.TransactionService;
import com.bridge.services.journal.JournalBatchService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
public class PurchaseOrderService extends AbstractService<PoHeader> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    @EJB
    private JournalBatchService batchService;

    @EJB
    private TransactionService transactionService;

    public PurchaseOrderService() {
        super(PoHeader.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<PoHeader> findByClientOrCodeOrStatus(Integer clientId, String poCode, PoStatus status) {
        StringBuilder queryString = new StringBuilder("SELECT po FROM PoHeader po WHERE 1 = 1");

        if (clientId != null) {
            queryString.append(" AND po.client.clientId = ").append(clientId);
        }

        if (poCode != null && !poCode.isEmpty()) {
            queryString.append(" AND po.poCode = '").append(poCode).append("'");
        }

        if (status != null) {
            queryString.append(" AND po.poStatus = :status");
        }

        TypedQuery<PoHeader> query = entityManager.createQuery(queryString.toString(), PoHeader.class);

        if (status != null) {
            query.setParameter("status", status);
        }

        return query.getResultList();
    }

    public void approvePos(String idsDelmited) {
        entityManager.createQuery("UPDATE PoHeader po set po.poStatus = :status "
                + "WHERE po.poHeaderId in (" + idsDelmited + ")").setParameter("status", PoStatus.APPROVED)
                .executeUpdate();
    }

    public void rejectPos(String idsDelmited) {
        entityManager.createQuery("UPDATE PoHeader po set po.poStatus = :status "
                + "WHERE po.poHeaderId in (" + idsDelmited + ")").setParameter("status", PoStatus.REJECTED)
                .executeUpdate();
    }

    @Override
    public PoHeader update(PoHeader entity) {

        PoHeader p = super.update(entity);

        p.setPoCode(p.getPoHeaderId() + "");

        return p;
    }

    public void updateInvoicedAmount(PoHeader po, BigDecimal newAmount) {
        // because until NOW , MERGE Cascade TO Lines [unneeded behaviour]

        entityManager.createQuery("UPDATE PoHeader po SET po.invoicedAmount = :amount "
                + "WHERE po = :po")
                .setParameter("po", po)
                .setParameter("amount", po.getInvoicedAmount().add(newAmount))
                .executeUpdate();
    }

    public void reactivatePo(PoHeader po) {
        po.setPoStatus(PoStatus.INCOMPLETE);

        update(po);
    }

    public void cancelPo(PoHeader po) {
        po.setPoStatus(PoStatus.CANCELED);

        update(po);
    }

    public PoHeader findPoWithLines(int id) {
        try {

            return entityManager.createQuery("SELECT p FROM PoHeader p left join fetch p.poLines WHERE p.poHeaderId = :id", PoHeader.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

    public List<PoHeader> findWithLinesToInvoiceByIdsAndClient(String idsDelimited, Client client) {
        return entityManager.createQuery("SELECT DISTINCT p FROM PoHeader p JOIN FETCH p.poLines l "
                + "WHERE ( p.poHeaderId IN (" + idsDelimited + ") "
                + "AND ((p.clientSite.supplierAcc.invoiceMatchingOption = com.bridge.enums.InvoiceMatchingOptions.PURCHASE_ORDER AND l.quantityOrdered > l.invoicedQuantity ) OR (p.clientSite.supplierAcc.invoiceMatchingOption = com.bridge.enums.InvoiceMatchingOptions.RECEIPT AND l.quantityReceived > l.invoicedQuantity)) ) "
                + "AND p.client = :client")
                .setParameter("client", client)
                .getResultList();
    }

    public List<PoLine> findNotClosedLinesOfApprovedPos(Integer supplierId, String poCode, PoLineType type) {
        StringBuilder builder = new StringBuilder("SELECT line FROM PoLine line "
                + "WHERE ( line.poHeader.poStatus = com.bridge.enums.PoStatus.APPROVED OR line.poHeader.poStatus = com.bridge.enums.PoStatus.PROCESSING ) "
                + " AND line.poLineStatus = false "
                + " AND line.poLineType = :type");

        if (poCode != null) {
            builder.append(" AND line.poHeader.poCode = '").append(poCode).append("'");
        }

        if (supplierId != null) {
            builder.append(" AND line.poHeader.client.clientId = ").append(supplierId);
        }

        return entityManager.createQuery(builder.toString(), PoLine.class)
                .setParameter("type", type).getResultList();

    }

    public List<PoHeader> findApprovedPosNeededToInvoiceByClient(Client client, Integer poId) {
        StringBuilder builder = new StringBuilder("SELECT p FROM PoHeader p "
                + "WHERE EXISTS ( SELECT 1 FROM PoLine l WHERE ((p.clientSite.supplierAcc.invoiceMatchingOption = com.bridge.enums.InvoiceMatchingOptions.PURCHASE_ORDER AND l.quantityOrdered > l.invoicedQuantity ) OR (p.clientSite.supplierAcc.invoiceMatchingOption = com.bridge.enums.InvoiceMatchingOptions.RECEIPT AND l.quantityReceived > l.invoicedQuantity)) AND l.poHeader = p ) "
                + "AND ( p.poStatus = com.bridge.enums.PoStatus.APPROVED OR p.poStatus = com.bridge.enums.PoStatus.PROCESSING )"
                + "AND p.client = :client ");

        if (poId != null) {
            builder.append(" AND p.poHeaderId = :id");
        }

        TypedQuery<PoHeader> query = entityManager.createQuery(builder.toString(), PoHeader.class);

        query.setParameter("client", client);

        Optional.ofNullable(poId).ifPresent(i -> query.setParameter("id", i));

        return query.getResultList();
    }

    public void removeLines(String lineIds) {
        entityManager.createQuery("DELETE FROM PoLine line WHERE line.poLineId IN (" + lineIds + ")")
                .executeUpdate();
    }

    public void recieveLines(List<PoLine> poLines) {

        poLines.stream().map(InvMaterialTransaction::new).forEach(tx -> {

            PoLine poline = tx.getPoLine();

            BigDecimal quantityCommitted = poline.getQuantityReceived() == null ? BigDecimal.ZERO : poline.getQuantityReceived();

            quantityCommitted = quantityCommitted.add(tx.getTransactionQuantity());

            poline.setQuantityReceived(quantityCommitted);

            if (quantityCommitted.compareTo(poline.getQuantityOrdered()) == 0 || quantityCommitted.compareTo(poline.getQuantityOrdered()) > 0) {
                poline.setPoLineStatus(true);
            }

            tx.setPrimaryQuantity(poline.getQuantityOrdered());

            entityManager.merge(poline);

            transactionService.create(tx);

            PoHeader header = poline.getPoHeader();

            if (header.getPoStatus() != PoStatus.PROCESSING) {
                header.setPoStatus(PoStatus.PROCESSING);

                entityManager.merge(header);
            }

            createJournals(poline);
        });
    }

    public void receiveAssets(List<FaAdditions> assets) {

        assets.forEach(addition -> {

            PoLine poLine = addition.getPoLine();

            BigDecimal quantityCommitted = poLine.getQuantityReceived() == null ? BigDecimal.ZERO : poLine.getQuantityReceived();

            quantityCommitted = quantityCommitted.add(addition.getReceivedQuantity());

            poLine.setQuantityReceived(quantityCommitted);

            if (quantityCommitted.compareTo(poLine.getQuantityOrdered()) == 0 || quantityCommitted.compareTo(poLine.getQuantityOrdered()) > 0) {
                poLine.setPoLineStatus(true);
            }
            addition.setAvailableQuantity(addition.getReceivedQuantity());
            addition.setItemType(FaAdditionType.RECEIPT_ITEM);
            addition.setItemName(poLine.getItemName());
            addition.setUnitPrice(poLine.getListPricePerUnit());
            addition.setTotalAmount(addition.getReceivedQuantity().multiply(poLine.getListPricePerUnit()));
            addition.setPoHeaderId(poLine.getPoHeader().getPoHeaderId());

            entityManager.merge(poLine);

            entityManager.persist(addition);

            PoHeader header = poLine.getPoHeader();

            if (header.getPoStatus() != PoStatus.PROCESSING) {
                header.setPoStatus(PoStatus.PROCESSING);

                entityManager.merge(header);
            }

        });
    }

    public void receiveService(List<PoLineLocation> services) {
        
        services.forEach(service -> {

            PoLine poLine = service.getPoLine();

            BigDecimal quantityCommitted = poLine.getQuantityReceived() == null ? BigDecimal.ZERO : poLine.getQuantityReceived();

            quantityCommitted = quantityCommitted.add(service.getUnitsReceived());

            poLine.setQuantityReceived(quantityCommitted);

            if (quantityCommitted.compareTo(poLine.getQuantityOrdered()) == 0 || quantityCommitted.compareTo(poLine.getQuantityOrdered()) > 0) {
                poLine.setPoLineStatus(true);
            }

            service.setItemType(poLine.getPoLineType());
            service.setUnitPrice(poLine.getListPricePerUnit());
            service.setTotalAmount(service.getUnitsReceived().multiply(poLine.getListPricePerUnit()));
            service.setPoHeaderId(poLine.getPoHeader().getPoHeaderId());
            entityManager.merge(poLine);

            entityManager.persist(service);

            PoHeader header = poLine.getPoHeader();

            if (header.getPoStatus() != PoStatus.PROCESSING) {
                header.setPoStatus(PoStatus.PROCESSING);

                entityManager.merge(header);
            }
        });
    }

    public void transferLinesToInventory(List<MtlInventoryItemsQuantity> lines, MainInventory sourceMainInventory, SecondaryInventory sourceSecondaryInventory, MainInventory targetMainInventory, SecondaryInventory targetSecondaryInventory) {
        lines.stream().forEach(mtl -> {

            InvMaterialTransaction issue
                    = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                            sourceMainInventory, sourceSecondaryInventory,
                            mtl.getTxQuantity(), mtl.getItemQuantity(), mtl.getSystemItem(), mtl.getTxDate(),
                            TxnSourceType.INVENTORY_TRANSFER, "Direct Inventory Transfer");

            InvMaterialTransaction recieve = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                    targetMainInventory, targetSecondaryInventory,
                    mtl.getTxQuantity(), mtl.getItemQuantity(), mtl.getSystemItem(),
                    mtl.getTxDate(),
                    TxnSourceType.INVENTORY_RECIEVE, "Direct Inventory Recieve");

            transactionService.create(recieve);

            transactionService.create(issue);

        });
    }

    public void transferLinesToAccount(List<MtlInventoryItemsQuantity> lines, MainInventory sourceMainInventory, SecondaryInventory sourceSecondaryInventory, GlCodeCombination targetAccount) {
        lines.stream().forEach(mtl -> {

            InvMaterialTransaction issue
                    = new InvMaterialTransaction(TxTransactionType.TRANSFER,
                            sourceMainInventory, sourceSecondaryInventory,
                            mtl.getTxQuantity(), mtl.getItemQuantity(), mtl.getSystemItem(),
                            mtl.getTxDate(),
                            TxnSourceType.INVENTORY_TRANSFER, "Direct Inventory Transfer");

            InvMaterialTransaction recieve = new InvMaterialTransaction(TxTransactionType.RECIEVE,
                    targetAccount,
                    sourceMainInventory.getOperatingUnit(),
                    mtl.getTxQuantity(), mtl.getItemQuantity(), mtl.getSystemItem(),
                    mtl.getTxDate(),
                    TxnSourceType.ACCOUNT_RECIEVE, "Direct Account Recieve");

            transactionService.create(recieve);

            transactionService.create(issue);
        });
    }

    public void createJournals(PoLine line) {
        BigDecimal recievedAmount = line.getTxQuantity().multiply(line.getListPricePerUnit());

        SecondaryInventory secondaryInventory = line.getTargetSecInv();

        batchService.createJournalsForDate(secondaryInventory.getGlId(), "Transaction", line.getTxDate(), recievedAmount, entityManager.find(GlCodeCombination.class, secondaryInventory.getMaterialAccount()), line.getPoHeader().getClientSite().getSupplierAcc().getLiabilityAccountId());
    }

}
