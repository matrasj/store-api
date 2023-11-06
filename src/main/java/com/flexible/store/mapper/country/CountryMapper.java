package com.flexible.store.mapper.country;

import com.flexible.store.entity.CountryEntity;
import com.flexible.store.payload.country.CountryPayloadResponse;

public class CountryMapper {
    public static CountryPayloadResponse fromEntityToPayloadResponse(CountryEntity country) {
        return CountryPayloadResponse.builder()
                .code(country.getCode())
                .name(country.getName())
                .lastEditDate(country.getLastEditDate())
                .createDate(country.getCreateDate())
                .removed(country.getRemoved())
                .build();
    }
}
