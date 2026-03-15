package com.example.dto;



import com.example.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LegalCaseSearchCriteria {

    private String title;
    private String description;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
