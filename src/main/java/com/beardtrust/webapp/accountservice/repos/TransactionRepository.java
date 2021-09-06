package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountTransaction;
import com.beardtrust.webapp.accountservice.entities.FinancialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<AccountTransaction, String> {
	Page<AccountTransaction> findAllBySource_IdOrTarget_IdIs(String sourceUserId, String targetUserId,
																	   Pageable page);

	Page<AccountTransaction> findAllBySource_IdOrTarget_IdIsAndStatusTimeOrTransactionAmount_DollarsOrTransactionAmount_CentsOrTransactionStatusContainsIgnoreCase(
			String sourceId, String targetId, LocalDateTime statusTime, int dollars, int cents, String transcationStatus, Pageable page);
}
