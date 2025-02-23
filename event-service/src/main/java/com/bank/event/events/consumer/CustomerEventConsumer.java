package com.bank.event.events.consumer;

import com.bank.event.events.CustomerCreatedEvent;
import com.bank.event.events.AccountInitiateEvent;
import com.bank.event.events.producer.EventKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerEventConsumer {

    private final EventKafkaProducer eventKafkaProducer;

    @KafkaListener(topics = "customer-events", groupId = "banking-group")
    public void processCustomerEvent(CustomerCreatedEvent event) {
        log.info("Processing customer created event: {}", event);

        // Create an AccountInitiateEvent
        AccountInitiateEvent accountEvent = new AccountInitiateEvent(event.getId(), "SALARY");

        // Publish it to Kafka
        eventKafkaProducer.publishAccountInitiateEvent(accountEvent);
    }
}