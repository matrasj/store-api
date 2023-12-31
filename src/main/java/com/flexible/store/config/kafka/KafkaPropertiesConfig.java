package com.flexible.store.config.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaPropertiesConfig {
    @Value("${kafka.topic.registrations.name}")
    private String registrationsTopicName;
}
