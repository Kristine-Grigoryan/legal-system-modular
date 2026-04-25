package com.example.rest.endpoint;

import com.example.repository.CountryRepository;
import com.example.rest.dto.CountryDto;
import com.example.rest.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryEndpoint {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @GetMapping("/countries")
    public List<CountryDto> getCountries() {
        return countryMapper.toDto(countryRepository.findAll());
    }

}
