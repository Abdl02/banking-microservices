package com.bank.customer.api.dto.request;

import jakarta.validation.constraints.*;

public record CustomerRequest (
        @Size(min = 7, max = 7, message = "Customer ID must be exactly 7 digits")
        String customerId,

        @NotBlank(message = "Name cannot be empty")
        String name,

        @NotBlank(message = "Legal ID cannot be empty")
        String legalId,

        @NotBlank(message = "Type cannot be empty")
        @Pattern(regexp = "RETAIL|CORPORATE|INVESTMENT", message = "Type must be RETAIL, CORPORATE, or INVESTMENT")
        String type,

        @NotBlank(message = "Address cannot be empty")
        String address
){}