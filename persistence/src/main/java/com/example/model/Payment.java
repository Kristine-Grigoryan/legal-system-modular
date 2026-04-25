package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="paid_date")
    private LocalDate paidDate;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod method;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "case_id")
    private LegalCase legalCase;


}
