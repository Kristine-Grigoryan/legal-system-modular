package com.example.rest.endpoint;

import com.example.model.LegalCase;
import com.example.repository.LegalCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "USER")
public class CaseDocumentEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LegalCaseRepository legalCaseRepository;


    @Test
    @WithMockUser(roles = "USER")
    public void shouldCreateCaseDocument() throws Exception {

        LegalCase legalCase = new LegalCase();
        legalCase.setTitle("Test legalCase");
        legalCase = legalCaseRepository.save(legalCase);

        Long realCaseId = legalCase.getId();

        String documentJson = String.format("""
    {
        "fileName": "contract.pdf",
        "filePath": "/storage/documents/2026/01/",
        "fileType": "application/pdf",
        "uploadedAt": "2026-04-20T10:30:00",
        "caseId": 2
    }
    """, realCaseId);

        mockMvc.perform(post("/caseDocument")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(documentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fileName").value("contract.pdf"))
                .andExpect(jsonPath("$.caseId").value(realCaseId));
    }
}