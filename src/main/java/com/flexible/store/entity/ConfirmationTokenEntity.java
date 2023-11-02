package com.flexible.store.entity;

import com.flexible.store.entity.abstraction.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "confirmation_token")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConfirmationTokenEntity extends BaseEntity {
    @Column(name = "token")
    private String token;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "user_account_id")
    private Long userAccountId;
}
