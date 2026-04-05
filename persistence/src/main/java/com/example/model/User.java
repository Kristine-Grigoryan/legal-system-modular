package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer   id;

    private String name;
    private String surname;
    private String phone;
    private String address;
    private String username;
    private String password;
    private Boolean enabled;
    private String verificationCode;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LegalCase> cases;

}
