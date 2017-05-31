/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.assets;

import com.bridge.entities.assets.FaAssets;
import com.bridge.entities.assets.FaAdditions;
import com.bridge.entities.assets.FaCategory;
import com.bridge.entities.assets.FaTransactions;
import com.bridge.entities.inventory.SystemItem;
import com.bridge.entities.ledger.GlLedger;
import com.bridge.entities.ledger.GlPeriodStatus;
import com.bridge.entities.modules.AssetModuleSetup;
import com.bridge.entities.modules.CashManagementModuleSetup;
import com.bridge.entities.organization.OrganizationUnit;
import com.bridge.entities.physicalStructure.PhysicalLocation;
import com.bridge.entities.po.PoHeader;
import com.bridge.entities.po.PoLine;
import com.bridge.enums.AssetTransactionType;
import com.bridge.enums.AssetReleaseType;
import com.bridge.enums.AssetStatus;
import com.bridge.enums.AssetType;
import com.bridge.enums.FaAdditionType;
import com.bridge.enums.AssetTransactionStatus;
import com.bridge.enums.PoStatus;
import com.bridge.services.AbstractService;
import com.bridge.services.categories.FaCategoryService;
import com.bridge.services.journal.JournalBatchService;
import com.bridge.services.modules.AssetConfigurationService;
import com.bridge.services.modules.CashManagementModuleService;
import com.bridge.services.organization.OrganizationUnitService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */
@Stateless
public class AssetService extends AbstractService<FaAssets> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    @EJB
    private JournalBatchService journalBatchService;
    @EJB
    private OrganizationUnitService orgUnitService;
    @EJB
    private FaCategoryService categoryService;
    @EJB
    private AssetConfigurationService assetConfigService;
    @EJB
    private CashManagementModuleService cashConfigService;

    public AssetService() {
        super(FaAssets.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void saveInitAsset(FaAdditions asset) {
        asset.setItemType(FaAdditionType.INIT_ITEM);
        asset.setAvailableQuantity(asset.getReceivedQuantity());
        entityManager.merge(asset);

    }



    public FaAdditions findAdditionById(Integer id) {
        try {
            return entityManager.createQuery("SELECT f FROM FaAdditions f WHERE f.id = " + id, FaAdditions.class).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<FaAdditions> findAdditions(OrganizationUnit org, String name) {

        StringBuilder queryString = new StringBuilder("SELECT f FROM FaAdditions f WHERE 1=1");

        if (org != null) {
            queryString.append(" AND f.operatingUnit = :org");
        }
        if (name != null) {
            queryString.append(" AND UPPER(f.asset.description) LIKE UPPER(:name)");
        }

        TypedQuery<FaAdditions> query = entityManager.createQuery(queryString.toString(), FaAdditions.class);

        if (org != null) {
            query.setParameter("org", org);
        }
        if (name != null) {
            query.setParameter("name", "%" + name + "%");
        }

        return query.getResultList();

    }

    public List<FaAssets> findAssets(String code, String name, AssetStatus status) {

        StringBuilder queryString = new StringBuilder("SELECT f FROM FaAssets f WHERE f.subAsset = false");
        if (code != null) {
            queryString.append(" AND UPPER(f.assetCode) = UPPER(:code)");
        }
        if (name != null) {
            queryString.append(" AND UPPER(f.assetName) LIKE UPPER(:name)");
        }
        if (status != null) {
            queryString.append(" AND f.status = :status");
        }

        TypedQuery<FaAssets> query = entityManager.createQuery(queryString.toString(), FaAssets.class);
        if (code != null) {
            query.setParameter("code", code);
        }
        if (name != null) {
            query.setParameter("name", "%" + name + "%");
        }
        if (status != null) {
            query.setParameter("status", status);
        }
        return query.getResultList();

    }

    public List<FaAssets> findAssetByOrg(OrganizationUnit org) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.operatingUnit = :org",
                FaAssets.class).setParameter("org", org).getResultList();

    }

    public List<FaAssets> findAssetByCat(FaCategory cat) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.assetCategory = :cat",
                FaAssets.class).setParameter("cat", cat).getResultList();

    }

    public boolean codeExists(String code) {

        return !entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.assetCode = '" + code + "'", FaAssets.class).getResultList().isEmpty();

    }

    public boolean nameExists(String name) {

        return !entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.assetName = '" + name + "'", FaAssets.class).getResultList().isEmpty();

    }

    public List<FaAssets> findAssetByNameAndLocation(PhysicalLocation location, String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.mainAsset = null"
                + " AND f.assetLocation = :location"
                + " AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("location", location)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findAssetOnServiceByNameAndLocation(PhysicalLocation location, String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.mainAsset=null"
                + " AND f.assetLocation = :location AND f.status = 'ONSERVICE'"
                + " AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("location", location)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findAssetOnServiceByName(String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.mainAsset=null"
                + " AND f.status = 'ONSERVICE' AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findAssetByName(String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.subAsset=false"
                + " AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findRetiredAssetByName(String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.subAsset=false"
                + " AND f.status = 'RETIRED'"
                + " AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findComponentByAssetId(Integer assetId) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.subAsset = true"
                + " AND f.mainAsset.assetId = " + assetId, FaAssets.class).getResultList();

    }

    public List<FaAssets> findComponentByName(String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE f.subAsset = true"
                + " AND UPPER(f.assetName) LIKE UPPER(:name)", FaAssets.class)
                .setParameter("name", "%" + name + "%").getResultList();

    }

    public List<FaAssets> findAllAssetByName(String name) {

        return entityManager.createQuery("SELECT f FROM FaAssets f WHERE UPPER(f.assetName) "
                + "LIKE UPPER(:name)", FaAssets.class).setParameter("name", "%" + name + "%").getResultList();

    }

    public void saveAssets(FaAssets asset, FaAdditions addition) {

        BigDecimal cost = addition.getUnitPrice();
        FaAssets main = asset.getMainAsset();
        boolean isNew = asset.getAssetId() == null;
        boolean isSub = asset.getSubAsset();
        boolean isPreservice = asset.getStatus() == AssetStatus.PRESERVICE;
        boolean isCapitalized = asset.getAssetType() == AssetType.CAPITALIZED;

        if (isNew) {

            asset.setOriginalCost(cost);
            
            asset.setCurrentCost(cost);

            asset.setRemainingMonths(asset.getAssetLifeMonths());

            asset.setPremiumDepreciation(asset.getOriginalCost().subtract(asset.getSalvageCost())
                    .divide(asset.getAssetLifeMonths(), 2, RoundingMode.CEILING));

            if (isCapitalized) {
                asset.setStatus(AssetStatus.ONSERVICE);
                asset.setLastEvaluationDate(asset.getOnserviceDate());
            } else {
                asset.setStatus(AssetStatus.PRESERVICE);
                asset.setLastEvaluationDate(asset.getAdditionDate());
            }

            if (isSub) {

                main.setCurrentCost(main.getCurrentCost().add(cost));
                main.setOriginalCost(main.getOriginalCost().add(cost));
                main.setPremiumDepreciation(main.getCurrentCost().divide(main.getAssetLifeMonths(), 2, RoundingMode.CEILING));
                asset.setAssetCategory(asset.getMainAsset().getAssetCategory());
                asset.setAssetType(asset.getMainAsset().getAssetType());
                asset.setStatus(asset.getMainAsset().getStatus());
                asset.setOnserviceDate(asset.getMainAsset().getOnserviceDate());
                update(main);

            }
        } else {

            if (isCapitalized && isPreservice && !isSub) {

                asset.setStatus(AssetStatus.ONSERVICE);

                asset.setLastEvaluationDate(asset.getOnserviceDate());

                changeComponentByAsset(asset);
            }

        }

        asset = update(asset);

        if (isNew) {

            if (isSub) {

                createTransaction(main, null, null, AssetTransactionType.COMPONENT_ADDITION, AssetTransactionStatus.JE_NEED, cost, asset.getAdditionDate(),
                        "update current cost of " + main.getAssetName() + " by add " + asset.getAssetName() + " with cost " + cost);

            } else {

                createTransaction(asset, null, null, AssetTransactionType.ADDITION, AssetTransactionStatus.JE_NEED, cost, asset.getAdditionDate(),
                        "add " + asset.getAssetName() + " with status " + asset.getStatus() + " and cost " + asset.getCurrentCost());
            }
        } else {

            if (isCapitalized && isPreservice && !isSub) {

                createTransaction(asset, null, null, AssetTransactionType.UPDATE, AssetTransactionStatus.JE_NOT_NEED, cost, asset.getAdditionDate(),
                        "update " + asset.getAssetName() + " to status " + asset.getStatus());
            }
        }

        if (addition != null) {

            addition.setLocatedQuantity(addition.getLocatedQuantity().add(BigDecimal.ONE));

            addition.setAvailableQuantity(addition.getAvailableQuantity().subtract(BigDecimal.ONE));

            entityManager.merge(addition);
        }

    }

    public void saveMaintainance(FaAssets asset, SystemItem sparepart, Date date, BigDecimal cost) {

        cost = cost == null ? BigDecimal.ZERO : cost;

        if (sparepart != null) {

//            =======================     inventory transaction for systemitem      ============================
//            =======================     inventory transaction for systemitem      ============================
//            =======================     inventory transaction for systemitem      ============================
            createTransaction(asset, null, null, AssetTransactionType.MAINTENANCE, AssetTransactionStatus.JE_NEED, SparePrice(sparepart), date,
                    "add sparepart to " + asset.getAssetName());
        }

        if (cost.compareTo(BigDecimal.ZERO) > 0) {
            createTransaction(asset, null, null, AssetTransactionType.MAINTENANCE, AssetTransactionStatus.JE_NEED, cost, date,
                    "maintaine " + asset.getAssetName());
        }

    }

    public BigDecimal SparePrice(SystemItem spare) {
        return BigDecimal.TEN;
    }

    public void saveAdjustAssets(FaAssets asset, FaCategory oldCategory, PhysicalLocation oldLocation, Date eventDate, BigDecimal newCost, BigDecimal newLife) {

        newCost = newCost == null ? BigDecimal.ZERO : newCost;

        newLife = newLife == null ? BigDecimal.ZERO : newLife;

        if (oldLocation != null) {

            createTransaction(asset, null, oldLocation, AssetTransactionType.LOCATION_CHANGE, AssetTransactionStatus.JE_NOT_NEED, BigDecimal.ZERO, eventDate,
                    "change asset location from " + oldLocation.getLocationDescription() + " to " + asset.getAssetLocation().getLocationDescription());
        }

        if (oldCategory != null) {

            createTransaction(asset, oldCategory, null, AssetTransactionType.CATEGORY_CHANGE, AssetTransactionStatus.JE_NEED, BigDecimal.ZERO, eventDate,
                    "change asset category from " + oldCategory.getCategoryName() + " to " + asset.getAssetCategory().getCategoryName());

        }

        if (newLife.compareTo(BigDecimal.ZERO) > 0) {

            asset.setRemainingMonths(asset.getRemainingMonths().add(newLife));

            asset.setAssetLifeMonths(asset.getAssetLifeMonths().add(newLife));

            createTransaction(asset, null, null, AssetTransactionType.RE_ADJUSTMENT, AssetTransactionStatus.JE_NOT_NEED, BigDecimal.ZERO, eventDate,
                    "add " + newLife + " Months to life of asset " + asset.getAssetName());

        }

        if (newCost.compareTo(BigDecimal.ZERO) > 0) {

            asset.setCurrentCost(asset.getCurrentCost().add(newCost));

            asset.setPremiumDepreciation(asset.getCurrentCost()
                    .divide(asset.getRemainingMonths(), 2, RoundingMode.CEILING));

            createTransaction(asset, null, null, AssetTransactionType.COST_ADDITION, AssetTransactionStatus.JE_NEED, newCost, eventDate,
                    "add " + newCost + " cost unit to current cost of asset " + asset.getAssetName());
        }

        update(asset);

        if (oldCategory != null || oldLocation != null) {

            changeComponentByAsset(asset);

        }

    }

    public void releasedAsset(FaAssets asset, AssetReleaseType type, BigDecimal cost, BigDecimal price) {

        BigDecimal currentCost = asset.getCurrentCost();

        cost = cost == null ? BigDecimal.ZERO : cost;

        asset.setStatus(AssetStatus.RELEASED);

        asset.setLastEvaluationDate(asset.getReleaseDate());

        if (cost.compareTo(BigDecimal.ZERO) > 0) {

            createTransaction(asset, null, null, AssetTransactionType.RELEASE, AssetTransactionStatus.JE_NEED, cost, asset.getReleaseDate(),
                    "release " + asset.getAssetName());
        }

        if (type == AssetReleaseType.SALE) {

            createTransaction(asset, null, null, AssetTransactionType.SALE, AssetTransactionStatus.JE_NEED, price, asset.getReleaseDate(),
                    "release " + asset.getAssetName());
        }

        update(asset);

        changeComponentByAsset(asset);

    }

    public void changeComponentByAsset(FaAssets asset) {

        List<FaAssets> componentLits = findComponentByAssetId(asset.getAssetId());

        componentLits.forEach(c -> {
            c.setAssetLocation(asset.getAssetLocation());
            c.setAssetCategory(asset.getAssetCategory());
            c.setStatus(asset.getStatus());
            c.setAssetType(asset.getAssetType());
            update(c);
        });
    }

    public void changeComponent(FaAssets component) {

        component.setAssetLocation(component.getMainAsset().getAssetLocation());
        component.setAssetCategory(component.getMainAsset().getAssetCategory());
        component.setStatus(component.getMainAsset().getStatus());
        component.setAssetType(component.getMainAsset().getAssetType());
        update(component);

    }

    public List<FaTransactions> categoryTransactions(GlPeriodStatus period) {

        return entityManager.createQuery("SELECT t FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                + " t.transactionType = 'CATEGORY_CHANGE' AND t.transactionDate BETWEEN :start AND :end", FaTransactions.class)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .getResultList();

    }

    public List<FaTransactions> releaseTransactions(GlPeriodStatus period) {

        return entityManager.createQuery("SELECT t FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                + " t.transactionType = 'RELEASE' AND t.transactionDate BETWEEN :start AND :end", FaTransactions.class)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .getResultList();

    }

    public List<FaTransactions> saleTransactions(GlPeriodStatus period) {

        return entityManager.createQuery("SELECT t FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                + " t.transactionType = 'SALE' AND t.transactionDate BETWEEN :start AND :end", FaTransactions.class)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .getResultList();

    }

    public List<FaTransactions> costAdditionTransactions(GlPeriodStatus period) {

        return entityManager.createQuery("SELECT t FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                + " t.transactionType = 'COST_ADDITION' AND t.transactionDate BETWEEN :start AND :end", FaTransactions.class)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .getResultList();

    }

    public List<FaTransactions> maintenanceTransactions(GlPeriodStatus period) {

        return entityManager.createQuery("SELECT t FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                + " t.transactionType = 'MAINTENANCE' AND t.transactionDate BETWEEN :start AND :end", FaTransactions.class)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .getResultList();

    }

    public BigDecimal additionAssetPrice(FaCategory cat, GlPeriodStatus period) {
        try {
            return entityManager.createQuery("SELECT SUM(t.amount) FROM FaTransactions t WHERE t.journalStatus = 'JE_NEED' AND "
                    + "t.category = :cat AND t.transactionDate BETWEEN :start AND :end AND "
                    + "(t.transactionType = 'ADDITION' OR t.transactionType = 'COMPONENT_ADDITION')", BigDecimal.class)
                    .setParameter("cat", cat)
                    .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .getSingleResult();

        } catch (Exception e) {

            return BigDecimal.ZERO;

        }
    }

    public void additionAssetConfirm(FaCategory cat, GlPeriodStatus period) {

        entityManager.createQuery("UPDATE FaTransactions t SET t.journalStatus = 'JE_CREATED' WHERE t.journalStatus = 'JE_NEED' AND "
                + "t.category = :cat AND t.transactionDate BETWEEN :start AND :end AND "
                + "(t.transactionType = 'ADDITION' OR t.transactionType = 'COMPONENT_ADDITION')")
                .setParameter("cat", cat)
                .setParameter("start", Date.from(period.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setParameter("end", Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .executeUpdate();

    }

    public void createAssetJornals(GlLedger ledger, GlPeriodStatus period) {

        createAddedJornals(ledger.getLedgerId(), period);

        createChangedCategoryJournals(ledger.getLedgerId(), period);

        createDepreciationJornals(ledger.getLedgerId(), period);

        createMaintenanceJournals(ledger.getLedgerId(), period);

        createCostAdditionJournals(ledger.getLedgerId(), period);

        createReleaseJournals(ledger.getLedgerId(), period);

        createSaleJournals(ledger.getLedgerId(), period);

    }

    public void createAddedJornals(Integer ledgerId, GlPeriodStatus period) {

        orgUnitService.findOperatingInLedger(ledgerId).forEach(o -> {
            categoryService.findCategoryByOrg(o).forEach(c -> {
                BigDecimal price = additionAssetPrice(c, period) == null ? BigDecimal.ZERO : additionAssetPrice(c, period);
                if (price.compareTo(BigDecimal.ZERO) > 0) {
                    createAddedJornal(ledgerId, c, price, period.getEndDate());
                    additionAssetConfirm(c, period);

                }
            });
        });
    }

    public void createDepreciationJornals(Integer ledgerId, GlPeriodStatus period) {

        orgUnitService.findOperatingInLedger(ledgerId).forEach(o -> {
            categoryService.findCategoryByOrg(o).forEach(c -> {
                createDepreciationJornal(c, period);
            });
        });

    }

    public void createAddedJornal(Integer ledgerId, FaCategory cat, BigDecimal amount, LocalDate date) {

        Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.ADDITION.getName(), d, amount,
                cat.getAssetAcc(), cat.getAssetClearingAcc());

    }

    public void createDepreciationJornal(FaCategory cat, GlPeriodStatus period) {

        Date date = Date.from(period.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<BigDecimal> depValues = new ArrayList<>();

        findAssetByCat(cat).forEach(a -> {

            if (!a.getSubAsset() && a.getStatus() == AssetStatus.ONSERVICE && a.getCurrentCost().compareTo(a.getSalvageCost()) > 0) {

                Integer months = date.getMonth() - a.getLastEvaluationDate().getMonth();

                if (a.getLastEvaluationDate().getDate() <= 15) {
                    months += 1;
                }

                if (months > 0) {

                    BigDecimal depValue = a.getPremiumDepreciation().multiply(new BigDecimal(months));

                    if (depValue.compareTo(a.getCurrentCost().subtract(a.getSalvageCost())) > 0) {
                        depValue = a.getCurrentCost().subtract(a.getSalvageCost());
                    }

                    depValues.add(depValue);

                    a.setCurrentCost(a.getCurrentCost().subtract(depValue));

                    a.setRemainingMonths(a.getRemainingMonths().subtract(new BigDecimal(months)));

                    a.setLastEvaluationDate(date);

                    update(a);
                }
            }

        });

        BigDecimal value = depValues.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        if (value.compareTo(BigDecimal.ZERO) > 0) {
            journalBatchService.createJournalsForDate(cat.getOperatingUnit().getGlId(), AssetTransactionType.DEPRECIATION.getName(), date, value,
                    cat.getDepreciationExpenseAcc(), cat.getAcumulationDepreciationAcc());
        }
    }

    public void createChangedCategoryJournals(Integer ledgerId, GlPeriodStatus period) {

        List<FaTransactions> transactions = categoryTransactions(period);

        transactions.forEach(t -> {

            journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.CATEGORY_CHANGE.getName(),
                    t.getTransactionDate(), t.getAmount(), t.getCategory().getAssetAcc(), t.getOldCategory().getAssetAcc());

            t.setJournalStatus(AssetTransactionStatus.JE_CREATED);

            entityManager.merge(t);

        });
    }

    public void createCostAdditionJournals(Integer ledgerId, GlPeriodStatus period) {

        List<FaTransactions> transactions = costAdditionTransactions(period);

        transactions.forEach(t -> {

            CashManagementModuleSetup cashSetup = cashConfigService.findByOperating(t.getAsset().getOperatingUnit());

            journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.CATEGORY_CHANGE.getName(),
                    t.getTransactionDate(), t.getAmount(), t.getCategory().getAssetAcc(), cashSetup.getCashAcc());

            t.setJournalStatus(AssetTransactionStatus.JE_CREATED);

            entityManager.merge(t);

        });
    }

    public void createMaintenanceJournals(Integer ledgerId, GlPeriodStatus period) {

        List<FaTransactions> transactions = maintenanceTransactions(period);

        transactions.forEach(t -> {

            CashManagementModuleSetup cashSetup = cashConfigService.findByOperating(t.getCategory().getOperatingUnit());

            AssetModuleSetup assetSetup = assetConfigService.findByOperating(t.getCategory().getOperatingUnit());

            journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.RELEASE.getName(),
                    t.getTransactionDate(), t.getAmount(), assetSetup.getAssetExpensesAcc(), cashSetup.getCashAcc());

            t.setJournalStatus(AssetTransactionStatus.JE_CREATED);

            entityManager.merge(t);

        });

    }

    public void createReleaseJournals(Integer ledgerId, GlPeriodStatus period) {

        List<FaTransactions> transactions = releaseTransactions(period);

        transactions.forEach(t -> {

            CashManagementModuleSetup cashSetup = cashConfigService.findByOperating(t.getAsset().getOperatingUnit());

            AssetModuleSetup assetSetup = assetConfigService.findByOperating(t.getAsset().getOperatingUnit());

            journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.RELEASE.getName(),
                    t.getTransactionDate(), t.getAmount(), assetSetup.getAssetExpensesAcc(), cashSetup.getCashAcc());

            t.setJournalStatus(AssetTransactionStatus.JE_CREATED);

            entityManager.merge(t);

        });

    }

    public void createSaleJournals(Integer ledgerId, GlPeriodStatus period) {

        List<FaTransactions> transactions = saleTransactions(period);

        transactions.forEach(t -> {

            CashManagementModuleSetup cashSetup = cashConfigService.findByOperating(t.getAsset().getOperatingUnit());

            AssetModuleSetup assetSetup = assetConfigService.findByOperating(t.getAsset().getOperatingUnit());

            BigDecimal balance = t.getAmount().subtract(t.getAsset().getCurrentCost());

            if (balance.compareTo(BigDecimal.ZERO) > 0) {

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), balance, cashSetup.getCashAcc(),
                        assetSetup.getCapitalGainsAcc());

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getCurrentCost(), cashSetup.getCashAcc(),
                        t.getCategory().getAssetAcc());

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getOriginalCost().subtract(t.getAsset().getCurrentCost()),
                        t.getCategory().getDepreciationExpenseAcc(), t.getCategory().getAssetAcc());

            } else if (balance.compareTo(BigDecimal.ZERO) < 0) {

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), balance.negate(), cashSetup.getCashAcc(), t.getCategory().getAssetAcc());

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getCurrentCost().add(balance), assetSetup.getCapitalLossesAcc(),
                        t.getCategory().getAssetAcc());

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getOriginalCost().subtract(t.getAsset().getCurrentCost()),
                        t.getCategory().getDepreciationExpenseAcc(), t.getCategory().getAssetAcc());

            } else {

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getCurrentCost(), cashSetup.getCashAcc(), t.getCategory().getAssetAcc());

                journalBatchService.createJournalsForDate(ledgerId, AssetTransactionType.SALE.getName(),
                        t.getTransactionDate(), t.getAsset().getOriginalCost().subtract(t.getAsset().getCurrentCost()),
                        t.getCategory().getDepreciationExpenseAcc(), t.getCategory().getAssetAcc());

            }

            t.setJournalStatus(AssetTransactionStatus.JE_CREATED);

            entityManager.merge(t);

        });

    }

    public void changeServiceAsset(FaAssets asset, Date date, BigDecimal cost, String reason) {

        cost = cost == null ? BigDecimal.ZERO : cost;

        AssetTransactionStatus status;

        AssetTransactionType type;

        String desc;

        if (cost.compareTo(BigDecimal.ZERO) > 0) {

            status = AssetTransactionStatus.JE_NEED;

        } else {

            status = AssetTransactionStatus.JE_NOT_NEED;

        }

        if (asset.getStatus() == AssetStatus.ONSERVICE) {

            asset.setStatus(AssetStatus.RETIRED);
            asset.setRetirmentDate(date);
            asset.setRetirmentReason(reason);
            type = AssetTransactionType.RETIREMENT;
            desc = "retire asset " + asset.getAssetName() + " for " + reason;

        } else {

            asset.setStatus(AssetStatus.ONSERVICE);
            asset.setOnserviceDate(date);
            asset.setLastEvaluationDate(date);
            type = AssetTransactionType.RE_SERVICE;
            desc = "reservice asset " + asset.getAssetName();

        }

        update(asset);

        changeComponentByAsset(asset);

        createTransaction(asset, null, null, type, status, cost, date, desc);

    }

    public void createTransaction(FaAssets asset, FaCategory oldCategory, PhysicalLocation oldLocation, AssetTransactionType type, AssetTransactionStatus status, BigDecimal amount, Date date, String Description) {

        FaTransactions transaction = new FaTransactions();

        transaction.setAsset(asset);
        transaction.setLocationId(asset.getAssetLocation().getLocationId());
        transaction.setCategory(asset.getAssetCategory());
        transaction.setOldCategory(oldCategory);
        transaction.setTransactionType(type);
        transaction.setJournalStatus(status);
        transaction.setTransactionDate(date);
        transaction.setAmount(amount);
        transaction.setDescription(Description);
        if (oldLocation != null) {
            transaction.setOldLocationId(oldLocation.getLocationId());
        }

        entityManager.persist(transaction);

    }

}
