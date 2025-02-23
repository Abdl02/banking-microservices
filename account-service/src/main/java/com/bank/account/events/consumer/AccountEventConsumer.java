package com.bank.account.events.consumer;

import com.bank.account.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.FixedBackOff;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableKafka
public class AccountEventConsumer {

    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "account-events",
            groupId = "banking-group",
            errorHandler = "kafkaErrorHandler"
    )
    public void processAccountInitiateEvent(String eventJson) {
        log.info("Processing Account Initiate Event: {}", eventJson);

        try {
            JsonNode event = objectMapper.readTree(eventJson);
            Long customerId = event.get("customerId").asLong();
            String accountType = event.get("accountType").asText();

            accountService.createDefaultAccountForCustomer(customerId, accountType);
        } catch (Exception e) {
            log.error("Error processing Account Initiate Event", e);
            throw e; // ✅ Re-throwing exception for retry
        }
    }

    @Bean
    public SeekToCurrentErrorHandler kafkaErrorHandler() {
        return new SeekToCurrentErrorHandler(new FixedBackOff(3000, 3));  // ✅ Retry 3 times before failing
    }
}