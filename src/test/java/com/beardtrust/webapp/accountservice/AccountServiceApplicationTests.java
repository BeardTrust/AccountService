package com.beardtrust.webapp.accountservice;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.repos.AccountTypeRepository;
import com.beardtrust.webapp.accountservice.repos.TransactionRepository;
import com.beardtrust.webapp.accountservice.repos.UserRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceApplicationTests {
    @Autowired
    private AccountService serv;

    @MockBean
    private AccountRepository repo;

    @Test
    public void testConstructor() {
        assertTrue((new AccountService(mock(AccountRepository.class), mock(AccountTypeRepository.class),
                mock(UserRepository.class), mock(TransactionRepository.class))).getListService("cf8bd72a" +
                "-4b4c-42f1-8dc3" +
                "-06cc2dc2cb8a").isEmpty());
    }

    @Test
    public void createServicetTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        when(this.repo.save((AccountEntity) any())).thenReturn(accountEntity);
    }

    @Test
    public void getSpecificServiceTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.getUser().setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);
        this.repo.save(accountEntity);
        when(this.repo.findById(anyString())).thenReturn(ofResult);
        AccountEntity actual = this.serv.getSpecificService("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        assertSame(actual, accountEntity);
        assertTrue(ofResult.isPresent());
    }

    @Test
    public void getAllServiceTest() {
        ArrayList<AccountEntity> aList = new ArrayList<AccountEntity>();
		when(this.repo.findAll()).thenReturn(aList);
		List<AccountEntity> actualAllAccountInfos = this.serv.getListService("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
		assertSame(aList, actualAllAccountInfos);
		assertTrue(actualAllAccountInfos.isEmpty());
		verify(this.repo).findAll();
    }

    @Test
    public void changeMoneyServiceTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.getUser().setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        when(this.repo.save((AccountEntity) any())).thenReturn(accountEntity);
    }

    @Test
    public void changeRecoveryServiceTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.getUser().setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        when(this.repo.save((AccountEntity) any())).thenReturn(accountEntity);
    }

    @Test
    public void updateServiceTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.getUser().setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        when(this.repo.save((AccountEntity) any())).thenReturn(accountEntity);
    }

    @Test
    public void deactivateAccountTest() {
        AccountTypeEntity accountType = new AccountTypeEntity(
				"Savings",
                "A recovery account",
                true,
                LocalDateTime.now(),
                LocalDateTime.MAX
        );

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        accountEntity.setActiveStatus(true);
        accountEntity.setBalance(new CurrencyValue(10,0));
        accountEntity.setCreateDate(LocalDateTime.parse("2001-01-01"));
        accountEntity.setInterest(1);
        accountEntity.setNickname("test");
        accountEntity.setType(accountType);
        accountEntity.getUser().setId("cf8bd72a-4b4c-42f1-8dc3-06cc2dc2cb8a");
        when(this.repo.save((AccountEntity) any())).thenReturn(accountEntity);
    }
}
