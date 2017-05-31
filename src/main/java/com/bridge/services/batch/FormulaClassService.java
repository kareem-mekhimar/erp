/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.batch;
import com.bridge.entities.batch.FormulaClass;
import com.bridge.services.AbstractService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author AIA
 */

@Stateless
public class FormulaClassService extends AbstractService<FormulaClass> {
    
    
    @PersistenceContext(unitName = "bridge")
    EntityManager entityManager;    
    
    public FormulaClassService(){
        super(FormulaClass.class);
    }
    @Override
    public EntityManager getEntityManager() {
     return entityManager;
    }

     public List<FormulaClass> findFormulaClass(String fmcode,String fmname, Integer fmstat)
    {
        StringBuilder queryString = new StringBuilder("SELECT fmc FROM FormulaClass fmc WHERE 1=1 ") ;
        
//        if(fmid !=null && fmid > 0)
//            
//            queryString.append(" AND fmc.Id = ").append(fmid);
        
          if(fmstat !=null )
            
            queryString.append(" AND fmc.status = ").append(fmstat);
          
          if(fmcode != null && ! fmcode.isEmpty())
            
            queryString.append(" AND upper(fmc.code) LIKE '%").append(fmcode.toUpperCase()).append("%'") ;
        
        if(fmname != null && ! fmname.isEmpty())
            
            queryString.append(" AND upper(fmc.name) LIKE '%").append(fmname.toUpperCase()).append("%'") ;
        
        TypedQuery<FormulaClass> query = entityManager.createQuery(queryString.toString(),FormulaClass.class) ;
                
        return query.getResultList() ;
    }
     
     
         public List<FormulaClass> findClassByName(String text)
    {
       return entityManager.createQuery("SELECT s FROM FormulaClass s WHERE UPPER(s.name) LIKE UPPER(:text)")
               .setParameter("text", text+"%")
               .getResultList() ;
    }
     
     
     
     public boolean isCodeExists(String code){
        return ! entityManager.createQuery("select 1 from FormulaClass s where upper(s.code)= :code")
                      .setParameter("code", code.toUpperCase())
                      .getResultList().isEmpty() ;
                 
     }
     
    public boolean isNameExists(String name){
        return ! entityManager.createQuery("select 1 from FormulaClass s where upper(s.name)= :name")
                      .setParameter("name", name.toUpperCase())
                      .getResultList().isEmpty() ;                 
    }
}
