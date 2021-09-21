/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.dtos.AccountDTO;
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
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody NewAccountRequestModel a) {
        log.info("controller inbound account: " + a.toString());

        return new ResponseEntity<>(as.createService(a), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/new")
    public ResponseEntity<AccountEntity> getNewAccount() {
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getNewAccountService(), HttpStatus.OK);
        log.info("Outbound entity: " + response);
        return response;
    }

    @PreAuthorize("hasAuthority('admin')")
    //@PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<Page<AccountEntity>> getAllAccount(/*Pageable page*/@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String sortName, @RequestParam String sortDir, @RequestParam String search) {//<-- Admin calls full list
        return new ResponseEntity<>(as.getAllService(Integer.parseInt(pageNum), Integer.parseInt(pageSize), sortName, sortDir, search), HttpStatus.OK);
    }

    @GetMapping("/{id}")//<-- Account Id goes here
    public ResponseEntity<AccountEntity> getSpecificAccount(@PathVariable String id) {
        return new ResponseEntity<>(as.getSpecificService(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin') or principal == #id")
    //@PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<AccountEntity>> getListAccount(@RequestParam("id") String id) {
        ResponseEntity<List<AccountEntity>> response = null;

        List<AccountEntity> accounts = as.getListService(id);
        response = new ResponseEntity<>(accounts, HttpStatus.OK);

        return response;
    }

    @PreAuthorize("hasAuthority('admin') or principal == #id")
    //@PreAuthorize("permitAll()")
    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> changeMoney(@PathVariable String id, @RequestBody TransferEntity amount) {//<-- The Account ID (amount should be set pos/neg by the front end)
        return new ResponseEntity<>(as.changeMoneyService(amount, id), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    //@PreAuthorize("permitAll()")
    @PutMapping("/recovery/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> recoverAccount(@PathVariable String id, @RequestBody AccountDTO a, @RequestParam String userId) {//<-- The Account ID (amount should be set pos/neg by the front end)
        return new ResponseEntity<>(as.changeRecoveryService(id), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('admin')")
    //@PreAuthorize("permitAll()")
    @PutMapping
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody UpdateAccountRequest a) {//<-- The entity with new/updated info
        return new ResponseEntity<>(as.updateService(a), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    //@PreAuthorize("permitAll()")
    @DeleteMapping
    public ResponseEntity<String> deactivateAccount(@RequestBody String a, @RequestParam String userId) {//<-- Send the Account Id that we want deactivated, and userId for security
        return new ResponseEntity<>(as.deactivateAccount(a), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    //@PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAccount(@PathVariable String id, @RequestParam String userId) {//<-- Send the Account Id that we want deactivated
        log.info("incomming delete request");
        return new ResponseEntity<>(as.removeAccount(id), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<Page<AccountTransaction>> getAccountTransactions(@PathVariable(name = "id") String id,
            @RequestParam(name = "search", required
                    = false) String search,
            Pageable page) {
        Page<AccountTransaction> newPage = as.getAllAccountTransactionsByUserId(id, search, page);
        return new ResponseEntity<>(newPage, HttpStatus.OK);
    }

}
