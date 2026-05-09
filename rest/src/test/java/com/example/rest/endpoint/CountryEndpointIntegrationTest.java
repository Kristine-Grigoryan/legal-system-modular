package com.example.rest.endpoint;
import com.example.model.Country;
import com.example.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CountryEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void cleanDb() {
        countryRepository.deleteAll();
    }

    @Test
    void getCountries_returnsPersistedRows() throws Exception {
        countryRepository.save(new Country(0, "Armenia", "ARM"));
        countryRepository.save(new Country(0, "France", "FRA"));

        mockMvc.perform(get("/countries").with(user("any")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                // CountryMapper maps entity.name -> dto.country
                .andExpect(jsonPath("$[?(@.code == 'ARM')].country").value("Armenia"));
    }

    @Test
    void getCountries_requiresAuthentication() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isUnauthorized());
    }
}