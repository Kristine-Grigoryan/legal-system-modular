package com.example.rest.endpoint;

import com.example.model.Status;
import com.example.rest.dto.LegalCaseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LegalCaseEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Test
    void shouldCreateLegalCase() throws Exception {
        LegalCaseDto request = LegalCaseDto.builder()
                .title("Test Title")
                .description("Test Description")
                .amount(new BigDecimal("1500.50"))
                .status(Status.OPEN)
                .userId(1L)
                .build();

        mockMvc.perform(post("/legalCase") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.amount").value(1500.50));
    }

    @Test
    void shouldGetAllLegalCases() throws Exception {
        mockMvc.perform(get("/legalCases")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetLegalCaseById() throws Exception {
        Long caseId = 1L;

        mockMvc.perform(get("/legalaCases/{id}", caseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(caseId));
    }

    @Test
    void shouldDeleteLegalCase() throws Exception {
        Long caseId = 1L;

        mockMvc.perform(delete("/legalCase/{id}", caseId))
                .andExpect(status().isNoContent());
    }
}