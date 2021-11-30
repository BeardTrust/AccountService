/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Entity
@Table(name = "accounts")
public class AccountEntity extends FinancialAsset {
	private static final long serialVersionUID = -3465065516553281959L;

	private Integer interest;
	private String nickname;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private AccountTypeEntity type;

	public AccountEntity() {
		this.setId(UUID.randomUUID().toString());
	}

	public AccountTypeEntity getType() {
		return type;
	}

	public void setType(AccountTypeEntity type) {
		this.type = type;
	}

	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString(){
		return "Account Entity: " + this.getId() + ", Type: " + getType().getName() + ", Interest rate: " + getInterest() + ", Nickname: " + getNickname() + ", Balance: " + getBalance().toString() + ", Date created: " + getCreateDate() + ", Expiration date: " + getType().getExpireDate();
	}
}
