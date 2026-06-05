package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.RedefinirSenhaRequest;
import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedefinirSenhaUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(RedefinirSenhaRequest request) {
        String senhaHash = passwordEncoder.encode(request.getNovaSenha());
        usuarioRepository.updateSenha(request.getEmail(), senhaHash);
    }
}
