/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.inventory;
import com.bridge.entities.inventory.UomLine;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AIA
 */
@Stateless
public class UomLineService extends AbstractService<UomLine>{

    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager; 
        
    public UomLineService() {
        super(UomLine.class);
    }

    @Override
    public EntityManager getEntityManager() {
     return entityManager;
    }
      
        public List<UomLine> findUomByName(String text) {
        return entityManager.createQuery("SELECT s FROM UomLine s WHERE UPPER(s.uomName) LIKE UPPER(:text)")
                .setParameter("text", "%" + text + "%")
                .getResultList();
    }
        
        public boolean isCodeExists(String code){
            
        return ! entityManager.createQuery("select t from UomLine t where upper(t.uomCode)= upper(:code)")
                      .setParameter("code", code)
                      .getResultList().isEmpty() ;
                 
     }
         public boolean isNameExists(String name){
        return ! entityManager.createQuery("select t from UomLine t where upper(t.uomName)= upper(:name)")
                      .setParameter("name", name)
                      .getResultList().isEmpty() ;
                 
     }
    
}
