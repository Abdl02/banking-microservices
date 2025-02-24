package com.bank.account.repository.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a bank account.
 * <p>
 * This class maps to the `accounts` table in the database.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "accounts")
public class Account {

    /**
     * Unique identifier for the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique 10-digit account number.
     */
    private String accountNumber;

    /**
     * Current balance in the account.
     */
    private Double balance;

    /**
     * Status of the account (e.g., ACTIVE, INACTIVE, CLOSED).
     */
    private String status;

    /**
     * Type of account (e.g., SAVING, SALARY, INVESTMENT).
     */
    private String type;

    /**
     * The customer ID associated with this account.
     */
    private Long customerId;
}