package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountTransaction;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.entities.FinancialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;

public interface TransactionRepository extends JpaRepository<AccountTransaction, String> {
	Page<AccountTransaction> findAllBySource_IdOrTarget_IdIs(String sourceUserId, String targetUserId,
																	   Pageable page);

	Page<AccountTransaction> findAllByStatusTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable page);

	Page<AccountTransaction> findAllByTransactionAmountEquals(CurrencyValue currencyValue, Pageable page);

	Page<AccountTransaction> findAllByTransactionStatus_StatusNameOrSource_IdOrTarget_IdEqualsOrNotesContainsIgnoreCase(String status,
																								  String sourceId,
																								  String targetId,
																								  String notes,
																								  Pageable page);
}
