/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang.NumberUtils.isNumber;
import org.apache.commons.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        System.out.println("Create servince rcv'd: " + a);
        repo.save(a);
        return a;
    }

    public AccountEntity getNewAccountService() {
        System.out.println("New Account Service");
        AccountEntity a = new AccountEntity();
        return a;
    }

    public Page<AccountEntity> getAllService( /*Pageable page*/ Integer n, Integer s, String sortName, String sortDir, String search) {
        List<Sort.Order> orders = new ArrayList();
        orders.add(new Sort.Order(getDirection(sortDir), sortName));
        System.out.println("Inbound sort: " + sortName + " " + sortDir);
        System.out.println("Combined orders: " + orders);
//        System.out.println("Inboung sort: " + page.getSort());
        Pageable page = PageRequest.of(n, s, Sort.by(orders));
        System.out.println("Compiled page: " + page);
        System.out.println("Search param: " + search);
        if (!("").equals(search)) {
            if (isNumber(search)) {
                Integer newSearch = Integer.parseInt(search) * 100;
                return repo.findAllByBalanceOrInterestIsLike(newSearch, newSearch, page);
            } else if (GenericValidator.isDate(search, "yyyy-MM", false)) { 
                return repo.findByCreateDate(LocalDate.parse(search), page);
            } else {
                return repo.findAllByUserIdOrAccountIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(search, search, Boolean.valueOf(search), search, search, page);
            }
        } else {
            return repo.findAll(page);
        }
    }
    
    public Sort.Direction getDirection(String dir) {
        if ("asc".equals(dir)) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }

    public AccountEntity getSpecificService(String id) {
        AccountEntity a = repo.findByAccountId(id);
        if (a.isActiveStatus()) {
            return a;
        } else {
            return null;
        }
    }

    public List<AccountEntity> getListService(String userId) {
        List<AccountEntity> preSort = repo.findAll();
        List<AccountEntity> Sort = new ArrayList();
        for (int i = 0; i < preSort.size(); i++) {
            if (preSort.get(i).getUserId().equals(userId) && preSort.get(i).isActiveStatus()) {
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
        if (a.isActiveStatus()) {
            a.setBalance(a.getBalance() + amount.getAmount());
            if (a.getBalance() == 0 && a.getType() == "Recovery") {
                deactivateAccount(a.getAccountId());
            }
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
        if (a.isActiveStatus()) {
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
                a2.setActiveStatus(false);
                repo.save(a2);
                return "Account " + a2.getAccountId() + " active status: " + a2.isActiveStatus();
            } catch (Exception e) {
                return "Account " + a2.getAccountId() + " deactivation failed with error: " + e.getLocalizedMessage();
            }
        }
        return "Account does not exist";
    }

    public String removeAccount(String id) {
        try {
            AccountEntity a = repo.findByAccountId(id);
            repo.delete(a);
            return "Remove successfull";
        } catch (Exception e) {
            return "Error finding Entity: " + e;
        }
    }

}
