/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.NewAccountTypeRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang.NumberUtils.isNumber;
import static org.apache.commons.validator.GenericValidator.isDouble;

/**
 * @author Nathanael <Nathanael.Grier at your.org>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Service
@Slf4j
public class AccountService {

    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository repo;

    public AccountService(AccountRepository repo, AccountTypeRepository accountTypeRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.repo = repo;
        this.accountTypeRepository = accountTypeRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountEntity createNewAccount(AccountEntity a) {
        a.setCreateDate(LocalDateTime.now());
        AccountTypeEntity at = new AccountTypeEntity(
                a.getType().getName(),
                a.getType().getDescription(),
                true,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30000));
        at.setId(a.getType().getId());
        a.setType(at);
        try {
            repo.save(a);
            return a;
        } catch(Exception e) {
            log.warn("Exception trying to save new account: " + e.getMessage());
            try {
                accountTypeRepository.save(a.getType());
                repo.save(a);
                return a;
            } catch(Exception e2) {
                log.warn("Unable to conclude second save attempt with error: " + e2.getMessage());
            }
            return null;
        }
    }

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

    public AccountEntity getNewAccountService(String userId) {
        log.trace("New Account Service...");
        AccountEntity a = new AccountEntity();
        a.setUser(userRepository.findById(userId).get());
        AccountTypeEntity at = new AccountTypeEntity();
        at.setActive(true);
        at.setCreatedDate(LocalDateTime.now());
        a.setType(at);
        return a;
    }

    public AccountEntity createNewAccountService() {
        log.trace("New Account Service...");
        AccountEntity a = new AccountEntity();
        AccountTypeEntity at = new AccountTypeEntity();
        at.setActive(true);
        at.setCreatedDate(LocalDateTime.now());
        a.setType(at);
        return a;
    }

    public List<AccountEntity> getMyAccountsList(String userId) {
        log.trace("Get my account list service reached...");
        log.trace("Returning from get my account list service...");
        return repo.findAllByUser_UserId(userId);
    }

    public Page<AccountEntity> getAllMyAccountsPage(int n, int s, String[] sortBy, String search, String id) {
        String sortName = sortBy[0];
        String sortDir = sortBy[1];
        System.out.println("Attempting to find my accounts");
        List<Sort.Order> orders = new ArrayList();
        orders.add(new Sort.Order(getSortDirection(sortDir), sortName));
        System.out.println("Inbound sort: " + sortName + " " + sortDir);
        System.out.println("Combined orders: " + orders);
        Pageable page = PageRequest.of(n, s, Sort.by(orders));
        System.out.println("Compiled page: " + page);
        System.out.println("Search param: " + search);
        if (!("").equals(search)) {
            if (isDouble(search)) {
//                System.out.println("search was a double");
//                Double newSearch = Double.parseDouble(search);
//                return repo.findAllByInterestOrBalance_DollarsOrBalance_CentsAndUser_Id(newSearch, newSearch, newSearch, id, page);
            } else if (isNumber(search)) {
                System.out.println("search was an Integer");
                Integer newSearch = Integer.parseInt(search);
                return repo.findAllByUser_UserIdAndInterestOrBalance_DollarsOrBalance_Cents(id, newSearch, newSearch, newSearch, page);
            }
            if (GenericValidator.isDate(search, "yyyy-MM", false)) {
                System.out.println("search was a date");
                return repo.findAllByUser_UserIdAndCreateDate(id, LocalDate.parse(search), page);
            } else {
                return repo.findByUser_UserIdAndNicknameOrUser_UserIdAndType_IdOrUser_UserIdAndType_NameOrUser_UserIdAndType_IsActiveOrUser_UserIdAndIdAllIgnoreCase(id, search, id, search, id, search, id, Boolean.valueOf(search), id, search, page);
            }
        }
        System.out.println("generic search, found:" + repo.findAllByUser_UserId(id, page));
        System.out.println("UserId searched by: " + id);
        return repo.findAllByUser_UserId(id, page);
    }

    private Sort.Direction getSortDirection(String direction) {
        Sort.Direction returnValue = Sort.Direction.ASC;

        if (direction.equals("desc")) {
            returnValue = Sort.Direction.DESC;
        }

        return returnValue;
    }

    private List<Sort.Order> parseOrders(String[] sortBy) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortBy[0].contains(",")) {
            for (String sortOrder : sortBy) {
                String[] _sortBy = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sortBy[1]), _sortBy[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
        }

        return orders;
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

    public Page<AccountEntity> getAllService( /*Pageable page*/Integer n, Integer s, String sortName, String sortDir, String search) {
        log.trace("Get all service...");
        log.debug("Page number received: " + n);
        log.debug("Page size received: " + s);
        log.debug("Sort name received: " + sortName);
        log.debug("Sort direction received: " + sortDir);
        log.debug("Search received: " + search);
        List<Sort.Order> orders = new ArrayList();
        orders.add(new Sort.Order(getSortDirection(sortDir), sortName));
        Pageable page = PageRequest.of(n, s, Sort.by(orders));
        if (!("").equals(search)) {
            log.trace("Search is present, sending to proper method...");
            if (isNumber(search)) {
                log.trace("Search was a number...");
                Integer newSearch = Integer.parseInt(search);
                return repo.findAllByBalance_DollarsOrBalance_CentsOrInterestIsLike(newSearch, newSearch, newSearch, page);
            } else if (GenericValidator.isDate(search, "yyyy-MM", false)) {
                log.trace("Search determined to be in date format...");
                return repo.findByCreateDate(LocalDate.parse(search), page);
            } else {
                log.trace("Generic search...");
                return repo.findAllIgnoreCaseByNicknameOrType_IdOrType_NameOrType_IsActiveAndUser_UserIdIs(search, search, search, Boolean.valueOf(search), search, page);
            }
        } else {
            log.trace("No search parameter, finding all as a page...");
            return repo.findAll(page);
        }
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

    /**
     * This method receives an account id as a String, a String of search
     * criteria, and a Pageable object and returns the requested page of
     * transactions associated with that account and matching that search
     * criteria. If the search term can be parsed as a CurrencyValue object, the
     * return value will be a list of all associated account transactions which
     * match that CurrencyValue. If the search term can be parsed as a
     * LocalDateTime object, the return value will be a list of all associated
     * account transactions with status dates on that date. If the search field
     * is anything else, it will return a list of associated account
     * transactions which match the search criteria in one or more of the
     * following fields: notes, status name, source id, and target id.
     *
     * @param id     String the account id of the associated account
     * @param search String the value to search for
     * @param page   Pageable an object representing the page request
     * @return Page the requested page
     */
    public Page<AccountTransaction> getAllAccountTransactionsByUserId(String id, String search, Pageable page) {
        log.trace("Get all transactions pagination service...");
        Page<AccountTransaction> returnValue = null;

        if (search == null) {
            log.trace("Search term not found, discarding from page...");
            returnValue = transactionRepository.findAllBySource_IdOrTarget_IdIs(id, id, page);
        } else {
            if (GenericValidator.isDate(search, "yyyy-MM-dd", true)) {
                log.info("Searching and filtering account transaction request as a timestamp");
                try {
                    LocalDateTime startDate = LocalDateTime.parse(search + "T00:00:00");
                    LocalDateTime endDate = startDate.plusDays(1);
                    returnValue = transactionRepository.findAllByStatusTimeBetween(startDate, endDate, page);
                } catch (IllegalArgumentException e) {
                    log.error(e.getMessage());
                }
            } else if (isNumber(search)) {
                log.info("Searching and filtering account transaction request as a number");
                try {
                    returnValue = transactionRepository.findAllByTransactionAmountEquals(CurrencyValue.valueOf(Double.parseDouble(search)),
                            page);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            } else {
                log.info("Searching and filtering account transaction request as a string");
                try {
                    returnValue
                            = transactionRepository.findAllByTransactionStatus_StatusNameOrSource_IdOrTarget_IdEqualsOrNotesContainsIgnoreCase(search, search, search, search,
                            page);
                } catch (IllegalArgumentException e) {
                    log.error(e.getMessage());
                }

            }
        }
        log.trace("Account page retrieved, returning...");
        return returnValue;
    }

    public CurrencyValue makePayment(CurrencyValue c, String id) {
        try {
            AccountEntity a = repo.findById(id).get();
            c.setNegative(true);
            a.getBalance().add(c);
            repo.save(a);
            return a.getBalance();
        } catch (Exception e) {
            System.out.println("Error trying to find account: " + e);
            return null;
        }
    }
}
