package com.beardtrust.webapp.accountservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AccountCreationResponseTest {

	@Test
	void testCanEqual() {
		assertFalse((new AccountCreationResponse()).canEqual("Other"));
	}

	@Test
	void testCanEqual2() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();

		AccountCreationResponse accountCreationResponse1 = new AccountCreationResponse();
		accountCreationResponse1.setUserID("User ID");
		assertTrue(accountCreationResponse.canEqual(accountCreationResponse1));
	}

	@Test
	void testConstructor() {
		AccountCreationResponse actualAccountCreationResponse = new AccountCreationResponse();
		actualAccountCreationResponse.setUserID("User ID");
		assertEquals("User ID", actualAccountCreationResponse.getUserID());
		assertEquals("AccountCreationResponse(userID=User ID)", actualAccountCreationResponse.toString());
	}

	@Test
	void testEquals() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID("User ID");
		assertFalse(accountCreationResponse.equals(null));
	}

	@Test
	void testEquals2() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID("User ID");
		assertFalse(accountCreationResponse.equals("Different type to AccountCreationResponse"));
	}

	@Test
	void testEquals3() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID("User ID");
		assertTrue(accountCreationResponse.equals(accountCreationResponse));
		int expectedHashCodeResult = accountCreationResponse.hashCode();
		assertEquals(expectedHashCodeResult, accountCreationResponse.hashCode());
	}

	@Test
	void testEquals4() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID("User ID");

		AccountCreationResponse accountCreationResponse1 = new AccountCreationResponse();
		accountCreationResponse1.setUserID("User ID");
		assertTrue(accountCreationResponse.equals(accountCreationResponse1));
		int expectedHashCodeResult = accountCreationResponse.hashCode();
		assertEquals(expectedHashCodeResult, accountCreationResponse1.hashCode());
	}

	@Test
	void testEquals5() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID(null);

		AccountCreationResponse accountCreationResponse1 = new AccountCreationResponse();
		accountCreationResponse1.setUserID("User ID");
		assertFalse(accountCreationResponse.equals(accountCreationResponse1));
	}

	@Test
	void testEquals6() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID("com.beardtrust.webapp.accountservice.models.AccountCreationResponse");

		AccountCreationResponse accountCreationResponse1 = new AccountCreationResponse();
		accountCreationResponse1.setUserID("User ID");
		assertFalse(accountCreationResponse.equals(accountCreationResponse1));
	}

	@Test
	void testEquals7() {
		AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
		accountCreationResponse.setUserID(null);

		AccountCreationResponse accountCreationResponse1 = new AccountCreationResponse();
		accountCreationResponse1.setUserID(null);
		assertTrue(accountCreationResponse.equals(accountCreationResponse1));
		int expectedHashCodeResult = accountCreationResponse.hashCode();
		assertEquals(expectedHashCodeResult, accountCreationResponse1.hashCode());
	}

}

