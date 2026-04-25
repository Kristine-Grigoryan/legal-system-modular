package com.example.rest.dto;

import com.example.model.LegalCase;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourtSessionDto {

        @Positive
        private Long id;
        @NotNull(message = "Session date is required")
        @FutureOrPresent(message = "Session date must be in the present or future")
        private LocalDateTime sessionDate;

        @NotBlank(message = "Courtroom cannot be blank")
        @Size(max = 20, message = "Courtroom name is too short")
        private String courtroom;

        @Size(max = 50, message = "Notes are too long, keep it under 50 characters")
        private String notes;

        @NotNull(message = "Case ID is required")
        private Long caseId;


        }









