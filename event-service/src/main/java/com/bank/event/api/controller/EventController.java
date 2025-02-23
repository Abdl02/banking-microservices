package com.bank.event.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/events")
public class EventController {

    @PostMapping("/customer-created")
    public void handleCustomerCreated(@RequestBody Object event) {
        log.info("Received customer created event: {}", event);
    }

    @PostMapping("/account-initiate")
    public void handleAccountInitiate(@RequestBody Object event) {
        log.info("Received account initiate event: {}", event);
    }
}