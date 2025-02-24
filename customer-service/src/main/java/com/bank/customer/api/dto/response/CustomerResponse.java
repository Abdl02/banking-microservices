package com.bank.customer.api.dto.response;

/**
 * DTO representing customer details.
 * <p>
 * This record is used as a response object when retrieving customer information.
 * </p>
 *
 * @param id The unique customer ID.
 * @param name The name of the customer.
 * @param legalId The unique legal ID of the customer.
 * @param type The type of the customer (RETAIL, CORPORATE, INVESTMENT).
 * @param address The address of the customer.
 */
public record CustomerResponse (
     Long id,
     String name,
     String legalId,
     String type,
     String address
){}