package com.beardtrust.webapp.accountservice.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.entities.UserEntity;
import org.junit.jupiter.api.Test;

public class AccountDTOTest {
	@Test
	public void testCanEqual() {
		assertFalse((new AccountDTO()).canEqual("Other"));
	}

	@Test
	public void testCanEqual2() {
		AccountDTO accountDTO = new AccountDTO();

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(0);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(0,0));
		assertTrue(accountDTO.canEqual(accountDTO1));
	}

	@Test
	public void testConstructor() {
		AccountDTO actualAccountDTO = new AccountDTO();
		actualAccountDTO.setId("42");
		actualAccountDTO.setActiveStatus(true);
		actualAccountDTO.setBalance(new CurrencyValue(1,0));
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualAccountDTO.setCreateDate(ofEpochDayResult);
		actualAccountDTO.setInterest(1);
		actualAccountDTO.setNickname("Nickname");
		actualAccountDTO.setType("Type");
		actualAccountDTO.setUser(new UserEntity());
		assertEquals("42", actualAccountDTO.getId());
		assertEquals(1, actualAccountDTO.getBalance());
		assertSame(ofEpochDayResult, actualAccountDTO.getCreateDate());
		assertEquals(1, actualAccountDTO.getInterest().intValue());
		assertEquals("Nickname", actualAccountDTO.getNickname());
		assertEquals("Type", actualAccountDTO.getType());
		assertEquals("42", actualAccountDTO.getUser());
		assertTrue(actualAccountDTO.isActiveStatus());
		assertEquals("AccountDTO(userId=42, id=42, activeStatus=true, balance=1, interest=1, nickname=Nickname,"
				+ " createDate=1970-01-02, type=Type)", actualAccountDTO.toString());
	}

	@Test
	public void testEquals() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(null));
	}

	@Test
	public void testEquals10() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals11() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId(null);
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals12() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("Nickname");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals13() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(0L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals14() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(null);
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals15() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("42");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals16() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType(null);
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals17() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(false);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals18() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(0,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals19() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(null);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals2() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals("Different type to AccountDTO"));
	}

	@Test
	public void testEquals20() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(null);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(null);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals21() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname(null);
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname(null);
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals22() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(null);
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(null);
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals3() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));
		assertTrue(accountDTO.equals(accountDTO));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO.hashCode());
	}

	@Test
	public void testEquals4() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals5() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(0);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals6() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(null);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals7() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("42");
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals8() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname(null);
		accountDTO.setUser(new UserEntity());
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals9() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUser(null);
		accountDTO.setId("42");
		accountDTO.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActiveStatus(true);
		accountDTO.setBalance(new CurrencyValue(1,0));

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUser(new UserEntity());
		accountDTO1.setId("42");
		accountDTO1.setCreateDate(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActiveStatus(true);
		accountDTO1.setBalance(new CurrencyValue(1,0));
		assertFalse(accountDTO.equals(accountDTO1));
	}
}

