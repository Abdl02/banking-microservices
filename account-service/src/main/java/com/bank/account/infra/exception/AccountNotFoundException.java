package com.bank.account.infra.exception;

import com.bank.account.infra.exception.base.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when an account is not found.
 * <p>
 * This exception extends {@link BaseException} and returns a 404 Not Found HTTP status.
 * </p>
 */
public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(Long id) {
        super("Account with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
}