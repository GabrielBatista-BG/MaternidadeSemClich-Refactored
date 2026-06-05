package com.maternidade.maternidadeBackendApp.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoogleTokenRequest {

    @NotBlank
    private String tokenID;
}
