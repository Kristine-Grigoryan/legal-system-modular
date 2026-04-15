package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "legal_case")
public class LegalCase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String pictureName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}