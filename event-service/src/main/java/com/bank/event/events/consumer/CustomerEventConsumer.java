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
        log.info("Processing Customer Created Event: {}", event);

        // Create a default account event and send it to Kafka
        AccountInitiateEvent accountEvent = new AccountInitiateEvent(event.getId(), "SALARY");
        eventKafkaProducer.publishAccountInitiateEvent(accountEvent);
    }
}