package com.bank.event.events.producer;

import com.bank.event.events.AccountInitiateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventProducer {

    private final KafkaTemplate<String, AccountInitiateEvent> kafkaTemplate;

    public void sendAccountInitiateEvent(AccountInitiateEvent event) {
        kafkaTemplate.send("account-events", event);
    }
}