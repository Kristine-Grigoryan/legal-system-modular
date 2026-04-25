package com.example.rest.endpoint;

import com.example.rest.dto.CourtSessionDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class CourtSessionEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Test
    void shouldCreateCourtSession() throws Exception {
        CourtSessionDto request = CourtSessionDto.builder()
                .sessionDate(LocalDateTime.now().plusDays(1)) // @FutureOrPresent
                .courtroom("Room 101")
                .notes("Standard hearing")
                .caseId(1L)
                .build();

        mockMvc.perform(post("/courtSession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courtroom").value("Room 101"))
                .andExpect(jsonPath("$.caseId").value(1));
    }

    @Test
    void shouldGetAllCourtSessions() throws Exception {
        mockMvc.perform(get("/courtSessions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetCourtSessionById() throws Exception {
        Long sessionId = 1L;

        mockMvc.perform(get("/courtSessions/{id}", sessionId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateCourtSession() throws Exception {
        Long sessionId = 1L;
        CourtSessionDto updateRequest = CourtSessionDto.builder()
                .sessionDate(LocalDateTime.now().plusDays(2))
                .courtroom("Room 202")
                .notes("Updated notes")
                .caseId(1L)
                .build();

        mockMvc.perform(put("/courtSession/{id}", sessionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courtroom").value("Room 202"));
    }

    @Test
    void shouldDeleteCourtSession() throws Exception {
        Long sessionId = 1L;

        mockMvc.perform(delete("/courtSession/{id}", sessionId))
                .andExpect(status().isNoContent());
    }
}