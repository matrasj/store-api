package com.flexible.store.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaRegistrationTopicConfig {

    @Bean
    public NewTopic createKafkaRegistrationTopic() {
        return TopicBuilder
                .name("registration")
                .build();
    }
}
