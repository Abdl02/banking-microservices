package com.bank.customer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "event-service", url = "http://localhost:8083")
public interface CustomerEventClient {
    @PostMapping("/api/events/customer-created")
    void sendCustomerCreatedEvent(@RequestBody Object event);
}