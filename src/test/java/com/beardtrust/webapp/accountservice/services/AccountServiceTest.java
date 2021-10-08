package com.beardtrust.webapp.accountservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.entities.TransferEntity;
import com.beardtrust.webapp.accountservice.entities.UserEntity;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.repos.AccountTypeRepository;
import com.beardtrust.webapp.accountservice.repos.TransactionRepository;
import com.beardtrust.webapp.accountservice.repos.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {

	@MockBean
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@MockBean
	private AccountTypeRepository accountTypeRepository;

	@MockBean
	private TransactionRepository transactionRepository;

	@MockBean
	private UserRepository userRepository;

	@Test
	void testConstructor() {
		AccountEntity newAccountService = (new AccountService(mock(AccountRepository.class),
				mock(AccountTypeRepository.class), mock(UserRepository.class), mock(TransactionRepository.class)))
				.getNewAccountService();
		assertNull(newAccountService.getBalance());
		assertFalse(newAccountService.isActiveStatus());
		assertNull(newAccountService.getUser());
		assertNull(newAccountService.getType());
		assertNull(newAccountService.getNickname());
		assertNull(newAccountService.getInterest());
		assertNull(newAccountService.getId());
		assertNull(newAccountService.getCreateDate());
	}

	@Test
	void testCreateService() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.userRepository.findById((String) any())).thenReturn(ofResult);

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		when(this.accountTypeRepository.findByNameIs((String) any())).thenReturn(accountTypeEntity);

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole("Role");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUsername("janedoe");
		userEntity1.setId("42");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity1);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity1);
		accountEntity.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
		accountTypeEntity2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setId("42");
		accountTypeEntity2.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity2.setName("Name");
		accountTypeEntity2.setActive(true);
		accountTypeEntity2.setDescription("The characteristics of someone or something");

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue1);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity2);
		newAccountRequestModel.setActiveStatus(true);
		assertSame(accountEntity, this.accountService.createService(newAccountRequestModel));
		verify(this.userRepository).findById((String) any());
		verify(this.accountTypeRepository).findByNameIs((String) any());
		verify(this.accountRepository).save((AccountEntity) any());
	}

	@Test
	void testCreateService2() {
		when(this.userRepository.findById((String) any())).thenReturn(Optional.<UserEntity>empty());

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		when(this.accountTypeRepository.findByNameIs((String) any())).thenReturn(accountTypeEntity);

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity1);
		accountEntity.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
		accountTypeEntity2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setId("42");
		accountTypeEntity2.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity2.setName("Name");
		accountTypeEntity2.setActive(true);
		accountTypeEntity2.setDescription("The characteristics of someone or something");

		NewAccountRequestModel newAccountRequestModel = new NewAccountRequestModel();
		newAccountRequestModel.setBalance(currencyValue1);
		newAccountRequestModel.setNickname("Nickname");
		newAccountRequestModel.setUserId("42");
		newAccountRequestModel.setCreateDate(LocalDate.ofEpochDay(1L));
		newAccountRequestModel.setInterest(1);
		newAccountRequestModel.setType(accountTypeEntity2);
		newAccountRequestModel.setActiveStatus(true);
		this.accountService.createService(newAccountRequestModel);
		verify(this.userRepository).findById((String) any());
	}

	@Test
	void testGetAllService() {
		PageImpl<AccountEntity> pageImpl = new PageImpl<AccountEntity>(new ArrayList<AccountEntity>());
		when(this.accountRepository.findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase((String) any(),
				(String) any(), anyBoolean(), (String) any(), (String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(pageImpl);
		Page<AccountEntity> actualAllService = this.accountService.getAllService(1, 1, "Sort Name", "Sort Dir", "Search");
		assertSame(pageImpl, actualAllService);
		assertTrue(actualAllService.toList().isEmpty());
		verify(this.accountRepository).findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase((String) any(),
				(String) any(), anyBoolean(), (String) any(), (String) any(), (org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetDirection() {
		assertEquals(Sort.Direction.DESC, this.accountService.getDirection("Dir"));
		assertEquals(Sort.Direction.ASC, this.accountService.getDirection("asc"));
	}

	@Test
	void testGetSpecificService() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);
		assertSame(accountEntity, this.accountService.getSpecificService("42"));
		verify(this.accountRepository).findById((String) any());
	}

	@Test
	void testGetSpecificService2() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity);
		accountEntity.setActiveStatus(false);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);
		assertNull(this.accountService.getSpecificService("42"));
		verify(this.accountRepository).findById((String) any());
	}

	@Test
	void testGetSpecificService3() {
		when(this.accountRepository.findById((String) any())).thenReturn(Optional.<AccountEntity>empty());
		assertNull(this.accountService.getSpecificService("42"));
		verify(this.accountRepository).findById((String) any());
	}

	@Test
	void testGetListService() {
		ArrayList<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		when(this.accountRepository.findAllByUserId((String) any())).thenReturn(accountEntityList);
		List<AccountEntity> actualListService = this.accountService.getListService("42");
		assertSame(accountEntityList, actualListService);
		assertTrue(actualListService.isEmpty());
		verify(this.accountRepository).findAllByUserId((String) any());
	}

	@Test
	void testChangeMoneyService() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole("Role");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUsername("janedoe");
		userEntity1.setId("42");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setBalance(currencyValue1);
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUser(userEntity1);
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity1.setType(accountTypeEntity1);
		accountEntity1.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity1);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);

		CurrencyValue currencyValue2 = new CurrencyValue();
		currencyValue2.setDollars(1);
		currencyValue2.setCents(1);
		currencyValue2.setNegative(true);

		TransferEntity transferEntity = new TransferEntity();
		transferEntity.setAmount(currencyValue2);
		assertSame(accountEntity, this.accountService.changeMoneyService(transferEntity, "42"));
		verify(this.accountRepository).findById((String) any());
		verify(this.accountRepository).save((AccountEntity) any());
	}

	@Test
	void testChangeRecoveryService() {
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		when(this.accountTypeRepository.findByNameIs((String) any())).thenReturn(accountTypeEntity);

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity1);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole("Role");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUsername("janedoe");
		userEntity1.setId("42");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
		accountTypeEntity2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setId("42");
		accountTypeEntity2.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity2.setName("Name");
		accountTypeEntity2.setActive(true);
		accountTypeEntity2.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setBalance(currencyValue1);
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUser(userEntity1);
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity1.setType(accountTypeEntity2);
		accountEntity1.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity1);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);
		AccountEntity actualChangeRecoveryServiceResult = this.accountService.changeRecoveryService("42");
		assertSame(accountEntity, actualChangeRecoveryServiceResult);
		assertEquals(0, actualChangeRecoveryServiceResult.getInterest().intValue());
		assertEquals(accountTypeEntity2, actualChangeRecoveryServiceResult.getType());
		verify(this.accountTypeRepository).findByNameIs((String) any());
		verify(this.accountRepository).findById((String) any());
		verify(this.accountRepository).save((AccountEntity) any());
	}

	@Test
	void testUpdateService() {
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");
		when(this.accountTypeRepository.findByNameIs((String) any())).thenReturn(accountTypeEntity);

		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity1);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole("Role");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUsername("janedoe");
		userEntity1.setId("42");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
		accountTypeEntity2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity2.setId("42");
		accountTypeEntity2.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity2.setName("Name");
		accountTypeEntity2.setActive(true);
		accountTypeEntity2.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setBalance(currencyValue1);
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUser(userEntity1);
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity1.setType(accountTypeEntity2);
		accountEntity1.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity1);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);

		CurrencyValue currencyValue2 = new CurrencyValue();
		currencyValue2.setDollars(1);
		currencyValue2.setCents(1);
		currencyValue2.setNegative(true);

		AccountTypeEntity accountTypeEntity3 = new AccountTypeEntity();
		accountTypeEntity3.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity3.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity3.setId("42");
		accountTypeEntity3.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity3.setName("Name");
		accountTypeEntity3.setActive(true);
		accountTypeEntity3.setDescription("The characteristics of someone or something");

		UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
		updateAccountRequest.setBalance(currencyValue2);
		updateAccountRequest.setNickname("Nickname");
		updateAccountRequest.setUserId("42");
		updateAccountRequest.setCreateDate(LocalDate.ofEpochDay(1L));
		updateAccountRequest.setId("42");
		updateAccountRequest.setInterest(1);
		updateAccountRequest.setType(accountTypeEntity3);
		updateAccountRequest.setActiveStatus(true);
		assertSame(accountEntity1, this.accountService.updateService(updateAccountRequest));
		verify(this.accountTypeRepository).findByNameIs((String) any());
		verify(this.accountRepository).findById((String) any());
		verify(this.accountRepository).save((AccountEntity) any());
	}

	@Test
	void testDeactivateAccount() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(1);
		currencyValue1.setCents(1);
		currencyValue1.setNegative(true);

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole("Role");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUsername("janedoe");
		userEntity1.setId("42");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
		accountTypeEntity1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity1.setId("42");
		accountTypeEntity1.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity1.setName("Name");
		accountTypeEntity1.setActive(true);
		accountTypeEntity1.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity1 = new AccountEntity();
		accountEntity1.setBalance(currencyValue1);
		accountEntity1.setInterest(1);
		accountEntity1.setNickname("Nickname");
		accountEntity1.setUser(userEntity1);
		accountEntity1.setId("42");
		accountEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity1.setType(accountTypeEntity1);
		accountEntity1.setActiveStatus(true);
		when(this.accountRepository.save((AccountEntity) any())).thenReturn(accountEntity1);
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);
		assertEquals("Account 42 active status: false", this.accountService.deactivateAccount("foo"));
		verify(this.accountRepository).findById((String) any());
		verify(this.accountRepository).save((AccountEntity) any());
	}

	@Test
	void testRemoveAccount() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUsername("janedoe");
		userEntity.setId("42");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		accountTypeEntity.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountTypeEntity.setId("42");
		accountTypeEntity.setAccounts(new HashSet<AccountEntity>());
		accountTypeEntity.setName("Name");
		accountTypeEntity.setActive(true);
		accountTypeEntity.setDescription("The characteristics of someone or something");

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setBalance(currencyValue);
		accountEntity.setInterest(1);
		accountEntity.setNickname("Nickname");
		accountEntity.setUser(userEntity);
		accountEntity.setId("42");
		accountEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		accountEntity.setType(accountTypeEntity);
		accountEntity.setActiveStatus(true);
		Optional<AccountEntity> ofResult = Optional.<AccountEntity>of(accountEntity);
		doThrow(new IllegalArgumentException("foo")).when(this.accountRepository).delete((AccountEntity) any());
		when(this.accountRepository.findById((String) any())).thenReturn(ofResult);
		assertEquals("Error finding Entity: java.lang.IllegalArgumentException: foo",
				this.accountService.removeAccount("42"));
		verify(this.accountRepository).delete((AccountEntity) any());
		verify(this.accountRepository).findById((String) any());
	}


}

