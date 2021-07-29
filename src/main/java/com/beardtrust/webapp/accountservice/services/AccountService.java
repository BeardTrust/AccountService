/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
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
        repo.save(a);
        return a;
    }
    
}
