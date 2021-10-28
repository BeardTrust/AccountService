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

    Page<AccountEntity> findAllByUser_UserIdOrIdOrActiveStatusOrNicknameOrTypeContainsIgnoreCase(String userId,
                                                                                             String id,
                                                                                             boolean activeStatus, String nickname, String type, Pageable page);
    
    Page<AccountEntity> findByCreateDate(LocalDate createDate, Pageable page);
    
    Page<AccountEntity> findByCreateDateAndUserId(LocalDate createDate, Pageable page, String userId);


    @Override
    Page<AccountEntity> findAll(Pageable page);

    /*
    *Find by Nickname
    *
     */
    AccountEntity findByNickname(String s);

    public Page<AccountEntity> findAllByUser_UserId(String userId, Pageable page);

    public Page<AccountEntity> findAllByUser_UserIdAndInterestOrBalance_DollarsOrBalance_Cents(String id, Integer newSearch, Integer newSearch0, Integer newSearch1, Pageable page);

    public Page<AccountEntity> findAllByUser_UserIdAndCreateDate(String id, LocalDate parse, Pageable page);

    public Page<AccountEntity> findAllByBalance_DollarsOrBalance_CentsOrInterestIsLike(Integer newSearch, Integer newSearch0, Integer newSearch1, Pageable page);

    public Page<AccountEntity> findAllIgnoreCaseByNicknameOrType_IdOrType_NameOrType_IsActiveAndUser_UserIdIs(String search, String search0, String search1, Boolean valueOf, String id, Pageable page);

    public List<AccountEntity> findAllByUser_UserId(String userId);

    Page<AccountEntity> findByUser_UserIdAndNicknameOrUser_UserIdAndType_IdOrUser_UserIdAndType_NameOrUser_UserIdAndType_IsActiveOrUser_UserIdAndIdAllIgnoreCase(String id, String search, String id1, String search1, String id2, String search2, String id3, Boolean valueOf, String id4, String search3, Pageable page);
}
