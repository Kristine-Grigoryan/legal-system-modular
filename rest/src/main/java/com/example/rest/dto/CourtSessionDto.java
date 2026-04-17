package com.example.rest.dto;

import com.example.model.LegalCase;
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




        private Long id;
        private LocalDateTime sessionDate;
        private String courtroom;
        private String notes;
        private Long caseId;





}
