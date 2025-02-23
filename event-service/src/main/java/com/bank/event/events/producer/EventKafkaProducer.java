package com.bank.event.events.producer;

import com.bank.event.events.CustomerCreatedEvent;
import com.bank.event.events.AccountInitiateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventKafkaProducer {

    private final KafkaTemplate<String, CustomerCreatedEvent> customerKafkaTemplate;
    private final KafkaTemplate<String, AccountInitiateEvent> accountKafkaTemplate;

    public void publishCustomerCreatedEvent(CustomerCreatedEvent event) {
        customerKafkaTemplate.send("customer-events", event);
    }

    public void publishAccountInitiateEvent(AccountInitiateEvent event) {
        accountKafkaTemplate.send("account-events", event);
    }
}