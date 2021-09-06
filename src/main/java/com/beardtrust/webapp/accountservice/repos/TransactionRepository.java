package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountTransaction;
import com.beardtrust.webapp.accountservice.entities.FinancialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<AccountTransaction, String> {
	Page<AccountTransaction> findAllBySource_IdOrTarget_IdIs(String sourceUserId, String targetUserId,
																	   Pageable page);
}
