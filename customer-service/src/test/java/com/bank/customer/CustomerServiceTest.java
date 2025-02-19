package com.bank.customer;

import com.bank.customer.api.dto.request.CustomerRequest;
import com.bank.customer.api.dto.response.CustomerResponse;
import com.bank.customer.infra.mapper.CustomerMapper;
import com.bank.customer.repository.entity.Customer;
import com.bank.customer.repository.repos.CustomerRepository;
import com.bank.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private CustomerRequest customerRequest;
    private Customer customer;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest(
                "1234567890",
                "John Doe",
                "1234567",
                "RETAIL",
                "NY"
        );

        customer = Customer.builder()
                .id(1L)
                .name("John Doe")
                .legalId("1234567")
                .type("RETAIL")
                .address("NY")
                .build();

        customerResponse = new CustomerResponse(
                1L,
                "John Doe",
                "1234567",
                "RETAIL",
                "NY"
        );

        // Mocking mapping behavior
        lenient().when(customerMapper.toEntity(any(CustomerRequest.class))).thenReturn(customer);
        lenient().when(customerMapper.toResponse(any(Customer.class))).thenReturn(customerResponse);
    }

    @Test
    void testCreateCustomer_Success() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse response = customerService.createCustomer(customerRequest);

        assertEquals("John Doe", response.name());
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<CustomerResponse> response = customerService.getAllCustomers();

        assertEquals(1, response.size());
        assertEquals("John Doe", response.get(0).name());
    }
}