package com.bank.account.events.producer;

import com.bank.account.clients.AccountEventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventProducer {

    private final AccountEventClient eventClient;

    public void sendAccountInitiateEvent(Object event) {
        eventClient.processAccountInitiateEvent(event);
    }
}