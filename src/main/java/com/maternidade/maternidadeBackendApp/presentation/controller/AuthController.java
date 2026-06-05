package com.maternidade.maternidadeBackendApp.presentation.controller;

import com.maternidade.maternidadeBackendApp.application.dto.request.GoogleTokenRequest;
import com.maternidade.maternidadeBackendApp.application.dto.request.LoginRequest;
import com.maternidade.maternidadeBackendApp.application.dto.request.RegisterRequest;
import com.maternidade.maternidadeBackendApp.application.dto.request.TokenRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.AuthResponse;
import com.maternidade.maternidadeBackendApp.application.dto.response.GoogleUserResponse;
import com.maternidade.maternidadeBackendApp.application.usecase.GoogleAuthUseCase;
import com.maternidade.maternidadeBackendApp.application.usecase.LoginUseCase;
import com.maternidade.maternidadeBackendApp.application.usecase.RegisterUseCase;
import com.maternidade.maternidadeBackendApp.application.usecase.ValidarTokenUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Login, registro e validação de tokens")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ValidarTokenUseCase validarTokenUseCase;
    private final GoogleAuthUseCase googleAuthUseCase;

    @Operation(summary = "Login com email e senha")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }

    @Operation(summary = "Cadastro de novo usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Email já cadastrado ou dados inválidos")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerUseCase.execute(request));
    }

    @Operation(summary = "Validar access token ou renovar via refresh token")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Token válido ou novo access token gerado"),
        @ApiResponse(responseCode = "403", description = "Tokens inválidos ou expirados")
    })
    @PostMapping("/token")
    public ResponseEntity<AuthResponse> validarToken(@RequestBody TokenRequest request) {
        return ResponseEntity.ok(validarTokenUseCase.execute(request));
    }

    @Operation(summary = "Autenticação via Google")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Token Google válido"),
        @ApiResponse(responseCode = "403", description = "Token Google inválido")
    })
    @PostMapping("/tokenGoogle")
    public ResponseEntity<Map<String, GoogleUserResponse>> tokenGoogle(
            @Valid @RequestBody GoogleTokenRequest request) {
        GoogleUserResponse user = googleAuthUseCase.execute(request);
        return ResponseEntity.ok(Map.of("user", user));
    }
}
