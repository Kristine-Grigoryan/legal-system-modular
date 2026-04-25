package com.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private Long id;
    private LocalDate paidDate;
    private String method;
    private Long caseId;
    private BigDecimal amount;
    private BigDecimal amountUSD;
    private BigDecimal amountEUR;
    private BigDecimal amountRUB;


}





