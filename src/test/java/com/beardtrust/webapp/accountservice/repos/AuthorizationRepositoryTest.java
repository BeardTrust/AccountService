package com.beardtrust.webapp.accountservice.repos;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AuthorizationRepositoryTest {

	@Autowired
	private AuthorizationRepository authorizationRepository;

	@Test
	void testFindByUserId() {
		assertFalse(this.authorizationRepository.findById("foo").isPresent());
	}

}

