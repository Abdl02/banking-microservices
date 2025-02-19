package com.bank.account.api.dto.response;

public record AccountResponse(
        Long id,
        String accountNumber,
        Double balance,
        String status,
        String type,
        Long customerId
) {
}