package com.beardtrust.webapp.accountservice.controllers;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
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
	public void testChangeMoney() throws Exception {
		TransferEntity transferEntity = new TransferEntity();
		transferEntity.setAmount(10);
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
	public void testCreateAccount() throws Exception {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreateDate(null);
		accountEntity.setType("Type");
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(1);
		String content = (new ObjectMapper()).writeValueAsString(accountEntity);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testDeactivateAccount() throws Exception {
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
	public void testGetListAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("id", "foo");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testGetSpecificAccount() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", "42");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testRecoverAccount() throws Exception {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreateDate(null);
		accountEntity.setType("Type");
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(1);
		String content = (new ObjectMapper()).writeValueAsString(accountEntity);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/recovery/{id}", "42")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testUpdateAccount() throws Exception {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreateDate(null);
		accountEntity.setType("Type");
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(1);
		String content = (new ObjectMapper()).writeValueAsString(accountEntity);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.accountController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}

