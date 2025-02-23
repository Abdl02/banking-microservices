package com.bank.customer.infra.exception;

import com.bank.customer.infra.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException(Long id) {
        super("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
}