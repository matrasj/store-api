package com.flexible.store.dto.country;

import com.flexible.store.dto.abstraction.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto extends BaseDto {
    private String name;
    private String code;
}
