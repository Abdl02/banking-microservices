package com.bank.customer.repository.repos;

import com.bank.customer.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByLegalId(String legalId);
}
