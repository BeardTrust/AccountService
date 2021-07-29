package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.accountservice.models.AccountCreation;
import com.beardtrust.accountservice.models.AccountCreationResponse;
import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
@Slf4j
public class AccountController {
    
    @Autowired
    private AccountService as;

	@GetMapping
	private String test(){
		return "Working";
	}
        
        @PostMapping
        private AccountEntity<AccountCreationResponse> createAccount(@RequestBody AccountCreation a) {
            AccountEntity<AccountCreationResponse> response = null;
            return as.createService(a);
        }
}
