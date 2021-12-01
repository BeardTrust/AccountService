/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;

import java.util.List;
import javax.ws.rs.Consumes;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@RestController
@RequestMapping(path = "/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private AccountService as;

    /**
     * this method accepts an HTTP POST request on the /accounts
     * endpoint and returns a newly created bank account
     * It accepts a model to build the account from, which was assembled by the front end.
     *
     * @param a the account model to build the account with
     *
     * @return  ResponseEntity</AccountEntity> The newly built account
     */
    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody NewAccountRequestModel a) {
        log.trace("Create account endpoint reached...");
        log.debug("Endpoint received: " + a);

        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.createService(a), HttpStatus.ACCEPTED);
        return null;
    }

    /**
     * this method accepts an HTTP POST request on the /accounts/{userId}/{id}
     * endpoint and returns a CurrencyValue Object from the resulting payment
     * It accepts the id of the account to pay on and the CurrencyValue of the payment
     *
     * @param id the account being paid from
     * @param userId the user paying
     * @param c the payment
     *
     * @return  ResponseEntity</CurrencyValue> The payment result
     */
    @PreAuthorize("hasRole('admin') or principal == #userId")
    @PostMapping("/{userId}/{id}")//<-- Account to be paid on
    public ResponseEntity<CurrencyValue> changeMoneyAccount(@PathVariable String id, @PathVariable String userId, @RequestBody CurrencyValue c) {
        log.trace("Change money endpoint reached...");
        log.debug("Change money received account id: " + id + ", userId: " + userId + ", amount: " + c.toString());
        ResponseEntity<CurrencyValue> response = new ResponseEntity<>(as.makePayment(c, id), HttpStatus.ACCEPTED);
        log.trace("End of change money endpoint...");
        return response;

    }

    /**
     * this method accepts an HTTP GET request on the /accounts/new
     * endpoint and returns an AccountEntity for the front end to build on
     * It accepts no parameters as it is the initiator of the account creation process
     *
     * @return  ResponseEntity</AccountEntity> The new account
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/new")
    public ResponseEntity<AccountEntity> getNewAccount() {
        log.trace("Get new endpoint reached...");
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.createNewAccountService(), HttpStatus.OK);
        log.debug("Get new Account outbound entity: " + response);
        log.trace("End of get new account endpoint...");
        return response;
    }

    /**
     * this method accepts an HTTP POST request on the /accounts/new
     * endpoint and returns an AccountEntity for the front end to build on
     * It accepts the userId of the user requesting the account
     *
     * @param userId the user to attatch to the account
     *
     * @return  ResponseEntity</AccountEntity> The new account
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/new")
    public ResponseEntity<AccountEntity> getNewAccount(@RequestBody String userId) {
        log.trace("Get new POST endpoint reached...");
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getNewAccountService(userId), HttpStatus.OK);
        log.info("Outbound entity: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP GET request on the /accounts/all
     * endpoint and returns a Pageable of all accounts applicable
     * to the sorting and filtering included in the request
     * This is an admin-level method intended to find ALL
     * accounts in the database.
     *
     * @param pageNum The page number for the Pageable Object
     * @param pageSize The page size for the Pageable Object
     * @param search The search string for the Pageable Object
     * @param sortBy The sort order for the Pageable Object
     *
     * @return a ResponseEntity<Page</AccountEntity>> The accounts found by the given criteria
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/all")
    public ResponseEntity<Page<AccountEntity>> getAllAccount(/*Pageable page*/@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required = false, defaultValue = "id,asc") String[] sortBy, @RequestParam(required = false, defaultValue = "") String search) {//<-- Admin calls full list
        log.trace("Get all accounts admin endpoint reached...");
        log.debug("Page number received: " + pageNum);
        log.debug("Page size received: " + pageSize);
        log.debug("Search received: " + search);
        ResponseEntity<Page<AccountEntity>> response = new ResponseEntity<>(as.getAllService(Integer.parseInt(pageNum), Integer.parseInt(pageSize), sortBy, search), HttpStatus.OK);
        log.debug("controller returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP GET request on the /accounts/{id}
     * endpoint and returns an AccountEntity based on the id submitted
     *
     * @param id The account to be retrieved
     *
     * @return a ResponseEntity</AccountEntity> The accounts found by the given criteria
     */
    @GetMapping("/{id}")//<-- Account Id goes here
    public ResponseEntity<AccountEntity> getSpecificAccount(@PathVariable String id) {
        log.trace("Get specific account endpoint reached...");
        log.debug("Endpoint received: " + id);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getSpecificService(id), HttpStatus.OK);
        log.trace("Controller returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP GET request on the /accounts
     * endpoint and returns a Pageable of all accounts applicable
     * to the sorting and filtering included in the request
     *
     * @param pageNum The page number for the Pageable Object
     * @param pageSize The page size for the Pageable Object
     * @param search The search string for the Pageable Object
     * @param sortBy The sort order for the Pageable Object
     * @param userId the userId to be filtered by
     *
     * @return a ResponseEntity<Page</AccountEntity>> The accounts found by the given criteria
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @GetMapping
    public ResponseEntity<Page<AccountEntity>> getAllMyAccountsPage(// <-- User calls personal list@RequestParam(name = "page", defaultValue = "0") int pageNum,
         @RequestParam(name = "page", defaultValue = "0") int pageNum,
         @RequestParam(name = "size", defaultValue = "10") int pageSize,
         @RequestParam(name = "sortBy", defaultValue = "id,asc") String[] sortBy,
         @RequestParam(name = "search", defaultValue = "") String search,
         @RequestParam(name = "userId", defaultValue = "") String userId) {
        log.trace("Get My Accounts endpoint reached...");
        log.debug("Get My Accounts received pageNum: " + pageNum + ". pageSize: " + pageSize + ". sortBy: " + sortBy + ". search: " + search + ". userId: " + userId);
        ResponseEntity<Page<AccountEntity>> response = new ResponseEntity<>(as.getAllMyAccountsPage(pageNum, pageSize, sortBy, search, userId), HttpStatus.OK);
        log.trace("End of Get My Accounts endpoint...");
        log.debug("Get My Accounts returning: " + response);
        return response;

    }

    /**
     * This method accepts an HTTP GET request on the /accounts/me
     * endpoint and returns a List of all accounts for use with
     * the payment features of the front-end
     *
     * @param userId The user requesting their accounts
     *
     * @return a ResponseEntity<List</AccountEntity>> The user's accounts
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @GetMapping("/me")
    public ResponseEntity<List<AccountEntity>> getMyAccountsList(@RequestParam String userId) {
        log.trace("Get My Account List endpoint reached...");
        log.debug("Get My Account List userId received: " + userId);
        ResponseEntity<List<AccountEntity>> response = new ResponseEntity<>(as.getMyAccountsList(userId), HttpStatus.OK);
        log.trace("End of Get My Accounts List...");
        log.debug("Get My Accounts List returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP PUT request on the /accounts/{id}
     * endpoint to increase or decrease the amount of money in an account
     * and returns an updated AccountEntity
     *
     * @param id The account to update
     * @param amount The amount to change by
     *
     * @return a ResponseEntity</AccountEntity> The user's accounts
     */
    @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> changeMoney(@PathVariable String id, @RequestBody CurrencyValue amount) {//<-- The Account ID (amount should be set pos/neg by the front end)
        log.trace("Change Money endpoint reached...");
        log.debug("Change Money endpoint received Id: " + id);
        log.debug("Change Money endpoint received amount: " + amount);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.changeMoneyService(amount, id), HttpStatus.NO_CONTENT);
        log.trace("End of Change Money...");
        log.debug("Change Money returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP PUT request on the /accounts/recovery/{id}
     * endpoint to increase or decrease the amount of money in an account
     * and returns an updated AccountEntity
     *
     * @param id The account id
     * @param a The amount to recover
     * @param userId the user recovering their account
     *
     * @return a ResponseEntity</AccountEntity> The recovered account
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @PutMapping("/recovery/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> recoverAccount(@PathVariable String id, @RequestBody AccountEntity a, @RequestParam String userId) {//<-- The Account ID (amount should be set pos/neg by the front end)
        log.trace("Recover account endpoint reached...");
        log.debug("Endpoint received account Id: " + id);
        log.debug("Endpoint received entity: " + a);
        log.debug("Endpoint received user Id: " + userId);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.changeRecoveryService(id), HttpStatus.NO_CONTENT);
        log.trace("Controller returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP PUT request on the /accounts
     * endpoint to update an account as requested. It accepts
     * an account model to rebuild the updated account from.
     *
     * @param a The account model to update from
     *
     * @return a ResponseEntity</AccountEntity> The user's updated accounts
     */
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody UpdateAccountRequest a) {//<-- The entity with new/updated info
        log.trace("Update account endpoint reached...");
        log.debug("Endpoint received: " + a);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.updateService(a), HttpStatus.OK);
        log.trace("Controller returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP DELETE request on the /accounts
     * endpoint to deactivate an account. This does NOT remove the account
     * from the database.
     *
     * @param a The account to deactivate
     * @param userId the user Id for accessing the method level security
     *
     * @return a ResponseEntity</String> A response message indicating success or failure
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @DeleteMapping
    public ResponseEntity<String> deactivateAccount(@RequestBody String a, @RequestParam String userId) {//<-- Send the Account Id that we want deactivated, and userId for security
        log.trace("Deactivate account endpoint reached...");
        log.debug("Endpoint received account Id: " + a);
        log.debug("Endpoint received user Id: " + userId);
        ResponseEntity<String> response = new ResponseEntity<>(as.deactivateAccount(a), HttpStatus.NO_CONTENT);
        log.debug("Controller returning: " + response);
        return response;
    }

    /**
     * This method accepts an HTTP DELETE request on the /accounts/{id}
     * endpoint to completely delete an account. This will remove
     * the account from the database and should therefore
     * not be used unless there is a NOSQL backup in place
     *
     * @param id The account to remove
     * @param userId the user Id for accessing the method level security
     *
     * @return a ResponseEntity</String> A response message indicating success or failure
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAccount(@PathVariable String id, @RequestParam String userId) {//<-- Send the Account Id that we want deactivated
        log.trace("Remove account endpoint reached...");
        log.debug("Endpoint received account Id: " + id);
        log.debug("Endpoint received user Id: " + userId);
        System.out.println("incomming delete request");
        ResponseEntity<String> response = new ResponseEntity<>(as.removeAccount(id), HttpStatus.NO_CONTENT);
        log.debug("Controller returning: " + response);
        return response;
    }

    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @GetMapping(path = "/{id}/security")
    public String testSecurity(@PathVariable(name = "id")String id){
        return "Working for user with userId of " + id;
    }

}
