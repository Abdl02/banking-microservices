package com.bank.customer.api.dto.response;

/**
 * DTO representing an account response.
 * <p>
 * This record encapsulates account details returned from the service layer to the client.
 * </p>
 *
 * @param id The unique account ID.
 * @param accountNumber The account number.
 * @param balance The current balance in the account.
 * @param status The account status.
 * @param type The account type (SAVING, SALARY, INVESTMENT).
 * @param customerId The ID of the customer who owns this account.
 */
public record AccountResponse(
        Long id,
        String accountNumber,
        Double balance,
        String status,
        String type,
        Long customerId
) {}