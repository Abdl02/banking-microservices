package com.bank.account.events.producer;

import com.bank.account.clients.AccountEventClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Kafka producer for sending account-related events.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountEventProducer {

    private final AccountEventClient eventClient;

    /**
     * Sends an event when an account is created.
     *
     * @param customerId The customer associated with the account.
     * @param accountType The type of the created account.
     */
    public void sendAccountInitiateEvent(Long customerId, String accountType) {
        Map<String, Object> event = Map.of(
                "customerId", customerId,
                "accountType", accountType
        );
        log.info("Sending Account Initiate Event: {}", event);
        eventClient.processAccountInitiateEvent(event);
    }
}