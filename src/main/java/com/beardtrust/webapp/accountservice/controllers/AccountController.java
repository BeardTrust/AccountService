/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public AccountEntity createAccount(@RequestBody AccountEntity a) {
        return as.createService(a);
    }

    @GetMapping("/{id}")//<-- Account Id goes here
    public AccountEntity getSpecificAccount(@PathVariable String id) {
        return as.getSpecificService(id);
    }

    @GetMapping()
    public List<AccountEntity> getListAccount(@RequestBody AccountEntity id) {//<-- The User's ID
        return as.getAllService(id.getUserId());
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping
    public AccountEntity updateAccount(@RequestBody AccountEntity a) {//<-- The entity with new/updated info
        return as.updateService(a);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping
    public String deactivateAccount(@RequestBody AccountEntity a) {//<-- Send the Account that we want deactivated
        return as.deactivateAccount(a);
    }

}
