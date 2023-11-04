package com.flexible.store.kafkaproducer;

import com.flexible.store.payload.useraccount.RegistrationEventPayload;

public interface KafkaProducer {
    void publishMessageAboutRegistration(RegistrationEventPayload registrationEventPayload);
}
