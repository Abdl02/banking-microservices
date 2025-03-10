package com.bank.customer.service;

import com.bank.customer.api.dto.request.CustomerRequest;
import com.bank.customer.api.dto.response.CustomerResponse;
import com.bank.customer.events.producer.CustomerEventProducer;
import com.bank.customer.infra.exception.CustomerNotFoundException;
import com.bank.customer.infra.mapper.CustomerMapper;
import com.bank.customer.repository.entity.Customer;
import com.bank.customer.repository.repos.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Service class for handling customer-related business logic.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerEventProducer customerEventProducer;

    /**
     * Creates a new customer and triggers an event.
     *
     * @param request The customer creation request payload.
     * @return The created customer details.
     */
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByLegalId(request.legalId())) {
            throw new IllegalArgumentException("A customer with this legal ID already exists.");
        }

        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);

        // Send event to Event Service after customer creation
        customerEventProducer.sendCustomerCreatedEvent(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getLegalId(),
                savedCustomer.getType(),
                savedCustomer.getAddress()
        );

        return customerMapper.toResponse(savedCustomer);
    }

    /**
     * Retrieves all customers.
     *
     * @return List of all customers.
     */
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id The customer ID.
     * @return The customer details.
     * @throws CustomerNotFoundException if the customer does not exist.
     */
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}