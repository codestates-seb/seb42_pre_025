package com.stackoverflow.team25.mail.sender;

public interface EmailSender {
    void send(String[] to, String subject, String message, String templateName) throws InterruptedException;
}
