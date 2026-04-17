package com.example.rest.dto;

import com.example.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

        private String name;
        private String surname;
        private String username;
        private String password;
        private String phone;
        private String address;
        private UserRole role;
    }

