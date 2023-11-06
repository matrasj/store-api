package com.flexible.store.service.country.impl;

import com.flexible.store.entity.CountryEntity;
import com.flexible.store.exception.WebApiException;
import com.flexible.store.exception.common.EntityNotFoundException;
import com.flexible.store.mapper.country.CountryMapper;
import com.flexible.store.payload.country.CountryPayloadRequest;
import com.flexible.store.payload.country.CountryPayloadResponse;
import com.flexible.store.repository.country.CountryRepository;
import com.flexible.store.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    @Override
    public CountryPayloadResponse create(CountryPayloadRequest countryPayloadRequest) {
        this.validateRequest(countryPayloadRequest);
        return CountryMapper.fromEntityToPayloadResponse(this.countryRepository.save(CountryEntity.builder()
                .name(countryPayloadRequest.getName())
                .code(countryPayloadRequest.getCode())
                .removed(Boolean.FALSE)
                .build()));
    }

    @Override
    public List<CountryPayloadResponse> getAll() {
        return this.countryRepository.findAll()
                .stream()
                .map(CountryMapper::fromEntityToPayloadResponse)
                .collect(Collectors.toList());
    }

    private void validateRequest(CountryPayloadRequest countryPayloadRequest) {
        if (this.countryRepository.findNotRemovedByName(countryPayloadRequest.getName()).isPresent()) {
            throw new WebApiException("Country with name: " + countryPayloadRequest.getName() + " already exists");
        }

        if (this.countryRepository.findNotRemovedByCode(countryPayloadRequest.getCode()).isPresent()) {
            throw new WebApiException("Country with code: " + countryPayloadRequest.getCode() + " already exists");
        }
    }

    @Override
    public CountryPayloadResponse getById(Long countryId) {
        return CountryMapper.fromEntityToPayloadResponse(
                this.countryRepository.findById(countryId).orElseThrow(EntityNotFoundException::new)
        );
    }
}
