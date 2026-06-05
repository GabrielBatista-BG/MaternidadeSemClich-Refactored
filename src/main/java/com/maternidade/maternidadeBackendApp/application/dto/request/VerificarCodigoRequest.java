package com.maternidade.maternidadeBackendApp.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerificarCodigoRequest {

    @Email
    @NotBlank
    private String to;

    @NotBlank
    private String code;
}
