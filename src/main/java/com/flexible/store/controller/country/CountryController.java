package com.flexible.store.controller.country;

import com.flexible.store.payload.country.CountryPayloadRequest;
import com.flexible.store.payload.country.CountryPayloadResponse;
import com.flexible.store.service.country.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    @PreAuthorize("hasAuthority('COUNTRY_CREATE')")
    public ResponseEntity<CountryPayloadResponse> createCountry(@RequestBody @Valid CountryPayloadRequest countryPayloadRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.countryService.create(countryPayloadRequest));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('COUNTRY_READ')")
    public ResponseEntity<List<CountryPayloadResponse>> getAllCountries() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.countryService.getAll());
    }

    @GetMapping("/{countryId}")
    @PreAuthorize("hasAuthority('COUNTRY_READ')")
    public ResponseEntity<CountryPayloadResponse> getById(@PathVariable Long countryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.countryService.getById(countryId));
    }
}
