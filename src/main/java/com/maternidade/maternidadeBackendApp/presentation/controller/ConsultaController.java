package com.maternidade.maternidadeBackendApp.presentation.controller;

import com.maternidade.maternidadeBackendApp.application.dto.request.ConsultaRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.MensagemResponse;
import com.maternidade.maternidadeBackendApp.application.usecase.CriarConsultaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Consultas", description = "Agendamento de consultas de psicologia")
public class ConsultaController {

    private final CriarConsultaUseCase criarConsultaUseCase;

    @Operation(summary = "Registrar nova consulta de acolhimento psicológico")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Consulta registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/consulta")
    public ResponseEntity<MensagemResponse> criarConsulta(@Valid @RequestBody ConsultaRequest request) {
        criarConsultaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MensagemResponse.of("Consulta registrada com sucesso!"));
    }
}
