package com.flexible.store.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaRegistrationTopicConfig {
    private final KafkaPropertiesConfig kafkaPropertiesConfig;
    @Bean
    public NewTopic createKafkaRegistrationTopic() {
        return TopicBuilder
                .name(this.kafkaPropertiesConfig.getRegistrationsTopicName())
                .build();
    }
}
