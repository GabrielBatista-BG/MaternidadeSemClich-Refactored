package com.maternidade.maternidadeBackendApp.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsultaRequest {

    @NotBlank(message = "Motivo em branco!")
    private String motivo;

    @NotBlank(message = "Escolha uma data disponível!")
    private String data;

    @NotNull
    private Long userId;
}
