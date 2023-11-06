package com.flexible.store.service.country;

import com.flexible.store.payload.country.CountryPayloadRequest;
import com.flexible.store.payload.country.CountryPayloadResponse;

import java.util.List;

public interface CountryService {
    CountryPayloadResponse create(CountryPayloadRequest countryPayloadRequest);
    List<CountryPayloadResponse> getAll();
    CountryPayloadResponse getById(Long countryId);
}
