package com.maternidade.maternidadeBackendApp.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RedefinirSenhaRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String novaSenha;
}
