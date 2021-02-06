package com.example.ISA2020.service;

public interface EmailNotificationService {
    void sendEmail(String to, String subject, String text);
}
