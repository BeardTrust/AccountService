/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.dtos.FinancialTransactionDTO;
import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.NewAccountTypeRequestModel;
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
import static org.apache.commons.validator.GenericValidator.isDouble;

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

    /**
     Gets all accounts as a Pageable object that sorts and filters based on params passed in.

     @param n the page number for the Pageable object
     @param s the page size for the Pageable object
     @param sortBy the array containing sort orders for the Pageable object
     @param search the String used for filtering the Pageable object
     @param id  the userId to filter by

     @return Page</AccountEntity> the accounts being returned.
     */
    public Page<AccountEntity> getAllMyAccountsPage(int n, int s, String[] sortBy, String search, String id) {
        List<Sort.Order> orders = parseOrders(sortBy);
        Pageable page = PageRequest.of(n, s, Sort.by(orders));
        if (!("").equals(search)) {
            if (isNumber(search)) {
                Integer newSearch = Integer.parseInt(search);
                return repo.findAllByUser_UserIdAndInterestOrBalance_DollarsOrBalance_Cents(id, newSearch, newSearch, newSearch, page);
            }
            if (GenericValidator.isDate(search, "yyyy-MM", false)) {
                return repo.findAllByUser_UserIdAndCreateDate(id, LocalDate.parse(search), page);
            } else {
                return repo.findByUser_UserIdAndNicknameContainingOrUser_UserIdAndType_IdOrUser_UserIdAndType_NameContainingOrUser_UserIdAndType_IsActiveOrUser_UserIdAndIdAllIgnoreCase(id, search, id, search, id, search, id, Boolean.valueOf(search), id, search, page);
            }
        }
        return repo.findAllByUser_UserId(id, page);
    }

    /**
     This accepts the sort direction in the form of a string and converts it to a proper Sort.Direction for use with parseOrders()

     @param direction the sort direction, in the form of a String

     @return Sort.Direction the properly parsed direction
      * */
    private Sort.Direction getSortDirection(String direction) {
        Sort.Direction returnValue = Sort.Direction.ASC;

        if (direction.equals("desc")) {
            returnValue = Sort.Direction.DESC;
        }

        return returnValue;
    }

    /**
     Sorting orders are sent in an array to be broken down and pieced back together into a Pageable Sort.Order list
     This allows for multiple fields to be sorted by at once It works in tandem with getSortDirection() to ensure proper oders

     @param sortBy the array containing the order(s)

     @return List</Sort.Order> The orders to put into a Pageable
      * */
    private List<Sort.Order> parseOrders(String[] sortBy) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortBy.length > 2) {
            for (int i = 0; i < sortBy.length; i++) {
                String sort = sortBy[i];
                String dir = sortBy[i + 1];
                orders.add(new Sort.Order(getSortDirection(dir), sort));
                i++;
            }
            System.out.println("parsed orders: " + orders);
        }  else {
            orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
        }

        return orders;
    }

    /**
     Accepts an id to use for retrieval of an individual account

     @param id the account id to retrieve

     @return AccountEntity the account found by the database
     */
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

    /**
     Gets all accounts as a Pageable object that sorts and filters based on params passed in.

     @param n the page number for the Pageable object
     @param s the page size for the Pageable object
     @param sortBy the array containing sort orders for the Pageable object
     @param search the String used for filtering the Pageable object

     @return Page</LoanTypeEntity> the  loan types being returned.
     */
    public Page<AccountEntity> getAllService( /*Pageable page*/Integer n, Integer s, String[] sortBy, String search) {
        log.trace("Get all service...");
        log.debug("Page number received: " + n);
        log.debug("Page size received: " + s);
        log.debug("Search received: " + search);
        List<Sort.Order> orders = parseOrders(sortBy);
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
                return repo.findAllIgnoreCaseByNicknameContainingOrType_IdOrType_NameContainingOrType_IsActiveAndUser_UserIdIs(search, search, search, Boolean.valueOf(search), search, page);
            }
        } else {
            log.trace("No search parameter, finding all as a page...");
            return repo.findAll(page);
        }
    }

    /**
     The method for despositing and withdrawing funds to/from an account.
     It accepts a CurrencyValue of amount and a String of id targeting
     the account to be worked on.

     @param id the account being targeted
     @param amount the amount to change

     @return AccountEntity The updated account
     */
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

    /**
     Changes the account to recovery status when something improper happens
     (such as closing an account with a balance)

     @param id the account to be recovered

     @return AccountEntity the account being recovered
     */
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

    /**
     /the method used for updating an account. It receives a model to save over the account with
     and returns the updated account

     @param a The UpdateAccountRequest model to update with

     @return AccountEntity The updated account
     */
    @Transactional
    public AccountEntity updateService(UpdateAccountRequest a) {
        log.trace("Update service reached...");
        log.debug("Service received: " + a);
        String id = a.getId() != null ? a.getId() : UUID.randomUUID().toString();
        Optional<AccountEntity> account = repo.findById(id);
        AccountTypeEntity type = accountTypeRepository.findByNameIs(a.getType().getName());
        type.setDescription(a.getType().getDescription());
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

    /**
     Disables the activeStatus of an account

     @param a the id of the account to disable

     @return String a success or failure message
     */
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

    /**
     Completely removes the entity from the database
     Should be used ONLY when a NOSQL backup is in place

     @param id the id of the account to delete

     @return String a success or failure message
     */
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
     Removes the received amount from the account in question

     @param id the account to work on
     @param c the amount if the payment

     @return CurrencyValue The resulting payment
     */
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
