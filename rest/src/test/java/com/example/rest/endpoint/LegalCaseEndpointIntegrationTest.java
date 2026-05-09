package com.example.rest.endpoint;

import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.repository.LegalCaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class LegalCaseEndpointIntegrationTest extends BaseEndpointIntegrationTest {




    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(roles = "USER")
    void shouldGetAllLegalCases() throws Exception {

        legalCaseRepository.save(LegalCase.builder()
                .title("Case 1")
                .description("Test case")
                .amount(BigDecimal.valueOf(1000))
                .status(Status.OPEN)
                .build());

        mockMvc.perform(get("/legalCases")
                        .with(user("admin").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].title").value("Test Legal Case"));
    }

}