package com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "court_session")
public class CourtSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime sessionDate;
    private String cartroom;
    private String notes;


   @ManyToOne
   @JoinColumn(name="case_id")
   private  LegalCase  legalCase;
}
