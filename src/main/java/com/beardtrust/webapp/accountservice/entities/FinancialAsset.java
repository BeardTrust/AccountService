package com.beardtrust.webapp.accountservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class is the abstract base class for all financial assets managed by
 * or interacting directly with assets managed by BeardTrust.  It has a UUID-
 * based id field, a reference to the owning user entity, a boolean representing
 * whether the financial asset is active, a balance consisting of a boolean flag
 * indicating whether the amount is positive or negative, an integer for the
 * number of dollars in the balance, and an integer for the number of cents in
 * the balance, and a timestamp issued when it was created.  Financial assets
 * are compared by their current balances.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
public abstract class FinancialAsset implements Comparable<FinancialAsset>, Serializable {
	@Id
	private String id;
	@ManyToOne
	private UserEntity user;
	private boolean activeStatus;
	@Embedded
	private CurrencyValue balance;
	private LocalDateTime createTime;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets user.
	 *
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * Sets user.
	 *
	 * @param user the user
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * Is active status boolean.
	 *
	 * @return the boolean
	 */
	public boolean isActiveStatus() {
		return activeStatus;
	}

	/**
	 * Sets active status.
	 *
	 * @param activeStatus the active status
	 */
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * Gets balance.
	 *
	 * @return the balance
	 */
	public CurrencyValue getBalance() {
		return balance;
	}

	/**
	 * Sets balance.
	 *
	 * @param balance the balance
	 */
	public void setBalance(CurrencyValue balance) {
		this.balance = balance;
	}

	/**
	 * Gets create time.
	 *
	 * @return the create time
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	/**
	 * Sets create time.
	 *
	 * @param createTime the create time
	 */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public int compareTo(FinancialAsset o) {
		return this.balance.compareTo(o.balance);
	}
}
