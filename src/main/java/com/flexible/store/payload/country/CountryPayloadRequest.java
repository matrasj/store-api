package com.flexible.store.payload.country;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryPayloadRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Code can not be blank")
    private String code;
}
