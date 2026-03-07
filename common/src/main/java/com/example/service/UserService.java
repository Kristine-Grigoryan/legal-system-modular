package com.example.service;

import com.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByUsername(String username);


    List<User>findAll();

}
