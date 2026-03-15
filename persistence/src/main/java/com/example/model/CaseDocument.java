package com.example.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "case_document")
public class CaseDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="file_name")
    private String fileName;
    @Column(name="file_path")
    private String filePath;
    @Column(name="file_type")
    private String fileType;
    private LocalDateTime uploadedAt;

   @ManyToOne
   @JoinColumn(name="case_id")
   private LegalCase legalCase;
}
