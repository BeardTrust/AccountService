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
    
    Page<AccountEntity> findAllByBalanceOrInterestIsLikeAndUserId(Integer balance, Integer interest, Pageable page, String userId);
    
    Page<AccountEntity> findByCreateDateAndUserId(LocalDate createDate, Pageable page, String userId);
    
    Page<AccountEntity> findAllByIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCaseAndUserId(String id,
                                                                                             boolean activeStatus, String nickname, String type, Pageable page, String userId);


    @Override
    Page<AccountEntity> findAll(Pageable page);

    /*
    *Find by Nickname
    *
     */
    AccountEntity findByNickname(String s);

    public Page<AccountEntity> findAllByInterestOrBalance_DollarsOrBalance_CentsAndUserId(Double newSearch, Double newSearch0, Double newSearch1, String userId, Pageable page);

    public Page<AccountEntity> findAllByInterestOrBalance_DollarsOrBalance_CentsAndUserId(Integer newSearch, Integer newSearch0, Integer newSearch1, String userId, Pageable page);

    public Page<AccountEntity> findByCreateDateAndUser_Id(LocalDate parse, String userId, Pageable page);

    public Page<AccountEntity> findAllByUser_Id(String userId, Pageable page);

    public Page<AccountEntity> findAllIgnoreCaseByNicknameOrType_IdOrType_NameOrType_IsActiveOrIdAndUser_Id(String search, String search0, String search1, Boolean valueOf, String search2, String userId, Pageable page);

    public Page<AccountEntity> findAllByInterestOrBalance_DollarsOrBalance_CentsAndUser_Id(Double newSearch, Double newSearch0, Double newSearch1, String userId, Pageable page);

    public Page<AccountEntity> findAllByInterestOrBalance_DollarsOrBalance_CentsAndUser_Id(Integer newSearch, Integer newSearch0, Integer newSearch1, String userId, Pageable page);

}
