package com.bank.event.infra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

    /**
     * Creates a new Kafka topic for customer-related events.
     *
     * @return A {@link NewTopic} instance representing the "customer-events" topic.
     *         The topic is configured with 1 partition and a replication factor of 1.
     *         This topic can be used to publish events such as customer creation,
     *         updates, or deletions in a microservices architecture.
     */
    @Bean
    public NewTopic customerCreatedTopic() {
        return new NewTopic("customer-events", 1, (short) 1);
    }

    /**
     * Creates a new Kafka topic for account-related events.
     *
     * @return A {@link NewTopic} instance representing the "account-events" topic.
     *         The topic is configured with 1 partition and a replication factor of 1.
     *         This topic can be used to publish events such as account initialization,
     *         updates, or other account-related operations.
     */
    @Bean
    public NewTopic accountInitiateTopic() {
        return new NewTopic("account-events", 1, (short) 1);
    }
}