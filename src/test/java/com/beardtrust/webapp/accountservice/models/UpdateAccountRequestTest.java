package com.beardtrust.webapp.accountservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class UpdateAccountRequestTest {
	@Test
	void testCanEqual() {
		assertFalse((new UpdateAccountRequest()).canEqual("Other"));
	}

	@Test
	void testCanEqual2() {
		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest1 = new UpdateAccountRequest();
		updateAccountRequest1.setBalance(currencyValue);
		updateAccountRequest1.setNickname("Nickname");
		updateAccountRequest1.setUserId("42");
		updateAccountRequest1.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest1.setId("42");
		updateAccountRequest1.setInterest(0);
		updateAccountRequest1.setType(accountTypeEntity);
		updateAccountRequest1.setActiveStatus(true);
		assertTrue(updateAccountRequest.canEqual(updateAccountRequest1));
	}

	@Test
	void testConstructor() {
		UpdateAccountRequest actualUpdateAccountRequest = new UpdateAccountRequest();
		actualUpdateAccountRequest.setActiveStatus(true);
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);
		actualUpdateAccountRequest.setBalance(currencyValue);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualUpdateAccountRequest.setCreateDate(ofEpochDayResult);
		actualUpdateAccountRequest.setId("42");
		actualUpdateAccountRequest.setInterest(1);
		actualUpdateAccountRequest.setNickname("Nickname");
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		actualUpdateAccountRequest.setType(accountTypeEntity);
		actualUpdateAccountRequest.setUserId("42");
		assertSame(currencyValue, actualUpdateAccountRequest.getBalance());
		assertSame(ofEpochDayResult, actualUpdateAccountRequest.getCreateDate());
		assertEquals("42", actualUpdateAccountRequest.getId());
		assertEquals(1, actualUpdateAccountRequest.getInterest());
		assertEquals("Nickname", actualUpdateAccountRequest.getNickname());
		assertSame(accountTypeEntity, actualUpdateAccountRequest.getType());
		assertEquals("42", actualUpdateAccountRequest.getUserId());
		assertTrue(actualUpdateAccountRequest.isActiveStatus());
		assertEquals(
				"UpdateAccountRequest(userId=42, id=42, balance=-$1.01, createDate=1970-01-02, type=Name: The characteristics"
						+ " of someone or something, nickname=Nickname, interest=1, activeStatus=true)",
				actualUpdateAccountRequest.toString());
	}

	@Test
	void testEquals() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
		updateAccountRequest.setBalance(currencyValue);
		updateAccountRequest.setNickname("Nickname");
		updateAccountRequest.setUserId("42");
		updateAccountRequest.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest.setId("42");
		updateAccountRequest.setInterest(1);
		updateAccountRequest.setType(accountTypeEntity);
		updateAccountRequest.setActiveStatus(true);
		assertFalse(updateAccountRequest.equals(null));
	}

	@Test
	void testEquals2() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
		updateAccountRequest.setBalance(currencyValue);
		updateAccountRequest.setNickname("Nickname");
		updateAccountRequest.setUserId("42");
		updateAccountRequest.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest.setId("42");
		updateAccountRequest.setInterest(1);
		updateAccountRequest.setType(accountTypeEntity);
		updateAccountRequest.setActiveStatus(true);
		assertFalse(updateAccountRequest.equals("Different type to UpdateAccountRequest"));
	}

	@Test
	void testEquals3() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
		updateAccountRequest.setBalance(currencyValue);
		updateAccountRequest.setNickname("Nickname");
		updateAccountRequest.setUserId("42");
		updateAccountRequest.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest.setId("42");
		updateAccountRequest.setInterest(1);
		updateAccountRequest.setType(accountTypeEntity);
		updateAccountRequest.setActiveStatus(true);
		assertTrue(updateAccountRequest.equals(updateAccountRequest));
		int expectedHashCodeResult = updateAccountRequest.hashCode();
		assertEquals(expectedHashCodeResult, updateAccountRequest.hashCode());
	}

	@Test
	void testEquals4() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
		updateAccountRequest.setBalance(currencyValue);
		updateAccountRequest.setNickname("Nickname");
		updateAccountRequest.setUserId("42");
		updateAccountRequest.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest.setId("42");
		updateAccountRequest.setInterest(1);
		updateAccountRequest.setType(accountTypeEntity);
		updateAccountRequest.setActiveStatus(true);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest1 = new UpdateAccountRequest();
		updateAccountRequest1.setBalance(currencyValue1);
		updateAccountRequest1.setNickname("Nickname");
		updateAccountRequest1.setUserId("42");
		updateAccountRequest1.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest1.setId("42");
		updateAccountRequest1.setInterest(1);
		updateAccountRequest1.setType(accountTypeEntity1);
		updateAccountRequest1.setActiveStatus(true);
		assertTrue(updateAccountRequest.equals(updateAccountRequest1));
		int expectedHashCodeResult = updateAccountRequest.hashCode();
		assertEquals(expectedHashCodeResult, updateAccountRequest1.hashCode());
	}
}

