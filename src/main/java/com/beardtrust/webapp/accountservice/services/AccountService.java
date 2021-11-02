/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.dtos.FinancialTransactionDTO;
import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang.NumberUtils.isNumber;

/**
 * @author Nathanael <Nathanael.Grier at your.org>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountRepository repo;

    @Transactional
    public AccountEntity createService(NewAccountRequestModel request) {
        log.trace("Create Service reached...");
        log.debug("Request received by service: " + request);
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        AccountEntity newAccount = new AccountEntity();

        if (user.isPresent()) {
            log.trace("Service found user, creating new account...");
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
            log.debug("New account saved: " + newAccount);
        }
        log.trace("Returning new account...");
        return newAccount;
    }

    public AccountEntity getNewAccountService() {
        log.trace("New Account Service...");
        AccountEntity a = new AccountEntity();
        return a;
    }

    public Page<AccountEntity> getAllService( /*Pageable page*/Integer n, Integer s, String sortName, String sortDir, String search) {
        log.trace("Get all service...");
        log.debug("Page number received: " + n);
        log.debug("Page size received: " + s);
        log.debug("Sort name received: " + sortName);
        log.debug("Sort direction received: " + sortDir);
        log.debug("Search received: " + search);
        List<Sort.Order> orders = new ArrayList();
        orders.add(new Sort.Order(getDirection(sortDir), sortName));
        Pageable page = PageRequest.of(n, s, Sort.by(orders));
        if (!("").equals(search)) {
            log.trace("Search is present, sending to proper method...");
            if (isNumber(search)) {
                log.trace("Search was a number...");
                Integer newSearch = Integer.parseInt(search) * 100;
                return repo.findAllByBalanceOrInterestIsLike(newSearch, newSearch, page);
            } else if (GenericValidator.isDate(search, "yyyy-MM", false)) {
                log.trace("Search determined to be in date format...");
                return repo.findByCreateDate(LocalDate.parse(search), page);
            } else {
                log.trace("Generic search...");
                return repo.findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(search, search, Boolean.valueOf(search), search, search, page);
            }
        } else {
            log.trace("No search parameter, finding all as a page...");
            return repo.findAll(page);
        }
    }

    public Sort.Direction getDirection(String dir) {
        log.trace("Parsing sort direction...");
        log.debug("direction received: " + dir);
        if ("asc".equals(dir)) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }

    public AccountEntity getSpecificService(String id) {
        log.trace("get specific service...");
        log.debug("Service received: " + id);
        Optional<AccountEntity> a = repo.findById(id);
        if (a.isPresent() && a.get().isActiveStatus()) {
            log.trace("Account was found, returning...");
            return a.get();
        } else {
            log.warn("Account not found!!!");
            return null;
        }
    }

    public List<AccountEntity> getListService(String userId) {
        log.trace("Getting account list service...");
        log.debug("Service received: " + userId);
        return repo.findAllByUserId(userId);
    }

    public AccountEntity changeMoneyService(TransferEntity amount, String id) {
        log.trace("Change money service...");
        log.debug("Service id received: " + id);
        log.debug("service amount received: " + amount);
        AccountEntity a = repo.findById(id).get();
        log.trace("Account found...");
        if (a.isActiveStatus()) {
            log.trace("Account active, proceeding...");
            a.getBalance().add(amount.getAmount());
            if ((a.getBalance().getDollars() == 0) && (a.getBalance().getCents() == 0) && (a.getType().getName()
                    == "Recovery")) {
                log.trace("Account was empty and in recovery mode, deactivating...");
                deactivateAccount(a.getId());
            }
            log.trace("Balance changed, saving...");
            repo.save(a);
            return a;
        } else {
            log.warn("Account inactive!!!");
            return null;
        }
    }

    public AccountEntity changeRecoveryService(String id) {
        log.trace("Change recovery service...");
        log.debug("Account Id received: " + id);
        AccountEntity a = repo.findById(id).get();
        log.trace("Account found, Checking active status...");
        if (a.isActiveStatus()) {
            log.trace("Account is active, changing to recovery...");
            a.setType(accountTypeRepository.findByNameIs("Recovery"));
            a.setInterest(0);
            repo.save(a);
            return a;
        } else {
            log.warn("Account inactive!!!");
            return null;
        }
    }

    @Transactional
    public AccountEntity updateService(UpdateAccountRequest a) {
        log.trace("Update service reached...");
        log.debug("Service received: " + a);
        String id = a.getId() != null ? a.getId() : UUID.randomUUID().toString();
        Optional<AccountEntity> account = repo.findById(id);
        AccountTypeEntity type = accountTypeRepository.findByNameIs(a.getType().getName());
        AccountEntity newAccount = null;

        if (account.isPresent()) {
            log.trace("Account found, updating...");
            account.get().setBalance(a.getBalance());
            account.get().setNickname(a.getNickname());
            account.get().setActiveStatus(a.isActiveStatus());
            account.get().setInterest(a.getInterest());
            newAccount = repo.save(account.get());
        } else {
            log.warn("Account couldn't be found, creating new...");
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
        log.trace("Returning the updated account...");
        return newAccount;
    }

    public String deactivateAccount(String a) {
        log.trace("Account deactivation service...");
        log.debug("Deactivate service received: " + a);
        AccountEntity a2 = repo.findById(a).get();
        log.trace("Account found...");
        if (a2 != null) {
            try {
                a2.setActiveStatus(false);
                log.trace("Account deactivated...");
                repo.save(a2);
                return "Account " + a2.getId() + " active status: " + a2.isActiveStatus();
            } catch (Exception e) {
                log.error("Error trying to deactivate an account: " + e);
                return "Account " + a2.getId() + " deactivation failed with error: " + e.getLocalizedMessage();
            }
        }
        log.error("Account could not be found!!!");
        return "Account does not exist";
    }

    public String removeAccount(String id) {
        log.trace("Remove account service...");
        try {
            AccountEntity a = repo.findById(id).get();
            log.trace("Account found, permenantly deleting...");
            repo.delete(a);
            return "Remove successfull";
        } catch (Exception e) {
            log.error("Account could not be found!!!");
            return "Error finding Entity: " + e;
        }
    }
}
