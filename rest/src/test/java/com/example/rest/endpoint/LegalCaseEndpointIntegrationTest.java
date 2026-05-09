package com.example.rest.endpoint;

import com.example.model.LegalCase;
import com.example.model.Status;
import com.example.repository.CaseDocumentRepository;
import com.example.repository.LegalCaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class LegalCaseEndpointIntegrationTest extends BaseEndpointIntegrationTest {


    @Autowired
    private CaseDocumentRepository caseDocumentRepository;

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithMockUser(roles = "USER")
    void shouldGetAllLegalCases() throws Exception {


        caseDocumentRepository.deleteAll();
        legalCaseRepository.deleteAll();

        List<LegalCase> cases = List.of(
                LegalCase.builder()
                        .title("Test Legal Case")
                        .description("Description 1")
                        .amount(BigDecimal.valueOf(1000))
                        .status(Status.OPEN)
                        .build(),

                LegalCase.builder()
                        .title("Case 2")
                        .description("Description 2")
                        .amount(BigDecimal.valueOf(2000))
                        .status(Status.CLOSED)
                        .build(),

                LegalCase.builder()
                        .title("Case 3")
                        .description("Description 3")
                        .amount(BigDecimal.valueOf(3000))
                        .status(Status.OPEN)
                        .build()
        );

        legalCaseRepository.saveAll(cases);

        mockMvc.perform(get("/legalCases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }


}