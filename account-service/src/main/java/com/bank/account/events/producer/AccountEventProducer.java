package com.bank.account.events.producer;

import com.bank.account.clients.AccountEventClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountEventProducer {

    private final AccountEventClient eventClient;

    public void sendAccountInitiateEvent(Long customerId, String accountType) {
        Map<String, Object> event = Map.of(
                "customerId", customerId,
                "accountType", accountType
        );
        log.info("Sending Account Initiate Event: {}", event);
        eventClient.processAccountInitiateEvent(event);
    }
}