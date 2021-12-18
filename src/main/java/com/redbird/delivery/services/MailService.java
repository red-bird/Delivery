package com.redbird.delivery.services;

public interface MailService {
    public void send(String emailTo, String subject, String message);
}
