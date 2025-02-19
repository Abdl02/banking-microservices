package com.bank.account;

import com.bank.account.api.dto.request.AccountRequest;
import com.bank.account.api.dto.response.AccountResponse;
import com.bank.account.infra.mapper.AccountMapper;
import com.bank.account.repository.entity.Account;
import com.bank.account.repository.repos.AccountRepository;
import com.bank.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    private AccountRequest accountRequest;
    private Account account;
    private AccountResponse accountResponse;

    @BeforeEach
    void setUp() {
        accountRequest = new AccountRequest(
                "1234567890",
                100.0,
                "ACTIVE",
                "SAVING",
                "1234567"
        );

        account = Account.builder()
                .id(1L)
                .accountNumber("1234567890")
                .balance(100.0)
                .status("ACTIVE")
                .type("SAVING")
                .customerId(1234567L)
                .build();

        accountResponse = new AccountResponse(
                1L,
                "1234567890",
                100.0,
                "ACTIVE",
                "SAVING",
                1234567L
        );

        // Using lenient() to avoid unnecessary stubbing errors
        lenient().when(accountMapper.toEntity(any(AccountRequest.class))).thenReturn(account);
        lenient().when(accountMapper.toResponse(any(Account.class))).thenReturn(accountResponse);
    }

    @Test
    void testCreateAccount_Success() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(accountMapper.toResponse(any(Account.class))).thenReturn(accountResponse);

        AccountResponse response = accountService.createAccount(accountRequest);

        assertEquals("1234567890", response.accountNumber());
        assertEquals(100.0, response.balance());
    }

    @Test
    void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(account));
        when(accountMapper.toResponse(any(Account.class))).thenReturn(accountResponse);

        List<AccountResponse> response = accountService.getAllAccounts();

        assertEquals(1, response.size());
        assertEquals("1234567890", response.get(0).accountNumber());
    }
}