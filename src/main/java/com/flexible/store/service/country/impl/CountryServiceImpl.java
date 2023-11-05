package com.flexible.store.service.country.impl;

import com.flexible.store.dto.country.CountryDto;
import com.flexible.store.entity.CountryEntity;
import com.flexible.store.service.country.CountryService;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl extends CrudServiceImpl<CountryEntity, CountryDto> implements CountryService {
    @Override
    @Cacheable(cacheNames = "countries")
    public List<CountryEntity> getAll() {
        return super.getAll();
    }

    @Override
    @CacheEvict(cacheNames = "countries", allEntries = true)
    public List<CountryEntity> saveAll(List<CountryDto> countryDtos) {
        return super.saveAll(countryDtos);
    }

    @Override
    @CacheEvict(cacheNames = "countries", allEntries = true)
    public CountryEntity save(CountryDto dto) {
        return super.save(dto);
    }
}
