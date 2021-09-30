package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * The interface Authorization repository.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@org.springframework.stereotype.Repository
public interface AuthorizationRepository extends Repository<AccountEntity, String> {
	/**
	 * Find by user id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<AccountEntity> findById(String id);
}
