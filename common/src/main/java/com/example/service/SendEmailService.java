package com.example.service;

import jakarta.mail.MessagingException;

public interface SendEmailService {

void sendMail(String to, String subject, String content);

void sendVerificationMailHtml(String to, String verifyCode) throws MessagingException;

}
