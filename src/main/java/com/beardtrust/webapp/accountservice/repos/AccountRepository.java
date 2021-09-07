/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Page<AccountEntity> findAllByUser_IdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(String userId,
                                                                                             String id,
                                                                                             boolean activeStatus, String nickname, String type, Pageable page);
    
    Page<AccountEntity> findAllByBalanceOrInterestIsLike(Integer balance, Integer interest, Pageable page);
    
    Page<AccountEntity> findByCreateDate(LocalDate createDate, Pageable page);

    List<AccountEntity> findAllByUserId(String userId);

    @Override
    Page<AccountEntity> findAll(Pageable page);

    /*
    *Find by Nickname
    *
     */
    AccountEntity findByNickname(String s);
}
