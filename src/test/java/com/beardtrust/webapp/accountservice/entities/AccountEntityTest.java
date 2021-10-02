package com.beardtrust.webapp.accountservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class AccountEntityTest {

	@Test
	void testConstructor() {
		AccountEntity actualAccountEntity = new AccountEntity();
		actualAccountEntity.setInterest(1);
		actualAccountEntity.setNickname("Nickname");
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		actualAccountEntity.setType(accountTypeEntity);
		assertEquals(1, actualAccountEntity.getInterest().intValue());
		assertEquals("Nickname", actualAccountEntity.getNickname());
		assertSame(accountTypeEntity, actualAccountEntity.getType());
		assertEquals(
				"FinancialAsset{id='null', user=null, activeStatus=false, balance=null, createDate=null, targetedTransactions"
						+ "=null, sourcedTransactions=null}",
				actualAccountEntity.toString());
	}

}

