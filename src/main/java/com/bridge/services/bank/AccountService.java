/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.bank;

import com.bridge.entities.bank.Bank;
import com.bridge.entities.bank.BankAccount;
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
public class AccountService extends AbstractService<BankAccount> {

    @PersistenceContext(unitName = "bridge")
    private EntityManager entityManager;

    public AccountService() {
        super(BankAccount.class);
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }

    public void update(List<BankAccount> accounts) {

        accounts.forEach(a -> entityManager.merge(a));
    }

    public List<BankAccount> findByNameAndOperatingId(String name,Integer operatingId)
    {
       return entityManager.createQuery("SELECT b FROM BankAccount b "
               + "WHERE UPPER(b.bankAccountName) LIKE UPPER(:name) "
               + "AND b.orgUnitId.orgUnitId = :operatingId")
               .setParameter("operatingId",operatingId )
               .setParameter("name", "%"+name+"%")
               .getResultList() ;
    }
    
    public List<BankAccount> findByBank(Bank bank)
    {
       return entityManager.createQuery("SELECT b FROM BankAccount b "
               + "WHERE b.bank = :bank")
               .setParameter("bank", bank)
               .getResultList() ;
    }
}
