package com.beardtrust.webapp.accountservice.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

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
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(0);
		assertTrue(accountDTO.canEqual(accountDTO1));
	}

	@Test
	public void testConstructor() {
		AccountDTO actualAccountDTO = new AccountDTO();
		actualAccountDTO.setAccountId("42");
		actualAccountDTO.setActive_status(true);
		actualAccountDTO.setBalance(1);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualAccountDTO.setCreate_date(ofEpochDayResult);
		actualAccountDTO.setInterest(1);
		actualAccountDTO.setNickname("Nickname");
		actualAccountDTO.setType("Type");
		actualAccountDTO.setUserId("42");
		assertEquals("42", actualAccountDTO.getAccountId());
		assertEquals(1, actualAccountDTO.getBalance().intValue());
		assertSame(ofEpochDayResult, actualAccountDTO.getCreate_date());
		assertEquals(1, actualAccountDTO.getInterest().intValue());
		assertEquals("Nickname", actualAccountDTO.getNickname());
		assertEquals("Type", actualAccountDTO.getType());
		assertEquals("42", actualAccountDTO.getUserId());
		assertTrue(actualAccountDTO.isActive_status());
		assertEquals("AccountDTO(userId=42, accountId=42, active_status=true, balance=1, interest=1, nickname=Nickname,"
				+ " create_date=1970-01-02, type=Type)", actualAccountDTO.toString());
	}

	@Test
	public void testEquals() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);
		assertFalse(accountDTO.equals(null));
	}

	@Test
	public void testEquals10() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("Nickname");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals11() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId(null);
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals12() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("Nickname");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals13() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(0L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals14() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(null);
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals15() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("42");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals16() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType(null);
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals17() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(false);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals18() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(0);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals19() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(null);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals2() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);
		assertFalse(accountDTO.equals("Different type to AccountDTO"));
	}

	@Test
	public void testEquals20() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(null);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(null);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals21() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname(null);
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname(null);
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals22() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId(null);
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId(null);
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals3() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);
		assertTrue(accountDTO.equals(accountDTO));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO.hashCode());
	}

	@Test
	public void testEquals4() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertTrue(accountDTO.equals(accountDTO1));
		int expectedHashCodeResult = accountDTO.hashCode();
		assertEquals(expectedHashCodeResult, accountDTO1.hashCode());
	}

	@Test
	public void testEquals5() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(0);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals6() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(null);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals7() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("42");
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals8() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname(null);
		accountDTO.setUserId("42");
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}

	@Test
	public void testEquals9() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setInterest(1);
		accountDTO.setNickname("Nickname");
		accountDTO.setUserId(null);
		accountDTO.setAccountId("42");
		accountDTO.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO.setType("Type");
		accountDTO.setActive_status(true);
		accountDTO.setBalance(1);

		AccountDTO accountDTO1 = new AccountDTO();
		accountDTO1.setInterest(1);
		accountDTO1.setNickname("Nickname");
		accountDTO1.setUserId("42");
		accountDTO1.setAccountId("42");
		accountDTO1.setCreate_date(LocalDate.ofEpochDay(1L));
		accountDTO1.setType("Type");
		accountDTO1.setActive_status(true);
		accountDTO1.setBalance(1);
		assertFalse(accountDTO.equals(accountDTO1));
	}
}

