package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.RegisterRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.AuthResponse;
import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import com.maternidade.maternidadeBackendApp.domain.exception.EmailJaCadastradoException;
import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import com.maternidade.maternidadeBackendApp.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse execute(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException();
        }

        String senhaHash = passwordEncoder.encode(request.getSenha());

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(senhaHash)
                .dataNascimento(request.getNascimento())
                .telefone(request.getTelefone())
                .cpf(request.getCpf())
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        String accessToken = jwtService.generateAccessToken(salvo);
        String refreshToken = jwtService.generateRefreshToken(salvo);

        return AuthResponse.builder()
                .mensagem("Cadastro bem-sucedido")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
