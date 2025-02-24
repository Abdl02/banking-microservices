package com.bank.customer.api.controller;

import com.bank.customer.api.dto.request.CustomerRequest;
import com.bank.customer.api.dto.response.CustomerResponse;
import com.bank.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing customer-related operations.
 * <p>
 * Provides endpoints for creating, retrieving, and managing customer data.
 * </p>
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customers Management", description = "Endpoints to manage customers")
public class CustomerController {
    private final CustomerService customerService;

    /**
     * Creates a new customer.
     *
     * @param request The customer creation request payload.
     * @return ResponseEntity containing the created customer details.
     */
    @Operation(summary = "Create a new customer")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    /**
     * Retrieves all registered customers.
     *
     * @return List of all customers.
     */
    @Operation(summary = "Get all customers")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Retrieves a specific customer by ID.
     *
     * @param id The unique identifier of the customer.
     * @return Customer details.
     */
    @Operation(summary = "Get customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}