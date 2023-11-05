package com.flexible.store.entity;

import com.flexible.store.entity.abstraction.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefreshTokenEntity extends BaseEntity {
    @Column(name = "token")
    private String token;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "user_account_id")
    private Long userAccountId;
}
