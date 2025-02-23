package com.bank.customer.events.producer;

import com.bank.customer.clients.CustomerEventClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerEventProducer {

    private final CustomerEventClient eventClient;

    public void sendCustomerCreatedEvent(Long customerId, String name, String legalId, String type, String address) {
        Map<String, Object> event = Map.of(
                "id", customerId,
                "name", name,
                "legalId", legalId,
                "type", type,
                "address", address
        );

        log.info("Sending Customer Created Event: {}", event);
        eventClient.sendCustomerCreatedEvent(event);
    }
}