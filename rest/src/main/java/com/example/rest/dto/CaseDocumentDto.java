package com.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseDocumentDto {



    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private LocalDateTime uploadedAt;
    private Long caseId;



}


