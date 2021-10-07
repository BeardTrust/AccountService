package com.beardtrust.webapp.accountservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import org.junit.jupiter.api.Test;

public class AccountCreationTest {

	@Test
	void testCanEqual() {
		assertFalse((new AccountCreation()).canEqual("Other"));
	}

	@Test
	void testCanEqual2() {
		AccountCreation accountCreation = new AccountCreation();

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setBalance(currencyValue);
		accountCreation1.setInterest(0);
		accountCreation1.setUserId("42");
		accountCreation1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setType("Type");
		accountCreation1.setActiveStatus("Active Status");
		assertTrue(accountCreation.canEqual(accountCreation1));
	}

	@Test
	void testConstructor() {
		AccountCreation actualAccountCreation = new AccountCreation();
		actualAccountCreation.setAccountId("42");
		actualAccountCreation.setActiveStatus("Active Status");
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);
		actualAccountCreation.setBalance(currencyValue);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualAccountCreation.setCreateDate(ofEpochDayResult);
		actualAccountCreation.setInterest(1);
		actualAccountCreation.setNicknsme("Nicknsme");
		actualAccountCreation.setType("Type");
		actualAccountCreation.setUserId("42");
		assertEquals("42", actualAccountCreation.getAccountId());
		assertEquals("Active Status", actualAccountCreation.getActiveStatus());
		assertSame(currencyValue, actualAccountCreation.getBalance());
		assertSame(ofEpochDayResult, actualAccountCreation.getCreateDate());
		assertEquals(1, actualAccountCreation.getInterest().intValue());
		assertEquals("Nicknsme", actualAccountCreation.getNicknsme());
		assertEquals("Type", actualAccountCreation.getType());
		assertEquals("42", actualAccountCreation.getUserId());
		assertEquals(
				"AccountCreation(accountId=42, userId=42, activeStatus=Active Status, balance=-$1.01, createDate=1970-01-02,"
						+ " interest=1, nicknsme=Nicknsme, type=Type)",
				actualAccountCreation.toString());
	}

	@Test
	void testEquals() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setBalance(currencyValue);
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setType("Type");
		accountCreation.setActiveStatus("Active Status");
		assertFalse(accountCreation.equals(null));
	}

	@Test
	void testEquals2() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setBalance(currencyValue);
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setType("Type");
		accountCreation.setActiveStatus("Active Status");
		assertFalse(accountCreation.equals("Different type to AccountCreation"));
	}

	@Test
	void testEquals3() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setBalance(currencyValue);
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setType("Type");
		accountCreation.setActiveStatus("Active Status");
		assertTrue(accountCreation.equals(accountCreation));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation.hashCode());
	}

	@Test
	void testEquals4() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setBalance(currencyValue);
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setType("Type");
		accountCreation.setActiveStatus("Active Status");

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setBalance(currencyValue1);
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setType("Type");
		accountCreation1.setActiveStatus("Active Status");
		assertTrue(accountCreation.equals(accountCreation1));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation1.hashCode());
	}

}

