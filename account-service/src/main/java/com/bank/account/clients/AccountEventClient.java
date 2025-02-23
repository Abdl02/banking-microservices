package com.bank.account.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "event-service", url = "http://localhost:8083")
public interface AccountEventClient {
    @PostMapping("/api/events/account-initiate")
    void processAccountInitiateEvent(@RequestBody Object event);
}