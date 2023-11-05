package com.flexible.store.dto.refreshtoken;

import com.flexible.store.dto.abstraction.BaseDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto extends BaseDto {
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private Long userAccountId;
}
