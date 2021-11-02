package com.beardtrust.webapp.accountservice.models;

import java.time.LocalDateTime;

public class NewAccountTypeRequestModel {
    private String name;
    private String description;
    private boolean isActive;
    private LocalDateTime createdDate;
}
