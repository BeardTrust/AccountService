package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

	@Autowired
	private AccountController accountController;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private AccountService accountService;

	@Test
	void testChangeMoney() throws Exception {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		TransferEntity transferEntity = new TransferEntity();
		transferEntity.setAmount(currencyValue);
		String content = (new ObjectMapper()).writeValueAsString(transferEntity);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{id}", "42")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testDeactivateAccount() throws Exception {
		MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/")
				.contentType(MediaType.APPLICATION_JSON);

		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletRequestBuilder requestBuilder = contentTypeResult
				.content(objectMapper.writeValueAsString(new String()));
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetAccountTransactions() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/{id}", "42");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetAllAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.param("pageNum", "foo")
				.param("pageSize", "foo")
				.param("search", "foo")
				.param("sortDir", "foo")
				.param("sortName", "foo");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetListAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("id", "foo");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetNewAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/new");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetSpecificAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", "42");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testRemoveAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", "42");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}

