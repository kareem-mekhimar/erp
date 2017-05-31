/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;

import com.bridge.entities.inventory.SystemItem;
import com.bridge.enums.SystemItemType;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bridge
 */

@Stateless
public class SystemItemService extends AbstractService<SystemItem> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public SystemItemService() {
        super(SystemItem.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<SystemItem> findSystemItems(Integer id, String name) {
        StringBuilder queryString = new StringBuilder("SELECT s FROM SystemItem s WHERE 1=1 ");

        if (id != null) {
            queryString.append(" AND s.inventoryItemId = ").append(id);
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(s.description) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        TypedQuery<SystemItem> query = entityManager.createQuery(queryString.toString(), SystemItem.class);

        return query.getResultList();
    }

    public List<SystemItem> findMainSystemItems(String code, String name, SystemItemType type, Boolean status) {
        StringBuilder queryString = new StringBuilder("SELECT s FROM SystemItem s WHERE 1=1 ");

        if (code != null && !code.isEmpty()) {
            queryString.append(" AND upper(s.inventoryItemCode) LIKE '%").append(code.toUpperCase()).append("%'");
        }

        if (name != null && !name.isEmpty()) {
            queryString.append(" AND upper(s.description) LIKE '%").append(name.toUpperCase()).append("%'");
        }

        if (type != null) {
            queryString.append(" AND upper(s.systemItemType) = :type");
        }

        if (status != null) {
            queryString.append(" AND s.enabledFlag = ").append(status);
        }

        TypedQuery<SystemItem> query = entityManager.createQuery(queryString.toString(), SystemItem.class);

        if (type != null) {
            query.setParameter("type", type);
        }

        return query.getResultList();
    }

    public List<SystemItem> findByNameForPo(String text, SystemItemType type) {

        StringBuilder queryString = new StringBuilder("SELECT s FROM SystemItem s WHERE 1=1 ");

        if (text != null && !text.isEmpty()) {
            queryString.append(" AND upper(s.description) LIKE UPPER('%").append(text).append("%')");
        }
        if (type != null) queryString.append(" AND s.systemItemType = :type");

        TypedQuery<SystemItem> query = entityManager.createQuery(queryString.toString(), SystemItem.class);

        if (type != null) query.setParameter("type", type);

        return query.getResultList();
 
    }
    
    public List<SystemItem> findSalesProductByName(String text) {

             return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text) AND s.customerOrderFlag = true "
                +"AND (s.systemItemType = com.bridge.enums.SystemItemType.INGREDIENT "
                + "OR s.systemItemType = com.bridge.enums.SystemItemType.BY_PRODUCT "
                + "OR s.systemItemType = com.bridge.enums.SystemItemType.FINISHED_PRODUCT)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
 
    }
    public List<SystemItem> findSalesServiceByName(String text) {

     return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text) AND s.customerOrderFlag = true "
                +"AND s.systemItemType = com.bridge.enums.SystemItemType.SERVICE")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
 
    }
    
    public List<SystemItem> findGoodsForSale(String text) {

        String queryString ="SELECT s FROM SystemItem s WHERE s.customerOrderFlag = true"
                + " AND upper(s.description) LIKE UPPER('%"+text
                +"%') AND (s.systemItemType != :type2 OR s.systemItemType != :type3)";
        
        TypedQuery<SystemItem> query = entityManager.createQuery(queryString, SystemItem.class);
        
        query.setParameter("type2", SystemItemType.SERVICE).setParameter("type3", SystemItemType.FIXED_ASSET);
        
        return query.getResultList();
 
    }

    public List<SystemItem> findByName(String text)
    {
        return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
                
    }
    public List<SystemItem> findIngredientByName(String text)
    {
        return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text) "+
                "AND (s.systemItemType = com.bridge.enums.SystemItemType.INGREDIENT "
                + "OR s.systemItemType = com.bridge.enums.SystemItemType.BY_PRODUCT "
                + "OR s.systemItemType = com.bridge.enums.SystemItemType.FINISHED_PRODUCT)")
                .setParameter("text", "%"+text+"%")
                .getResultList() ;
                
    }
    
    public List<SystemItem> findByNameAndType(String text, SystemItemType itemType) {
        return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text) AND s.systemItemType = :type", SystemItem.class)
                .setParameter("text", "%" + text + "%")
                .setParameter("type", itemType)
                .getResultList();
    }

    public List<SystemItem> findByNameAndTypeAndNotIn(String text, SystemItemType itemType, String idsDelimited) {
        return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE s.inventoryItemId NOT IN (" + idsDelimited + ") "
                + " AND UPPER(s.description) LIKE UPPER(:text) AND s.systemItemType = :type", SystemItem.class)
                .setParameter("text", "%" + text + "%")
                .setParameter("type", itemType)
                .getResultList();
    }

    public List<SystemItem> findUnFormedProductsByName(String text) {
        return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE UPPER(s.description) LIKE UPPER(:text) "
                + "AND s.systemItemType = com.bridge.enums.SystemItemType.FINISHED_PRODUCT "
                + "AND s NOT IN (SELECT l.systemItem FROM FormulaLines l)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }
    
    public List<SystemItem> findInInventoriesByNameAndMainIdAndSubId(int mainInvId,int subInv,String text)
    {
       return entityManager.createQuery("SELECT i.systemItem FROM MtlInventoryItemsQuantity i "
               + "WHERE i.mainInventoryId = :mainInv "
               + "AND i.secondaryInventoryId = :subInv "
               + "AND i.itemQuantity > 0"
               + "AND UPPER(i.systemItem.description) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("mainInv",mainInvId )
               .setParameter("subInv", subInv)
               .getResultList() ;
    }
    
    public List<SystemItem> findSparePartInInventoriesByNameAndMainIdAndSubId(int mainInvId,int subInv,String text)
    {
       return entityManager.createQuery("SELECT i.systemItem FROM MtlInventoryItemsQuantity i "
               + "WHERE i.mainInventoryId = :mainInv AND i.secondaryInventoryId = :subInv "
               + "AND i.itemQuantity > 0 AND i.systemItem.systemItemType = 'SPAREPART' "
               + "AND UPPER(i.systemItem.description) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .setParameter("mainInv",mainInvId )
               .setParameter("subInv", subInv)
               .getResultList();
    }
    
    public List<SystemItem> findGoodsForSaleInInventory(int subInv,String text)
    {
       return entityManager.createQuery("SELECT i.systemItem FROM MtlInventoryItemsQuantity i"
               + " WHERE i.secondaryInventoryId = :subInv"
               + " AND i.itemQuantity > 0"
               + " AND i.systemItem.customerOrderFlag = true"
               + " AND UPPER(i.systemItem.description) LIKE UPPER(:text)")
               .setParameter("subInv", subInv)
               .setParameter("text", "%"+text+"%")
               .getResultList() ;
    }
    
    public List<SystemItem> findInInventoriesByName(String text)
    {
      return entityManager.createQuery("SELECT i.systemItem FROM MtlInventoryItemsQuantity i "
              + "WHERE UPPER(i.systemItem.description) LIKE UPPER(:text)")
              .setParameter("text", "%"+text+"%")
              .getResultList() ;
    }

    
 
    public SystemItem findItemWithCategories(Integer id)
    {
        try {
            
               return entityManager.createQuery("SELECT s FROM SystemItem s "
                + "left join fetch s.categoryList "
                + "WHERE s.inventoryItemId =:id",SystemItem.class)
                .setParameter("id", id)
                .getSingleResult();
               
        } catch (Exception e) {
            return null;
        }
     
    }

    
    public List<SystemItem> findGoodsByName(String text)
    {
        TypedQuery<SystemItem> query = entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE s.systemItemType IN (com.bridge.enums.SystemItemType.BY_PRODUCT,com.bridge.enums.SystemItemType.FINISHED_PRODUCT,com.bridge.enums.SystemItemType.INGREDIENT) "
                + "AND UPPER(s.description) LIKE UPPER(:text)",SystemItem.class) 
                .setParameter("text", "%"+text+"%");
      
       return query.getResultList() ;   
    }
    
    public List<SystemItem> findGoodsByNameAndNotIn(String text,String idsDelemited)
    {
        TypedQuery<SystemItem> query = entityManager.createQuery("SELECT s FROM SystemItem s "
                + "WHERE s.systemItemType IN (com.bridge.enums.SystemItemType.BY_PRODUCT,com.bridge.enums.SystemItemType.FINISHED_PRODUCT,com.bridge.enums.SystemItemType.INGREDIENT) "
                + "AND s.inventoryItemId NOT IN("+idsDelemited+")"
                + "AND UPPER(s.description) LIKE UPPER(:text)",SystemItem.class) 
                .setParameter("text", "%"+text+"%");
      
       return query.getResultList() ;       
    }
    
        
    public boolean isCodeExists(String code) {
        return !entityManager.createQuery("select 1 from SystemItem s where upper(s.inventoryItemCode)= UPPER(:code)")
                .setParameter("code", code)
                .getResultList().isEmpty();

    }
     
    public boolean isDescExists(String desc) {
      
        return !entityManager.createQuery("select 1 from SystemItem s where upper(s.description)= UPPER(:desc)")
                .setParameter("desc", desc)
                .getResultList().isEmpty();

    }

    
}
