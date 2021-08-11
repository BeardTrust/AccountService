package com.beardtrust.webapp.accountservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AccountEntityTest {
	@Test
	public void testConstructor() {
		AccountEntity actualAccountEntity = new AccountEntity();
		actualAccountEntity.setAccountId("42");
		actualAccountEntity.setActive_status(true);
		actualAccountEntity.setBalance(1);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualAccountEntity.setCreate_date(ofEpochDayResult);
		actualAccountEntity.setInterest(1);
		actualAccountEntity.setNickname("Nickname");
		actualAccountEntity.setType("Type");
		actualAccountEntity.setUserId("42");
		assertEquals("42", actualAccountEntity.getAccountId());
		assertEquals(1, actualAccountEntity.getBalance().intValue());
		assertSame(ofEpochDayResult, actualAccountEntity.getCreate_date());
		assertEquals(1, actualAccountEntity.getInterest().intValue());
		assertEquals("Nickname", actualAccountEntity.getNickname());
		assertEquals("Type", actualAccountEntity.getType());
		assertEquals("42", actualAccountEntity.getUserId());
		assertTrue(actualAccountEntity.isActive_status());
		assertEquals("AccountEntity{userId='42', accountId='42', active_status='true', balance='1', interest='1',"
				+ " nickname='Nickname', create_date=1970-01-02}", actualAccountEntity.toString());
	}

	@Test
	public void testConstructor2() {
		AccountEntity actualAccountEntity = new AccountEntity();
		assertFalse(actualAccountEntity.isActive_status());
		assertNull(actualAccountEntity.getUserId());
		assertNull(actualAccountEntity.getType());
		assertNull(actualAccountEntity.getNickname());
		assertNull(actualAccountEntity.getInterest());
		assertNull(actualAccountEntity.getCreate_date());
		assertNull(actualAccountEntity.getBalance());
	}

	@Test
	public void testEquals() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);
		assertFalse(accountEntity.equals(null));
	}

	@Test
	public void testEquals10() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(false);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals11() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(0);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals2() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);
		assertFalse(accountEntity.equals("Different type to AccountEntity"));
	}

	@Test
	public void testEquals3() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);
		assertTrue(accountEntity.equals(accountEntity));
		int expectedHashCodeResult = accountEntity.hashCode();
		assertEquals(expectedHashCodeResult, accountEntity.hashCode());
	}

	@Test
	public void testEquals4() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertTrue(accountEntity.equals(accountEntity1));
		int expectedHashCodeResult = accountEntity.hashCode();
		assertEquals(expectedHashCodeResult, accountEntity1.hashCode());
	}

	@Test
	public void testEquals5() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(0);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals6() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname(null);
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals7() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId(null);
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals8() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId(null);
		accountEntity.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals9() {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUserId("42");
		accountEntity.setAccountId("42");
		accountEntity.setCreate_date(LocalDate.ofEpochDay(0L));
		accountEntity.setType("Type");
		accountEntity.setActive_status(true);
		accountEntity.setBalance(1);

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUserId("42");
		accountEntity1.setAccountId("42");
		accountEntity1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountEntity1.setType("Type");
		accountEntity1.setActive_status(true);
		accountEntity1.setBalance(1);
		assertFalse(accountEntity.equals(accountEntity1));
	}
}

