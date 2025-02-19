package com.bank.account.api.dto.request;

import jakarta.validation.constraints.*;

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