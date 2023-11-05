package com.flexible.store.dto.confirmationtoken;

import com.flexible.store.dto.abstraction.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConfirmationTokenDto extends BaseDto {
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private Long userAccountId;
}
