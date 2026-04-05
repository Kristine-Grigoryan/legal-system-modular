package com.example.service.impl;
import com. example.model.User;
import com.example.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import  com.example.repository.UserRepository;
import com.example.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Random;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final SendEmailService sendEmailService;
    private final Random random = new Random();

    @Override
    public void save(User user) {
        user.setEnabled(false);
        String verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        userRepository.save(user);
        if (user.getUsername().contains("@")) {
            try {
                sendEmailService.sendVerificationMailHtml(user.getUsername(),
                        verificationCode);
            } catch (MessagingException e) {
                log.error("Error sending email: {}", e.getMessage());
            }
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean verifyUser(String email, String verifyCode) {
        Optional<User> user = userRepository.findByUsername(email);
        if (user.isPresent() && user.get().getVerificationCode().equals(verifyCode)) {
            user.get().setVerificationCode(null);
            user.get().setEnabled(true);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        int code = random.nextInt(1000, 9999);
        return String.valueOf(code);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }




}

