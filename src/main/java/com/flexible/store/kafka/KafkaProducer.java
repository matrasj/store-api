package com.flexible.store.kafka;

import com.flexible.store.payload.useraccount.RegistrationEventPayload;

public interface KafkaProducer {
    void publishMessageAboutRegistration(RegistrationEventPayload registrationEventPayload);
}
