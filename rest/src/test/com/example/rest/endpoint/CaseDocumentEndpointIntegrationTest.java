package com.example.rest.endpoint;

import com.example.rest.dto.CaseDocumentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CaseDocumentEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCaseDocument() throws Exception {
        CaseDocumentDto dto = CaseDocumentDto.builder()
                .fileName("evidence_01.pdf")
                .filePath("/storage/cases/10/evidence_01.pdf")
                .fileType("application/pdf")
                .uploadedAt(LocalDateTime.now())
                .caseId(10L)
                .build();

        mockMvc.perform(post("/caseDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fileName").value("evidence_01.pdf"))
                .andExpect(jsonPath("$.fileType").value("application/pdf"));
    }

    @Test
    public void testGetAllCaseDocuments() throws Exception {
        mockMvc.perform(get("/caseDocuments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCaseDocumentById() throws Exception {
        mockMvc.perform(get("/caseDocuments/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testDeleteCaseDocument() throws Exception {
        mockMvc.perform(delete("/caseDocument/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
