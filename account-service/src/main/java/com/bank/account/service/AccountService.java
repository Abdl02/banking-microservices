package com.bank.account.service;

import com.bank.account.api.dto.request.AccountRequest;
import com.bank.account.api.dto.response.AccountResponse;
import com.bank.account.clients.CustomerClient;
import com.bank.account.infra.mapper.AccountMapper;
import com.bank.account.repository.entity.Account;
import com.bank.account.repository.repos.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;
    private final AccountMapper accountMapper;

    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        List<Account> accounts = accountRepository.findByCustomerId(Long.parseLong(request.customerId()));
        if (accounts.size() >= 10) {
            throw new IllegalStateException("A customer can have a maximum of 10 accounts.");
        }

        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    public AccountResponse getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}