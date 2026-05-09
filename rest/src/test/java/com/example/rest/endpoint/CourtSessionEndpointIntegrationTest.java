package com.example.rest.endpoint;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.model.LegalCase;
import com.example.repository.LegalCaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


    @WithMockUser(roles = "USER")
    public class CourtSessionEndpointIntegrationTest  extends BaseEndpointIntegrationTest {


        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private LegalCaseRepository legalCaseRepository;

        @Test
        @WithMockUser(username = "admin", roles = {"ADMIN"})
        public void shouldCreateCourtSession() throws Exception {
            LegalCase legalCase = new LegalCase();
            legalCase.setTitle("Test Legal Case");
            legalCase = legalCaseRepository.save(legalCase);

            Long savedCaseId = legalCase.getId();


            String sessionJson = """
                    {
                        "sessionDate": "2026-12-01T10:00:00",
                        "courtroom": "Room 101",
                        "notes": "Standard hearing",
                        "caseId": %d
                    }
                    """.formatted(savedCaseId);

            // 3. Kanchum enq Endpoint-y
            mockMvc.perform(post("/courtSession")
                            .with(csrf()) // Hamozvir, vor Security-n sa pahanjum e
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(sessionJson))
                    .andDo(print()) // Sa ognum e tesnel, te inch e galis het (Response)
                    .andExpect(status().isCreated()) // Kam isOk(), kaxvats qo logic-ic
                    .andExpect(jsonPath("$.caseId").value(savedCaseId));
        }


    }





