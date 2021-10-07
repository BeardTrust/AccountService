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

class NewAccountRequestModelTest {
	@Test
	void testCanEqual() {
		assertFalse((new NewAccountRequestModel()).canEqual("Other"));
	}

	@Test
	void testCanEqual2() {
		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();

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

		NewAccountRequestModel newAccountRequestModel1 = new NewAccountRequestModel();
		newAccountRequestModel1.setBalance(currencyValue);
		newAccountRequestModel1.setNickname("Nickname");
		newAccountRequestModel1.setUserId("42");
		newAccountRequestModel1.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel1.setInterest(0);
		newAccountRequestModel1.setType(accountTypeEntity);
		newAccountRequestModel1.setActiveStatus(true);
		assertTrue(newAccountRequestModel.canEqual(newAccountRequestModel1));
	}

	@Test
	void testConstructor() {
		NewAccountRequestModel actualNewAccountRequestModel = new NewAccountRequestModel();
		actualNewAccountRequestModel.setActiveStatus(true);
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);
		actualNewAccountRequestModel.setBalance(currencyValue);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualNewAccountRequestModel.setCreateDate(ofEpochDayResult);
		actualNewAccountRequestModel.setInterest(1);
		actualNewAccountRequestModel.setNickname("Nickname");
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		actualNewAccountRequestModel.setType(accountTypeEntity);
		actualNewAccountRequestModel.setUserId("42");
		assertSame(currencyValue, actualNewAccountRequestModel.getBalance());
		assertSame(ofEpochDayResult, actualNewAccountRequestModel.getCreateDate());
		assertEquals(1, actualNewAccountRequestModel.getInterest());
		assertEquals("Nickname", actualNewAccountRequestModel.getNickname());
		assertSame(accountTypeEntity, actualNewAccountRequestModel.getType());
		assertEquals("42", actualNewAccountRequestModel.getUserId());
		assertTrue(actualNewAccountRequestModel.isActiveStatus());
		assertEquals(
				"NewAccountRequestModel(userId=42, balance=-$1.01, createDate=1970-01-02, type=Name: The characteristics"
						+ " of someone or something, nickname=Nickname, interest=1, activeStatus=true)",
				actualNewAccountRequestModel.toString());
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

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity);
		newAccountRequestModel.setActiveStatus(true);
		assertFalse(newAccountRequestModel.equals(null));
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

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity);
		newAccountRequestModel.setActiveStatus(true);
		assertFalse(newAccountRequestModel.equals("Different type to NewAccountRequestModel"));
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

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity);
		newAccountRequestModel.setActiveStatus(true);
		assertTrue(newAccountRequestModel.equals(newAccountRequestModel));
		int expectedHashCodeResult = newAccountRequestModel.hashCode();
		assertEquals(expectedHashCodeResult, newAccountRequestModel.hashCode());
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

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity);
		newAccountRequestModel.setActiveStatus(true);

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

		NewAccountRequestModel newAccountRequestModel1 = new NewAccountRequestModel();
		newAccountRequestModel1.setBalance(currencyValue1);
		newAccountRequestModel1.setNickname("Nickname");
		newAccountRequestModel1.setUserId("42");
		newAccountRequestModel1.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel1.setInterest(1);
		newAccountRequestModel1.setType(accountTypeEntity1);
		newAccountRequestModel1.setActiveStatus(true);
		assertTrue(newAccountRequestModel.equals(newAccountRequestModel1));
		int expectedHashCodeResult = newAccountRequestModel.hashCode();
		assertEquals(expectedHashCodeResult, newAccountRequestModel1.hashCode());
	}
}

