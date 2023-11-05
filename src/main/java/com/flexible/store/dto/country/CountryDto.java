package com.flexible.store.dto.country;

import com.flexible.store.dto.abstraction.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto extends BaseDto {
    private String name;
    private String code;
}
