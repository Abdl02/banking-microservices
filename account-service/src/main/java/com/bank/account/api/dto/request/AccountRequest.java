package com.bank.account.api.dto.request;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) representing an account creation request.
 * <p>
 * This record is used to encapsulate the data required for creating a new account.
 * Includes validation constraints to ensure input correctness.
 * </p>
 *
 * @param accountNumber The 10-digit unique account number (validated).
 * @param balance The initial balance (must be non-negative).
 * @param status The status of the account (must be ACTIVE, INACTIVE, or CLOSED).
 * @param type The type of the account (SAVING, SALARY, or INVESTMENT).
 * @param customerId The customer ID (must be exactly 7 digits).
 */
public record AccountRequest (
        @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits")
        String accountNumber,

        @Min(value = 0, message = "Balance cannot be negative")
        Double balance,

        @Pattern(regexp = "ACTIVE|INACTIVE|CLOSED", message = "Status must be ACTIVE, INACTIVE, or CLOSED")
        String status,

        @Pattern(regexp = "SAVING|SALARY|INVESTMENT", message = "Type must be SAVING, SALARY, or INVESTMENT")
        String type,

        @Pattern(regexp = "\\d{7}", message = "Customer ID must be exactly 7 digits")
        String customerId
) {}