package com.flexible.store.kafka.impl;

import com.flexible.store.config.KafkaPropertiesConfig;
import com.flexible.store.kafka.KafkaProducer;
import com.flexible.store.payload.useraccount.RegistrationEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {
    private final KafkaTemplate<String, RegistrationEventPayload> kafkaUserAccountTemplate;
    private final KafkaPropertiesConfig kafkaPropertiesConfig;

    public void publishMessageAboutRegistration(RegistrationEventPayload registrationEventPayload) {
        Message<RegistrationEventPayload> message = MessageBuilder
                .withPayload(registrationEventPayload)
                .setHeader(KafkaHeaders.TOPIC, this.kafkaPropertiesConfig.getRegistrationsTopicName())
                .build();
        this.kafkaUserAccountTemplate.send(message);
    }
}
