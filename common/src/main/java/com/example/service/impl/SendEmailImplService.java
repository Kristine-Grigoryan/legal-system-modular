package com.example.service.impl;

import com.example.service.SendEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
@Service
public class SendEmailImplService  implements SendEmailService {



@Override
@Async
public void sendMail(String to, String subject, String content) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(content);
    emailSender.send(simpleMailMessage);
}

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public SendEmailImplService(JavaMailSender emailSender,
                                @Qualifier("emailTemplateEngine") TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

@Override
@Async
public void sendVerificationMailHtml(String to, String verifyCode) throws MessagingException {
    final Context ctx = new Context(Locale.ENGLISH);
    ctx.setVariable("code", verifyCode);

    // Prepare message using a Spring helper
    final MimeMessage mimeMessage = emailSender.createMimeMessage();
    final MimeMessageHelper message =
            new MimeMessageHelper(mimeMessage, false, "UTF-8"); // true = multipart
    message.setSubject("Please verify your email address");
    message.setFrom("java92850@gmail.com");
    message.setTo(to);

    // Create the HTML body using Thymeleaf
    final String htmlContent = templateEngine.process("verificationMailTemplate", ctx);
    message.setText(htmlContent, true); // true = isHtml

    emailSender.send(mimeMessage);

}
}
