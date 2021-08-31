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
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    @Id
    @Column(unique = true)
    private String accountId;
    private String userId;
    private boolean activeStatus;
    @Embedded
    private CurrencyValue balance;
    @NotNull
    private LocalDate createDate;
    private Integer interest;
    private String nickname;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AccountEntity() {
        this.accountId = UUID.randomUUID().toString();
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public CurrencyValue getBalance() {
        return balance;
    }

    public void setBalance(CurrencyValue balance) {
        this.balance = balance;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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
        return Objects.equals(accountId, that.accountId) && Objects.equals(userId, that.userId) && Objects.equals(activeStatus, that.activeStatus) && Objects.equals(balance, that.balance) && Objects.equals(createDate, that.createDate) && Objects.equals(interest, that.interest) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, activeStatus, balance, interest, createDate, nickname);
    }

    @Override
    public String toString() {
        return "AccountEntity{"
                + "userId='" + userId + '\''
                + ", accountId='" + accountId + '\''
                + ", activeStatus='" + activeStatus + '\''
                + ", balance='" + balance + '\''
                + ", interest='" + interest + '\''
                + ", nickname='" + nickname + '\''
                + ", create_date=" + createDate
                + '}';
    }
}
