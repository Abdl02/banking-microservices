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

/**
 * Service class for managing bank accounts.
 * <p>
 * Handles business logic for account creation, retrieval, and validation.
 * Uses {@link AccountRepository} for database interactions and {@link AccountEventProducer}
 * for emitting events after account creation.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountEventProducer accountEventProducer;

    /**
     * Creates a new account for a customer.
     * Enforces a limit of 10 accounts per customer.
     *
     * @param request The request containing account details.
     * @return The created account's response object.
     * @throws IllegalStateException If the customer already has 10 accounts.
     */
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

    /**
     * Retrieves all existing bank accounts.
     *
     * @return A list of all accounts.
     */
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    /**
     * Retrieves a specific account by its ID.
     *
     * @param id The unique account ID.
     * @return The corresponding account response object.
     * @throws AccountNotFoundException If the account is not found.
     */
    public AccountResponse getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    /**
     * Generates a default account for a customer upon event reception.
     *
     * @param customerId The customer's unique ID.
     * @param accountType The type of account to create.
     * @throws IllegalStateException If the customer already has 10 accounts.
     */
    @Transactional
    public void createDefaultAccountForCustomer(Long customerId, String accountType) {
        List<Account> existingAccounts = accountRepository.findByCustomerId(customerId);
        if (existingAccounts.size() >= 10) {
            throw new IllegalStateException("A customer can have a maximum of 10 accounts.");
        }

        Account newAccount = Account.builder()
                .accountNumber(generateAccountNumber())
                .balance(0.0)
                .status("ACTIVE")
                .type(accountType)
                .customerId(customerId)
                .build();

        accountRepository.save(newAccount);
    }

    /**
     * Generates a random 10-digit account number.
     *
     * @return A randomly generated account number.
     */
    private String generateAccountNumber() {
        return String.valueOf((long) (Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }
}