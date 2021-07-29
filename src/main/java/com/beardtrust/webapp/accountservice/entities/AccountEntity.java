/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    @Id
    @Column(unique = true)
    private String accountID;
    @Column(unique = true)
    private String userID;
    private boolean active_status;
    private Integer balance;
    private LocalDate create_date;
    private Integer interest;
    private String nickname;

    public AccountEntity() {
        this.accountID = UUID.randomUUID().toString();
    }
    public String getAccountId() {
        return accountID;
    }

    public void setAccountId(String accountId) {
        this.accountID = accountId;
    }

    public String getUserId() {
        return userID;
    }

    public void setUserId(String userId) {
        this.userID = userId;
    }

    public boolean isActive_status() {
        return active_status;
    }

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(accountID, that.accountID) && Objects.equals(userID, that.userID) && Objects.equals(active_status, that.active_status) && Objects.equals(balance, that.balance) && Objects.equals(create_date, that.create_date) && Objects.equals(interest, that.interest) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, accountID, active_status, balance, interest, create_date, nickname);
    }

    @Override
    public String toString() {
        return "AccountEntity{"
                + "userId='" + userID + '\''
                + ", accountId='" + accountID + '\''
                + ", active_status='" + active_status + '\''
                + ", balance='" + balance + '\''
                + ", interest='" + interest + '\''
                + ", nickname='" + nickname + '\''
                + ", create_date=" + create_date
                + '}';
    }
}
