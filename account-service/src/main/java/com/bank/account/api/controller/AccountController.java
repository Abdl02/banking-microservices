package com.bank.account.api.controller;

import com.bank.account.api.dto.request.AccountRequest;
import com.bank.account.api.dto.response.AccountResponse;
import com.bank.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing bank accounts.
 * <p>
 * This controller provides endpoints for creating, retrieving, and listing accounts.
 * It delegates business logic execution to the {@link AccountService}.
 * </p>
 * <p>
 * Key Features:
 * - Create a new account.
 * - Retrieve all accounts.
 * - Fetch a specific account by ID.
 * </p>
 * <p>
 * Uses Spring Web annotations to handle HTTP requests and responses.
 * </p>
 */
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Management", description = "Endpoints to manage bank accounts")
public class AccountController {
    private final AccountService accountService;

    /**
     * Creates a new bank account for a customer.
     *
     * @param request Account request data.
     * @return The created account details wrapped in a ResponseEntity.
     */
    @Operation(summary = "Create a new account")
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    /**
     * Retrieves all bank accounts.
     *
     * @return A list of all accounts wrapped in a ResponseEntity.
     */
    @Operation(summary = "Get all accounts")
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    /**
     * Retrieves an account by its unique ID.
     *
     * @param id The ID of the account to retrieve.
     * @return The account details wrapped in a ResponseEntity.
     */
    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}