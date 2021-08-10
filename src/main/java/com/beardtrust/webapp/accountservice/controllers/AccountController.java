/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;
import java.util.List;
import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AccountService as = new AccountService(repo);

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity a) {
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.createService(a), HttpStatus.ACCEPTED);
         return response;
    }

    @GetMapping("/{id}")//<-- Account Id goes here
    public ResponseEntity<AccountEntity> getSpecificAccount(@PathVariable String id) {
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.getSpecificService(id), HttpStatus.OK);
         return response;
    }

    //@PreAuthorize("hasAuthority('admin') or principal == #id")
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<AccountEntity>> getListAccount(@RequestParam("id") String id) {//<-- The User's ID
        ResponseEntity<List<AccountEntity>> response = new ResponseEntity<>(as.getAllService(id), HttpStatus.OK);
         return response;
    }
    
    //@PreAuthorize("hasAuthority('admin') or principal == #id")
    @PreAuthorize("permitAll()")
    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> changeMoney(@PathVariable String id, @RequestBody TransferEntity amount) {//<-- The Account ID (amount should be set pos/neg by the front end)
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.changeMoneyService(amount, id), HttpStatus.NO_CONTENT);
         return response;
    }
    
    //@PreAuthorize("hasAuthority('admin') or principal == #id")
    @PreAuthorize("permitAll()")
    @PutMapping("/recovery/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEntity> recoverAccount(@PathVariable String id, @RequestBody AccountEntity a) {//<-- The Account ID (amount should be set pos/neg by the front end)
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.changeRecoveryService(id), HttpStatus.NO_CONTENT);
         return response;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity a) {//<-- The entity with new/updated info
        ResponseEntity<AccountEntity> response = new ResponseEntity<>(as.updateService(a), HttpStatus.OK);
         return response;
    }

    //@PreAuthorize("hasAuthority('admin') or principal == #id")
    @PreAuthorize("permitAll()")
    @DeleteMapping
    public ResponseEntity<String> deactivateAccount(@RequestBody String a) {//<-- Send the Account Id that we want deactivated
        ResponseEntity<String> response = new ResponseEntity<>(as.deactivateAccount(a), HttpStatus.NO_CONTENT);
        return response;
    }

}
