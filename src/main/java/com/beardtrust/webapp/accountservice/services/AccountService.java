/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang.NumberUtils.isNumber;

import com.beardtrust.webapp.accountservice.repos.AccountTypeRepository;
import com.beardtrust.webapp.accountservice.repos.TransactionRepository;
import com.beardtrust.webapp.accountservice.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Service
@Slf4j
public class AccountService {

    private AccountRepository repo;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository repo, AccountTypeRepository accountTypeRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.repo = repo;
        this.accountTypeRepository = accountTypeRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public AccountEntity createService(NewAccountRequestModel request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        AccountEntity newAccount = new AccountEntity();

        if(user.isPresent()){
            AccountTypeEntity type = accountTypeRepository.findByNameIs(request.getType().getName());
            newAccount.setUser(user.get());
            newAccount.setType(type);
            newAccount.setBalance(request.getBalance());
            newAccount.setInterest(request.getInterest());
            newAccount.setActiveStatus(request.isActiveStatus());
            newAccount.setCreateDate(LocalDateTime.now());
            newAccount.setNickname(request.getNickname());
            newAccount.setId(UUID.randomUUID().toString());
            newAccount = repo.save(newAccount);
        }

        return newAccount;
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
                return repo.findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(search, search, Boolean.valueOf(search), search, search, page);
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
        Optional<AccountEntity> a = repo.findById(id);
        if (a.isPresent() && a.get().isActiveStatus()) {
            return a.get();
        } else {
            return null;
        }
    }

    public List<AccountEntity> getListService(String userId) {
        return repo.findAllByUserId(userId);
    }

    public AccountEntity changeMoneyService(TransferEntity amount, String id) {
        System.out.println("String in service: " + id);
        System.out.println("Amount rcv'd: " + amount.getAmount());
        AccountEntity a = repo.findById(id).get();
        System.out.println("A: " + a);
        if (a.isActiveStatus()) {
            a.getBalance().add(amount.getAmount());
            if ((a.getBalance().getDollars() == 0) && (a.getBalance().getCents() == 0) && (a.getType().getName() ==
                    "Recovery")) {
                deactivateAccount(a.getId());
            }
            repo.save(a);
            return a;
        } else {
            return null;
        }
    }

    public AccountEntity changeRecoveryService(String id) {
        System.out.println("String in service: " + id);
        AccountEntity a = repo.findById(id).get();
        System.out.println("A: " + a);
        if (a.isActiveStatus()) {
            a.setType(accountTypeRepository.findByNameIs("Recovery"));
            a.setInterest(0);
            repo.save(a);
            return a;
        } else {
            return null;
        }
    }

    @Transactional
    public AccountEntity updateService(UpdateAccountRequest a) {
        String id = a.getId() != null ? a.getId() : UUID.randomUUID().toString();
        Optional<AccountEntity> account = repo.findById(id);
        AccountTypeEntity type = accountTypeRepository.findByNameIs(a.getType().getName());
        AccountEntity newAccount = null;

        if(account.isPresent()){
            account.get().setBalance(a.getBalance());
            account.get().setNickname(a.getNickname());
            account.get().setActiveStatus(a.isActiveStatus());
            account.get().setInterest(a.getInterest());
            newAccount = repo.save(account.get());
        } else {
            newAccount = new AccountEntity();
            newAccount.setId(id);
            newAccount.setCreateDate(LocalDateTime.now());
            newAccount.setUser(userRepository.findById(a.getUserId()).orElse(null));
            newAccount.setBalance(a.getBalance());
            newAccount.setInterest(a.getInterest());
            newAccount.setActiveStatus(a.isActiveStatus());
            newAccount.setNickname(a.getNickname());
            newAccount.setType(type);
            newAccount = repo.save(newAccount);
        }

        return newAccount;
//        if (repo.existsById(a.getId())) {
//            repo.save(a);
//            return a;
//        } else {
//            repo.save(a);
//            return a;
//        }
    }

    public String deactivateAccount(String a) {
        AccountEntity a2 = repo.findById(a).get();
        System.out.println("incoming A: " + a);
        if (a2 != null) {
            try {
                a2.setActiveStatus(false);
                repo.save(a2);
                return "Account " + a2.getId() + " active status: " + a2.isActiveStatus();
            } catch (Exception e) {
                return "Account " + a2.getId() + " deactivation failed with error: " + e.getLocalizedMessage();
            }
        }
        return "Account does not exist";
    }

    public String removeAccount(String id) {
        try {
            AccountEntity a = repo.findById(id).get();
            repo.delete(a);
            return "Remove successfull";
        } catch (Exception e) {
            return "Error finding Entity: " + e;
        }
    }

	public Page<AccountTransaction> getAllAccountTransactionsByUserId(String id, Pageable page) {
        return transactionRepository.findAllBySource_IdOrTarget_IdIs(id, id, page);
	}
}
