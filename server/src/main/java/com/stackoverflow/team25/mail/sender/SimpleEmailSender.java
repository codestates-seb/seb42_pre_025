package com.stackoverflow.team25.mail.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class SimpleEmailSender implements EmailSender{
    private final JavaMailSender javaMailSender;

    public SimpleEmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String[] to, String subject, String message, String templateName) throws InterruptedException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setText(message);
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);

        log.info("#Sent simple email!");
    }
}
