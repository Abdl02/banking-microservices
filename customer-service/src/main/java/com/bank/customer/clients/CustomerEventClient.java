package com.bank.customer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client for sending customer-related events to the Event Service.
 */
@FeignClient(name = "event-service", url = "http://localhost:8083")
public interface CustomerEventClient {

    /**
     * Sends a customer creation event to the Event Service.
     *
     * @param event The event data payload.
     */
    @PostMapping("/api/events/customer-created")
    void sendCustomerCreatedEvent(@RequestBody Object event);
}