package com.bank.account.api.dto.response;

public record CustomerResponse(
        Long id,
        String name,
        String legalId,
        String type,
        String address
) {}