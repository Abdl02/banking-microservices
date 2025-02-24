package com.bank.account.clients;

import com.bank.account.api.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the Customer Service.
 * <p>
 * Provides REST API communication between Account Service and Customer Service.
 * </p>
 */
@FeignClient(name = "customer-service", url = "http://localhost:8081")
public interface CustomerClient {

    /**
     * Retrieves customer details by customer ID.
     *
     * @param customerId The unique identifier of the customer.
     * @return Customer details.
     */
    @GetMapping("/api/customers/{customerId}")
    CustomerResponse getCustomerById(@PathVariable("customerId") Long customerId);
}