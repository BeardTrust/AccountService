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
    private String accountID;
    @NotBlank(message = "How did no account ID get passed?")
    private String userID;
    @NotBlank(message = "The user ID is extremely important")
    private String active_status;
    @NotBlank(message = "Default value upon creation should be true")
    private String balance;
    private LocalDate create_date;
    @NotNull(message = "Check your calender and put in today.")
    private Integer interest;
    private String nicknsme;
}
