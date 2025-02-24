package com.bank.account.infra.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base exception class for handling application-specific exceptions.
 * <p>
 * All custom exceptions should extend this class.
 * </p>
 */
@Getter
public abstract class BaseException extends RuntimeException {

    /**
     * HTTP status code associated with the exception.
     */
    private final HttpStatus status;

    /**
     * Constructor for defining a custom exception.
     *
     * @param message Error message.
     * @param status  HTTP status code.
     */
    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

