package com.beardtrust.webapp.accountservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
@Slf4j
public class AccountController {

	@GetMapping
	private String test(){
		return "Working";
	}
}
