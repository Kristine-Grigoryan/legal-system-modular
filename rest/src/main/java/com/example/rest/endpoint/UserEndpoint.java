package com.example.rest.endpoint;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.rest.dto.SaveUserRequest;
import com.example.rest.dto.UserAuthRequest;
import com.example.rest.mapper.UserAuthResponse;
import com.example.rest.mapper.UserMapper;
import com.example.rest.util.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil tokenUtil;

    private final UserMapper userMapper;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest userAuthRequest) {

        Optional<User> byEmail = userRepository.findByUsername(userAuthRequest.getUsername());

        if (byEmail.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        User user = byEmail.get();
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .ok(UserAuthResponse.builder()
                            .token(tokenUtil.generateToken(user.getUsername()))
                            .name(user.getName())
                            .surname(user.getSurname())
                            .userId(user.getId())
                            .build());
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid SaveUserRequest saveUserRequest) {
        if (userRepository.findByUsername(saveUserRequest.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        saveUserRequest.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        userRepository.save(userMapper.toEntity(saveUserRequest));
        return ResponseEntity
                .ok()
                .build();
    }


}


