package com.beardtrust.webapp.accountservice.models;

import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewAccountRequestModel {
	private String userId;
	private CurrencyValue balance;
	private LocalDate createDate;
	private AccountTypeEntity type;
	private String nickname;
	private int interest;
	private boolean activeStatus;
}
