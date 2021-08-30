package com.beardtrust.webapp.accountservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TransferEntityTest {
	@Test
	public void testConstructor() {
		TransferEntity actualTransferEntity = new TransferEntity();
		actualTransferEntity.setAmount(new CurrencyValue(1,0));
		assertEquals(new CurrencyValue(1,0), actualTransferEntity.getAmount());
	}
}

