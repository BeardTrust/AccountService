package com.beardtrust.webapp.accountservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AccountCreationTest {
	@Test
	public void testCanEqual() {
		assertFalse((new AccountCreation()).canEqual("Other"));
	}

	@Test
	public void testCanEqual2() {
		AccountCreation accountCreation = new AccountCreation();

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(0);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertTrue(accountCreation.canEqual(accountCreation1));
	}

	@Test
	public void testConstructor() {
		AccountCreation actualAccountCreation = new AccountCreation();
		actualAccountCreation.setAccountId("42");
		actualAccountCreation.setActive_status("Active status");
		actualAccountCreation.setBalance("Balance");
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualAccountCreation.setCreate_date(ofEpochDayResult);
		actualAccountCreation.setInterest(1);
		actualAccountCreation.setNicknsme("Nicknsme");
		actualAccountCreation.setType("Type");
		actualAccountCreation.setUserId("42");
		assertEquals("42", actualAccountCreation.getAccountId());
		assertEquals("Active status", actualAccountCreation.getActive_status());
		assertEquals("Balance", actualAccountCreation.getBalance());
		assertSame(ofEpochDayResult, actualAccountCreation.getCreate_date());
		assertEquals(1, actualAccountCreation.getInterest().intValue());
		assertEquals("Nicknsme", actualAccountCreation.getNicknsme());
		assertEquals("Type", actualAccountCreation.getType());
		assertEquals("42", actualAccountCreation.getUserId());
		assertEquals("AccountCreation(accountId=42, userId=42, active_status=Active status, balance=Balance, create_date"
				+ "=1970-01-02, interest=1, nicknsme=Nicknsme, type=Type)", actualAccountCreation.toString());
	}

	@Test
	public void testEquals() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");
		assertFalse(accountCreation.equals(null));
	}

	@Test
	public void testEquals10() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme(null);
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals11() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId(null);
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals12() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("Active status");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals13() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("42");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals14() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance(null);
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals15() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(0L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals16() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(null);
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals17() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("42");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals18() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType(null);
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals19() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("42");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals2() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");
		assertFalse(accountCreation.equals("Different type to AccountCreation"));
	}

	@Test
	public void testEquals20() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status(null);

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals21() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(null);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(null);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertTrue(accountCreation.equals(accountCreation1));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation1.hashCode());
	}

	@Test
	public void testEquals22() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId(null);
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId(null);
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertTrue(accountCreation.equals(accountCreation1));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation1.hashCode());
	}

	@Test
	public void testEquals23() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme(null);
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme(null);
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertTrue(accountCreation.equals(accountCreation1));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation1.hashCode());
	}

	@Test
	public void testEquals3() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");
		assertTrue(accountCreation.equals(accountCreation));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation.hashCode());
	}

	@Test
	public void testEquals4() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertTrue(accountCreation.equals(accountCreation1));
		int expectedHashCodeResult = accountCreation.hashCode();
		assertEquals(expectedHashCodeResult, accountCreation1.hashCode());
	}

	@Test
	public void testEquals5() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(0);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals6() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(null);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals7() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId(null);
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals8() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("Active status");
		accountCreation.setNicknsme("Nicknsme");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}

	@Test
	public void testEquals9() {
		AccountCreation accountCreation = new AccountCreation();
		accountCreation.setInterest(1);
		accountCreation.setUserId("42");
		accountCreation.setNicknsme("42");
		accountCreation.setAccountId("42");
		accountCreation.setBalance("Balance");
		accountCreation.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation.setType("Type");
		accountCreation.setActive_status("Active status");

		AccountCreation accountCreation1 = new AccountCreation();
		accountCreation1.setInterest(1);
		accountCreation1.setUserId("42");
		accountCreation1.setNicknsme("Nicknsme");
		accountCreation1.setAccountId("42");
		accountCreation1.setBalance("Balance");
		accountCreation1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountCreation1.setType("Type");
		accountCreation1.setActive_status("Active status");
		assertFalse(accountCreation.equals(accountCreation1));
	}
}

