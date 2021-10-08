package com.beardtrust.webapp.accountservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TransferEntityTest {

	@Test
	void testConstructor() {
		TransferEntity actualTransferEntity = new TransferEntity();
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);
		actualTransferEntity.setAmount(currencyValue);
		assertSame(currencyValue, actualTransferEntity.getAmount());
	}
}

