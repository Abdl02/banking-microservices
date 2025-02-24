package com.bank.account.events.consumer;

import com.bank.account.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountEventConsumer {
//
//    private final AccountService accountService;
//    private final ObjectMapper objectMapper;
//
//    @KafkaListener(topics = "account-events", groupId = "banking-group")
//    public void processAccountInitiateEvent(String eventJson) {
//        log.info("Processing Account Initiate Event: {}", eventJson);
//        try {
//            JsonNode event = objectMapper.readTree(eventJson);
//            Long customerId = event.get("customerId").asLong();
//            String accountType = event.get("accountType").asText();
//
//            accountService.createDefaultAccountForCustomer(customerId, accountType);
//            log.info("Account created successfully for customerId: {}", customerId);
//        } catch (Exception e) {
//            log.error("Failed to process Account Initiate Event: {}", e.getMessage(), e);
//            throw new RuntimeException("Error processing event");  // Kafka will retry automatically
//        }
//    }
}