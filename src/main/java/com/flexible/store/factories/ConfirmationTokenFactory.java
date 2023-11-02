package com.flexible.store.factories;

import com.flexible.store.dto.useraccount.ConfirmationTokenDto;
import com.flexible.store.entity.ConfirmationTokenEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmationTokenFactory {
    public static ConfirmationTokenDto generateConfirmationToken(Long userAccountId) {
        return ConfirmationTokenDto.builder()
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .userAccountId(userAccountId)
                .build();
    }
}
