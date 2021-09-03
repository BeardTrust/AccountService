/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Entity
public class AccountEntity extends FinancialAsset {
	private static final long serialVersionUID = -3465065516553281959L;

	private Integer interest;
	private String nickname;
	@JsonBackReference
	@ManyToOne
	private AccountTypeEntity type;

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
}
