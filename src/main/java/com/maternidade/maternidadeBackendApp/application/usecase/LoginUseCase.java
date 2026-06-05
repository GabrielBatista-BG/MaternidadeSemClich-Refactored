package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.LoginRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.AuthResponse;
import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import com.maternidade.maternidadeBackendApp.domain.exception.CredenciaisInvalidasException;
import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import com.maternidade.maternidadeBackendApp.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse execute(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(CredenciaisInvalidasException::new);

        boolean senhaCorreta = passwordEncoder.matches(request.getSenha(), usuario.getSenha());
        if (!senhaCorreta) {
            throw new CredenciaisInvalidasException();
        }

        String accessToken = jwtService.generateAccessToken(usuario);
        String refreshToken = jwtService.generateRefreshToken(usuario);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
