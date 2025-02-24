package com.bank.customer.clients;

import com.bank.customer.api.dto.response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 * Feign client for interacting with the Account Service.
 * <p>
 * Provides REST API communication between Customer Service and Account Service.
 * </p>
 */
@FeignClient(name = "account-service", url = "http://localhost:8082")
public interface AccountClient {

    /**
     * Retrieves all accounts associated with a given customer.
     *
     * @param customerId The unique identifier of the customer.
     * @return List of associated accounts.
     */
    @GetMapping("/api/accounts/customer/{customerId}")
    List<AccountResponse> getAccountsByCustomerId(@PathVariable("customerId") Long customerId);
}