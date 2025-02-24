package com.bank.account.repository.repos;

import com.bank.account.repository.entity.Account;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for accessing {@link Account} entities in the database.
 * <p>
 * Provides methods for querying accounts based on customer ID and account status.
 * </p>
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Retrieves a list of accounts for a given customer ID.
     *
     * @param customerId The customer's unique identifier.
     * @return A list of accounts belonging to the specified customer.
     */
    List<Account> findByCustomerId(Long customerId);

    /**
     * Retrieves accounts based on their status.
     *
     * @param status The status of the accounts (e.g., ACTIVE, INACTIVE, CLOSED).
     * @return A list of accounts matching the specified status.
     */
    @Query("SELECT a FROM Account a WHERE a.status = :status")
    List<Account> findByStatus(@Param("status") String status);
}