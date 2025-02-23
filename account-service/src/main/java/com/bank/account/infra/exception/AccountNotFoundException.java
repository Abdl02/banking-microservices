package com.bank.account.infra.exception;

import com.bank.account.infra.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(Long id) {
        super("Account with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
}