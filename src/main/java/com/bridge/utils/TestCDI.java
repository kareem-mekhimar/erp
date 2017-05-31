/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import com.bridge.entities.coa.GlCodeCombination;
import com.bridge.entities.inventory.SecondaryInventory;
import com.bridge.services.coa.COAAccountService;
import com.bridge.services.inventory.SubInvService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bridge
 */

@Named
@SessionScoped
public class TestCDI implements Serializable{

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;
  
    @EJB
    private COAAccountService accountService ;
    
    private Integer sitetId ;
    
    public void findSecInv()
    {
     //   List<SecondaryInventory> secondaryInventorys = 
//                   entityManager.createNativeQuery("SELECT * FROM MTL_SECONDARY_INVENTORIES",
//                           SecondaryInventory.class).getResultList() ;
//    
        //secondaryInventorys.forEach(System.out::println);
        
//        entityManager.createQuery("SELECT new com.bridge.entities.inventory.SecondaryInventory((SELECT g.codeCombinationShortName FROM GlCodeCombination g WHERE g.codeCombinationId = s.materialAccount))  "
//                + " FROM SecondaryInventory s ",SecondaryInventory.class).getResultList().forEach((SecondaryInventory s) -> System.out.println(s.getMaterialAccountName())) ;
        
//        entityManager.createNamedQuery("SecondaryInventory.findById",Object[].class)
//                 .setParameter("id", 2)
//                .getResultList().forEach((Object[] o) -> System.out.println(o[0]));
        
    }
    
    public List<GlCodeCombination> completeAccounts(String text) {
        return accountService.findByAlias(text);

    }
    public Integer getSitetId() {
        return sitetId;
    }

    public void setSitetId(Integer sitetId) {
        this.sitetId = sitetId;
    }
    
    
}
