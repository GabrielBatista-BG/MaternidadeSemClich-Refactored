package com.maternidade.maternidadeBackendApp.infra.service;

import com.maternidade.maternidadeBackendApp.domain.service.EmailSenderPort;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderAdapter implements EmailSenderPort {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationCode(String to, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setTo(to);
            helper.setSubject("Recuperação de Senha - Maternidade Sem Clichê");
            helper.setText("Seu código de verificação é: " + code);
            mailSender.send(message);
            log.info("Código enviado para {}", to);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail para {}: {}", to, e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail.", e);
        }
    }
}
