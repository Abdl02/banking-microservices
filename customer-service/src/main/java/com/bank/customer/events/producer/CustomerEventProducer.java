package com.bank.customer.events.producer;

import com.bank.customer.clients.CustomerEventClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEventProducer {

    private final CustomerEventClient eventClient;

    public void sendCustomerCreatedEvent(Object event) {
        eventClient.sendCustomerCreatedEvent(event);
    }
}