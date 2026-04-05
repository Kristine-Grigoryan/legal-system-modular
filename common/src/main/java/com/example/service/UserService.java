package com.example.service;


import com.example.model.User;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void save(User user);

    Optional<User> findByUsername(String username);


    boolean verifyUser(String email, String verifyCode);


    List<User>findAll();


}


