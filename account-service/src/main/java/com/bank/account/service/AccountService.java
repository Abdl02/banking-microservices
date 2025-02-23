package com.bank.account.service;

import com.bank.account.api.dto.request.AccountRequest;
import com.bank.account.api.dto.response.AccountResponse;
import com.bank.account.events.producer.AccountEventProducer;
import com.bank.account.infra.exception.AccountNotFoundException;
import com.bank.account.infra.mapper.AccountMapper;
import com.bank.account.repository.entity.Account;
import com.bank.account.repository.repos.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountEventProducer accountEventProducer;

    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        List<Account> accounts = accountRepository.findByCustomerId(Long.parseLong(request.customerId()));
        if (accounts.size() >= 10) {
            throw new IllegalStateException("A customer can have a maximum of 10 accounts.");
        }

        Account account = accountMapper.toEntity(request);
        Account savedAccount = accountRepository.save(account);

        // Send event to event-service after account creation
        accountEventProducer.sendAccountInitiateEvent(savedAccount.getCustomerId(), savedAccount.getType());

        return accountMapper.toResponse(savedAccount);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    public AccountResponse getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    // New method to handle account creation when an event is received
    @Transactional
    public void createDefaultAccountForCustomer(Long customerId, String accountType) {
        // Check if customer already has 10 accounts
        List<Account> existingAccounts = accountRepository.findByCustomerId(customerId);
        if (existingAccounts.size() >= 10) {
            throw new IllegalStateException("A customer can have a maximum of 10 accounts.");
        }

        // Create a default account
        Account newAccount = Account.builder()
                .accountNumber(generateAccountNumber())
                .balance(0.0)
                .status("ACTIVE")
                .type(accountType)
                .customerId(customerId)
                .build();

        accountRepository.save(newAccount);
    }

    // Helper method to generate a random 10-digit account number
    private String generateAccountNumber() {
        return String.valueOf((long) (Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }
}