package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.TokenRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.AuthResponse;
import com.maternidade.maternidadeBackendApp.infra.security.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidarTokenUseCase {

    private final JwtService jwtService;

    public AuthResponse execute(TokenRequest request) {
        // Tenta validar o access token primeiro
        if (request.getAccessToken() != null) {
            try {
                Claims claims = jwtService.validateAccessToken(request.getAccessToken());
                return AuthResponse.builder()
                        .mensagem("Access token válido")
                        .accessToken(request.getAccessToken())
                        .build();
            } catch (Exception ignored) {
                // Access token inválido, tenta o refresh token
            }
        }

        // Tenta renovar via refresh token
        if (request.getRefreshToken() != null) {
            Claims claims = jwtService.validateRefreshToken(request.getRefreshToken());
            String novoAccessToken = jwtService.generateAccessTokenFromClaims(claims);
            return AuthResponse.builder()
                    .mensagem("Novo access token gerado")
                    .accessToken(novoAccessToken)
                    .build();
        }

        throw new IllegalArgumentException("Tokens não fornecidos ou inválidos.");
    }
}
