package com.example.rest.dto;

import com.example.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LegalCaseDto {


    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private String pictureName;
    private Status status;
    private Long userId;
}

