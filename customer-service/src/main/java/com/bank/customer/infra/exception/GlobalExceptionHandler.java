package com.bank.customer.infra.exception;

import com.bank.customer.infra.exception.base.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(BaseException ex) {
        return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
    }
}