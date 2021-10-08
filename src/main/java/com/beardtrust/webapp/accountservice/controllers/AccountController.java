/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTransaction;
import com.beardtrust.webapp.accountservice.entities.FinancialTransaction;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;

import java.net.http.HttpResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
public class AccountController {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private AccountService as;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody NewAccountRequestModel a) {
        log.trace("Create account endpoint reached...");
        log.debug("Endpoint received: " + a);

        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.createService(a), HttpStatus.ACCEPTED);
        return null;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/new")
    public ResponseEntity<AccountEntity> getNewAccount() {
        log.trace("Get new endpoint reached...");
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getNewAccountService(), HttpStatus.OK);
        log.info("Outbound entity: " + response);
        return response;
    }

    @PreAuthorize("hasAuthority('admin')")
    //@PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<Page<AccountEntity>> getAllAccount(/*Pageable page*/@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String sortName, @RequestParam String sortDir, @RequestParam String search) {//<-- Admin calls full list
        log.trace("Get all accounts admin endpoint reached...");
        log.debug("Page number received: " + n);
        log.debug("Page size received: " + s);
        log.debug("Sort name received: " + sortName);
        log.debug("Sort direction received: " + sirtDir);
        log.debug("Search received: " + search);
        ResponseEntity<Page<AccountEntity>> response = new ResponseEntity<>(as.getAllService(Integer.parseInt(pageNum), Integer.parseInt(pageSize), sortName, sortDir, search), HttpStatus.OK);
        log.debug("controller returning: " + response);
        return response;
    }

    @GetMapping("/{id}")//<-- Account Id goes here
    public ResponseEntity<AccountEntity> getSpecificAccount(@PathVariable String id) {
        log.trace("Get specific account endpoint reached...");
        log.debug("Endpoint received: " + id);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getSpecificService(id), HttpStatus.OK);
        log.trace("Controller returning: " + response);
        return response;
    }

    @PreAuthorize("hasAuthority('admin') or principal == #id")
    @GetMapping
    public ResponseEntity<List<AccountEntity>> getListAccount(@RequestParam("id") String id) {
        log.trace("Get account list endpoint reached...");
        log.debug("Endpoint received: " + id);
        ResponseEntity<List<AccountEntity>> response = null;

        List<AccountEntity> accounts = as.getListService(id);
        response = new ResponseEntity<>(accounts, HttpStatus.OK);
        log.trace("Controller returning: " + response);
        return response;
    }

    @PreAuthorize("hasAuthority('admin') or principal == #id")
    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> changeMoney(@PathVariable String id, @RequestBody TransferEntity amount) {//<-- The Account ID (amount should be set pos/neg by the front end)
        log.trace("Change money endpoint reached...");
        log.debug("Endpoint received Id: " + id);
        log.debug("Endpoint received amount: " + amount);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.changeMoneyService(amount, id), HttpStatus.NO_CONTENT);
        log.trace("Controller returning: " + response);
        return response;
    }

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

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody UpdateAccountRequest a) {//<-- The entity with new/updated info
        log.trace("Update account endpoint reached...");
        log.debug("Endpoint received: " + a);
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.updateService(a), HttpStatus.OK);
        log.trace("Controller returning: " + response);
        return response;
    }

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

    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAccount(@PathVariable String id, @RequestParam String userId) {//<-- Send the Account Id that we want deactivated
        log.trace("Remove account endpoint reached...");
        log.debug("Endpoint received account Id: " + a);
        log.debug("Endpoint received user Id: " + userId);
        System.out.println("incomming delete request");
        ResponseEntity<String> response = new ResponseEntity<>(as.removeAccount(id), HttpStatus.NO_CONTENT);
        log.debug("Controller returning: " + response);
        return response;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<Page<AccountTransaction>> getAccountTransactions(@PathVariable(name = "id") String id, @RequestParam(name = "search", required = false) String search, Pageable page) {
        log.trace("Get account transactions endpoint reached...");
        log.debug("Endpoint received Id: " + id);
        log.debug("Endpoint received search: " + search);
        log.debug("Endpoint received page: " + page);
        Page<AccountTransaction> newPage = as.getAllAccountTransactionsByUserId(id, search, page);
        ResponseEntity<Page<AccountEntity>> response = new ResponseEntity<>(newPage, HttpStatus.OK);
        log.debug("Controller returning: " + response);
        return response;
    }

}
