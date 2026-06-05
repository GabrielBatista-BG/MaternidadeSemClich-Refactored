package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import com.maternidade.maternidadeBackendApp.domain.service.EmailSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class EnviarCodigoUseCase {

    private final UsuarioRepository usuarioRepository;
    private final EmailSenderPort emailSenderPort;

    public void execute(String email) {
        if (!usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        String code = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
        usuarioRepository.updateCodigoVerificacao(email, code);
        emailSenderPort.sendVerificationCode(email, code);
    }
}
