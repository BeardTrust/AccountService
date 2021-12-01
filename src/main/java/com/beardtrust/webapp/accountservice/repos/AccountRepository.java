/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import java.time.LocalDate;
import java.util.List;

import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
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
    
    Page<AccountEntity> findByCreateDate(LocalDate createDate, Pageable page);

    @Override
    Page<AccountEntity> findAll(Pageable page);

    public Page<AccountEntity> findAllByUser_UserId(String userId, Pageable page);

    public Page<AccountEntity> findAllByUser_UserIdAndCreateDate(String id, LocalDate parse, Pageable page);

    public Page<AccountEntity> findAllByBalance_DollarsOrBalance_CentsOrInterestIsLike(Integer newSearch, Integer newSearch0, Integer newSearch1, Pageable page);

    public List<AccountEntity> findAllByUser_UserId(String userId);

    Page<AccountEntity> findAllIgnoreCaseByNicknameContainingOrType_IdOrType_NameContainingOrType_IsActiveAndUser_UserIdIs(String search, String search1, String search2, Boolean valueOf, String search3, Pageable page);

    Page<AccountEntity> findByUser_UserIdAndNicknameContainingOrUser_UserIdAndType_IdOrUser_UserIdAndType_NameContainingOrUser_UserIdAndType_IsActiveOrUser_UserIdAndIdAllIgnoreCase(String id, String search, String id1, String search1, String id2, String search2, String id3, Boolean valueOf, String id4, String search3, Pageable page);

    Page<AccountEntity> findAllByUser_UserIdAndBalance(String id, CurrencyValue amount, Pageable page);

    Page<AccountEntity> findAllByUser_UserIdAndInterestOrUser_UserIdAndBalance_DollarsOrUser_UserIdAndBalance_Cents(String id, Integer newSearch, String id1, Integer newSearch1, String id2, Integer newSearch2, Pageable page);
}
