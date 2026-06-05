package com.maternidade.maternidadeBackendApp.domain.service;

public interface EmailSenderPort {
    void sendVerificationCode(String to, String code);
}
