/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Data
public class AccountCreation {

    @NotBlank(message = "How did no account ID get passed?")
    private String accountId;
    @NotBlank(message = "The user ID is extremely important")
    private String userId;
    @NotBlank(message = "Default value upon creation should be true")
    private String activeStatus;
    private String balance;
    @NotNull(message = "Check your calender and put in today.")
    private LocalDate createDate;
    private Integer interest;
    private String nicknsme;
    @NotNull(message = "Is this a checking or savings account?")
    private String type;
}
