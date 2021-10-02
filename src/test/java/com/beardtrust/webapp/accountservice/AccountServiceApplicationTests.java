package com.beardtrust.webapp.accountservice;

import com.beardtrust.webapp.accountservice.entities.AccountEntity;
import com.beardtrust.webapp.accountservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.accountservice.entities.CurrencyValue;
import com.beardtrust.webapp.accountservice.repos.AccountRepository;
import com.beardtrust.webapp.accountservice.repos.AccountTypeRepository;
import com.beardtrust.webapp.accountservice.repos.TransactionRepository;
import com.beardtrust.webapp.accountservice.repos.UserRepository;
import com.beardtrust.webapp.accountservice.services.AccountService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceApplicationTests {

}
