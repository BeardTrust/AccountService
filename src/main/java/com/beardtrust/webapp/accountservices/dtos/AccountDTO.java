/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservices.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Data
public class AccountDTO implements Serializable {

    private String userID;
    private String accountID;
    private boolean active_status;
    private Integer balance;
    private Integer interest;
    private String nickname;
    private LocalDate create_date;

}
