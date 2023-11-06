package com.flexible.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "confirmation_token")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConfirmationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "last_edit_date")
    @UpdateTimestamp
    private LocalDateTime lastEditDate;
    @Column(name = "removed")
    private Boolean removed;
    @Column(name = "token")
    private String token;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;
    @Column(name = "user_account_id")
    private Long userAccountId;
}
