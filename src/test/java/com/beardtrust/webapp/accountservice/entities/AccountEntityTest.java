package com.beardtrust.webapp.accountservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

public class AccountEntityTest {
	@Test
	public void testConstructor() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity actualAccountEntity = new AccountEntity();
		actualAccountEntity.setId("42");
		actualAccountEntity.setActiveStatus(true);
		actualAccountEntity.setBalance(new CurrencyValue(1, 0));
		LocalDateTime ofEpochDayResult = LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC);
		actualAccountEntity.setCreateDate(ofEpochDayResult);
		actualAccountEntity.setInterest(1);
		actualAccountEntity.setNickname("Nickname");
		actualAccountEntity.setType(accountType);;
		actualAccountEntity.getUser().setId("42");
		assertEquals("42", actualAccountEntity.getId());
		assertEquals(1, actualAccountEntity.getBalance());
		assertSame(ofEpochDayResult, actualAccountEntity.getCreateDate());
		assertEquals(1, actualAccountEntity.getInterest().intValue());
		assertEquals("Nickname", actualAccountEntity.getNickname());
		assertEquals("Type", actualAccountEntity.getType());
		assertEquals("42", actualAccountEntity.getUser().getId());
		assertTrue(actualAccountEntity.isActiveStatus());
		assertEquals("AccountEntity{userId='42', id='42', activeStatus='true', balance='1', interest='1',"
				+ " nickname='Nickname', createDate=1970-01-02}", actualAccountEntity.toString());
	}

	@Test
	public void testConstructor2() {
		
		AccountEntity actualAccountEntity = new AccountEntity();
		assertFalse(actualAccountEntity.isActiveStatus());
		assertNull(actualAccountEntity.getUser().getId());
		assertNull(actualAccountEntity.getType());
		assertNull(actualAccountEntity.getNickname());
		assertNull(actualAccountEntity.getInterest());
		assertNull(actualAccountEntity.getCreateDate());
		assertNull(actualAccountEntity.getBalance());
	}

	@Test
	public void testEquals() {

		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(0,0));
		assertFalse(accountEntity.equals(null));
	}

	@Test
	public void testEquals10() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(false);
		accountEntity.setBalance(new CurrencyValue(0,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(0,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals11() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(0,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals2() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals("Different type to AccountEntity"));
	}

	@Test
	public void testEquals3() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));
		assertTrue(accountEntity.equals(accountEntity));
		int expectedHashCodeResult = accountEntity.hashCode();
		assertEquals(expectedHashCodeResult, accountEntity.hashCode());
	}

	@Test
	public void testEquals4() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertTrue(accountEntity.equals(accountEntity1));
		int expectedHashCodeResult = accountEntity.hashCode();
		assertEquals(expectedHashCodeResult, accountEntity1.hashCode());
	}

	@Test
	public void testEquals5() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(0);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals6() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname(null);
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals7() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId(null);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals8() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId(null);
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}

	@Test
	public void testEquals9() {
		AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
				"A recovery account",
				true,
				LocalDateTime.now(),
				LocalDateTime.MAX
		);
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.getUser().setId("42");
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.ofEpochSecond(0L, 0, ZoneOffset.UTC));
		accountEntity.setType(accountType);;
		accountEntity.setActiveStatus(true);
		accountEntity.setBalance(new CurrencyValue(1,0));

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.getUser().setId("42");
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.ofEpochSecond(1L, 0, ZoneOffset.UTC));
		accountEntity1.setType(accountType);;
		accountEntity1.setActiveStatus(true);
		accountEntity1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountEntity.equals(accountEntity1));
	}
}

