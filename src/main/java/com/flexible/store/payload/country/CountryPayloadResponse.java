package com.flexible.store.payload.country;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryPayloadResponse {
    private LocalDateTime createDate;
    private LocalDateTime lastEditDate;
    private Boolean removed;
    private String name;
    private String code;
}
