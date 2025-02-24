package com.bank.customer.repository.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a customer.
 * <p>
 * This class maps to the `customers` table in the database.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Full name of the customer.
     */
    private String name;

    /**
     * Legal ID of the customer (e.g., National ID, Tax ID).
     */
    private String legalId;

    /**
     * Type of customer (e.g., RETAIL, CORPORATE, INVESTMENT).
     */
    private String type;

    /**
     * Address of the customer.
     */
    private String address;
}