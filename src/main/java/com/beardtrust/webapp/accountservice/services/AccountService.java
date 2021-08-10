/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.dtos.AccountDTO;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Service
public class AccountService {

    private AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public AccountEntity createService(AccountEntity a) {
        repo.save(a);
        return a;
    }

    public AccountEntity getSpecificService(String id) {
        AccountEntity a = repo.findByAccountId(id);
        if (a.isActive_status()) {
            return a;
        } else {
            return null;
        }
    }

    public List<AccountEntity> getAllService(String userId) {
        List<AccountEntity> preSort = repo.findAll();
        List<AccountEntity> Sort = new ArrayList();
        for (int i = 0; i < preSort.size(); i++) {
            if (preSort.get(i).getUserId().equals(userId) && preSort.get(i).isActive_status()) {
                Sort.add(preSort.get(i));
            }
        }
        return Sort;
    }

    public AccountEntity changeMoneyService(TransferEntity amount, String id) {
        System.out.println("String in service: " + id);
        System.out.println("Amount rcv'd: " + amount.getAmount());
        AccountEntity a = repo.findByAccountId(id);
        System.out.println("A: " + a);
        if (a.isActive_status()) {
            a.setBalance(a.getBalance() + amount.getAmount());
            repo.save(a);
            return a;
        } else {
            return null;
        }
    }
    
    public AccountEntity changeRecoveryService(String id) {
        System.out.println("String in service: " + id);
        AccountEntity a = repo.findByAccountId(id);
        System.out.println("A: " + a);
        if (a.isActive_status()) {
            a.setType("Recovery");
            a.setInterest(0);
            repo.save(a);
            return a;
        } else {
            return null;
        }
    }

    public AccountEntity updateService(AccountEntity a) {
        if (repo.existsById(a.getAccountId())) {
            System.out.println("Update Success. WARNING: Account ID has been changed.");
            repo.save(a);
            return a;
        } else {
            System.out.println("Update Success");
            repo.save(a);
            return a;
        }
    }

    public String deactivateAccount(String a) {
        AccountEntity a2 = repo.findByAccountId(a);
        System.out.println("incoming A: " + a);
        if (a2 != null) {
            try {
                a2.setActive_status(false);
                repo.save(a2);
                return "Account " + a2.getAccountId() + " active status: " + a2.isActive_status();
            } catch (Exception e) {
                return "Account " + a2.getAccountId() + " deactivation failed with error: " + e.getLocalizedMessage();
            }
        }
        return "Account does not exist";
    }

}
