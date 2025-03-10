package com.bank.account.infra.exception;

import com.bank.account.infra.exception.base.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler for handling application-wide exceptions.
 * <p>
 * This class provides centralized exception handling using {@code @RestControllerAdvice}.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom exceptions extending {@link BaseException}.
     *
     * @param ex The custom exception.
     * @return ResponseEntity with the appropriate HTTP status and error message.
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(BaseException ex) {
        return ResponseEntity.status(ex.getStatus().value()).body(Map.of("message", ex.getMessage()));
    }

    /**
     * Handles account not found exceptions.
     *
     * @param ex The AccountNotFoundException.
     * @return ResponseEntity with a 404 Not Found status.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param ex The IllegalArgumentException.
     * @return ResponseEntity with a 400 Bad Request status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(400).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(400).body(errors);
    }

    /**
     * Handles general unhandled exceptions.
     *
     * @param ex The general exception.
     * @return ResponseEntity with a 500 Internal Server Error status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error: " + ex.getMessage()));
    }
}