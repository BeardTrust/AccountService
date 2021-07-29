/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.repos;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String>{
    
    /*
    *Find by the Account ID
    *
    */
    AccountEntity findByAccountID(String s);
    
    /*
    *Find by the User ID    
    *
    */
    AccountEntity findByUserID(String s);
    
    /*
    *Find by Nickname
    *
    */
    AccountEntity findByNickname(String s);
}
