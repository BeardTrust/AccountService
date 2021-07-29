package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        @PreAuthorize(value = "hasAuthority('admin')")
        private AccountEntity createAccount(@RequestBody AccountEntity a) {
            return as.createService(a);
        }
}
