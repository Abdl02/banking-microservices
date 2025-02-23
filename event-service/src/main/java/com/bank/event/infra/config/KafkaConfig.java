package com.bank.event.infra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic customerCreatedTopic() {
        return new NewTopic("customer-events", 1, (short) 1);
    }

    @Bean
    public NewTopic accountInitiateTopic() {
        return new NewTopic("account-events", 1, (short) 1);
    }
}
