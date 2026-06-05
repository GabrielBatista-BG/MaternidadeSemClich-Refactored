package com.maternidade.maternidadeBackendApp.presentation.controller;

import com.maternidade.maternidadeBackendApp.application.dto.request.RedefinirSenhaRequest;
import com.maternidade.maternidadeBackendApp.application.dto.request.VerificarCodigoRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.MensagemResponse;
import com.maternidade.maternidadeBackendApp.application.usecase.RedefinirSenhaUseCase;
import com.maternidade.maternidadeBackendApp.application.usecase.VerificarCodigoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/esqueciSenha")
@RequiredArgsConstructor
@Tag(name = "Recuperação de Senha", description = "Fluxo de redefinição de senha via código de verificação")
public class EsqueciSenhaController {

    private final VerificarCodigoUseCase verificarCodigoUseCase;
    private final RedefinirSenhaUseCase redefinirSenhaUseCase;

    @Operation(summary = "Verificar código de recuperação enviado por email")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Código válido"),
        @ApiResponse(responseCode = "400", description = "Código inválido ou expirado")
    })
    @PostMapping("/codigoVerificacao")
    public ResponseEntity<MensagemResponse> verificarCodigo(
            @Valid @RequestBody VerificarCodigoRequest request) {
        verificarCodigoUseCase.execute(request);
        return ResponseEntity.ok(MensagemResponse.of("Código válido!"));
    }

    @Operation(summary = "Redefinir senha após verificação do código")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/redefinirSenha")
    public ResponseEntity<MensagemResponse> redefinirSenha(
            @Valid @RequestBody RedefinirSenhaRequest request) {
        redefinirSenhaUseCase.execute(request);
        return ResponseEntity.ok(MensagemResponse.of("Senha atualizada com sucesso!"));
    }
}
