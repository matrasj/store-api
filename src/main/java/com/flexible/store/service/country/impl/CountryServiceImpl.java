package com.flexible.store.service.country.impl;

import com.flexible.store.dto.country.CountryDto;
import com.flexible.store.entity.CountryEntity;
import com.flexible.store.service.country.CountryService;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl extends CrudServiceImpl<CountryEntity, CountryDto> implements CountryService {
}
