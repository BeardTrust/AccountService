package com.beardtrust.webapp.accountservice.services;

import com.beardtrust.webapp.accountservice.entities.*;
import com.beardtrust.webapp.accountservice.models.NewAccountRequestModel;
import com.beardtrust.webapp.accountservice.models.UpdateAccountRequest;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.repos.AccountTypeRepository;
import com.beardtrust.webapp.accountservice.repos.TransactionRepository;
import com.beardtrust.webapp.accountservice.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/**
 * @author Nathanael <Nathanael.Grier at your.org>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Service
@Slf4j
public class AccountService {

	private final AccountTypeRepository accountTypeRepository;
	private final UserRepository userRepository;
	private final TransactionRepository transactionRepository;
	private final AccountRepository repo;

	public AccountService(AccountRepository repo, AccountTypeRepository accountTypeRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
		this.repo = repo;
		this.accountTypeRepository = accountTypeRepository;
		this.userRepository = userRepository;
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public AccountEntity createService(NewAccountRequestModel request) {
		Optional<UserEntity> user = userRepository.findById(request.getUserId());
		AccountEntity newAccount = new AccountEntity();

		if (user.isPresent()) {
			AccountTypeEntity type = accountTypeRepository.findByNameIs(request.getType().getName());
			newAccount.setUser(user.get());
			newAccount.setType(type);
			newAccount.setBalance(request.getBalance());
			newAccount.setInterest(request.getInterest());
			newAccount.setActiveStatus(request.isActiveStatus());
			newAccount.setCreateDate(LocalDateTime.now());
			newAccount.setNickname(request.getNickname());
			newAccount.setId(UUID.randomUUID().toString());
			newAccount = repo.save(newAccount);
		}

		return newAccount;
	}

	public AccountEntity getNewAccountService() {
		return new AccountEntity();
	}

	public Page<AccountEntity> getAllService( /*Pageable page*/ Integer n, Integer s, String sortName, String sortDir, String search) {
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(new Sort.Order(getDirection(sortDir), sortName));

		Pageable page = PageRequest.of(n, s, Sort.by(orders));

		if (!("").equals(search)) {
			if (isCreatable(search)) {
				Integer newSearch = Integer.parseInt(search) * 100;
				return repo.findAllByBalanceOrInterestIsLike(newSearch, newSearch, page);
			} else if (GenericValidator.isDate(search, "yyyy-MM", false)) {
				return repo.findByCreateDate(LocalDate.parse(search), page);
			} else {
				return repo.findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(search, search, Boolean.parseBoolean(search), search, search, page);
			}
		} else {
			return repo.findAll(page);
		}
	}

	public Sort.Direction getDirection(String dir) {
		if ("asc".equals(dir)) {
			return Sort.Direction.ASC;
		} else {
			return Sort.Direction.DESC;
		}
	}

	public AccountEntity getSpecificService(String id) {
		Optional<AccountEntity> a = repo.findById(id);
		if (a.isPresent() && a.get().isActiveStatus()) {
			return a.get();
		} else {
			return null;
		}
	}

	public List<AccountEntity> getListService(String userId) {
		return repo.findAllByUserId(userId);
	}

	public AccountEntity changeMoneyService(TransferEntity amount, String id) {
		Optional<AccountEntity> a = repo.findById(id);

		if (a.isPresent() && a.get().isActiveStatus()) {
			a.get().getBalance().add(amount.getAmount());
			if ((a.get().getBalance().getDollars() == 0) && (a.get().getBalance().getCents() == 0) &&
					(a.get().getType().getName().equals("Recovery"))) {
				deactivateAccount(a.get().getId());
			}
			repo.save(a.get());
		}

		return a.orElse(null);
	}

	public AccountEntity changeRecoveryService(String id) {

		Optional<AccountEntity> a = repo.findById(id);

		if (a.isPresent() && a.get().isActiveStatus()) {
			a.get().setType(accountTypeRepository.findByNameIs("Recovery"));
			a.get().setInterest(0);
			repo.save(a.get());
		}

		return a.orElse(null);
	}

	@Transactional
	public AccountEntity updateService(UpdateAccountRequest a) {
		String id = a.getId() != null ? a.getId() : UUID.randomUUID().toString();
		Optional<AccountEntity> account = repo.findById(id);
		AccountTypeEntity type = accountTypeRepository.findByNameIs(a.getType().getName());
		AccountEntity newAccount;

		if (account.isPresent()) {
			account.get().setBalance(a.getBalance());
			account.get().setNickname(a.getNickname());
			account.get().setActiveStatus(a.isActiveStatus());
			account.get().setInterest(a.getInterest());
			newAccount = repo.save(account.get());
		} else {
			newAccount = new AccountEntity();
			newAccount.setId(id);
			newAccount.setCreateDate(LocalDateTime.now());
			newAccount.setUser(userRepository.findById(a.getUserId()).orElse(null));
			newAccount.setBalance(a.getBalance());
			newAccount.setInterest(a.getInterest());
			newAccount.setActiveStatus(a.isActiveStatus());
			newAccount.setNickname(a.getNickname());
			newAccount.setType(type);
			newAccount = repo.save(newAccount);
		}

		return newAccount;
	}

	public String deactivateAccount(String a) {
		Optional<AccountEntity> a2 = repo.findById(a);

		if (a2.isPresent()) {
			try {
				a2.get().setActiveStatus(false);
				repo.save(a2.get());
				return "Account " + a2.get().getId() + " active status: " + a2.get().isActiveStatus();
			} catch (Exception e) {
				return "Account " + a2.get().getId() + " deactivation failed with error: " + e.getLocalizedMessage();
			}
		}
		return "Account does not exist";
	}

	public String removeAccount(String id) {
		String returnValue;

		try {
			Optional<AccountEntity> a = repo.findById(id);
			a.ifPresent(repo::delete);

			returnValue = "Remove successful";
		} catch (Exception e) {
			returnValue = "Error finding Entity: " + e;
		}
		return returnValue;
	}

	/**
	 * This method receives an account id as a String, a String of search criteria, and a Pageable object and returns
	 * the requested page of transactions associated with that account and matching that search criteria.  If the
	 * search term can be parsed as a CurrencyValue object, the return value will be a list of all associated account
	 * transactions which match that CurrencyValue.  If the search term can be parsed as a LocalDateTime object,
	 * the return value will be a list of all associated account transactions with status dates on that date.  If the
	 * search field is anything else, it will return a list of associated account transactions which match the search
	 * criteria in one or more of the following fields: notes, status name, source id, and target id.
	 *
	 * @param id     String the account id of the associated account
	 * @param search String the value to search for
	 * @param page   Pageable an object representing the page request
	 * @return Page the requested page
	 */
	public Page<AccountTransaction> getAllAccountTransactionsByUserId(String id, String search, Pageable page) {
		Page<AccountTransaction> returnValue = null;

		if (search == null) {
			returnValue = transactionRepository.findAllBySource_IdOrTarget_IdIs(id, id, page);
		} else {
			if (GenericValidator.isDate(search, "yyyy-MM-dd", true)) {
				log.info("Searching and filtering account transaction request as a timestamp");
				try {
					LocalDateTime startDate = LocalDateTime.parse(search + "T00:00:00");
					LocalDateTime endDate = startDate.plusDays(1);
					returnValue = transactionRepository.findAllByStatusTimeBetween(startDate, endDate, page);
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage());
				}
			} else if (isCreatable(search)) {
				log.info("Searching and filtering account transaction request as a number");
				try {
					returnValue = transactionRepository.findAllByTransactionAmountEquals(CurrencyValue.valueOf(Double.parseDouble(search)),
							page);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			} else {
				log.info("Searching and filtering account transaction request as a string");
				try {
					returnValue =
							transactionRepository.findAllByTransactionStatus_StatusNameOrSource_IdOrTarget_IdEqualsOrNotesContainsIgnoreCase(search, search, search, search,
									page);
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage());
				}

			}
		}
		return returnValue;
	}
}
