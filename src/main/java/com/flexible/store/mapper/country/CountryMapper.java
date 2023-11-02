package com.flexible.store.mapper.country;

import com.flexible.store.dto.country.CountryDto;
import com.flexible.store.entity.CountryEntity;
import com.flexible.store.mapper.abstraction.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CountryMapper implements EntityMapper<CountryEntity, CountryDto> {
}
