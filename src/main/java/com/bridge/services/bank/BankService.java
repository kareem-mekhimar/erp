/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.bank;

import com.bridge.entities.bank.Bank;
import com.bridge.entities.bank.BankBranch;
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
public class BankService extends AbstractService<Bank> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public BankService() {
        super(Bank.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public List<Bank> findBank(String name, Boolean status) {
        StringBuilder queryString = new StringBuilder("SELECT b FROM Bank b WHERE 1=1 ");
        if (name != null && !name.isEmpty()) {
            queryString.append("AND UPPER(d.bankName) LIKE UPPER('%").append(name).append("%')");
        }

//        if(status != null)
//        queryString.append("AND b.status = ").append(status);
//        
        return entityManager.createQuery(queryString.toString(), Bank.class).getResultList();
    }

    public Bank findBankWithBranches(int id) {

        try {

            return entityManager.createQuery("SELECT b FROM Bank b left join fetch b.branchList WHERE b.bankId = :id", Bank.class)
                    .setParameter("id", id).getSingleResult();

        } catch (Exception e) {

            return null;
        }

    }

    public void removeBankBranches(String ids) {

        entityManager.createQuery("DELETE FROM BankBranch b WHERE b.bankBranchId IN(" + ids + ")").executeUpdate();
    }

       

}
