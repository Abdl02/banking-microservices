package com.bank.account.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client for sending events to the Event Service.
 * <p>
 * Facilitates event-driven communication between microservices.
 * </p>
 */
@FeignClient(name = "event-service", url = "http://localhost:8083")
public interface AccountEventClient {

    /**
     * Sends an account initiation event to the Event Service.
     *
     * @param event The event data payload.
     */
    @PostMapping("/api/events/account-initiate")
    void processAccountInitiateEvent(@RequestBody Object event);
}