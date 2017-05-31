/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.hr;


import com.bridge.entities.hr.Employee;


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
public class EmployeeService extends AbstractService<Employee>{
    
    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager ;

    public EmployeeService() {
        super(Employee.class);
    }

    
    @Override
    public EntityManager getEntityManager() {

        return entityManager ;
    }    
    
    public List<Employee> findByNameOrDeptOrJob(String name ,Integer deptId  ,Integer jobId)
    {
          StringBuilder queryString = new StringBuilder("SELECT e FROM Employee e WHERE 1=1 ") ;
        
        if(deptId != null)
            
            queryString.append(" AND e.department.departmentId = ").append(deptId);
        
          if(jobId !=null )
            
            queryString.append(" AND e.job.jobId = ").append(jobId);
        
        if(name != null && ! name.isEmpty())
            
            queryString.append(" AND upper(e.firstNameEn) LIKE '%").append(name.toUpperCase()).append("%'") ;
        
        TypedQuery<Employee> query = entityManager.createQuery(queryString.toString(),Employee.class) ;
                
        return query.getResultList() ;      
    }
    
    public List<Employee> findByName(String text)
    {
       return entityManager.createQuery("SELECT e FROM Employee e WHERE UPPER(e.firstNameEn) LIKE UPPER(:text)")
               .setParameter("text", "%"+text+"%")
               .getResultList();
    }
}
