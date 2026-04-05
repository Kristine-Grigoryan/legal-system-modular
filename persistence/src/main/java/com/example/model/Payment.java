package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paidDate;
    private String method;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "case_id")
    private LegalCase legalCase;
}
