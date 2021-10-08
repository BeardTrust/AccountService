package com.beardtrust.webapp.accountservice.repos;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AccountTypeRepositoryTest {

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Test
	void testFindByNameIs() {
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");
		this.accountTypeRepository.<AccountTypeEntity>save(accountTypeEntity);
		this.accountTypeRepository.<AccountTypeEntity>save(accountTypeEntity1);
		assertNull(this.accountTypeRepository.findByNameIs("foo"));
	}

}

