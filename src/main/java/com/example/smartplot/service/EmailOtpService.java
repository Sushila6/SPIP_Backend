package com.example.smartplot.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpService {

    private final ObjectProvider<JavaMailSender> mailSenderProvider;
    private final String fromEmail;

    public EmailOtpService(
            ObjectProvider<JavaMailSender> mailSenderProvider,
            @Value("${app.mail.from:}") String fromEmail
    ) {
        this.mailSenderProvider = mailSenderProvider;
        this.fromEmail = fromEmail;
    }

    public void sendOtpEmail(String toEmail, String subject, String heading, String otpCode) {
        JavaMailSender mailSender = mailSenderProvider.getIfAvailable();
        if (mailSender == null || fromEmail == null || fromEmail.isBlank()) {
            throw new IllegalArgumentException("Email OTP is not configured yet. Set mail credentials in application properties or environment variables.");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(buildBody(heading, otpCode));
        mailSender.send(message);
    }

    private String buildBody(String heading, String otpCode) {
        return heading + System.lineSeparator()
                + System.lineSeparator()
                + "Your SmartPlot verification code is: " + otpCode + System.lineSeparator()
                + "This code will expire in 5 minutes." + System.lineSeparator()
                + System.lineSeparator()
                + "If you did not request this code, you can ignore this email." + System.lineSeparator()
                + System.lineSeparator()
                + "Team SmartPlot";
    }
}
