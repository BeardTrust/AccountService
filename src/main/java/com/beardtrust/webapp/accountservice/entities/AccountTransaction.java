package com.beardtrust.webapp.accountservice.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_transactions")
public class AccountTransaction extends FinancialTransaction{
	private static final long serialVersionUID = 4628434455108292689L;
}
